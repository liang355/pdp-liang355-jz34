package edu.neu.ccs.cs5010.part1.lifts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * liftThread is a thread finds the total number of lift rides in this day.
 */
public class LiftThread extends Thread {
  // FIELDS:
  //liftQueue: <liftID>
  //liftRides: <liftID, value: total number of rides>
  private Queue<String> liftQueue;
  private Map<String,Integer> liftRides = new HashMap<>();

  /**
   * Constructor: initialize field with lift queue.
   * @param liftQueue a String queue with each string
   *                  representing necessary data from a row for lifts.csv
   */
  public LiftThread(Queue<String> liftQueue) {
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
    writeToFile();
  }

  private void writeToFile() {
    try {
      LiftFileEditor fe = new LiftFileEditor("lifts.dat");
      for (String key : liftRides.keySet()) {
        fe.insertRecord(new LiftRecord(Integer.valueOf(key), liftRides.get(key)));
      }
    } catch (IOException ioe) {
      System.out.println("*** OOPS! Something is wrong when writing to file!");
    }
  }
}
