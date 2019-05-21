package br.com.fiap;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.fiap.common.Questao;
import br.com.fiap.common.Resposta;

@Stateless
@Remote
@WebService
public class AvaliacaoBean implements Avaliacao {

	@PersistenceContext(unitName = "jdbc/fiap")
	private EntityManager entityManager;

	@Override
	@WebMethod

	public List<String> obterQuestoes(@WebParam(name = "codigoAvaliacao") int codigoAvaliacao) {
		
		Integer numQuestao = 1;
		Integer numResposta = 1;
		
		StringBuilder stringBuilder = new StringBuilder();
		
		TypedQuery<Questao> query = entityManager.createQuery("SELECT q FROM Questao q WHERE codigo = :codigo",
				Questao.class);
		
		query.setParameter("codigo", codigoAvaliacao);
		
		for (Questao q : query.getResultList()) {
			
			System.out.println(q.getDescricao());
			
			stringBuilder.append(numQuestao.toString()).append(" - ").append(q.getDescricao());
			stringBuilder.append("\n");

			numQuestao++;
			
			List<Resposta> respostas = obterResposta(codigoAvaliacao);

			for (Resposta r : respostas) {
				stringBuilder.append("\t").append(numResposta.toString()).append(" - ").append(r.getDescricao());
				stringBuilder.append("\n");
				
				numResposta++;
			}
		}
		List<String> resposta = new ArrayList<>();
		resposta.add(stringBuilder.toString());
		return resposta;
	}

	private List<Resposta> obterResposta(int codigoAvaliacao) {

		TypedQuery<Resposta> query = entityManager
				.createQuery("select q from Resposta q where codigoquestao = :codigoquestao", Resposta.class);

		query.setParameter("codigoquestao", codigoAvaliacao);
		for (Resposta q : query.getResultList()) {
			System.out.println(q.getDescricao());
		}
		return query.getResultList();

	}

	@Override
	public void removerEJB() {
		// TODO Auto-generated method stub

	}

}
