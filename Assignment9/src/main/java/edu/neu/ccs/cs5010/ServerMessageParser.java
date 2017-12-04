package edu.neu.ccs.cs5010;

import java.util.Map;

/**
 * ServerMessageParser generates the appropriate message to clients
 * based on the frame sent from server and also prints the score board.
 */
public class ServerMessageParser {

  private static final String[] KEYS = new String[]
      {"Aces", "Twos", "Threes", "Fours", "Fives", "Sixes", "Total",
          "ThreeOfKind", "FourOfKind", "FullHouse", "SmallStraight",
          "LargeStraight", "Yahtzee", "Chance"};

  /**
   * printScores prints a well-formatted score board.
   * @param scoreMap score map with Key:scoreId to Value:score.
   */
  private void printScores(Map<String, String> scoreMap) {
    System.out.println("\nYOUR CURRENT SCORES:");

    System.out.printf("%-30.30s  %-30.30s%n", "Upper Section", "Lower Section");
    // the separation line
    String separation = "----------------------";
    System.out.printf("%-30.30s  %-30.30s%n", separation, separation);

    int n = KEYS.length;
    for(int i = 0, j = n/2; j < n; i++, j++) {
      String value;
      String leftColumn;
      String rightColumn;

      // left column
      value = scoreMap.get(KEYS[i]);
      value = value.equals("-1") ? "" : value;
      leftColumn = "| " + String.format("%-13s", KEYS[i]) + " | " + String.format("%-3s", value) + "|";

      // right column
      value = scoreMap.get(KEYS[j]);
      value = value.equals("-1") ? "" : value;
      rightColumn = "| " + String.format("%-13s", KEYS[j]) + " | " + String.format("%-3s", value) + "|";

      System.out.printf("%-30.30s  %-30.30s%n", leftColumn, rightColumn);
      System.out.printf("%-30.30s  %-30.30s%n", separation, separation);
    }
    System.out.println();
  }

  /**
   * parse generates appropriate message to client based on the server frame.
   * @param serverFrame server Frame.
   * @param scoreMap score map with Key:scoreId to Value:score.
   * @return String message.
   */
  public String parse(Frame serverFrame, Map<String, String> scoreMap) {
    String tag = serverFrame.getTag();
    String message = serverFrame.getPayload();
    message = message.trim();
    if (message.equals("Joining the game.")) {
      return "SERVER: Welcome to dice-rolling game Yahtzee!\n" +
          "ACTION: Press \"Enter\" to continue.";
    }

    if (message.equals("Waiting for more players to join.")) {
      return "SERVER: Server is waiting for another player to join.\n" +
          "ACTION: Press \"Enter\" to continue.";
    }

    if (message.equals("Game Started. Waiting for my turn.")) {
      return "SERVER: Another player joined! Please wait for you turn to play\n" +
          "ACTION: Press \"Enter\" to continue.";
    }

    if (tag.equals("START_ROUND")) {
      return "*********************** Round " + message + //get the round #
          " ***********************"+ "\n" + "ACTION: Press \"Enter\" to continue.";
    }

    if (tag.equals("START_TURN")) {
      return "SERVER: Now it's your turn to play\n" +
          "ACTION: Press \"Enter\" to get your first rolled dice";
    }

    if (tag.equals("CHOOSE_DICE")) {
      return "SERVER: Your rolled dice: " + message + "\n" +
          "ACTION: Select the dice you want to re-roll or keep:\n" +
          "        Use 1 to keep the dice number and 0 to re-roll the dice.\n" +
          "        For Example: \"1 1 0 0 1\" means you want to keep the 1st " +
          "2nd 5th dice, and re-roll third fourth dice.";
    }

    if (tag.equals("CHOOSE_SCORE")) {
      return "SERVER: Your rolled dice: " + message.substring(0, 10) + "\n" +
              "ACTION: Select your score choice from: " + message.substring(10);
    }

    if (tag.equals("SCORE_CHOICE_VALID")) {
      printScores(scoreMap);
      return "SERVER: Scoring phase finished.\n" + "ACTION: Press \"Enter\" to continue.";
    }

    if (tag.equals("SCORE_CHOICE_INVALID")) {
      return "SERVER: Invalid score choice!\n" + "ACTION: Please choose a valid score ID from: "
              + message.substring(10) + "based on score board";
    }

    if (tag.equals("TURN_OVER")) {
      return "SERVER: Your turn is over.\n" + "ACTION: Press \"Enter\" to continue.";
    }

    if (tag.equals("ROUND_OVER")) {
      return "Current Score: " + scoreMap.get("Total");
    }

    if (tag.equals("GAME_OVER")) {
      System.out.println(serverFrame.toString());
      System.out.println("Total Score: " + scoreMap.get("Total"));
      return "";
    }
    return message;
  }
}
