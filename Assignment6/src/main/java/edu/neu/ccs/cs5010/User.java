package main.java.edu.neu.ccs.cs5010;

import java.util.HashSet;
import java.util.Set;

public class User {
  //private int nodeId;
  private String dateCreated;
  private String gender;
  private int age;
  private String city;
  private Set<Integer> followerList = new HashSet<>();
  private Set<Integer> followingList = new HashSet<>();

  //总共被推荐的次数
  private int beRecommendedTimes = 0;

  public User(int nodeID, String dateCreated, String gender, int age, String city) {
    //this.nodeId = nodeID;
    this.dateCreated = dateCreated;
    this.gender = gender;
    this.age = age;
    this.city = city;

  }

  public void addFollower (int otherUserId){
    this.followerList.add(otherUserId);
    //otherUser.followingList.add(this.nodeId);
  }

  public void addFollowing (int otherUserId){
    this.followingList.add(otherUserId);
    //otherUser.followerList.add(this.nodeId);
  }

  public String getDateCreated() {
    return dateCreated;
  }

  public Set<Integer> getFollowerList() {
    return followerList;
  }

  public Set<Integer> getFollowingList() {
    return followingList;
  }

  public Integer getBeRecommendedTimes() {
    return beRecommendedTimes;
  }

  public void setBeRecommendedTimes(int beRecommendedTimes) {
    this.beRecommendedTimes = beRecommendedTimes;
  }
}
