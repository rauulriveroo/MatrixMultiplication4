import org.example.matrices.COOMatrix;
import org.example.matrices.DenseMatrix;
import org.example.mtx.MTXReader;
import org.example.operations.TiledMatrixMultiplication;
import org.example.transformation.DenseMatrixBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

public class TiledMatrixMultiplicationTest {

    @Test
    public void testMultiply() throws InterruptedException {
        double[][] data1 = {
                {2, 1, 5, 3},
                {0, 7, 1, 6},
                {9, 2, 4, 4},
                {3, 6, 7, 2}
        };

        double[][] data2 = {
                {6, 1, 2, 3},
                {4, 5, 6, 5},
                {1, 9, 8, -8},
                {4, 0, -8, 5}
        };

        DenseMatrix matrix1 = new DenseMatrix(data1);
        DenseMatrix matrix2 = new DenseMatrix(data2);

        TiledMatrixMultiplication multiplication = new TiledMatrixMultiplication(matrix1, matrix2, 4);
        multiplication.multiply();

        DenseMatrix result = multiplication.getResult();

        double[][] expectedData = {
                {33, 52, 26, -14},
                {53, 44, 2, 57},
                {82, 55, 30, 25},
                {57, 96, 82, -7}
        };

        DenseMatrix expected = new DenseMatrix(expectedData);

        assertEquals(expected, result);
    }

    @Test
    public void test2multiply() throws InterruptedException {

        MTXReader mtxReader = new MTXReader();
        COOMatrix cooMatrix1 = mtxReader.readMTXFile("src/main/java/org/example/datalake/1138_bus.mtx");
        COOMatrix cooMatrix2 = mtxReader.readMTXFile("src/main/java/org/example/datalake/1138_bus.mtx");

        DenseMatrixBuilder denseMatrixBuilder = new DenseMatrixBuilder();
        DenseMatrix matrix1 = denseMatrixBuilder.convertToDenseMatrix(cooMatrix1);
        DenseMatrix matrix2 = denseMatrixBuilder.convertToDenseMatrix(cooMatrix2);

        TiledMatrixMultiplication multiplication = new TiledMatrixMultiplication(matrix1, matrix2, 2);
        multiplication.multiply();

        DenseMatrix result = multiplication.getResult();

        COOMatrix cooMatrix3 = mtxReader.readMTXFile("src/main/java/org/example/datalake/result.mtx");
        DenseMatrix expected = denseMatrixBuilder.convertToDenseMatrix(cooMatrix3);

        assertEquals(expected, result);
    }
}