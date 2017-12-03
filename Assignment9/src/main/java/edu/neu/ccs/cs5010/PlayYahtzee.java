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

    ServerMessageParser serverParser = new ServerMessageParser();
    ClientMessageParser clientParser = new ClientMessageParser();

    boolean playerOneWin;

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
      ySocket.setSoTimeout(3000); // socket SO_TIMEOUT
      BufferedReader stdIn =
          new BufferedReader(new InputStreamReader(System.in));
      String fromServer;
      String fromUser;

      //while ((fromServer = in.readLine()) != null) {
      //  System.out.println("完成后删了：*****Server: " + fromServer + "*****"); //完成后删了

        /*
        different server flag requires different client frame
        server flag ---------- client frame
        ""          ---------- press "Enter"
        CHOOSE_DICE ---------- KEEP_DICE
        CHOOSE_SCORE---------- SCORE_CHOICE
        */

        //String serverFlag = "";
        //System.out.println(serverParser.parser(fromServer, serverFlag)); //update

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
        String serverFlag = "";
        System.out.println("Server: " + fromServer);
        System.out.println(serverParser.parser(fromServer, serverFlag)); //update

        // waiting for user input
        fromUser = stdIn.readLine();

        while (!clientParser.isValidMessage(fromServer, serverFlag)) {
          System.out.println("Please enter a valid choice: ");
          fromUser = stdIn.readLine();
//          if (fromUser != null) {
//            System.out.println("Your input: " + fromUser);
//            out.println(fromUser);
//          }
        }

        System.out.println("Your input: " + fromUser);
        out.println(fromUser);


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
