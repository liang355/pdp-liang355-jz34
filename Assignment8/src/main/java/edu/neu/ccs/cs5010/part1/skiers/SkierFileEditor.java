package edu.neu.ccs.cs5010.part1.skiers;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile editor for skier data.
 */
public class SkierFileEditor {
  RandomAccessFile file;

  public SkierFileEditor(String fileString)
      throws IOException {
    file = new RandomAccessFile(fileString, "rw");
  }

  public void close() throws IOException {
    if (file != null)
      file.close();
  }

  /**
   * Get a record from the file.
   * @param skierId given skierId
   * @return skierRecord
   * @throws IOException
   */
  public SkierRecord getRecord(int skierId) throws IOException {
    SkierRecord record = new SkierRecord();
    if (skierId < 1)
      throw new IllegalArgumentException("invalid ID!!");
    file.seek((skierId - 1) * SkierRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  /**
   * Insert a record to the file.
   * @param record to be inserted.
   * @throws IllegalArgumentException
   * @throws IOException
   */
  public void insertRecord(SkierRecord record)
      throws IllegalArgumentException, IOException {

    file.seek((record.getSkierId() - 1) * SkierRecord.SIZE);
    record.writeToFile(file);
  }
}
