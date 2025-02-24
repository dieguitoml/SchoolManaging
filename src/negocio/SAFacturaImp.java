package negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import integracion.DAOAlumno;
import integracion.DAOAlumnoServicio;
import integracion.DAOEmpleados;
import integracion.DAOFactura;
import integracion.DAOLineaFactura;
import integracion.DAOServicios;
import integracion.FactoriaAbstractaIntegracion;

public class SAFacturaImp implements SAFactura{

	@Override
	public int create(TDatosFactura datos) { //TODO
		int exito= 0; // 0 = exito, 1 = incompleto, 2 = no añadido,
		//Crear DAOs necesarios
		DAOFactura        Df  = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
		DAOLineaFactura   Dl  = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
		DAOEmpleados      De  = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleados();
		DAOServicios      Ds  = FactoriaAbstractaIntegracion.getInstance().crearDAOServicios();
		DAOAlumno         Da  = FactoriaAbstractaIntegracion.getInstance().crearDAOAlumno();
		DAOAlumnoServicio Das = FactoriaAbstractaIntegracion.getInstance().crearDAOAlumnoServicio();
		//Comprobar que existe el TODO alumno y la secretaria
		TFactura facturaFinal = datos.getFactura();
		TEmpleados secretaria = new TEmpleados(facturaFinal.getIdSecretaria(), "Secretaria");
		secretaria = De.buscarEmpleado(secretaria);
		
		if(secretaria == null)exito = 2;//TODO meter también lo del empleado
		else {
			//TODO Validamos las lineas del carrito->existe el servicio y hay plazas disponibles
			List<TLineaFactura> lineasFacturaValidas = new ArrayList<TLineaFactura>();
			double precioTotal = 0;
			for(TLineaFactura linea: datos.getCarrito()) {
				if(true) {
					lineasFacturaValidas.add(linea);
					//TODO BUSCAR PRECIO DEL servicio y modificarlo
					int precio = 6;
					precioTotal += precio;
					//TODO Quitar plazasde los servicios y hacer duo servicio alumno
				}
				else exito = 1; //No se han añadido todos los servicios propuestos
			}
			//Acumulamos el precioTotal de la Factura
			facturaFinal.addServicioPrice(precioTotal);
			//Actualizo en BD(JSON) el fichero:
			////Si hay lineas validadas: Pido al DAO crearfactura
			if(!lineasFacturaValidas.isEmpty()) {
				String id = Df.create(facturaFinal); 
				for(TLineaFactura lF: lineasFacturaValidas) {
					lF.setFactura(id);//Establezaco idFactura en las lineas validadas
					String i = Dl.create(lF);//Pido al DAO que cree en la BD las lineas de factura
				}
			}
			else exito = 2; //No se ha creado la factura porque no hay servicios validos
		}
		return exito;
	}


	@Override
	public List<Object[]> listar() {
		DAOFactura df = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
		List<TFactura> facturas = df.listar();
		List<Object[]> rows = new ArrayList<Object[]>();
		for(TFactura fact: facturas) {
			rows.add(fact.getDecription());
		}
		return  rows;
	}


	@Override
	public TDatosFactura buscar(String IDFactura) {
		TDatosFactura datosFactura;
		//DAOS
		DAOFactura        Df  = FactoriaAbstractaIntegracion.getInstance().crearDAOFactura();
		DAOLineaFactura   Dl  = FactoriaAbstractaIntegracion.getInstance().crearDAOLineaFactura();
		DAOEmpleados      De  = FactoriaAbstractaIntegracion.getInstance().crearDAOEmpleados();
		//DAOServicios      Ds  = FactoriaAbstractaIntegracion.getInstance().crearDAOServicios();
		//DAOAlumno         Da  = FactoriaAbstractaIntegracion.getInstance().crearDAOAlumno();
		TFactura factura = Df.buscar(IDFactura);
		if(factura != null) {
			datosFactura = new TDatosFactura(factura);
			TEmpleados secretaria = new TEmpleados(factura.getIdSecretaria(), "Secretaria");
			//TODO falta poner secretarias que existan
			//secretaria = De.buscarEmpleado(secretaria);
			//datosFactura.setSecretaria(secretaria.getNombreyApellidos());
			datosFactura.setSecretaria("Secretaria" +factura.getIdSecretaria());
			//TODO FAlta el nombre del alumno y servicios???
			//TAlumno alumno = Da.buscarAlumno(null)
			datosFactura.setAlumno("Alumno"+factura.getIdAlumno());
			datosFactura.setCarrito(Dl.buscar(IDFactura));
			return datosFactura;
		}		
		return null;
	}




}
