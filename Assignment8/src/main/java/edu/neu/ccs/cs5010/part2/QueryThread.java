package edu.neu.ccs.cs5010.part2;

import edu.neu.ccs.cs5010.ReadWriteCsv;
import edu.neu.ccs.cs5010.SkierFileEditor;
import edu.neu.ccs.cs5010.SkierRecord;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class QueryThread extends Thread {
    private Queue<String> queryQueue = new LinkedList<>();
    private StringBuilder skierSB = new StringBuilder("");
    private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    private SkierFileEditor skierFE;
    private int threadId;

    public QueryThread(Queue<String> queryQueue, int threadId) throws IOException{
        this.queryQueue = queryQueue;
        this.threadId = threadId;
        skierFE = new SkierFileEditor("skier.dat");
    }

    @Override
    public void run() {
        String outputFileName = "results_" + threadId + ".txt";
        while(!queryQueue.isEmpty()) {
            String[] currPair = queryQueue.poll().split(",");
            String queryType = currPair[0];
            String queryParam = currPair[1];
            try {
                if(queryType.equals("1")) {
                    SkierRecord skierRecord = skierFE.getRecord(Integer.valueOf(queryParam));
                    skierSB.append(skierRecord.toString()).append("\n");
                }
            } catch (IOException ioe) {
                System.out.println("*** OOPS! Something is wrong when reading file!");
            }
        }
        readWriteCsv.printStringToCsv(skierSB.toString(), outputFileName);
    }
}
