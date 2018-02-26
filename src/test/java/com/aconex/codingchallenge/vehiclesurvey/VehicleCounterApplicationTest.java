package com.aconex.codingchallenge.vehiclesurvey;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class VehicleCounterApplicationTest {

    private VehicleCounterApplication app = new VehicleCounterApplication();
    private int[] intervals = {15, 30, 60};
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream oldOutput;

    @Before
    public void setUp() throws Exception {
        oldOutput = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut((oldOutput));
    }

    @Test
    public void shouldCreateProcessorsWithIntervalCorrectly() throws Exception {
        app.createProcessorsWithInterval(intervals);
        assertEquals(intervals.length*3, app.getProcessors().size());
    }

    @Test
    public void run() throws Exception {
        app.createProcessorsWithInterval(intervals);
        app.run("dataSample.txt");
        assertEquals("", outContent.toString());
    }

}