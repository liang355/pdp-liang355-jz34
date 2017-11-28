package edu.neu.ccs.cs5010.part1;

/**
 * Persists the four data sets.
 */
public class PersistData {
  private ConcurrentSolution multiThread = new ConcurrentSolution();

  /**
   * Run concurrently.
   * @param args no command line arguments
   */
  public static void main(String[] args) {
    PersistData processor = new PersistData();
    processor.multiThread.runConcurrent();
  }
}
