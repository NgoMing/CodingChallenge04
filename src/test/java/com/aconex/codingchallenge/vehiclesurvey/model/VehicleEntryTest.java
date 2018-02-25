package com.aconex.codingchallenge.vehiclesurvey.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleEntryTest {
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
}