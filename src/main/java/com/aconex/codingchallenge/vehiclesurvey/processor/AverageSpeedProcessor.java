package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.model.SessionsOfDay;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;

import java.util.List;

public class AverageSpeedProcessor extends BaseProcessor{

    private int interval; // in minutes

    public AverageSpeedProcessor(int interval) {
        this.interval = interval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();

        List<SessionsOfDay> sessions = SessionsOfDay.createSessionsWithInterval(interval);
        for (SessionsOfDay session : sessions) {
            List<VehicleEntry> entriesInTheSession = getEntriesInTheSession(entries, session);
            double totalSpeed = totalSpeedInTheSession(entriesInTheSession);
            double averageSpeed = 0;
            if (!entriesInTheSession.isEmpty())
                averageSpeed = totalSpeed / entriesInTheSession.size();
            sb.append(session).append(" | Average speed = ").append(averageSpeed).append('\n');
        }

        return sb.toString();
    }

    private double totalSpeedInTheSession(List<VehicleEntry> entriesInTheSession) {
        return entriesInTheSession.stream().
                mapToDouble(VehicleEntry::getSpeedInKmPH).sum();
    }
}
