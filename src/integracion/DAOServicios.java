package integracion;

import java.util.List;

import negocio.TServicios;

public interface DAOServicios {
	public int createServicio(TServicios ts);
	public int eliminateServicio(TServicios ts);
	public int modifyServicio(TServicios ts);
	public TServicios buscarServicio(TServicios ts);
	public List<TServicios>listarServicios();
	public TServicios searchByID(TServicios ts);
	
}
