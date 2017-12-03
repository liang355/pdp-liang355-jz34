package edu.neu.ccs.cs5010;

/**
 * ClientMessageParser checks whether the client message complies with given protocol.
 */
public class ClientMessageParser {
  //public static final String[] VALID_FRAME =
      //new String[]{"KEEP_DICE:","SCORE_CHOICE:","ACK:","PRINT_GAME_STATE:"};

  public boolean isValidMessage(String message, String serverFlag) {
    message = message.trim();
    if (!serverFlag.equals("")) {
      if (serverFlag.equals("CHOOSE_DICE")) {
        return message.contains("KEEP_DICE");
      }

      if (serverFlag.equals("CHOOSE_SCORE")) {
        return message.contains("SCORE_CHOICE");
      }
    }
//    for (String str : VALID_FRAME) {
//      if (message.contains(str)) {
//        return true;
//      }
//    }
    return true;
  }
}
