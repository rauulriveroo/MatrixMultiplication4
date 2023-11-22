package org.example.matrices;

import java.util.Arrays;

public class DenseMatrix implements Matrix {
    private final double[][] values;

    public DenseMatrix(double[][] values) {
        this.values = values;
    }

    @Override
    public int size() {
        return values.length;
    }

    public double[][] getValues() {
        return values;
    }

    @Override
    public double get(int i, int j) {
        return values[i][j];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DenseMatrix that = (DenseMatrix) o;
        return Arrays.deepEquals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(values);
    }

    @Override
    public String toString() {
        return "DenseMatrix{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}

