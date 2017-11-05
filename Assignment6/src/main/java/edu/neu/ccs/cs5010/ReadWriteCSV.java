package main.java.edu.neu.ccs.cs5010;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteCSV {

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

    public List<String> readLinesFromCSV(String CSVFileName) {
        String CSVFilePathName = "Assignment6/" + CSVFileName;
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
