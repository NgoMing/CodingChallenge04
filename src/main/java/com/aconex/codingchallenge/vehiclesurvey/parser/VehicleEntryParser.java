package com.aconex.codingchallenge.vehiclesurvey.parser;

import com.aconex.codingchallenge.vehiclesurvey.constance.App;
import com.aconex.codingchallenge.vehiclesurvey.model.Direction;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntry;
import com.aconex.codingchallenge.vehiclesurvey.model.VehicleEntryException;
import com.aconex.codingchallenge.vehiclesurvey.utils.TimeParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * parse vehicle entry to get features from raw data recorded
 */
public class VehicleEntryParser {

    public static final int NUMBER_OF_ENTRIES_FOR_NORTH_DIRECTION = 2;
    public static final int NUMBER_OF_ENTRIES_FOR_SOUTH_DIRECTION = 4;
    public static final int MINIMUM_NUMBER_OF_ENTRIES_NEEDED = 2;

    private int currentDay;
    private Date lastEntryDay;

    /**
     * parse all the input data lines
     * @param inputLines
     * @return list of VehicleEntry
     */
    public List<VehicleEntry> parse(List<String> inputLines) {
        List<VehicleEntry> emptyList = new ArrayList<>();
        List<VehicleEntry> vehicleEntries = new ArrayList<>();

        currentDay = 0;
        lastEntryDay = new Date(0);

        while(!inputLines.isEmpty()) {
            if (isInsufficientEntries(inputLines, MINIMUM_NUMBER_OF_ENTRIES_NEEDED)) {
                return emptyList;
            }

            // check the second line to get direction
            Direction direction = parseDirection(inputLines.get(1));

            int numberOfEntriesNeeded = (direction == Direction.NORTH ?
                                            NUMBER_OF_ENTRIES_FOR_NORTH_DIRECTION :
                                            NUMBER_OF_ENTRIES_FOR_SOUTH_DIRECTION);

            if (isInsufficientEntries(inputLines, numberOfEntriesNeeded)) {
                return emptyList;
            }

            try {
                if (direction == Direction.NORTH)
                    inputLines = addNorthDirectionEntry(inputLines, vehicleEntries);
                else
                    inputLines = addSouthDirectionEntry(inputLines, vehicleEntries);
            }
            catch (VehicleEntryException vee) {
                System.out.println("Error while creating entry");
                return emptyList;
            }
        }

        return vehicleEntries;
    }

    /**
     * add Vehicle entry related to South direction
     * @param inputLines
     * @param vehicleEntries
     * @return input lines remained after parsing
     * @throws VehicleEntryException if invalid order of entries or invalid entries occurs
     */
    private List<String> addSouthDirectionEntry(List<String> inputLines, List<VehicleEntry> vehicleEntries) throws VehicleEntryException {
        String frontAxleEntry1 = inputLines.get(0);
        String rearAxleEntry1 = inputLines.get(1);
        String frontAxleEntry2 = inputLines.get(2);
        String rearAxleEntry2 = inputLines.get(3);

        if (!isOrderOfEntriesValid(frontAxleEntry1, rearAxleEntry1,
                                   frontAxleEntry2, rearAxleEntry2))
            throw new VehicleEntryException("Invalid order of entries: " +
                                    frontAxleEntry1 + ", " +
                                    rearAxleEntry1 + ", " +
                                    frontAxleEntry2 + ", " +
                                    rearAxleEntry2);

        int frontAxleTime1 = TimeParser.parseMilisecondsFrom(frontAxleEntry1);
        int rearAxleTime1 = TimeParser.parseMilisecondsFrom(rearAxleEntry1);
        int frontAxleTime2 = TimeParser.parseMilisecondsFrom(frontAxleEntry2);
        int rearAxleTime2 = TimeParser.parseMilisecondsFrom(rearAxleEntry2);

        int frontAxleTime = (frontAxleTime1 + frontAxleTime2) / 2;
        int rearAxleTime = (rearAxleTime1 + rearAxleTime2) / 2;

        VehicleEntry vehicleEntry = new VehicleEntry(frontAxleTime, rearAxleTime, Direction.SOUTH, currentDay);

        if (!vehicleEntry.isValid()) {
            throw new VehicleEntryException("Invalid South direction entry");
        }

        updateDay(vehicleEntry);
        vehicleEntries.add(vehicleEntry);
        return inputLines.subList(4, inputLines.size());
    }

    /**
     * check the validation of order of entries
     * @param frontAxleEntry1
     * @param rearAxleEntry1
     * @param frontAxleEntry2
     * @param rearAxleEntry2
     * @return true if the order is correct
     */
    private boolean isOrderOfEntriesValid(String frontAxleEntry1, String rearAxleEntry1, String frontAxleEntry2, String rearAxleEntry2) {

        return frontAxleEntry1.startsWith(App.FIRST_SENSOR_PREFIX) &&
               rearAxleEntry1.startsWith(App.SECOND_SENSOR_PREFIX) &&
               frontAxleEntry2.startsWith(App.FIRST_SENSOR_PREFIX) &&
               rearAxleEntry2.startsWith(App.SECOND_SENSOR_PREFIX);
    }

    /**
     * add Vehicle entry related to North direction
     * @param inputLines
     * @param vehicleEntries
     * @return input lines remained after parsing
     * @throws VehicleEntryException if invalid entries occurs
     */
    private List<String> addNorthDirectionEntry(List<String> inputLines, List<VehicleEntry> vehicleEntries) throws VehicleEntryException {
        String frontAxleEntry = inputLines.get(0);
        String rearAxleEntry = inputLines.get(1);

        int frontAxleTime = TimeParser.parseMilisecondsFrom(frontAxleEntry);
        int rearAxleTime = TimeParser.parseMilisecondsFrom(rearAxleEntry);

        VehicleEntry vehicleEntry = new VehicleEntry(frontAxleTime, rearAxleTime, Direction.NORTH, currentDay);

        if (!vehicleEntry.isValid()) {
            throw new VehicleEntryException("Invalid North direction entry");
        }

        updateDay(vehicleEntry);
        vehicleEntries.add(vehicleEntry);
        return inputLines.subList(2, inputLines.size());
    }

    /**
     * increase day if current day < previous day
     * @param vehicleEntry
     */
    private void updateDay(VehicleEntry vehicleEntry) {
        Date currentEntryTime = vehicleEntry.getTimeEntry();
        if (currentEntryTime.compareTo(lastEntryDay) < 0) {
            currentDay ++;
            vehicleEntry.setDay(currentDay);
        }

        lastEntryDay = currentEntryTime;
    }

    /**
     * define direction based on the second line
     * @param secondLine
     * @return direction of the vehicle entry
     */
    private Direction parseDirection(String secondLine) {
        if (secondLine.startsWith(App.SECOND_SENSOR_PREFIX)) {
            return Direction.SOUTH;
        }
        else {
            return Direction.NORTH;
        }
    }

    /**
     * check the sufficient number of data lines for specific entry
     * @param inputLines
     * @param numberOfEntriesNeeded
     * @return true if number of data lines are smaller than number of data lines needed
     */
    private boolean isInsufficientEntries(List<String> inputLines, int numberOfEntriesNeeded) {
        return inputLines.size() < numberOfEntriesNeeded;
    }
}
