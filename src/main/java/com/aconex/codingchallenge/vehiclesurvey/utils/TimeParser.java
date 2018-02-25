package com.aconex.codingchallenge.vehiclesurvey.utils;

import com.aconex.codingchallenge.vehiclesurvey.constance.Time;

public class TimeParser {

    /**
     * parse input line to get miliseconds value
     * @param inputLine
     * @return miliseconds value or -1 if input line include wrong type of information (integer)
     *                                  or miliseconds value is out of valid range
     */
    public static int parseMilisecondsFrom(String inputLine) {

        try {
            // format valid input line: A[miliseconds] or B[miliseconds]
            int miliseconds = Integer.valueOf(inputLine.substring(1));
            if (!isValid(miliseconds)) {
                System.out.println("Out of miliseconds range from: " + inputLine);
                return -1;
            }
            return miliseconds;
        }
        catch (NumberFormatException nfe) {
            System.out.println("Error parsing miliseconds from: " + inputLine);
        }

        return -1;
    }

    private static boolean isValid(int miliseconds) {
        int milisecondsPerDay = Time.MILISECONDS_PER_DAY;

        return ((miliseconds >= 0) && (miliseconds < milisecondsPerDay));
    }

    public static double convertToHours(int miliseconds) {
        return (double)miliseconds / (double)Time.MILISECONDS_PER_HOUR;
    }
}
