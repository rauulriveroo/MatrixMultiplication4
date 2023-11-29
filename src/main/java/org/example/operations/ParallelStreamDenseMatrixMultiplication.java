package org.example.operations;

import org.example.matrices.DenseMatrix;

import java.util.stream.IntStream;

public class ParallelStreamDenseMatrixMultiplication implements MatrixMultiplication {

    private DenseMatrix a;
    private DenseMatrix b;
    private DenseMatrix c;
    private int blockSize;

    public ParallelStreamDenseMatrixMultiplication(DenseMatrix a, DenseMatrix b, int blockSize) {
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

    public void multiply() {
        int size = a.size();

        IntStream.range(0, size).boxed()
                .flatMap(i -> IntStream.range(0, size).mapToObj(j -> new int[]{i, j}))
                .flatMap(pair -> IntStream.range(0, size).mapToObj(k -> new int[]{pair[0], pair[1], k}))
                .parallel()
                .forEach(block -> multiplyBlock(block[0], block[1], block[2]));
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