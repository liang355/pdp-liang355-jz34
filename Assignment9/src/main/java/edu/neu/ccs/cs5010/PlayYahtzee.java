package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class PlayYahtzee { //Client
  private Frame rawLineToFrame(String line) {
    if(!line.contains(":")) {
      return new Frame();
    }
    String[] splitedServerMessage = line.split(":\\s*?");
    String tag = splitedServerMessage[0];
    String message = splitedServerMessage[1];
    return new Frame(tag, message);
  }

  private void runYahtzee(String hostName, int portNumber) {
    ServerMessageParser serverParser = new ServerMessageParser();
    ClientMessageParser clientParser = new ClientMessageParser();

    try (
            Socket ySocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(ySocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(ySocket.getInputStream()))
    ) {
      ySocket.setSoTimeout(3000); // socket SO_TIMEOUT
      BufferedReader stdIn =
              new BufferedReader(new InputStreamReader(System.in));

      while (true) {
//        try {
//          fromServer = in.readLine();
//        } catch (SocketTimeoutException stoe) {
//          fromServer = "server not responding with your request ... Try another one";
//        }

        // waiting for server response
        Frame serverFrame = rawLineToFrame(in.readLine());
        if (serverFrame.getTag().equals("GAME OVER")) {
          System.out.println(serverFrame.toString());
          break;
        }
        System.out.println(serverFrame.toString());
        System.out.println(serverParser.parse(serverFrame)); //update

        // waiting for user input
        Frame clientFrame = rawLineToFrame(stdIn.readLine());
        while (!clientParser.isValidMessage(clientFrame, serverFrame)) {
          System.out.println("Please enter a valid input: ");
          clientFrame = rawLineToFrame(stdIn.readLine());
        }
        out.println(clientFrame.toString());
      }
      ySocket.close();
      System.out.println("socket closed !");

    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1); //?????status
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to " + hostName);
    }
  }

  public static void main(String[] args) throws IOException{
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
