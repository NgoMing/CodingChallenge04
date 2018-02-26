package com.aconex.codingchallenge.vehiclesurvey;

import com.aconex.codingchallenge.vehiclesurvey.constants.App;

public class VehicleCounterSimulator {

    public static void main(String[] args) {

        VehicleCounterApplication app = new VehicleCounterApplication();
        String fileName;
        if (args.length != 1)
            fileName = App.DEFAULT_FILE_NAME;
        else
            fileName = args[0];

        int[] intervals = {15, 30, 60, 360};
        app.createProcessorsWithInterval(intervals);
        app.run(fileName);
    }
}
