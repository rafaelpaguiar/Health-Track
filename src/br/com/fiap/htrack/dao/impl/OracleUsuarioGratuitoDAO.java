package br.com.fiap.htrack.dao.impl;

/**
 * Classe que implementa métodos DAO do objeto usuário gratuitos (CRUD).
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
import java.util.List;

import br.com.fiap.htrack.bean.UsuarioGratuito;
import br.com.fiap.htrack.dao.UsuarioGratuitoDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.singleton.ConnectionManager;


public class OracleUsuarioGratuitoDAO implements UsuarioGratuitoDAO {

	private Connection conexao;

	SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void cadastrar(UsuarioGratuito usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_HTK_USUARIO (" + "id_user," + "nm_usuario," + "dt_nascimento," + "dt_inclusao,"
					+ "nm_login," + "tx_senha," + "tx_email,"
					+ "vl_altura) VALUES (SQ_USUARIO.NEXTVAL,?,TO_DATE(?),TO_DATE(?),?,?,?,?)";

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNmUsuario());

			java.sql.Date dt_nascimento = new java.sql.Date(usuario.getDtNascimento().getTime());
			java.sql.Date dt_inclusao = new java.sql.Date(usuario.getDtInclusao().getTime());

			stmt.setString(4, usuario.getNmLogin());
			stmt.setString(5, usuario.getTxSenha());
			stmt.setString(6, usuario.getTxEmail());
			stmt.setDouble(7, usuario.getVlAltura());

			stmt.setDate(2, dt_nascimento);
			stmt.setDate(3, dt_inclusao);

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
	public List<UsuarioGratuito> listar() {

		List<UsuarioGratuito> lista = new ArrayList<UsuarioGratuito>();
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

				UsuarioGratuito usuarioApp = new UsuarioGratuito(idUsuario, nmUsuario, dtNascimento, dtInclusao,
						nmLogin, txSenha, txEmal, vlAltura);
				lista.add(usuarioApp);
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
	public UsuarioGratuito buscar(int id) {

		UsuarioGratuito usuarioGratuito = null;
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

				usuarioGratuito = new UsuarioGratuito(idUsuario, nmUsuario, dtNascimento, dtInclusao, nmLogin, txSenha,
						txEmal, vlAltura);
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
		return usuarioGratuito;
	}

	@Override
	public void atualizar(UsuarioGratuito usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_HTK_USUARIO SET nm_usuario = ?," + "    dt_nascimento = TO_DATE(?, 'DD/MM/YYYY'),"
					+ "    dt_inclusao = TO_DATE(?, 'DD/MM/YYYY')," + "    nm_login = ?," + "    tx_senha = ?,"
					+ "    tx_email = ?," + "    vl_altura = ?" + " WHERE ID_USER = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, usuario.getNmUsuario());

			java.sql.Date dt_nascimento = new java.sql.Date(usuario.getDtNascimento().getTime());
			java.sql.Date dt_inclusao = new java.sql.Date(usuario.getDtInclusao().getTime());

			stmt.setString(4, usuario.getNmLogin());
			stmt.setString(5, usuario.getTxSenha());
			stmt.setString(6, usuario.getTxEmail());
			stmt.setDouble(7, usuario.getVlAltura());

			stmt.setDate(2, dt_nascimento);
			stmt.setDate(3, dt_inclusao);
			stmt.setInt(8, usuario.getIdUsuario());

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
	public boolean validarUsuario(UsuarioGratuito usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_HTK_USUARIO WHERE NM_LOGIN = ? AND TX_SENHA = ?");
			stmt.setString(1, usuario.getNmLogin());
			stmt.setString(2, usuario.getTxSenha());
			rs = stmt.executeQuery();

			if (rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	

}
