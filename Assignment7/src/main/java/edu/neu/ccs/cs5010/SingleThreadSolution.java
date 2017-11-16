package edu.neu.ccs.cs5010;

import java.util.*;

public class SingleThreadSolution {
  private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
  private List<String[]> allRows = readWriteCsv.getAllRows();
  private Map<String,Integer> skierVertical = new HashMap<>(); //key: SkierID;  value: total vertical
  private Map<String,Integer> liftRides = new HashMap<>(); //key: liftID;  value: total number of rides
  private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>(); //key: liftId;  value: liftID -> number of rides in this hour

  public static void main(String[] args) {
    SingleThreadSolution ss = new SingleThreadSolution();
    ss.skierResult();
    ss.liftResult();
    ss.hourResult();
  }

  public void skierResult() {
    for (int i = 1; i < allRows.size(); i++) { //starting from 1 to avoid the header line
      String skierId = allRows.get(i)[2];
      String liftNum = allRows.get(i)[3];
      if (!skierVertical.containsKey(skierId)) {
        skierVertical.put(skierId,0);
      }
      skierVertical.put(skierId,skierVertical.get(skierId) + getVertical(liftNum)); //update total vertical of the skier
    }

    Queue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>( //maxHeap sort the map by value in descending order
        (object1, object2) -> object2.getValue() - object1.getValue()
    );

    maxHeap.addAll(skierVertical.entrySet());

    StringBuilder skierSb = new StringBuilder("SkierID,Vertical\n");
    for (int i = 0; i < 100 && maxHeap.size() != 0; i++) { //top 100, check whether there are enough skiers at the same time
      Map.Entry<String,Integer> curr = maxHeap.poll();
      skierSb.append(curr.getKey() + "," + curr.getValue() + "\n");
    }

    readWriteCsv.printStringToCsv(skierSb.toString(),"sequential results/skier.csv");
  }

  private int getVertical(String liftNum) {
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

  public void liftResult() {
    for (int i = 1; i < allRows.size(); i++) {
      String liftId = allRows.get(i)[3];
      liftRides.put(liftId, liftRides.getOrDefault(liftId,0) + 1); //update the number of ride of current lift
    }
    StringBuilder liftSb = new StringBuilder("LiftID,Number of Rides\n");
    for (int i = 1; i <= 40; i++) { //all 40 lifts
      String liftId = String.valueOf(i);
      liftSb.append(i + "," + liftRides.getOrDefault(liftId,0) + "\n");
    }

    readWriteCsv.printStringToCsv(liftSb.toString(),"sequential results/lifts.csv");
  }

  public void hourResult() {
    for (int i = 1; i <= 6; i++) { // initialize the hourRides with six hour sections
      hourRides.put(i,new HashMap<>());
    }

    for (int i = 1; i < allRows.size(); i++) {
      int hour = getHourNum(allRows.get(i)[4]);
      String liftId = allRows.get(i)[3];
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

    readWriteCsv.printStringToCsv(hourSb.toString(),"sequential results/hours.csv");
  }

  private int getHourNum(String minute) {
    int minuteNum = Integer.parseInt(minute);
    if (minuteNum < 61) {
      return 1;
    } else if (minuteNum < 121) {
      return 2;
    } else if (minuteNum < 181) {
      return 3;
    } else if (minuteNum < 241) {
      return 4;
    } else if (minuteNum < 301) {
      return 5;
    } else {
      return 6;
    }
  }
}
