package org.example.operations;

import org.example.matrices.DenseMatrix;

import java.util.stream.IntStream;

public class ParallelStreamDenseMatrixMultiplication implements MatrixMultiplication {

    private DenseMatrix a;
    private DenseMatrix b;
    private DenseMatrix c;

    public ParallelStreamDenseMatrixMultiplication(DenseMatrix a, DenseMatrix b) {
        this.a = a;
        this.b = b;
        this.c = new DenseMatrix(new double[a.size()][b.size()]);
    }

    public void multiply() {
        int size = a.size();

        IntStream.range(0, size).parallel().forEach(i -> {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    c.getValues()[i][j] += a.getValues()[i][k] * b.getValues()[k][j];
                }
            }
        });
    }

    public DenseMatrix getResult() {
        return c;
    }
}