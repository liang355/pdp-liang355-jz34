package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class LiftThread extends Thread {
  private Queue<String> liftQueue; // <liftID>
  private Map<String,Integer> liftRides = new HashMap<>(); //key: liftID;  value: total number of rides
  private CommonBuilderWriter commonBw = new CommonBuilderWriter();

  public LiftThread(Queue<String> liftQueue) {
    this.liftQueue = liftQueue;
  }

  public void run() { //refactor later
    while (!liftQueue.isEmpty()) {
      String currLiftId = liftQueue.poll();
      liftRides.put(currLiftId, liftRides.getOrDefault(currLiftId,0) + 1); //update the number of ride of current lift
    }

    commonBw.liftBuildWrite(liftRides,"concurrent results/lifts.csv");
  }
}
