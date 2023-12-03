package org.example;

import org.example.matrices.COOMatrix;
import org.example.matrices.DenseMatrix;
import org.example.mtx.MTXReader;
import org.example.operations.ParallelDenseMatrixMultiplication;
import org.example.operations.transformation.DenseMatrixBuilder;
import org.example.operations.transformation.MatrixTransformation;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MTXReader reader = new MTXReader();
        COOMatrix cooMatrix = reader.readMTXFile("src/main/java/org/example/datalake/1138_bus.mtx");

        MatrixTransformation<DenseMatrix> denseMatrixBuilder = new DenseMatrixBuilder();
        DenseMatrix denseMatrix = denseMatrixBuilder.transform(cooMatrix);

        ParallelDenseMatrixMultiplication parallelDenseMatrixMultiplication = new ParallelDenseMatrixMultiplication(denseMatrix, denseMatrix, 72);

        //time
        long startTime = System.currentTimeMillis();
        parallelDenseMatrixMultiplication.multiply();
        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds");



    }
}
