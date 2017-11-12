package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ReadWriteCsvTest {
    private ReadWriteCsv readWriteCsv;

    @Before
    public void setUp() throws Exception {
        readWriteCsv = new ReadWriteCsv();
    }

    @Test
    public void printStringToCsvException() throws Exception {
        readWriteCsv = new ReadWriteCsv();
        readWriteCsv.printStringToCsv(" ", "noDirectory/noFile.csv");
        assertTrue(readWriteCsv.isFileNotFoundExceptionCaught());
    }

    @Test
    public void printStringToCsv() throws Exception {
        readWriteCsv = new ReadWriteCsv();
        readWriteCsv.printStringToCsv("none\nHello World", "test.csv");
        String expected = "Hello World";
        String actual = readWriteCsv.readLinesFromCsv("test.csv").get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void readLinesFromCsvException() throws Exception {
        // test FileNotFoundException
        readWriteCsv = new ReadWriteCsv();
        readWriteCsv.readLinesFromCsv("no.csv");
        assertTrue(readWriteCsv.isFileNotFoundExceptionCaught());
    }

    @Test
    public void readLinesFromCsv() throws Exception {
        readWriteCsv = new ReadWriteCsv();
        List<String> expected = Arrays.asList("1,39", "1,79", "1,80");
        List<String> actual = readWriteCsv.readLinesFromCsv("edges_small.csv").subList(0, 3);
        assertEquals(expected, actual);
    }

}