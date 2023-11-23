import org.example.matrices.*;
import org.example.operations.transformation.CCSMatrixBuilder;
import org.example.operations.transformation.CRSMatrixBuilder;
import org.example.operations.transformation.DenseMatrixBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransformationTest {

    @Test
    public void testCRSTransformation() {
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(0, 0, 1),
                new Coordinate(0, 2, 6),
                new Coordinate(1, 1, 2),
                new Coordinate(1, 3, 7),
                new Coordinate(2, 2, 3),
                new Coordinate(3, 0, 5),
                new Coordinate(3, 3, 4)
        );
        COOMatrix cooMatrix = new COOMatrix(3, 3, coordinates);

        CRSMatrixBuilder crsMatrixBuilder = new CRSMatrixBuilder();
        CRSMatrix crsMatrix = crsMatrixBuilder.convertToCRS(cooMatrix);

        CRSMatrix expected = new CRSMatrix(
                new double[]{1, 6, 2, 7, 3, 5, 4},
                new int[]{0, 2, 1, 3, 2, 0, 3},
                new int[]{0, 2, 4, 5, 7}
        );

        assertEquals(expected, crsMatrix);
    }

    @Test
    public void testCCSTransformation() {
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(0, 0, 1),
                new Coordinate(0, 2, 6),
                new Coordinate(1, 1, 2),
                new Coordinate(1, 3, 7),
                new Coordinate(2, 2, 3),
                new Coordinate(3, 0, 5),
                new Coordinate(3, 3, 4)
        );
        COOMatrix cooMatrix = new COOMatrix(3, 3, coordinates);

        CCSMatrixBuilder ccsMatrixBuilder = new CCSMatrixBuilder();
        CCSMatrix ccsMatrix = ccsMatrixBuilder.convertToCCS(cooMatrix);

        CCSMatrix expected = new CCSMatrix(
                new double[]{1, 5, 2, 6, 3, 7, 4},
                new int[]{0, 3, 1, 0, 2, 1, 3},
                new int[]{0, 2, 3, 5, 7}
        );

        assertEquals(expected, ccsMatrix);
    }

    @Test
    public void testDenseTransformation() {
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(0, 0, 1),
                new Coordinate(0, 2, 6),
                new Coordinate(1, 1, 2),
                new Coordinate(1, 3, 7),
                new Coordinate(2, 2, 3),
                new Coordinate(3, 0, 5),
                new Coordinate(3, 3, 4)
        );
        COOMatrix cooMatrix = new COOMatrix(3, 3, coordinates);

        DenseMatrixBuilder denseMatrixBuilder = new DenseMatrixBuilder();
        DenseMatrix result = denseMatrixBuilder.convertToDense(cooMatrix);

        double[][] expectedData = {
                {1, 0, 6, 0},
                {0, 2, 0, 7},
                {0, 0, 3, 0},
                {5, 0, 0, 4}
        };

        DenseMatrix expected = new DenseMatrix(expectedData);

        assertEquals(expected, result);

        System.out.println("Result:" + result);
        System.out.println("Expected:" + expected);
    }
}