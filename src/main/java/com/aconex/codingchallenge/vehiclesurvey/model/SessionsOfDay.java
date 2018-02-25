package com.aconex.codingchallenge.vehiclesurvey.model;

import com.aconex.codingchallenge.vehiclesurvey.constance.Time;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * create session of a day with specific interval value
 */
public class SessionsOfDay {
    private Date startTime;
    private Date endTime;

    private SessionsOfDay(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // access methods
    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    /**
     * create sessions of a day
     * @param intervalInMinute minute unit
     * @return list of sessions
     */
    public static List<SessionsOfDay> createSessionsWithInterval(int intervalInMinute) {
        List<SessionsOfDay> sessions = new ArrayList<>();
        int minutesInDay = Time.MINUTES_PER_HOUR * Time.HOURS_PER_DAY;

        if ((minutesInDay % intervalInMinute) != 0) {
            System.out.println("Interval of " + intervalInMinute +
                    " minutes can not be evenly distributed in a day");
            return sessions;
        }

        for (int i = 0; i < minutesInDay / intervalInMinute; i++) {
            Date startTime = new Date(i * Time.MILISECONDS_PER_SECOND * Time.SECONDS_PER_MINUTE * intervalInMinute);
            Date endTime = new Date((i + 1) * Time.MILISECONDS_PER_SECOND * Time.SECONDS_PER_MINUTE * intervalInMinute);

            sessions.add(new SessionsOfDay(startTime, endTime));
        }

        return sessions;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));

        return "Session " + sdf.format(startTime) + " to " + sdf.format(endTime);
    }
}
