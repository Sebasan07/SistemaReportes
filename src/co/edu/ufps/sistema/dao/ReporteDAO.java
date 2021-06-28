package co.edu.ufps.sistema.dao;

import co.edu.ufps.sistema.entities.Reporte;
import co.edu.ufps.sistema.util.Conexion;

public class ReporteDAO extends Conexion<Reporte> implements GenericDAO<Reporte>{
	public ReporteDAO() {
		super(Reporte.class);
	}

}
