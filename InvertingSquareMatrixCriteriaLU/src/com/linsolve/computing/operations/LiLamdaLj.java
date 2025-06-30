package com.linsolve.computing.operations;

import com.linsolve.computing.components.Eij;
import com.linsolve.computing.components.Identity;
import com.linsolve.computing.components.SquareMatrix;

public class LiLamdaLj  extends SquareMatrix
{

	public LiLamdaLj(Integer n,Integer i, Integer j, Double lamda) throws Exception
	{
		Identity id=new Identity(n);		
		Eij eij= new Eij(n,i,j);
		SquareMatrix s=new SquareMatrix();
		s=MatrixOperations.AddLamdaMatrix(id, eij, lamda);
		super.setD(s.getD());
		super.setN(n);
	}
	
	public LiLamdaLj()
	{
		// TODO Auto-generated constructor stub	
	}

}
