package com.aconex.codingchallenge.vehiclesurvey.model;

import com.aconex.codingchallenge.vehiclesurvey.constants.App;
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

    // access method
    public Direction getDirection() {
        return direction;
    }

    public int getDay() {
        return day;
    }

    // update method
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * calculate speed in kilometer per hour
     * @return speed of the vehicle
     */
    public double getSpeedInKmPH() {
        if (!isValid())
            return 0;

        double timeTaken = TimeParser.convertToHours(rearAxleTime - frontAxleTime);
        return App.LENGTH_OF_VEHICLE / timeTaken;
    }

    /**
     * calculate Date when the vehicle went cross the counter
     * @return
     */
    public Date getTimeEntry() {
        return new Date((frontAxleTime + rearAxleTime) / 2);
    }

    /**
     * check the validation of entry
     * @return true if recording time of front axle is smaller than one of rear axle
     */
    public boolean isValid() {
        return (frontAxleTime < rearAxleTime);
    }
}
