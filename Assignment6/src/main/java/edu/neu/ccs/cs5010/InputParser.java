package main.java.edu.neu.ccs.cs5010;

import java.util.Arrays;

/**
 * InputParser validate the input arguments and extract node file name, edge file name, and output file name.
 * It also extracts processing flag, number of users to process, and number of recommendations if they are given.
 */
public class InputParser {
  private String[] args;
  private String nodeFile;
  private String edgeFile;
  private String outputFile;
  private char processingFlag;
  private int numberOfUsersToProcess;
  private int numberOfRecommendations;

  /**
   * Constructor with arguments
   * @param arguments input arguments
   */
  public InputParser(String[] arguments) {
    args = Arrays.copyOf(arguments, arguments.length);
  }


  /**
   * checkArgs validates node file name, edge file name, and output file name.
   * The default values of processing flag, number of users to process, and number of recommendations
   * are assigned if they are not given in arguments.
   * Appropriate error messages will be generated for invalid arguments.
   */
  public void checkArgs() {
    //when argument number is less than 3
    if (args.length < 3) {
      System.out.println("Error: One or more arguments are missing.");
      return;
    }

    //when three csv files names are not ended with ".csv"
    if (!(args[0].endsWith(".csv") && args[1].endsWith(".csv") && args[2].endsWith(".csv"))) {
      System.out.println("Error: Invalid csv file names.");
      return;
    } else {
      nodeFile = args[0];
      edgeFile = args[1];
      outputFile = args[2];
    }

    //try to get first optional argument processingFlag
    try {
      processingFlag = args[3].charAt(0);
    } catch (IndexOutOfBoundsException error) {
      processingFlag = 's';
      numberOfUsersToProcess = 100;
      numberOfRecommendations = 15;
      return;
    }

    //try to get second optional argument numberOfUsersToProcess
    try {
      numberOfUsersToProcess = Integer.parseInt(args[4]);
    } catch (IndexOutOfBoundsException error) {
      numberOfUsersToProcess = 100;
      numberOfRecommendations = 15;
      return;
    }

    //try to get third optional argument numberOfRecommendations
    try {
      numberOfRecommendations = Integer.parseInt(args[5]);
    } catch (IndexOutOfBoundsException error) {
      numberOfRecommendations = 15;
    }
  }

  /**
   * Returns the node file name.
   * @return the node file name.
   */
  public String getNodeFile() {
        return nodeFile;
    }

  /**
   * Returns the edge file name.
   * @return the edge file name.
   */
  public String getEdgeFile() {
        return edgeFile;
    }

  /**
   * Returns the output file name.
   * @return the output file name.
   */
  public String getOutputFile() {
        return outputFile;
    }

  /**
   * Returns the processing flag.
   * @return the processing flag.
   */
    public char getProcessingFlag() {
        return processingFlag;
    }

  /**
   * Returns the number of users to process.
   * @return the number of users to process.
   */
    public int getNumberOfUsersToProcess() {
        return numberOfUsersToProcess;
    }

  /**
   * Returns the number of recommendations.
   * @return the number of recommendations.
   */
    public int getNumberOfRecommendations() {
        return numberOfRecommendations;
    }



}
