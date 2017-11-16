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
    readWriteCsv.readForSequential();
    skierQueue = readWriteCsv.getSkierQueue();
    liftQueue = readWriteCsv.getLiftQueue();
    hourQueue = readWriteCsv.getHourQueue();
  }



}
