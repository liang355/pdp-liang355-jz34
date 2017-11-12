package edu.neu.ccs.cs5010;

public interface IRecommendationSystem {
    void getRecommendationsForUsers(char flag, int numberOfUsersToProcess,
                                    int numberOfRecommendations, String outputFileName);
}
