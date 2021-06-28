package co.edu.ufps.sistema.dao;

import co.edu.ufps.sistema.entities.Connectiontoken;
import co.edu.ufps.sistema.util.Conexion;

public class ConnectiontokenDAO extends Conexion<Connectiontoken> implements GenericDAO<Connectiontoken>{
	public ConnectiontokenDAO() {
		super(Connectiontoken.class);
	}

}
