package com.lincompute.multiplication.components;
 

public class SquareMatrix 
{
	 private Double[][] m;

	 public SquareMatrix(int rows) 
	 {
	        this.m = new Double[rows][rows];
	 }

	public SquareMatrix() 
	{
		// TODO Auto-generated constructor stub
	}
	
	   public static Boolean compareSquareMatrix(SquareMatrix m1, SquareMatrix m2)
	    {
	    	if(m1.getM()!=null && m2.getM()!=null)
	    	{
	    		for(int i=0;i<m1.getM().length;i++)
	    		{
	    			for(int j=0;j<m1.getM()[0].length;j++)
	    			{
	    				// double rounded = Math.round(143.10000000000002 * 1000000.0)/1000000.0;
	    				if(Math.round(m1.getM()[i][j].doubleValue()*1000000.0)/1000000.0 !=Math.round(m2.getM()[i][j].doubleValue()*1000000.0)/1000000.0)
	    				{
	    					return Boolean.FALSE;
	    				}
	    			}
	    		}	
	    		return Boolean.TRUE;	
	    	}
	    	
	    	return Boolean.FALSE;
	    }
	   
	public SquareMatrix(Double[][] m)
	{
		super();
		this.m = m;
	}
	
	public Double[][] getM() {
		return m;
	}

	public void setM(Double[][] m) {
		this.m = m;
	}
	
	 public static void display_matrix(SquareMatrix m)
	 {
	    	Double[][] m1=m.getM();
	    	for(int i=0;i<m1.length;i++)
	    	{
	    		for(int j=0;j<m1[0].length;j++)
	    		{
	    			System.out.print(m1[i][j]+ " | ");
	    		}
	    		System.out.println();
	    	}   
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
