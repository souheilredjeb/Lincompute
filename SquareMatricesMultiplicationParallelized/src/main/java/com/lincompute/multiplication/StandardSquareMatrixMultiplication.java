package com.lincompute.multiplication;

import com.lincompute.multiplication.components.SquareMatrix;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;


public class StandardSquareMatrixMultiplication {
    
    private SquareMatrix matrixResult;

    public StandardSquareMatrixMultiplication(SquareMatrix matrix_a, SquareMatrix matrix_b) {
        this.matrixResult = new SquareMatrix(matrix_a.getM().length);
    }

    public void compute_matrix_result(SquareMatrix matrix_a, SquareMatrix matrix_b) {
        int size = matrix_a.getM().length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Double coefficient = 0.0;
                for (int k = 0; k < size; k++) {
                    coefficient += matrix_a.getM()[i][k] * matrix_b.getM()[k][j];
                }
                matrixResult.getM()[i][j] = coefficient;
            }
        }
    }

    public SquareMatrix getMatrixResult() {
        return matrixResult;
    }

    public static void main(String[] args) {
        Double[][] dataA = {{2.0,1.0,3.0,4.0,5.0},{6.0,7.9,8.0,9.0,1.0},{3.0,4.0,5.0,6.0,7.0},{8.0,9.0,1.0,2.0,3.0},{4.0,5.0,6.0,7.0,8.0}};
        Double[][] dataB = {{1.0,2.0,3.0,4.0,5.0},{9.0,8.0,7.0,6.0,5.0},{4.0,5.0,6.0,7.0,8.0},{3.0,2.0,1.0,9.0,8.0},{7.0,6.0,5.0,4.0,3.0}}; ;

        SquareMatrix matrixA = new SquareMatrix(dataA);
        SquareMatrix matrixB = new SquareMatrix(dataB);

        StandardSquareMatrixMultiplication multiplication = new StandardSquareMatrixMultiplication(matrixA, matrixB);
        
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        // Record the start time
        long startTime = System.nanoTime();
        
        // Record initial CPU time
        long initialCpuTime = osBean.getProcessCpuTime();
        
        multiplication.compute_matrix_result(matrixA, matrixB);
    
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

}
}
