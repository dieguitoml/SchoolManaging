package negocio;

import java.util.List;

public interface SAFactura {

	public int create(TDatosFactura datos);//devuelve loque hace correctamente
	public List<Object[]> listar(); //devuelve una lista de todas las facturas
	public TDatosFactura buscar(String campo); //develve la primera factura que se correponde con los campos
}
