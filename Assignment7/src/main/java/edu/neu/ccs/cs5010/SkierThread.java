package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SkierThread extends Thread {
  // FIELDS:
  // skierQueue: <skierID, liftID>
  // skierVertical: <SkierID, total vertical>
  private Queue<String> skierQueue;
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
      skierVertical.put(skierId, skierVertical.getOrDefault(skierId, 0)
              + commonBw.getVertical(liftNum)); //update total vertical of the skier
    }

    commonBw.skierBuildWrite(skierVertical, "concurrent results/skiers.csv");
  }
}
