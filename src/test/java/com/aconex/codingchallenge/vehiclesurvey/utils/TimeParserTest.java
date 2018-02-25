package com.aconex.codingchallenge.vehiclesurvey.utils;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class TimeParserTest {

    private static final Object[] invalidInput() {
        return new Object[] {
                // negative value
                new Object[] {"A-1234"},
                // greater than the miliseconds in a day
                new Object[] {"B86400001"},
                // wrong type number
                new Object[] {"A12ab45"},
                // large input
                new Object[] {"A123456789123456789123456789"}
        };
    }

    @Test
    public void shouldReturnMilisecondsForValidInput() throws Exception {
        int miliseconds = TimeParser.parseMilisecondsFrom("A123456");
        assertEquals(123456, miliseconds);
    }

    @Test
    @Parameters(method = "invalidInput")
    public void shouldReturnMinusOneForInvalidInput(String input) throws Exception {
        int minusOne = TimeParser.parseMilisecondsFrom(input);
        assertEquals(-1, minusOne);
    }
}