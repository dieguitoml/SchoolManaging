package integracion;

import java.io.File;
import java.util.List;

import negocio.TAlumno;

public interface DAOAlumno {
	public int createAlumno(TAlumno alumno);
	public int eliminateAlumno(TAlumno alumno);
	public TAlumno buscarAlumno(TAlumno alumno);
	public TAlumno buscarAlumno(TAlumno alumno, File archivo);
	int modificarAlumno(TAlumno alumno);
	List<TAlumno> listarAlumno();
}
