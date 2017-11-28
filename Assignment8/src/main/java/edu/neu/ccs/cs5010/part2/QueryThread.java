package edu.neu.ccs.cs5010.part2;

import edu.neu.ccs.cs5010.ReadWriteCsv;
import edu.neu.ccs.cs5010.SkierFileEditor;
import edu.neu.ccs.cs5010.SkierRecord;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class QueryThread extends Thread {
    private List<String[]> rows = new LinkedList<>();
    private StringBuilder skierSB = new StringBuilder("");
    private ReadWriteCsv readWriteCsv = new ReadWriteCsv();
    private SkierFileEditor skierFE;
    private int threadId;

    public QueryThread(List<String[]> rows, int threadId, SkierFileEditor skierFE) {
        this.rows = rows;
        this.threadId = threadId;
        this.skierFE = skierFE;
    }

    @Override
    public synchronized void run() {
        try {
            skierFE = new SkierFileEditor("skier.dat");
        } catch (IOException ioe) {
            System.out.println("*** OOPS! Something is wrong when creating SkierFileEditor");
        }
        String outputFileName = "results_" + threadId + ".txt";
        System.out.println(rows.size());
        for(int i = 0; i < rows.size(); i++) {
            String[] currPair = rows.get(i);
            String queryType = currPair[0];
            String queryParam = currPair[1];
            try {
                if(queryType.equals("1")) {
                    // read
//                    System.out.println(queryParam);
                    SkierRecord skierRecord = skierFE.getRecord(Integer.valueOf(queryParam));
                    // update (viewCount++)
//                    skierRecord.setNumberOfViews(skierRecord.getNumberOfViews() + 1);
                    // write
//                    skierFE.insertRecord(skierRecord);
                    skierSB.append(skierRecord.toString()).append("\n");
                }
            } catch (IOException ioe) {
                System.out.println("*** OOPS! Something is wrong when reading file!");
            }
        }
        try {
            skierFE.close();
        } catch (IOException ioe) {
            System.out.println("*** OOPS! Something is wrong when closing file!");
        }

        readWriteCsv.printStringToCsv(skierSB.toString(), outputFileName);
    }
}
