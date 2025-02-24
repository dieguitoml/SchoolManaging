package integracion;

import java.util.List;

import negocio.TDatosFactura;
import negocio.TFactura;

public interface DAOFactura {
	public String create(TFactura tf);//devuelve el id si la crea correctamente
	public List<TFactura> listar(); //devuelve una lista de todas las facturas
	public TFactura buscar(String id);
}
