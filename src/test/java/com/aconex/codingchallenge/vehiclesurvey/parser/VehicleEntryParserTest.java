package com.aconex.codingchallenge.vehiclesurvey.parser;

import com.aconex.codingchallenge.vehiclesurvey.model.Direction;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.converters.Param;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class VehicleEntryParserTest {

    private static final Object[] listOfValidInputs() {
        return new Object[] {
                // North entry
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "A2345")), Direction.NORTH},
                // South entry
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "B1245", "A2345", "B2357")), Direction.SOUTH}
        };
    }

    @Test
    @Parameters(method = "listOfValidInputs")
    public void shouldCreateValidEntry(List<String> listOfInputs, Direction direction) throws Exception {
        List<String> input = listOfInputs;
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertEquals(direction, output.get(0).getDirection());
    }

    private static final Object[] listOfMultipleValidInputs() {
        return new Object[] {
                // North entries
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "A1245", "A4567", "A4578")),
                        new Direction[]{Direction.NORTH, Direction.NORTH}},
                // South entries
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "B1240", "A1245", "B1250", "A4567", "B4570", "A4578", "B4580")),
                        new Direction[]{Direction.SOUTH, Direction.SOUTH}},
                // Both entries
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "A1245", "A4567", "B4570", "A4578", "B4580",
                        "A12340", "B12400", "A12450", "B12500", "A45670", "A45780")),
                        new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH}}
        };
    }

    @Test
    @Parameters(method = "listOfMultipleValidInputs")
    public void shouldCreateMultipleValidEntries(List<String> listOfInputs, Direction[] directions) {
        List<String> input = listOfInputs;
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        for (int i = 0; i < directions.length; i++)
            assertEquals(directions[i], output.get(i).getDirection());
    }

    private static final Object[] listOfInvalidInputs() {
        return new Object[] {
                // number of entries is smaller than minimum one needed
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "A2345", "A4567"))},
                // number of entries is smaller than one needed for South direction
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "B1245", "A2345", "B7789", "A8851", "B8900", "A9851"))},
                // invalid order of South direction entry
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "B1245", "A2345", "A2356"))},
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "B1245", "B2345", "A2356"))},
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "B1245", "B2345", "B2356"))},
                // invalid South entry
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "B1234", "A1245", "B1245"))},
                // invalid North entry
                new Object[] {new ArrayList<String>(Arrays.asList("A1234", "A1234"))}
        };
    }

    @Test
    @Parameters(method = "listOfInvalidInputs")
    public void shouldReturnEmptyListWhenTryCreateInvalidEntries(List<String> listOfInputs) {
        List<String> input = listOfInputs;
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertTrue(output.isEmpty());
    }

    private static final Object[] updateDayTest() {
        return new Object[] {
                // should increase day with North direction entry
                new Object[] {new ArrayList<String>(Arrays.asList("A2000", "A2005", "A1000", "A1005")), 0, 1},
                // should increase day with South direction entry
                new Object[] {new ArrayList<String>(Arrays.asList("A2000", "B2005", "A2100", "B2105", "A1000", "B1005", "A1100", "B1105")), 0, 1},
                // should increase day with both direction entry
                new Object[] {new ArrayList<String>(Arrays.asList("A2000", "A2005", "A1000", "B1005", "A1100", "B1105")), 0, 1},
                // should not increase day with North direction entry
                new Object[] {new ArrayList<String>(Arrays.asList("A1000", "A1005", "A2000", "A2005")), 0, 0},
                // should not increase day with South direction entry
                new Object[] {new ArrayList<String>(Arrays.asList("A1000", "B1005", "A1100", "B1105", "A2000", "B2005", "A2100", "B2105")), 0, 0},
                // should not increase day with both direction entry
                new Object[] {new ArrayList<String>(Arrays.asList("A1000", "B1005", "A1100", "B1105", "A2000", "A2005")), 0, 0}
        };
    }

    @Test
    @Parameters(method = "updateDayTest")
    public void shouldUpdateDayCorrectly(List<String> listOfInputs, int firstDay, int secondDay) throws Exception {
        List<String> input = listOfInputs;
        List<VehicleEntry> output = new VehicleEntryParser().parse(input);

        assertEquals(firstDay, output.get(0).getDay());
        assertEquals(secondDay, output.get(1).getDay());
    }
}