package com.linsolve.computing.components;


public class Identity extends SquareMatrix 
{
	
	public Identity(Integer n) throws Exception
	{
		Double[][] d1=new Double[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(i==j)
				{
					d1[i][j]= 1.0;
				}
				else
				{
					d1[i][j]=0.0;
				}
			}
			super.setD(d1);
		}
		// TODO Auto-generated constructor stub
	}
}