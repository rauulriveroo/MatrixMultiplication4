import org.example.matrices.CCSMatrix;
import org.example.matrices.CRSMatrix;
import org.example.matrices.COOMatrix;
import org.example.mtx.MTXReader;
import org.example.operations.SparseMatrixMultiplication;
import org.example.operations.transformation.CCSMatrixBuilder;
import org.example.operations.transformation.CRSMatrixBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

public class SparseMatrixMultiplicationTest {

    @Test
    public void testMultiply() {

        MTXReader reader = new MTXReader();
        COOMatrix cooMatrix = reader.readMTXFile("src/main/java/org/example/datalake/1138_bus.mtx");

        CCSMatrixBuilder ccsMatrixBuilder = new CCSMatrixBuilder();
        CCSMatrix ccsMatrix = ccsMatrixBuilder.convertToCCS(cooMatrix);

        CRSMatrixBuilder crsMatrixBuilder = new CRSMatrixBuilder();
        CRSMatrix crsMatrix = crsMatrixBuilder.convertToCRS(cooMatrix);

        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication(crsMatrix, ccsMatrix);
        sparseMatrixMultiplication.multiply();

        COOMatrix result = sparseMatrixMultiplication.getResult();

        COOMatrix expected = reader.readMTXFile("src/main/java/org/example/datalake/result.mtx");

        assertEquals(expected, result);
    }

    @Test
    public void test2Multiply() {

        MTXReader reader = new MTXReader();
        COOMatrix cooMatrix = reader.readMTXFile("src/main/java/org/example/datalake/494_bus.mtx");

        CCSMatrixBuilder ccsMatrixBuilder = new CCSMatrixBuilder();
        CCSMatrix ccsMatrix = ccsMatrixBuilder.convertToCCS(cooMatrix);

        CRSMatrixBuilder crsMatrixBuilder = new CRSMatrixBuilder();
        CRSMatrix crsMatrix = crsMatrixBuilder.convertToCRS(cooMatrix);

        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication(crsMatrix, ccsMatrix);
        sparseMatrixMultiplication.multiply();

        COOMatrix result = sparseMatrixMultiplication.getResult();

        COOMatrix expected = reader.readMTXFile("src/main/java/org/example/datalake/result2.mtx");

        assertEquals(expected, result);
    }
}