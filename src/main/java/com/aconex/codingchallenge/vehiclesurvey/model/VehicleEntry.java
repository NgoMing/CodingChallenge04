package com.aconex.codingchallenge.vehiclesurvey.model;

import com.aconex.codingchallenge.vehiclesurvey.constance.App;
import com.aconex.codingchallenge.vehiclesurvey.constance.Time;
import com.aconex.codingchallenge.vehiclesurvey.utils.TimeParser;

/**
 * VehicleEntry class includes all information which can be retrieved from input data
 *      time when front axle is triggered
 *      time when rear axle is triggered
 *      direction of the vehicle
 */
public class VehicleEntry {
    private int frontAxleTime;
    private int rearAxleTime;
    private Direction direction;

    public VehicleEntry(int frontAxleTime, int rearAxleTime, Direction direction) {
        this.frontAxleTime = frontAxleTime;
        this.rearAxleTime = rearAxleTime;
        this.direction = direction;
    }

    public double getSpeedInKmPH() {
        if (!isValid())
            return 0;

        double timeTaken = TimeParser.convertToHours(rearAxleTime - frontAxleTime);
        return App.LENGTH_OF_VEHICLE / timeTaken;
    }

    public boolean isValid() {
        return (frontAxleTime < rearAxleTime);
    }

    public Direction getDirection() {
        return direction;
    }
}
