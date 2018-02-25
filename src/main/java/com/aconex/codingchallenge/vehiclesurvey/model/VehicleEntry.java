package com.aconex.codingchallenge.vehiclesurvey.model;

import com.aconex.codingchallenge.vehiclesurvey.constance.App;
import com.aconex.codingchallenge.vehiclesurvey.constance.Time;
import com.aconex.codingchallenge.vehiclesurvey.utils.TimeParser;

import java.util.Date;

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
    private int day;

    public VehicleEntry(int frontAxleTime, int rearAxleTime, Direction direction, int day) {
        this.frontAxleTime = frontAxleTime;
        this.rearAxleTime = rearAxleTime;
        this.direction = direction;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public double getSpeedInKmPH() {
        if (!isValid())
            return 0;

        double timeTaken = TimeParser.convertToHours(rearAxleTime - frontAxleTime);
        return App.LENGTH_OF_VEHICLE / timeTaken;
    }

    public Date getTimeEntry() {
        return new Date((frontAxleTime + rearAxleTime) / 2);
    }

    public boolean isValid() {
        return (frontAxleTime < rearAxleTime);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
