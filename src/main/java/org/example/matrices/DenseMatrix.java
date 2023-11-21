package org.example.matrices;

import org.example.Matrix;

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
}

