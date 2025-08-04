package in.trois.stockmanagement.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

    private static final ZoneId indianTimeZone = ZoneId.of("Asia/Kolkata");

    public static boolean validateDateRange(LocalDate fromDate, LocalDate toDate) {
        return toDate.isAfter(fromDate);
    }

    public static boolean isDateWithinPeriod(LocalDate fromDate, LocalDate toDate) {
        return !LocalDate.now(indianTimeZone).isBefore(fromDate)
                && !LocalDate.now(indianTimeZone).isAfter(toDate);
    }

    public static Long getDaysDifference(LocalDate fromDate, LocalDate toDate) {
        return ChronoUnit.DAYS.between(fromDate, toDate.plusDays(1));
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now(indianTimeZone);
    }

    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now(indianTimeZone);
    }

    public static boolean isBeforeOrEqualCurrentLocalDate(LocalDate date) {
        return date.isEqual(LocalDate.now(indianTimeZone))
                || date.isBefore(LocalDate.now(indianTimeZone));
    }

    public static boolean isSameOrFuture(LocalDate date1, LocalDate date2) {
        return date2 != null && (date2.isEqual(date1) || date2.isAfter(date1));
    }

    public static boolean isAfterOrEqualCurrentLocalDate(LocalDate date) {
        return date.isEqual(LocalDate.now(indianTimeZone))
                || date.isAfter(LocalDate.now(indianTimeZone));
    }

    public static boolean isAfterCurrentLocalDate(LocalDate date) {
        return date.isAfter(LocalDate.now(indianTimeZone));
    }

    public static Date getCurrentDate() {
        return Date.from(getCurrentLocalDateTime().atZone(indianTimeZone).toInstant());
    }

    public static String getCurrentMonthAndYear() {
        LocalDate now = getCurrentLocalDate();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    public static String getStringformatedDate(LocalDate date) {
        // Create DateTimeFormatter with desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Format the LocalDate object
        return date.format(formatter);
    }

    public static String getStringformatedLocalDateTime(LocalDateTime date) {
        // Create DateTimeFormatter with desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Format the LocalDate object
        return date.format(formatter);
    }

    public static String getCurrentYear() {
        LocalDate now = getCurrentLocalDate();
        return now.format(DateTimeFormatter.ofPattern("yyyy"));
    }
}
