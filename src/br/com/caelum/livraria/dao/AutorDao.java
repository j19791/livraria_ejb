package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.modelo.Autor;

//Utilizando a arquitetura EJB, as regras de negócio são implementadas em componentes específicos -  
//Session Beans. O EJB Container administra esses componentes oferecendo diversos recursos.
@Stateless // Para transformar a classe AutorDao em um EJB: acesso aos serviços do EJB
			// Server Container, como transação, persistência com JPA ou tratamento de erro.
// Thread safety: Apenas um thread pode usar o nosso Session Bean AutorDao ao
// mesmo tempo para melhor o desempenho.
// standalone.xml configura a qtd de threads (pool de objetos) q podem limitar o
// uso da aplicação
public class AutorDao {

	// private Banco banco = new Banco();
	@Inject // Banco é ejb stateless
	private Banco banco;

	@PostConstruct // Callback: Este método não será chamado pela AutorBean. @PostConstruct será
					// chamado pelo próprio EJB Container. apenas um Session Bean é criada por aba
	void aposCriacao() {
		System.out.println("AutorDao foi criado");
	}

	public void salva(Autor autor) {

		System.out.println("antes de salvar autor:" + autor.getNome());

		try {
			Thread.sleep(20000);// travar a execução da thread atual por 20s
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		banco.save(autor);

		System.out.println("depois de salvar autor: " + autor.getNome());
	}

	public List<Autor> todosAutores() {
		return banco.listaAutores();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.banco.buscaPelaId(autorId);
		return autor;
	}

}
