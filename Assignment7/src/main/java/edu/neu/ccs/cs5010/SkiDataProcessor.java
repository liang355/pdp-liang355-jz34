package edu.neu.ccs.cs5010;

//import edu.neu.ccs.cs5010.Bonus.ExplorationSolution;

/**
 * SkiDataProcessor is the starting point of the program that given a large amount of data,
 * finds the skiers with the Top 100 vertical totals in descending order, the ride number
 * of each liftId, and the top 10 busiest lifts for each section of hour both in sequential
 * and concurrent solutions.
 */
public class SkiDataProcessor {
  private SequentialSolution singleThread = new SequentialSolution();
  private ConcurrentSolution multiThread = new ConcurrentSolution();
//  private ExplorationSolution manyThread = new ExplorationSolution(); // Bonus part

  /**
   * Run sequential solution and concurrent solution.
   * @param args no command line arguments
   */
  public static void main(String[] args) {
    SkiDataProcessor processor = new SkiDataProcessor();
    processor.singleThread.runSequential();
    processor.multiThread.runConcurrent();
//    processor.manyThread.runExploration(); // Bonus part
  }
}
