package edu.neu.ccs.cs5010;

import java.util.Set;

public interface IUser {
    void addFollower(int otherUserId);
    void addFollowing(int otherUserId);
    String getDateCreated();
    Set<Integer> getFollowerList();
    Set<Integer> getFollowingList();
    int getRecommendedTimes();
    void incrementRecommendedTimes();
}
