package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	@WebResult(name = "autores") // anotações @WebResult e @WebParam utilizas para melhorar legibilidade da
									// chamada e retorno do webservice
	public List<Livro> livrosPeloNome(@WebParam(name = "titulo") String nome) {// metodo utilizado pelo ws
		TypedQuery<Livro> query = this.manager.createQuery("select l from Livro l " + "where l.titulo like :pTitulo",
				Livro.class);
		query.setParameter("pTitulo", "%" + nome + "%");

		return query.getResultList();
	}

}
