package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

//Um interceptador é útil para relacionar uma funcionalidades com vários Session Beans. No lugar de repetir a funcionalidade em cada Session Bean, podemos centralizar o código em um lugar apenas e configurar aonde aplicar.
// é preciso fazer um monitoramento: pode ser necessário monitorar o tempo de acesso ao banco de dados. Para tal existem os interceptadores que separam bem essa responsabilidade em um único lugar.
public class LogInterceptador {

	@AroundInvoke // deixa claro para o EJB Container que o método intercepta o fluxo
	public Object intercepta(InvocationContext context) throws Exception { // o método sempre deve retornar um Object

		long millis = System.currentTimeMillis();

		// Através InvocationContext podemos continuar a execução da aplicação, ou seja,
		// chamar o método no DAO chamada do método do dao
		Object o = context.proceed(); // O retorno desse método representa o possível retorno do método do DAO

		String metodo = context.getMethod().getName(); // nome do metodo q estamos interceptando

		String nomeClasse = context.getTarget().getClass().getSimpleName(); // nome da classe q estamos intecepatando

		System.out.println("Classe e metodo inteceptado: " + nomeClasse + "." + metodo + ". Tempo gasto:  "
				+ (System.currentTimeMillis() - millis));

		return o; // retorno do metodo dao, aonde vai ser aplicado o interceptador

	}
}
