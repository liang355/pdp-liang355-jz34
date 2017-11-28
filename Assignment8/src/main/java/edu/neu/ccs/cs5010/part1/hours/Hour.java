package edu.neu.ccs.cs5010.part1.hours;

import java.util.ArrayList;
import java.util.List;

/**
 * Hour object with its hour number and the top 10 most populat lifts
 * in this hour.
 */
public class Hour {
  private int hourNum;
  private List<Integer> liftIdList = new ArrayList<>();

  public Hour() {
    this(0,null);
  }

  public Hour(int hourNum, List<Integer> liftIdList) {
    this.hourNum = hourNum;
    this.liftIdList = liftIdList;
  }

  public int getHourNum() {
    return hourNum;
  }

  public void setHourNum(int hourNum) {
    this.hourNum = hourNum;
  }

  public List<Integer> getLiftIdList() {
    return liftIdList;
  }

  public void setLiftIdList(List<Integer> liftIdList) {
    this.liftIdList = liftIdList;
  }

  @Override
  public String toString() {
    String ret =  "[" + hourNum + "]\n";
    for (int id : liftIdList) {
      ret += id + "\n";
    }
    return ret;
  }
}
