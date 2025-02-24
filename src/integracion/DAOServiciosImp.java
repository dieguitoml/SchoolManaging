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

import negocio.TServicios;

public class DAOServiciosImp implements DAOServicios{
	@Override
	public int createServicio(TServicios ts){//Devuelve 0 si se ha podido anadir 1 si no se ha podido anadir
		File s=buscarArchivo(ts);
		TServicios service=buscarServicio(ts);//Busco si existe ya o no el servicio especificado
		String se="ArchivosJSON/Servicios/"+ts.escribirServicioId().get("Nombre servicio").toString()+".json";
		if(s==null) {//Si no existe ya el servicio ni el archivo, se crea uno nuevo
		try {
		OutputStream output=new FileOutputStream(new File(se));
		PrintStream p = new PrintStream(output);
		JSONObject a=new JSONObject();
		a.put("Servicio", ts.escribirServicioId().get("Nombre servicio"));
		a.put("Id último añadido", 1);
		JSONArray arr=new JSONArray();
		JSONObject j=ts.escribirServicioId();
		j.put("Id", 1);
		arr.put(j);
		a.put("Tipos", arr);
		p.println(a.toString());
	    output.close();  
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return 0;
		}
		else if(service==null) {//En el caso de que exista el archivo, pero aun no se haya creado ese servicio
		try {
			InputStream input;
			input = new FileInputStream(se);
			JSONObject jsonInput = new JSONObject(new JSONTokener(input));
			JSONObject j=ts.escribirServicioId();
			j.put("Id", jsonInput.getInt("Id último añadido")+1);
			jsonInput.getJSONArray("Tipos").put(j);
			int ult=jsonInput.getInt("Id último añadido");
			jsonInput.put("Id último añadido",ult+1);
			OutputStream output;
			output = new FileOutputStream(new File(se));
			PrintStream p = new PrintStream(output);
			p.println(jsonInput.toString());
		    output.close();  
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return 0;
		}else {//Si ya esta el servicio en la base de datos, compruebo que este a true
			try {
				InputStream input;
				input = new FileInputStream(se);
				JSONObject jsonInput = new JSONObject(new JSONTokener(input));
				if(service.escribirServicioId().getBoolean("Estado")==false) {//Existe en la bdd, pero no activo
					JSONObject c=service.escribirServicioId();
					c.put("Estado",true);
					c.put("Precio de cada plaza", ts.escribirServicioId().get("Precio de cada plaza"));
					c.put("Número de plazas disponibles", ts.escribirServicioId().get("Número de plazas disponibles"));
					jsonInput.getJSONArray("Tipos").put(c.getInt("Id")-1,c);
					OutputStream output;
					output = new FileOutputStream(new File(se));
					PrintStream p = new PrintStream(output);
					p.println(jsonInput.toString());
				    output.close();
					return 0;
				}else {//Existe y está activo
					return 1;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		return 1;
	}
	
	public File buscarArchivo(TServicios ts) {//Busca si ya existe el servicio en la base de datos
		File directorioFile=new File("ArchivosJSON/Servicios");
		File[] lista = directorioFile.listFiles();//Se ha podido añadir
		if(lista!=null) {
			for(File elemento: lista) {
				if((ts.escribirServicioId().get("Nombre servicio").toString() +".json").equals(elemento.getName())) {//El servicio ya existe, no se puede añadir dos veces
					return elemento;
				}
			}
		}
		return null;
	}
	

	@Override
	public int eliminateServicio(TServicios ts) {
		File arch=buscarArchivo(ts);
		String se="ArchivosJSON/Servicios/"+ts.escribirServicioId().get("Nombre servicio").toString()+".json";
		TServicios service=buscarServicio(ts);
		if(arch==null || service==null) {//No existe dicho servicio
			return 1;
		}else {
			try {
				InputStream input;
				input = new FileInputStream(arch);
				JSONObject jsonInput = new JSONObject(new JSONTokener(input));
				JSONArray a=jsonInput.getJSONArray("Tipos");
				JSONObject b=a.getJSONObject(service.escribirServicioId().getInt("Id")-1);
				if((b.get("Estado").toString()).equals("true")) {//Existe en la bdd, pero no activo
					JSONObject o=new JSONObject();
					o.put("Estado", false);
					o.put("Nombre servicio", b.get("Nombre servicio"));
					o.put("Subtipo", b.get("Subtipo"));
					o.put("Precio de cada plaza", b.get("Precio de cada plaza"));
					o.put("Número de plazas disponibles", b.get("Número de plazas disponibles"));
					o.put("Id", b.get("Id"));
					jsonInput.getJSONArray("Tipos").put(service.escribirServicioId().getInt("Id")-1,o);
					OutputStream output;
					output = new FileOutputStream(new File(se));
					PrintStream p = new PrintStream(output);
					p.println(jsonInput.toString());
				    output.close();
					return 0;
				}else {
					return 1;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	public TServicios buscarServicio(TServicios ts) {
		File a= buscarArchivo(ts);
		if(a!=null) {
			try {
				InputStream input;
				input = new FileInputStream(a);
				JSONObject jsonInput = new JSONObject(new JSONTokener(input));
				JSONArray b=jsonInput.getJSONArray("Tipos");
				for(int i=0;i<b.length();i++) {
				if(b.getJSONObject(i).get("Subtipo").equals(ts.escribirServicioId().get("Subtipo"))) {	
					TServicios tt=new TServicios(b.getJSONObject(i).get("Id").toString(),b.getJSONObject(i).get("Subtipo").toString(),jsonInput.get("Servicio").toString(),
					Integer.parseInt(b.getJSONObject(i).get("Número de plazas disponibles").toString()),Double.parseDouble(b.getJSONObject(i).get("Precio de cada plaza").toString()),
					b.getJSONObject(i).getBoolean("Estado"));
					return tt;
				}
			}
				return null;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
		return null;
	}

	@Override
	public List<TServicios> listarServicios() {
		File directorioFile=new File("ArchivosJSON/Servicios");
		File[] lista = directorioFile.listFiles();//Se ha podido añadir
		List<TServicios>l = new ArrayList<>();
		for(File elemento: lista) {
			
			try {
				InputStream input=new FileInputStream(elemento);
				JSONObject jsonInput = new JSONObject(new JSONTokener(input));
				JSONArray b=jsonInput.getJSONArray("Tipos");
				for(int i=0;i<b.length();i++) {
					JSONObject c=b.getJSONObject(i);
					if(c.getBoolean("Estado")==true) {
						l.add(new TServicios(String.valueOf(c.getInt("Id")),c.getString("Subtipo"),
								c.getString("Nombre servicio"),c.getInt("Número de plazas disponibles"),c.getDouble("Precio de cada plaza")));
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}			
		}
		return l;
	}

	@Override
	public int modifyServicio(TServicios ts) {
		File s=buscarArchivo(ts);
		TServicios service=buscarServicio(ts);//Busco si existe ya o no el servicio especificado
		String se="ArchivosJSON/Servicios/"+ts.escribirServicioId().get("Nombre servicio").toString()+".json";
		if(s==null) return 1; //No existe, por lo que no puede modificarse
		try {
			InputStream input;
			input = new FileInputStream(se);
			JSONObject jsonInput = new JSONObject(new JSONTokener(input));
			if(service.escribirServicioId().getBoolean("Estado")==true) {//Existe en la bdd y está activo
				JSONObject c=service.escribirServicioId();
				c.put("Estado",true);
				c.put("Precio de cada plaza", ts.escribirServicioId().get("Precio de cada plaza"));
				c.put("Número de plazas disponibles", ts.escribirServicioId().get("Número de plazas disponibles"));
				jsonInput.getJSONArray("Tipos").put(c.getInt("Id")-1,c);
				OutputStream output;
				output = new FileOutputStream(new File(se));
				PrintStream p = new PrintStream(output);				
				p.println(jsonInput.toString());
			    output.close();
				return 0;
				}
			else {//Existe y no está activo
					return 1;
				}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
		public TServicios searchByID(TServicios ts) {//Te busca el servicio dado su id
		File a= buscarArchivo(ts);
		if(a!=null) {
			try {
				InputStream input;
				input = new FileInputStream(a);
				JSONObject jsonInput = new JSONObject(new JSONTokener(input));
				JSONArray b=jsonInput.getJSONArray("Tipos");
				for(int i=0;i<b.length();i++) {
				if(b.getJSONObject(i).get("Id").equals(ts.escribirServicioId().get("Id"))&& b.getJSONObject(i).getBoolean("Estado")==true) {	
					TServicios tt=new TServicios(b.getJSONObject(i).get("Id").toString(),b.getJSONObject(i).get("Subtipo").toString(),jsonInput.get("Servicio").toString(),
					Integer.parseInt(b.getJSONObject(i).get("Número de plazas disponibles").toString()),Double.parseDouble(b.getJSONObject(i).get("Precio de cada plaza").toString()),
					b.getJSONObject(i).getBoolean("Estado"));
					return tt;
				}
			}
				return null;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
		return null;
	}
	
}
