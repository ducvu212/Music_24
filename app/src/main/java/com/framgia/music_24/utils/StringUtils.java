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
}
