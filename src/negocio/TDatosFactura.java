package negocio;

import java.util.List;

public class TDatosFactura {
	TFactura _factura;
	String _nombreAlumno;
	String _nombreSecretaria;
	List<TLineaFactura> _carrito;
	
	public TDatosFactura(String alumno, String secretaria, Fecha fecha, List<String> carrito, Double precio){
		_factura = new TFactura(alumno,secretaria, fecha);
		_factura.addServicioPrice(precio);
	}
	public TDatosFactura(String alumno, String secretaria, Fecha fecha){
		_factura = new TFactura(alumno,secretaria, fecha);
	}
	public TDatosFactura(TFactura factura) {
		_factura = factura;
	}
	public void setAlumno(String alumno) {
		_nombreAlumno = alumno;
	}
	public void setSecretaria(String secre) {
		_nombreSecretaria = secre;
	}
	public void setCarrito(List<TLineaFactura> carrito) {
		_carrito = carrito;
	}
	public String getAlumno() {
		return _nombreAlumno;
	}
	public String getSecretaria() {
		return _nombreSecretaria;
	}
	public List<TLineaFactura> getCarrito(){
		return _carrito;
	}
	public TFactura getFactura() {
		return _factura;
	}
}
