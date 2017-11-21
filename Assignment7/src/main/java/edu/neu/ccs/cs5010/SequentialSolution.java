package edu.neu.ccs.cs5010;

import java.util.*;


/**
 * Sequential solution that reads data from given file and generate desired output files.
 */
public class SequentialSolution {
  // CONSTANTS:
  private static final int FIRST_HOUR = 1;
  private static final int LAST_HOUR = 6;
  private static final int NUMBER_OF_MINUTES_IN_HOUR = 60;

  // private class members
  //skierVertical: <SkierID, vertical sum>
  //liftRides: <liftID,total number of rides>
  //hourRides: <liftId, <liftID, number of rides in this hour>>
  private CommonBuilderWriter commonBw = new CommonBuilderWriter();
  private List<String[]> allRows;
  private Map<String,Integer> skierVertical = new HashMap<>();
  private Map<String,Integer> liftRides = new HashMap<>();
  private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>();

  /**
   * Constructs a new SequentialSolution and initialize allRows, a string array list.
   */
  public SequentialSolution() {
    ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    readWriteCsv.readForSequential("PDPAssignment.csv");
    allRows = readWriteCsv.getAllRows();
    // initialize the hourRides with six hour sections
    for (int i = FIRST_HOUR; i <= LAST_HOUR; i++) {
      hourRides.put(i, new HashMap<>());
    }
  }

  /**
   * run the sequential solution, record actual runtime.
   */
  public void runSequential() {
    long startTime = System.nanoTime();
    processAllRows();
    long endTime = System.nanoTime();
    long duration = endTime - startTime;
    System.out.println("Sequential Solution Runtime: " + duration/1000f + " microseconds");
  }

  /**
   * process all rows in memory, get results from each row, pass maps to file writer functions.
   */
  private void processAllRows() {
    for (int i = 1; i < allRows.size(); i++) {
      // scan all rows, starting from 1 to avoid the header line
      String[] row = allRows.get(i);
      skierResult(row);
      liftResult(row);
      hourResult(row);
    }
    // pass resulting maps to corresponding write-methods
    commonBw.skierBuildWrite(skierVertical, "sequential results/skiers.csv");
    commonBw.liftBuildWrite(liftRides,"sequential results/lifts.csv");
    commonBw.hourBuildWrite(hourRides,"sequential results/hours.csv");
  }

  /**
   * Get result from input row for skiers.csv.
   * @param row a row in the memory
   */
  private void skierResult(String[] row) {
    String skierId = row[2];
    String liftNum = row[3];
    skierVertical.put(skierId, skierVertical.getOrDefault(skierId, 0)
            + commonBw.getVertical(liftNum)); //update total vertical of the skier
  }

  /**
   * Get result from input row for lifts.csv
   * @param row a row in the memory
   */
  private void liftResult(String[] row) {
    String liftId = row[3];
    //update the number of ride of current lift
    liftRides.put(liftId, liftRides.getOrDefault(liftId,0) + 1);
  }

  /**
   * Get result from input row for hours.csv.
   * @param row a row in the memory
   */
  private void hourResult(String[] row) {
    int minute = Integer.parseInt(row[4]);
    int hour = minute / NUMBER_OF_MINUTES_IN_HOUR
            + (minute % NUMBER_OF_MINUTES_IN_HOUR == 0 ? 0 : 1); //calculate the hour
    String liftId = row[3];
    hourRides.get(hour).put(liftId, hourRides.get(hour).getOrDefault(liftId,0) + 1);
  }
}
