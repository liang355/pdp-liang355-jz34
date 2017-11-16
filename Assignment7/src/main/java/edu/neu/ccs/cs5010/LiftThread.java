package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class LiftThread extends Thread {
  private Queue<String> liftQueue; // <liftID>
  private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
  private Map<String,Integer> liftRides = new HashMap<>(); //key: liftID;  value: total number of rides

  public LiftThread(Queue<String> liftQueue) {
    this.liftQueue = liftQueue;
  }

  public void run() { //refactor later
    while (!liftQueue.isEmpty()) {
      String currLiftId = liftQueue.poll();
      liftRides.put(currLiftId, liftRides.getOrDefault(currLiftId,0) + 1); //update the number of ride of current lift
    }

    StringBuilder liftSb = new StringBuilder("LiftID,Number of Rides\n");
    for (int i = 1; i <= 40; i++) { //all 40 lifts
      String liftId = String.valueOf(i);
      liftSb.append(i + "," + liftRides.getOrDefault(liftId,0) + "\n");
    }

    readWriteCsv.printStringToCsv(liftSb.toString(),"concurrent results/lifts.csv");
  }
}
