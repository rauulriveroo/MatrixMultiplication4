package org.example.matrices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        COOMatrix that = (COOMatrix) o;
        if (numRows != that.numRows || numCols != that.numCols || coordinates.size() != that.coordinates.size()) {
            return false;
        }
        List<Coordinate> otherCoordinates = new ArrayList<>(that.coordinates);
        for (Coordinate coordinate : coordinates) {
            if (!otherCoordinates.remove(coordinate)) {
                return false;
            }
        }
        return true;
    }

}
