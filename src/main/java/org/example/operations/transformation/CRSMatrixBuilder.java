package org.example.operations.transformation;

import org.example.matrices.COOMatrix;
import org.example.matrices.CRSMatrix;
import org.example.matrices.Coordinate;

import java.util.Comparator;
import java.util.List;

public class CRSMatrixBuilder implements MatrixTransformation<CRSMatrix>{

    @Override
    public CRSMatrix transform(COOMatrix cooMatrix) {
        List<Coordinate> coordinates = cooMatrix.getCoordinates();

        coordinates.sort(Comparator.comparing(Coordinate::i).thenComparing(Coordinate::j));

        int numNonZeroValues = coordinates.size();
        double[] values = new double[numNonZeroValues];
        int[] colIndices = new int[numNonZeroValues];
        int[] rowPtr = new int[cooMatrix.getNumRows() + 1];

        int currentRow = 0;
        int currentValueIndex = 0;
        rowPtr[currentRow] = 0;


        for (Coordinate coordinate : coordinates) {
            int row = coordinate.i();
            int col = coordinate.j();
            double value = coordinate.value();


            if (row != currentRow) {
                currentRow = row;
                rowPtr[currentRow] = currentValueIndex;
            }

            values[currentValueIndex] = value;
            colIndices[currentValueIndex] = col;
            currentValueIndex++;

        }
        rowPtr[cooMatrix.getNumRows()] = numNonZeroValues;

        return new CRSMatrix(values, colIndices, rowPtr);
    }

}
