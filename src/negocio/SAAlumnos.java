package negocio;

import java.util.List;

public interface SAAlumnos {
	public int create(TAlumno alumnos);
	public int eliminate(TAlumno alumnos);
	public TAlumno buscar(TAlumno alumnos);
	public int modificar(TAlumno alumnos);
	public List<TAlumno> listar();
}
