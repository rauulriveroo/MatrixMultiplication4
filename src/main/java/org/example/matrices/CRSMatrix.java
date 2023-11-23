package org.example.matrices;

import java.util.Arrays;
import java.util.Objects;

public class CRSMatrix extends SparseMatrix{

    private double[] values;
    private int[] colIndices;
    private int[] rowPtr;

    public CRSMatrix(double[] values, int[] colIndices, int[] rowPtr) {
        this.values = values;
        this.colIndices = colIndices;
        this.rowPtr = rowPtr;
    }

    public double get(int row, int col) {
        int start = rowPtr[row];
        int end = rowPtr[row + 1];
        for (int i = start; i < end; i++) {
            if (colIndices[i] == col) {
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

    public int[] getColIndices() {
        return colIndices;
    }

    public int[] getRowPtr() {
        return rowPtr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CRSMatrix)) return false;
        CRSMatrix crsMatrix = (CRSMatrix) o;
        return Arrays.equals(values, crsMatrix.values) &&
                Arrays.equals(colIndices, crsMatrix.colIndices) &&
                Arrays.equals(rowPtr, crsMatrix.rowPtr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(values, colIndices, rowPtr);
        result = 31 * result + Arrays.hashCode(values);
        result = 31 * result + Arrays.hashCode(colIndices);
        result = 31 * result + Arrays.hashCode(rowPtr);
        return result;
    }

    @Override
    public String toString() {
        return "CRSMatrix{" +
                "values=" + Arrays.toString(values) +
                ", colIndices=" + Arrays.toString(colIndices) +
                ", rowPtr=" + Arrays.toString(rowPtr) +
                '}';
    }

}
