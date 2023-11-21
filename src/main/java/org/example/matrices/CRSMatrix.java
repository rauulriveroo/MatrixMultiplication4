package org.example.matrices;

public class CRSMatrix extends SparseMatrix{

    private double[] values; // Lista de valores no nulos en la matriz
    private int[] colIndices; // Lista de índices de columna para cada valor no nulo
    private int[] rowPtr; // Lista de punteros a filas

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
}
