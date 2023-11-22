package org.example;

import org.example.matrices.*;
import org.example.mtx.MTXWriter;
import org.example.operations.SparseMatrixMultiplication;
import org.example.mtx.MTXReader;
import org.example.operations.transformation.CCSMatrixBuilder;
import org.example.operations.transformation.CRSMatrixBuilder;

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

        SparseMatrixMultiplication cmm = new SparseMatrixMultiplication(crs, ccs);
        cmm.multiply();
        COOMatrix result = cmm.getResult();

        MTXWriter mtxWriter = new MTXWriter();
        mtxWriter.writeToMTXFile(result, "src/main/java/org/example/datalake/result.mtx");

        long sparseEndTime = System.currentTimeMillis();
        long sparseExecTime = sparseEndTime - sparseStartTime;
        System.out.println("Sparse matrix execution time: " + sparseExecTime + " ms");

        /*DenseMatrixBuilder denseMatrixBuilder = new DenseMatrixBuilder();
        DenseMatrix a = denseMatrixBuilder.convertToDenseMatrix(cooMatrix);


        long denseStartTime = System.currentTimeMillis();

        DenseMatrixMultiplication denseMatrixMultiplication = new DenseMatrixMultiplication(a,a);
        denseMatrixMultiplication.multiply();
        DenseMatrix result = denseMatrixMultiplication.getResult();


        long denseEndTime = System.currentTimeMillis();
        long denseExecTime = denseEndTime - denseStartTime;
        System.out.println("Dense matrix execution time: " + denseExecTime + " ms");*/
    }

}
