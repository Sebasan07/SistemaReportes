package co.edu.ufps.sistema.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import co.edu.ufps.sistema.entities.Connectiontoken;
import co.edu.ufps.sistema.entities.Reporte;
import co.edu.ufps.sistema.util.Conexion;

public class ConnectiontokenDAO extends Conexion<Connectiontoken> implements GenericDAO<Connectiontoken>{
	public ConnectiontokenDAO() {
		super(Connectiontoken.class);
	}

	public List<Connectiontoken> listTokens(String usuario) {
		Query consulta = getEm().createNativeQuery(
				"select c.id, u.id as usuario from usuario u inner join connectiontoken c on c.user=u.id inner join reporte on reporte.conexion=c.id where u.id=:usuario")
				.setParameter("usuario", usuario);
		List<Object[]> lista = consulta.getResultList();
		List<Connectiontoken> listtokens =null;
		if (lista != null) {
			listtokens = new ArrayList<>();
			for (Object[] s : lista) {
				Connectiontoken r = new Connectiontoken();
				r = this.find(Integer.parseInt(s[0].toString()));
				listtokens.add(r);
			}
		}
		return listtokens;
	}

}
