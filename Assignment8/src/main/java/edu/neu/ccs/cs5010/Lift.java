package edu.neu.ccs.cs5010;

public class Lift {
  private int liftId; // 1-40 lift
  private int numRides;  // total number of lift in this day

  public Lift() {
    this(0, 0);
  }

  public Lift(int liftId, int numRides) {
    this.liftId = liftId;
    this.numRides = numRides;
  }

  public int getLiftId() {
    return liftId;
  }

  public void setLiftId(int liftId) {
    this.liftId = liftId;
  }

  public int getNumRides() {
    return numRides;
  }

  public void setNumRides(int numRides) {
    this.numRides = numRides;
  }

  @Override
  public String toString() {
    return "[" + liftId + "," + numRides + "]";
  }
}
