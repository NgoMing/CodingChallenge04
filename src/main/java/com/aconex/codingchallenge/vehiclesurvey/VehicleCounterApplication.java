package com.aconex.codingchallenge.vehiclesurvey;

import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;
import com.aconex.codingchallenge.vehiclesurvey.parser.VehicleEntryParser;
import com.aconex.codingchallenge.vehiclesurvey.processor.AverageDistanceProcessor;
import com.aconex.codingchallenge.vehiclesurvey.processor.AverageSpeedProcessor;
import com.aconex.codingchallenge.vehiclesurvey.processor.IDataProcessor;
import com.aconex.codingchallenge.vehiclesurvey.processor.VehicleCountProcessor;
import com.aconex.codingchallenge.vehiclesurvey.utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleCounterApplication {

    private Map<String, IDataProcessor> processors;

    // access method
    public Map<String, IDataProcessor> getProcessors() {
        return processors;
    }

    public void createProcessorsWithInterval(int[] intervals) {
        processors = new HashMap<>();

        for (int interval : intervals) {
            String key = "Vehicle counts in " + interval + " minute intervals";
            processors.put(key, new VehicleCountProcessor(interval));
            key = "Average speed in " + interval + " minute intervals";
            processors.put(key, new AverageSpeedProcessor(interval));
            key = "Average distance in " + interval + " minute intervals";
            processors.put(key, new AverageDistanceProcessor(interval));
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

        processors.forEach( (key, processors) -> {
            System.out.println(key);
            System.out.println(processors.process(entries));
        });
    }
}
