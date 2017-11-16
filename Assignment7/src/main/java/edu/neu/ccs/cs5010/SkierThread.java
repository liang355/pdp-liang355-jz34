package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class SkierThread extends Thread{
  private Queue<String> skierQueue; // KV pair: <skierID,liftID>
  private Map<String,Integer> skierVertical = new HashMap<>(); //key: SkierID;  value: total vertical
  private CommonBuilderWriter commonBw = new CommonBuilderWriter();

  public SkierThread(Queue<String> skierQueue) {
    this.skierQueue = skierQueue;
  }

  public void run() {
    while (!skierQueue.isEmpty()) {
      String[] currPair = skierQueue.poll().split(",");
      String skierId = currPair[0];
      String liftNum = currPair[1];
      skierVertical.put(skierId, skierVertical.getOrDefault(skierId, 0) +
          commonBw.getVertical(liftNum)); //update total vertical of the skier
    }

    commonBw.skierBuildWrite(skierVertical, "concurrent results/skier.csv");
  }
}
