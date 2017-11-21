package edu.neu.ccs.cs5010.Bonus;

import edu.neu.ccs.cs5010.CommonBuilderWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SmallSkierThread extends Thread{
    private Queue<String> skierQueue;
    private Map<String,Integer> skierVertical = new HashMap<>();
    private CommonBuilderWriter commonBw = new CommonBuilderWriter();

    /**
     * Constructor: initialize field with skier queue.
     * @param skierQueue a String queue with each string representing
     *                   necessary data from a row for skiers.csv
     */
    public SmallSkierThread(Queue<String> skierQueue) {
        this.skierQueue = skierQueue;
    }

    /**
     * Build Map[skierId, vertical] from skier queue.
     */
    public void run() {
        while (!skierQueue.isEmpty()) {
            String[] currPair = skierQueue.poll().split(",");
            skierVertical.put(currPair[0], skierVertical.getOrDefault(currPair[0], 0)
                    + commonBw.getVertical(currPair[1]));
        }
    }
}
