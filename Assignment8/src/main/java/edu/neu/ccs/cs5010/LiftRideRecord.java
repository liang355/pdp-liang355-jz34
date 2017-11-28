package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;

public class LiftRideRecord extends LiftRide{
  public static final int SIZE = 1024;

  public LiftRideRecord() {
    super();
  }

  public LiftRideRecord(int skierId, String rideInfo) {
    super(skierId, rideInfo);
  }

  public void readFromFile(RandomAccessFile file)
      throws IOException {
    int skierId = file.readInt();
    if (skierId < 1) {
      throw new IllegalArgumentException("given skierId does not exist in records!!");
    }
    setSkierId(skierId);
    setRideInfo(readString(file));
  }

  public void writeToFile(RandomAccessFile file)
      throws IOException {
    file.writeInt(getSkierId());
    writeString(file,getRideInfo());
  }

  private void writeString(RandomAccessFile file, String s)
      throws IOException {
    StringBuffer buffer = null;
    if (s != null) {
      buffer = new StringBuffer(s);
    } else {
      buffer = new StringBuffer(256);
    }
    buffer.setLength(256);
    file.writeChars(buffer.toString());
  }

  private String readString(RandomAccessFile file)
      throws IOException {
    char[] s = new char[256];
    for (int i = 0; i < s.length; i++) {
      s[i] = file.readChar();
    }
    return new String(s).replace('\0',' ');
  }
}
