package edu.neu.ccs.cs5010.part1.liftData;

import java.io.IOException;
import java.util.Map;

/**
 * liftThread is a thread to get raw data from csv file.
 */
public class LiftRideThread extends Thread {

  private Map<Integer, String> skierTimeLift; //skierID -> <time:LiftId>

  public LiftRideThread(Map<Integer, String> skierTimeLift) {
    this.skierTimeLift = skierTimeLift;
  }

  public void run() {
    writeToFile();
  }

  private void writeToFile() {
    try {
      LiftRideFileEditor fe = new LiftRideFileEditor("liftrides.dat");
      for (int key : skierTimeLift.keySet()) {
        fe.insertRecord(new LiftRideRecord(key, skierTimeLift.get(key)));
      }
    } catch (IOException ioe) {
      System.out.println("*** OOPS! Something is wrong when writing to file!");
    }
  }
}
