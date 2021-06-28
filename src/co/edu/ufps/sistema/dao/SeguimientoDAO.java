package co.edu.ufps.sistema.dao;

import co.edu.ufps.sistema.entities.Seguimiento;
import co.edu.ufps.sistema.util.Conexion;

public class SeguimientoDAO extends Conexion<Seguimiento> implements GenericDAO<Seguimiento>{
	public SeguimientoDAO() {
		super(Seguimiento.class);
	}

}
