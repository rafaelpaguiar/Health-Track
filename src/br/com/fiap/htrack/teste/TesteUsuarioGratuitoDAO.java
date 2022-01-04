package br.com.fiap.htrack.teste;

/**
 * Classe de teste dos DAO de Usuarios gratuitos, favor descomentar as linhas para testar.
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

import br.com.fiap.htrack.bean.UsuarioGratuito;
import br.com.fiap.htrack.dao.UsuarioGratuitoDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

public class TesteUsuarioGratuitoDAO {

	public static void main(String[] args) throws ParseException {

		UsuarioGratuitoDAO dao = DAOFactory.getUsuarioGratuitoDAO();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		// Cadastrar um usuário gratuito
		UsuarioGratuito usuarioGratuito = new UsuarioGratuito(0, "Rafael Aguiar", sdf1.parse("18/01/1984"),
				sdf1.parse("13/11/1984"), "rafael", "senha", "rafael@gmail.com", 1.58);
		try {
			dao.cadastrar(usuarioGratuito);
			System.out.println("Usuário cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		// Listar os usuários gratuitos
		List<UsuarioGratuito> lista = dao.listar();
		for (UsuarioGratuito usuariosDaLista : lista) {
			System.out.println(usuariosDaLista);
		}

		/* Buscar e atualizar um usuário gratuito pelo id */

		usuarioGratuito = dao.buscar(61);
		usuarioGratuito.setTxEmail("rafaelpaguiar@gmail.com");
		usuarioGratuito.setTxSenha("passoword");
		try {
			dao.atualizar(usuarioGratuito);
			System.out.println("Usuário atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		/* Remover um usuário gratuito pelo id */

		try {
			dao.remover(61);
			System.out.println("Usuário removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}

	}

}
