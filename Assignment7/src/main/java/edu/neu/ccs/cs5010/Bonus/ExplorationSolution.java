package edu.neu.ccs.cs5010.Bonus;

import edu.neu.ccs.cs5010.HourThread;
import edu.neu.ccs.cs5010.LiftThread;
import edu.neu.ccs.cs5010.ReadWriteCsv;
import edu.neu.ccs.cs5010.SkierThread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ExplorationSolution {
    private Queue<String> skierQueue; // KV pair: <skierID,liftID>
    private Queue<String> liftQueue; // <liftID>
    private Queue<String> hourQueue; //<hour number(1-6), liftID>

    public ExplorationSolution() {
        ReadWriteCsv readWriteCsv = new ReadWriteCsv();
        readWriteCsv.readForConcurrent("PDPAssignment.csv");
        skierQueue = readWriteCsv.getSkierQueue();
        liftQueue = readWriteCsv.getLiftQueue();
        hourQueue = readWriteCsv.getHourQueue();
    }

    private List<Queue<String>> partitionQueue(Queue<String> trunkQueue) {
        int partitionSize = 100000;
        List<Queue<String>> smallerQueues = new ArrayList<>();
        List<String> trunkList = new LinkedList<>(trunkQueue);
        for(int i = 0; i < trunkList.size(); i += partitionSize) {
            List<String> curList = trunkList.subList(i,
                    Math.min(i + partitionSize, trunkList.size()));
            smallerQueues.add((Queue<String>) curList);
        }
        return smallerQueues;
    }

    public void runExploration() {
        // partition queues into smaller queues
        List<Queue<String>> smallSkierQueues = partitionQueue(skierQueue);
        List<Queue<String>> smallLiftQueues = partitionQueue(liftQueue);
        List<Queue<String>> smallhourQueues = partitionQueue(hourQueue);

        // run threads here
        long startTime = System.nanoTime();
        for(int i = 0; i < 8; i++) {
            SmallSkierThread runSmallSkier = new SmallSkierThread(smallSkierQueues.get(i));
            SmallLiftThread runSmallLift = new SmallLiftThread(smallLiftQueues.get(i));
            SmallHourThread runSmallHour = new SmallHourThread(smallhourQueues.get(i));
        }
        // -> Aggregate results here
        // ...
        // ...
        // ...
        // <- End aggregation
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Concurrent Solution Runtime: " + duration/1000f + " microseconds");
    }
}
