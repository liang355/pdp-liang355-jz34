package edu.neu.ccs.cs5010;

/**
 * ClientMessageParser checks whether the client message complies with given protocol.
 */
public class ClientMessageParser {
  //public static final String[] VALID_FRAME =
      //new String[]{"KEEP_DICE:","SCORE_CHOICE:","ACK:","PRINT_GAME_STATE:"};

  public boolean isValidMessage(String message, String serverFlag) {
    message = message.trim();
    for (String str : VALID_FRAME) {
      if (message.contains(str)) {
        return true;
      }
    }
    return false;
  }
}
