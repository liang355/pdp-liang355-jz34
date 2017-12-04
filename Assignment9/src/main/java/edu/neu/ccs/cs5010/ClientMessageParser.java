package edu.neu.ccs.cs5010;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClientMessageParser checks whether the client message complies with given protocol.
 */
public class ClientMessageParser {
  // Cases when no input is required from the client sides
  private static final Set<String> HIT_ENTER_CASES = new HashSet<>(
      Arrays.asList("SCORE_CHOICE_VALID", "START_TURN", "START_ROUND", "INFO", "TURN_OVER"));

  /**
   * isValidMessage checks whether the client message complies with given protocol.
   * @param clientFrame client Frame.
   * @param serverFrame server Frame.
   * @return whether user input is valid.
   */
  public boolean isValidMessage(Frame clientFrame, Frame serverFrame) {
    // When client wants to print game state
    if (clientFrame.getPayload().equals("print game state")) {
      clientFrame.setTag("PRINT_GAME_STATE");
      return true;
    }

    // WHen client hit enter (empty input)
    if (serverFrame.getTag().equals("")) {
      return true;
    }

    // When server sends frame includes the cases that no input required from clients
    if (HIT_ENTER_CASES.contains(serverFrame.getTag())) {
      return clientFrame.getPayload().equals("");
    }

    // When server asks clients to choose dice
    if (serverFrame.getTag().equals("CHOOSE_DICE")) {
      clientFrame.setTag("KEEP_DICE");
      String fiveDice = serverFrame.getPayload().trim(); // get five dice number

      /* Check whether Payload(user input) of KEEP_DICE consists of 5 valid integers
      * should only have 5 ints
      * each be either 0 or 1
      * */
      String userInput = clientFrame.getPayload().trim();
      String[] inputArray = userInput.split("\\s+");
      if (inputArray.length != 5) {
        return false;
      }
      for (String str : inputArray) {
        int num;
        try {
          num = Integer.valueOf(str);
        } catch (NumberFormatException e) {
          return false;
        }
        if (num > 1 || num < 0) {
          return false;
        }
      }
      clientFrame.setPayload(fiveDice + " " + userInput);
      return true;
    }

    // When server asks clients to choose the score or re-choose the score.
    if (serverFrame.getTag().equals("CHOOSE_SCORE")
        || serverFrame.getTag().equals("SCORE_CHOICE_INVALID")) {
      clientFrame.setTag("SCORE_CHOICE");
      List<String> list = Arrays.asList("Twos", "Fours", "SmallStraight",
          "FullHouse", "Yahtzee", "Fives", "LargeStraight", "Aces", "Chance",
          "Threes", "FourOfKind", "Sixes", "ThreeOfKind");
      Set<String> set = new HashSet<>(list);
      return set.contains(clientFrame.getPayload());
    }
    return true;
  }
}
