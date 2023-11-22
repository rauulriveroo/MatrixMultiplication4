package org.example.matrices;

import org.example.Matrix;

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

    @Override
    public double get(int i, int j) {
        return values[i][j];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DenseMatrix that = (DenseMatrix) obj;
        return Arrays.deepEquals(values, that.values);
    }
}

