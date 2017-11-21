package edu.neu.ccs.cs5010.Bonus;

import edu.neu.ccs.cs5010.CommonBuilderWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SmallHourThread extends Thread {
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
    public SmallHourThread(Queue<String> hourQueue) {
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
            hourRides.get(Integer.parseInt(currPair[0])).put(currPair[1],
                    hourRides.get(Integer.parseInt(currPair[0])).getOrDefault(currPair[1],0)
                            + 1);
        }
    }
}
