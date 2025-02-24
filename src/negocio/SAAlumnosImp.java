package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONTokener;

import integracion.DAOAlumno;
import integracion.FactoriaAbstractaIntegracion;

public class SAAlumnosImp implements SAAlumnos {
	@Override
	public int create(TAlumno alumno) {
		DAOAlumno dAlumno = FactoriaAbstractaIntegracion.getInstance().crearDAOAlumno();
		return dAlumno.createAlumno(alumno);
	}

	@Override
	public int eliminate(TAlumno alumno) {
		DAOAlumno dAlumno = FactoriaAbstractaIntegracion.getInstance().crearDAOAlumno();
		return dAlumno.eliminateAlumno(alumno);
	}

	@Override
	public TAlumno buscar(TAlumno alumno) {
		DAOAlumno dAlumno = FactoriaAbstractaIntegracion.getInstance().crearDAOAlumno();
		return dAlumno.buscarAlumno(alumno);
	}
	
	@Override
	public int modificar(TAlumno alumnos) {
		DAOAlumno dAlumno = FactoriaAbstractaIntegracion.getInstance().crearDAOAlumno();
		return dAlumno.modificarAlumno(alumnos);
	}

	@Override
	public List<TAlumno> listar() {
		DAOAlumno dAlumno = FactoriaAbstractaIntegracion.getInstance().crearDAOAlumno();
		return dAlumno.listarAlumno();
	}

}
