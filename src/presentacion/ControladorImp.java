package presentacion;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import negocio.FactoriaAbstractaNegocio;
import negocio.SAAlumnos;
import negocio.SADepartamento;
import negocio.SAEmpleados;
import negocio.SAFactura;
import negocio.SAServicios;
import negocio.TAlumno;
import negocio.TDatosFactura;
import negocio.TDepartamento;
import negocio.TEmpleados;
import negocio.TFactura;
import negocio.TLineaFactura;
import negocio.TServicios;

public class ControladorImp extends Controlador{

	@Override
	public void accion(int evento, Object datos)  {
		// TODO Auto-generated method stub
		switch(evento) {
	//SERVICIOS
		case Eventos.ALTA_SERVICIO:{
			TServicios ts=(TServicios)datos;
			SAServicios ss=FactoriaAbstractaNegocio.getInstance().crearSAServicio();
			int exito=ss.create(ts);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_SERVICIO_OK, exito);
			break;
		}case Eventos.BAJA_SERVICIO:{
			TServicios ts=(TServicios)datos;
			SAServicios ss=FactoriaAbstractaNegocio.getInstance().crearSAServicio();
			int exito=ss.eliminate(ts);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_SERVICIO_OK, exito);
			break;
		}case Eventos.BUSCAR_SERVICIO:{
			TServicios name=(TServicios)datos;
			SAServicios ss=FactoriaAbstractaNegocio.getInstance().crearSAServicio();
			TServicios buscado=ss.buscar(name);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_SERVICIO_OK, buscado);
			break;
		}case Eventos.LISTAR_SERVICIO:{
			SAServicios ss=FactoriaAbstractaNegocio.getInstance().crearSAServicio();
			List<TServicios> l=ss.listar();
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_LISTAR_SERVICIOS_OK, l);
			break;
		}case Eventos.MODIFICAR_SERVICIO:{
			TServicios ts=(TServicios) datos;
			SAServicios ss=FactoriaAbstractaNegocio.getInstance().crearSAServicio();
			int exito=ss.modify(ts);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_SERVICIO_OK, exito);
			break;
		}
	//FACTURAS
		case Eventos.BUSCAR_FACTURA:{
			String IDFactura = (String) datos;
			SAFactura sf = FactoriaAbstractaNegocio.getInstance().crearSAFactura();
			TDatosFactura encontrado = sf.buscar(IDFactura);
			if(encontrado != null)FactoriaAbstractaPresentacion.getInstance().createVista(Eventos.MOSTRAR_FACTURA).actualizar(Eventos.MOSTRAR_FACTURA,encontrado);
			else FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_FACTURA_NO,encontrado);
			break;
		}
		case Eventos.LISTAR_FACTURA:{
			SAFactura sf = FactoriaAbstractaNegocio.getInstance().crearSAFactura(); //SA
			List<Object[]> facturas = sf.listar();
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.LISTAR_FACTURA_OK,facturas);
			break;
		}
		case Eventos.ALTA_FACTURA:{
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(evento,null);
			break;
		}
		case Eventos.CERRAR_FACTURA:{
			TDatosFactura campo = (TDatosFactura) datos;
			SAFactura sf = FactoriaAbstractaNegocio.getInstance().crearSAFactura(); //SA
			int exito = sf.create(campo);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.ALTA_FACTURA_OK,exito);
			break;
		}
	//EMPLEADOS
		case Eventos.ALTA_EMPLEADOS:{
			TEmpleados empleado = (TEmpleados) datos;
			SAEmpleados se = FactoriaAbstractaNegocio.getInstance().crearSAEmpleados();
			int integrado=se.create(empleado);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_EMPLEADOS_OK, integrado);
			break;
		}
		case Eventos.BAJA_EMPLEADOS:{
			TEmpleados empleado = (TEmpleados) datos;
			SAEmpleados se = FactoriaAbstractaNegocio.getInstance().crearSAEmpleados();
			int eliminado=se.eliminate(empleado);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_EMPLEADOS_OK, eliminado);
			break;
		}
		case Eventos.BUSCAR_EMPLEADOS:{
			TEmpleados empleado = (TEmpleados) datos;
			SAEmpleados se = FactoriaAbstractaNegocio.getInstance().crearSAEmpleados();
			TEmpleados buscado=se.buscar(empleado);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_EMPLEADOS_OK, buscado);
			break;
		}
		case Eventos.MODIFICAR_EMPLEADOS:{
			TEmpleados empleado = (TEmpleados) datos;
			SAEmpleados se = FactoriaAbstractaNegocio.getInstance().crearSAEmpleados();
			int modificado=se.modificar(empleado);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_EMPLEADOS_OK, modificado);
			break;
		}
		case Eventos.LISTAR_EMPLEADOS:{
			SAEmpleados se = FactoriaAbstractaNegocio.getInstance().crearSAEmpleados();
			List<TEmpleados> listaEmpleados=se.listar();
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_LISTAR_EMPLEADOS_OK, listaEmpleados);
			break;
		}
	//ALUMNOS 
		case Eventos.ALTA_ALUMNOS:{
			TAlumno alumno = (TAlumno) datos;
			SAAlumnos sa = FactoriaAbstractaNegocio.getInstance().crearSAAlumnos();
			int integrado=sa.create(alumno);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_ALUMNOS_OK, integrado);
			break;
		}
		case Eventos.BAJA_ALUMNOS:{
			TAlumno alumno = (TAlumno) datos;
			SAAlumnos sa = FactoriaAbstractaNegocio.getInstance().crearSAAlumnos();
			int eliminado=sa.eliminate(alumno);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_ALUMNOS_OK, eliminado);
			break;
		}
		case Eventos.BUSCAR_ALUMNOS:{
			TAlumno alumno = (TAlumno) datos;
			SAAlumnos sa = FactoriaAbstractaNegocio.getInstance().crearSAAlumnos();
			TAlumno buscado=sa.buscar(alumno);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_ALUMNOS_OK, buscado);
			break;
		}
		case Eventos.MODIFICAR_ALUMNOS:{
			TAlumno alumno = (TAlumno) datos;
			SAAlumnos sa = FactoriaAbstractaNegocio.getInstance().crearSAAlumnos();
			int modificado=sa.modificar(alumno);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_ALUMNOS_OK, modificado);
			break;
		}
		case Eventos.LISTAR_ALUMNOS:{
			SAAlumnos sa = FactoriaAbstractaNegocio.getInstance().crearSAAlumnos();
			List<TAlumno> listaAlumnos=sa.listar();
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_LISTAR_ALUMNOS_OK, listaAlumnos);
			break;
		}
				
				
	// DEPARTAMENTOS 
		case Eventos.ALTA_DEPARTAMENTO:{
			TDepartamento Departamento = (TDepartamento) datos;
			SADepartamento se = FactoriaAbstractaNegocio.getInstance().crearSADepartamento();
			int integrado=se.create(Departamento);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_DEPARTAMENTO_OK, integrado);
			break;
		}
		case Eventos.BAJA_DEPARTAMENTO:{
			TDepartamento Departamento = (TDepartamento) datos;
			SADepartamento se = FactoriaAbstractaNegocio.getInstance().crearSADepartamento();
			int eliminado=se.eliminate(Departamento);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_DEPARTAMENTO_OK, eliminado);
			break;
		}
		case Eventos.LISTAR_DEPARTAMENTO:{
			TDepartamento Departamento = (TDepartamento) datos;
			SADepartamento se = FactoriaAbstractaNegocio.getInstance().crearSADepartamento();
			List<TDepartamento> lista=se.toList();
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_LISTAR_DEPARTAMENTO_OK, lista);
			break;
		}
		case Eventos.BUSCAR_DEPARTAMENTO:{
			TDepartamento Departamento = (TDepartamento) datos;
			SADepartamento se = FactoriaAbstractaNegocio.getInstance().crearSADepartamento();
			TDepartamento departamento=se.search(Departamento);
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_DEPARTAMENTO_OK, departamento);
			break;
		}
		}

	}
}
