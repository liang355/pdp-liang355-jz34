package main.java.edu.neu.ccs.cs5010;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * User class represents the Twitter user and it's also the node of the social network graph.
 * It has the date created, gender, age, city, be recommended times, follower list as the
 * inward edge and following list as the outward edge in the social network graph.
 */
public class User {
  private String dateCreated;
  private String gender;
  private int age;
  private String city;
  private Set<Integer> followerList = new HashSet<>();
  private Set<Integer> followingList = new HashSet<>();
  private int recommendedTimes = 0;

  /**
   * Constructor with date created, gender, age, and city of the user.
   * @param dateCreated of the account
   * @param gender of the user
   * @param age of the user
   * @param city where the user is located
   */
  public User(String dateCreated, String gender, int age, String city) {
    this.dateCreated = dateCreated;
    this.gender = gender;
    this.age = age;
    this.city = city;
  }

  /**
   * Add the otherUserId to the follower list of this user.
   * @param otherUserId to be added to the follower list.
   */
  public void addFollower(int otherUserId) {
    if (otherUserId != 0) {
      this.followerList.add(otherUserId);
    }

  }

  /**
   * Add the otherUserId to the following list of this user.
   * @param otherUserId to be added to the following list.
   */
  public void addFollowing(int otherUserId) {
    this.followingList.add(otherUserId);
  }

  /**
   * Returns the date created of the user's twitter account.
   * @return the date created of the user's twitter account.
   */
  public String getDateCreated() {
    return dateCreated;
  }

  /**
   * Returns the follower list of this user.
   * @return the follower list of this user.
   */
  public Set<Integer> getFollowerList() {
        return followerList;
    }

  /**
   * Returns the following list of this user.
   * @return the following list of this user.
   */
  public Set<Integer> getFollowingList() {
        return followingList;
    }

  /**
   * Returns the times of being recommended of this user.
   * @return the times of being recommended of this user.
   */
  public int getRecommendedTimes() {
        return recommendedTimes;
    }

  /**
   * Increment the beRecommendedTimes by one.
   */
  public void incrementRecommendedTimes() {
        this.recommendedTimes++;
    }

  /**
   * Represent the user in string.
   * @return string representation of the user.
   */
  @Override
  public String toString() {
    return String.join(",", dateCreated, gender, String.valueOf(age), city,
        Arrays.toString(followerList.toArray()), Arrays.toString(followerList.toArray()));
  }
}
