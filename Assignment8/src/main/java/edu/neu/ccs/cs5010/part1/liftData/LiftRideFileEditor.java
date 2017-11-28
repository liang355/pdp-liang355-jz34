package edu.neu.ccs.cs5010.part1.liftData;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile editor for liftRide data.
 */
public class LiftRideFileEditor {
  RandomAccessFile file;

  public LiftRideFileEditor(String fileString)
      throws IOException {
    file = new RandomAccessFile(fileString, "rw");
  }

  /**
   * close the file.
   * @throws IOException
   */
  public void close() throws IOException {
    if (file != null) {
      file.close();
    }
  }

  /**
   * Get a record from the file.
   * @param skierId lift id.
   * @return LiftRideRecord
   * @throws IOException if something wrong in I/O.
   */
  public LiftRideRecord getRecord(int skierId) throws IOException {
    LiftRideRecord record = new LiftRideRecord();
    if (skierId < 1) {
      throw new IllegalArgumentException("invalid ID!!");
    }
    file.seek((skierId - 1) * LiftRideRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  /**
   * Insert a record to the file.
   * @param record to be inserted.
   * @throws IllegalArgumentException if argument is illegal.
   * @throws IOException if something wrong in I/O.
   */
  public void insertRecord(LiftRideRecord record)
      throws IllegalArgumentException, IOException {
    file.seek((record.getSkierId() - 1) * LiftRideRecord.SIZE);
    record.writeToFile(file);
  }
}
