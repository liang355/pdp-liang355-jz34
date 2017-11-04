package edu.neu.ccs.cs5010;

import java.util.List;
import java.util.Map;

public class RecommendationSystem {
  private Map<Integer,User> userMap;

  public static void main(String[] args) {
    InputParser inputParser = new InputParser(args);
    inputParser.checkArgs();
    //System.out.println(inputParser.getNumberOfRecommendations());  //for debug
  }
}
