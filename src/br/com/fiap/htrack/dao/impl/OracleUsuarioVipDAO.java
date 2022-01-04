package br.com.fiap.htrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.htrack.bean.UsuarioVip;
import br.com.fiap.htrack.dao.UsuarioVipDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.singleton.ConnectionManager;

public class OracleUsuarioVipDAO implements UsuarioVipDAO {
	private Connection conexao;

	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void cadastrar(UsuarioVip usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HTK_USUARIO (" + "id_user," + "nm_usuario," + "dt_nascimento," + "dt_inclusao,"
					+ "nm_login," + "tx_senha," + "tx_email," + "vl_altura," + "vl_plano," + "dt_pagamento"
					+ ") VALUES (SQ_USUARIO.NEXTVAL,?,TO_DATE(?),TO_DATE(?),?,?,?,?,?,TO_DATE(?))";

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNmUsuario());

			java.sql.Date dt_nascimento = new java.sql.Date(usuario.getDtNascimento().getTime());
			java.sql.Date dt_inclusao = new java.sql.Date(usuario.getDtInclusao().getTime());
			java.sql.Date dt_pagamento = new java.sql.Date(usuario.getDtPagamento().getTime());
			stmt.setString(4, usuario.getNmLogin());
			stmt.setString(5, usuario.getTxSenha());
			stmt.setString(6, usuario.getTxEmail());
			stmt.setDouble(7, usuario.getVlAltura());
			stmt.setDouble(8, usuario.getVlPlano());

			stmt.setDate(2, dt_nascimento);
			stmt.setDate(3, dt_inclusao);
			stmt.setDate(9, dt_pagamento);

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
	public List<UsuarioVip> listar() {

		List<UsuarioVip> lista = new ArrayList<UsuarioVip>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_USUARIO");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idUsuario = rs.getInt("ID_USER");
				String nmUsuario = rs.getString("NM_USUARIO");
				java.sql.Date dtNascimento = rs.getDate("DT_NASCIMENTO");
				java.sql.Date dtInclusao = rs.getDate("DT_INCLUSAO");
				String nmLogin = rs.getString("NM_LOGIN");
				String txSenha = rs.getString("TX_SENHA");
				String txEmal = rs.getString("TX_EMAIL");
				double vlAltura = rs.getDouble("VL_ALTURA");
				double vlPlano = rs.getDouble("VL_PLANO");
				java.sql.Date dtPagamento = rs.getDate("DT_PAGAMENTO");

				UsuarioVip usuario = new UsuarioVip(idUsuario, nmUsuario, dtNascimento, dtInclusao, nmLogin, txSenha,
						txEmal, vlPlano, vlAltura, dtPagamento);
				lista.add(usuario);
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
			String sql = "DELETE FROM T_HTK_USUARIO WHERE ID_USER = ?";
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
	public UsuarioVip buscar(int id) {

		UsuarioVip usuarioVip = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_USUARIO WHERE ID_USER = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("ID_USER");
				String nmUsuario = rs.getString("NM_USUARIO");
				java.sql.Date dtNascimento = rs.getDate("DT_NASCIMENTO");
				java.sql.Date dtInclusao = rs.getDate("DT_INCLUSAO");
				String nmLogin = rs.getString("NM_LOGIN");
				String txSenha = rs.getString("TX_SENHA");
				String txEmal = rs.getString("TX_EMAIL");
				double vlAltura = rs.getDouble("VL_ALTURA");
				double vlPlano = rs.getDouble("VL_PLANO");
				java.sql.Date dtPagamento = rs.getDate("DT_PAGAMENTO");

				usuarioVip = new UsuarioVip(idUsuario, nmUsuario, dtNascimento, dtInclusao, nmLogin, txSenha, txEmal,
						vlAltura, vlPlano, dtPagamento);
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
		return usuarioVip;
	}

	@Override
	public void atualizar(UsuarioVip usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HTK_USUARIO SET nm_usuario = ?," + "    dt_nascimento = TO_DATE(?, 'DD/MM/YYYY'),"
					+ "    dt_inclusao = TO_DATE(?, 'DD/MM/YYYY')," + "    nm_login = ?," + "    tx_senha = ?,"
					+ "    tx_email = ?," + "    vl_altura = ?," + "    vl_plano = ?,"
					+ "    dt_pagamento = TO_DATE(?, 'DD/MM/YYYY')" + " WHERE ID_USER = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, usuario.getNmUsuario());

			java.sql.Date dt_nascimento = new java.sql.Date(usuario.getDtNascimento().getTime());
			java.sql.Date dt_inclusao = new java.sql.Date(usuario.getDtInclusao().getTime());
			java.sql.Date dt_pagamento = new java.sql.Date(usuario.getDtPagamento().getTime());

			stmt.setString(4, usuario.getNmLogin());
			stmt.setString(5, usuario.getTxSenha());
			stmt.setString(6, usuario.getTxEmail());
			stmt.setDouble(7, usuario.getVlAltura());
			stmt.setDouble(8, usuario.getVlPlano());

			stmt.setDate(2, dt_nascimento);
			stmt.setDate(3, dt_inclusao);
			stmt.setDate(9, dt_pagamento);

			stmt.setInt(10, usuario.getIdUsuario());

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
	public UsuarioVip validarUsuario(UsuarioVip usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UsuarioVip usuarioVip = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_USUARIO WHERE NM_LOGIN = ? AND TX_SENHA = ?");
			stmt.setString(1, usuario.getNmLogin());
			stmt.setString(2, usuario.getTxSenha());
			rs = stmt.executeQuery();

			if (rs.next()) {
				int idUsuario = rs.getInt("ID_USER");
				String nmUsuario = rs.getString("NM_USUARIO");
				java.sql.Date dtNascimento = rs.getDate("DT_NASCIMENTO");
				java.sql.Date dtInclusao = rs.getDate("DT_INCLUSAO");
				String nmLogin = rs.getString("NM_LOGIN");
				String txSenha = rs.getString("TX_SENHA");
				String txEmal = rs.getString("TX_EMAIL");
				double vlAltura = rs.getDouble("VL_ALTURA");
				double vlPlano = rs.getDouble("VL_PLANO");
				java.sql.Date dtPagamento = rs.getDate("DT_PAGAMENTO");

				usuarioVip = new UsuarioVip(idUsuario, nmUsuario, dtNascimento, dtInclusao, nmLogin, txSenha, txEmal,
						vlAltura, vlPlano, dtPagamento);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				
				if (rs != null ) {rs.close();}
				
				
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuarioVip;
	}
}
