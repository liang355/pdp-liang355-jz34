package edu.neu.ccs.cs5010.part1.skiers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * SkierThread is a thread finds the number of lift rides they do each day,
 * and the total amount of vertical metres they ski.
 */
public class SkierThread extends Thread {
  // CONSTANTS:
  private static final int METER_200  = 200;
  private static final int METER_300  = 300;
  private static final int METER_400  = 400;
  private static final int METER_500  = 500;

  // FIELDS:
  // skierQueue: <skierID, liftID>
  // skierVertical: <SkierID, total vertical>
  private Queue<String> skierQueue;
  private Map<String,Integer> skierFrequency = new HashMap<>();
  private Map<String,Integer> skierVertical = new HashMap<>();

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
          + getVertical(liftNum)); //update total vertical of the skier
    }
    writeToFile();
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

  /**
   * finds the vertical rise distance of given liftId.
   * @param liftNum liftId.
   * @return vertical rise distance of this liftId.
   */
  public int getVertical(String liftNum) {
    int liftNumInt = Integer.parseInt(liftNum);
    if (liftNumInt < 11) {
      return METER_200;
    } else if (liftNumInt < 21) {
      return METER_300;
    } else if (liftNumInt < 31) {
      return METER_400;
    } else {
      return METER_500;
    }
  }
}
