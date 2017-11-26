package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;

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

    public SkierRecord getRecord(int skierId) throws IOException {
        SkierRecord record = new SkierRecord();
        if (skierId < 1)
            throw new IllegalArgumentException("invalid ID!!");
        file.seek((skierId - 1) * SkierRecord.SIZE);
        record.readFromFile(file);
        return record;
    }

    public void insertRecord(SkierRecord record)
            throws IllegalArgumentException, IOException {

        file.seek((record.getSkierId() - 1) * SkierRecord.SIZE);
        record.writeToFile(file);
    }
}
