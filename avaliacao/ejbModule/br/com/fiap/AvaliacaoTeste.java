package br.com.fiap;

import java.util.List;

import javax.naming.InitialContext;

public class AvaliacaoTeste {

	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		Avaliacao avaliacao = (Avaliacao) context.lookup("AvaliacaoBean/remote");
		
		List<String> te = (List<String>)avaliacao.obterQuestoes(1);
		
		for(String s : te) {
			System.out.println(s);
		}

	}

}
