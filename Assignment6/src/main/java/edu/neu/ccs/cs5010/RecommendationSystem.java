package main.java.edu.neu.ccs.cs5010;

import java.util.List;
import java.util.Map;

public class RecommendationSystem {
  private Map<Integer,User> userMap;

  public static void main(String[] args) {
    InputParser inputParser = new InputParser(args);
    inputParser.checkArgs();
    //System.out.println(inputParser.getNumberOfRecommendations());  //for debug

    //TODO:等你push了你的 我才能写下面的

    //一个user 的 pq 按照被推荐次数倒序排
    //每次给一个用户推荐完，把被推荐人逐个加入pq，然后打印前十个

  }
}
