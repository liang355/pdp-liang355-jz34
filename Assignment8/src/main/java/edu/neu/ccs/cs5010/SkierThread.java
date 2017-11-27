package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SkierThread extends Thread {
    // FIELDS:
    // skierQueue: <skierID, liftID>
    // skierVertical: <SkierID, total vertical>
    private Queue<String> skierQueue;
    private Map<String,Integer> skierFrequency = new HashMap<>();
    private Map<String,Integer> skierVertical = new HashMap<>();
    private CommonBuilderWriter commonBw = new CommonBuilderWriter();

    /**
     * Constructor: initialize field with skier queue.
     * @param skierQueue a String queue with each string representing
     *                   necessary data from a row for skiers.csv
     */
    public SkierThread(Queue<String> skierQueue) {
        this.skierQueue = skierQueue;
    }

    /**
     * Build Map[skierId, vertical] from skier queue.
     */
    public void run() {
        while (!skierQueue.isEmpty()) {
            String[] currPair = skierQueue.poll().split(",");
            String skierId = currPair[0];
            String liftNum = currPair[1];
            skierFrequency.put(skierId, skierFrequency.getOrDefault(skierId, 0) + 1);
            skierVertical.put(skierId, skierVertical.getOrDefault(skierId, 0)
                    + commonBw.getVertical(liftNum)); //update total vertical of the skier
        }
        writeToFile();
//        commonBw.skierBuildWrite(skierVertical, "concurrent results/skiers.csv");
    }

    private void writeToFile() {
        try {
            SkierFileEditor fe = new SkierFileEditor("skier.dat");
            for(String key : skierFrequency.keySet()) {
                fe.insertRecord(new SkierRecord(Integer.valueOf(key), skierFrequency.get(key), skierVertical.get(key), 0));
            }
        } catch (IOException ioe) {
            System.out.println("*** OOPS! Something is wrong when writing to file!");
        }
    }
}
