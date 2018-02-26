package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.constance.App;
import com.aconex.codingchallenge.vehiclesurvey.constance.Time;
import com.aconex.codingchallenge.vehiclesurvey.model.Direction;
import com.aconex.codingchallenge.vehiclesurvey.model.SessionsOfDay;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Count the number of vehicle
 */
public class VehicleCountProcessor extends BaseProcessor{
    private int interval; // in minutes

    public VehicleCountProcessor(int interval) {
        this.interval = interval;
    }

    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();
        SessionsOfDay peakSession = null;
        int peakVehicleCount = 0;
        List<SessionsOfDay> sessions = SessionsOfDay.createSessionsWithInterval(interval);

        if (sessions.isEmpty())
            return "";

        for (SessionsOfDay session : sessions) {
            int totalCountAcrossDay = 0;
            List<VehicleEntry> sessionEntries = getEntriesInTheSession(entries, session);
            sb.append(session.toString());
            for (int day = 0; day < App.NUMBER_OF_DAYS; day++) {
                int southCount = countEntriesForDayInDirection(sessionEntries, day, Direction.SOUTH);
                int northCount = countEntriesForDayInDirection(sessionEntries, day, Direction.NORTH);
                sb.append(" | Day ").append(day).append(" Count in South = ").append(southCount).append(" in North = ").append(northCount);
                totalCountAcrossDay += southCount + northCount;
            }
            sb.append("\n");
            if (peakVehicleCount < totalCountAcrossDay) {
                peakVehicleCount = totalCountAcrossDay;
                peakSession = session;
            }
        }

        sb.append('\n').append(peakSession).append(" is peak session with ").append(peakVehicleCount).append(" cars");

        return sb.toString();
    }

    private int countEntriesForDayInDirection(List<VehicleEntry> sessionEntries, int day, Direction direction) {
        return sessionEntries.stream().
                filter(entry -> entry.getDay() == day && entry.getDirection() == direction).
                collect(Collectors.toList()).size();
    }
}
