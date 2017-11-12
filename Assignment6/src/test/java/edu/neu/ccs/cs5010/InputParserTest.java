package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputParserTest {
  private InputParser test0;
  private InputParser test1;
  private InputParser test2;
  private InputParser test3;
  private InputParser test4;
  private InputParser test5;


  private String[] args0 = new String[]{"nodes_small.csv","edges_small.csv", "output.csv", "s", "100", "15"};
  private String[] args1 = new String[]{};
  private String[] args2 = new String[]{"a","a","a"};
  private String[] args3 = new String[]{"nodes_small.csv","edges_small.csv", "output.csv"};
  private String[] args4 = new String[]{"nodes_small.csv","edges_small.csv", "output.csv", "s"};
  private String[] args5 = new String[]{"nodes_small.csv","edges_small.csv", "output.csv", "s", "100"};

  @Before
  public void setUp() throws Exception {
    test0 = new InputParser(args0);
    test1 = new InputParser(args1);
    test2 = new InputParser(args2);
    test3 = new InputParser(args3);
    test4 = new InputParser(args4);
    test5 = new InputParser(args5);
    test0.checkArgs();
    test1.checkArgs();
    test2.checkArgs();
    test3.checkArgs();
    test4.checkArgs();
    test5.checkArgs();
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