import org.example.matrices.CCSMatrix;
import org.example.matrices.CRSMatrix;
import org.example.matrices.COOMatrix;
import org.example.mtx.MTXReader;
import org.example.operations.SparseMatrixMultiplication;
import org.example.operations.transformation.CCSMatrixBuilder;
import org.example.operations.transformation.CRSMatrixBuilder;
import org.example.operations.transformation.MatrixTransformation;
import org.junit.Test;
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
}