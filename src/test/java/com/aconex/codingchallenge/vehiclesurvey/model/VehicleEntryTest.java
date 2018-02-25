package com.aconex.codingchallenge.vehiclesurvey.model;

import javafx.scene.control.cell.TextFieldTableCell;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class VehicleEntryTest {

    private static final double DELTA = 1e-15;

    @Test
    public void isValidReturnTrue() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(2, 3, Direction.SOUTH, 0);
        assertTrue(vehicleEntry.isValid());
    }

    @Test
    public void isValidReturnFalse() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(3, 2, Direction.SOUTH, 0);
        assertFalse(vehicleEntry.isValid());
    }

    @Test
    public void getSouthDirection() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(3, 4, Direction.SOUTH, 0);
        assertEquals(Direction.SOUTH, vehicleEntry.getDirection());
    }

    @Test
    public void getNorthDirection() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(3, 4, Direction.NORTH, 0);
        assertEquals(Direction.NORTH, vehicleEntry.getDirection());
    }

    @Test
    public void shouldReturnSpeedOfVehicle() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(0, 1000, Direction.SOUTH, 0);
        assertEquals(9d, vehicleEntry.getSpeedInKmPH(), DELTA);
    }

    @Test
    public void shouldReturnZeroWhenEntryIsInvalid() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(1000, 0, Direction.SOUTH, 0);
        assertEquals(0d, vehicleEntry.getSpeedInKmPH(), DELTA);
    }

    @Test
    public void shouldReturnDateOfVehicleEntry() throws Exception {
        VehicleEntry vehicleEntry = new VehicleEntry(0, 1000, Direction.NORTH, 0);
        Date date = new Date(500);
        assertEquals(date, vehicleEntry.getTimeEntry());
    }
}