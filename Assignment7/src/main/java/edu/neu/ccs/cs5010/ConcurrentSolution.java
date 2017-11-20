package edu.neu.ccs.cs5010;

import java.util.*;

/**
 * ConcurrentSolution using data processing pipelines to parallelize the solution.
 */
public class ConcurrentSolution {
  private Queue<String> skierQueue; // KV pair: <skierID,liftID>
  private Queue<String> liftQueue; // <liftID>
  private Queue<String> hourQueue; //<hour number(1-6), liftID>

  /**
   * Constructs a new ConcurrentSolution and initializes the three queues that stores the data.
   */
  public ConcurrentSolution() {
    ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    readWriteCsv.readForConcurrent("PDPAssignment.csv");
    skierQueue = readWriteCsv.getSkierQueue();
    liftQueue = readWriteCsv.getLiftQueue();
    hourQueue = readWriteCsv.getHourQueue();
  }

  /**
   * Start three threads with queues, record actual runtime.
   */
  public void runConcurrent() {
    SkierThread runSkier = new SkierThread(skierQueue);
    LiftThread runLift = new LiftThread(liftQueue);
    HourThread runHour = new HourThread(hourQueue);

    long startTime = System.currentTimeMillis();
    runSkier.start();
    runLift.start();
    runHour.start();
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    System.out.println("Concurrent Solution Runtime: " + duration + " ms.");
  }
}
