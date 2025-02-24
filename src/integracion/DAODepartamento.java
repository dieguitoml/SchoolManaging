package integracion;

import java.io.File;
import java.util.List;

import negocio.TDepartamento;

public interface DAODepartamento {	
	public int createDepartamento(TDepartamento td);
	public int eliminateDepartamento(TDepartamento td);
	public TDepartamento searchDepartamento(TDepartamento td);
	public List<TDepartamento> toListDepartamento();
	public void modifyDepartamento(TDepartamento td, int num); 	// La unica forma en la que se puede modificar 
	// Mi SA no llama a modify									// un departamento es añadiendo o eliminando profesores, 
																//num representa el numero de profesores eliminados o insertados 
	
}