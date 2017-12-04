package edu.neu.ccs.cs5010;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Junit test for Frame.
 */
public class FrameTest {
  private Frame test = new Frame("tag","payload");
  @Test
  public void testGetterAndSetter() throws Exception {
    assertEquals(test.getTag(),"tag");
    assertEquals(test.getPayload(),"payload");
    test.setTag("newTag");
    test.setPayload("newPayload");
    assertEquals(test.getTag(),"newTag");
    assertEquals(test.getPayload(),"newPayload");
  }

  @Test
  public void testToString() throws Exception {
    assertEquals(test.toString(),"tag:payload");
  }

}