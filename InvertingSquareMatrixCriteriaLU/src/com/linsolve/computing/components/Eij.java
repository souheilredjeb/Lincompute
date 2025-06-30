package com.linsolve.computing.components;

public class Eij extends SquareMatrix 
{

	public Eij(Integer n,Integer i, Integer j) 
	{
		// TODO Auto-generated constructor stub
		Double[][] d =new Double[n][n];	
		for(int k=0;k<n;k++)
		{
			for(int l=0;l<n;l++)
			{
				if(k==i && l==j)
				{
					d[k][l]=1.0;
				}
				else
				{
					d[k][l]=0.0;
				}
			}
		}
		super.setD(d);
		super.setN(n);
	}

}