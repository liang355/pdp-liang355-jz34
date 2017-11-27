package edu.neu.ccs.cs5010.tutorial;

import edu.neu.ccs.cs5010.tutorial.PersonRecord;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileEditor {
    RandomAccessFile file;

    public FileEditor(String fileString)
            throws IOException {
        file = new RandomAccessFile(fileString, "rw");
    }

    public void close() throws IOException {
        if (file != null)
            file.close();
    }

    public PersonRecord getRecord(int id) throws IOException {
//        System.out.println(id);
        PersonRecord record = new PersonRecord();
        if (id < 1)
            throw new IllegalArgumentException("invalid ID!!");
        file.seek((id - 1) * PersonRecord.SIZE);
        record.readFromFile(file);
        return record;
    }

    public void insertRecord(PersonRecord record)
            throws IllegalArgumentException, IOException {
//        if (getRecord(record.getId()).getId() != 0)
//            System.out.println("Cannot add new. Record already exists.");
//        else {
//            file.seek((record.getId() - 1) * PersonRecord.SIZE);
//            record.writeToFile(file);
//        }
        file.seek((record.getId() - 1) * PersonRecord.SIZE);
        record.writeToFile(file);
    }

    public void updateRecord(PersonRecord record)
            throws IllegalArgumentException, IOException {
        if (getRecord(record.getId()).getId() == 0)
            System.out.println("Cannot update. Record does not exist.");
        else {
            file.seek((record.getId() - 1) * PersonRecord.SIZE);
            record.writeToFile(file);
        }
    }

    public void deleteRecord(PersonRecord record)
            throws IllegalArgumentException, IOException {
        if (getRecord(record.getId()).getId() == 0)
            System.out.println("Cannot delete. Record does not exist.");
        else {
            file.seek((record.getId() - 1) * PersonRecord.SIZE);
            record = new PersonRecord();
            record.writeToFile(file);
        }
    }

    public void showAllRecords() {
        PersonRecord record = new PersonRecord();
        try {
            file.seek(0);
            while (true) {
                do {
                    record.readFromFile(file);
                } while (record.getId() == 0);
                System.out.println(record.toString());
            }
        } catch (EOFException ex1) {
            return;
        } catch (IOException ex2) {
            System.err.println("error reading file");
        }
    }
}
