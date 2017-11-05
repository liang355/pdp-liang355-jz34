package main.java.edu.neu.ccs.cs5010;

import java.util.*;

public class RecommendationSystem {
    private Map<Integer, User> users = new HashMap<>();
    private ReadWriteCSV readWriteCSV = new ReadWriteCSV();

    private void readNodesFromCSV(String CSVFileName) {
        List<String> lines = this.readWriteCSV.readLinesFromCSV(CSVFileName);
        for (String line : lines) {
            String[] params = line.split(",");
            users.put(Integer.valueOf(params[0]),
                    new User(Integer.valueOf(params[0]), params[1], params[2], Integer.valueOf(params[3]), params[4]));
        }
    }

    private void readEdgesFromCSV(String CSVFileName) {
        List<String> lines = this.readWriteCSV.readLinesFromCSV(CSVFileName);
        for (String line : lines) {
            String[] params = line.split(",");
            int sourceID = Integer.valueOf(params[0]);
            int destinationID = Integer.valueOf(params[1]);
            if(users.containsKey(sourceID) && users.containsKey(destinationID)) {
                users.get(sourceID).addFollowing(destinationID);
                users.get(destinationID).addFollower(sourceID);
            }
        }
    }

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

    private void appendCurLineToPrint(int i, Recommendation recommendation, StringBuilder fsb) {
        Set<Integer> recommendedUsers = recommendation.getRecommendation(i);
        List<String> list = new ArrayList<>();
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

    public void getRecommendationsForUsers(char flag, int numberOfUsersToProcess, int numberOfRecommendations, String outputFileName) {
        Recommendation recommendation = new Recommendation(users, numberOfRecommendations);
        StringBuilder fsb = new StringBuilder("Node ID,Recommended nodes\n");
        if (flag == 's') {
            for (int i = 1; i <= numberOfUsersToProcess; i++) {
                appendCurLineToPrint(i, recommendation, fsb);
            }
        } else if (flag == 'e') {
            int maxKey = Collections.max(users.keySet());
            for (int i = maxKey; i > users.size() - numberOfUsersToProcess; i--) {
                appendCurLineToPrint(i, recommendation, fsb);
            }
        } else if (flag == 'r') {
            List<Integer> list = new ArrayList<>(users.keySet());
            Collections.shuffle(list);
            for (int i = 0; i < numberOfUsersToProcess; i++) {
                appendCurLineToPrint(i, recommendation, fsb);
            }
        }
        String stringToPrint = fsb.toString();
//        System.out.println(stringToPrint);
        readWriteCSV.printStringToCSV(stringToPrint, outputFileName);
        printTopRecommendUserIds();
    }

    public RecommendationSystem(String nodeFile, String edgeFile) {
        readNodesFromCSV(nodeFile);
        readEdgesFromCSV(edgeFile);
    }

    public static void main(String[] args) {
//        InputParser inputParser = new InputParser(args);
//        inputParser.checkArgs();
        RecommendationSystem recommendationSystem = new RecommendationSystem("nodes_small.csv",
                "edges_small.csv");
        recommendationSystem.getRecommendationsForUsers('s', 100, 15,
                "output.csv");
        // 我把我们两个的部分merge了，现在结果能跑出来了，输出文件叫output.csv
        // top 10 most frequently recommended, 目前是在得到所有recommendations之后打印一遍，我在PIAZZA上问了明天看回复
    }
}
