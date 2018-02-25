package com.aconex.codingchallenge.vehiclesurvey.constance;

/**
 * Define constance related to time
 */
public class Time {

    public static final int HOURS_PER_DAY = 24;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int MILISECONDS_PER_SECOND = 1000;

    public static final int MILISECONDS_PER_HOUR = Time.MILISECONDS_PER_SECOND *
                                                   Time.SECONDS_PER_MINUTE *
                                                   Time.MINUTES_PER_HOUR;
    public static final int MILISECONDS_PER_DAY = Time.MILISECONDS_PER_HOUR *
                                                  Time.HOURS_PER_DAY;

    public Time() {}
}
