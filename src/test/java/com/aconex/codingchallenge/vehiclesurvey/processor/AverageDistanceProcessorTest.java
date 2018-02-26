package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.model.Direction;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AverageDistanceProcessorTest {
    private final List<VehicleEntry> entries = new ArrayList<>();

    private String expectedOutput =
        "Session 00:00:00 to 06:00:00 | Average distance between cars = 0.0 meters\n" +
        "Session 06:00:00 to 12:00:00 | Average distance between cars = 12.500083333333333 meters\n" +
        "Session 12:00:00 to 18:00:00 | Average distance between cars = 0.0 meters\n" +
        "Session 18:00:00 to 00:00:00 | Average distance between cars = 14.16675 meters\n";

    @Before
    public void setUp() throws Exception {
        entries.add(new VehicleEntry(10000_000,10000_100, Direction.SOUTH,0));
        entries.add(new VehicleEntry(25000_000,25000_200, Direction.SOUTH,0));
        entries.add(new VehicleEntry(32000_000,32000_300, Direction.SOUTH,0));
        entries.add(new VehicleEntry(40000_000,40000_400, Direction.SOUTH,0));
        entries.add(new VehicleEntry(54000_000,54000_500, Direction.SOUTH,1));
        entries.add(new VehicleEntry(68000_000,68000_600, Direction.SOUTH,1));
        entries.add(new VehicleEntry(72000_000,72000_700, Direction.SOUTH,2));
        entries.add(new VehicleEntry(85000_000,85000_800, Direction.SOUTH,2));
    }

    @Test
    public void shouldGiveAverageDistanceForEachSession() throws Exception {
        String output = new AverageDistanceProcessor(360).process(entries);

        assertEquals(expectedOutput, output);
    }

}