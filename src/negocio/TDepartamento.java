package negocio;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TDepartamento {
	private String nombre;
	private int ID;
	private boolean activo;
	private int num_profesores;
	
	public TDepartamento(String nombre){
		this.nombre = nombre; 
		this.activo = true;
		this.ID =-1;
	}
	
	public TDepartamento(String nombre, boolean activo) {
		this.nombre = nombre;
		this.activo = activo;
		this.ID =-1;
		this.num_profesores = 0; 
	}
	
	public TDepartamento(int ID, String nombre) {
		this.nombre = nombre;
		this.ID = ID;
	}
	
	public TDepartamento(int ID, String nombre, int num_profesores) {
		this.nombre = nombre;
		this.ID = ID; 
		this.num_profesores = num_profesores;
		this.activo = true;			
	}

	public JSONObject escribirDepartamentoID() {
		JSONObject Departamento = new JSONObject();
		Departamento.put("Estado", activo);
		Departamento.put("Nombre", nombre);
		Departamento.put("ID", ID);
		Departamento.put("num_profesores", num_profesores);
		return Departamento;
	}
}
