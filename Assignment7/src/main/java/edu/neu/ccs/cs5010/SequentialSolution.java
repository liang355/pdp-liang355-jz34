package edu.neu.ccs.cs5010;

import java.util.*;

public class SequentialSolution {
  private CommonBuilderWriter commonBw = new CommonBuilderWriter();
  private List<String[]> allRows;
  private Map<String,Integer> skierVertical = new HashMap<>(); //key: SkierID;  value: total vertical
  private Map<String,Integer> liftRides = new HashMap<>(); //key: liftID;  value: total number of rides
  private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>(); //key: liftId;  value: liftID -> number of rides in this hour

  public SequentialSolution() {
    ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    readWriteCsv.readForSequential();
    allRows = readWriteCsv.getAllRows();
    for (int i = 1; i <= 6; i++) {    // initialize the hourRides with six hour sections
      hourRides.put(i, new HashMap<>());
    }
  }

  public void runSequential() {
    long startTime = System.currentTimeMillis();
    processAllRows();
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    System.out.println("Sequential Solution Runtime: " + duration + " ms.");
  }

  private void processAllRows() {
    for (int i = 1; i < allRows.size(); i++) {  // scan all rows, starting from 1 to avoid the header line
      String[] row = allRows.get(i);
      skierResult(row);
      liftResult(row);
      hourResult(row);
    }
    // pass resulting maps to corresponding write-methods
    commonBw.skierBuildWrite(skierVertical, "sequential results/skier.csv");
    commonBw.liftBuildWrite(liftRides,"sequential results/lifts.csv");
    commonBw.hourBuildWrite(hourRides,"sequential results/hours.csv");
  }

  private void skierResult(String[] row) {
    String skierId = row[2];
    String liftNum = row[3];
    skierVertical.put(skierId, skierVertical.getOrDefault(skierId, 0)
            + commonBw.getVertical(liftNum)); //update total vertical of the skier
  }

  private void liftResult(String[] row) {
    String liftId = row[3];
    liftRides.put(liftId, liftRides.getOrDefault(liftId,0) + 1); //update the number of ride of current lift
  }

  private void hourResult(String[] row) {
    int minute = Integer.parseInt(row[4]);
    int hour = minute / 60 + (minute % 60 == 0 ? 0 : 1); //calculate the hour
    String liftId = row[3];
    hourRides.get(hour).put(liftId, hourRides.get(hour).getOrDefault(liftId,0) + 1);
  }

//  public void skierResult() {
//    for (int i = 1; i < allRows.size(); i++) { //starting from 1 to avoid the header line
//      String skierId = allRows.get(i)[2];
//      String liftNum = allRows.get(i)[3];
//      skierVertical.put(skierId, skierVertical.getOrDefault(skierId, 0)
//          + commonBw.getVertical(liftNum)); //update total vertical of the skier
//    }
//
//    commonBw.skierBuildWrite(skierVertical, "sequential results/skier.csv");
//  }
//
//  public void liftResult() {
//    for (int i = 1; i < allRows.size(); i++) {
//      String liftId = allRows.get(i)[3];
//      liftRides.put(liftId, liftRides.getOrDefault(liftId,0) + 1); //update the number of ride of current lift
//    }
//
//    commonBw.liftBuildWrite(liftRides,"sequential results/lifts.csv");
//  }
//
//  public void hourResult() {
//    for (int i = 1; i <= 6; i++) { // initialize the hourRides with six hour sections
//      hourRides.put(i, new HashMap<>());
//    }
//
//    for (int i = 1; i < allRows.size(); i++) {
//      int minute = Integer.parseInt(allRows.get(i)[4]);
//      int hour = minute / 60 + (minute % 60 == 0 ? 0 : 1); //calculate the hour
//      String liftId = allRows.get(i)[3];
//      hourRides.get(hour).put(liftId, hourRides.get(hour).getOrDefault(liftId,0) + 1);
//    }
//
//    commonBw.hourBuildWrite(hourRides,"sequential results/hours.csv");
//  }
}
