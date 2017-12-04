package edu.neu.ccs.cs5010;

/**
 * ClientMessageParser checks whether the client message complies with given protocol.
 */
public class ClientMessageParser {
  public boolean isValidMessage(Frame clientFrame, Frame serverFrame) {
    if (serverFrame.getTag().equals("")) {
      return true;
    }

    if (serverFrame.getTag().equals("CHOOSE_DICE")) {
      clientFrame.setTag("KEEP_DICE");
      String fiveDice = serverFrame.getMessage().trim(); // get five dice number

      /* Check whether Payload(user input) of KEEP_DICE consists of 5 valid integers
      * should only have 5 ints
      * each be either 0 or 1
      * */
      String userInput = clientFrame.getMessage().trim();
      String[] inputArray = userInput.split("\\s+");
      if (inputArray.length != 5) {
        return false;
      }
      for (String str : inputArray) {
        int num;
        try {
          num = Integer.valueOf(str);
        } catch(NumberFormatException e) {
          return false;
        }
        if (num > 1 || num < 0) {
          return false;
        }
      }

      clientFrame.setMessage(fiveDice + " " + userInput);
      return true;
    }

    if (serverFrame.getTag().equals("CHOOSE_SCORE") || serverFrame.getTag().equals("SCORE_CHOICE_INVALID")) {
      clientFrame.setTag("SCORE_CHOICE");
      // 此处不用判断，server能处理invalid message
      return true;
    }

    return true;
  }
}
