import org.example.matrices.DenseMatrix;
import org.example.operations.ParallelDenseMatrixMultiplication;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParallelDenseMatrixMultiplicationTest {

    @Test
    public void testMultiply() throws InterruptedException {
        double[][] data1 = {
                {1, 2},
                {3, 4}
        };

        double[][] data2 = {
                {5, 6},
                {7, 8}
        };

        DenseMatrix matrix1 = new DenseMatrix(data1);
        DenseMatrix matrix2 = new DenseMatrix(data2);

        ParallelDenseMatrixMultiplication multiplication = new ParallelDenseMatrixMultiplication();
        DenseMatrix result = multiplication.multiply(matrix1, matrix2);

        double[][] expectedData = {
                {19, 22},
                {43, 50}
        };

        DenseMatrix expected = new DenseMatrix(expectedData);

        result.equals(expected);
    }
}