package edu.neu.ccs.cs5010;


import java.util.*;

/**
 * A recommendation system for the Twitter social network.
 */
public class RecommendationSystem {
  private Map<Integer, User> users = new HashMap<>();
  private ReadWriteCsv readWriteCsv = new ReadWriteCsv();

  /**
   * Constructor with node file name and edge file name.
   * @param nodeFile node file name.
   * @param edgeFile edge file name.
   */
  public RecommendationSystem(String nodeFile, String edgeFile) {
    readNodesFromCsv(nodeFile);
    readEdgesFromCsv(edgeFile);
  }

  /**
   * Main method to interact with different classes and methods.
   * @param args input arguments.
   */
  public static void main(String[] args) {
    InputParser inputParser = new InputParser(args);
    inputParser.checkArgs();

    RecommendationSystem recommendationSystem = new RecommendationSystem(inputParser.getNodeFile(),
        inputParser.getEdgeFile());
    recommendationSystem.getRecommendationsForUsers(inputParser.getProcessingFlag(),
        inputParser.getNumberOfUsersToProcess(),inputParser.getNumberOfRecommendations(),
            inputParser.getOutputFile());


//    RecommendationSystem recommendationSystem = new RecommendationSystem("nodes_small.csv",
//        "edges_small.csv");
//    recommendationSystem.getRecommendationsForUsers('s', 100, 15,
//        "output.csv");
  }

  /**
   * Extracts the user information from the given node file and encodes the data into a map.
   * Every user is a node in the social network。
   * @param csvFileName node file name.
   */
  private void readNodesFromCsv(String csvFileName) {
    List<String> lines = this.readWriteCsv.readLinesFromCsv(csvFileName);
    for (String line : lines) {
      String[] params = line.split(",");
      users.put(Integer.valueOf(params[0]),
          new User(params[1], params[2], Integer.valueOf(params[3]), params[4]));
    }
  }

  /**
   * Extracts the user relations from given edge file and builds the edges of the social
   * network graph Every following and follower is a directed edge to be add into following
   * list and follower list of each user.
   * @param csvFileName edge file name.
   */
  private void readEdgesFromCsv(String csvFileName) {
    List<String> lines = this.readWriteCsv.readLinesFromCsv(csvFileName);
    for (String line : lines) {
      String[] params = line.split(",");
      int sourceId = Integer.valueOf(params[0]);
      int destinationId = Integer.valueOf(params[1]);
      if (users.containsKey(sourceId) && users.containsKey(destinationId)) {
        users.get(sourceId).addFollowing(destinationId);
        users.get(destinationId).addFollower(sourceId);
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
      public int compare(Integer object1, Integer object2) {
        return users.get(object2).getRecommendedTimes() - users.get(object1).getRecommendedTimes();
      }
    });
    System.out.println("Top ten most frequently recommended userId, frequency");
    for (int i = 0; i < 10; i++) {
      System.out.println(userList.get(i) + ", " + users.get(userList.get(i)).getRecommendedTimes());
    }
  }

  /**
   * appendCurLineToPoint writes every user's recommended users to one line.
   * @param curId user ID
   * @param recommendation recommendation instance
   * @param fsb string builder for the final output
   */
  private void appendCurLineToPrint(int curId, Recommendation recommendation, StringBuilder fsb) {
    Set<Integer> recommendedUsers = recommendation.getRecommendation(curId);
    List<String> list = new ArrayList<>(); //list of recommended users
    for (int userId : recommendedUsers) {
      if (users.containsKey(userId)) {
        users.get(userId).incrementRecommendedTimes();
        list.add(String.valueOf(userId));
      }
    }
    String recommendedUsersString = "[" + String.join(" ", list) + "]";
    String curLine = String.valueOf(curId) + "," + recommendedUsersString;
    fsb.append(curLine + "\n");
  }

  /**
   * getRecommendationsForUsers generates recommended users for every user and
   * write the results as a csv file.
   * @param flag processing flag, the order of the process.
   * @param numberOfUsersToProcess total number of the users to be processed.
   * @param numberOfRecommendations number of recommendation for each user.
   * @param outputFileName output file name.
   */
  public void getRecommendationsForUsers(char flag, int numberOfUsersToProcess,
                                         int numberOfRecommendations, String outputFileName) {
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
    readWriteCsv.printStringToCsv(stringToPrint, outputFileName);
    printTopRecommendUserIds();
  }
}
