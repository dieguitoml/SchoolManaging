package negocio;

import java.util.List;

public interface SADepartamento {
		public int create(TDepartamento departamento);
		public int eliminate(TDepartamento departamento);
		public TDepartamento search(TDepartamento departamento);
		public List<TDepartamento> toList();
		// public int modify(TDepartamento departamento);
}
