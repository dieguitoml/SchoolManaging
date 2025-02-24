package negocio;

import org.json.JSONObject;

public class TEmpleados {
	private String DNI;
	private String Nombre;
	private String Apellidos;
	private String Cargo;
	
	public TEmpleados(String DNI, String Nombre, String Apellidos, String Cargo){
		this.DNI=DNI;
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.Cargo = Cargo;
	}
	
	public TEmpleados(String DNI, String Cargo){
		this.DNI = DNI;
		this.Cargo = Cargo;
	}
	
	public JSONObject escribirEmpleado() {
		JSONObject empleado = new JSONObject();
		empleado.put("DNI", DNI);
		empleado.put("Nombre", Nombre);
		empleado.put("Apellidos", Apellidos);
		empleado.put("Cargo", Cargo);
		return empleado;
	}
	public String getNombreyApellidos() {
		return Apellidos +", " + Nombre;
	}
}
