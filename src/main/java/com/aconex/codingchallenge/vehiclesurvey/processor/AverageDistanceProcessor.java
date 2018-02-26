package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.constance.App;
import com.aconex.codingchallenge.vehiclesurvey.constance.Time;
import com.aconex.codingchallenge.vehiclesurvey.model.SessionsOfDay;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;
import com.sun.deploy.security.SessionCertStore;

import java.util.List;

public class AverageDistanceProcessor extends BaseProcessor {

    private int interval; // in minutes

    public AverageDistanceProcessor(int interval) {
        this.interval = interval;
    }

    @Override
    public String process(List<VehicleEntry> entries) {
        StringBuilder sb = new StringBuilder();

        List<SessionsOfDay> sessions = SessionsOfDay.createSessionsWithInterval(interval);
        for (SessionsOfDay session : sessions) {
            List<VehicleEntry> entriesInTheSession = getEntriesInTheSession(entries, session);
            double averageDistance = getAverageDistance(entriesInTheSession);
            sb.append(session).append(" | Average distance between cars = ").append(averageDistance).append(" meters\n");
        }

        return sb.toString();
    }

    private double getAverageDistance(List<VehicleEntry> entriesInTheSession) {
        if (entriesInTheSession.isEmpty())
            return 0;
        long timeOfFirstVehicle = entriesInTheSession.get(0).getTimeEntry().getTime();
        long timeOfLastVehicle = entriesInTheSession.get(entriesInTheSession.size() - 1).getTimeEntry().getTime();
        double averageTimeBetweenVehicles = ((double)timeOfLastVehicle - (double)timeOfFirstVehicle) / (double)entriesInTheSession.size();
        return averageTimeBetweenVehicles / Time.MILISECONDS_PER_SECOND * App.LENGTH_OF_VEHICLE;
    }
}
