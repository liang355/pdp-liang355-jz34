package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

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

//    private String readString(RandomAccessFile file)
//            throws IOException {
//        char[] s = new char[15];
//        for (int i = 0; i < s.length; i++)
//            s[i] = file.readChar();
//        return new String(s).replace('\0', ' ');
//    }
//
//    private void writeString(RandomAccessFile file, String s)
//            throws IOException {
//        StringBuffer buffer = null;
//        if (s != null)
//            buffer = new StringBuffer(s);
//        else
//            buffer = new StringBuffer(15);
//        buffer.setLength(15);
//        file.writeChars(buffer.toString());
//    }
}
