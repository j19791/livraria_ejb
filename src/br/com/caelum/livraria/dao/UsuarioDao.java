package br.com.caelum.livraria.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Usuario;

@Stateless
public class UsuarioDao {

	// private Banco banco = new Banco();
	@PersistenceContext
	EntityManager manager;

	public Usuario buscaPeloLogin(String login) {
		// return this.banco.buscaPeloNome(login);

		Usuario usuario = null;
		try {
			usuario = (Usuario) this.manager.createQuery("select u from Usuario u where u.login=:pLogin")
					.setParameter("pLogin", login).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuario;
	}

}
