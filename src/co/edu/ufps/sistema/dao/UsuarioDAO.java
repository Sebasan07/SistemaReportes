package co.edu.ufps.sistema.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.edu.ufps.sistema.entities.Usuario;
import co.edu.ufps.sistema.util.Conexion;
import co.edu.ufps.sistema.entities.Usuario;

public class UsuarioDAO extends Conexion<Usuario> implements GenericDAO<Usuario>{
	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario findByUsuario(String usuario) {
		Usuario t = null;
		try {
			Query q = super.getEm().createNamedQuery(Usuario.class.getSimpleName() + ".findByUsuario", Usuario.class)
					.setParameter("usuario", usuario);
			Object o = q.getSingleResult();
			t = (Usuario) o;
		} catch (NoResultException ne) {
			t = null;
		}
		return t;
	}
}
