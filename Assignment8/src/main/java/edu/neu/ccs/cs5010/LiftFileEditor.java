package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

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

  public LiftRecord getRecord(int liftId) throws IOException {
    LiftRecord record = new LiftRecord();
    if (liftId < 1 || liftId > 40) {
      throw new IllegalArgumentException("invalid ID!");
    }
    file.seek((liftId - 1) * LiftRecord.SIZE);
    record.readFromFile(file);
    return record;
  }

  public void insertRecord(LiftRecord record)
      throws IllegalArgumentException, IOException {
    file.seek((record.getLiftId() - 1) * LiftRecord.SIZE);
    record.writeToFile(file);
  }
}
