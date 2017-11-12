package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RecommendationSystemTest {
    private ReadWriteCsv readWriteCsv;

    @Before
    public void setUp() throws Exception {
        readWriteCsv = new ReadWriteCsv();
    }

    @Test
    public void main() throws Exception {
        RecommendationSystem.main(new String[] {"nodes_small.csv", "edges_small.csv", "output.csv", "s", "100", "15"});
        RecommendationSystem.main(new String[] {"nodes_small.csv", "edges_small.csv", "output.csv", "e", "100", "15"});
        RecommendationSystem.main(new String[] {"nodes_small.csv", "edges_small.csv", "output.csv", "r", "100", "15"});
    }

    @Test
    public void getRecommendationsForUsers() throws Exception {
    }

}