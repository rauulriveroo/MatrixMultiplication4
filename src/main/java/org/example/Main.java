package org.example;

import org.example.matrices.CCSMatrix;
import org.example.matrices.COOMatrix;
import org.example.matrices.CRSMatrix;
import org.example.mtx.MTXReader;
import org.example.mtx.MTXWriter;
import org.example.operations.SparseMatrixMultiplication;
import org.example.operations.transformation.CCSMatrixBuilder;
import org.example.operations.transformation.CRSMatrixBuilder;

public class Main {

    public static void main(String[] args) {
        MTXReader reader = new MTXReader();
        COOMatrix cooMatrix = reader.readMTXFile("src/main/java/org/example/datalake/mc2depi.mtx");

        CCSMatrixBuilder ccsMatrixBuilder = new CCSMatrixBuilder();
        CCSMatrix ccsMatrix = ccsMatrixBuilder.convertToCCS(cooMatrix);

        CRSMatrixBuilder crsMatrixBuilder = new CRSMatrixBuilder();
        CRSMatrix crsMatrix = crsMatrixBuilder.convertToCRS(cooMatrix);

        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication(crsMatrix, ccsMatrix);
        sparseMatrixMultiplication.multiply();

        COOMatrix result = sparseMatrixMultiplication.getResult();

        MTXWriter writer = new MTXWriter();
        writer.writeToMTXFile(result, "src/main/java/org/example/datalake/result_mc2depi.mtx");


    }
}
