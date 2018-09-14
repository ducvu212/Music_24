package com.framgia.music_24.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by CuD HniM on 18/08/24.
 */

public final class StringUtils {

    private StringUtils() {
        // No-op
    }

    public static boolean isBlank(String input) {
        return input.isEmpty();
    }

    public static boolean isNotBlank(String input) {
        return !isBlank(input);
    }

    public static String convertMilisecToMinute(long milisec) {
        return String.format("%d : %02d", TimeUnit.MILLISECONDS.toMinutes(milisec),
                TimeUnit.MILLISECONDS.toSeconds(milisec) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(milisec)));
    }

    public static String convertTime(String time) {
        StringBuilder builder = new StringBuilder();
        String timeConvert;
        String timeCharacter = "T";
        String dash = "-";
        timeConvert = time.substring(0, time.indexOf(timeCharacter));
        String date =
                timeConvert.substring(timeConvert.lastIndexOf(dash) + 1, timeConvert.length());
        String month =
                timeConvert.substring(timeConvert.indexOf(dash), timeConvert.lastIndexOf(dash) + 1);
        String year = timeConvert.substring(0, timeConvert.indexOf(dash));
        builder.append(date).append(month).append(year);
        return builder.toString();
    }
}
