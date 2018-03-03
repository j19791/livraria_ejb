package br.com.caelum.livraria.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorService { // controller/dominio: classe intermediaria entre bean (s/ jta) e autordao
							// (c/jta)
	// com controle de transacao JTA MANDATORY no dao, o dao não poderá abrir uma
	// transação
	// autorservice q vai abrir a transacao

	@Inject
	AutorDao dao;

	public void adiciona(Autor autor) {
		this.dao.salva(autor);
	}

	public List<Autor> todosAutores() {
		return this.dao.todosAutores();
	}
}
