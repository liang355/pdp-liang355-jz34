package main.java.edu.neu.ccs.cs5010;

import java.util.*;

/**
 * A recommendation system for the Twitter social network.
 */
public class RecommendationSystem {
  private Map<Integer, User> users = new HashMap<>();
  private ReadWriteCSV readWriteCSV = new ReadWriteCSV();

  /**
   * Constructor with node file name and edge file name.
   * @param nodeFile node file name.
   * @param edgeFile edge file name.
   */
  public RecommendationSystem(String nodeFile, String edgeFile) {
    readNodesFromCSV(nodeFile);
    readEdgesFromCSV(edgeFile);
  }

  /**
   * Main method to interact with different classes and methods.
   * @param args input arguments.
   */
  public static void main(String[] args) {
    /* ****** 晚点用这个，在edit configuration里改input arguments ******
    InputParser inputParser = new InputParser(args);
    inputParser.checkArgs();
    RecommendationSystem recommendationSystem = new RecommendationSystem(inputParser.getNodeFile(),
        inputParser.getEdgeFile());
    recommendationSystem.getRecommendationsForUsers(inputParser.getProcessingFlag(),
        inputParser.getNumberOfUsersToProcess(),inputParser.getNumberOfRecommendations(),inputParser.getOutputFile());
     */

    RecommendationSystem recommendationSystem = new RecommendationSystem("nodes_small.csv",
        "edges_small.csv");
    recommendationSystem.getRecommendationsForUsers('s', 100, 15,
        "output.csv");
  }

  /**
   * Extracts the user information from the given node file and encodes the data into a map.
   * Every user is a node in the social network。
   * @param CSVFileName node file name.
   */
  private void readNodesFromCSV(String CSVFileName) {
    List<String> lines = this.readWriteCSV.readLinesFromCSV(CSVFileName);
    for (String line : lines) {
      String[] params = line.split(",");
      users.put(Integer.valueOf(params[0]),
          new User(params[1], params[2], Integer.valueOf(params[3]), params[4]));
    }
  }

  /**
   * Extracts the user relations from given edge file and builds the edges of the social network graph.
   * Every following and follower is a directed edge to be add into following list and follower list of each user.
   * @param CSVFileName edge file name.
   */
  private void readEdgesFromCSV(String CSVFileName) {
    List<String> lines = this.readWriteCSV.readLinesFromCSV(CSVFileName);
    for (String line : lines) {
      String[] params = line.split(",");
      int sourceID = Integer.valueOf(params[0]);
      int destinationID = Integer.valueOf(params[1]);
      if(users.containsKey(sourceID) && users.containsKey(destinationID)) { //handle exception such as nodeId "0"
        users.get(sourceID).addFollowing(destinationID);
        users.get(destinationID).addFollower(sourceID);
      }
    }
  }

  /**
   * Prints out the top ten most frequently recommended node IDs after the program execution.
   */
  private void printTopRecommendUserIds() {
    List<Integer> userList = new ArrayList<>(users.keySet());
    Collections.sort(userList, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return users.get(o2).getRecommendedTimes() - users.get(o1).getRecommendedTimes();
      }
    });
    System.out.println("Top ten most frequently recommended userId, frequency");
    for(int i = 0; i < 10; i++) {
      System.out.println(userList.get(i) + ", " + users.get(userList.get(i)).getRecommendedTimes());
    }
  }

  /**
   * appendCurLineToPoint writes every user's recommended users to one line.
   * @param i user ID
   * @param recommendation recommendation instance
   * @param fsb string builder for the final output
   */
  private void appendCurLineToPrint(int i, Recommendation recommendation, StringBuilder fsb) {
    Set<Integer> recommendedUsers = recommendation.getRecommendation(i);
    List<String> list = new ArrayList<>(); //list of recommended users
    for (int userId : recommendedUsers) {
      if(users.containsKey(userId)) {
        users.get(userId).incrementRecommendedTimes();
        list.add(String.valueOf(userId));
      }
    }
    String recommendedUsersString = "[" + String.join(" ", list) + "]";
    String curLine = String.valueOf(i) + "," + recommendedUsersString;
    fsb.append(curLine + "\n");
  }

  /**
   * getRecommendationsForUsers generates recommended users for every user and write the results as a csv file.
   * @param flag processing flag, the order of the process.
   * @param numberOfUsersToProcess total number of the users to be processed.
   * @param numberOfRecommendations number of recommendation for each user.
   * @param outputFileName output file name.
   */
  public void getRecommendationsForUsers(char flag, int numberOfUsersToProcess, int numberOfRecommendations, String outputFileName) {
    Recommendation recommendation = new Recommendation(users, numberOfRecommendations);
    StringBuilder fsb = new StringBuilder("Node ID,Recommended nodes\n");
    if (flag == 's') { //process from beginning
      for (int i = 1; i <= numberOfUsersToProcess; i++) {
        appendCurLineToPrint(i, recommendation, fsb);
      }
    } else if (flag == 'e') { //process from the end
      int maxKey = Collections.max(users.keySet());
      for (int i = maxKey; i > users.size() - numberOfUsersToProcess; i--) {
        appendCurLineToPrint(i, recommendation, fsb);
      }
    } else if (flag == 'r') { //process in random order
      List<Integer> list = new ArrayList<>(users.keySet());
      Collections.shuffle(list);
      for (int i = 1; i <= numberOfUsersToProcess; i++) {
        appendCurLineToPrint(i, recommendation, fsb);
      }
    }
    String stringToPrint = fsb.toString();  //finish build
    //System.out.println(stringToPrint);
    readWriteCSV.printStringToCSV(stringToPrint, outputFileName);
    printTopRecommendUserIds();
  }
}
