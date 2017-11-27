package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;

public class HourRecord extends Hour {
  public static final int SIZE = 2 * 4; ////????????

  public HourRecord() {
    this(0,null);
  }

  public HourRecord(int hourNum, Map<Integer, List<Integer>> hourRides) {
    super(hourNum, hourRides);
  }

  public void readFromFile(RandomAccessFile file)
      throws IOException {
    int hourNum = file.readInt();
    if (hourNum < 1 || hourNum > 6) {
      throw new IllegalArgumentException("given hourNum does not exist in records!!");
    }
    setHourNum(hourNum);
  }


}
