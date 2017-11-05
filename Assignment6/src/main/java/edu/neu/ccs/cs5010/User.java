package main.java.edu.neu.ccs.cs5010;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class User {
    //private int nodeId;
    private String dateCreated;
    private String gender;
    private int age;
    private String city;
    private Set<Integer> followerList;
    private Set<Integer> followingList;
    private int recommendedTimes;

    public User(int nodeID, String dateCreated, String gender, int age, String city) {
        //this.nodeId = nodeID;
        this.dateCreated = dateCreated;
        this.gender = gender;
        this.age = age;
        this.city = city;
        followerList = new HashSet<>();
        followingList = new HashSet<>();
        this.recommendedTimes = 0;
    }

    public void addFollower(int otherUserId) {
        this.followerList.add(otherUserId);
        //otherUser.followingList.add(this.nodeId);
    }

    public void addFollowing(int otherUserId) {
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

    public int getRecommendedTimes() {
        return recommendedTimes;
    }

    public void incrementRecommendedTimes() {
        this.recommendedTimes++;
    }

    @Override
    public String toString() {
        return String.join(",", dateCreated, gender, String.valueOf(age), city,
                Arrays.toString(followerList.toArray()), Arrays.toString(followerList.toArray()));
    }
}
