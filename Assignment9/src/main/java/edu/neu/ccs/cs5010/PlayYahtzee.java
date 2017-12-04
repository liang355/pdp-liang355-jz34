package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


/**
 * PlayYahtzee is a client application to be used to connect an
 * already-written server application, implementing the given
 * game protocol.
 */
public class PlayYahtzee {
  // score ID -> current score
  Map<String, String> scoreMap = new HashMap<>();

  /**
   * rawLineToFrame converts the a given line of message
   * into a frame which consists of a tag and a payload.
   * @param line input line message
   * @return a Frame consists of a tag and a payloard
   */
  private Frame rawLineToFrame(String line) {
    //when line is empty or the payload is empty
    if (!line.contains(":") || line.indexOf(":") == line.length() - 1) {
      return new Frame();
    }
    String[] splitedServerMessage = line.split(":\\s*?");
    String tag = splitedServerMessage[0];
    String message = splitedServerMessage[1].trim();
    return new Frame(tag, message);
  }

  /**
   * runYahtzee connects with the server with given hostName and
   * portNumber.
   * @param hostName hostName
   * @param portNumber portNumber
   */
  private void runYahtzee(String hostName, int portNumber) {
    ServerMessageParser serverParser = new ServerMessageParser();
    ClientMessageParser clientParser = new ClientMessageParser();

    try (
            Socket ySocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(ySocket.getOutputStream(),true);
            BufferedReader serverIn = new BufferedReader(
                    new InputStreamReader(ySocket.getInputStream()))
    ) {
      BufferedReader stdIn =
              new BufferedReader(new InputStreamReader(System.in));

      while (true) {
        Frame serverFrame = rawLineToFrame(serverIn.readLine());

        // Get and save current scores
        if (serverFrame.getTag().equals("SCORE_CHOICE_VALID")) {
          String[] brackets = serverFrame.getPayload().split("\\s");
          for (int i = 0; i < 14; i++) {
            scoreMap.put(brackets[2 * i], brackets[2 * i + 1]);
          }
        }

        // parse the serverFrame
        System.out.println(serverParser.parse(serverFrame, scoreMap));

        //Terminate the game then servers sends "GAME_OVER" tag
        if (serverFrame.getTag().equals("GAME_OVER")) {
          break;
        }

        // waiting for user input
        String userInput = stdIn.readLine();
        Frame clientFrame = new Frame("", userInput);

        // check whether user input complies to the protocol
        while (!clientParser.isValidMessage(clientFrame, serverFrame)) {
          System.out.println("Please enter a valid input: ");
          userInput = stdIn.readLine();
          clientFrame = new Frame("", userInput);
        }
        out.println(clientFrame.toString());
      }
      ySocket.close();
      //System.out.println("socket closed !");

    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1); //?????status
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to " + hostName);
    }
  }

  /**
   * Starting point of the client application.
   * @param args command-line arguments
   * @throws IOException if I/O error happens
   */
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.err.println("Usage: java YahtzeeClient <host name> <port number>");
      System.exit(1);
    }
    String hostName = args[0];
    int portNumber = Integer.parseInt(args[1]);

    PlayYahtzee game = new PlayYahtzee();
    game.runYahtzee(hostName, portNumber);
  }
}
