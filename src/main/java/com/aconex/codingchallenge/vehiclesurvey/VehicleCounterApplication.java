package com.aconex.codingchallenge.vehiclesurvey;

import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;
import com.aconex.codingchallenge.vehiclesurvey.parser.VehicleEntryParser;
import com.aconex.codingchallenge.vehiclesurvey.processor.AverageDistanceProcessor;
import com.aconex.codingchallenge.vehiclesurvey.processor.AverageSpeedProcessor;
import com.aconex.codingchallenge.vehiclesurvey.processor.IDataProcessor;
import com.aconex.codingchallenge.vehiclesurvey.processor.VehicleCountProcessor;
import com.aconex.codingchallenge.vehiclesurvey.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleCounterApplication {

    private List<IDataProcessor> processors;

    // access method
    public List<IDataProcessor> getProcessors() {
        return processors;
    }

    public void createProcessorsWithInterval(int[] intervals) {
        processors = new ArrayList<>();

        for (int interval : intervals) {
            processors.add(new VehicleCountProcessor(interval));
            processors.add(new AverageSpeedProcessor(interval));
            processors.add(new AverageDistanceProcessor(interval));
        }
    }

    public void run(String fileName) {
        List<String> inputLines = new FileUtils().readResourceFile(fileName);
        if (inputLines.isEmpty()) {
            System.out.println("Error reading data file input: " + fileName);
            return;
        }

        List<VehicleEntry> entries = new VehicleEntryParser().parse(inputLines);
        if (entries.isEmpty()) {
            System.out.println("Error parsing inputs");
            return;
        }

        processors.forEach( processors -> {
            System.out.println(processors.description());
            System.out.println(processors.process(entries));
        });
    }
}
