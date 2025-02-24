package integracion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.TDepartamento;
import negocio.TEmpleados;
import negocio.TServicios;

public class DAODepartamentoImp implements DAODepartamento {
	@Override
	public int createDepartamento(TDepartamento td) {
		File file = searchFile();
		TDepartamento dep =searchDepartamento(td); 
		if (dep == null || td.escribirDepartamentoID().getString("Nombre") != "") {
	    try {
	        // Leer el contenido del archivo y convertirlo en un objeto JSON
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        StringBuilder jsonBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            jsonBuilder.append(line);
	        }
	        reader.close();
	        
	        
	        JSONObject json = new JSONObject(jsonBuilder.toString());
	        
	        // Obtener el último ID asignado
	        int ultimoID = json.optInt("ultimo_id", 0);
	        
	        // Obtener el arreglo del objeto JSON
	        JSONArray array = json.getJSONArray("departamentos");

	        // Buscar si el departamento ya existe, pero esta a dfalse
	        boolean found = false;
	        for (int i = 0; i < array.length(); i++) {
	            JSONObject obj = array.getJSONObject(i);
	            if (obj.getString("Nombre").equals(td.escribirDepartamentoID().get("Nombre")) && !obj.getBoolean("Estado")) {
	                // Si el departamento ya existe, actualizar su estado
	                obj.put("Estado", true);
	                found = true;
	                break;
	            }
	        }

	        if (!found) {
	            // Si el departamento no existe, añadirlo
	            JSONObject newObject = td.escribirDepartamentoID();
	            newObject.put("ID", ultimoID);
	            array.put(newObject);
	            json.put("ultimo_id", ultimoID + 1);
	        }

	        // Escribir el objeto JSON actualizado en el archivo
	        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	        writer.write(json.toString());
	        writer.close();

	        return 0; // Se ha añadido el departamento correctamente
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		}
	        return 1; // Ha habido un error al añadir el departamento o ya existia
	    
	}


	
	public File searchFile() {
		File file = new File("ArchivosJSON/Departamentos/Departamento.json");
		if (!file.exists() ) {
			try {
				file.createNewFile();
				inicializarJSON(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return file; 
		} 
		else if (file.length()==0) {
			try {
				file.createNewFile();
				inicializarJSON(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return file; 
		}
		        return file;
	
	}

	@Override
	public int eliminateDepartamento(TDepartamento td) {
		File file= searchFile();
		if(searchDepartamento(td) != null ) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
		        StringBuilder jsonBuilder = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            jsonBuilder.append(line);
		        }
		        reader.close();

		        JSONObject json = new JSONObject(jsonBuilder.toString());

		        // Obtener el arreglo del objeto JSON
		        JSONArray array = json.getJSONArray("departamentos");

		        // Buscar si el departamento ya existe en el arreglo
		        for (int i = 0; i < array.length(); i++) {
		            JSONObject obj = array.getJSONObject(i);
		            if ((obj.getString("Nombre").equals(td.escribirDepartamentoID().get("Nombre")) || obj.getInt("ID") == (td.escribirDepartamentoID().getInt("ID")) )  && obj.getBoolean("Estado")) {
		                // Si el departamento ya existe en el arreglo, actualizar su estado
		                obj.put("Estado", false);
		                obj.put("num_profesores", 0);
		            }
		        }
		        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		        writer.write(json.toString());
		        writer.close();
		        return 0;
		      
		        
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
		return 1;
	}

	@Override
	public TDepartamento searchDepartamento(TDepartamento td) {
		File file= searchFile();
		if( file.length() != 0 ) {
			try {
				String jsonString = new String(Files.readAllBytes(file.toPath()));
	            // Parsear la cadena de texto como un objeto JSON
	            JSONObject jsonObject = new JSONObject(jsonString);
	            // Buscar el departamento en el objeto JSON
	            JSONArray departamentosArray = jsonObject.getJSONArray("departamentos");
	            for (int i = 0; i < departamentosArray.length(); i++) {
	                JSONObject departamentoObject = departamentosArray.getJSONObject(i);
	                if ((departamentoObject.getString("Nombre").equals(td.escribirDepartamentoID().get("Nombre")) || departamentoObject.getInt("ID") == (td.escribirDepartamentoID().getInt("ID")) ) && departamentoObject.getBoolean("Estado")) {
        				return new TDepartamento(departamentoObject.getInt("ID"),departamentoObject.getString("Nombre"));
 
	                }
	            }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
		return null;
	}


	@Override
	public List<TDepartamento> toListDepartamento() {
		File file= searchFile();
		if( file.length() != 0 ) {
			try { 
				List <TDepartamento> lista= new ArrayList<TDepartamento>();
				String jsonString = new String(Files.readAllBytes(file.toPath()));
	            // Parsear la cadena de texto como un objeto JSON
	            JSONObject jsonObject = new JSONObject(jsonString);
	            // Buscar el departamento en el objeto JSON
	            JSONArray departamentosArray = jsonObject.getJSONArray("departamentos");
	            for (int i = 0; i < departamentosArray.length(); i++) {
	                JSONObject obj = departamentosArray.getJSONObject(i);
	                if (obj.getBoolean("Estado"))
	                    lista.add(new TDepartamento(obj.getInt("ID"),obj.getString("Nombre"),obj.getInt("num_profesores")));      
	            }
	            return lista;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
		return null;
	}



	@Override
	public void modifyDepartamento(TDepartamento td, int num) {	
		File file= searchFile();
		if(searchDepartamento(td) != null ) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
		        StringBuilder jsonBuilder = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            jsonBuilder.append(line);
		        }
		        reader.close();

		        JSONObject json = new JSONObject(jsonBuilder.toString());

		        // Obtener el arreglo del objeto JSON
		        JSONArray array = json.getJSONArray("departamentos");
		        // Buscar si el departamento ya existe en el arreglo
		        for (int i = 0; i < array.length(); i++) {
		            JSONObject obj = array.getJSONObject(i);
		            if (obj.getString("Nombre").equals(td.escribirDepartamentoID().getString("Nombre")) && obj.getBoolean("Estado")) {
		                // Si el departamento ya existe en el arreglo, actualizar su estado
		            	  obj.put("num_profesores",obj.getInt("num_profesores")+num);
		            }
		        }
		        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		        writer.write(json.toString());
		        writer.close();
		       
		      
		        
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	}
	
	private void inicializarJSON (File file) throws IOException {
		JSONObject json = new JSONObject();
	     JSONArray departamentos = new JSONArray();
	     json.put("departamentos", departamentos);
	     json.put("ultimo_id", 0);
	     FileWriter writer = new FileWriter(file);
	     writer.write(json.toString());
	     writer.flush();
	     writer.close();

	}
	
}