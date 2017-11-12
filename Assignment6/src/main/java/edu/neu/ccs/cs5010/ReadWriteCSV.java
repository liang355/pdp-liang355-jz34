package edu.neu.ccs.cs5010;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadWriteCSV reads data from given file, and writes csv file as output.
 */
public class ReadWriteCSV {

  /**
   * Writes the given string into given csv file.
   * @param str string to be written in the output file.
   * @param outputFileName output file name.
   */
  public void printStringToCSV(String str, String outputFileName) {
    try {
      PrintWriter out = new PrintWriter(outputFileName);
      out.print(str);
      out.flush();
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    }
  }

  /**
   * readLinesFromCSV reads the data from the given file name and returns a the data in list format.
   * @param CSVFileName file source name
   * @return list representation of the csv file content.
   */
  public List<String> readLinesFromCSV(String CSVFileName) {
    String CSVFilePathName = CSVFileName;
    List<String> lines = new ArrayList<>();
    BufferedReader inputFile = null;
    try {
      inputFile = new BufferedReader(new FileReader(CSVFilePathName));
      String line;
      inputFile.readLine(); // skip first line
      while ((line = inputFile.readLine()) != null) {
        lines.add(line);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    } finally {
      if (inputFile != null) {
        try {
          inputFile.close();
        } catch (IOException e) {
          System.out.println("Failed to close input stream in finally block");
          e.printStackTrace();
        }
      }
    }
    return lines;
  }
}
