/*
 * Created 01-06, 2022 by Geo.  The MIT free software license is incorporated by reference.
 */
package net.stumpwiz.mrradb.utilities;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Constants
{
    public static final String BACKUP_FILE = "raj_backup.sql";
    public static final String BAD_REPORT_SPEC = "Bad report spec: ";
    public static final String BLANK = "";
    public static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final int MAX_BODY_IMAGE_LENGTH = 45;
    public static final String EXPIRATIONS = "expirations";
    public static final String EXPIRATIONS_REPORT = "expirations";
    public static final int FIRST_ELEMENT = 0;
    public static final String IMAGE_PATH = "C:\\Users\\Geo\\OneDrive\\IdeaProjects\\mrradb\\src\\main\\resources\\img\\";
    public static final int MAX_BODY_MISSION_LENGTH = 512;
    public static final int MAX_BODY_NAME_LENGTH = 45;
    public static final int MAX_OFFICE_TITLE_LENGTH = 45;
    public static final LocalDate MAX_DATE = LocalDate.of(9999, 12, 31);
    public static final int MAX_PERSON_APT_LENGTH = 4;
    public static final int MAX_PERSON_EMAIL_LENGTH = 45;
    public static final int MAX_PERSON_FIRST_LENGTH = 15;
    public static final int MAX_PERSON_IMAGE_LENGTH = 45;
    public static final int MAX_PERSON_LAST_LENGTH = 30;
    public static final int MAX_PERSON_PHONE_LENGTH = 19;
    public static final int MAX_TERM_ORDINAL_LENGTH = 7;
    public static final int MIN_BODY_MISSION_LENGTH = 0;
    public static final int MIN_BODY_NAME_LENGTH = 5;
    public static final int MIN_OFFICE_TITLE_LENGTH = 5;
    public static final int MIN_PERSON_FIRST_LENGTH = 1;
    public static final int MIN_PERSON_LAST_LENGTH = 1;
    public static final LocalDate MIN_TERM_START = LocalDate.of(2002, 1, 1);
    public static final String OS_PATH = "C:\\Users\\Geo\\OneDrive\\MercyRidge\\AdminAssistant\\1Roster\\";
    public static final String MISSING_PERSON_IMAGE = "missingPhoto.png";
    public static final String PATH = "C:/Users/Geo/OneDrive/MercyRidge/AdminAssistant/1Roster/";
    public static final int SECOND_ELEMENT = 1;
    public static final String PASSWORD = System.getenv("MYSQL_PASSWORD");
    public static final int THIRD_ELEMENT = 2;
    public static final String ROSTER_LONG = "rosterLong";
    public static final String ROSTER_LONG_REPORT = "rosterLong";
    public static final String ROSTER_SHORT = "rosterShort";
    public static final String ROSTER_SHORT_REPORT = "rosterShort";
    public static final String USER_NAME = System.getenv("MYSQL_USER_NAME");
    public static final String TEX = ".tex";
    public static final String URL = "jdbc:mysql://localhost:3306/raj";
    public static final String VACANT = "(Vac";
    public static final BigInteger VACANT_ID = new BigInteger("18");
    static final List<String> VALID_ORDINALS = Arrays.asList("First", "Second", "None");
    private static final String EMAIL_PATTERN = "^[_A-Za-z\\d-+]+(\\" + ".[_A"
            + "-Za-z\\d-]+)*@" + "[A-Za-z\\d-]+(\\.[A-Za-z\\d]+)*(\\" + ".[A" +
            "-Za-z]{2,})$";
    public static final String VACANCIES = "vacancies";
    static final Pattern VALID_EMAIL = Pattern.compile(EMAIL_PATTERN);
    private static final String PHONE_PATTERN = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)"
            + "?)?(?:\\(" + "\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9" + "])\\s*\\)|" + "([2-9]1[02-9]|[2-9]"
            + "[02-8]1|[2-9][02-8][02-9" + "]))\\s*(?:[.-]\\s*)?)?" + "([2-9]1[02-9]|[2-9][02-9]1|" + "[2-9"
            + "][02-9]{2})\\s*(?:[.-]\\s*)?(\\d{4})(?:\\s*(?:#|x\\" + ".?|ext" + "\\.?" + "|extension)\\s*(\\d+))?$";
    static final Pattern VALID_PHONE = Pattern.compile(PHONE_PATTERN);
}
