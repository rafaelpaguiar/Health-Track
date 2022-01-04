package br.com.fiap.htrack.teste;

/**
 * Classe de teste dos DAO de pressão arterial, favor descomentar as linhas para testar.
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

import br.com.fiap.htrack.bean.PrsArterial;
import br.com.fiap.htrack.dao.PrsArterialDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

public class TestePrsArterialDAO {

	public static void main(String[] args) throws ParseException  {
		
		PrsArterialDAO dao = DAOFactory.getPrsArterialDAO();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		// Cadastrar uma pressão arterial
		PrsArterial prsArterial = new PrsArterial(0,sdf1.parse("19/09/2021"),12,18,64);
		try {
			dao.cadastrar(prsArterial);
			System.out.println("Pressão arterial cadastrada.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		/* Listar as pressão arterial de um usuário */
		List<PrsArterial> lista = dao.listar(63);
		for (PrsArterial prsArterialDaLista : lista) {
			System.out.println(prsArterialDaLista);
		}

		/* Buscar e atualizar uma pressão arterial pelo id */
		prsArterial = dao.buscar(45);
		prsArterial.setVlPrsArterialMax(12);
		prsArterial.setVlPrsArterialMin(10);
		try {
			dao.atualizar(prsArterial);
			System.out.println("Pressão arterial atualizada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		/* Remover uma pressão arterial pelo id */
		try {
			dao.remover(48);
			System.out.println("Pressão arterial removida.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
	}

}
