package edu.neu.ccs.cs5010.part2;



import edu.neu.ccs.cs5010.part1.ReadWriteCsv;
import edu.neu.ccs.cs5010.part1.skiers.SkierFileEditor;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class QueryData {
    private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    private List<List<String[]>> chunks = new ArrayList<>();
    private List<String[]> rows;
    private SkierFileEditor skierFE;


    public QueryData(String filename, int numOfQueries) {
        this.readWriteCsv.readForQuery(filename, numOfQueries);
        this.rows = readWriteCsv.getRows();
        int chunkSize = numOfQueries / 20;
        for(int i = 0; i < numOfQueries; i += chunkSize) {
            chunks.add(rows.subList(i, i + chunkSize));
        }

//        System.out.println(chunks.size());
    }

    public void runQueries() {

        for(int i = 0; i < 20; i++) {
            new QueryThread(chunks.get(i), i+1, skierFE).start();

        }
    }

    public static void main(String[] args) {
        // Params:
        String filename = "PDPAssignment8.csv";
        int numOfQueries = 40000;

        QueryData queryData = new QueryData(filename, numOfQueries);
        queryData.runQueries();
    }
}
