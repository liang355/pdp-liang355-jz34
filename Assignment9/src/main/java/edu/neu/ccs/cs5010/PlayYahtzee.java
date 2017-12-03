package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class PlayYahtzee { //Client



  public static void main(String[] args) throws IOException{
    ServerMessageParser parser = new ServerMessageParser();
    boolean playerOneWin;
    //public void runServer(String[] args) throws IOException{

    if (args.length != 2) {
      System.err.println("Usage: java YahtzeeClient <host name> <port number>");
      System.exit(1);
    }

    String hostName = args[0];
    int portNumber = Integer.parseInt(args[1]);

    try (
        Socket ySocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(ySocket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ySocket.getInputStream()))
    ) {
      ySocket.setSoTimeout(3000); // socket timeout
      BufferedReader stdIn =
          new BufferedReader(new InputStreamReader(System.in));
      String fromServer;
      String fromUser;

      while (true) {
        // waiting for server response
        try {
          fromServer = in.readLine();
        } catch (SocketTimeoutException stoe) {
          fromServer = "server not responding with your request ... Try another one";
        }
        // check for game over
        if (fromServer.contains("GAME OVER")) {
          System.out.println(fromServer);
          break;
        }

        // print server message
        System.out.println("Server: " + fromServer);
        System.out.println(parser.parser(fromServer)); //update

        // waiting for user input
        fromUser = stdIn.readLine();
        if (fromUser != null) {
          System.out.println("Your input: " + fromUser);
          out.println(fromUser); //what's this???  send the text to the server???
        }
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
}
