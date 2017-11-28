package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hour { //the top 10 most popular lifts for each hour
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
