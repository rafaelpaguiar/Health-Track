package br.com.fiap.htrack.dao.impl;

/**
 * Classe que implementa métodos DAO do objeto refeição (CRUD).
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

import br.com.fiap.htrack.bean.Refeicao;
import br.com.fiap.htrack.dao.RefeicaoDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.singleton.ConnectionManager;

public class OracleRefeicaoDAO implements RefeicaoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Refeicao refeicao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HTK_REFEICAO (id_refeicao, dt_refeicao, ds_alimentos_ingeridos, vl_calorias_consumidas, t_htk_usuario_id_user ) VALUES"
					+ "(SQ_REFEICAO.NEXTVAL, TO_DATE(?), ?, ?, ?)";

			stmt = conexao.prepareStatement(sql);

			java.sql.Date dtRefeicao = new java.sql.Date(refeicao.getDtRefeicao().getTime());
			stmt.setDate(1, dtRefeicao);

			stmt.setString(2, refeicao.getDsRefeicao());
			stmt.setInt(3, refeicao.getVlCaloriasConsumidas());
			stmt.setInt(4, refeicao.getIdUsuario());

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
	public List<Refeicao> listar(int id) {

		List<Refeicao> lista = new ArrayList<Refeicao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_REFEICAO WHERE T_HTK_USUARIO_ID_USER = ?  ORDER BY DT_REFEICAO DESC");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idRefeicao = rs.getInt("ID_REFEICAO");
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				java.sql.Date dtRefeicao = rs.getDate("DT_REFEICAO");
				String dsRefeicao = rs.getString("DS_ALIMENTOS_INGERIDOS");
				int vlCaloriasConsumidas = rs.getInt("VL_CALORIAS_CONSUMIDAS");

				Refeicao refeicao = new Refeicao(idRefeicao, dtRefeicao, dsRefeicao, vlCaloriasConsumidas, idUsuario);
				lista.add(refeicao);
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
	public void remover(int idRefeicao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HTK_REFEICAO WHERE ID_REFEICAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idRefeicao);
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
	public Refeicao buscar(int id) {

		Refeicao refeicao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_REFEICAO WHERE ID_REFEICAO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("ID_REFEICAO");
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				java.sql.Date dtRefeicao = rs.getDate("DT_REFEICAO");
				String dsRefeicao = rs.getString("DS_ALIMENTOS_INGERIDOS");
				int vlCaloriasConsumidas = rs.getInt("VL_CALORIAS_CONSUMIDAS");

				refeicao = new Refeicao(id, dtRefeicao, dsRefeicao, vlCaloriasConsumidas, idUsuario);
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
		return refeicao;
	}

	@Override
	public void atualizar(Refeicao refeicao) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HTK_REFEICAO SET dt_refeicao = TO_DATE(?), ds_alimentos_ingeridos = ?, vl_calorias_consumidas = ? WHERE ID_REFEICAO = ?";
			stmt = conexao.prepareStatement(sql);

			java.sql.Date dtRefeicao = new java.sql.Date(refeicao.getDtRefeicao().getTime());
			stmt.setDate(1, dtRefeicao);
			stmt.setString(2, refeicao.getDsRefeicao());
			stmt.setInt(3, refeicao.getVlCaloriasConsumidas());
			System.out.println(refeicao.getIdRefeicao());
			stmt.setInt(4, refeicao.getIdRefeicao());

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
	public Refeicao buscarUltimo(int id) {

		Refeicao refeicao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_REFEICAO WHERE T_HTK_USUARIO_ID_USER = ? AND ROWNUM = 1 ORDER BY DT_REFEICAO");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("ID_REFEICAO");
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				java.sql.Date dtRefeicao = rs.getDate("DT_REFEICAO");
				String dsRefeicao = rs.getString("DS_ALIMENTOS_INGERIDOS");
				int vlCaloriasConsumidas = rs.getInt("VL_CALORIAS_CONSUMIDAS");

				refeicao = new Refeicao(id, dtRefeicao, dsRefeicao, vlCaloriasConsumidas, idUsuario);
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
		return refeicao;
	}

}
