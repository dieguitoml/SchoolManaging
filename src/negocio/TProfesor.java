package negocio;

import org.json.JSONObject;

public class TProfesor extends TEmpleados{
		private String Departamento;
		
		public TProfesor(String DNI, String Nombre, String Apellidos, String Cargo, String Departamento){
			super(DNI, Nombre, Apellidos, Cargo);
			this.Departamento = Departamento;
		}
		
		@Override
		public JSONObject escribirEmpleado() {
			JSONObject empleado = super.escribirEmpleado();
			empleado.put("Departamento", Departamento);
			return empleado;
		}
}


