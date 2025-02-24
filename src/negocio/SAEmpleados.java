package negocio;

import java.util.List;

public interface SAEmpleados {
	public int create(TEmpleados empleados);
	public int eliminate(TEmpleados empleados);
	public TEmpleados buscar(TEmpleados empleados);
	public int modificar(TEmpleados empleados);
	public List<TEmpleados> listar();
}
