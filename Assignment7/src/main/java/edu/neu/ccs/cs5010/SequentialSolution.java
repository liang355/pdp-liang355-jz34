package edu.neu.ccs.cs5010;

import java.util.*;

/**
 * Sequential solution that reads data from given file and generate desired output files.
 */
public class SequentialSolution {
  private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
  private CommonBuilderWriter commonBw = new CommonBuilderWriter();
  private List<String[]> allRows;
  private Map<String,Integer> skierVertical = new HashMap<>(); //key: SkierID;  value: total vertical
  private Map<String,Integer> liftRides = new HashMap<>(); //key: liftID;  value: total number of rides
  private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>(); //key: liftId;  value: liftID -> number of rides in this hour

  /**
   * Constructs a new SequentialSolution and initialize allRows, a string array list.
   */
  public SequentialSolution() {
    readWriteCsv.readForSequential();
    allRows = readWriteCsv.getAllRows();
  }

  /**
   * Run the single-thread solution.
   */
  public void runSequential() {
    long startTime = System.currentTimeMillis();
    skierResult();
    liftResult();
    hourResult();
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    System.out.println("Sequential Solution Runtime: " + duration + " ms.");
  }

  /**
   * finds the skiers with the Top 100 vertical totals in descending order.
   */
  public void skierResult() {
    for (int i = 1; i < allRows.size(); i++) { //starting from 1 to avoid the header line
      String skierId = allRows.get(i)[2];
      String liftNum = allRows.get(i)[3];
      skierVertical.put(skierId, skierVertical.getOrDefault(skierId, 0)
          + commonBw.getVertical(liftNum)); //update total vertical of the skier
    }

    commonBw.skierBuildWrite(skierVertical, "sequential results/skier.csv");
  }

  /**
   * finds the ride number of each liftId.
   */
  public void liftResult() {
    for (int i = 1; i < allRows.size(); i++) {
      String liftId = allRows.get(i)[3];
      liftRides.put(liftId, liftRides.getOrDefault(liftId,0) + 1); //update the number of ride of current lift
    }

    commonBw.liftBuildWrite(liftRides,"sequential results/lifts.csv");
  }

  /**
   * finds the top 10 busiest lifts for each section of hour.
   */
  public void hourResult() {
    for (int i = 1; i <= 6; i++) { // initialize the hourRides with six hour sections
      hourRides.put(i,new HashMap<>());
    }

    for (int i = 1; i < allRows.size(); i++) {
      int minute = Integer.parseInt(allRows.get(i)[4]);
      int hour = minute/60 + (minute%60 == 0 ? 0 : 1);//calculate the hour
      String liftId = allRows.get(i)[3];
      hourRides.get(hour).put(liftId, hourRides.get(hour).getOrDefault(liftId,0) + 1);
    }

    commonBw.hourBuildWrite(hourRides,"sequential results/hours.csv");
  }
}
