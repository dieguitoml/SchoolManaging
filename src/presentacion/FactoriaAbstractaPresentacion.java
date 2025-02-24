package presentacion;

public abstract class FactoriaAbstractaPresentacion {//PARA CREAR LAS VISTAS DENTRO DE CADA SUBSISTEMA 
	//ES UN SINGLETON, SOLO HAY UNA ÚNICA INSTANCIA PARA TODO EL PROGRAMA 
	
	private static FactoriaAbstractaPresentacion instancia;
	
	public static FactoriaAbstractaPresentacion getInstance() {
		if(instancia==null) {
			return new FactoriaPresentacion();
		}
		return instancia;
	}
	
	public abstract IGUI createVista(int id);
}
