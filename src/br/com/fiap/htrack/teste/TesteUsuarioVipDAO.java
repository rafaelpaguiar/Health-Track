package br.com.fiap.htrack.teste;

/**
 * Classe de teste dos DAO de Usuarios VIP, favor descomentar as linhas para testar.
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

import br.com.fiap.htrack.bean.UsuarioVip;
import br.com.fiap.htrack.dao.UsuarioVipDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

public class TesteUsuarioVipDAO {

	public static void main(String[] args) throws ParseException {

		UsuarioVipDAO dao = DAOFactory.getUsuarioVipDAO();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		// Cadastrar um usuário vip
		UsuarioVip usuarioVip = new UsuarioVip(0, "Marcelo Souza", sdf1.parse(" 27/09/1996"), sdf1.parse("15/09/2021"),
				"m.souza", "m.souza@1996", "marcelo.souza@gmail.com", 1.86, 49.90, sdf1.parse("15/09/2021"));

		try {
			dao.cadastrar(usuarioVip);
			System.out.println("Usuário cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		// Listar os usuários vips
		List<UsuarioVip> lista = dao.listar();
		for (UsuarioVip usuariosDaLista : lista) {
			System.out.println(usuariosDaLista);
		}

		// Buscar e atualizar um usuário vip pelo id
		usuarioVip = dao.buscar(86);
//		usuarioVip.setTxEmail("marcelo_souza@gmail.com");
//		usuarioVip.setTxSenha("senha");
//		try {
//			dao.atualizar(usuarioVip);
//			System.out.println("Usuário atualizado.");
//		} catch (DBException e) {
//			e.printStackTrace();
//		}
		

		System.out.println(usuarioVip.getDtNascimento());
		
		/* Remover um usuário gratuito pelo id */
//		try {
//			dao.remover(62);
//			System.out.println("Usuário removido.");
//		} catch (DBException e) {
//			e.printStackTrace();
//		}

	}
}