package com.lincompute.implementation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Matrix {
    private Double[][] data;
    private int rows;
    private int cols;
    
    // Constructor
    public Matrix(Double[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }
    
    // Constructor with dimensions
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Double[rows][cols];
        // Initialize with zeros
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = 0.0;
            }
        }
    }
    
    // Getters
    public Double[][] getData() {
        return data;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    // Set value at specific position
    public void setValue(int row, int col, Double value) {
        data[row][col] = value;
    }
    
    // Get value from specific position
    public Double getValue(int row, int col) {
        return data[row][col];
    }
    
    // Submatrix methods for A
    public Matrix getA11() {
        int m11 = rows / 2;
        int n11 = cols / 2;
        Matrix A11 = new Matrix(m11, n11);
        
        for (int i = 0; i < m11; i++) {
            for (int j = 0; j < n11; j++) {
                A11.setValue(i, j, data[i][j]);
            }
        }
        return A11;
    }
    
    public Matrix getA21() {
        int m21 = rows - (rows / 2);
        int n21 = cols / 2;
        Matrix A21 = new Matrix(m21, n21);
        
        for (int i = 0; i < m21; i++) {
            for (int j = 0; j < n21; j++) {
                A21.setValue(i, j, data[i + (rows / 2)][j]);
            }
        }
        return A21;
    }
    
    public Matrix getA12() {
        int m12 = rows / 2;
        int n12 = cols - (cols / 2);
        Matrix A12 = new Matrix(m12, n12);
        
        for (int i = 0; i < m12; i++) {
            for (int j = 0; j < n12; j++) {
                A12.setValue(i, j, data[i][j + (cols / 2)]);
            }
        }
        return A12;
    }
    
    public Matrix getA22() {
        int m22 = rows - (rows / 2);
        int n22 = cols - (cols / 2);
        Matrix A22 = new Matrix(m22, n22);
        
        for (int i = 0; i < m22; i++) {
            for (int j = 0; j < n22; j++) {
                A22.setValue(i, j, data[i + (rows / 2)][j + (cols / 2)]);
            }
        }
        return A22;
    }
    
    // Submatrix methods for B
    public Matrix getB11() {
        int n11 = rows / 2;
        int p11 = cols / 2;
        Matrix B11 = new Matrix(n11, p11);
        
        for (int i = 0; i < n11; i++) {
            for (int j = 0; j < p11; j++) {
                B11.setValue(i, j, data[i][j]);
            }
        }
        return B11;
    }
    
    public Matrix getB21() {
        int n21 = rows - (rows / 2);
        int p21 = cols / 2;
        Matrix B21 = new Matrix(n21, p21);
        
        for (int i = 0; i < n21; i++) {
            for (int j = 0; j < p21; j++) {
                B21.setValue(i, j, data[i + (rows / 2)][j]);
            }
        }
        return B21;
    }
    
    public Matrix getB12() {
        int n12 = rows / 2;
        int p12 = cols - (cols / 2);
        Matrix B12 = new Matrix(n12, p12);
        
        for (int i = 0; i < n12; i++) {
            for (int j = 0; j < p12; j++) {
                B12.setValue(i, j, data[i][j + (cols / 2)]);
            }
        }
        return B12;
    }
    
    public Matrix getB22() {
        int n22 = rows - (rows / 2);
        int p22 = cols - (cols / 2);
        Matrix B22 = new Matrix(n22, p22);
        
        for (int i = 0; i < n22; i++) {
            for (int j = 0; j < p22; j++) {
                B22.setValue(i, j, data[i + (rows / 2)][j + (cols / 2)]);
            }
        }
        return B22;
    }
    
    // Regular matrix multiplication
    public static Matrix multiplyingMatrix(Matrix m1, Matrix m2) {
        if (m1.getCols() == m2.getRows()) {
            Double[][] dr = new Double[m1.getRows()][m2.getCols()];
            
            for (int i = 0; i < m1.getRows(); i++) {
                for (int j = 0; j < m2.getCols(); j++) {
                    Double coefficient = 0.0;
                    for (int k = 0; k < m1.getCols(); k++) {
                        coefficient += m1.getValue(i, k) * m2.getValue(k, j);
                    }
                    dr[i][j] = coefficient;
                }
            }
            return new Matrix(dr);
        }
        return null;
    }
    
    // Matrix addition
    public static Matrix addMatrix(Matrix m1, Matrix m2) {
        if (m1.getRows() == m2.getRows() && m1.getCols() == m2.getCols()) {
            Matrix result = new Matrix(m1.getRows(), m1.getCols());
            for (int i = 0; i < m1.getRows(); i++) {
                for (int j = 0; j < m1.getCols(); j++) {
                    result.setValue(i, j, m1.getValue(i, j) + m2.getValue(i, j));
                }
            }
            return result;
        }
        return null;
    }
    
    // Threaded matrix multiplication using four threads
    public static Matrix multiplyMatricesParallel(Matrix A, Matrix B) {
        if (A.getCols() != B.getRows()) {
            return null;
        }
        
        // Create submatrices
        Matrix A11 = A.getA11();
        Matrix A12 = A.getA12();
        Matrix A21 = A.getA21();
        Matrix A22 = A.getA22();
        
        Matrix B11 = B.getB11();
        Matrix B12 = B.getB12();
        Matrix B21 = B.getB21();
        Matrix B22 = B.getB22();
        
        // Create executor service with 4 threads
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        try {
            // Submit all four tasks
            Future<Matrix> future1 = executor.submit(new MatrixMultiplier(A11, B11, A12, B21));
            Future<Matrix> future2 = executor.submit(new MatrixMultiplier(A11, B12, A12, B22));
            Future<Matrix> future3 = executor.submit(new MatrixMultiplier(A21, B11, A22, B21));
            Future<Matrix> future4 = executor.submit(new MatrixMultiplier(A21, B12, A22, B22));
            
            // Get results from all threads
            Matrix C11 = future1.get();
            Matrix C12 = future2.get();
            Matrix C21 = future3.get();
            Matrix C22 = future4.get();
            
            // Combine results into final matrix
            return combineSubmatrices(C11, C12, C21, C22, A.getRows(), B.getCols());
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            executor.shutdown();
        }
    }
    
    // Callable task for matrix multiplication and addition
    private static class MatrixMultiplier implements Callable<Matrix> {
        private Matrix left1, right1, left2, right2;
        
        public MatrixMultiplier(Matrix left1, Matrix right1, Matrix left2, Matrix right2) {
            this.left1 = left1;
            this.right1 = right1;
            this.left2 = left2;
            this.right2 = right2;
        }
        
        @Override
        public Matrix call() throws Exception {
            Matrix product1 = multiplyingMatrix(left1, right1);
            Matrix product2 = multiplyingMatrix(left2, right2);
            return addMatrix(product1, product2);
        }
    }
    
    // Combine four submatrices into one
    private static Matrix combineSubmatrices(Matrix C11, Matrix C12, Matrix C21, Matrix C22, 
                                           int totalRows, int totalCols) {
        Matrix result = new Matrix(totalRows, totalCols);
        
        int rows11 = C11.getRows();
        int cols11 = C11.getCols();
        
        // Copy C11 (top-left)
        for (int i = 0; i < rows11; i++) {
            for (int j = 0; j < cols11; j++) {
                result.setValue(i, j, C11.getValue(i, j));
            }
        }
        
        // Copy C12 (top-right)
        for (int i = 0; i < C12.getRows(); i++) {
            for (int j = 0; j < C12.getCols(); j++) {
                result.setValue(i, j + cols11, C12.getValue(i, j));
            }
        }
        
        // Copy C21 (bottom-left)
        for (int i = 0; i < C21.getRows(); i++) {
            for (int j = 0; j < C21.getCols(); j++) {
                result.setValue(i + rows11, j, C21.getValue(i, j));
            }
        }
        
        // Copy C22 (bottom-right)
        for (int i = 0; i < C22.getRows(); i++) {
            for (int j = 0; j < C22.getCols(); j++) {
                result.setValue(i + rows11, j + cols11, C22.getValue(i, j));
            }
        }
        
        return result;
    }
    
    // Utility method to display matrix
    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8.2f", data[i][j]);
            }
            System.out.println();
        }
    }
    
    // Test method
    public static void main(String[] args) {
        // Create sample matrices
        Double[][] aData = {
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        };
        
        Double[][] bData = {
            {1.0, 2.0, 3.0, 4.0},
            {5.0, 6.0, 7.0, 8.0},
            {9.0, 10.0, 11.0, 12.0},
            {13.0, 14.0, 15.0, 16.0}
        };
        
        Matrix A = new Matrix(aData);
        Matrix B = new Matrix(bData);
        
        System.out.println("Matrix A:");
        A.display();
        
        System.out.println("\nMatrix B:");
        B.display();
        
        // Test regular multiplication
        System.out.println("\nRegular Multiplication Result:");
        Matrix regularResult = multiplyingMatrix(A, B);
        if (regularResult != null) {
            regularResult.display();
        }
        
        // Test parallel multiplication
        System.out.println("\nParallel Multiplication Result:");
        Matrix parallelResult = multiplyMatricesParallel(A, B);
        if (parallelResult != null) {
            parallelResult.display();
        }
        
        // Verify results are the same
        System.out.println("\nResults are equal: " + 
            (regularResult != null && parallelResult != null && 
             matricesEqual(regularResult, parallelResult)));
    }
    
    // Utility method to check if two matrices are equal
    private static boolean matricesEqual(Matrix m1, Matrix m2) {
        if (m1.getRows() != m2.getRows() || m1.getCols() != m2.getCols()) {
            return false;
        }
        
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getCols(); j++) {
                if (!m1.getValue(i, j).equals(m2.getValue(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
