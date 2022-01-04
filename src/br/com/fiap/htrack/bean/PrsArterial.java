package br.com.fiap.htrack.bean;

import java.util.Date;

/**
 * Classe que implementa o objeto pressão arterial e seus métodos.
 * @author Caian Henrique Nunes França - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Vinícius Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

public class PrsArterial {
	
	private int idPrsArterial;
	private Date dtPrsArterial;
	private double vlPrsArterialMin;
	private double vlPrsArterialMax;
	private int idUsuario;

	public PrsArterial() {
	}
	
	public PrsArterial(int idPrsArterial, Date dtPrsArterial, double vlPrsArterialMin, double vlPrsArterialMax,
			int idUsuario) {
		this.idPrsArterial = idPrsArterial;
		this.dtPrsArterial = dtPrsArterial;
		this.vlPrsArterialMin = vlPrsArterialMin;
		this.vlPrsArterialMax = vlPrsArterialMax;
		this.idUsuario = idUsuario;
	}

	public int getIdPrsArterial() {
		return idPrsArterial;
	}

	public void setIdPrsArterial(int idPrsArterial) {
		this.idPrsArterial = idPrsArterial;
	}

	public Date getDtPrsArterial() {
		return dtPrsArterial;
	}

	public void setDtPrsArterial(Date dtPrsArterial) {
		this.dtPrsArterial = dtPrsArterial;
	}

	public double getVlPrsArterialMin() {
		return vlPrsArterialMin;
	}

	public void setVlPrsArterialMin(double vlPrsArterialMin) {
		this.vlPrsArterialMin = vlPrsArterialMin;
	}

	public double getVlPrsArterialMax() {
		return vlPrsArterialMax;
	}

	public void setVlPrsArterialMax(double vlPrsArterialMax) {
		this.vlPrsArterialMax = vlPrsArterialMax;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	

}
