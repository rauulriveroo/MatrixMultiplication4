package org.example.operations;

import org.example.matrices.DenseMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelDenseMatrixMultiplication implements MatrixMultiplication {

    private DenseMatrix a;
    private DenseMatrix b;
    private DenseMatrix c;
    private int blockSize;

    public ParallelDenseMatrixMultiplication(DenseMatrix a, DenseMatrix b, int blockSize) {
        if (a.size() % blockSize != 0 || b.size() % blockSize != 0) {
            throw new IllegalArgumentException("Matrix size must be divisible by block size");
        }
        if (blockSize > a.size() || blockSize > b.size()) {
            throw new IllegalArgumentException("Block size must not be greater than matrix size");
        }
        if (a.size() % blockSize != 0 || b.size() % blockSize != 0) {
            throw new IllegalArgumentException("Matrix size must be divisible by block size. Matrix size: " + a.size() + ", " + b.size() + ". Block size: " + blockSize);
        }
        this.a = a;
        this.b = b;
        this.blockSize = blockSize;
        this.c = new DenseMatrix(new double[a.size()][b.size()]);
    }

    public void multiply() throws InterruptedException {
        int size = a.size();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < size; i += blockSize) {
            for (int j = 0; j < size; j += blockSize) {
                for (int k = 0; k < size; k += blockSize) {
                    final int ii = i;
                    final int jj = j;
                    final int kk = k;
                    executor.execute(() -> multiplyBlock(ii, jj, kk));
                }
            }
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

    private void multiplyBlock(int row, int col, int i2) {
        for (int i = row; i < Math.min(row + blockSize, a.size()); i++) {
            for (int j = col; j < Math.min(col + blockSize, b.size()); j++) {
                for (int k = i2; k < Math.min(i2 + blockSize, a.size()); k++) {
                    c.getValues()[i][j] += a.getValues()[i][k] * b.getValues()[k][j];
                }
            }
        }
    }

    public DenseMatrix getResult() {
        return c;
    }
}