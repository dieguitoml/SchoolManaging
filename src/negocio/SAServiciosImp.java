package negocio;

import java.util.List;

import integracion.DAOServicios;
import integracion.FactoriaAbstractaIntegracion;

public class SAServiciosImp implements SAServicios{

	@Override
	public int create(TServicios ts) {//Devuelve 0 si se ha anadido, 1 si no se ha podido anadir
		DAOServicios Ds=FactoriaAbstractaIntegracion.getInstance().crearDAOServicios();
		return Ds.createServicio(ts);
	}

	@Override
	public int eliminate(TServicios ts) {
		DAOServicios Ds=FactoriaAbstractaIntegracion.getInstance().crearDAOServicios();
		return Ds.eliminateServicio(ts);
	}

	@Override
	public TServicios buscar(TServicios servicio) {
		DAOServicios Ds=FactoriaAbstractaIntegracion.getInstance().crearDAOServicios();
		TServicios a=Ds.buscarServicio(servicio);
		if(a==null||!a.escribirServicioId().getBoolean("Estado")) {
			return null;
		}else {
			return a;
		}
	}

	@Override
	public List<TServicios> listar() {
		DAOServicios Ds=FactoriaAbstractaIntegracion.getInstance().crearDAOServicios();
		return Ds.listarServicios();
	}

	@Override
	public int modify(TServicios ts) {
		DAOServicios Ds=FactoriaAbstractaIntegracion.getInstance().crearDAOServicios();
		return Ds.modifyServicio(ts);
	}
	
	

}
