package negocio;

import java.util.List;


public interface SAServicios {
	public int create(TServicios ts);
	public int eliminate(TServicios ts);
	public int modify(TServicios ts);
	public TServicios buscar(TServicios servicio);
	public List<TServicios>listar();
}
