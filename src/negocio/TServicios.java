package negocio;

import org.json.JSONObject;

public class TServicios {
	
	private String id_servicio;
	private String nombre_servicio;
	private String subtipo_servicio;
	private int num_plazas;
	private double precio_servicio;
	private boolean activo;
	
	public TServicios(String identificador,String s,String id, int num, double precio) {
		id_servicio=identificador;
		subtipo_servicio=s;
		nombre_servicio=id;
		num_plazas=num;
		precio_servicio=precio;
		activo=true;
	} 
	
	public TServicios(String identificador,String s,String id, int num, double precio,boolean activo) {
		id_servicio=identificador;
		subtipo_servicio=s;
		nombre_servicio=id;
		num_plazas=num;
		precio_servicio=precio;
		this.activo=activo;
	} 
	public TServicios(String s,String id, int num, double precio) {

		subtipo_servicio=s;
		nombre_servicio=id;
		num_plazas=num;
		precio_servicio=precio;
		activo=true;
	} 	
	public TServicios(String n,String s) {
		// TODO Auto-generated constructor stub
		nombre_servicio=n;
		subtipo_servicio=s;
	}
	
	public JSONObject escribirServicioId() {
		JSONObject a=new JSONObject();
		a.put("Estado", activo);
		a.put("Nombre servicio", nombre_servicio);
		a.put("Subtipo", subtipo_servicio);
		a.put("Precio de cada plaza", precio_servicio);
		a.put("Número de plazas disponibles", num_plazas);
		a.put("Id", id_servicio);

		return a;//Devuelve el correspondiente JSONObject del servicio	con su identificador
	}
	
}
