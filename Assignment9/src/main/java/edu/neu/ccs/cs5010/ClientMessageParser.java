package edu.neu.ccs.cs5010;

/**
 * ClientMessageParser checks whether the client message complies with given protocol.
 */
public class ClientMessageParser {
  //public static final String[] VALID_FRAME =
      //new String[]{"KEEP_DICE:","SCORE_CHOICE:","ACK:","PRINT_GAME_STATE:"};

  public boolean isValidMessage(Frame clientFrame, Frame serverFrame) {
    if (serverFrame.getTag().equals("")) {
      return true;
    }

    if (serverFrame.getTag().equals("CHOOSE_DICE")) {
      clientFrame.setTag("KEEP_DICE");
      String message = clientFrame.getMessage();
      // 在这里判断message是否valid
      // ...
      //
      return clientFrame.getTag().equals("KEEP_DICE");
    }

    if (serverFrame.getTag().equals("CHOOSE_SCORE")) {
      clientFrame.setTag("SCORE_CHOICE");
      String message = clientFrame.getMessage();
      // 在这里判断message是否valid
      // ...
      //
      return clientFrame.getTag().equals("SCORE_CHOICE");
    }

    if (serverFrame.getTag().equals("SCORE_CHOICE_INVALID")) {
      clientFrame.setTag("SCORE_CHOICE");
      String message = clientFrame.getMessage();
      // 在这里判断message是否valid
      // ...
      //
      return clientFrame.getTag().equals("SCORE_CHOICE");
    }
    return true;
  }
}
