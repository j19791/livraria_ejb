package br.com.caelum.livraria.dao;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true) // por padrão, Application exception não gera rollback. Declarando
										// @ApplicationException podemos deixar ela unchecked. Isso significa que não
										// precisamos estender a classe Exception e sim RuntimeException
public class LivrariaException extends RuntimeException {

}
