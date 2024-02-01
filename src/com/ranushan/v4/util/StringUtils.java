package com.ranushan.v4.util;

public class StringUtils {

    private StringUtils() {}

    /**
     * Check if input value providing by User is empty or blank
     * @param value Input value
     * @return Is input is empty or blank
     */
    public static boolean emptyValue(String value) {
        return value.isBlank() || value.isEmpty();
    }

}
