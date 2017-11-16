package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class SkierThread extends Thread{
  private Queue<String> skierQueue; // KV pair: <skierID,liftID>
  private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
  private Map<String,Integer> skierVertical = new HashMap<>(); //key: SkierID;  value: total vertical

  public SkierThread(Queue<String> skierQueue) {
    this.skierQueue = skierQueue;
  }

  public void run() {
    while (!skierQueue.isEmpty()) {
      String[] currPair = skierQueue.poll().split(",");
      String skierId = currPair[0];
      String liftNum = currPair[1];
      skierVertical.put(skierId,skierVertical.getOrDefault(skierId,0) + getVertical(liftNum)); //update total vertical of the skier
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

    readWriteCsv.printStringToCsv(skierSb.toString(),"concurrent results/skier.csv");
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

  //refactor!!!!!
}
