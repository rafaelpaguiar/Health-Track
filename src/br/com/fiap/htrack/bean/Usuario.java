package br.com.fiap.htrack.bean;

/**
 * Classe que implementa o objeto abstrato usuário e seus métodos.
 * @author Caian Henrique Nunes França - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Vinícius Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

import java.util.Date;

import br.com.fiap.htrack.util.CriptografiaUtil;

public abstract class Usuario {

	protected int idUsuario;
	protected String nmUsuario;
	protected Date dtNascimento;
	protected Date dtInclusao;
	protected String nmLogin;
	protected String txSenha;
	protected String txEmail;

	public Usuario() {
	}


	public Usuario(int idUsuario, String nmUsuario, Date dtNascimento, Date dtInclusao, String nmLogin, String txEmail) {
		this.idUsuario = idUsuario;
		this.nmUsuario = nmUsuario;
		this.dtNascimento = dtNascimento;
		this.dtInclusao = dtInclusao;
		this.nmLogin = nmLogin;
		this.txEmail = txEmail;
	}
	
	public Usuario(int idUsuario, String nmUsuario, Date dtNascimento, Date dtInclusao, String nmLogin, String txSenha,
			String txEmail) {
		this.idUsuario = idUsuario;
		this.nmUsuario = nmUsuario;
		this.dtNascimento = dtNascimento;
		this.dtInclusao = dtInclusao;
		this.nmLogin = nmLogin;
		this.txSenha = txSenha;
		this.txEmail = txEmail;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Date getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public String getNmLogin() {
		return nmLogin;
	}

	public void setNmLogin(String nmLogin) {
		this.nmLogin = nmLogin;
	}

	public String getTxSenha() {
		return txSenha;
	}

	public void setTxSenha(String txSenha) {
		try {
			this.txSenha = CriptografiaUtil.criptografar(txSenha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTxEmail() {
		return txEmail;
	}

	public void setTxEmail(String txEmail) {
		this.txEmail = txEmail;
	}

}
