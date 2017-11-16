package edu.neu.ccs.cs5010;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CommonBuilderWriter {
  private ReadWriteCsv readWriteCsv = new ReadWriteCsv();

  public void skierBuildWrite(Map<String,Integer> skierVertical, String fileName) {
    Queue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>( //maxHeap sort the map by value in descending order
        (object1, object2) -> object2.getValue() - object1.getValue()
    );

    maxHeap.addAll(skierVertical.entrySet());

    StringBuilder skierSb = new StringBuilder("SkierID,Vertical\n");
    for (int i = 0; i < 100 && maxHeap.size() != 0; i++) { //top 100, check whether there are enough skiers at the same time
      Map.Entry<String,Integer> curr = maxHeap.poll();
      skierSb.append(curr.getKey() + "," + curr.getValue() + "\n");
    }

    readWriteCsv.printStringToCsv(skierSb.toString(),fileName);
  }

  public int getVertical(String liftNum) {
    int listNumInt = Integer.parseInt(liftNum);
    if (listNumInt < 11) {
      return 200;
    } else if (listNumInt < 21) {
      return 300;
    } else if (listNumInt < 31) {
      return 400;
    } else {
      return 500;
    }
  }

  public void liftBuildWrite(Map<String,Integer> liftRides,String fileName) {
    StringBuilder liftSb = new StringBuilder("LiftID,Number of Rides\n");
    for (int i = 1; i <= 40; i++) { //all 40 lifts
      String liftId = String.valueOf(i);
      liftSb.append(i + "," + liftRides.getOrDefault(liftId,0) + "\n");
    }

    readWriteCsv.printStringToCsv(liftSb.toString(),fileName);
  }

  public void hourBuildWrite(Map<Integer,Map<String,Integer>> hourRides, String fileName) {
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

    readWriteCsv.printStringToCsv(hourSb.toString(),fileName);
  }
}
