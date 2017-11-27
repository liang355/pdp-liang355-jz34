package edu.neu.ccs.cs5010;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hour { //the top 10 most popular lifts for each hour
  private int hourNum;
  private Map<Integer, List<Integer>> hourRides = new HashMap<>();

  public Hour() {
    this(0,null);
  }

  public Hour(int hourNum, Map<Integer, List<Integer>> hourRides) {
    this.hourNum = hourNum;
    this.hourRides = hourRides;
  }

  public int getHourNum() {
    return hourNum;
  }

  public void setHourNum(int hourNum) {
    this.hourNum = hourNum;
  }

  public Map<Integer, List<Integer>> getHourRides() {
    return hourRides;
  }

  public void setHourRides(Map<Integer, List<Integer>> hourRides) {
    this.hourRides = hourRides;
  }

  @Override
  public String toString() {
    String ret =  "[" + hourNum + "]\n";
    for (int key : hourRides.keySet()) {
      ret += "[" + key + "," + hourRides.get(key) + "]\n";
    }
    return ret;
  }
}
