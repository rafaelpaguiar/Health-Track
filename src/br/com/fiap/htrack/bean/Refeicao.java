package br.com.fiap.htrack.bean;

/**
 * Classe que implementa o objeto refeição e seus métodos.
 * @author Caian Henrique Nunes França - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Vinícius Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

import java.util.Date;

public class Refeicao {

	private int idRefeicao;
	private Date dtRefeicao;
	private String dsRefeicao;
	private int vlCaloriasConsumidas;
	private int idUsuario;
	
	public Refeicao() {}
	
	public Refeicao(int idRefeicao, Date dtRefeicao, String dsRefeicao, int vlCaloriasConsumidas, int idUsuario) {
		this.idRefeicao = idRefeicao;
		this.dtRefeicao = dtRefeicao;
		this.dsRefeicao = dsRefeicao;
		this.vlCaloriasConsumidas = vlCaloriasConsumidas;
		this.idUsuario = idUsuario;
	}
	
	public int getIdRefeicao() {
		return idRefeicao;
	}
	
	public void setIdRefeicao(int idRefeicao) {
		this.idRefeicao = idRefeicao;
	}
	
	public Date getDtRefeicao() {
		return dtRefeicao;
	}
	
	public void setDtRefeicao(Date dtRefeicao) {
		this.dtRefeicao = dtRefeicao;
	}
	
	public String getDsRefeicao() {
		return dsRefeicao;
	}
	
	public void setDsRefeicao(String dsRefeicao) {
		this.dsRefeicao = dsRefeicao;
	}
	
	public int getVlCaloriasConsumidas() {
		return vlCaloriasConsumidas;
	}
	
	public void setVlCaloriasConsumidas(int vlCaloriasConsumidas) {
		this.vlCaloriasConsumidas = vlCaloriasConsumidas;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	
	
	
}	