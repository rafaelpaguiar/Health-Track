package br.com.fiap.htrack.bean;

/**
 * Classe que implementa o objeto peso e seus métodos.
 * @author Caian Henrique Nunes França - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Vinícius Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

import java.util.Date;

public class Peso {

	private int idPeso;
	private Date dtPeso;
	private double vlPeso;
	private int idUsuario;
	
	public Peso() {}
	
	public Peso(int idPeso, Date dtPeso, double vlPeso, int idUsuario) {
		this.idPeso = idPeso;
		this.dtPeso = dtPeso;
		this.vlPeso = vlPeso;
		this.idUsuario = idUsuario;
	}

	public int getIdPeso() {
		return idPeso;
	}
	
	public void setIdPeso(int idPeso) {
		this.idPeso = idPeso;
	}
	
	public Date getDtPeso() {
		return dtPeso;
	}
	
	public void setDtPeso(Date dtPeso) {
		this.dtPeso = dtPeso;
	}
	
	public double getVlPeso() {
		return vlPeso;
	}
	
	public void setVlPeso(double vlPeso) {
		this.vlPeso = vlPeso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	
}