package org.example.operations;

import org.example.matrices.CCSMatrix;
import org.example.matrices.CRSMatrix;
import org.example.matrices.Coordinate;
import org.example.matrices.COOMatrix;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixMultiplication implements MatrixMultiplication {

    private CRSMatrix a;
    private CCSMatrix b;
    private COOMatrix c;

    public SparseMatrixMultiplication(CRSMatrix a, CCSMatrix b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void multiply() {
        double[] valA = a.getValues();
        int[] colA = a.getColIndices();
        int[] rowPtrA = a.getRowPtr();

        double[] valB = b.getValues();
        int[] rowB = b.getRowIndices();
        int[] colPtrB = b.getColPtr();

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

        this.c = new COOMatrix(numRowsA, numColsB, resultCOO);
    }

    public COOMatrix getResult() {
        return c;
    }
}