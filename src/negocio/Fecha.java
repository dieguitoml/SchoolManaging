package negocio;

public class Fecha {
	private int _dia;
	private int _mes;
	private int _anno;
	
	public Fecha(int Dia, int Mes, int Anno) {
		_dia = Dia;
		_mes = Mes;
		_anno = Anno;
	}
	public int getDia() {
		return _dia;
	}
	public int getMes() {
		return _mes;
	}
	public int getAnno() {
		return _anno;
	}
	
	public String toString() {
		String aux="";
		if(_dia<10) aux += "0"+_dia;
		else aux += _dia;
		aux += "/";
		if(_mes < 10) aux += "0"+_mes;
		else aux+=_mes;
		aux+= "/"+_anno;
		return aux;
		}
	
}
