package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class Skier {
    private int skierId;
    private int numRides;
    private int totalVertical;
    private int numberOfViews;

    public Skier() {
        this(0, 0, 0, 0);
    }

    public Skier(int skierId, int numRides, int totalVertical, int numberOfViews) {
        this.skierId = skierId;
        this.numRides = numRides;
        this.totalVertical = totalVertical;
        this.numberOfViews = numberOfViews;
    }

    public int getSkierId() {
        return skierId;
    }

    public void setSkierId(int skierId) {
        this.skierId = skierId;
    }

    public int getNumRides() {
        return numRides;
    }

    public void setNumRides(int numRides) {
        this.numRides = numRides;
    }

    public int getTotalVertical() {
        return totalVertical;
    }

    public void setTotalVertical(int totalVertical) {
        this.totalVertical = totalVertical;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    @Override
    public String toString() {
        return "[" + String.valueOf(skierId) + "," + String.valueOf(numRides)
                + "," + String.valueOf(totalVertical) + "," + String.valueOf(numberOfViews) + "]";
    }
}
