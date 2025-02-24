package integracion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.TAlumno;
import negocio.TEmpleados;
import negocio.TProfesor;
import negocio.TAlumno;

public class DAOAlumnoImp implements DAOAlumno{
	
	DAOAlumnoImp(){
		super();
	}
	
	@Override
	public int createAlumno(TAlumno alumno) {
		File archivo = buscarArchivo(alumno);
		TAlumno alumnoAniadir = buscarAlumno(alumno, archivo);
		if(alumnoAniadir==null) {
			try {	
				JSONObject archivoJSON = archivoJSON(archivo);

				archivoJSON.getJSONArray("Alumnos").put(alumno.escribirAlumno());

				writeJSON(archivoJSON, archivo);
				return 0;				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	public int eliminateAlumno(TAlumno alumno) {
		File archivo = buscarArchivo(alumno);
		TAlumno alumnoEliminar = buscarAlumno(alumno, archivo);
		if(alumnoEliminar!=null) {
			try {
				JSONObject archivoJSON = archivoJSON(archivo);
				JSONObject joAlumnoEliminar = alumnoEliminar.escribirAlumno();
				JSONArray joArray = new JSONArray();

				joArray = archivoJSON.getJSONArray("Alumnos");

				int i = 0;
				JSONArray joArrayNuevo = new JSONArray();
				while(i<joArray.length()) {
					if(!joArray.getJSONObject(i).getString("DNI").equals(joAlumnoEliminar.getString("DNI"))) {
						joArrayNuevo.put(joArray.getJSONObject(i));
					}
					i++;
				}
				JSONObject archivoJSONNuevo = new JSONObject();
				archivoJSONNuevo.put("Alumnos", joArrayNuevo);


				//Escribimos la lista entera en el archivo sobreescribiendo lo anterior
				writeJSON(archivoJSONNuevo, archivo);
				return 0;
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	public TAlumno buscarAlumno(TAlumno alumno) {
		File archivo = buscarArchivo(alumno);
		//si no existe, creo el archivo
		if(archivo == null) archivo = new File("ArchivosJSON/Alumnos"+alumno.escribirAlumno().get("Curso".toString())+".json");
		try {
			JSONObject archivoJSON = archivoJSON(archivo);

			boolean encontrado = false;
			JSONObject alumnoBuscar = alumno.escribirAlumno();
			JSONArray joArray = new JSONArray();

			joArray = archivoJSON.getJSONArray("Alumnos");


			int i = 0;
			JSONObject alumnoEncontrado = new JSONObject();
			while(i<joArray.length() && !encontrado) {
				if(joArray.getJSONObject(i).getString("DNI").equals(alumnoBuscar.getString("DNI"))) {
					encontrado = true;
					alumnoEncontrado = joArray.getJSONObject(i);
				}
				i++;
			}
			if(encontrado) {
				TAlumno TAlumnoBuscado;
				TAlumnoBuscado = new TAlumno(alumnoEncontrado.getString("DNI"), 
						alumnoEncontrado.getString("Nombre"), alumnoEncontrado.getString("Apellidos"),
						alumnoEncontrado.getString("Curso"));
				return TAlumnoBuscado;
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	return null;
	}
	
	@Override
	public TAlumno buscarAlumno(TAlumno alumno, File archivo) {
		if(archivo == null) archivo = new File("ArchivosJSON/Alumnos"+ alumno.escribirAlumno().get("Curso".toString())+".json");
		try {
			JSONObject archivoJSON = archivoJSON(archivo);

			boolean encontrado = false;
			JSONObject alumnoBuscar = alumno.escribirAlumno();
			JSONArray joArray = new JSONArray();

			joArray = archivoJSON.getJSONArray("Alumnos");

			int i = 0;
			JSONObject alumnoEncontrado = new JSONObject();
			while(i<joArray.length() && !encontrado) {
				if(joArray.getJSONObject(i).getString("DNI").equals(alumnoBuscar.getString("DNI"))) {
					encontrado = true;
					alumnoEncontrado = joArray.getJSONObject(i);
				}
				i++;
			}
			if(encontrado) {
				TAlumno TAlumnoBuscado;
				TAlumnoBuscado = new TAlumno(alumnoEncontrado.getString("DNI"), 
						alumnoEncontrado.getString("Nombre"), alumnoEncontrado.getString("Apellidos"),
						alumnoEncontrado.getString("Curso"));

				return TAlumnoBuscado;
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	return null;
	}
	
	@Override
	public int modificarAlumno(TAlumno alumno) {
		File archivo = buscarArchivo(alumno);
		TAlumno alumnoModificar = buscarAlumno(alumno, archivo);
		
		if(alumnoModificar!=null) {//existe el alumno
			try {
				JSONObject archivoJSON = archivoJSON(archivo);
				
				JSONObject joAux=alumno.escribirAlumno();
				
				//Reviso si no se ha introducido alguno de los campos a modificar
				String nombre = joAux.getString("Nombre");
				String apellidos = joAux.getString("Apellidos");
								if(nombre.equals("")) nombre = alumnoModificar.escribirAlumno().getString("Nombre");
				if(apellidos.equals("")) apellidos = alumnoModificar.escribirAlumno().getString("Apellidos");

				TAlumno alumnoAux = new TAlumno(joAux.getString("DNI"), nombre, apellidos, joAux.getString("Curso"));
				JSONObject joAlumnoModificar = alumnoAux.escribirAlumno();
				JSONArray joArray = new JSONArray();
				
				joArray = archivoJSON.getJSONArray("Alumnos");
				
				
				
				int i = 0;
				boolean modificado = false;
				while(i<joArray.length() && !modificado) {
					if(joArray.getJSONObject(i).getString("DNI").equals(joAlumnoModificar.getString("DNI"))) {
						joArray.put(i, joAlumnoModificar);
						modificado = true;
					}
					i++;
				}
				
				JSONObject archivoJSONNuevo = new JSONObject();
				archivoJSONNuevo.put("Alumnos", joArray);
				
				writeJSON(archivoJSONNuevo, archivo);
				return 0;
				} catch(FileNotFoundException e) {
				e.printStackTrace();
				} catch (IOException e1) {
				e1.printStackTrace();
				}
		}
		return 1;
	}
	
	@Override
	public List<TAlumno> listarAlumno() {
		List<TAlumno> lista = new ArrayList<TAlumno>();
		File directorioFile = new File("ArchivosJSON/Alumnos");
		File[] listaArchivos = directorioFile.listFiles();
		JSONArray archivosJSON = new JSONArray();//JsonArray con todos los jsons con toda la informacion de cada archivo
		for(File archivo : listaArchivos) {
			try {
				archivosJSON.put(archivoJSON(archivo));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		//añadimos a la lista todos los jsons de cada uno de los archivos

		for(int i = 0;i<archivosJSON.length();i++) {
			JSONArray joArray = new JSONArray();
			if(archivosJSON.getJSONObject(i).has("Alumnos"))joArray = archivosJSON.getJSONObject(i).getJSONArray("Alumnos");

			for(int j = 0;j<joArray.length();j++) {
				JSONObject joEnj = joArray.getJSONObject(j);
				lista.add(new TAlumno(joEnj.getString("DNI"), joEnj.getString("Nombre"),
										joEnj.getString("Apellidos"),joEnj.getString("Curso")));
			}
		}
		return lista;
	}
	
	private JSONObject archivoJSON(File archivo) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream(archivo));
		String datosArchivo = sc.nextLine();
		JSONObject archivoJSON = new JSONObject(datosArchivo);
		return archivoJSON;
	}
	
	private void writeJSON(JSONObject escribir, File archivo) {
		try {
			OutputStream out = new FileOutputStream(archivo);
			PrintStream p = new PrintStream(out);
			p.println(escribir.toString());
			p.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File buscarArchivo(TAlumno alumno) {
		File directorioFile = new File("ArchivosJSON/Alumnos");
		File[] listaArchivos = directorioFile.listFiles();
		for(File archivo : listaArchivos) {
			if((alumno.escribirAlumno().get("Curso").toString()+".json").equals(archivo.getName())) return archivo;
		}
		return null;
	}

}
