package edu.neu.ccs.cs5010;

import java.util.Arrays;

public class InputParser {
  private String[] args;
  private String nodeFile;
  private String edgeFile;
  private String outputFile;
  private char processingFlag;
  private int numberOfUsersToProcess;
  private int numberOfRecommendations;
  //private boolean validInput;


  public InputParser(String[] arguments) {
    args = Arrays.copyOf(arguments,arguments.length);
//    processingFlag = 'n';
//    numberOfUsersToProcess = -1;
//    numberOfRecommendations = -1;
  }

  public void checkArgs(){
    //when argument number is less than 3
    if (args.length < 3) {
      //validInput = false;
      System.out.println("Error: One or more arguments are missing.");
      return;
    }

    //when three csv files names are not ended with ".csv"
    if (!(args[0].endsWith(".csv") && args[1].endsWith(".csv") && args[2].endsWith(".csv"))){
      //validInput = false;
      System.out.println("Error: Invalid csv file names.");
      return;
    } else {
      nodeFile = args[0];
      edgeFile = args[1];
      outputFile = args[2];
    }

    //try to get first optional argument processingFlag
    try{
      processingFlag = args[3].charAt(0);
    } catch (IndexOutOfBoundsException error){
      processingFlag = 's';
      numberOfUsersToProcess = 100;
      numberOfRecommendations = 15;
      return;
    }

    //try to get second optional argument numberOfUsersToProcess
    try{
      numberOfUsersToProcess = Integer.parseInt(args[4]);
    } catch (IndexOutOfBoundsException error){
      numberOfUsersToProcess = 100;
      numberOfRecommendations = 15;
      return;
    }

    //try to get third optional argument numberOfRecommendations
    try{
      numberOfRecommendations = Integer.parseInt(args[5]);
    } catch (IndexOutOfBoundsException error){
      numberOfRecommendations = 15;
    }
  }

  public String getNodeFile() {
    return nodeFile;
  }

  public String getEdgeFile() {
    return edgeFile;
  }

  public String getOutputFile() {
    return outputFile;
  }

  public char getProcessingFlag() {
    return processingFlag;
  }

  public int getNumberOfUsersToProcess() {
    return numberOfUsersToProcess;
  }

  public int getNumberOfRecommendations() {
    return numberOfRecommendations;
  }

  /*public boolean isValidInput() {
    return validInput;
  }*/
}
