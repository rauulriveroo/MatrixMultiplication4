package org.example.matrices;

import java.util.Arrays;

public class CCSMatrix extends SparseMatrix{
    private double[] values;
    private int[] rowIndices;
    private int[] colPtr;


    public CCSMatrix(double[] values, int[] rowIndices, int[] colPtr) {
        this.values = values;
        this.rowIndices = rowIndices;
        this.colPtr = colPtr;
    }

    public double get(int row, int col) {
        int colStart = colPtr[col];
        int colEnd = colPtr[col + 1];
        for (int i = colStart; i < colEnd; i++) {
            if (rowIndices[i] == row) {
                return values[i];
            }
        }
        return 0.0;
    }


    public int size() {
        return values.length;
    }

    public double[] getValues() {
        return values;
    }

    public int[] getRowIndices() {
        return rowIndices;
    }

    public int[] getColPtr() {
        return colPtr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CCSMatrix that = (CCSMatrix) o;
        return Arrays.equals(values, that.values) &&
                Arrays.equals(rowIndices, that.rowIndices) &&
                Arrays.equals(colPtr, that.colPtr);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(values);
        result = 31 * result + Arrays.hashCode(rowIndices);
        result = 31 * result + Arrays.hashCode(colPtr);
        return result;
    }
}
