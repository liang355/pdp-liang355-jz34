package edu.neu.ccs.cs5010;

public class ServerMessageParser {
  public String parser(String message) {
    message = message.trim();
    if (message.equals("INFO: Joining the game.")) {
      return "Welcome to dice-rolling game Yahtzee!\n" +
          "Press \"Enter\" to continue.";
    }

    if (message.equals("INFO: Waiting for more players to join.")) {
      return "Server is waiting for another player to join.\n" +
          "Press \"Enter\" to continue.";
    }

    if (message.equals("INFO: Game Started. Waiting for my turn.")) {
      return "Another player joined! Please wait for you turn to play\n" +
          "Press \"Enter\" to continue.";
    }

    if (message.contains("START_ROUND:")) {
      return "********** Round" + message.substring(12, message.length()) + //get the round #
          " **********"+ "\n" + "Press \"Enter\" to continue.";
    }

    if (message.contains("START_TURN:")) {
      return "Now it's your turn to play\n" +
          "Press \"Enter\" to get your first rolled dice";
    }

    if (message.contains("CHOOSE_DICE:")) {
      return "Your rolled dice: " + message.substring(12, message.length()) + "\n" +
          "Select the dice you want to re-roll or keep.";
    }


    return message;

  }
}
