package edu.neu.ccs.cs5010.part1.liftData;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * LiftRideRecord to read/write from/to the random access file.
 */
public class LiftRideRecord extends LiftRide{
  public static final int SIZE = 1024;

  public LiftRideRecord() {
    super();
  }

  public LiftRideRecord(int skierId, String rideInfo) {
    super(skierId, rideInfo);
  }

  /**
   * Reads from given random access file.
   * @param file given file.
   * @throws IOException if something wrong in I/O.
   */
  public void readFromFile(RandomAccessFile file)
      throws IOException {
    int skierId = file.readInt();
    if (skierId < 1) {
      throw new IllegalArgumentException("given skierId does not exist in records!!");
    }
    setSkierId(skierId);
    setRideInfo(readString(file));
  }

  /**
   * Writes to given random access file.
   * @param file given file.
   * @throws IOException if something wrong in I/O.
   */
  public void writeToFile(RandomAccessFile file)
      throws IOException {
    file.writeInt(getSkierId());
    writeString(file,getRideInfo());
  }

  private void writeString(RandomAccessFile file, String str)
      throws IOException {
    StringBuffer buffer = null;
    if (str != null) {
      buffer = new StringBuffer(str);
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
