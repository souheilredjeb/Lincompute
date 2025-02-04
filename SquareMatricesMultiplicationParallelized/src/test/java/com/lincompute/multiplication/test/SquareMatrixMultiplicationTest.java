package com.lincompute.multiplication.test;

import org.junit.jupiter.api.Test;

import com.lincompute.multiplication.ParallelizingSquareMatrixMultiplication;
import com.lincompute.multiplication.components.SquareMatrix;


public class SquareMatrixMultiplicationTest
{

	private SquareMatrix s1=null;
	private SquareMatrix s2=null;
	private SquareMatrix result=null;
	private SquareMatrix expected=null;
	
	
	
	private ParallelizingSquareMatrixMultiplication psmm=null;
	
	@Test public void t_001()
	{
		Double[][] m11= {{2.0,1.0,3.0,4.0,5.0},{6.0,7.9,8.0,9.0,1.0},{3.0,4.0,5.0,6.0,7.0},{8.0,9.0,1.0,2.0,3.0},{4.0,5.0,6.0,7.0,8.0}};
		Double[][] m12= {{1.0,2.0,3.0,4.0,5.0},{9.0,8.0,7.0,6.0,5.0},{4.0,5.0,6.0,7.0,8.0},{3.0,2.0,1.0,9.0,8.0},{7.0,6.0,5.0,4.0,3.0}}; 
		Double[][] m1= {{70.00,65.00,60.00,91.00,86.00},{143.10,139.20,135.30,212.40,208.50},{126.00,117.00,108.00,153.00,144.00},{120.00,115.00,110.00,123.00,118.00},{150.00,140.00,130.00,183.00,173.00}};
		s1=new SquareMatrix(m11);
		s2=new SquareMatrix(m12);
		expected=new SquareMatrix(m1);
		psmm= new ParallelizingSquareMatrixMultiplication(s1, s2);
		try 
		{
			psmm.compute_matrix_result(s1, s2);
			result=psmm.getMatrixResult();
			SquareMatrix.display_matrix(result);
			System.out.println("---------------------------");
			System.out.println("---------------------------");
			SquareMatrix.display_matrix(expected);		
			org.junit.jupiter.api.Assertions.assertTrue(SquareMatrix.compareSquareMatrix(result, expected));
			
		}
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test public void t_002()
	{
		Double[][] m21= {{5.0,4.0,3.0,2.0,1.0},{8.0,9.0,7.0,6.0,5.0},{2.0,1.0,9.0,8.0,7.0},{6.0,5.0,4.0,3.0,2.0},{9.0,8.0,7.0,6.0,5.0}};
		Double[][] m22= {{3.0,2.0,1.0,9.0,8.0},{7.0,6.0,5.0,4.0,3.0},{9.0,8.0,7.0,6.0,5.0},{1.0,2.0,3.0,4.0,5.0},{6.0,5.0,4.0,3.0,2.0}};
		Double[][] m2= {{78.00,67.00,56.00,90.00,79.00},{186.00,163.00,140.00,189.00,166.00},{144.00,133.00,122.00,129.00,118.00},{104.00,90.00,76.00,116.00,102.00},{182.00,159.00,136.00,194.00,171.00}};
		s1=new SquareMatrix(m21);
		s2=new SquareMatrix(m22);
		expected=new SquareMatrix(m2);
		psmm= new ParallelizingSquareMatrixMultiplication(s1, s2);
		try 
		{
			psmm.compute_matrix_result(s1, s2);
			result=psmm.getMatrixResult();
			SquareMatrix.display_matrix(result);
			System.out.println("---------------------------");
			System.out.println("---------------------------");
			SquareMatrix.display_matrix(expected);		
			org.junit.jupiter.api.Assertions.assertTrue(SquareMatrix.compareSquareMatrix(result, expected));
			
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}
	
	@Test public void t_003()
	{
		Double[][] m31= {{9.0,8.0,7.0,6.0,5.0},{4.0,5.0,6.0,7.0,8.0},{3.0,2.0,1.0,9.0,8.0},{7.0,6.0,5.0,4.0,3.0},{2.0,1.0,9.0,8.0,7.0}};
		Double[][] m32= {{6.0,5.0,4.0,3.0,2.0},{1.0,2.0,3.0,4.0,5.0},{8.0,9.0,7.0,6.0,5.0},{4.0,5.0,6.0,7.0,8.0},{3.0,2.0,1.0,9.0,8.0}};
		Double[][] m3= {{157.00,164.00,150.00,188.00,181.00},{129.00,135.00,123.00,189.00,183.00},{88.00,89.00,87.00,158.00,157.00},{113.00,118.00,108.00,130.00,125.00},{138.00,147.00,129.00,183.00,174.00}};
		s1=new SquareMatrix(m31);
		s2=new SquareMatrix(m32);
		expected=new SquareMatrix(m3);
		psmm= new ParallelizingSquareMatrixMultiplication(s1, s2);
		try 
		{
			psmm.compute_matrix_result(s1, s2);
			result=psmm.getMatrixResult();
			SquareMatrix.display_matrix(result);
			System.out.println("---------------------------");
			System.out.println("---------------------------");
			SquareMatrix.display_matrix(expected);		
			org.junit.jupiter.api.Assertions.assertTrue(SquareMatrix.compareSquareMatrix(result, expected));	
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}
	
	@Test public void t_004()
	{
		Double[][] m41= {{8.0,7.0,6.0,5.0,4.0},{3.0,2.0,1.0,9.0,8.0},{1.0,2.0,3.0,4.0,5.0},{8.0,9.0,7.0,6.0,5.0},{4.0,5.0,6.0,7.0,8.0}};
		Double[][] m42= {{4.0,5.0,6.0,7.0,8.0},{2.0,1.0,9.0,8.0,7.0},{7.0,6.0,5.0,4.0,3.0},{1.0,2.0,3.0,4.0,5.0},{9.0,8.0,7.0,6.0,5.0}};
		Double[][] m4= {{129.00,125.00,184.00,180.00,176.00},{104.00,105.00,124.00,125.00,126.00},{78.00,73.00,86.00,81.00,76.00},{150.00,143.00,217.00,210.00,203.00},{147.00,139.00,176.00,168.00,160.00}};
		s1=new SquareMatrix(m41);
		s2=new SquareMatrix(m42);
		expected=new SquareMatrix(m4);
		psmm= new ParallelizingSquareMatrixMultiplication(s1, s2);
		try 
		{
			psmm.compute_matrix_result(s1, s2);
			result=psmm.getMatrixResult();
			SquareMatrix.display_matrix(result);
			System.out.println("---------------------------");
			System.out.println("---------------------------");
			SquareMatrix.display_matrix(expected);		
			org.junit.jupiter.api.Assertions.assertTrue(SquareMatrix.compareSquareMatrix(result, expected));
			
		}
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	@Test public void t_005()
	{
		Double[][] m51= {{7.0,6.0,5.0,4.0,3.0},{9.0,8.0,7.0,6.0,5.0},{1.0,2.0,3.0,4.0,5.0},{8.0,9.0,7.0,6.0,5.0},{4.0,5.0,6.0,7.0,8.0}};
		Double[][] m52= {{5.0,4.0,3.0,2.0,1.0},{6.0,5.0,4.0,3.0,2.0},{9.0,8.0,7.0,6.0,5.0},{3.0,2.0,1.0,9.0,8.0},{7.0,6.0,5.0,4.0,3.0}};
		Double[][] m5= {{149.00,124.00,99.00,110.00,85.00},{209.00,174.00,139.00,158.00,123.00},{91.00,76.00,61.00,82.00,67.00},{210.00,175.00,140.00,159.00,124.00},{181.00,151.00,121.00,154.00,124.00}};
		s1=new SquareMatrix(m51);
		s2=new SquareMatrix(m52);
		expected=new SquareMatrix(m5);
		psmm= new ParallelizingSquareMatrixMultiplication(s1, s2);
		try 
		{
			psmm.compute_matrix_result(s1, s2);
			result=psmm.getMatrixResult();
			SquareMatrix.display_matrix(result);
			System.out.println("---------------------------");
			System.out.println("---------------------------");
			SquareMatrix.display_matrix(expected);		
			//-------------------------------------------------//
			org.junit.jupiter.api.Assertions.assertTrue(SquareMatrix.compareSquareMatrix(result, expected));
			
		}
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public SquareMatrixMultiplicationTest()
	{
		// TODO Auto-generated constructor stub
	}

}
