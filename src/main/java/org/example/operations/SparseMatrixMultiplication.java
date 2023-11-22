package org.example.operations;

import org.example.matrices.CCSMatrix;
import org.example.matrices.CRSMatrix;
import org.example.matrices.Coordinate;
import org.example.matrices.COOMatrix;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixMultiplication {
    public COOMatrix multiply(CRSMatrix matrixA, CCSMatrix matrixB) {
        double[] valA = matrixA.getValues();
        int[] colA = matrixA.getColIndices();
        int[] rowPtrA = matrixA.getRowPtr();

        double[] valB = matrixB.getValues();
        int[] rowB = matrixB.getRowIndices();
        int[] colPtrB = matrixB.getColPtr();

        int numRowsA = rowPtrA.length - 1;
        int numColsB = colPtrB.length - 1;

        List<Coordinate> resultCOO = new ArrayList<>();

        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsB; j++) {
                double sum = 0;
                int startA = rowPtrA[i];
                int endA = rowPtrA[i + 1];
                int startB = colPtrB[j];
                int endB = colPtrB[j + 1];

                while (startA < endA && startB < endB) {
                    int colIndexA = colA[startA];
                    int rowIndexB = rowB[startB];
                    if (colIndexA == rowIndexB) {
                        sum += valA[startA] * valB[startB];
                        startA++;
                        startB++;
                    } else if (colIndexA < rowIndexB) {
                        startA++;
                    } else {
                        startB++;
                    }
                }

                if (sum != 0) {
                    resultCOO.add(new Coordinate(i, j, sum));
                }
            }
        }

        return new COOMatrix(numRowsA,numColsB,resultCOO);
    }
}
