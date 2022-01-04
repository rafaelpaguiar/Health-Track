package br.com.fiap.htrack.teste;

/**
 * Classe de teste dos DAO de Exercicios, favor descomentar as linhas para testar.
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

import br.com.fiap.htrack.bean.Exercicio;
import br.com.fiap.htrack.dao.ExercicioDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

public class TesteExercicioDAO {

	public static void main(String[] args) throws ParseException {

		ExercicioDAO dao = DAOFactory.getExercicioDAO();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		/* Cadastrar um exercício */
		Exercicio exercicio = new Exercicio(0, "Musculação", sdf1.parse("19/09/2021"), 70, 417, 64);

		try {
			dao.cadastrar(exercicio);
			System.out.println("Exercício cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		/* Listar os exercicícios de um usuário */
		List<Exercicio> lista = dao.listar(64);
		for (Exercicio exercicioDaLista : lista) {
			System.out.println(exercicioDaLista);
		}

		/* Buscar e atualizar um exercício pelo id */

		exercicio = dao.buscar(47);
		exercicio.setNmExercicio("Natação");
		exercicio.setGastoCalorico(450);
		try {
			dao.atualizar(exercicio);
			System.out.println("Exercício atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		/* Remover um exercício pelo id */
		try {
			dao.remover(44);
			System.out.println("Exercício removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}

	}
}
