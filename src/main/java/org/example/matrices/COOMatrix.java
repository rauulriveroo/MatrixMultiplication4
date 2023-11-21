package org.example.matrices;

import java.util.List;

public class COOMatrix extends SparseMatrix {
    public final int numRows;
    public final int numCols;
    public final List<Coordinate> coordinates;

    public COOMatrix(int numRows, int numCols, List<Coordinate> coordinates) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.coordinates = coordinates;
    }

    @Override
    public int size() {
        return coordinates.size();
    }


    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    @Override
    public double get(int i, int j) {
        return coordinates.stream()
                .filter(c -> c.i() == i && c.j() == j)
                .findFirst()
                .map(Coordinate::value)
                .orElse(0.0);
    }
}
