package negocio;

public class FactoriaNegocio extends FactoriaAbstractaNegocio{

	@Override
	public SAServicios crearSAServicio() {
		return new SAServiciosImp();
	}
	@Override
	public SAFactura crearSAFactura() {
		return new SAFacturaImp();
	}
	
	@Override
	public SAEmpleados crearSAEmpleados() {
		return new SAEmpleadosImp();
	}
	
	@Override
	public SAAlumnos crearSAAlumnos() {
		return new SAAlumnosImp();
	}
	@Override
	public SADepartamento crearSADepartamento() {
		return new SADepartamentoImp(); 
	}
}
