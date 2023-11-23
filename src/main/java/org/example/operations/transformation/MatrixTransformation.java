package org.example.operations.transformation;

import org.example.matrices.COOMatrix;
import org.example.matrices.Matrix;

public interface MatrixTransformation<T extends Matrix> {
    T transform(COOMatrix cooMatrix);
}