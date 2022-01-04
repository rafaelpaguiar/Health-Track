package br.com.fiap.htrack.dao.impl;

/**
 * Classe que implementa métodos DAO do objeto exercícios (CRUD).
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.htrack.bean.Exercicio;
import br.com.fiap.htrack.dao.ExercicioDAO;
import br.com.fiap.htrack.singleton.ConnectionManager;
import br.com.fiap.htrack.exception.DBException;

public class OracleExercicioDAO implements ExercicioDAO {

	private Connection conexao;

	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void cadastrar(Exercicio exercicio) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_HTK_EXERCICIO (id_exercicio, nm_exercicio, dt_exercicio, vl_duracao, vl_gasto_calorico, t_htk_usuario_id_user) VALUES (SQ_EXERCICIO.NEXTVAL, ?, TO_DATE(?), ?, ?, ?)";

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, exercicio.getNmExercicio());

			java.sql.Date dtExercicio = new java.sql.Date(exercicio.getDtExercicio().getTime());
			stmt.setDate(2, dtExercicio);

			stmt.setInt(3, exercicio.getVlDuracao());
			stmt.setInt(4, exercicio.getGastoCalorico());
			stmt.setInt(5, exercicio.getIdUsuario());
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
	public List<Exercicio> listar(int idUsuario) {

		List<Exercicio> lista = new ArrayList<Exercicio>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_EXERCICIO WHERE T_HTK_USUARIO_ID_USER = ? ORDER BY DT_EXERCICIO DESC");
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idExercicio = rs.getInt("ID_EXERCICIO");
				String nmExercicio = rs.getString("NM_EXERCICIO");
				Date dtExercicio = rs.getDate("DT_EXERCICIO");
				int vlDuracao = rs.getInt("VL_DURACAO");
				int vlGastoCalorico = rs.getInt("VL_GASTO_CALORICO");

				Exercicio exercicio = new Exercicio(idExercicio, nmExercicio, dtExercicio, vlDuracao, vlGastoCalorico,
						idUsuario);
				lista.add(exercicio);
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
	public void remover(int idExercicio) throws DBException {

		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_HTK_EXERCICIO WHERE ID_EXERCICIO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idExercicio);
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
	public Exercicio buscar(int idExercicio) {

		Exercicio exercicio = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_EXERCICIO WHERE ID_EXERCICIO = ?");
			stmt.setInt(1, idExercicio);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				String nmExercicio = rs.getString("NM_EXERCICIO");
				Date dtExercicio = rs.getDate("DT_EXERCICIO");
				int vlDuracao = rs.getInt("VL_DURACAO");
				int vlGastoCalorico = rs.getInt("VL_GASTO_CALORICO");
				exercicio = new Exercicio(idExercicio, nmExercicio, dtExercicio, vlDuracao, vlGastoCalorico, idUsuario);
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
		return exercicio;
	}

	@Override
	public void atualizar(Exercicio exercicio) {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HTK_EXERCICIO SET NM_EXERCICIO = ?, VL_GASTO_CALORICO = ?, DT_EXERCICIO = TO_DATE(?), VL_DURACAO = ? WHERE ID_EXERCICIO = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, exercicio.getNmExercicio());
			stmt.setInt(2, exercicio.getGastoCalorico());

			java.sql.Date dtExercicio = new java.sql.Date(exercicio.getDtExercicio().getTime());
			stmt.setDate(3, dtExercicio);
			
			stmt.setInt(4, exercicio.getVlDuracao());
			stmt.setInt(5, exercicio.getIdExercicio());

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
	
	public Exercicio buscarUltimo(int idExercicio) {

		Exercicio exercicio = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_EXERCICIO WHERE T_HTK_USUARIO_ID_USER = ? AND ROWNUM = 1 ORDER BY DT_EXERCICIO DESC");
			stmt.setInt(1, idExercicio);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				String nmExercicio = rs.getString("NM_EXERCICIO");
				Date dtExercicio = rs.getDate("DT_EXERCICIO");
				int vlDuracao = rs.getInt("VL_DURACAO");
				int vlGastoCalorico = rs.getInt("VL_GASTO_CALORICO");
				exercicio = new Exercicio(idExercicio, nmExercicio, dtExercicio, vlDuracao, vlGastoCalorico, idUsuario);
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
		return exercicio;
	}

}
