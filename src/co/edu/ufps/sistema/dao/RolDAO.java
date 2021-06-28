package co.edu.ufps.sistema.dao;

import co.edu.ufps.sistema.entities.Rol;
import co.edu.ufps.sistema.util.Conexion;

public class RolDAO extends Conexion<Rol> implements GenericDAO<Rol>{
	public RolDAO() {
		super(Rol.class);
	}

}
