package edu.neu.ccs.cs5010.part2;

import edu.neu.ccs.cs5010.ReadWriteCsv;

import java.util.List;

public class QueryData {
    private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    private List<String[]> allRows;
    private QueryThread runQuery;

    public QueryData(String filename, int numOfQueries) {
        this.readWriteCsv.readForQuery(filename, numOfQueries);
        this.allRows = readWriteCsv.getAllRows();
    }

    public static void main(String[] args) {
        // Params:
        String filename = "PDPAssignment8.csv";
        int numOfQueries = 400;

        QueryData queryData = new QueryData(filename, numOfQueries);
    }
}
