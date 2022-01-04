package br.com.fiap.htrack.dao.impl;

/**
 * Classe que implementa métodos DAO do objeto peso (CRUD).
 * @author Caian Henrique Nunes França - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Vinícius Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.htrack.bean.Peso;
import br.com.fiap.htrack.dao.PesoDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.singleton.ConnectionManager;

public class OraclePesoDAO implements PesoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HTK_PESO (id_peso, t_htk_usuario_id_user, dt_peso, vl_peso) VALUES (SQ_PESO.NEXTVAL, ?, TO_DATE(?), ?)";

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, peso.getIdUsuario());

			java.sql.Date dtPeso = new java.sql.Date(peso.getDtPeso().getTime());
			stmt.setDate(2, dtPeso);

			stmt.setDouble(3, peso.getVlPeso());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Peso> listar(int id) {

		List<Peso> lista = new ArrayList<Peso>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_PESO WHERE T_HTK_USUARIO_ID_USER = ? ORDER BY DT_PESO DESC");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idPeso = rs.getInt("ID_PESO");
				java.sql.Date dtPeso = rs.getDate("DT_PESO");
				double vlPeso = rs.getDouble("VL_PESO");

				Peso peso = new Peso(idPeso, dtPeso, vlPeso, id);
				lista.add(peso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public void remover(int id) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HTK_PESO WHERE ID_PESO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Peso buscar(int id) {

		Peso peso = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_PESO WHERE ID_PESO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				java.sql.Date dtPeso = rs.getDate("DT_PESO");
				double vlPeso = rs.getDouble("VL_PESO");

				peso = new Peso(id, dtPeso, vlPeso, idUsuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peso;
	}

	@Override
	public void atualizar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HTK_PESO SET VL_PESO = ?, DT_PESO = TO_DATE(?)"
					+ " WHERE ID_PESO = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setDouble(1, peso.getVlPeso());

			java.sql.Date dtPeso = new java.sql.Date(peso.getDtPeso().getTime());
			stmt.setDate(2, dtPeso);
			stmt.setInt(3, peso.getIdPeso());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Peso buscarUltimo(int id) {

		Peso peso = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_PESO WHERE T_HTK_USUARIO_ID_USER = ? AND ROWNUM = 1 ORDER BY DT_PESO DESC");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				java.sql.Date dtPeso = rs.getDate("DT_PESO");
				double vlPeso = rs.getDouble("VL_PESO");

				peso = new Peso(id, dtPeso, vlPeso, idUsuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peso;
	}

}
