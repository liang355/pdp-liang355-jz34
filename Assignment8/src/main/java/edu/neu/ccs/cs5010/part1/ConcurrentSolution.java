package edu.neu.ccs.cs5010.part1;

import edu.neu.ccs.cs5010.part1.hours.HourThread;
import edu.neu.ccs.cs5010.part1.liftData.LiftRideThread;
import edu.neu.ccs.cs5010.part1.lifts.LiftThread;
import edu.neu.ccs.cs5010.part1.skiers.SkierThread;

import java.util.*;

/**
 * Concurrently persists data for lift data, for all 40k skiers, for each of the 40 lifts
 * and for each hour in the ski day.
 */
public class ConcurrentSolution {
  private Queue<String> skierQueue; // KV pair: <skierID,liftID>
  private Queue<String> liftQueue; // <liftID>
  private Queue<String> hourQueue; //<hour number(1-6), liftID>
  private Map<Integer, String> skierTimeLiftQueue;


  /**
   * Constructs a new ConcurrentSolution and initializes the three queues that stores the data.
   */
  public ConcurrentSolution() {
    ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    readWriteCsv.readForConcurrent("data/PDPAssignment.csv");
    skierQueue = readWriteCsv.getSkierQueue();
    liftQueue = readWriteCsv.getLiftQueue();
    hourQueue = readWriteCsv.getHourQueue();
    skierTimeLiftQueue = readWriteCsv.getSkierTimeLift();
  }

  /**
   * Start three threads with queues, record actual runtime.
   */
  public void runConcurrent() {
//    long startTime = System.nanoTime();
    SkierThread runSkier = new SkierThread(skierQueue);
    LiftThread runLift = new LiftThread(liftQueue);
    HourThread runHour = new HourThread(hourQueue);
    LiftRideThread runLiftRide = new LiftRideThread(skierTimeLiftQueue);
    runSkier.start();
    runLift.start();
    runHour.start();
    runLiftRide.start();
//    long endTime = System.nanoTime();
//    long duration = endTime - startTime;
//    System.out.println("Concurrent Solution Runtime: " + duration/1000f + " microseconds");
  }
}
