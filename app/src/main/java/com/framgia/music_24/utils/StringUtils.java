package com.framgia.music_24.utils;

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
}
