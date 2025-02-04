package com.lincompute.multiplication;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import com.lincompute.multiplication.components.SquareMatrix;
import com.sun.management.OperatingSystemMXBean;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;


public class ParallelizingSquareMatrixMultiplication 
{
	
	private SquareMatrix matrixResult;

	public ParallelizingSquareMatrixMultiplication(SquareMatrix matrix_a, SquareMatrix matrix_b) 
	{
		 this.matrixResult = new SquareMatrix(matrix_a.getM().length);
	}
	
	 public void compute_and_store_coefficient(int i, int j, SquareMatrix matrix_a, SquareMatrix matrix_b)
	 {
	        Double coefficient = 0.0;
	        for (int k = 0; k < matrix_a.getM()[0].length; k++) {
	            coefficient += matrix_a.getM()[i][k] * matrix_b.getM()[k][j];
	        }
	        matrixResult.getM()[i][j] = coefficient;
	 }
	 
	 public void compute_matrix_result(SquareMatrix matrix_a, SquareMatrix matrix_b) throws InterruptedException 
	 {
		 int size = matrix_a.getM().length;
	        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	        List<Future<?>> futures = new ArrayList<>();

	        for (int i = 0; i < size; i++) {
	            for (int j = 0; j < size; j++) {
	                final int row = i;
	                final int col = j;
	                futures.add(executor.submit(() -> {
	                    Double coefficient = 0.0;
	                    for (int k = 0; k < size; k++) {
	                        coefficient += matrix_a.getM()[row][k] * matrix_b.getM()[k][col];
	                    }
	                    matrixResult.getM()[row][col] = coefficient;
	                }));
	            }
	        }

	        // Wait for all tasks to complete
	        for (Future<?> future : futures) {
	            try {
	                future.get();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        executor.shutdown();
	  }
	 
	 

	public SquareMatrix getMatrixResult() {
		return matrixResult;
	}

	public void setMatrixResult(SquareMatrix matrixResult) {
		this.matrixResult = matrixResult;
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Double[][] m11= {{2.0,1.0,3.0,4.0,5.0},{6.0,7.9,8.0,9.0,1.0},{3.0,4.0,5.0,6.0,7.0},{8.0,9.0,1.0,2.0,3.0},{4.0,5.0,6.0,7.0,8.0}};
		Double[][] m12= {{1.0,2.0,3.0,4.0,5.0},{9.0,8.0,7.0,6.0,5.0},{4.0,5.0,6.0,7.0,8.0},{3.0,2.0,1.0,9.0,8.0},{7.0,6.0,5.0,4.0,3.0}}; 
		
		SquareMatrix s1=new SquareMatrix(m11);
		SquareMatrix s2=new SquareMatrix(m12);
		
		ParallelizingSquareMatrixMultiplication psmm= new ParallelizingSquareMatrixMultiplication(s1, s2);
		
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

	        // Record the start time
	    long startTime = System.nanoTime();
	        
	        // Record initial CPU time
	        long initialCpuTime = osBean.getProcessCpuTime();
        
	

		try 
		{
			
			psmm.compute_matrix_result(s1, s2);
			SquareMatrix.display_matrix(psmm.getMatrixResult());
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
        // Record the end time
        long endTime = System.nanoTime();
        
        // Record final CPU time
        long finalCpuTime = osBean.getProcessCpuTime();

        // Compute execution time in milliseconds
        long executionTime = (endTime - startTime) / 1_000_000;
        
        // Compute CPU consumption in percent
        long cpuTimeUsed = finalCpuTime - initialCpuTime;
        double cpuUsage = (cpuTimeUsed / (double) (endTime - startTime)) * 100;
        
        // Print results
        System.out.println("Execution Time: " + executionTime + " ms");
        System.out.println("CPU Usage: " + String.format("%.2f", cpuUsage) + " %");
        
        double rounded = Math.round(143.10000000000002 * 1000000.0)/1000000.0;
        System.out.println(rounded);
	}

}
