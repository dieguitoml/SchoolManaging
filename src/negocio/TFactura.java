package negocio;

import org.json.JSONObject;

public class TFactura {
	
	private static int _idCount ;
	private String _id;
	private String _idAlumno;
	private String _idSecretaria;
	private Double _precioTotal;
	private Fecha _fecha;
	
	public TFactura(String idAlumno, String idSecretaria, Fecha fecha) {
		_idAlumno = idAlumno;
		_idSecretaria = idSecretaria;
		_precioTotal = 0.0; //va cambiando segun se añaden cosas a la lineaFactura
		_fecha = fecha;
	}
	public TFactura(String ID,String idAlumno, String idSecretaria, Fecha fecha,Double Precio) {
		this(idAlumno,idSecretaria,fecha);
		_id = ID;
		_precioTotal = Precio;
	}
	public void addServicioPrice(Double euros) {
		_precioTotal += euros;
	}
	public String getID() {
		return _id;
	}
	public Fecha getFecha() {
		return _fecha;
	}
	public String getPrecio() {
		String aux = Double.toString(_precioTotal);
		aux += " euros";
		return aux;
	}
	public String getIdSecretaria() {
		return _idSecretaria;
	}
	public String getIdAlumno() {
		return _idAlumno;
	}
	public JSONObject escribirJSON(int IDcount) {
		_idCount = IDcount; //Se actualiza cada vez que se hace una factura
		_idCount++;
		JSONObject a=new JSONObject();
		a.put("ID",_idCount);
		a.put("ID Alumno", _idAlumno);
		a.put("ID Secretaria", _idSecretaria);
		a.put("Precio Total", _precioTotal);
		a.accumulate("Fecha",_fecha.getDia());
		a.accumulate("Fecha",_fecha.getMes());
		a.accumulate("Fecha",_fecha.getAnno());
		return a;//Devuelve el correspondiente JSONObject de la	con su identificador
	}
	public Object[] getDecription() {
		Object[] aux ={_id,_idAlumno,_idSecretaria,getPrecio(),_fecha.toString()};
		return  aux ;
	}
	
}
