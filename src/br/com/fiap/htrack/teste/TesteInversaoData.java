package br.com.fiap.htrack.teste;

import java.text.SimpleDateFormat;

public class TesteInversaoData {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dt_nasc = "2017-04-30";
		
		String dt_nasc_dia = dt_nasc.substring(8, 10);
		String dt_nasc_mes = dt_nasc.substring(5, 7);
		String dt_nasc_ano = dt_nasc.substring(0, 4);
		
		dt_nasc = dt_nasc_dia + "/" + dt_nasc_mes + "/" + dt_nasc_ano;
		System.out.println(dt_nasc);
		
	}

}
