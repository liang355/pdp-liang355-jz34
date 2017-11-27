package edu.neu.ccs.cs5010;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CommonBuilderWriter {
    // CONSTANTS:
    private static final int NUMBER_OF_TOP_SKIERS_BY_VERTICAL = 100;
    private static final int NUMBER_OF_TOP_LIFTS_BY_FREQUENCY = 10;
    private static final int METER_200  = 200;
    private static final int METER_300  = 300;
    private static final int METER_400  = 400;
    private static final int METER_500  = 500;
    private static final int NUMBER_OF_LIFTS  = 40;
    private static final int NUMBER_OF_HOUR = 6;

    // Fields:
    private ReadWriteCsv readWriteCsv = new ReadWriteCsv();

    /**
     * skierBuildWrite sorts the skierIds by their total vertical rise in descending order
     * and write them in designated output file.
     * @param skierVertical Map contains skierId as key and its vertical distance as value.
     * @param fileName output file name.
     */
    public void skierBuildWrite(Map<String,Integer> skierVertical, String fileName) {
        //maxHeap sort the map by value in descending order
        Queue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>(
                (object1, object2) -> object2.getValue() - object1.getValue()
        );

        maxHeap.addAll(skierVertical.entrySet());

        StringBuilder skierSb = new StringBuilder("SkierID,Vertical\n");
        // top 100, check whether there are enough skiers at the same time
        for (int i = 0; i < NUMBER_OF_TOP_SKIERS_BY_VERTICAL && maxHeap.size() != 0; i++) {
            Map.Entry<String,Integer> curr = maxHeap.poll();
            skierSb.append(curr.getKey() + "," + curr.getValue() + "\n");
        }

        readWriteCsv.printStringToCsv(skierSb.toString(),fileName);
    }

    /**
     * Helper method for skierBuildWrite that finds the vertical rise distance of given liftId.
     * @param liftNum liftId.
     * @return vertical rise distance of this liftId.
     */
    public int getVertical(String liftNum) {
        int liftNumInt = Integer.parseInt(liftNum);
        if (liftNumInt < 11) {
            return METER_200;
        } else if (liftNumInt < 21) {
            return METER_300;
        } else if (liftNumInt < 31) {
            return METER_400;
        } else {
            return METER_500;
        }
    }

    /**
     * liftBuildWrite sorts the liftIds by their ride number frequency in descending order
     * and write them in designated output file.
     * @param liftRides map contains liftId and its frequency of ride.
     * @param fileName output file name.
     */
    public void liftBuildWrite(Map<String,Integer> liftRides,String fileName) {
        StringBuilder liftSb = new StringBuilder("LiftID,Number of Rides\n");
        for (int i = 1; i <= NUMBER_OF_LIFTS; i++) { // all 40 lifts
            String liftId = String.valueOf(i);
            liftSb.append(i + "," + liftRides.getOrDefault(liftId,0) + "\n");
        }

        readWriteCsv.printStringToCsv(liftSb.toString(),fileName);
    }

    /**
     * hourBuildWrite finds the top 10 busiest lifts for 6 section of hour.
     * @param hourRides map contains key of the section hour number and value of a map with liftId
     *                  mapping to its frequency.
     * @param fileName output file name.
     */
    public void hourBuildWrite(Map<Integer,Map<String,Integer>> hourRides, String fileName) {
        //maxHeap sort the map by value in descending order
        Queue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>(
                (object1, object2) -> object2.getValue() - object1.getValue()
        );

        StringBuilder hourSb = new StringBuilder();

        for (int i = 1; i <= NUMBER_OF_HOUR; i++) {
            maxHeap.clear();
            hourSb.append("Hour " + i + "\n");
            hourSb.append("LiftID,Number of Rides\n");
            maxHeap.addAll(hourRides.get(i).entrySet());
            for (int j = 0; j < NUMBER_OF_TOP_LIFTS_BY_FREQUENCY && maxHeap.size() != 0; j++) {
                Map.Entry<String,Integer> curr = maxHeap.poll();
                hourSb.append(curr.getKey() + "," + curr.getValue() + "\n");
            }
            hourSb.append("\n");
        }

        readWriteCsv.printStringToCsv(hourSb.toString(),fileName);
    }
}
