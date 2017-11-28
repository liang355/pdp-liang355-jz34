package edu.neu.ccs.cs5010.part1.hours;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile editor for hour data.
 */
public class HourFileEditor {
  RandomAccessFile file;

  public HourFileEditor(String fileString)
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
   * @param hourNum lift id.
   * @return LiftRecord
   * @throws IOException
   */
  public HourRecord getRecord(int hourNum)
      throws IOException {
    HourRecord record = new HourRecord();
    if (hourNum < 1 || hourNum > 6) {
      throw new IllegalArgumentException("invalid hourNum!");
    }
    file.seek((hourNum - 1) * HourRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  /**
   * Insert a record to the file.
   * @param record to be inserted.
   * @throws IllegalArgumentException
   * @throws IOException
   */
  public void insertRecord(HourRecord record)
      throws IllegalArgumentException, IOException {
    file.seek((record.getHourNum() - 1) * HourRecord.SIZE);
    record.writeToFile(file);
  }
}
