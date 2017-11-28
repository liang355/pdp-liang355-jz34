package edu.neu.ccs.cs5010;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SkierFileEditor {
    private RandomAccessFile file;
    private ReadWriteLock rwlock = new ReentrantReadWriteLock();

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
        rwlock.readLock().lock();
        try {
            if (skierId < 1)
                throw new IllegalArgumentException("invalid ID!!");
            file.seek((skierId - 1) * SkierRecord.SIZE);
            record.readFromFile(file);
        } finally {
            rwlock.readLock().unlock();
        }
//        if (skierId < 1)
//            throw new IllegalArgumentException("invalid ID!!");
//        file.seek((skierId - 1) * SkierRecord.SIZE);
//        record.readFromFile(file);
        return record;
    }

    public void insertRecord(SkierRecord record)
            throws IllegalArgumentException, IOException {
        rwlock.writeLock().lock();
        try {
            file.seek((record.getSkierId() - 1) * SkierRecord.SIZE);
            record.writeToFile(file);
        } finally {
            rwlock.writeLock().unlock();
        }
//        file.seek((record.getSkierId() - 1) * SkierRecord.SIZE);
//        record.writeToFile(file);
    }
}
