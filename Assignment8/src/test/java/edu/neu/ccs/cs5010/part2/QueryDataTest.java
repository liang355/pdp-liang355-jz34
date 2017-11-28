package edu.neu.ccs.cs5010.part2;

import org.junit.Test;


public class QueryDataTest {
  private QueryData test = new QueryData("PDPAssignment8.csv",4000);
  private String[] args = new String[]{"PDPAssignment8.csv","4000"};

  @Test
  public void main() throws Exception {
    test.main(args);
  }

}
