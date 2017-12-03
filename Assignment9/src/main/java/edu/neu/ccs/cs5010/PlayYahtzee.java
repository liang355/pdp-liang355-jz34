package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class PlayYahtzee { //Client



  public static void main(String[] args) throws IOException{
    ServerMessageParser parser = new ServerMessageParser();
    //public void runServer(String[] args) throws IOException{

    if (args.length != 2) {
      System.err.println("Usage: java YahtzeeClient <host name> <port number>");
      System.exit(1);
    }

    String hostName = args[0];
    int portNumber = Integer.parseInt(args[1]);

    try(
        Socket ySocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(ySocket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ySocket.getInputStream()))
    ) {
      BufferedReader stdIn =
          new BufferedReader(new InputStreamReader(System.in));
      String fromServer;
      String fromUser;

      while ((fromServer = in.readLine()) != null) {
        System.out.println("完成后删了：*****Server: " + fromServer + "*****"); //完成后删了
        System.out.println(parser.parser(fromServer)); //update
        //if (fromServer.equals("Bye.")) { //*************change later
        //  break; //*************change later
        //}
        fromUser = stdIn.readLine();
        if (fromUser != null) {
          //System.out.println("Your input: " + fromUser);
          out.println(fromUser); //what's this???  send the text to the server???
        }
      }
      ySocket.close();
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1); //?????status
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to " + hostName);
    }
  }
}
