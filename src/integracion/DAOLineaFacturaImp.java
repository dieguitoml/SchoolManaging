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
import negocio.TFactura;
import negocio.TLineaFactura;

public class DAOLineaFacturaImp implements DAOLineaFactura{

	public DAOLineaFacturaImp() {
		super();
	}
	
	/**
	 * Devuelve una cadena vacía si ha habido un error y el id de la linea de la factura si el proceso es correcto
	 */
	@Override
	public String create(TLineaFactura lf) {
		//Obtener file directory
		File directorioFile=new File("ArchivosJSON/Facturas/LineasFactura.json");
		//Nunca se puede crear una linea de factura repetida, siempre va a existir el archivo
		try {
			//directorio: leer los datos que ya estan
			InputStream input;
			input = new FileInputStream(directorioFile);
			JSONObject jsonInput = new JSONObject(new JSONTokener(input));
			//crear nuevo JSONObject de la linea de factura
			int ultimoID = jsonInput.getInt("Id ultimo añadido");
			JSONObject j= lf.escribirJSON(ultimoID);
			//Añadir al JSON al input
			jsonInput.getJSONArray("Lineas de Facturas").put(j);
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
	 * Devuelve una lista de las lineas de una factura o null si no encuentra
	 */
	@Override
	public List<TLineaFactura> buscar(String IDFactura) {
		//Obtener file directory
		File dirFile=new File("ArchivosJSON/Facturas/LineasFactura.json");
		if(dirFile!=null) {//Existe la file
			try {
				//Crear input stream con el directorio
				InputStream input;
				input = new FileInputStream(dirFile);
				JSONObject jsonInput = new JSONObject(new JSONTokener(input));
				JSONArray facturas=jsonInput.getJSONArray("Lineas de Facturas");
				List<TLineaFactura> lista = new ArrayList<TLineaFactura>();
				for(int i=0;i<facturas.length();i++) {
					if(facturas.getJSONObject(i).get("ID Factura").equals(IDFactura)){	//es la factura que buscamos
						JSONObject lineaFactura = facturas.getJSONObject(i);
						lista.add(JSONtoLineaFactura(lineaFactura));
					}
				}
				if(!lista.isEmpty())return lista;
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private TLineaFactura JSONtoLineaFactura(JSONObject lineaFactura) {
		TLineaFactura lineaFact = new TLineaFactura(
				lineaFactura.getString("ID"), 
				lineaFactura.getString("ID Factura"),
				lineaFactura.getString("ID servicio")); 
		return lineaFact;
	}
	
	

}
