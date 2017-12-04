package edu.neu.ccs.cs5010;

import org.junit.Test;
import org.omg.CORBA.FREE_MEM;

import static org.junit.Assert.*;

/**
 * Junit test for ClientMessageParser.
 */
public class ClientMessageParserTest {
  private Frame client0 = new Frame();
  private Frame client1 = new Frame("","print game state");
  private Frame client2 = new Frame("","1 1 1 1 1");
  private Frame client3 = new Frame("","1 1");
  private Frame client4 = new Frame("","1 1 1 1 5");
  private Frame client5 = new Frame("","1 1 1 1 -1");
  private Frame client6 = new Frame("","1 e e e e");
  private Frame client7 = new Frame("","Twos");
  private Frame client8 = new Frame("","888");


  private Frame server0 = new Frame();
  private Frame server1 = new Frame("TURN_OVER","");
  private Frame server2 = new Frame("CHOOSE_DICE","");
  private Frame server3 = new Frame("CHOOSE_SCORE","");
  private Frame server4 = new Frame("SCORE_CHOICE_INVALID","");
  private Frame server5 = new Frame("888","");

  private ClientMessageParser test = new ClientMessageParser();


  @Test
  public void isValidMessageTest() throws Exception {
    assertEquals(test.isValidMessage(client1,server0),true);
    assertEquals(test.isValidMessage(client0,server0),true);
    assertEquals(test.isValidMessage(client0,server1),true);
    assertEquals(test.isValidMessage(client2,server2),true);
    assertEquals(test.isValidMessage(client3,server2),false);
    assertEquals(test.isValidMessage(client4,server2),false);
    assertEquals(test.isValidMessage(client5,server2),false);
    assertEquals(test.isValidMessage(client6,server2),false);
    assertEquals(test.isValidMessage(client7,server3),true);
    assertEquals(test.isValidMessage(client7,server4),true);
    assertEquals(test.isValidMessage(client8,server5),true);

  }

}