package br.com.fiap.htrack.bean;

/**
 * Classe que implementa os objestos de usu�rios gratuitos.
 * @author Caian Henrique Nunes Fran�a - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Vin�cius Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

import java.util.Date;

public class UsuarioGratuito extends Usuario {
	
	private double vlAltura;
	private double vlPlano;

	public UsuarioGratuito() {}
	
	public UsuarioGratuito(int idUsuario, String nmUsuario, Date dtNascimento, Date dtInclusao, String nmLogin, String txEmal, double vlAltura) {
		super(idUsuario, nmUsuario, dtNascimento, dtInclusao, nmLogin, txEmal);
		this.vlAltura = vlAltura;
	}
	
	public UsuarioGratuito(int idUsuario, String nmUsuario, Date dtNascimento, Date dtInclusao, String nmLogin, String txSenha,
			String txEmal, double vlAltura) {
		super(idUsuario, nmUsuario, dtNascimento, dtInclusao, nmLogin, txSenha, txEmal);
		this.vlAltura = vlAltura;
	}

	public double getVlAltura() {
		return vlAltura;
	}

	public void setVlAltura(double vlAltura) {
		this.vlAltura = vlAltura;
	}

	public double getVlPlano() {
		return vlPlano;
	}

	public void setVlPlano(double vlPlano) {
		this.vlPlano = vlPlano;
	}
	
	
	
}
