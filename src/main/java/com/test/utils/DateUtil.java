package com.test.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.test.utils.StringUtil.ExtractnNum;

public class DateUtil {

    public static List<String> getDateList(String startDateString, String endDateString) throws ParseException {
        startDateString = ExtractnNum(startDateString);
        endDateString = ExtractnNum(endDateString);

        List<String> dateList = new ArrayList<String>();
        // start date
        int startDate = Integer.parseInt(startDateString);
        int startYear = startDate / 10000;
        int startMonth = startDate / 100 % 100;
        int startDay = startDate % 100;
        // end date
        int endDate = Integer.parseInt(endDateString);
        int endYear = endDate / 10000;
        int endMonth = endDate / 100 % 100;
        int endDay = endDate % 100;
        // begin
        int y = startYear;
        int m = startMonth;
        int d = startDay;
        while (y < endYear || y == endYear && m < endMonth || y == endYear && m == endMonth && d <= endDay) {

            String tmpDateString = String.format("%d-%02d-%02d", y, m, d);
            dateList.add(tmpDateString);
            boolean isRunNian = (y % 400 == 0 || y % 4 == 0 && y % 100 != 0);
            int lastDay = 31;
            if (m == 2) {
                if (isRunNian) lastDay = 29;
                else lastDay = 28;
            } else if (m <= 7 && m % 2 == 0 || m > 7 && m % 2 == 1) {
                lastDay = 30;
            }
            if (d >= lastDay) {
                m++;
                d = 1;
                if (m > 12) {
                    y++;
                    m = 1;
                }
            } else {
                d++;
            }
        }
        return dateList;
    }

    /**
     * 获取当前日期，格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDate() {
        //当前日期 格式:"yyyy-MM-dd HH:mm:ss"
        Date d = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sbf.format(d);
        return date;
    }

    /**
     * 根据时间字符串获取年月日时分秒,比如2019-1-21 13:06:06
     *
     * @param timeString
     * @return
     */
    public static Date parseTimeString2Date(String timeString) {
        if ((timeString == null) || (timeString.equals(""))) {
            return null;
        }
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = new Date(dateFormat.parse(timeString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDate2String(Date date, String pattern) {
        if (date == null)
            return null;
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static int getYear(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(0, 4));
    }

    public static int getMonth(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(5, 7));
    }

    public static int getDay(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(8, 10));
    }

    public static int getHour(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(11, 13));
    }

    public static int getMinute(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(14, 16));
    }

    public static int getSecond(String timeString) {
        String timeStr = convertDate2String(parseTimeString2Date(timeString), "yyyy-MM-dd HH:mm:ss");
        return Integer.parseInt(timeStr.substring(17, 19));
    }

}
