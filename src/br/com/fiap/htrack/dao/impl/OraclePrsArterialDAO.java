package br.com.fiap.htrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.htrack.bean.PrsArterial;
import br.com.fiap.htrack.dao.PrsArterialDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.singleton.ConnectionManager;

public class OraclePrsArterialDAO implements PrsArterialDAO {

	private Connection conexao;

	@Override
	public void cadastrar(PrsArterial prsArterial) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HTK_PRES_ARTERIAL (id_pres_arterial, t_htk_usuario_id_user, dt_pres_arterial, vl_pr_arterial_min, vl_pr_arterial_max) VALUES (SQ_PESO.NEXTVAL, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?)";

			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, prsArterial.getIdUsuario());

			java.sql.Date dtPrsArterial = new java.sql.Date(prsArterial.getDtPrsArterial().getTime());
			stmt.setDate(2, dtPrsArterial);
			stmt.setDouble(3, prsArterial.getVlPrsArterialMin());
			stmt.setDouble(4, prsArterial.getVlPrsArterialMax());
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
	public List<PrsArterial> listar(int id) {

		List<PrsArterial> lista = new ArrayList<PrsArterial>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_PRES_ARTERIAL WHERE T_HTK_USUARIO_ID_USER = ? ORDER BY DT_PRES_ARTERIAL DESC");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idPrsArterial = rs.getInt("ID_PRES_ARTERIAL");
				java.sql.Date dtPrsArterial = rs.getDate("DT_PRES_ARTERIAL");
				double vlPrsArterialMin = rs.getDouble("VL_PR_ARTERIAL_MIN");
				double vlPrsArterialMax = rs.getDouble("VL_PR_ARTERIAL_MAX");

				PrsArterial prsArterial = new PrsArterial(idPrsArterial, dtPrsArterial, vlPrsArterialMin, vlPrsArterialMax, id);
				lista.add(prsArterial);
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
			String sql = "DELETE FROM T_HTK_PRES_ARTERIAL WHERE ID_PRES_ARTERIAL = ?";
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
	public PrsArterial buscar(int id) {

		PrsArterial prsArterial = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_PRES_ARTERIAL WHERE ID_PRES_ARTERIAL = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				java.sql.Date dtPrsArterial = rs.getDate("DT_PRES_ARTERIAL");
				double vlPrsArterialMin = rs.getDouble("VL_PR_ARTERIAL_MIN");
				double vlPrsArterialMax = rs.getDouble("VL_PR_ARTERIAL_MAX");

				prsArterial = new PrsArterial(id, dtPrsArterial, vlPrsArterialMin, vlPrsArterialMax, idUsuario);
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
		return prsArterial;
	}

	@Override
	public void atualizar(PrsArterial prsArterial) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HTK_PRES_ARTERIAL SET VL_PR_ARTERIAL_MIN = ?, VL_PR_ARTERIAL_MAX = ?, DT_PRES_ARTERIAL = TO_DATE(?, 'DD/MM/YYYY')"
					+ " WHERE ID_PRES_ARTERIAL = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setDouble(1, prsArterial.getVlPrsArterialMin());
			stmt.setDouble(2, prsArterial.getVlPrsArterialMax());

			java.sql.Date dtPrsArterial = new java.sql.Date(prsArterial.getDtPrsArterial().getTime());
			
			stmt.setDate(3, dtPrsArterial);
			stmt.setInt(4, prsArterial.getIdPrsArterial());

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
	public PrsArterial buscarUltimo(int id) {

		PrsArterial prsArterial = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_PRES_ARTERIAL WHERE T_HTK_USUARIO_ID_USER = ? AND ROWNUM = 1 ORDER BY DT_PRES_ARTERIAL");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("T_HTK_USUARIO_ID_USER");
				java.sql.Date dtPrsArterial = rs.getDate("DT_PRES_ARTERIAL");
				double vlPrsArterialMin = rs.getDouble("VL_PR_ARTERIAL_MIN");
				double vlPrsArterialMax = rs.getDouble("VL_PR_ARTERIAL_MAX");

				prsArterial = new PrsArterial(id, dtPrsArterial, vlPrsArterialMin, vlPrsArterialMax, idUsuario);
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
		return prsArterial;
	}
	
}
