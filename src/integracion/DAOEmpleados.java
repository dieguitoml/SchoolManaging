package integracion;

import java.io.File;
import java.util.List;

import negocio.TEmpleados;

public interface DAOEmpleados {
	public int createEmpleado(TEmpleados empleado);
	public int eliminateEmpleado(TEmpleados empleado);
	public TEmpleados buscarEmpleado(TEmpleados empleado);
	public TEmpleados buscarEmpleado(TEmpleados empleado, File archivo);
	public int modificarEmpleado(TEmpleados empleado);
	public List<TEmpleados> listarEmpleado();
}
