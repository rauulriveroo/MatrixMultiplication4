import org.example.matrices.COOMatrix;
import org.example.matrices.DenseMatrix;
import org.example.mtx.MTXReader;
import org.example.operations.ParallelDenseMatrixMultiplication;
import org.example.operations.transformation.DenseMatrixBuilder;
import org.example.operations.transformation.MatrixTransformation;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParallelDenseMatrixMultiplicationTest {

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

        ParallelDenseMatrixMultiplication multiplication = new ParallelDenseMatrixMultiplication(matrix1, matrix2, 2);
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
    public void test2Multiply() throws InterruptedException {
        double[][] data1 = {
                {1, 2, 3, 4, 5, 1},
                {6, 7, 8, 9, 10, 2},
                {11, 12, 13, 14, 15, 3},
                {16, 17, 18, 19, 20, 4},
                {21, 22, 23, 24, 25, 5},
                {6, 7, 8, 9, 10, 11, 12}
        };

        double[][] data2 = {
                {26, 27, 28, 29, 30, 1},
                {31, 32, 33, 34, 35, 2},
                {36, 37, 38, 39, 40, 3},
                {41, 42, 43, 44, 45, 4},
                {46, 47, 48, 49, 50, 5},
                {51, 52, 53, 54, 55, 6}
        };

        DenseMatrix matrix1 = new DenseMatrix(data1);
        DenseMatrix matrix2 = new DenseMatrix(data2);

        ParallelDenseMatrixMultiplication multiplication = new ParallelDenseMatrixMultiplication(matrix1, matrix2, 3);
        multiplication.multiply();

        DenseMatrix result = multiplication.getResult();

        double[][] expectedData = {
                {641, 657, 673, 689, 705, 61},
                {1592, 1634, 1676, 1718, 1760, 142},
                {2543, 2611, 2679, 2747, 2815, 223},
                {3494, 3588, 3682, 3776, 3870, 304},
                {4445, 4565, 4685, 4805, 4925, 385},
                {2051, 2102, 2153, 2204, 2255, 196}
        };

        DenseMatrix expected = new DenseMatrix(expectedData);

        assertEquals(expected, result);
    }

    @Test
    public void test3Multiply() throws InterruptedException {

        MTXReader reader = new MTXReader();
        COOMatrix cooMatrix = reader.readMTXFile("src/main/java/org/example/datalake/1138_bus.mtx");

        MatrixTransformation<DenseMatrix> denseMatrixBuilder = new DenseMatrixBuilder();
        DenseMatrix denseMatrix = denseMatrixBuilder.transform(cooMatrix);

        ParallelDenseMatrixMultiplication parallelDenseMatrixMultiplication = new ParallelDenseMatrixMultiplication(denseMatrix, denseMatrix, 569);
        parallelDenseMatrixMultiplication.multiply();

        DenseMatrix result = parallelDenseMatrixMultiplication.getResult();

        COOMatrix cooMatrix1 = reader.readMTXFile("src/main/java/org/example/datalake/result_2.mtx");
        DenseMatrix expected = denseMatrixBuilder.transform(cooMatrix1);

        assertEquals(expected, result);
    }

    @Test
    public void test4Multiply() throws InterruptedException {

        MTXReader reader = new MTXReader();
        COOMatrix cooMatrix = reader.readMTXFile("src/main/java/org/example/datalake/mc2depi.mtx");

        MatrixTransformation<DenseMatrix> denseMatrixBuilder = new DenseMatrixBuilder();
        DenseMatrix denseMatrix = denseMatrixBuilder.transform(cooMatrix);

        ParallelDenseMatrixMultiplication parallelDenseMatrixMultiplication = new ParallelDenseMatrixMultiplication(denseMatrix, denseMatrix,36867);
        parallelDenseMatrixMultiplication.multiply();

        DenseMatrix result = parallelDenseMatrixMultiplication.getResult();

        COOMatrix cooMatrix1 = reader.readMTXFile("src/main/java/org/example/datalake/result_mc2depi.mtx");
        DenseMatrix expected = denseMatrixBuilder.transform(cooMatrix1);

        assertEquals(expected, result);
    }
}