package com.linsolve.computing.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.linsolve.computing.components.SquareMatrix;
import com.linsolve.computing.operations.InvertingSquareMatrix;
import com.linsolve.computing.operations.MatrixOperations;

import java.util.ArrayList;




public class MatrixOperationsTest 
{
	
    @Test
    public void testMatrixInversionAndVerification() throws Exception {
        // Test case 1
        Double[][] d1 = {
            {3.0, 2.0, 1.0, 4.0},
            {1.0, 5.0, 3.0, 2.0},
            {4.0, 1.0, 6.0, 3.0},
            {2.0, 4.0, 3.0, 7.0}
        };
        SquareMatrix s1 = new SquareMatrix(d1, 4);
        verifyInverse(s1);

        // Test case 2
        Double[][] d2 = {
            {4.0, -1.0, 2.0},
            {-1.0, 5.0, -1.0},
            {2.0, -1.0, 6.0}
        };
        SquareMatrix s2 = new SquareMatrix(d2, 3);
        verifyInverse(s2);

        // Test case 3
        Double[][] d3 = {
            {6.0, 1.0, 0.0, -2.0},
            {1.0, 7.0, -2.0, 1.0},
            {0.0, -2.0, 8.0, -1.0},
            {-2.0, 1.0, -1.0, 9.0}
        };
        SquareMatrix s3 = new SquareMatrix(d3, 4);
        verifyInverse(s3);

        // Test case 4
        Double[][] d4 = {
            {5.0, -1.0, 0.0, 0.0, 2.0},
            {-1.0, 6.0, -2.0, 0.0, 1.0},
            {0.0, -2.0, 7.0, -1.0, 0.0},
            {0.0, 0.0, -1.0, 8.0, -2.0},
            {2.0, 1.0, 0.0, -2.0, 0.0}
        };
        SquareMatrix s4 = new SquareMatrix(d4, 5);
        verifyInverse(s4);

        // Test case 5
        Double[][] d5 = {
            {10.0, -3.0, 1.0, 2.0},
            {-3.0, 12.0, -4.0, 1.0},
            {1.0, -4.0, 11.0, -2.0},
            {2.0, 1.0, -2.0, 15.0}
        };
        SquareMatrix s5 = new SquareMatrix(d5, 4);
        verifyInverse(s5);

        // Test case 6
        Double[][] d6 = {
            {7.0, -2.0, 1.0},
            {-2.0, 8.0, -2.0},
            {1.0, -3.0, 9.0}
        };
        SquareMatrix s6 = new SquareMatrix(d6, 3);
        verifyInverse(s6);
    }
    
    private void verifyInverse(SquareMatrix matrix) throws Exception {
        ArrayList<SquareMatrix> elementaryMatrices = new ArrayList<>();
        SquareMatrix original = new SquareMatrix(matrix.getD(), matrix.getN());
        
        // Compute inverse
        InvertingSquareMatrix.apply_pivot_gauss(matrix, elementaryMatrices);
        SquareMatrix inverse = InvertingSquareMatrix.computeInverse(matrix, elementaryMatrices);
        
        // Multiply original with inverse
        SquareMatrix identityCheck = MatrixOperations.MultiplyMatrix(original, inverse);
        
        // Apply rounding with tolerance instead of ceil
        SquareMatrix roundedMatrix = roundWithTolerance(identityCheck, 1e-10);
        
        // Verify the result is identity matrix
        for (int i = 0; i < original.getN(); i++) {
            for (int j = 0; j < original.getN(); j++) {
                double expected = (i == j) ? 1.0 : 0.0;
                assertEquals(expected, roundedMatrix.getD()[i][j], 1e-10,
                    String.format("Mismatch at [%d][%d]", i, j));
            }
        }
    }

    private SquareMatrix roundWithTolerance(SquareMatrix matrix, double tolerance) throws Exception {
        Double[][] result = new Double[matrix.getN()][matrix.getN()];
        for (int i = 0; i < matrix.getN(); i++) {
            for (int j = 0; j < matrix.getN(); j++) {
                double value = matrix.getD()[i][j];
                if (Math.abs(value - 1.0) < tolerance) {
                    result[i][j] = 1.0;
                } else if (Math.abs(value) < tolerance) {
                    result[i][j] = 0.0;
                } else {
                    result[i][j] = value;
                }
            }
        }
        return new SquareMatrix(result, matrix.getN());
    }
    
    private SquareMatrix applyCeil(SquareMatrix matrix) throws Exception {
        Double[][] result = new Double[matrix.getN()][matrix.getN()];
        for (int i = 0; i < matrix.getN(); i++) {
            for (int j = 0; j < matrix.getN(); j++) {
                result[i][j] = Math.ceil(matrix.getD()[i][j]);
            }
        }
        return new SquareMatrix(result, matrix.getN());
    }


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
