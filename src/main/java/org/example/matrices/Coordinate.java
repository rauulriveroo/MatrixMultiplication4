package org.example.matrices;


public class Coordinate {
    private final int i;
    private final int j;
    private final double value;

    public Coordinate(int i, int j, double value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public int i() {
        return i;
    }

    public int j() {
        return j;
    }

    public double value() {
        return value;
    }


}
