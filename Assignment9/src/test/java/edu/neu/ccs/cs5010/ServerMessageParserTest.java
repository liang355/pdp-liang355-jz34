package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Junit test for ServerMessageParser.
 */
public class ServerMessageParserTest {
  ServerMessageParser test = new ServerMessageParser();

  Frame server0 = new Frame("","Joining the game.");
  Frame server1 = new Frame("","Waiting for more players to join.");
  Frame server2 = new Frame("","Game Started. Waiting for my turn.");
  Frame server3 = new Frame("START_ROUND","1");
  Frame server4 = new Frame("START_TURN","");
  Frame server5 = new Frame("CHOOSE_DICE","2 2 2 2 2");
  Frame server6 = new Frame("CHOOSE_SCORE","123456789011");
  Frame server7 = new Frame("SCORE_CHOICE_VALID","");
  Frame server8 = new Frame("SCORE_CHOICE_INVALID","11111111111");
  Frame server9 = new Frame("TURN_OVER","");
  Frame server10 = new Frame("ROUND_OVER","");
  Frame server11 = new Frame("GAME_OVER","bbb");
  Frame server12 = new Frame("666","666");

  Map<String, String> scoreBoard = new HashMap<>();

  @Before
  public void setUp() throws Exception {
    scoreBoard.put("Aces","1");
    scoreBoard.put("Twos","2");
    scoreBoard.put("Threes","3");
    scoreBoard.put("Fours","4");
    scoreBoard.put("Fives","-1");
    scoreBoard.put("Sixes","6");
    scoreBoard.put("Total","7");
    scoreBoard.put("ThreeOfKind","8");
    scoreBoard.put("FourOfKind","9");
    scoreBoard.put("FullHouse","-1");
    scoreBoard.put("SmallStraight","11");
    scoreBoard.put("LargeStraight","12");
    scoreBoard.put("Yahtzee","13");
    scoreBoard.put("Chance","14");


  }

  @Test
  public void parse() throws Exception {
    assertEquals(test.parse(server0,scoreBoard),"SERVER: Welcome to dice-rolling game Yahtzee!\n"
        + "ACTION: Press \"Enter\" to continue.");
    assertEquals(test.parse(server1,scoreBoard),"SERVER: Server is waiting for another player to join.\n"
        + "ACTION: Press \"Enter\" to continue.");
    assertEquals(test.parse(server2,scoreBoard),"SERVER: Another player joined! Please wait for you turn to play\n"
        + "ACTION: Press \"Enter\" to continue.");
    assertEquals(test.parse(server3,scoreBoard),"*********************** Round 1"
        + " ***********************" + "\n" + "ACTION: Press \"Enter\" to continue.");
    assertEquals(test.parse(server4,scoreBoard),"SERVER: Now it's your turn to play\n"
        + "ACTION: Press \"Enter\" to get your first rolled dice");
    assertEquals(test.parse(server5,scoreBoard),"SERVER: Your rolled dice: 2 2 2 2 2\n"
        + "ACTION: Select the dice you want to re-roll or keep:\n"
        + "        Use 1 to keep the dice number and 0 to re-roll the dice.\n"
        + "        For Example: \"1 1 0 0 1\" means you want to keep the 1st "
        + "2nd 5th dice, and re-roll third fourth dice.");
    assertEquals(test.parse(server6,scoreBoard),"SERVER: Your rolled dice: 1234567890\n"
        + "ACTION: Select your score choice from: 11");
    assertEquals(test.parse(server7,scoreBoard),"SERVER: Scoring phase finished.\n"
        + "ACTION: Press \"Enter\" to continue.");
    assertEquals(test.parse(server8,scoreBoard),"SERVER: Invalid score choice!\n"
        + "ACTION: Please choose a valid score ID from: 1"
        + "based on score board");
    assertEquals(test.parse(server9,scoreBoard),"SERVER: Your turn is over.\n"
        + "ACTION: Press \"Enter\" to continue.");
    assertEquals(test.parse(server10,scoreBoard),"Current Score: 7");
    assertEquals(test.parse(server11,scoreBoard),"");
    assertEquals(test.parse(server12,scoreBoard),"666");


  }

}