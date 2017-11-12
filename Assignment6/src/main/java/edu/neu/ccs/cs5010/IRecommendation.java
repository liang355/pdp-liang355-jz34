package edu.neu.ccs.cs5010;

import java.util.Set;

public interface IRecommendation {
    Set<Integer> getRecommendation(int userId);
}
