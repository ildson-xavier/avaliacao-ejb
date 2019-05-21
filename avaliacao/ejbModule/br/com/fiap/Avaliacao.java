package br.com.fiap;

import java.util.List;

public interface Avaliacao {

   public List<String> obterQuestoes(int codigoAvaliacao);
   public void removerEJB();
}
