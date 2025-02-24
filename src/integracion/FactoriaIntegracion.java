package integracion;

public class FactoriaIntegracion extends FactoriaAbstractaIntegracion{

	@Override
	public DAOServicios crearDAOServicios() {
		// TODO Auto-generated method stub
		return new DAOServiciosImp();
	}

	@Override
	public DAOFactura crearDAOFactura() {
		return new DAOFacturaImp();
	}
	
	@Override
	public DAOLineaFactura crearDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}
	
	@Override
	public DAOEmpleados crearDAOEmpleados() {
		return new DAOEmpleadosImp();
	}

	@Override
	public DAOAlumno crearDAOAlumno() {
		return new DAOAlumnoImp();
	}

	@Override
	public DAOAlumnoServicio crearDAOAlumnoServicio() {
		return new DAOAlumnoServicioImp() ;
	}
	
	@Override
	public DAODepartamento crearDAODepartamento() {
		return new DAODepartamentoImp();
	}

	
	

}
