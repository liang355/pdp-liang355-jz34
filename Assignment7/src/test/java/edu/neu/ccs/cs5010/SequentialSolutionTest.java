package edu.neu.ccs.cs5010;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class SequentialSolutionTest {
    @Test
    public void runSequential() throws Exception {
        SequentialSolution singleThread = new SequentialSolution();
        singleThread.runSequential();
        // test output file result
        BufferedReader reader = new BufferedReader(new FileReader("sequential results/hours.csv"));
        String line;
        StringBuilder sb = new StringBuilder("");
        while((line = reader.readLine()) != null && !line.equals("")) {
            sb.append(line).append("\n");
        }
        String actual = sb.toString();
        String expected = "Hour 1\n" +
                "LiftID,Number of Rides\n" +
                "29,3466\n" +
                "34,3466\n" +
                "9,3444\n" +
                "24,3423\n" +
                "33,3423\n" +
                "38,3420\n" +
                "26,3418\n" +
                "39,3408\n" +
                "12,3399\n" +
                "7,3386\n";
        assertEquals(expected, actual);
    }

}