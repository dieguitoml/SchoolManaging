package integracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import negocio.TEmpleados;
import negocio.TProfesor;

public class DAOEmpleadosImp implements DAOEmpleados{

	@Override
	public int createEmpleado(TEmpleados empleado) {
		File archivo = buscarArchivo(empleado);
		TEmpleados empleadoAniadir = buscarEmpleado(empleado, archivo);
		if(empleadoAniadir==null) {//no existe el empleado, por tanto lo añadimos
			try {	
				JSONObject archivoJSON = archivoJSON(archivo);

				archivoJSON.getJSONArray("Empleados").put(empleado.escribirEmpleado());

				writeJSON(archivoJSON, archivo);
				return 0;				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	@Override
	public int eliminateEmpleado(TEmpleados empleado) {
		File archivo = buscarArchivo(empleado);
		TEmpleados empleadoEliminar = buscarEmpleado(empleado, archivo);
		if(empleadoEliminar!=null) {
			try {
				JSONObject archivoJSON = archivoJSON(archivo);
				
				JSONObject joEmpleadoEliminar = empleadoEliminar.escribirEmpleado();
				JSONArray joArray = new JSONArray();

				joArray = archivoJSON.getJSONArray("Empleados");

				int i = 0;
				JSONArray joArrayNuevo = new JSONArray();
				while(i<joArray.length()) {
					if(!joArray.getJSONObject(i).getString("DNI").equals(joEmpleadoEliminar.getString("DNI"))) {
						joArrayNuevo.put(joArray.getJSONObject(i));
					}
					i++;
				}
				JSONObject archivoJSONNuevo = new JSONObject();
				archivoJSONNuevo.put("Empleados", joArrayNuevo);


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
	public TEmpleados buscarEmpleado(TEmpleados empleado) {
		File archivo = buscarArchivo(empleado);
		//si no existe, creo el archivo
		if(archivo == null) archivo = new File("ArchivosJSON/Empleados/"+empleado.escribirEmpleado().get("Cargo".toString())+".json");
		try {
			JSONObject archivoJSON = archivoJSON(archivo);

			boolean encontrado = false;
			JSONObject empleadoBuscar = empleado.escribirEmpleado();
			JSONArray joArray = new JSONArray();

			joArray = archivoJSON.getJSONArray("Empleados");


			int i = 0;
			JSONObject empleadoEncontrado = new JSONObject();
			while(i<joArray.length() && !encontrado) {
				if(joArray.getJSONObject(i).getString("DNI").equals(empleadoBuscar.getString("DNI"))) {
					encontrado = true;
					empleadoEncontrado = joArray.getJSONObject(i);
				}
				i++;
			}
			if(encontrado) {
				TEmpleados TEmpleadoBuscado;
				if(empleadoEncontrado.length()==4)TEmpleadoBuscado = new TEmpleados(empleadoEncontrado.getString("DNI"), 
						empleadoEncontrado.getString("Nombre"), empleadoEncontrado.getString("Apellidos"),
						empleadoEncontrado.getString("Cargo"));
				else TEmpleadoBuscado = new TProfesor(empleadoEncontrado.getString("DNI"), 
						empleadoEncontrado.getString("Nombre"), empleadoEncontrado.getString("Apellidos"),
						empleadoEncontrado.getString("Cargo"), empleadoEncontrado.getString("Departamento"));
				return TEmpleadoBuscado;
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	return null;
	}

	@Override
	public TEmpleados buscarEmpleado(TEmpleados empleado, File archivo) {
		if(archivo == null) archivo = new File("ArchivosJSON/Empleados/"+empleado.escribirEmpleado().get("Cargo".toString())+".json");
		try {
			JSONObject archivoJSON = archivoJSON(archivo);

			boolean encontrado = false;
			JSONObject empleadoBuscar = empleado.escribirEmpleado();
			JSONArray joArray = new JSONArray();

			joArray = archivoJSON.getJSONArray("Empleados");

			int i = 0;
			JSONObject empleadoEncontrado = new JSONObject();
			while(i<joArray.length() && !encontrado) {
				if(joArray.getJSONObject(i).getString("DNI").equals(empleadoBuscar.getString("DNI"))) {
					encontrado = true;
					empleadoEncontrado = joArray.getJSONObject(i);
				}
				i++;
			}
			if(encontrado) {//Mirar para TProfesor
				TEmpleados TEmpleadoBuscado;
				if(empleadoEncontrado.length()==4)TEmpleadoBuscado = new TEmpleados(empleadoEncontrado.getString("DNI"), 
						empleadoEncontrado.getString("Nombre"), empleadoEncontrado.getString("Apellidos"),
						empleadoEncontrado.getString("Cargo"));
				else TEmpleadoBuscado = new TProfesor(empleadoEncontrado.getString("DNI"), 
						empleadoEncontrado.getString("Nombre"), empleadoEncontrado.getString("Apellidos"),
						empleadoEncontrado.getString("Cargo"), empleadoEncontrado.getString("Departamento"));

				return TEmpleadoBuscado;
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	return null;
	}

	@Override
	public int modificarEmpleado(TEmpleados empleado) {
		File archivo = buscarArchivo(empleado);
		TEmpleados empleadoModificar = buscarEmpleado(empleado, archivo);
		
		if(empleadoModificar!=null) {//existe el empleado
			try {
				JSONObject archivoJSON = archivoJSON(archivo);
				
				JSONObject joAux=empleado.escribirEmpleado();
				
				//Reviso si no se ha introducido alguno de los campos a modificar
				String nombre = joAux.getString("Nombre");
				String apellidos = joAux.getString("Apellidos");
				
				String departamento = null;
				if (joAux.has("Departamento")) {
					 departamento = joAux.getString("Departamento");
					 if (departamento.equals("")) departamento = empleadoModificar.escribirEmpleado().getString("Nombre");
				}
	
				if(nombre.equals("")) nombre = empleadoModificar.escribirEmpleado().getString("Nombre");
				if(apellidos.equals("")) apellidos = empleadoModificar.escribirEmpleado().getString("Apellidos");

				

				TEmpleados empleadoAux;
				if(empleadoModificar.escribirEmpleado().length()==4)empleadoAux= new TEmpleados(joAux.getString("DNI"),
																	nombre, apellidos, joAux.getString("Cargo"));
				else empleadoAux = new TProfesor(joAux.getString("DNI"),
						nombre, apellidos, joAux.getString("Cargo"), departamento); 
				JSONObject joEmpleadoModificar = empleadoAux.escribirEmpleado();
				JSONArray joArray = new JSONArray();

				joArray = archivoJSON.getJSONArray("Empleados");



				int i = 0;
				boolean modificado = false;
				while(i<joArray.length() && !modificado) {
					if(joArray.getJSONObject(i).getString("DNI").equals(joEmpleadoModificar.getString("DNI"))) {
						joArray.put(i, joEmpleadoModificar);
						modificado = true;
					}
					i++;
				}

				JSONObject archivoJSONNuevo = new JSONObject();
				archivoJSONNuevo.put("Empleados", joArray);

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
	public List<TEmpleados> listarEmpleado() {
		List<TEmpleados> lista = new ArrayList<TEmpleados>();
		File directorioFile = new File("ArchivosJSON/Empleados");
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
			if(archivosJSON.getJSONObject(i).has("Empleados"))joArray = archivosJSON.getJSONObject(i).getJSONArray("Empleados");

			for(int j = 0;j<joArray.length();j++) {
				JSONObject joEnj = joArray.getJSONObject(j); 

				if(joEnj.length()==4)lista.add(new TEmpleados(joEnj.getString("DNI"), joEnj.getString("Nombre"),
										joEnj.getString("Apellidos"),joEnj.getString("Cargo")));
				else lista.add(new TProfesor(joEnj.getString("DNI"), joEnj.getString("Nombre"),
										joEnj.getString("Apellidos"),joEnj.getString("Cargo"), 
										joEnj.getString(("Departamento"))));
			}
		}
		return lista;
	}



	private File buscarArchivo(TEmpleados empleado) {
		File directorioFile = new File("ArchivosJSON/Empleados");
		File[] listaArchivos = directorioFile.listFiles();
		for(File archivo : listaArchivos) {
			if((empleado.escribirEmpleado().get("Cargo").toString()+".json").equals(archivo.getName())) return archivo;
		}
		return null;
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
}
