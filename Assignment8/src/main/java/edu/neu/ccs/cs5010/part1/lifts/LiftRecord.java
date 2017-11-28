package edu.neu.ccs.cs5010.part1.lifts;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * LiftRecord to read/write from/to the random access file.
 */
public class LiftRecord extends Lift {
  public static final int SIZE = 2 * 4;

  public LiftRecord() {
    super();
  }

  public LiftRecord(int liftId, int numRides) {
    super(liftId, numRides);
  }

  public void readFromFile(RandomAccessFile file)
      throws IOException {
    int liftId = file.readInt();
    if (liftId < 1 || liftId > 40) {
      throw new IllegalArgumentException("No given liftId found!");
    }
    setLiftId(liftId);
    setNumRides(file.readInt());
  }

  public void writeToFile(RandomAccessFile file)
      throws IOException {
    file.writeInt(getLiftId());
    file.writeInt(getNumRides());
  }
}
