package edu.neu.ccs.cs5010.part1.lifts;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile editor for lift data.
 */
public class LiftFileEditor {
  RandomAccessFile file;

  public LiftFileEditor(String fileString)
      throws IOException {
    file = new RandomAccessFile(fileString, "rw");
  }

  public void close() throws IOException {
    if (file != null) {
      file.close();
    }
  }

  /**
   * Get a record from the file.
   * @param liftId lift id.
   * @return LiftRecord
   * @throws IOException
   */
  public LiftRecord getRecord(int liftId) throws IOException {
    LiftRecord record = new LiftRecord();
    if (liftId < 1 || liftId > 40) {
      throw new IllegalArgumentException("invalid ID!");
    }
    file.seek((liftId - 1) * LiftRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  /**
   * Insert a record to the file.
   * @param record to be inserted.
   * @throws IllegalArgumentException
   * @throws IOException
   */
  public void insertRecord(LiftRecord record)
      throws IllegalArgumentException, IOException {
    file.seek((record.getLiftId() - 1) * LiftRecord.SIZE);
    record.writeToFile(file);
  }
}
