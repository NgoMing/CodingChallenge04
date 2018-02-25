package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.constance.App;
import com.aconex.codingchallenge.vehiclesurvey.constance.Time;
import com.aconex.codingchallenge.vehiclesurvey.model.SessionsOfDay;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Count the number of vehicle
 */
public class VehicleCountProcessor {
    private int interval; // in minutes
    private Date startTime;
    private Date endTime;
    private int startDay;
    private int endDay;

    public VehicleCountProcessor(int interval) {
        this.interval = interval;
        startTime = new Date(0);
        endTime = new Date(Time.MILISECONDS_PER_DAY);
        startDay = 0;
        endDay = App.NUMBER_OF_DAYS;
    }

    public VehicleCountProcessor from(int startTimeInMinute) {
        startTime = new Date(Time.MILISECONDS_PER_SECOND * Time.SECONDS_PER_MINUTE * startTimeInMinute);
        return this;
    }

    public VehicleCountProcessor to(int endTimeInMinute) {
        endTime = new Date(Time.MILISECONDS_PER_SECOND * Time.SECONDS_PER_MINUTE * endTimeInMinute);
        return this;
    }

    public VehicleCountProcessor fromDay(int startDay) {
        this.startDay = startDay;
        return this;
    }

    public VehicleCountProcessor toDay(int endDay) {
        this.endDay = endDay;
        return this;
    }

    public int getCount(List<VehicleEntry> entries) {
        List<VehicleEntry> sessionEntries = getEntriesInTheSession(entries, startTime, endTime);
        return countEntriesForGivenDay(sessionEntries, startDay, endDay);
    }

    private int countEntriesForGivenDay(List<VehicleEntry> sessionEntries, int startDay, int endDay) {
        return sessionEntries.stream().
                filter(entry -> entry.getDay() >= startDay && entry.getDay() <= endDay).
                collect(Collectors.toList()).size();
    }

    private List<VehicleEntry> getEntriesInTheSession(List<VehicleEntry> entries, Date startTime, Date endTime) {
        return entries.stream().
                filter(entry -> entry.getTimeEntry().compareTo(startTime) >= 0 &&
                            entry.getTimeEntry().compareTo(endTime) < 0)
                .collect(Collectors.toList());
    }

    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();
        List<SessionsOfDay> sessions = SessionsOfDay.createSessionsWithInterval(interval);
        for(SessionsOfDay session : sessions){
                List<VehicleEntry> sessionEntries = getEntriesInTheSession(entries, session);
                sb.append(session.toString());
                for (int day = 0; day < App.NUMBER_OF_DAYS; day++) {
                        int count = countEntriesForTheDay(sessionEntries,day);
                        sb.append(" | Day " + day + " Count=" + count);
                    }
                sb.append("\n");
            }
        return sb.toString();
    }

    private int countEntriesForTheDay(List<VehicleEntry> sessionEntries, int day) {
        return sessionEntries.stream().filter(entry -> entry.getDay() == day).collect(Collectors.toList()).size();
    }

    private List<VehicleEntry> getEntriesInTheSession(List<VehicleEntry> entries, SessionsOfDay session) {
        return entries.stream().
                        filter(entry -> entry.getTimeEntry().compareTo(session.getStartTime()) >= 0
                                && entry.getTimeEntry().compareTo(session.getEndTime()) < 0)
                        .collect(Collectors.toList());
    }
}
