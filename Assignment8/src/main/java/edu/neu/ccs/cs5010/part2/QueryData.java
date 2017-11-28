package edu.neu.ccs.cs5010.part2;

import edu.neu.ccs.cs5010.ReadWriteCsv;

import java.util.List;

public class QueryData {
    private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    private List<String[]> rows;
    private QueryThread queryThread;

    public QueryData(String filename, int numOfQueries) {
        this.readWriteCsv.readForQuery(filename, numOfQueries);
        this.rows = readWriteCsv.getRows();
    }

    public void runQueries() {
        
        queryThread = new QueryThread(rows, 1);
        queryThread.start();
    }

    public static void main(String[] args) {
        // Params:
        String filename = "PDPAssignment8.csv";
        int numOfQueries = 40000;

        QueryData queryData = new QueryData(filename, numOfQueries);
        queryData.runQueries();
    }
}
