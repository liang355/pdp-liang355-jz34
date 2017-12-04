package edu.neu.ccs.cs5010;

public class ServerMessageParser {


  public String parse(Frame serverFrame, int currentScore) {
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
      return "Select your score choice from: " + message.substring(10); //extract all choices
    }

    if (tag.equals("SCORE_CHOICE_VALID")) {
      return "Scoring phase finished.\n" + "Press \"Enter\" to continue.";
    }

    if (tag.equals("SCORE_CHOICE_INVALID")) {
      return "Invalid score choice! Please choose a valid score ID from: " + message.substring(10) + "based on score board";
    }

    if (tag.equals("ROUND_OVER")) {
      return "Current Score: " + currentScore;
    }

    return message;

  }
}
