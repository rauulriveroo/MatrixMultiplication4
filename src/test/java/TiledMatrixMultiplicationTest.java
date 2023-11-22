import org.example.matrices.DenseMatrix;
import org.example.operations.TiledMatrixMultiplication;
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

        TiledMatrixMultiplication multiplication = new TiledMatrixMultiplication(matrix1, matrix2, 6);
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

        System.out.println("Result:" + result);
        System.out.println("Expected:" + expected);
    }
}