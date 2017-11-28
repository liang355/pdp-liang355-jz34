package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;

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

  public void insertRecord(HourRecord record)
      throws IllegalArgumentException, IOException {
    file.seek((record.getHourNum() - 1) * HourRecord.SIZE);
    record.writeToFile(file);
  }
}
