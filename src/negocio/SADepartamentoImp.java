package negocio;

import java.util.List;

import integracion.DAODepartamento;
import integracion.DAOEmpleados;
import integracion.DAOServicios;
import integracion.FactoriaAbstractaIntegracion;

public class SADepartamentoImp implements SADepartamento{

	@Override
	public int create(TDepartamento tDepartamento) { //Devuelve 0 si se ha anadido, 1 si no se ha podido anadir
		DAODepartamento DAOD=FactoriaAbstractaIntegracion.getInstance().crearDAODepartamento();
		return DAOD.createDepartamento(tDepartamento);
	}

	@Override
	public int eliminate(TDepartamento tDepartamento) {
		DAODepartamento DAOD=FactoriaAbstractaIntegracion.getInstance().crearDAODepartamento();
		DAOEmpleados dEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleados();
		List<TEmpleados> Lista_empleados = dEmpleado.listarEmpleado();
		for (TEmpleados empleado: Lista_empleados) {
			if (empleado.escribirEmpleado().getString("Departamento").equals(tDepartamento.escribirDepartamentoID().get("Nombre")) || 	// porque se puede eliminar por nombre o por id y por id no se tiene cual es el nombre del depart
					(empleado.escribirEmpleado().getString("Departamento").equals(DAOD.searchDepartamento(tDepartamento).escribirDepartamentoID().get("Nombre"))))
				dEmpleado.modificarEmpleado(new TProfesor(empleado.escribirEmpleado().getString("DNI"), empleado.escribirEmpleado().getString("Nombre"),
						empleado.escribirEmpleado().getString("Apellidos"),empleado.escribirEmpleado().getString("Cargo"),"SIN DEPARTAMENTO"));
		}
	
		return DAOD.eliminateDepartamento(tDepartamento);
	}

	@Override
	public TDepartamento search(TDepartamento tDepartamento) {
		// TODO Auto-generated method stub
		DAODepartamento DAOD=FactoriaAbstractaIntegracion.getInstance().crearDAODepartamento();
		TDepartamento a=DAOD.searchDepartamento(tDepartamento);
			return a;
		}
	

	@Override
	public List<TDepartamento> toList() {
		// TODO Auto-generated method stub
		DAODepartamento DAOD=FactoriaAbstractaIntegracion.getInstance().crearDAODepartamento();
		return DAOD.toListDepartamento();
	}

	
	

}
