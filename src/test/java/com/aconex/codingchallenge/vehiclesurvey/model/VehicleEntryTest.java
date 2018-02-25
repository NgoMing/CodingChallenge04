package com.aconex.codingchallenge.vehiclesurvey.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleEntryTest {

    private static final double DELTA = 1e-15;

    @Test
    public void isValidReturnTrue() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(2, 3, Direction.SOUTH);
        assertTrue(vehicleEntry.isValid());
    }

    @Test
    public void isValidReturnFalse() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(3, 2, Direction.SOUTH);
        assertFalse(vehicleEntry.isValid());
    }

    @Test
    public void getSouthDirection() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(3, 4, Direction.SOUTH);
        assertEquals(Direction.SOUTH, vehicleEntry.getDirection());
    }

    @Test
    public void getNorthDirection() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(3, 4, Direction.NORTH);
        assertEquals(Direction.NORTH, vehicleEntry.getDirection());
    }

    @Test
    public void shouldReturnSpeedOfVehicle() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(0, 1000, Direction.SOUTH);
        assertEquals(9d, vehicleEntry.getSpeedInKmPH(), DELTA);
    }

    @Test
    public void shouldReturnZeroWhenEntryIsInvalid() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(1000, 0, Direction.SOUTH);
        assertEquals(0d, vehicleEntry.getSpeedInKmPH(), DELTA);
    }
}