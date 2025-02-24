package negocio;

import org.json.JSONObject;

public class TLineaFactura {
	
	private static int _idCount ;
	private String _id;
	private String _idFactura;
	private String _idServicio;
	
	public TLineaFactura(String id, String idFactura, String idServicio) {
		this(idServicio);
		setFactura(idFactura);
		_id = id;
	}
	public TLineaFactura( String idServicio) {
		_idServicio = idServicio;
	}
	public void setFactura(String idFactura) {
		_idFactura = idFactura;
	}
	public String getServiceId() {
		return _idServicio;
	}
	
	public JSONObject escribirJSON(int IDcount) {
		_idCount = IDcount; //Se actualiza cada vez que se hace una factura
		_idCount++;
		JSONObject a=new JSONObject();
		a.put("ID",_idCount);
		a.put("ID Factura", _idFactura);
		a.put("ID servicio", _idServicio);
		return a;//Devuelve el correspondiente JSONObject de la	con su identificador
	}
	
}
