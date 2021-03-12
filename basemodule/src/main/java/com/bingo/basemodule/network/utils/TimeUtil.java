package com.bingo.basemodule.network.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    // 将毫秒转换为时间精确到秒
    public static String getDateTimeFromMillisecond(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    // 将毫秒转换为时间精确到分
    public static String getTimeFromMillisecond(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    // 将毫秒转换为天和小时
    public static String getTimeFromDay(Long millisecond) {
        long days = millisecond / (1000 * 60 * 60 * 24);
        long hours = (millisecond % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        if (days == 0) {
            return hours + "小时";
        }
        return days + "天" + hours + "小时";
    }


    public static String formatTimeS(long seconds) {
        int temp = 0;
        StringBuffer sb = new StringBuffer();
        if (seconds > 3600) {
            temp = (int) (seconds / 3600);
            sb.append((seconds / 3600) < 10 ? "0" + temp + ":" : temp + ":");
            temp = (int) (seconds % 3600 / 60);
            changeSeconds(seconds, temp, sb);
        } else {
            temp = (int) (seconds % 3600 / 60);
            changeSeconds(seconds, temp, sb);
        }
        return sb.toString();
    }


    private static void changeSeconds(long seconds, int temp, StringBuffer sb) {
        sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");
        temp = (int) (seconds % 3600 % 60);
        sb.append((temp < 10) ? "0" + temp : "" + temp);
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }


    /**
     * 根据毫秒时间戳来格式化字符串
     * 今天显示几时几分、昨天显示昨天、今年内只显示日期否则显示年
     * 早于前天的显示具体年-月-日，如2017-06-12；
     *
     * @param timeStamp 毫秒值
     * @return 几时几分 昨天 前天 或者 yyyy-MM-dd 类型字符串
     */
    public static String format(long timeStamp) {
        String strTime = "";
        long curTimeMillis = System.currentTimeMillis();
        Date curDate = new Date(curTimeMillis);
        int todayHoursSeconds = curDate.getHours() * 60 * 60;
        int todayMinutesSeconds = curDate.getMinutes() * 60;
        int todaySeconds = curDate.getSeconds();
        int todayMillis = (todayHoursSeconds + todayMinutesSeconds + todaySeconds) * 1000;
        long todayStartMillis = curTimeMillis - todayMillis;
        // 今天
        if (timeStamp >= todayStartMillis) {
            Date date = new Date(timeStamp);
            strTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();//显示几时几分积分
            return "今天";
        }
        int oneDayMillis = 24 * 60 * 60 * 1000;
        long yesterdayStartMillis = todayStartMillis - oneDayMillis;
        if (timeStamp >= yesterdayStartMillis) {
            return "昨天";
        }
//        long yesterdayBeforeStartMillis = yesterdayStartMillis - oneDayMillis;
//        if (timeStamp >= yesterdayBeforeStartMillis) {
//            return "前天";
//        }
        Date date = new Date(timeStamp);
        int datecompareafter = comparedate(new Date(), date);
        int daecomparebefore = comparedate(date, getoneyear());
        if (datecompareafter == -1 && daecomparebefore == -1) {
            //如果不是在一年以内,则弹出提示
            strTime = getFormatedDateTime("yyyy/MM/dd", timeStamp);
        } else {
            //在一年以内做的逻辑
            strTime = getFormatedDateTime("MM/dd", timeStamp);
        }
        return strTime;
    }


    // 比较时间
    public static int comparedate(Date d1, Date d2) {
        if (d1.getTime() > d2.getTime()) {
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            return -1;
        } else {// 相等
            return 0;
        }
    }

    //前rg加1年
    public static Date getoneyear() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }

    /**
     * 毫秒转日期
     *
     * @param pattern
     * @param dateTime
     * @return
     */
    public static String getFormatedDateTime(String pattern, long dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(dateTime + 0));
    }

    /**
     * @return 年-月-日
     */
    public static String getCurrentData() {
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        return year + "-" + month + "-" + date;
    }

    // 获取当前年-月
    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        return year;
    }

    // 获取当前年-月
    public static String getCurrentMonth() {
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        return year + "-" + month;
    }

    // 获取当前月
    public static int getMonth() {
        Calendar c = Calendar.getInstance();

        int month = c.get(Calendar.MONTH);
        return month;
    }

    public static String getCurrentMData() {
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        return year + "" + month + "" + date + "" + hour + "" + minute + "" + second + "";
    }

    /**
     * 秒转换为指定格式的日期
     *
     * @param second
     * @param patten
     * @return
     */
    public static String secondToDate(long second, String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }

    // 秒转时分秒
    public static String secondToDate(long second) {
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        String hourStr = "";
        String minuStr = "";
        String secondStr = "";
        if (hours < 10) {
            hourStr = "0" + hours;
        } else {
            hourStr = hours + "";
        }

        if (minutes < 10) {
            minuStr = "0" + minutes;
        } else {
            minuStr = minutes + "";
        }

        if (second < 10) {
            secondStr = "0" + second;
        } else {
            secondStr = second + "";
        }
        return hourStr + ":" + minuStr + ":" + secondStr;
    }


    /**
     * 时间转为秒
     *
     * @param data
     * @return
     */
    public static int dateToSecond(String data) {
        String[] dataString = data.split(":");
        int minute = Integer.parseInt(dataString[0]);
        int second = Integer.parseInt(dataString[1]);
        int resultTime = minute * 60 + second;
        return resultTime;
    }

    public static String formatDateToMD(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }

    /* //日期转换为时间戳 */
    public static long timeToStamp(String timers) {
        Date d = new Date();
        long timeStemp = 0;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            d = sf.parse(timers);// 日期转换为时间戳
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        timeStemp = d.getTime() / 1000; //返回秒
        return timeStemp;
    }


    /**
     * @param utcTime
     * @return utc时间转换为 时：分
     */
    public static String exchangeTimefromUtc(String utcTime) {
        String str = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            Date date = sd.parse(utcTime);
            str = sdf.format(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return str;
        }
        return str;
    }


    /**
     * @param utcTime
     * @return utc时间转换为年-月-日 时：分：秒
     */
    public static String exchangeDatefromUtc(String utcTime) {
        String str = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            Date date = sd.parse(utcTime);
            str = sdf.format(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return str;
        }
        return str;
    }
}
