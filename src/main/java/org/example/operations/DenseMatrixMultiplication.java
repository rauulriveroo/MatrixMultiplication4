package org.example.operations;

import org.example.matrices.DenseMatrix;

public class DenseMatrixMultiplication implements MatrixMultiplication {

    private DenseMatrix a;
    private DenseMatrix b;
    private DenseMatrix c;

    public DenseMatrixMultiplication(DenseMatrix a, DenseMatrix b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void multiply() {
        if (a.size() != b.size()) {
            throw new IllegalArgumentException("Cannot multiply matrices: number of columns of A must match number of rows of B.");
        }

        int size = a.size();
        double[][] result = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    result[i][j] += a.get(i, k) * b.get(k, j);
                }
            }
        }

        this.c = new DenseMatrix(result);

    }

    public DenseMatrix getResult() {
        return c;
    }
}