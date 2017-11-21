package edu.neu.ccs.cs5010.Bonus;

import edu.neu.ccs.cs5010.CommonBuilderWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SmallLiftThread extends Thread {
    private Queue<String> liftQueue;
    private Map<String,Integer> liftRides = new HashMap<>();
    private CommonBuilderWriter commonBw = new CommonBuilderWriter();

    /**
     * Constructor: initialize field with lift queue.
     * @param liftQueue a String queue with each string
     *                  representing necessary data from a row for lifts.csv
     */
    public SmallLiftThread(Queue<String> liftQueue) {
        this.liftQueue = liftQueue;
    }

    /**
     * Build Map[liftId, frequency] from lift queue.
     */
    public void run() { //refactor later
        while (!liftQueue.isEmpty()) {
            //update the number of ride of current lift
            String currLiftId = liftQueue.poll();
            liftRides.put(currLiftId, liftRides.getOrDefault(currLiftId,0) + 1);
        }
    }
}
