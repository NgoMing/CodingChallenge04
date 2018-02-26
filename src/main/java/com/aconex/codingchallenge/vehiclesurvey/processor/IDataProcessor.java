package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;

import java.util.List;

public interface IDataProcessor {
    String process(List<VehicleEntry> entries);

    String description();
}
