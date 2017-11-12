package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserTest {
  private User test0;


  @Before
  public void setUp() throws Exception {
    test0 = new User("1/1/09","F",22,"Seattle");
    test0.addFollower(0);
    test0.addFollower(666);
    test0.addFollower(777);
    test0.addFollowing(0);
    test0.addFollowing(888);
    test0.addFollowing(999);
  }

  @Test
  public void getDateCreated() throws Exception {
    assertEquals(test0.getDateCreated(),"1/1/09");
  }

  @Test
  public void getFollowerList() throws Exception {
    Set<Integer> temp = new HashSet<>();
    temp.add(666);
    temp.add(777);
    assertEquals(test0.getFollowerList(),temp);
  }

  @Test
  public void getFollowingList() throws Exception {
    Set<Integer> temp = new HashSet<>();
    temp.add(888);
    temp.add(999);
    assertEquals(test0.getFollowingList(),temp);
  }

  @Test
  public void getRecommendedTimes() throws Exception {
    assertEquals(test0.getRecommendedTimes(),0);
  }

  @Test
  public void incrementRecommendedTimes() throws Exception {
    test0.incrementRecommendedTimes();
    assertEquals(test0.getRecommendedTimes(),1);
  }

  @Test
  public void testToString() throws Exception {
    String temp = "1/1/09,F,22,Seattle,[999, 888],[777, 666]";
    assertEquals(test0.toString(),temp);
  }

}