package negocio;

import org.json.JSONObject;

public class TAlumno {
	private String DNI;
	private String Nombre;
	private String Apellidos;
	private String Curso;
	
	public TAlumno(String DNI, String Nombre, String Apellidos, String Curso){
		this.DNI=DNI;
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Curso = Curso;
	}
	
	public TAlumno(String DNI, String Curso){
		this.DNI = DNI;
		this.Curso = Curso;
	}
	
	public JSONObject escribirAlumno() {
		JSONObject alumno = new JSONObject();
		alumno.put("DNI", DNI);
		alumno.put("Nombre", Nombre);
		alumno.put("Apellidos", Apellidos);
		alumno.put("Curso", Curso);
		return alumno;
	}
	public String getNombreyApellidos() {
		return Apellidos +", " + Nombre;
	}
}
