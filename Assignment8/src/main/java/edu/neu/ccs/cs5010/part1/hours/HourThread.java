package edu.neu.ccs.cs5010.part1.hours;

import java.io.IOException;
import java.util.*;

/**
 * hourThread is a thread finds the top 10 most popular lifts, ordered from the lift with
 * the most rides to the lift with the least.
 */
public class HourThread extends Thread {
  // CONSTANTS:
  private static final int NUMBER_OF_HOUR = 6;

  // FIELDS:
  //hourQueue: <hour number(1-6), liftID>
  //hourRides: <liftId, <liftID -> number of rides in this hour>
  private Queue<String> hourQueue;
  private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>();

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
    writeToFile();
  }

  private void writeToFile() {
    try {
      HourFileEditor fe = new HourFileEditor("hours.dat");
      Queue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>(
          (object1, object2) -> object2.getValue() - object1.getValue()
      );
      for (int i = 1; i <= NUMBER_OF_HOUR; i++) {
        //maxHeap.clear();
        maxHeap.addAll(hourRides.get(i).entrySet());
        List<Integer> topTenList = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
          Map.Entry<String, Integer> curr = maxHeap.poll();
          topTenList.add(Integer.valueOf(curr.getKey()));
        }
        fe.insertRecord(new HourRecord(i,topTenList));
      }
    } catch (IOException ioe) {
      System.out.println("*** OOPS! Something is wrong when writing to file!");
    }
  }
}
