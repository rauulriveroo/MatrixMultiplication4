package org.example.matrices;

import java.util.Arrays;
import java.util.Objects;

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
        CCSMatrix ccsMatrix = (CCSMatrix) o;
        return Arrays.equals(values, ccsMatrix.values) &&
                Arrays.equals(rowIndices, ccsMatrix.rowIndices) &&
                Arrays.equals(colPtr, ccsMatrix.colPtr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(values, rowIndices, colPtr);
        result = 31 * result + Arrays.hashCode(values);
        result = 31 * result + Arrays.hashCode(rowIndices);
        result = 31 * result + Arrays.hashCode(colPtr);
        return result;
    }

    @Override
    public String toString() {
        return "CCSMatrix{" +
                "values=" + Arrays.toString(values) +
                ", rowIndices=" + Arrays.toString(rowIndices) +
                ", colPtr=" + Arrays.toString(colPtr) +
                '}';
    }
}
