package br.com.fiap.htrack.teste;

/**
 * Classe de teste dos DAO de peso, favor descomentar as linhas para testar.
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

import br.com.fiap.htrack.bean.Peso;
import br.com.fiap.htrack.dao.PesoDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

public class TestePesoDAO {

	public static void main(String[] args) throws ParseException {

		PesoDAO dao = DAOFactory.getPesoDAO();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		/* Cadastrar um peso */
		Peso peso = new Peso(0, sdf1.parse("19/09/2021"), 98.3, 63);
		try {
			dao.cadastrar(peso);
			System.out.println("Peso cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		/*
		 * Listar os pesos de um usuário List<Peso> lista = dao.listar(63); for (Peso
		 * pesoDaLista : lista) { System.out.println(pesoDaLista); }
		 * 
		 * Buscar e atualizar um peso pelo id peso = dao.buscar(41);
		 * peso.setVlPeso(105); try { dao.atualizar(peso);
		 * System.out.println("Peso atualizado."); } catch (DBException e) {
		 * e.printStackTrace(); }
		 * 
		 * Remover um peso pelo id try { dao.remover(41);
		 * System.out.println("Peso removido."); } catch (DBException e) {
		 * e.printStackTrace(); }
		 */

	}
}
