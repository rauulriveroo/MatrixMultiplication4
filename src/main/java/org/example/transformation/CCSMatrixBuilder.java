package org.example.transformation;

import org.example.matrices.CCSMatrix;
import org.example.matrices.COOMatrix;
import org.example.matrices.Coordinate;

import java.util.Comparator;
import java.util.List;

public class CCSMatrixBuilder {

    public CCSMatrix convertToCCS(COOMatrix cooMatrix) {
        List<Coordinate> coordinates = cooMatrix.getCoordinates();

        coordinates.sort(Comparator.comparing(Coordinate::j).thenComparing(Coordinate::i));

        int numNonZeroValues = coordinates.size();
        double[] values = new double[numNonZeroValues];
        int[] rowIndices = new int[numNonZeroValues];
        int[] colPtr = new int[cooMatrix.getNumCols() + 1];

        int currentCol = 0;
        int currentValueIndex = 0;
        colPtr[currentCol] = 0;

        for (Coordinate coordinate : coordinates) {
            int row = coordinate.i();
            int col = coordinate.j();
            double value = coordinate.value();

            if (col != currentCol) {
                currentCol = col;
                colPtr[currentCol] = currentValueIndex;
            }

            values[currentValueIndex] = value;
            rowIndices[currentValueIndex] = row;
            currentValueIndex++;
        }
        colPtr[cooMatrix.getNumCols()] = numNonZeroValues;

        return new CCSMatrix(values, rowIndices, colPtr);
    }
}
