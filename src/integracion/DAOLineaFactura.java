package integracion;

import java.util.List;

import negocio.TLineaFactura;

public interface DAOLineaFactura {
	public String create(TLineaFactura lf) ;//devuelve el id si se puede crear
	public List<TLineaFactura> buscar(String IDFactura);
}
