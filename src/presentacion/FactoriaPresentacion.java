package presentacion;

import presentacion.GUIDepartamentos.VistaAltaDepartamento;
import presentacion.GUIDepartamentos.VistaBajaDepartamento;
import presentacion.GUIDepartamentos.VistaBuscarDepartamento;
import presentacion.GUIDepartamentos.VistaListarDepartamento;
import presentacion.GUIDepartamentos.VistaModificarDepartamento;
import presentacion.GUIEmpleados.VistaAltaEmpleado;
import presentacion.GUIEmpleados.VistaBajaEmpleado;
import presentacion.GUIEmpleados.VistaBuscarEmpleado;
import presentacion.GUIEmpleados.VistaListarEmpleado;
import presentacion.GUIEmpleados.VistaModificarEmpleado;
import presentacion.GUIFacturas.VistaAltaFactura;
import presentacion.GUIFacturas.VistaAnnadirLineaFactura;
import presentacion.GUIFacturas.VistaBuscarFactura;
import presentacion.GUIFacturas.VistaCerrarFactura;
import presentacion.GUIFacturas.VistaListarFacturas;
import presentacion.GUIFacturas.VistaMostrarFactura;
import presentacion.GUIServicios.VistaAltaServicio;
import presentacion.GUIServicios.VistaBajaServicio;
import presentacion.GUIServicios.VistaBuscarServicio;
import presentacion.GUIServicios.VistaListarServicios;
import presentacion.GUIServicios.VistaModificarServicio;
import presentacion.GUIAlumno.VistaAltaAlumno;
import presentacion.GUIAlumno.VistaBajaAlumno;
import presentacion.GUIAlumno.VistaBuscarAlumno;
import presentacion.GUIAlumno.VistaListarAlumno;
import presentacion.GUIAlumno.VistaModificarAlumno;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{

	@Override
	public IGUI createVista(int id) {//DEVUELVE LA VISTA QUE LE PEDIMOS EN CADA MOMENTO
		switch(id) {
	//Servicios
		case  Eventos.ALTA_SERVICIO:{
			return new VistaAltaServicio();
		}
		case Eventos.BAJA_SERVICIO:{
			return new VistaBajaServicio();
		}
		case Eventos.BUSCAR_SERVICIO:{
			return new VistaBuscarServicio();
		}
		case Eventos.LISTAR_SERVICIO:{
			return new VistaListarServicios();
		}
		case Eventos.MODIFICAR_SERVICIO:{
			return new VistaModificarServicio();
		}
	//Empleados
		case Eventos.ALTA_EMPLEADOS:{
			return new VistaAltaEmpleado();
		}
		case Eventos.BAJA_EMPLEADOS:{
			return new VistaBajaEmpleado();
		}	
		case Eventos.LISTAR_EMPLEADOS:{
			return new VistaListarEmpleado();
		}
		case Eventos.BUSCAR_EMPLEADOS:{
			return new VistaBuscarEmpleado();
		}
		case Eventos.MODIFICAR_EMPLEADOS:{
			return new VistaModificarEmpleado();
		}
	//Departamentos
		case Eventos.ALTA_DEPARTAMENTO:{
			return new VistaAltaDepartamento();
		}
		case Eventos.BAJA_DEPARTAMENTO:{
			return new VistaBajaDepartamento();
		}
		case Eventos.BUSCAR_DEPARTAMENTO:{
			return new VistaBuscarDepartamento();
		}
		case Eventos.LISTAR_DEPARTAMENTO:{
			return new VistaListarDepartamento();
		}
	//Facturas
		case Eventos.ALTA_FACTURA:{
			return new VistaAltaFactura();
		}
		case Eventos.ANNADIR_LINEA_FACTURA:{
			return new VistaAnnadirLineaFactura();
		}
		case Eventos.CERRAR_FACTURA:{
			return new VistaCerrarFactura();
		}
		case Eventos.BUSCAR_FACTURA:{
			return new VistaBuscarFactura();
		}
		case Eventos.LISTAR_FACTURA:{
			return new VistaListarFacturas();
		}
		case Eventos.MOSTRAR_FACTURA:{
			return new VistaMostrarFactura();
		}
	//ALUMNOS
		case Eventos.ALTA_ALUMNOS:{
			return new VistaAltaAlumno();
		}
		case Eventos.BAJA_ALUMNOS:{
			return new VistaBajaAlumno();
		}	
		case Eventos.LISTAR_ALUMNOS:{
			return new VistaListarAlumno();
		}
		case Eventos.BUSCAR_ALUMNOS:{
			return new VistaBuscarAlumno();
		}
		case Eventos.MODIFICAR_ALUMNOS:{
			return new VistaModificarAlumno();
		}

	}
		return null;
	}
}
