package com.aconex.codingchallenge.vehiclesurvey.processor;

import com.aconex.codingchallenge.vehiclesurvey.model.Direction;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleCountProcessorTest {

    private List<VehicleEntry> entries = new ArrayList<>();

    private String expectedOutput =
        "Session 00:00:00 to 01:00:00 | Day 0 Count=3 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 01:00:00 to 02:00:00 | Day 0 Count=1 | Day 1 Count=2 | Day 2 Count=1 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 02:00:00 to 03:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=1 | Day 3 Count=1 | Day 4 Count=1\n" +
        "Session 03:00:00 to 04:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 04:00:00 to 05:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 05:00:00 to 06:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 06:00:00 to 07:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 07:00:00 to 08:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 08:00:00 to 09:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 09:00:00 to 10:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 10:00:00 to 11:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 11:00:00 to 12:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 12:00:00 to 13:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 13:00:00 to 14:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 14:00:00 to 15:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 15:00:00 to 16:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 16:00:00 to 17:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 17:00:00 to 18:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 18:00:00 to 19:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 19:00:00 to 20:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 20:00:00 to 21:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 21:00:00 to 22:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 22:00:00 to 23:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n" +
        "Session 23:00:00 to 00:00:00 | Day 0 Count=0 | Day 1 Count=0 | Day 2 Count=0 | Day 3 Count=0 | Day 4 Count=0\n";

    @Before
    public void setUp() throws Exception {
        entries.add(new VehicleEntry(1000_000,1000_100, Direction.SOUTH,0));
        entries.add(new VehicleEntry(2000_000,2000_100, Direction.SOUTH,0));
        entries.add(new VehicleEntry(3000_000,3000_100, Direction.SOUTH,0));
        entries.add(new VehicleEntry(4000_000,4000_100, Direction.SOUTH,0));
        entries.add(new VehicleEntry(5000_000,5000_100, Direction.SOUTH,1));
        entries.add(new VehicleEntry(6000_000,6000_100, Direction.SOUTH,1));
        entries.add(new VehicleEntry(7000_000,7000_100, Direction.SOUTH,2));
        entries.add(new VehicleEntry(8000_000,8000_100, Direction.SOUTH,2));
        entries.add(new VehicleEntry(9000_000,9000_100, Direction.SOUTH,3));
        entries.add(new VehicleEntry(1000_0000,10000_100, Direction.SOUTH,4));
    }

    @Test
    public void shouldGiveSessionOutputForHourlyCount() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(60);
        String output = processor.process(entries);

        assertEquals(expectedOutput, output);
    }

            @Test
    public void shouldNotGiveOutputIfSessionIntervalIsNotEvenlyDistributed() throws Exception {
        VehicleCountProcessor processor = new VehicleCountProcessor(25);
        String output = processor.process(entries);

        assertEquals("", output);
    }
}