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
  // CONSTANTS:
  private static final int MINUTES_IN_A_HOUR = 60;

  //for sequential solution
  private List<String[]> allRows;

  //for concurrency solution
  private Queue<String> skierQueue = new LinkedList<>(); // KV pair: <skierID,liftID>
  private Queue<String> liftQueue = new LinkedList<>(); // <liftID>
  private Queue<String> hourQueue = new LinkedList<>(); //<hour number(1-6), liftID>

  /**
   * read CSV for sequential solution.
   */
  public void readForSequential() {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    allRows = parser.parseAll(new File("PDPAssignment.csv"));
  }

  /**
   * read CSV for concurrent solution.
   */
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
      int hour = minute / MINUTES_IN_A_HOUR + (minute % MINUTES_IN_A_HOUR == 0 ? 0 : 1);

      skierQueue.add(skierId + "," + liftId);
      liftQueue.add(liftId);
      hourQueue.add(hour + "," + liftId);
    }
  }

  /**
   * print output file string to specified output file.
   * @param str string to write to file.
   * @param outputFileName the name of the output file.
   */
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
