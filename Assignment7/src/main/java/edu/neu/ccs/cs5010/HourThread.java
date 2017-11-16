package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class HourThread extends Thread{
  private Queue<String> hourQueue; //<hour number(1-6), liftID>
  private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>(); //key: liftId;  value: liftID -> number of rides in this hour
  private CommonBuilderWriter commonBw = new CommonBuilderWriter();

  public HourThread(Queue<String> hourQueue) {
    this.hourQueue = hourQueue;
  }

  public void run(){
    for (int i = 1; i <= 6; i++) { // initialize the hourRides with six hour sections
      hourRides.put(i,new HashMap<>());
    }

    while (!hourQueue.isEmpty()) {
      String[] currPair = hourQueue.poll().split(",");
      int hour = Integer.parseInt(currPair[0]);
      String liftId = currPair[1];
      hourRides.get(hour).put(liftId, hourRides.get(hour).getOrDefault(liftId,0) + 1);
    }

    commonBw.hourBuildWrite(hourRides,"concurrent results/hours.csv");
  }
}
