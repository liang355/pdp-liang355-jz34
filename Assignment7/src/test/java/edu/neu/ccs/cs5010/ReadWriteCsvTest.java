package edu.neu.ccs.cs5010;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

public class ReadWriteCsvTest {
    private static ReadWriteCsv readWriteCsv;

    @BeforeClass
    public static void setUp() throws Exception {
        readWriteCsv = new ReadWriteCsv();
    }

    @Test
    public void readForSequential() throws Exception {
        readWriteCsv.readForSequential("TestRead.csv");
        String[] expected = new String[] {"resort","day","skier","lift","time"};
        String[] actual = readWriteCsv.getAllRows().get(0);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void readForConcurrent() throws Exception {
        readWriteCsv.readForConcurrent("TestRead.csv");
        assertEquals("5,7", readWriteCsv.getSkierQueue().poll());
        assertEquals("6,8", readWriteCsv.getSkierQueue().poll());
        assertEquals("7", readWriteCsv.getLiftQueue().poll());
        assertEquals("8", readWriteCsv.getLiftQueue().poll());
        assertEquals("1,7", readWriteCsv.getHourQueue().poll());
        assertEquals("1,8", readWriteCsv.getHourQueue().poll());
    }

    @Test
    public void printStringToCsv() throws Exception {
        String s = "Hello World!";
        readWriteCsv.printStringToCsv(s, "TestPrint.csv");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("TestPrint.csv"));
        String expected = "Hello World!";
        String actual = bufferedReader.readLine();
        assertEquals(expected, actual);
    }
}