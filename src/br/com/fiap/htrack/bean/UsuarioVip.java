package br.com.fiap.htrack.bean;

import java.util.Date;

/**
 * Classe que implementa os usuários VIP/Pagantes.
 * @author Caian Henrique Nunes França - caianhnf2@hotmail.com
 * @author Caroline Maki Nagata - caroline.nagata@gmail.com
 * @author Cilene Alves Silva - eusouaci@yahoo.com.br
 * @author Rafael Pereira de Aguiar - rafaelpaguiar@gmail.com
 * @author Paschoalin Campos de Castro - vinicius.castro@eldoradobrasil.com.br 
 * @version 1.0
 */

public class UsuarioVip extends Usuario {
	
		private Date dtPagamento;
		private double vlPlano;
		private double vlAltura;
		

		public UsuarioVip() {
		}

		public UsuarioVip(int idUsuario, String nmUsuario, Date dtNascimento, Date dtInclusao, String nmLogin, String txEmail, double vlAltura, double vlPlano, Date dtPagamento) {
			super(idUsuario, nmUsuario, dtNascimento, dtInclusao, nmLogin, txEmail);
			this.dtPagamento = dtPagamento;
			this.vlPlano = vlPlano;
			this.vlAltura = vlAltura;
		}
		
		public UsuarioVip(int idUsuario, String nmUsuario, Date dtNascimento, Date dtInclusao, String nmLogin,
				String txSenha, String txEmail, double vlAltura, double vlPlano, Date dtPagamento) {
			super(idUsuario, nmUsuario, dtNascimento, dtInclusao, nmLogin, txSenha, txEmail);
			this.dtPagamento = dtPagamento;
			this.vlPlano = vlPlano;
			this.vlAltura = vlAltura;
		}

		public Date getDtPagamento() {
			return dtPagamento;
		}

		public void setDtPagamento(Date dtPagamento) {
			this.dtPagamento = dtPagamento;
		}

		public double getVlPlano() {
			return vlPlano;
		}

		public void setVlPlano(double vlPlano) {
			this.vlPlano = vlPlano;
		}

		public double getVlAltura() {
			return vlAltura;
		}

		public void setVlAltura(double vlAltura) {
			this.vlAltura = vlAltura;
		}


		
}
