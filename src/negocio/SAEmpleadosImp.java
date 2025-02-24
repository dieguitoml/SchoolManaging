package negocio;

import java.util.List;

import integracion.DAODepartamento;
import integracion.DAOEmpleados;
import integracion.FactoriaAbstractaIntegracion;

public class SAEmpleadosImp implements SAEmpleados{

	@Override
	public int create(TEmpleados empleados) {
		DAOEmpleados dEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleados();
		if (empleados.escribirEmpleado().has("Departamento")) {
			DAODepartamento dDepartamento = FactoriaAbstractaIntegracion.getInstance().crearDAODepartamento();
			if(dDepartamento.searchDepartamento(new TDepartamento(empleados.escribirEmpleado().getString("Departamento"))) == null) 
				return 0;
			dDepartamento.modifyDepartamento(new TDepartamento(empleados.escribirEmpleado().getString("Departamento")),1);
		}
		return dEmpleado.createEmpleado(empleados);
	}

	@Override
	public int eliminate(TEmpleados empleados) {
		DAOEmpleados dEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleados();
		DAODepartamento dDepartamento = FactoriaAbstractaIntegracion.getInstance().crearDAODepartamento();
		if (dEmpleado.buscarEmpleado(empleados).escribirEmpleado().has("Departamento")) 	//Este TEmpleado no tiene departamento, lo busco 
			dDepartamento.modifyDepartamento(new TDepartamento(dEmpleado.buscarEmpleado(empleados).escribirEmpleado().getString("Departamento")),-1);
		return dEmpleado.eliminateEmpleado(empleados);
	}

	@Override
	public TEmpleados buscar(TEmpleados empleados) {
		DAOEmpleados dEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleados();
		return dEmpleado.buscarEmpleado(empleados);
	}

	@Override
	public int modificar(TEmpleados empleados) {
		DAOEmpleados dEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleados();
		return dEmpleado.modificarEmpleado(empleados);
	}

	@Override
	public List<TEmpleados> listar() {
		DAOEmpleados dEmpleado = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleados();
		return dEmpleado.listarEmpleado();
	}

}
