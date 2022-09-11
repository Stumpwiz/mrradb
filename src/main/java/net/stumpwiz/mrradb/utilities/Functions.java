/*
 * Created 01-23, 2022 by Geo.  The MIT free software license is incorporated by reference.
 */
package net.stumpwiz.mrradb.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.regex.Matcher;

import static net.stumpwiz.mrradb.utilities.Constants.*;

/**
 * Holds non-FXML functions.
 */
@SuppressWarnings("ALL")
public class Functions
{
    private static Matcher matcher;

    /**
     * Standard alert for run-time errors
     *
     * @param msg The error message.
     */
    public static void errorAlert(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        Label label = new Label(msg);
        label.setWrapText(true);
        alert.getDialogPane().setContent(label);
        alert.showAndWait();
    }

    /**
     * Formats the TEX file into a PDF, displays the PDF to the screen, and deletes the LuaLatex work files.
     *
     * @param fileName The name of the file to process.
     * @throws IOException          Thrown on attempt to delete a non-existent file; ignored.
     * @throws InterruptedException Thrown on interruption of wait for formatting thread.
     */
    public static void formatDisplayAndClean(String fileName)
            throws IOException, InterruptedException
    {
        /* Delete any prior report PDF's. */
        try {
            Files.delete(FileSystems.getDefault()
                    .getPath(OS_PATH + fileName + ".pdf"));
        } catch (IOException e) {
            //ignore if no predecessor.
        }

        /* Format the TEX file into a PDF. */
        String cmd = "cmd.exe /c lualatex --interaction=batchmode " +
                "--output-directory=" + PATH + " " + PATH + fileName + ".tex";
        gobble(cmd);
        Process process;
        StreamGobbler streamGobbler;
        int exitCode;

        /* Display the resulting PDF file. */
        cmd =
                "cmd.exe /c rundll32 url.dll,FileProtocolHandler " + OS_PATH + fileName + ".pdf";
        process = Runtime.getRuntime().exec(cmd);
        streamGobbler = new Functions.StreamGobbler(process.getInputStream(),
                System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        exitCode = process.waitFor();
        assert exitCode == 0;

        /* Clean up work files from the lualatex run. */
        try {
            Files.delete(FileSystems.getDefault().getPath(PATH + fileName +
                    ".aux"));
            Files.delete(FileSystems.getDefault().getPath(PATH + fileName +
                    ".log"));
            Files.delete(FileSystems.getDefault().getPath(PATH + fileName +
                    ".synctex.gz"));
        } catch (IOException e) {
            // ignore file not found.
        }
    }

    /**
     * Utility for running shell commands. https://www.baeldung .com/run-shell-command-in-java
     */
    public static void gobble(String cmd) throws IOException, InterruptedException
    {
        Process process = Runtime.getRuntime().exec(cmd);
        Functions.StreamGobbler streamGobbler = new Functions.StreamGobbler(process.getInputStream(),
                System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }

    /**
     * Standard alert for information messages.
     *
     * @param msg The information message.
     */
    public static void infoAlert(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        Label label = new Label(msg);
        label.setWrapText(true);
        alert.getDialogPane().setContent(label);
        alert.showAndWait();
    }

    /**
     * For validating apartment. Must either be <code>null</code>, zero length, or be in a list of valid apartments.
     */
    public static boolean isValidApt(String apt)
    {
        if (apt == null || apt.equals("")) return true;
        //TODO Make a list of valid apartments and implement
        // return VALID_APTS.contains(apt);
        return true;
    }

    /**
     * For validating email addresses. Must either be <code>null</code>, zero length,  or match a validating pattern.
     */
    public static boolean isValidEmail(String address)
    {
        if (address == null || address.equals("")) return true;
        matcher = VALID_EMAIL.matcher(address);
        return matcher.matches();
    }

    /**
     * For validating person first names. May not be null. Checks only for length.
     */
    public static boolean isValidFirst(String first)
    {
        if (first == null) return false;
        return (first.length() <= MAX_PERSON_FIRST_LENGTH && first.length() >= MIN_PERSON_FIRST_LENGTH);
    }

    /**
     * For validating person/body image file. May not be null. Checks only for length.
     */
    public static boolean isValidImage(String image)
    {
        if (image == null) return false;
        return (image.length() <= MAX_PERSON_IMAGE_LENGTH);
    }

    /**
     * For validating person last names. May not be null. Checks only for length.
     */
    public static boolean isValidLast(String last)
    {
        if (last == null) return false;
        return (last.length() <= MAX_PERSON_LAST_LENGTH && last.length() >= MIN_PERSON_LAST_LENGTH);
    }

    /**
     * For validating body mission. May be null. Checks only for length.
     */
    public static boolean isValidMission(String mission)
    {
        if (mission == null) return true;
        return (mission.length() <= MAX_BODY_MISSION_LENGTH);
    }

    /**
     * For validating body name. May not be null. Checks only for length.
     */
    public static boolean isValidName(String name)
    {
        if (name == null) return false;
        return (name.length() <= MAX_BODY_NAME_LENGTH && name.length() >= MIN_BODY_NAME_LENGTH);
    }

    /**
     * For validating term ordinals. Must be in a set of valid ordinals.
     */
    public static boolean isValidOrdinal(String ordinal)
    {
        return VALID_ORDINALS.contains(ordinal);
    }

    /**
     * For validating phone numbers. Must either be <code>null</code>, zero length, or match a validating pattern.
     */
    public static boolean isValidPhone(String number)
    {
        if (number == null || number.equals("")) return true;
        matcher = VALID_PHONE.matcher(number);
        return matcher.matches();
    }

    /**
     * For validating term start and end dates.  Must be non-null. Start must be before end.
     */
    public static boolean isValidStartEnd(LocalDate start, LocalDate end)
    {
        if (start == null || end == null) return false;
        return (start.isBefore(end));
    }

    /**
     * For validating office title. May not be null. Checks only for length.
     */
    public static boolean isValidTitle(String title)
    {
        if (title == null) return false;
        return (title.length() <= MAX_OFFICE_TITLE_LENGTH && title.length() >= MIN_OFFICE_TITLE_LENGTH);
    }

    public static class StreamGobbler implements Runnable
    {
        private final InputStream inputStream;
        private final Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer)
        {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run()
        {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }
}
