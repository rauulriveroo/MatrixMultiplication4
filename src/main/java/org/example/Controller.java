package org.example;

import org.example.matrices.*;
import org.example.operations.DenseMatrixMultiplication;
import org.example.operations.SparseMatrixMultiplication;
import org.example.reader.MTXReader;
import org.example.transformation.CCSMatrixBuilder;
import org.example.transformation.CRSMatrixBuilder;
import org.example.transformation.DenseMatrixBuilder;

public class Controller {

    private final String filePath;

    public Controller(String filePath) {
        this.filePath = filePath;
    }

    public void run() {
        COOMatrix cooMatrix;
        MTXReader mtxReader = new MTXReader();
        cooMatrix = mtxReader.readMTXFile(filePath);

        CCSMatrixBuilder ccsBuilder = new CCSMatrixBuilder();
        CCSMatrix ccs = ccsBuilder.convertToCCS(cooMatrix);
        CRSMatrixBuilder crsBuilder = new CRSMatrixBuilder();
        CRSMatrix crs = crsBuilder.convertToCRS(cooMatrix);

        long sparseStartTime = System.currentTimeMillis();

        SparseMatrixMultiplication cmm = new SparseMatrixMultiplication();
        cmm.multiply(crs, ccs);

        long sparseEndTime = System.currentTimeMillis();
        long sparseExecTime = sparseEndTime - sparseStartTime;
        System.out.println("Sparse matrix execution time: " + sparseExecTime + " ms");

        DenseMatrixBuilder denseMatrixBuilder = new DenseMatrixBuilder();
        DenseMatrix a = denseMatrixBuilder.convertToDenseMatrix(cooMatrix);


        long denseStartTime = System.currentTimeMillis();

        DenseMatrixMultiplication denseMatrixMultiplication = new DenseMatrixMultiplication();
        denseMatrixMultiplication.multiply(a, a);

        long denseEndTime = System.currentTimeMillis();
        long denseExecTime = denseEndTime - denseStartTime;
        System.out.println("Dense matrix execution time: " + denseExecTime + " ms");
    }

}
