package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.constance.App;
import com.aconex.codingchallenge.vehiclesurvey.model.SessionsOfDay;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Count the number of vehicle
 */
public class VehicleCountProcessor {
    private int interval; // in minutes

    public VehicleCountProcessor(int interval) {
        this.interval = interval;
    }

    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();
        List<SessionsOfDay> sessions = SessionsOfDay.createSessionsWithInterval(interval);
        for(SessionsOfDay session : sessions){
                List<VehicleEntry> sessionEntries = getEntriesInTheSession(entries, session);
                sb.append(session.toString());
                for (int day = 0; day < App.NUMBER_OF_DAYS; day++) {
                        int count = countEntriesForTheDay(sessionEntries,day);
                        sb.append(" | Day " + day + " Count = " + count);
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
