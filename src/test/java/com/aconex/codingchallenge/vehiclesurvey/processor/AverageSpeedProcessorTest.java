package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.model.Direction;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AverageSpeedProcessorTest {

    private final List<VehicleEntry> entries = new ArrayList<>();

    private String expectedOutput =
        "Session 00:00:00 to 06:00:00 | Average speed = 67.5\n" +
        "Session 06:00:00 to 12:00:00 | Average speed = 26.25\n" +
        "Session 12:00:00 to 18:00:00 | Average speed = 16.5\n" +
        "Session 18:00:00 to 00:00:00 | Average speed = 12.053571428571429\n";

    @Before
    public void setUp() throws Exception {
        entries.add(new VehicleEntry(10000_000,10000_100, Direction.SOUTH,0));
        entries.add(new VehicleEntry(20000_000,20000_200, Direction.SOUTH,0));
        entries.add(new VehicleEntry(30000_000,30000_300, Direction.SOUTH,0));
        entries.add(new VehicleEntry(40000_000,40000_400, Direction.SOUTH,0));
        entries.add(new VehicleEntry(50000_000,50000_500, Direction.SOUTH,1));
        entries.add(new VehicleEntry(60000_000,60000_600, Direction.SOUTH,1));
        entries.add(new VehicleEntry(70000_000,70000_700, Direction.SOUTH,2));
        entries.add(new VehicleEntry(80000_000,80000_800, Direction.SOUTH,2));
    }

    @Test
    public void shouldGiveAverageSpeedForEachSession() throws Exception {
        String output = new AverageSpeedProcessor(360).process(entries);

        assertEquals(expectedOutput, output);
    }

}