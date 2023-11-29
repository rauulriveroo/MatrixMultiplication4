import org.example.matrices.CCSMatrix;
import org.example.matrices.CRSMatrix;
import org.example.matrices.COOMatrix;
import org.example.matrices.Coordinate;
import org.example.mtx.MTXReader;
import org.example.operations.SparseMatrixMultiplication;
import org.example.operations.transformation.CCSMatrixBuilder;
import org.example.operations.transformation.CRSMatrixBuilder;
import org.example.operations.transformation.MatrixTransformation;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SparseMatrixMultiplicationTest {

    @Test
    public void testMultiply() {

        MTXReader reader = new MTXReader();
        COOMatrix cooMatrix = reader.readMTXFile("src/main/java/org/example/datalake/1138_bus.mtx");

        MatrixTransformation<CCSMatrix> ccsMatrixBuilder = new CCSMatrixBuilder();
        CCSMatrix ccsMatrix = ccsMatrixBuilder.transform(cooMatrix);

        MatrixTransformation<CRSMatrix> crsMatrixBuilder = new CRSMatrixBuilder();
        CRSMatrix crsMatrix = crsMatrixBuilder.transform(cooMatrix);

        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication(crsMatrix, ccsMatrix);
        sparseMatrixMultiplication.multiply();

        COOMatrix result = sparseMatrixMultiplication.getResult();

        COOMatrix expected = reader.readMTXFile("src/main/java/org/example/datalake/result.mtx");

        assertEquals(expected, result);
    }

    @Test
    public void test2Multiply() {

        MTXReader reader = new MTXReader();
        COOMatrix cooMatrix = reader.readMTXFile("src/main/java/org/example/datalake/mc2depi.mtx");

        MatrixTransformation<CCSMatrix> ccsMatrixBuilder = new CCSMatrixBuilder();
        CCSMatrix ccsMatrix = ccsMatrixBuilder.transform(cooMatrix);

        MatrixTransformation<CRSMatrix> crsMatrixBuilder = new CRSMatrixBuilder();
        CRSMatrix crsMatrix = crsMatrixBuilder.transform(cooMatrix);

        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication(crsMatrix, ccsMatrix);
        sparseMatrixMultiplication.multiply();

        COOMatrix result = sparseMatrixMultiplication.getResult();

        COOMatrix expected = reader.readMTXFile("src/main/java/org/example/datalake/result_mc2depi.mtx");

        assertEquals(expected, result);
    }

    @Test
    public void test3Multiply() {

        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(0, 0, 1),
                new Coordinate(0, 1, 2),
                new Coordinate(1, 0, 3),
                new Coordinate(1, 1, 4)
        );
        COOMatrix cooMatrix = new COOMatrix(2, 2, coordinates);

        List<Coordinate> coordinates1 = Arrays.asList(
                new Coordinate(0, 0, 7),
                new Coordinate(0, 1, 10),
                new Coordinate(1, 0, 15),
                new Coordinate(1, 1, 22)
        );

        COOMatrix expected = new COOMatrix(2, 2, coordinates1);


        MatrixTransformation<CCSMatrix> ccsMatrixBuilder = new CCSMatrixBuilder();
        CCSMatrix ccsMatrix = ccsMatrixBuilder.transform(cooMatrix);

        MatrixTransformation<CRSMatrix> crsMatrixBuilder = new CRSMatrixBuilder();
        CRSMatrix crsMatrix = crsMatrixBuilder.transform(cooMatrix);

        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication(crsMatrix, ccsMatrix);
        sparseMatrixMultiplication.multiply();

        COOMatrix result = sparseMatrixMultiplication.getResult();

        assertEquals(expected, result);

    }
}