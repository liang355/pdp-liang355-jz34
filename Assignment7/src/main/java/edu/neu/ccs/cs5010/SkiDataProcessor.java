package edu.neu.ccs.cs5010;

public class SkiDataProcessor {
  private SequentialSolution singleThread = new SequentialSolution();
  private ConcurrentSolution multiThread = new ConcurrentSolution();

  /**
   * Run sequential solution and concurrent solution.
   * @param args no command line arguments
   */
  public static void main(String[] args) {
    SkiDataProcessor processor = new SkiDataProcessor();
    processor.singleThread.runSequential();
    processor.multiThread.runConcurrent();
  }
}
