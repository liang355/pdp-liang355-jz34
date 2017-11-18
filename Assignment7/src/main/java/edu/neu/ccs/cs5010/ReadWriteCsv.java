package edu.neu.ccs.cs5010;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ReadWriteCsv {
  //for sequential solution
  private List<String[]> allRows;

  //for concurrency solution
  private Queue<String> skierQueue = new LinkedList<>(); // KV pair: <skierID,liftID>
  private Queue<String> liftQueue = new LinkedList<>(); // <liftID>
  private Queue<String> hourQueue = new LinkedList<>(); //<hour number(1-6), liftID>

  // main method is only for test the reading time for two solutions
//  public static void main(String[] args) {
//    ReadWriteCsv test = new ReadWriteCsv();
//    long s1 = System.currentTimeMillis();
//    test.readForSequential();
//    long e1 = System.currentTimeMillis();
//    long r1 = e1 - s1;
//    System.out.println(r1);
//
//    long s2 = System.currentTimeMillis();
//    test.readForConcurrent();
//    long e2 = System.currentTimeMillis();
//    long r2 = e2 - s2;
//    System.out.println(r2);
//  }

  public void readForSequential() {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    allRows = parser.parseAll(new File("PDPAssignment.csv"));
  }

  public void readForConcurrent() {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    parser.beginParsing(new File("PDPAssignment.csv"));

    String[] row;
    parser.parseNext(); //skip the header
    while ((row = parser.parseNext()) != null) {
      String skierId = row[2];
      String liftId = row[3];
      int minute = Integer.parseInt(row[4]);
      int hour = minute/60 + (minute%60 == 0 ? 0 : 1);//calculate the hour

      skierQueue.add(skierId + "," + liftId);
      liftQueue.add(liftId);
      hourQueue.add(hour + "," + liftId);
    }
  }

  public void printStringToCsv(String str, String outputFileName) {
    try {
      PrintWriter out = new PrintWriter(outputFileName);
      out.print(str);
      out.flush();
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
    }
  }

  public List<String[]> getAllRows() {
    return allRows;
  }

  public Queue<String> getSkierQueue() {
    return skierQueue;
  }

  public Queue<String> getLiftQueue() {
    return liftQueue;
  }

  public Queue<String> getHourQueue() {
    return hourQueue;
  }
}
