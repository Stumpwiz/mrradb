/*
 * Created 01-06, 2022 by Geo.  MIT free software license is incorporated by reference.
 */
package net.stumpwiz.mrradb.reports;

import net.stumpwiz.mrradb.generated.tables.records.PtoRecord;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static net.stumpwiz.mrradb.utilities.Constants.*;
import static net.stumpwiz.mrradb.utilities.JooqDefs.*;

public final class Reports
{
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public static void doExpirations(@NotNull DSLContext create) throws Exception
    {
        LocalDate endOfYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OS_PATH + EXPIRATIONS
                + TEX), StandardCharsets.UTF_8));
        List<PtoRecord> expiringAndVacantRecords = create
                .select(ptoFields).from(ptoTable)
                .where(termPersonId.eq(personId)
                        .and(termOfficeId.eq(officeId))
                        .and(officeBodyId.eq(bodyId)))
                .and(termEnd.le(endOfYear))
                .orderBy(bodyPrecedence, officePrecedence, personFirst,
                        personLast).fetchInto(PtoRecord.class);
        writeHeader(writer, "Vacancies and Expirations This Year\\footnote{Incumbents with " +
                "expiring second terms are term-limited.}");
        writer.write("\\begin{center} \n");
        writer.write("\\footnotesize \n");
        writer.write("\\begin{tabular}{llll} \n");
        writer.write("{\\em Body} & {\\em Office} & {\\em Incumbent} &  {\\em " + "Term} \\\\ \\hline \n");
        for (PtoRecord record : expiringAndVacantRecords) {
            LocalDate endOfTerm = LocalDate.parse(Objects.requireNonNull(termEnd.getValue(record)).toString());
            if (endOfTerm.isBefore(endOfYear) || endOfTerm.isEqual(endOfYear) &&
                    !Objects.equals(officeTitle.getValue(record), "Liaison")) {
                writer.write(bodyName.getValue(record) + " & " + officeTitle.getValue(record) + " & "
                        + personFirst.getValue(record) + " " + personLast.getValue(record) + " & " +
                        termOrdinal.getValue(record) + " \\\\ \n");
            }
        }
        writer.write("\\end{tabular} \n");
        writer.write("\\end{center} \n");
        writer.write("\\end{document} \n");
        writer.close();
    }

    /* Write the output TEX file header. */
    private static void writeHeader(Writer writer, String title) throws IOException
    {
        writer.write("\\documentclass[12pt,twoside]{article} \n");
        writer.write("\\usepackage{fullpage} \n");
        writer.write("\\usepackage{graphicx} \n");
        writer.write("\\usepackage{fancyhdr} \n");
        writer.write("\\usepackage{lastpage} \n");
        writer.write("\\usepackage{multicol} \n");
        writer.write("\\usepackage{sectsty} \n");
        writer.write("\\allsectionsfont{\\centering} \n");
        writer.write("\\begin{document} \n");
        LocalDateTime prepDate = LocalDateTime.now();
        writer.write("\\def\\prepDate{" + prepDate.format(formatter) + "} \n");
        writer.write("\\def\\version{Generated~\\prepDate} \n");
        writer.write("\\def\\footerLine{\\small \\version} \n");
        writer.write("\\begin{center}\\includegraphics{" + PATH + "residentCouncilLogoSmall.eps} \\\\ \n");
        writer.write("\\Large {\\bf  " + title + "}  \n");
        writer.write("\\end{center} \n");
        writer.write("\\pagestyle{fancy} \n");
        writer.write("\\fancyhead{} \n");
        writer.write("\\fancyfoot[ROF,LEF]{\\thepage} \n");
        writer.write("\\fancyfoot[LOF,REF]{\\footerLine} \n");
        writer.write("\\fancyfoot[COF,CEF]{} \n");
        writer.write("\\renewcommand{\\headrulewidth}{0.0pt} \n");
        writer.write("\\renewcommand{\\footrulewidth}{0.4pt} \n");
        writer.write("\\large \n");
        writer.write("\\setcounter{secnumdepth}{0} \n");
    }

    public static void doShortRoster(@NotNull DSLContext create) throws Exception
    {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OS_PATH + ROSTER_SHORT
                + TEX), StandardCharsets.UTF_8));
        /* Fetch body names in order of precedence for report sequencing. */
        Result<Record1<String>> rcBodies = create.select(bodyName).from(bodyTable).orderBy(bodyPrecedence).fetch();
        List<PtoRecord> records = create
                .select(ptoFields).from(ptoTable)
                .where(termPersonId.eq(personId)
                        .and(termOfficeId.eq(officeId))
                        .and(officeBodyId.eq(bodyId)))
                .orderBy(bodyName, officePrecedence, personFirst,
                        personLast).fetchInto(PtoRecord.class);
        writeHeader(writer, "Short Form Roster");
        writer.write("\\begin{multicols}{2} \n");
        for (Record1<String> bodyNameRecord : rcBodies) {
            String bodyName = bodyNameRecord.value1();
            writer.write("\\section{" + bodyName + "} \n ");
            writer.write("\\begin{center} \n");
            writer.write("\\footnotesize \n");
            writer.write("\\begin{tabular}{llc} \n");
            writer.write("{\\em Office} & {\\em Incumbent} \\\\ \\hline \n");
            for (PtoRecord record : records) {
                String currentBody = record.getName();
                if (currentBody.equals(bodyName)) {
                    writer.write(record.getTitle() + " & " + record.getFirst() + " "
                            + (record.getLast() == null ? BLANK : record.getLast() + " \\\\ \n"));
                }
            }
            writer.write("\\end{tabular} \n");
            writer.write("\\end{center} \n");
        }
        writer.write("\\end{multicols} \n");
        writer.write("\\end{document} \n");
        writer.close();
    }

    public static void doLongRoster(@NotNull DSLContext create) throws Exception
    {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OS_PATH
                + ROSTER_LONG_REPORT + TEX), StandardCharsets.UTF_8));
        Result<Record1<String>> rcBodies = create.select(bodyName).from(bodyTable).orderBy(bodyPrecedence).fetch();
        List<PtoRecord> records = create
                .select(ptoFields).from(ptoTable)
                .where(termPersonId.eq(personId)
                        .and(termOfficeId.eq(officeId))
                        .and(officeBodyId.eq(bodyId)))
                .orderBy(bodyName, officePrecedence, personFirst,
                        personLast).fetchInto(PtoRecord.class);
        writeHeader(writer, "Long Form Roster");
        for (Record1<String> bodyNameRecord : rcBodies) {
            String bodyName = bodyNameRecord.value1();
            writer.write("\\section{" + bodyName + "} \n ");
            writer.write("\\begin{center} \n");
            writer.write("\\footnotesize \n");
            writer.write("\\begin{tabular}{llllc} \n");
            writer.write("{\\em Incumbent} & {\\em Office} & {\\em Email} & {\\em Phone} & {\\em Apt} \\\\ \\hline \n");
            for (PtoRecord record : records) {
                String currentBody = record.getName();
                if (currentBody.equals(bodyName)) {
                    writer.write(record.getFirst() + " " + (record.getLast() == null ? BLANK : record.getLast())
                            + " & " + (record.getTitle() == null ? BLANK : record.getTitle()) + " & "
                            + (record.getEmail() == null ? BLANK : "{\\tt " + record.getEmail() + "}") + " & "
                            + (record.getPhone() == null ? BLANK : record.getPhone()) + " & "
                            + (record.getApt() == null ? BLANK : record.getApt())
                            + " \\\\ \n");
                }
            }
            writer.write("\\end{tabular} \n");
            writer.write("\\end{center} \n");
        }
        writer.write("\\end{document} \n");
        writer.close();
    }
}
