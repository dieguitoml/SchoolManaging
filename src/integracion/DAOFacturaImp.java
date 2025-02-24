package integracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.Fecha;
import negocio.TDatosFactura;
import negocio.TFactura;
import negocio.TServicios;

public class DAOFacturaImp implements DAOFactura {

	DAOFacturaImp(){
		super();
	}

	@Override
	/**
	 * Devuelve una cadena vacía si ha habido un error y el id de la factura si el proceso es correcto
	 */
	public String create(TFactura tf) {
		//Obtener file directory
		File directorioFile=new File("ArchivosJSON/Facturas/Facturas.json");
		//Nunca se puede crear una factura repetida, siempre va a existir el archivo
		try {
			//directorio: leer los datos que ya estan
			InputStream input;
			input = new FileInputStream(directorioFile);
			JSONObject jsonInput = new JSONObject(new JSONTokener(input));
			//crear nuevo JSONObject factura
			int ultimoID = jsonInput.getInt("Id ultimo añadido");
			JSONObject j= tf.escribirJSON(ultimoID);
			//Añadir al JSON al input
			jsonInput.getJSONArray("Facturas").put(j);
			jsonInput.put("Id ultimo añadido",ultimoID+1);
			//Escribir los datos del JSON de nuevo en el directorio
			OutputStream output;
			output = new FileOutputStream(directorioFile);
			PrintStream p = new PrintStream(output);
			p.println(jsonInput.toString());
		    output.close();  
		    return Integer.toString(ultimoID+1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return " ";
	}
	/**
	 * Devueleve una lista de las facturas o null si hay un fallo
	 */
	@Override
	public List<TFactura> listar() {
		//Obtener file directory
		File dirFile=new File("ArchivosJSON/Facturas/Facturas.json");
		if(dirFile!=null) {//Existe la file
			try {
				//Crear input stream con el directorio
				InputStream input;
				input = new FileInputStream(dirFile);
				JSONObject jsonInput = new JSONObject(new JSONTokener(input));
				//Comprobar si existe la factura
				JSONArray facturas=jsonInput.getJSONArray("Facturas");
				List<TFactura> lista = new ArrayList<TFactura>();
				for(int i=0;i<facturas.length();i++) {
					JSONObject factura = facturas.getJSONObject(i);
					lista.add(JSONtoFactura(factura));
				}
				return lista;
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Devuelve una factura o null si no la encuentra
	 */
	@Override
	public TFactura buscar(String id) {
		//Obtener file directory
		File dirFile=new File("ArchivosJSON/Facturas/Facturas.json");
		if(dirFile!=null) {//Existe la file
			try {
				//Crear input stream con el directorio
				InputStream input;
				input = new FileInputStream(dirFile);
				JSONObject jsonInput = new JSONObject(new JSONTokener(input));
				JSONArray facturas=jsonInput.getJSONArray("Facturas");
				for(int i=0;i<facturas.length();i++) {
					if(facturas.getJSONObject(i).get("ID").equals(id)){	//es la factura que buscamos
						JSONObject factura = facturas.getJSONObject(i);
						return JSONtoFactura(factura);
					}
				}
				return null;
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private TFactura JSONtoFactura(JSONObject factura) {
		JSONArray date = factura.getJSONArray("Fecha");
		TFactura Fact = new TFactura(Integer.toString(factura.getInt("ID")), //ID
				factura.getString("ID Alumno"),//ID Alumno
				factura.getString("ID Secretaria"), //ID Secretaria
				new Fecha(date.getInt(0), date.getInt(1),date.getInt(2)),//Fecha
				factura.getDouble("Precio Total") //precio
				);
		return Fact;
	}
}
