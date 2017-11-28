package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HourRecord extends Hour {
  public static final int SIZE = 11 * Integer.BYTES; ////????????

  public HourRecord() {
    this(0,null);
  }

  public HourRecord(int hourNum, List<Integer> liftIdList) {
    super(hourNum, liftIdList);
  }

  public void readFromFile(RandomAccessFile file)
      throws IOException {
    int hourNum = file.readInt();
    if (hourNum < 1 || hourNum > 6) {
      throw new IllegalArgumentException("given hourNum does not exist in records!!");
    }
    setHourNum(hourNum);
    setLiftIdList(readList(file));
  }

  public void writeToFile(RandomAccessFile file)
      throws IOException {
    file.writeInt(getHourNum());
    for (int liftId : getLiftIdList()) {
      file.writeInt(liftId);
    }
  }

  private List readList(RandomAccessFile file)
      throws IOException {
    List<Integer> res = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      res.add(file.readInt());
    }
    return res;
  }


}
