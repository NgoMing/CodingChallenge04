package com.aconex.codingchallenge.vehiclesurvey.model;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class SessionsOfDayTest {
    @Test
    public void shouldCreateSessionWithGivenInterval() throws Exception {
        List<SessionsOfDay> output = SessionsOfDay.createSessionsWithInterval(60);

        assertEquals(24, output.size());
        assertEquals(new Date(0), output.get(0).getStartTime());
        assertEquals(new Date(3600_000), output.get(0).getEndTime());
        assertEquals(new Date(43200_000), output.get(12).getStartTime());
        assertEquals(new Date(46800_000), output.get(12).getEndTime());
    }

    @Test
    public void shouldReturnZeroWhenUnevenInterval() throws Exception {
        List<SessionsOfDay> output = SessionsOfDay.createSessionsWithInterval(17);

        assertEquals(0, output.size());
    }
}