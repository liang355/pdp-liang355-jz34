package edu.neu.ccs.cs5010;

import java.util.*;

public class ConcurrentSolution {
  private ReadWriteCsv readWriteCsv = new ReadWriteCsv();

  private Queue<String> skierQueue; // KV pair: <skierID,liftID>
  private Queue<String> liftQueue; // <liftID>
  private Queue<String> hourQueue; //<hour number(1-6), liftID>

  private Map<String,Integer> skierVertical = new HashMap<>(); //key: SkierID;  value: total vertical
  private Map<String,Integer> liftRides = new HashMap<>(); //key: liftID;  value: total number of rides
  private Map<Integer,Map<String,Integer>> hourRides = new HashMap<>(); //key: liftId;  value: liftID -> number of rides in this hour

  public ConcurrentSolution() {
    readWriteCsv.readForConcurrent();
    skierQueue = readWriteCsv.getSkierQueue();
    liftQueue = readWriteCsv.getLiftQueue();
    hourQueue = readWriteCsv.getHourQueue();
  }

////  public static void main(String[] args) {
////    ConcurrentSolution test1 = new ConcurrentSolution();
////    long startTime = System.currentTimeMillis();
////    test1.runConcurrent();
////    long endTime = System.currentTimeMillis();
////    long duration = endTime - startTime;
////    System.out.println("Execution Time of Concurrent Solution: " + duration + " ms.");
//
//
//  }

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
