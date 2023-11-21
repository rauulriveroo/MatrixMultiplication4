package org.example.transformation;

import org.example.matrices.COOMatrix;
import org.example.matrices.Coordinate;
import org.example.matrices.DenseMatrix;

import java.util.List;

public class DenseMatrixBuilder {

    public DenseMatrix convertToDenseMatrix(COOMatrix cooMatrix) {
        int numRows = cooMatrix.getNumRows();
        int numCols = cooMatrix.getNumCols();
        List<Coordinate> coordinates = cooMatrix.getCoordinates();

        double[][] data = new double[numRows][numCols];

        for (Coordinate coordinate : coordinates) {
            int row = coordinate.i();
            int col = coordinate.j();
            double value = coordinate.value();
            data[row][col] = value;
        }

        return new DenseMatrix(data);
    }
}
