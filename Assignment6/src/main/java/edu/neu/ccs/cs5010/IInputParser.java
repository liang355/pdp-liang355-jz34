package edu.neu.ccs.cs5010;

public interface IInputParser {
    void checkArgs();
    String getNodeFile();
    String getEdgeFile();
    String getOutputFile();
    char getProcessingFlag();
    int getNumberOfUsersToProcess();
    int getNumberOfRecommendations();
}
