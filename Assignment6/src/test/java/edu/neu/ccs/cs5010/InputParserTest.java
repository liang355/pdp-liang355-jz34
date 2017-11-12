package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputParserTest {
  private InputParser test0;
  private String[] args = new String[]{"nodes_small.csv","edges_small.csv", "output.csv", "s", "100", "15"};

  @Before
  public void setUp() throws Exception {
    test0 = new InputParser(args);
    test0.checkArgs();
  }

  @Test
  public void getNodeFile() throws Exception {
    assertEquals(test0.getNodeFile(),"nodes_small.csv");
  }

  @Test
  public void getEdgeFile() throws Exception {
    assertEquals(test0.getEdgeFile(),"edges_small.csv");
  }

  @Test
  public void getOutputFile() throws Exception {
    assertEquals(test0.getOutputFile(),"output.csv");
  }

  @Test
  public void getProcessingFlag() throws Exception {
    assertEquals(test0.getProcessingFlag(),'s');
  }

  @Test
  public void getNumberOfUsersToProcess() throws Exception {
    assertEquals(test0.getNumberOfUsersToProcess(),100);
  }

  @Test
  public void getNumberOfRecommendations() throws Exception {
    assertEquals(test0.getNumberOfRecommendations(),15);
  }

}