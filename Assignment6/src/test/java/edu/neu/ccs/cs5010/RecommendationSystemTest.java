package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecommendationSystemTest {
    private RecommendationSystem recommendationSystem;

    @Before
    public void setUp() throws Exception {
        recommendationSystem = new RecommendationSystem("nodes_small.csv", "edges_small.csv");
    }

    @Test
    public void main() throws Exception {
        RecommendationSystem.main(new String[] {"nodes_small.csv", "edges_small.csv", "output.csv", "s", "100", "15"});
        RecommendationSystem.main(new String[] {"nodes_small.csv", "edges_small.csv", "output.csv", "e", "100", "15"});
        RecommendationSystem.main(new String[] {"nodes_small.csv", "edges_small.csv", "output.csv", "r", "100", "15"});
    }

    @Test
    public void getRecommendationsForUsers() throws Exception {
        boolean isIncreasingOrder = true;
        recommendationSystem.getRecommendationsForUsers('s', 100, 15, "output.csv");
        String[] lines = recommendationSystem.getRecommendationString().split("\n");
        int prev = 0;
        for(String line : lines) {
            if(line.equals("Node ID,Recommended nodes")) {
                continue;
            }
            int id = Integer.valueOf(line.split(",")[0]);
            if(id < prev) {
                isIncreasingOrder = false;
            }
            prev = id;
        }
        assertTrue(isIncreasingOrder);
    }

}