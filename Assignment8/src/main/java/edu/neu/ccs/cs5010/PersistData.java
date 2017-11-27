package edu.neu.ccs.cs5010;

public class PersistData {
    private ConcurrentSolution multiThread = new ConcurrentSolution();

    /**
     * Run concurrent solution.
     * @param args no command line arguments
     */
    public static void main(String[] args) {
        PersistData processor = new PersistData();
        processor.multiThread.runConcurrent();
    }
}
