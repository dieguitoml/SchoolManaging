package negocio;

public abstract class FactoriaAbstractaNegocio {//ES UN SINGLETON LO QUE SIGNIFICA QUE HAY UNA UNICA INSTANCIA PARA TODO EL PROGRAMA
	
	private static FactoriaAbstractaNegocio instancia;
	
	public static FactoriaAbstractaNegocio getInstance() {
		if(instancia==null) {
			instancia= new FactoriaNegocio();
		}
		return instancia;
	}
	
	public abstract SAServicios crearSAServicio();
	
	public abstract SAFactura crearSAFactura();

	public abstract SAEmpleados crearSAEmpleados();
	
	public abstract SAAlumnos crearSAAlumnos();
	
	public abstract SADepartamento crearSADepartamento();

}
