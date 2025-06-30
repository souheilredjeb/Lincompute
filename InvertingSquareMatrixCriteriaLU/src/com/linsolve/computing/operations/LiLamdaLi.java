package com.linsolve.computing.operations;

import com.linsolve.computing.components.Eij;
import com.linsolve.computing.components.Identity;
import com.linsolve.computing.components.SquareMatrix;

public class LiLamdaLi extends SquareMatrix
{
	
	public LiLamdaLi(Integer n, Integer i, Double lamda) throws Exception 
	{
		Identity id=new Identity(n);		
		Eij eii= new Eij(n,i,i);
		SquareMatrix s=new SquareMatrix();
		s=MatrixOperations.AddLamdaMatrix(id, eii,-1.0);
		s=MatrixOperations.AddLamdaMatrix(s, eii, lamda);
		super.setD(s.getD());
		super.setN(n);
	}

	public LiLamdaLi()
	{
		// TODO Auto-generated constructor stub
	}

}