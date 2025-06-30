package com.linsolve.computing.operations;

import java.util.ArrayList;

import com.linsolve.computing.components.Identity;
import com.linsolve.computing.components.SquareMatrix;

public class InvertingSquareMatrix 
{
	public static void apply_pivot_gauss(SquareMatrix s1, ArrayList<SquareMatrix> p) throws Exception {
	    int n = s1.getD().length;
	    
	    // Forward elimination
	    for (int i = 0; i < n; i++) 
	    {
	        // Normalize the pivot row
	        Double pivot = s1.getD()[i][i];
	        if (pivot == 0) {
	            throw new Exception("Matrix diagonal contains zero pivot, cannot proceed.");
	        }

	        LiLamdaLi scaleRow = new LiLamdaLi(n, i, 1.0 / pivot);
	        s1 = MatrixOperations.MultiplyMatrix(scaleRow, s1);
	        p.add(scaleRow);

	        // Eliminate column entries below the pivot
	        for (int j = i + 1; j < n; j++) 
	        {
	            Double factor = -s1.getD()[j][i];
	            LiLamdaLj eliminateRow = new LiLamdaLj(n, j, i, factor);
	            s1 = MatrixOperations.MultiplyMatrix(eliminateRow, s1);
	            p.add(eliminateRow);
	        }
	    }

	    // Back substitution
	    for (int i = n - 1; i >= 0; i--) 
	    {
	        for (int j = i - 1; j >= 0; j--) 
	        {
	            Double factor = -s1.getD()[j][i];
	            LiLamdaLj eliminateRow = new LiLamdaLj(n, j, i, factor);
	            s1 = MatrixOperations.MultiplyMatrix(eliminateRow, s1);
	            p.add(eliminateRow);
	        }
	    }

	    System.out.println("Reduced matrix:");
	    DisplayMatrix(s1);

	    // Compute the inverse by multiplying the pivoting matrices in reverse order
	    SquareMatrix inverse = computeInverse(s1, p);
	    System.out.println("Inverse matrix:");
	    DisplayMatrix(inverse);
	}

	public static SquareMatrix computeInverse(SquareMatrix s1, ArrayList<SquareMatrix> elementaryMatrices) throws Exception {
	    int n = s1.getD().length;
	    SquareMatrix inverse = new Identity(n);

	    // Multiply elementary matrices in reverse order to compute the inverse
	    for (int i = 0; i<elementaryMatrices.size()		; i++)
	    {
	        inverse = MatrixOperations.MultiplyMatrix(elementaryMatrices.get(i), inverse);
	    }
	    return inverse;
	}
	
	public static void DisplayMatrix(SquareMatrix s)
	{
		for(int i=0;i<s.getD().length;i++)
		{
			for(int j=0;j<s.getD().length;j++)
			{
				System.out.print(s.getD()[i][j]+ "  ");
				
			}
			System.out.println();
		}
	}

}
