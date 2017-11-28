package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;

public class LiftRideFileEditor {
  RandomAccessFile file;

  public LiftRideFileEditor(String fileString)
      throws IOException {
    file = new RandomAccessFile(fileString, "rw");
  }

  public void close() throws IOException {
    if (file != null) {
      file.close();
    }
  }

  public LiftRideRecord getRecord(int skierId) throws IOException {
    LiftRideRecord record = new LiftRideRecord();
    if (skierId < 1) {
      throw new IllegalArgumentException("invalid ID!!");
    }
    file.seek((skierId - 1) * LiftRideRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  public void insertRecord(LiftRideRecord record)
      throws IllegalArgumentException, IOException {
    file.seek((record.getSkierId() - 1) * LiftRideRecord.SIZE);
    record.writeToFile(file);
  }
}
