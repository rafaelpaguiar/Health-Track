package br.com.fiap.htrack.teste;

/**
 * Classe de teste dos DAO de refeição, favor descomentar as linhas para testar.
 * @author Caian Henrique Nunes França - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Vinícius Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.fiap.htrack.bean.Refeicao;
import br.com.fiap.htrack.dao.RefeicaoDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

public class TesteRefeicaoDAO {

	public static void main(String[] args) throws ParseException {

		RefeicaoDAO dao = DAOFactory.getRefeicaoDAO();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

//		/* Cadastrar um peso */
		Refeicao refeicao = new Refeicao(0,sdf1.parse("19/09/2021"), "Salada, frango grelahdo, grão de bico", 261, 64);
////		try {
////			dao.cadastrar(refeicao);
////			System.out.println("Refeição cadastrada.");
////		} catch (DBException e) {
////			e.printStackTrace();
////		}
//
//		/* Listar os pesos de um usuário */
//		List<Refeicao> lista = dao.listar(63);
//		for (Refeicao refeicaoDaLista : lista) {
//			System.out.println(refeicaoDaLista);
//		}

		/* Buscar e atualizar uma refeição pelo id */
		refeicao = dao.buscar(22);
		refeicao.setDsRefeicao("Abobrinha");
		refeicao.setVlCaloriasConsumidas(221);
		try {
			dao.atualizar(refeicao);
			System.out.println("Refeição atualizada.");
		} catch (DBException e) {
			e.printStackTrace();
		}

//		/* Remover uma pressão arterial pelo id */
//		try {
//			dao.remover(24);
//			System.out.println("Refeição removida.");
//		} catch (DBException e) {
//			e.printStackTrace();
//		}
	}
}
