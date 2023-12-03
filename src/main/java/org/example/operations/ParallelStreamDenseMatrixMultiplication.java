package org.example.operations;

import org.example.matrices.DenseMatrix;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ParallelStreamDenseMatrixMultiplication implements MatrixMultiplication {

    private DenseMatrix a;
    private DenseMatrix b;
    private DenseMatrix c;
    private AtomicInteger rowCounter;

    public ParallelStreamDenseMatrixMultiplication(DenseMatrix a, DenseMatrix b) {
        this.a = a;
        this.b = b;
        this.c = new DenseMatrix(new double[a.size()][b.size()]);
        this.rowCounter = new AtomicInteger(0);
    }

    public void multiply() {
        int size = a.size();

        IntStream.range(0, size).parallel().forEach(i -> {
            int row;
            while ((row = rowCounter.getAndIncrement()) < size) {
                for (int k = 0; k < size; k++) {
                    for (int j = 0; j < size; j++) {
                        c.getValues()[row][j] += a.getValues()[row][k] * b.getValues()[k][j];
                    }
                }
            }
        });
    }

    public DenseMatrix getResult() {
        return c;
    }
}