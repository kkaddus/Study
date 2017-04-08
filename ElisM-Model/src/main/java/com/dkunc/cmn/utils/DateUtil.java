package com.dkunc.cmn.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    public static final String YYYYMMDD          = "yyyyMMdd";
    public static final String YYYYMMDDHH        = "yyyyMMddHH";
    public static final String YYYYMMDDHHMM      = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS    = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String HHMMSS            = "HHmmss";
    public static String dateToString(Date obj, String format) {
        if (obj == null)
            return "";
        SimpleDateFormat dtFormatter = new SimpleDateFormat(format);
        return dtFormatter.format(obj);
    }
    public static Date stringToDate(String strDate, String format) throws ParseException {
        SimpleDateFormat dtFormatter = new SimpleDateFormat(format);
        if (StringUtil.isEmpty(strDate))
            throw new ParseException("Cannot convert empty string to Date.", 0);
        return dtFormatter.parse(strDate.trim());
    }
    public static Date convertFlexibleDate(String strDate, String[] formats) throws ParseException {
        if (StringUtil.isEmpty(strDate))
            return null;
        for (int i = 0; i < formats.length; i++) {
            try {
                SimpleDateFormat dtFormatter = new SimpleDateFormat(formats[i]);
                dtFormatter.setLenient(false);
                return dtFormatter.parse(strDate.trim());
            } catch (ParseException e) {
                // do nothing... try other format
            }
        }
        // we are unable to convert
        throw new ParseException("No matching date format for " + strDate, 0);
    }
    public static Date getToday() {
        return new Date();
    }
    public static String getTodayYYYYMMDD() {
        return dateToString(new Date(), YYYYMMDD);
    }
    public static int getTodayIntYYYYMMDD() {
        return Integer.parseInt(getTodayYYYYMMDD()) ;
    }
    public static String convertShortDate(Date obj) {
        return dateToString(obj, YYYYMMDD);
    }
    public static Date convertShortDate(String str) throws ParseException {
        return stringToDate(str, YYYYMMDD);
    }
    public static Date convertShortDate(String str, Date defaultDate) {
        try {
            return stringToDate(str, YYYYMMDD);
        } catch (ParseException pex) {
            return defaultDate;
        }
    }
    public static Date convertFlexibleDate(String strDate) throws ParseException {
        if (StringUtil.isEmpty(strDate))
            throw new ParseException("Cannot convert empty string to Date.", 0);
        String[] formats = { "MM/dd/yyyy", "MM-dd-yyyy", YYYYMMDD, "yyyy-MM-dd", "MMM dd yyyy", "MMM dd, yyyy", "MMM yyyy", "MM/yyyy", "MM-yyyy", "yyyy" };
        return convertFlexibleDate(strDate, formats);
    }
    public static boolean compareNullableDates(Date date1, Date date2) {
        if ((date1 == null) && (date2 == null))
            return true;
        if (date1 != null) {
            if (date1.equals(date2))
                return true;
            else
                return false;
        }
        return false;
    }
    /**
     * Checks if the given date has a time recorded or just plain 00:00:00.000
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static boolean hasTime(Date date) {
        return (date.getHours() + date.getMinutes() + date.getSeconds() > 0);
    }
    /**
     * Get current date according to client's time zone.
     * 
     * @param calendar
     *            - adapting calendar
     * @param timeZone
     *            - client time zone
     * @return adapt calendar to client time zone
     */
    public static Date getClientCurrentDate(final Calendar calendar, final TimeZone timeZone) {
        Calendar result = new GregorianCalendar(timeZone);
        result.setTimeInMillis(calendar.getTimeInMillis() + timeZone.getOffset(calendar.getTimeInMillis()) - TimeZone.getDefault().getOffset(calendar.getTimeInMillis()));
        result.getTime();
        return result.getTime();
    }
    public static final int YEAR       = 1;
    public static final int MONTH      = 2;
    public static final int DATE       = 3;
    public static final int MonTHFIRST = 4;
    public static final int MONTHEND   = 5;
    public static String getYyyymmdd(Calendar cal) {
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = YYYYMMDD;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(cal.getTime());
    }
    public static GregorianCalendar getGregorianCalendar(String yyyymmdd) {
        int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
        int mm = Integer.parseInt(yyyymmdd.substring(4, 6));
        int dd = Integer.parseInt(yyyymmdd.substring(6));
        GregorianCalendar calendar = new GregorianCalendar(yyyy, mm - 1, dd, 0, 0, 0);
        return calendar;
    }
    public static String getCurrentDateTime() {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = YYYYMMDDHHMMSS;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(today);
    }
    public static String getCurrentTime() {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = HHMMSS;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(today);
    }
    public static String getCurrentYyyymmdd() {
        return getCurrentDateTime().substring(0, 8);
    }
    public static String getWeekToDay(String yyyymm, int week, String pattern) {
        Calendar cal = Calendar.getInstance(Locale.FRANCE);
        int new_yy = Integer.parseInt(yyyymm.substring(0, 4));
        int new_mm = Integer.parseInt(yyyymm.substring(4, 6));
        int new_dd = 1;
        cal.set(new_yy, new_mm - 1, new_dd);
        // 임시 코드
        if (cal.get(cal.DAY_OF_WEEK) == cal.SUNDAY) {
            week = week - 1;
        }
        cal.add(Calendar.DATE, (week - 1) * 7 + (cal.getFirstDayOfWeek() - cal.get(Calendar.DAY_OF_WEEK)));
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.FRANCE);
        return formatter.format(cal.getTime());
    }
    public static String getOpDate(int field, int amount, String date) {
        GregorianCalendar calDate = getGregorianCalendar(date);
        if (field == Calendar.YEAR) {
            calDate.add(GregorianCalendar.YEAR, amount);
        } else if (field == Calendar.MONTH) {
            calDate.add(GregorianCalendar.MONTH, amount);
        } else {
            calDate.add(GregorianCalendar.DATE, amount);
        }
        return getYyyymmdd(calDate);
    }
    public static int getWeek(String yyyymmdd, int addDay) {
        Calendar cal = Calendar.getInstance(Locale.FRANCE);
        int new_yy = Integer.parseInt(yyyymmdd.substring(0, 4));
        int new_mm = Integer.parseInt(yyyymmdd.substring(4, 6));
        int new_dd = Integer.parseInt(yyyymmdd.substring(6, 8));
        cal.set(new_yy, new_mm - 1, new_dd);
        cal.add(Calendar.DATE, addDay);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        return week;
    }
    public static int getLastDayOfMon(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public static int getLastDayOfMon(String yyyymm) {
        Calendar cal = Calendar.getInstance();
        int yyyy = Integer.parseInt(yyyymm.substring(0, 4));
        int mm = Integer.parseInt(yyyymm.substring(4)) - 1;
        cal.set(yyyy, mm, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public static boolean isCorrect(String yyyymmdd) {
        boolean flag = false;
        if (yyyymmdd.length() < 8)
            return false;
        try {
            int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
            int mm = Integer.parseInt(yyyymmdd.substring(4, 6));
            int dd = Integer.parseInt(yyyymmdd.substring(6));
            flag = DateUtil.isCorrect(yyyy, mm, dd);
        } catch (Exception ex) {
            return false;
        }
        return flag;
    }
    public static boolean isCorrect(int yyyy, int mm, int dd) {
        if (yyyy < 0 || mm < 0 || dd < 0)
            return false;
        if (mm > 12 || dd > 31)
            return false;
        String year = "" + yyyy;
        String month = "00" + mm;
        String year_str = year + month.substring(month.length() - 2);
        int endday = DateUtil.getLastDayOfMon(year_str);
        if (dd > endday)
            return false;
        return true;
    }
    public static String getThisDay(String type) {
        Date date = new Date();
        SimpleDateFormat sdf = null;
        try {
            if (type.toLowerCase().equals(YYYYMMDD.toLowerCase())) {
                sdf = new SimpleDateFormat(YYYYMMDD);
                return sdf.format(date);
            }
            if (type.toLowerCase().equals(YYYYMMDDHH.toLowerCase())) {
                sdf = new SimpleDateFormat(YYYYMMDDHH);
                return sdf.format(date);
            }
            if (type.toLowerCase().equals(YYYYMMDDHHMM.toLowerCase())) {
                sdf = new SimpleDateFormat(YYYYMMDDHHMM);
                return sdf.format(date);
            }
            if (type.toLowerCase().equals(YYYYMMDDHHMMSS.toLowerCase())) {
                sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
                return sdf.format(date);
            }
            if (type.toLowerCase().equals(YYYYMMDDHHMMSSSSS.toLowerCase())) {
                sdf = new SimpleDateFormat(YYYYMMDDHHMMSSSSS);
                return sdf.format(date);
            } else {
                sdf = new SimpleDateFormat(type);
                return sdf.format(date);
            }
        } catch (Exception e) {
            return "[ ERROR ]: parameter must be 'YYYYMMDD', 'YYYYMMDDHH', 'YYYYMMDDHHSS'or 'YYYYMMDDHHSSMS'";
        }
    }
    public static String changeDateFormat(String yyyymmdd) {
        String rtnDate = null;
        String yyyy = yyyymmdd.substring(0, 4);
        String mm = yyyymmdd.substring(4, 6);
        String dd = yyyymmdd.substring(6, 8);
        rtnDate = yyyy + " 년 " + mm + " 월 " + dd + " 일";
        return rtnDate;
    }
    public static long getDifferDays(String startDate, String endDate) {
        GregorianCalendar StartDate = getGregorianCalendar(startDate);
        GregorianCalendar EndDate = getGregorianCalendar(endDate);
        long difer = (EndDate.getTime().getTime() - StartDate.getTime().getTime()) / 86400000;
        return difer;
    }
    public static int getDayOfWeek() {
        Calendar rightNow = Calendar.getInstance();
        int day_of_week = rightNow.get(Calendar.DAY_OF_WEEK);
        return day_of_week;
    }
    public static int getWeekOfYear() {
        Locale LOCALE_COUNTRY = Locale.KOREA;
        Calendar rightNow = Calendar.getInstance(LOCALE_COUNTRY);
        int week_of_year = rightNow.get(Calendar.WEEK_OF_YEAR);
        return week_of_year;
    }
    public static int getWeekOfMonth() {
        Locale LOCALE_COUNTRY = Locale.KOREA;
        Calendar rightNow = Calendar.getInstance(LOCALE_COUNTRY);
        int week_of_month = rightNow.get(Calendar.WEEK_OF_MONTH);
        return week_of_month;
    }
    public static Calendar getCalendarInstance(String p_date) {
        // Locale LOCALE_COUNTRY = Locale.KOREA;
        Locale LOCALE_COUNTRY = Locale.FRANCE;
        Calendar retCal = Calendar.getInstance(LOCALE_COUNTRY);
        if (p_date != null && p_date.length() == 8) {
            int year = Integer.parseInt(p_date.substring(0, 4));
            int month = Integer.parseInt(p_date.substring(4, 6)) - 1;
            int date = Integer.parseInt(p_date.substring(6));
            retCal.set(year, month, date);
        }
        return retCal;
    }
}
