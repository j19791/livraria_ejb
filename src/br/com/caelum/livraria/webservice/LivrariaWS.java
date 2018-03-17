package br.com.caelum.livraria.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;

@Stateless
@WebService // para o container EJB publicar o serviços usando os padrões SOAP e WSDL - web
			// service p/ devolver os livros do sistema a partir do tiotulo.
			// O EJB usa o JAX-WS para publicar os Web Services.
public class LivrariaWS {

	@Inject
	private LivroDao dao;

	public List<Livro> getLivrosPeloNome(String nome) {

		System.out.println("LivrariaWS: procurando pelo nome " + nome);

		// chamando: http://localhost:8080/livraria/LivrariaWS
		// nao funciona pois soap usa requisição post eo navegador usa requisição get
		// utilizar

		// http://localhost:8080/livraria/LivrariaWS?wsdl //definição do serviço ou a
		// interface do serviço.

		// aqui usaremos o DAO para executar a pesquisa
		return dao.livrosPeloNome(nome);

	}
}
