package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroDao {

	// private Banco banco = new Banco();
	@PersistenceContext
	EntityManager manager;

	public void salva(Livro livro) {
		// banco.save(livro);
		manager.persist(livro);
	}

	public List<Livro> todosLivros() {
		// return banco.listaLivros();
		return manager.createQuery("select l from Livro l", Livro.class).getResultList();
	}

}
