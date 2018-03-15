package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

//Utilizando a arquitetura EJB, as regras de negócio são implementadas em componentes específicos -  
//Session Beans. O EJB Container administra esses componentes oferecendo diversos recursos.
@Stateless // Para transformar a classe AutorDao em um EJB: acesso aos serviços do EJB
			// Server Container, como transação, persistência com JPA ou tratamento de erro.
// Thread safety: Apenas um thread pode usar o nosso Session Bean AutorDao ao
// mesmo tempo para melhor o desempenho.
// standalone.xml configura a qtd de threads (pool de objetos) q podem limitar o
// uso da aplicação
@TransactionManagement(TransactionManagementType.CONTAINER) // O JTA é a forma padrão de gerenciar a transação dentro do
															// servidor JavaEE e funciona sem nenhuma configuração (a
															// config CONTAINER É OPCIONAL):
															// CONTAINER MANAGED TRANSACTION (CMT).
public class AutorDao {

	// private Banco banco = new Banco();
	// @Inject // Banco é ejb stateless
	// private Banco banco;

	@PersistenceContext // EJB Container administrará o JPA - o EJB Container injete o EntityManager -
						// serve para gerenciar os dados armazenados pelos sistemas.
	EntityManager manager;

	@PostConstruct // Callback: Este método não será chamado pela AutorBean. @PostConstruct será
					// chamado pelo próprio EJB Container. apenas um Session Bean é criada por aba
	void aposCriacao() {
		System.out.println("AutorDao foi criado");
	}

	// @TransactionAttribute(TransactionAttributeType.REQUIRED) // opcional - O jta
	// JÁ CONFIGURA ESSA opção como padrão
	@TransactionAttribute(TransactionAttributeType.MANDATORY) // o dao nao pode ter controle de transacao - o controle
																// deverá vir de outros ejb (autorservice)
	public void salva(Autor autor) {

		System.out.println("antes de salvar autor:" + autor.getNome());

		/*
		 * try { Thread.sleep(20000);// travar a execução da thread atual por 20s }
		 * catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// banco.save(autor);

		manager.persist(autor); // substitudo p/ usar o jpa

		System.out.println("depois de salvar autor: " + autor.getNome());

		// throw new RuntimeException("Serviço externo deu erro!");
		// exceção foi "embrulhada" em uma outra do tipo
		// EJBTransactionRollbackException: foi feito um rollback da transação.
		// unchecked., System Exception, algo grave e imprevisto.

	}

	public List<Autor> todosAutores() {
		// return banco.listaAutores();
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		// Autor autor = this.banco.buscaPelaId(autorId);

		Autor autor = this.manager.find(Autor.class, autorId);

		return autor;
	}

}
