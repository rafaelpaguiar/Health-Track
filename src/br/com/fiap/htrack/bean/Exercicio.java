package br.com.fiap.htrack.bean;

/**
 * Classe que implementa o objeto exercício e seus métodos.
 * @author Caian Henrique Nunes França - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Vinícius Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

import java.util.Date;

public class Exercicio {

	private int idExercicio;
	private String nmExercicio;
	private Date dtExercicio;
	private int vlDuracao;
	private int gastoCalorico;
	private int idUsuario;
	
	public Exercicio() {}
	

	public Exercicio(int idExercicio, String nmExercicio, Date dtExercicio, int vlDuracao, int gastoCalorico, int idUsuario) {
		this.idExercicio = idExercicio;
		this.nmExercicio = nmExercicio;
		this.dtExercicio = dtExercicio;
		this.vlDuracao = vlDuracao;
		this.gastoCalorico = gastoCalorico; 
		this.idUsuario = idUsuario; 
	}

	public String getNmExercicio() {
		return nmExercicio;
	}


	public void setNmExercicio(String nmExercicio) {
		this.nmExercicio = nmExercicio;
	}


	public void setGastoCalorico(int gastoCalorico) {
		this.gastoCalorico = gastoCalorico;
	}


	public int getIdExercicio() {
		return idExercicio;
	}

	public void setIdExercicio(int idExercicio) {
		this.idExercicio = idExercicio;
	}

	public int getVlDuracao() {
		return vlDuracao;
	}

	public void setVlDuracao(int vlDuracao) {
		this.vlDuracao = vlDuracao;
	}


	public Date getDtExercicio() {
		return dtExercicio;
	}


	public void setDtExercicio(Date dtExercicio) {
		this.dtExercicio = dtExercicio;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public int getGastoCalorico() {
		return gastoCalorico;
	}

	
	

}

