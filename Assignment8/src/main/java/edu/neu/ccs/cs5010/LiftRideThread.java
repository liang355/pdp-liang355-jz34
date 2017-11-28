package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;

public class LiftRideThread extends Thread {

  private Map<Integer, String> skierTimeLift;

  public LiftRideThread(Map<Integer, String> skierTimeLift) {
    this.skierTimeLift = skierTimeLift;
  }

  public void run() {
//    while (!skierLiftTime.isEmpty()) {
//      String[] currTriple = skierLiftTime.poll().split(",");
//      String skierId = currTriple[0];
//      String liftId = currTriple[1];
//      String time = currTriple[2];
//    }
    writeToFile();
  }

  private void writeToFile() {
    try {
      LiftRideFileEditor fe = new LiftRideFileEditor("liftrides.dat");
      for (int key : skierTimeLift.keySet()) {
        fe.insertRecord(new LiftRideRecord(key, skierTimeLift.get(key)));
      }
//      while (!skierTimeLift.isEmpty()) {
//        String[] currTriple = skierLiftTime.poll().split(",");
//        String skierId = currTriple[0];
//        String liftId = currTriple[1];
//        String time = currTriple[2];
//        fe.insertRecord(new LiftRideRecord(Integer.valueOf(skierId), Integer.valueOf(liftId), Integer.valueOf(time)));
//      }
    } catch (IOException ioe) {
      System.out.println("*** OOPS! Something is wrong when writing to file!");
    }
  }
}
