package edu.neu.ccs.cs5010.tutorial;

public class Ride {
    private String time;
    private String liftId;

    public Ride(String time, String liftId) {
        this.time = time;
        this.liftId = liftId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLiftId() {
        return liftId;
    }

    public void setLiftId(String liftId) {
        this.liftId = liftId;
    }

    @Override
    public String toString() {
        return "[" + time + "," + liftId + "]";
    }
}
