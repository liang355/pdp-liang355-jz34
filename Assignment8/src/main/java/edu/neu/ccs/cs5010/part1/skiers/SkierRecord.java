package edu.neu.ccs.cs5010.part1.skiers;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * SkierRecord to read/write from/to the random access file.
 */
public class SkierRecord extends Skier {
  public static final int SIZE = 4 * 4; // there are 4 integer fields, each is 4 bytes

  public SkierRecord() {
        super();
    }

  public SkierRecord(int skierId, int numRides, int totalVertical, int numberOfViews) {
    super(skierId, numRides, totalVertical, numberOfViews);
  }

  public void readFromFile(RandomAccessFile file)
      throws IOException {
    int skierId = file.readInt();
    if(skierId < 1) {
      throw new IllegalArgumentException("given skierId does not exist in records!!");
    }
    setSkierId(skierId);
    setNumRides(file.readInt());
    setTotalVertical(file.readInt());
    setNumberOfViews(file.readInt());
  }

  public void writeToFile(RandomAccessFile file)
      throws IOException {
    file.writeInt(getSkierId());
    file.writeInt(getNumRides());
    file.writeInt(getTotalVertical());
    file.writeInt(getNumberOfViews());
  }
}
