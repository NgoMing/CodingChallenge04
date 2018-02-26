package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.model.SessionsOfDay;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseProcessor {
    public List<VehicleEntry> getEntriesInTheSession(List<VehicleEntry> entries, SessionsOfDay session) {
        return entries.stream().
                filter(entry -> entry.getTimeEntry().compareTo(session.getStartTime()) >= 0
                        && entry.getTimeEntry().compareTo(session.getEndTime()) <= 0).
                collect(Collectors.toList());
    }
}
