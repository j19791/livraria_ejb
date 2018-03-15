package br.com.caelum.livraria.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivrariaException;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorService { // controller/dominio: classe intermediaria entre bean (s/ jta) e autordao
							// (c/jta)
	// com controle de transacao JTA MANDATORY no dao, o dao não poderá abrir uma
	// transação
	// autorservice q vai abrir a transacao

	@Inject
	AutorDao dao;

	public void adiciona(Autor autor) {// retirado throws LivrariaException - extends RuntimeException fica unchecked
		this.dao.salva(autor);

		throw new LivrariaException();
		// Ao lançar a exceção LivrariaException é preciso deixar o tratamento
		// explícito. Essa exceção é do tipo checked, ou seja, é necessário o uso do
		// try-catch ou throws. Não tem rollback. Application Exception. Que é uma erro
		// que pode acontecer durante a vida da aplicação e é relacionado ao domínio.
	}

	public List<Autor> todosAutores() {
		return this.dao.todosAutores();
	}
}
