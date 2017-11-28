package edu.neu.ccs.cs5010;

public class LiftRide {
  //Fields
  //rideInfo: "(time):(liftId)"
  private int skierId;
  private String rideInfo;

  public LiftRide() {
    this(0,null);
  }

  public LiftRide(int skierId, String rideInfo) {
    this.skierId = skierId;
    this.rideInfo = rideInfo;
  }

  public int getSkierId() {
    return skierId;
  }

  public void setSkierId(int skierId) {
    this.skierId = skierId;
  }

  public String getRideInfo() {
    return rideInfo;
  }

  public void setRideInfo(String rideInfo) {
    this.rideInfo = rideInfo;
  }

  @Override
  public String toString() {
    return "[" + skierId + "," + rideInfo + "]";
  }
}
