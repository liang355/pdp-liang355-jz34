package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class HourThread extends Thread {
    // CONSTANTS:
    private static final int NUMBER_OF_HOUR = 6;

    // FIELDS:
    //hourQueue: <hour number(1-6), liftID>
    //hourRides: <liftId, <liftID -> number of rides in this hour>
    private Queue<String> hourQueue;
    private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>();
    private CommonBuilderWriter commonBw = new CommonBuilderWriter();

    /**
     * Constructor: initialize field with hour queue.
     * @param hourQueue a String queue with each string representing
     *                  necessary data from a row for hours.csv
     */
    public HourThread(Queue<String> hourQueue) {
        this.hourQueue = hourQueue;
    }

    /**
     * Build Map[hour, Map[liftId, hour frequency]] from hour queue.
     */
    public void run() {
        // initialize the hourRides with six hour sections
        for (int i = 1; i <= NUMBER_OF_HOUR; i++) {
            hourRides.put(i,new HashMap<>());
        }

        while (!hourQueue.isEmpty()) {
            String[] currPair = hourQueue.poll().split(",");
            int hour = Integer.parseInt(currPair[0]);
            String liftId = currPair[1];
            hourRides.get(hour).put(liftId, hourRides.get(hour).getOrDefault(liftId,0) + 1);
        }

        commonBw.hourBuildWrite(hourRides,"concurrent results/hours.csv");
    }
}
