package edu.neu.ccs.cs5010;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ReadWriteCsv {
  private List<String[]> allRows;

  public ReadWriteCsv() {
    CsvParserSettings settings = new CsvParserSettings();
    CsvParser parser = new CsvParser(settings);
    allRows = parser.parseAll(new File("PDPAssignment.csv"));
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

}
