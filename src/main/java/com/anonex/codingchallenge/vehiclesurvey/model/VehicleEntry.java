package com.anonex.codingchallenge.vehiclesurvey.model;

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
}
