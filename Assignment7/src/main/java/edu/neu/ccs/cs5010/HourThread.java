package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class HourThread extends Thread{
  private Queue<String> hourQueue; //<hour number(1-6), liftID>
  private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
  private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>(); //key: liftId;  value: liftID -> number of rides in this hour

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

    Queue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>( //maxHeap sort the map by value in descending order
        (object1, object2) -> object2.getValue() - object1.getValue()
    );

    StringBuilder hourSb = new StringBuilder();

    for (int i = 1; i <= 6; i++) {
      maxHeap.clear();
      hourSb.append("Hour " + i + "\n");
      hourSb.append("LiftID,Number of Rides\n");
      maxHeap.addAll(hourRides.get(i).entrySet());
      for (int j = 0; j < 10 && maxHeap.size() != 0; j++) {
        Map.Entry<String,Integer> curr = maxHeap.poll();
        hourSb.append(curr.getKey() + "," + curr.getValue() + "\n");
      }
      hourSb.append("\n");
    }

    readWriteCsv.printStringToCsv(hourSb.toString(),"concurrent results/hours.csv");
  }
} //refactor!!!!!!!
