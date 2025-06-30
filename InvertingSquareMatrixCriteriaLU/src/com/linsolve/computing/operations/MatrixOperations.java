package com.linsolve.computing.operations;


import java.util.ArrayList;

import com.linsolve.computing.components.SquareMatrix;
import com.linsolve.computing.components.Identity;

public class MatrixOperations
{

	public MatrixOperations() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public static SquareMatrix MultiplyMatrix(SquareMatrix s1, SquareMatrix s2) throws Exception
	{
		if(s1.getD().length==s2.getD().length)
		{
			Double[][] d1=s1.getD();
			Double[][] d2=s2.getD();
			Double[][] d3=new Double[d1.length][d1.length];
			Double cij=0.0;
			for(int i=0;i<d1.length;i++)
			{
				
				for(int j=0;j<d2.length;j++)
				{
						for(int k=0; k<d3.length;k++)
						{	
							cij=cij+d1[i][k]*d2[k][j];															//cij+d1[i][k]*d2[k][j];
						}
						d3[i][j]=cij;
						cij=0.0;
				}
				
			}
			SquareMatrix s3=new SquareMatrix(d3,d1.length);
			return s3;
		}
		return null;
	}
	
	
	
	public static SquareMatrix AddMatrix(SquareMatrix s1, SquareMatrix s2) throws Exception
	{
		if(s1.getD().length==s2.getD().length)
		{
			Double[][] d1=s1.getD();
			Double[][] d2=s2.getD();
			Double[][] d3=new Double[d1.length][d1.length];
			
			for(int i=0;i<d1.length;i++)
			{	
				for(int j=0;j<d2.length;j++)
				{
						d3[i][j]=d1[i][j]+d2[i][j];							
				}	
			}
			SquareMatrix s3=new SquareMatrix(d3,d1.length);
			return s3;
		}
		return null;	
	}
	
	public static SquareMatrix AddLamdaMatrix(SquareMatrix s1, SquareMatrix s2, Double lamda) throws Exception
	{
		if(s1.getD().length==s2.getD().length)
		{
			Double[][] d1=s1.getD();
			Double[][] d2=s2.getD();
			Double[][] d3=new Double[d1.length][d1.length];		
			for(int i=0;i<d1.length;i++)
			{	
				for(int j=0;j<d2.length;j++)
				{
						d3[i][j]=d1[i][j]+lamda*d2[i][j]   ;
				}	
			}
			SquareMatrix s3=new SquareMatrix(d3,d1.length);
			return s3;
		}		
		return null;		
	}
	

	
	
	public static SquareMatrix MultiplyMatrixProduct(ArrayList<SquareMatrix>  p) throws Exception
	{
		int size =p.size();
		SquareMatrix product=new Identity(p.get(0).getD().length);
		for(int k=size-1;k>=0;k--)
		{			
			SquareMatrix m1=p.get(k);
			product=MatrixOperations.MultiplyMatrix(product, m1);
		}
		return product;
	}
	
	public static SquareMatrix applyCeil(SquareMatrix matrix) throws Exception {
	    Double[][] result = new Double[matrix.getN()][matrix.getN()];
	    for (int i = 0; i < matrix.getN(); i++) {
	        for (int j = 0; j < matrix.getN(); j++) {
	            result[i][j] = Math.ceil(matrix.getD()[i][j]);
	        }
	    }
	    return new SquareMatrix(result, matrix.getN());
	}
	
}

