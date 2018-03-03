package br.com.caelum.livraria.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.caelum.livraria.modelo.Autor;

@Model
public class AutorBean {

	private Autor autor = new Autor();

	// private AutorDao dao = new AutorDao();
	// Ao usar o EJB AutorDAo (anotado com @Stateless), não podemos mais instanciar
	// o AutorDao na mão. Estamos assumindo
	// o controle ao criar o DAO naquela linha. Nesse caso não estamos usando o
	// AutorDao como um EJB.O DAO está sendo administrado pelo EJB Container.
	// Portanto, quem cria o DAO é o EJB Container e não a minha classe.

	// @Inject // precisamos pedir ao EJB Container passar aquela instancia que ele
	// está
	// administrando - invertendo o controle ou fazendo a injeção de dependencias

	// private AutorDao dao;//sera utilizado agora AutorService

	@Inject
	private AutorService service;// classe intermediaria entre bean e o dao. Inicializa o tx

	public Autor getAutor() {
		return autor;
	}

	public void cadastra() {
		// this.dao.salva(autor);
		this.service.adiciona(autor);
		this.autor = new Autor();// p/ limpeza do campo do form
	}

	public List<Autor> getAutores() {
		// return this.dao.todosAutores();
		return this.service.todosAutores();
	}
}
