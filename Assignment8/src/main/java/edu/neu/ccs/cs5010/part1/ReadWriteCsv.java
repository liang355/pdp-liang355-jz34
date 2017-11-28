package edu.neu.ccs.cs5010.part1;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * ReadWriteCsv reads and writes csv files.
 */
public class ReadWriteCsv {
  private static final int MINUTES_IN_A_HOUR = 60;

  private List<String[]> rows;

  //for concurrency
  private Queue<String> skierQueue = new LinkedList<>(); // KV pair: <skierID,liftID>
  private Queue<String> liftQueue = new LinkedList<>(); // <liftID>
  private Queue<String> hourQueue = new LinkedList<>(); //<hour number(1-6), liftID>
  private Map<Integer,String> skierTimeLift = new HashMap<>(); //skierID -> <time:LiftId>

  /**
   * read CSV for the multithreading.
   */
  public void readForConcurrent(String pathname) {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    parser.beginParsing(new File(pathname));
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
      skierTimeLift.put(Integer.valueOf(skierId),
          skierTimeLift.getOrDefault(Integer.valueOf(skierId),"")
              + minute + ":" + liftId + "|");
    }
  }

  /**
   * Reads files for queries.
   * @param pathname path name
   * @param numOfQueries number of queries
   */
  public void readForQuery(String pathname, int numOfQueries) {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    parser.beginParsing(new File(pathname));

    rows = new ArrayList<>();
    String[] row;
    int count = 0;
    while ((row = parser.parseNext()) != null && count < numOfQueries) {
      rows.add(row);
      count++;
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

  /**
   * Getter method of the rows.
   * @return a string array list that contains all the data of the given file.
   */
  public List<String[]> getRows() {
        return rows;
    }

  /**
   * Getter method of the skierQueue.
   * @return a Queue contains KV pair, namely "skierID, liftID";
   */
  public Queue<String> getSkierQueue() {
        return skierQueue;
    }

  /**
   * Getter method of the liftQueue.
   * @return a Queue contains a record for every lift ride.
   */
  public Queue<String> getLiftQueue() {
        return liftQueue;
    }

  /**
   * Getter method of the hourQueue.
   * @return a Queue contains a record for every lift ride in each hour,
   * namely "hour number(1-6), liftID".
   */
  public Queue<String> getHourQueue() {
        return hourQueue;
    }

  /**
   * Getter method of the skierTimeLift map.
   * @return the skierTimeLift queue.
   */
  public Map<Integer, String> getSkierTimeLift() {
    return skierTimeLift;
  }
}
