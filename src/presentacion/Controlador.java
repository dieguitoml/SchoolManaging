package presentacion;

import java.io.FileNotFoundException;

import org.json.JSONException;

public abstract class Controlador {//ES SINGLETON, SE USA UNA UNICA INSTANCIA EN TODO EL PROGRAMA 
	
	private static Controlador instancia=null;
	
	static public Controlador getInstancia() {
		if(instancia==null) {
			instancia=new ControladorImp();
		}
		return instancia;	
	}
	
	public abstract void accion(int evento, Object datos) throws FileNotFoundException, JSONException;
	
}
