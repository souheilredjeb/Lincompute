package com.linsolve.computing.components;



public class SquareMatrix 
{
	private Double[][] d;
    private Integer n;
    
    public SquareMatrix(Double[][] d, Integer n) throws Exception {
        if (d == null || n == null) {
            throw new Exception("Matrix data and size cannot be null");
        }
        if (d.length != n || (d.length > 0 && d[0].length != n)) {
            throw new Exception("Double[][] should have square structure");
        }
        this.d = new Double[n][n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.d[i][j] = d[i][j];
            }
        }
    }
    
    
	
    public SquareMatrix() {
    }

    public static void DisplayMatrix(SquareMatrix s) {
        for(int i = 0; i < s.getD().length; i++) {
            for(int j = 0; j < s.getD().length; j++) {
                System.out.print(s.getD()[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
    public Double[][] getD() {
        return d;
    }

    public void setD(Double[][] d) {
        this.d = d;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }


	
}
