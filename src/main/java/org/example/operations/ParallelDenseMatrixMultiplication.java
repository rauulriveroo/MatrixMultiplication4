package org.example.operations;

import org.example.matrices.DenseMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelDenseMatrixMultiplication {

    public DenseMatrix multiply(DenseMatrix a, DenseMatrix b) throws InterruptedException {
        int rowsA = a.size();
        int colsA = a.size();
        int colsB = b.size();

        double[][] result = new double[rowsA][colsB];

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                final int row = i;
                final int col = j;
                executor.execute(() -> {
                    for (int k = 0; k < colsA; k++) {
                        result[row][col] += a.get(row, k) * b.get(k, col);
                    }
                });
            }
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        return new DenseMatrix(result);
    }

}