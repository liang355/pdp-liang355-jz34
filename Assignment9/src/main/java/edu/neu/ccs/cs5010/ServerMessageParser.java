package edu.neu.ccs.cs5010;

import java.util.Arrays;
import java.util.Map;

public class ServerMessageParser {

  private String[] keys = new String[] {"Aces", "Twos", "Threes", "Fours", "Fives", "Sixes", "Total",
          "ThreeOfKind", "FourOfKind", "FullHouse", "SmallStraight", "LargeStraight", "Yahtzee", "Chance"};

  private void printScores(Map<String, String> scoreMap) {
    System.out.println("\nYOUR CURRENT SCORES:");

    System.out.printf("%-30.30s  %-30.30s%n", "Upper Section", "Lower Section");
    // the separation line
    String separation = "----------------------";
    System.out.printf("%-30.30s  %-30.30s%n", separation, separation);

    int n = keys.length;
    for(int i = 0, j = n/2; j < n; i++, j++) {
      String value;
      String leftColumn;
      String rightColumn;

      // left column
      value = scoreMap.get(keys[i]);
      value = value.equals("-1") ? "" : value;
      leftColumn = "| " + String.format("%-13s", keys[i]) + " | " + String.format("%-3s", value) + "|";

      // right column
      value = scoreMap.get(keys[j]);
      value = value.equals("-1") ? "" : value;
      rightColumn = "| " + String.format("%-13s", keys[j]) + " | " + String.format("%-3s", value) + "|";

      System.out.printf("%-30.30s  %-30.30s%n", leftColumn, rightColumn);
      System.out.printf("%-30.30s  %-30.30s%n", separation, separation);
    }
    System.out.println();
  }

  public String parse(Frame serverFrame, Map<String, String> scoreMap) {
    String tag = serverFrame.getTag();
    String message = serverFrame.getMessage();
    message = message.trim();
    if (message.equals("Joining the game.")) {
      return "Welcome to dice-rolling game Yahtzee!\n" +
          "Press \"Enter\" to continue.";
    }

    if (message.equals("Waiting for more players to join.")) {
      return "Server is waiting for another player to join.\n" +
          "Press \"Enter\" to continue.";
    }

    if (message.equals("Game Started. Waiting for my turn.")) {
      return "Another player joined! Please wait for you turn to play\n" +
          "Press \"Enter\" to continue.";
    }

    if (tag.equals("START_ROUND")) {
      return "********** Round" + message.substring(message.length() - 1) + //get the round #
          " **********"+ "\n" + "Press \"Enter\" to continue.";
    }

    if (tag.equals("START_TURN")) {
      return "Now it's your turn to play\n" +
          "Press \"Enter\" to get your first rolled dice";
    }

    if (tag.equals("CHOOSE_DICE")) {
      return "Your rolled dice: " + message + "\n" +
          "Select the dice you want to re-roll or keep.";
    }

    if (tag.equals("CHOOSE_SCORE")) {
      return "Your rolled dice: " + message.substring(0, 10) + "\n" +
              "Select your score choice from: " + message.substring(10); //extract all choices
    }

    if (tag.equals("SCORE_CHOICE_VALID")) {
      printScores(scoreMap);
      return "Scoring phase finished.\n" + "Press \"Enter\" to continue.";
    }

    if (tag.equals("SCORE_CHOICE_INVALID")) {
      return "Invalid score choice! Please choose a valid score ID from: " + message.substring(10) + "based on score board";
    }

    if (tag.equals("TURN_OVER")) {
      return "Your turn is over.\n" + "Press \"Enter\" to continue.";
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
