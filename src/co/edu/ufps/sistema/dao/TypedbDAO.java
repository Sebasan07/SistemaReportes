package co.edu.ufps.sistema.dao;

import co.edu.ufps.sistema.entities.Typedb;
import co.edu.ufps.sistema.util.Conexion;

public class TypedbDAO extends Conexion<Typedb> implements GenericDAO<Typedb>{
	public TypedbDAO() {
		super(Typedb.class);
	}

}
