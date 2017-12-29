package edu.neu.ccs.cs5010;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.net.Socket;
import static org.junit.Assert.*;

public class PlayYahtzeeTest {
  //PlayYahtzee test = new PlayYahtzee();
  String[] args = new String[]{"localhost","1200"};

  @Test
  public void main() throws Exception {
    //byte[] emptyPayload = new byte[1001];

//    final Socket socket = mock(Socket.class);
//    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//    when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);

    PlayYahtzee test = new PlayYahtzee();
    test.main(args);

//    Mockito.when(mockGameService.getAllChoices()).thenReturn(expected); //mock the service here with user defined expected result
//    assertEquals(expected, gameController.getAllChoices());
//    Mockito.verify(mockGameService, Mockito.times(1)).getAllChoices(); //to verify wanted number of invocations of a function
  }

}