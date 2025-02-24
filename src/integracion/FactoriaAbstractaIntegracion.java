package integracion;

public abstract class FactoriaAbstractaIntegracion {//ES UN SINGLETON LO QUE SIGNIFICA QUE HAY UNA UNICA INSTANCIA PARA TODO EL PROGRAMA
	
	private static FactoriaAbstractaIntegracion instancia;
	
	
	public static FactoriaAbstractaIntegracion getInstance() {
		if(instancia==null) {
			instancia= new FactoriaIntegracion();
		}
		return instancia;
	}
	
	public abstract DAOServicios crearDAOServicios();

	public abstract DAOFactura crearDAOFactura();
	
	public abstract DAOLineaFactura crearDAOLineaFactura();
	
	public abstract DAOEmpleados crearDAOEmpleados();
	
	public abstract DAOAlumno crearDAOAlumno();

	public abstract DAOAlumnoServicio crearDAOAlumnoServicio();
	
	public abstract DAODepartamento crearDAODepartamento(); 

}
