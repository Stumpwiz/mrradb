/*
 * Created 01-18, 2022 by Geo.  The MIT free software license is incorporated by reference.
 */
package net.stumpwiz.mrradb.utilities;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * jOOQ set-ups for type safety and terseness in other classes.
 */
public class JooqDefs
{
    /***************************************************************************
     * BODY
     **************************************************************************/
    public static Field<BigInteger> bodyId = field("bodyid", BigInteger.class);
    public static Field<String> bodyImage = field("bodyimage", String.class);
    public static Field<String> bodyMission = field("mission", String.class);
    public static Field<String> bodyName = field("name", String.class);
    public static Field<BigDecimal> bodyPrecedence = field("bodyprecedence", BigDecimal.class);
    @SuppressWarnings("unused")
    public static List<Field<?>> bodyFields = Arrays.asList(bodyId, bodyImage, bodyName, bodyMission, bodyPrecedence);
    @SuppressWarnings("unused")
    public static Table<Record> bodyTable = table("body");
    /***************************************************************************
     * OFFICE
     **************************************************************************/
    public static Field<BigInteger> officeBodyId = field("officebodyid", BigInteger.class);
    public static Field<BigInteger> officeId = field("officeid", BigInteger.class);
    public static Field<BigDecimal> officePrecedence = field("officeprecedence", BigDecimal.class);
    public static Field<String> officeTitle = field("title", String.class);
    @SuppressWarnings("unused")
    public static List<Field<?>> officeFields = Arrays.asList(officeId, officeTitle, officePrecedence, officeBodyId);
    @SuppressWarnings("unused")
    public static Table<Record> officeTable = table("office");
    /***************************************************************************
     * PERSON
     **************************************************************************/
    public static Field<String> personApt = field("apt", String.class);
    public static Field<String> personEmail = field("email", String.class);
    public static Field<String> personFirst = field("first", String.class);
    public static Field<BigInteger> personId = field("personid", BigInteger.class);
    public static Field<String> personImage = field("personimage", String.class);
    public static Field<String> personLast = field("last", String.class);
    public static Field<String> personPhone = field("phone", String.class);
    @SuppressWarnings("unused")
    public static List<Field<?>> personFields = Arrays.asList(personId, personFirst, personLast, personEmail,
            personPhone, personApt, personImage);
    @SuppressWarnings("unused")
    public static Table<Record> personTable = table("person");
    /***************************************************************************
     * PERSON TERM OF OFFICE (PTO)
     **************************************************************************/
    public static Field<BigInteger> ptoPersonId = field("personid", BigInteger.class);
    public static Field<String> ptoPersonFirst = field("first", String.class);
    public static Field<String> ptoPersonLast = field("last", String.class);
    public static Field<String> ptoPersonEmail = field("email", String.class);
    public static Field<String> ptoPersonPhone = field("phone", String.class);
    public static Field<String> ptoPersonApt = field("apt", String.class);
    public static Field<LocalDate> ptoTermStart = field("start", LocalDate.class);
    public static Field<LocalDate> ptoTermEnd = field("end", LocalDate.class);
    public static Field<String> ptoTermOrdinal = field("ordinal", String.class);
    public static Field<BigInteger> ptoTermPersonId = field("termpersonid", BigInteger.class);
    public static Field<BigInteger> ptoTermOfficeId = field("termofficeid", BigInteger.class);
    public static Field<BigInteger> ptoOfficeId = field("officeid", BigInteger.class);
    public static Field<String> ptoOfficeTitle = field("title", String.class);
    public static Field<Double> ptoOfficePrecedence = field("officeprecedence", Double.class);
    public static Field<BigInteger> ptoOfficeBodyId = field("officebodyid", BigInteger.class);
    public static Field<BigInteger> ptoBodyId = field("bodyid", BigInteger.class);
    public static Field<String> ptoBodyName = field("name", String.class);
    public static Field<Double> ptoBodyPrecedence = field("bodyprecedence", Double.class);
    @SuppressWarnings("unused")
    public static List<Field<?>> ptoFields = Arrays.asList(ptoPersonId, ptoPersonFirst, ptoPersonLast,
            ptoPersonEmail, ptoPersonPhone, ptoPersonApt, ptoTermStart, ptoTermEnd, ptoTermOrdinal, ptoTermPersonId,
            ptoTermOfficeId, ptoOfficeId, ptoOfficeTitle, ptoOfficePrecedence, ptoOfficeBodyId, ptoBodyId,
            ptoBodyName, ptoBodyPrecedence);
    @SuppressWarnings("unused")
    public static Table<Record> ptoTable = table("pto");

    /***************************************************************************
     * TERM
     **************************************************************************/
    public static Field<LocalDate> termEnd = field("end", LocalDate.class);
    public static Field<BigInteger> termOfficeId = field("termofficeid", BigInteger.class);
    public static Field<String> termOrdinal = field("ordinal", String.class);
    public static Field<BigInteger> termPersonId = field("termpersonid", BigInteger.class);
    public static Field<LocalDate> termStart = field("start", LocalDate.class);
    @SuppressWarnings("unused")
    public static List<Field<?>> termFields = Arrays.asList(termStart, termEnd, termOrdinal, termPersonId,
            termOfficeId);
    @SuppressWarnings("unused")
    public static Table<Record> termTable = table("term");
}
