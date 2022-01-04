package br.com.fiap.htrack.factory;

import br.com.fiap.htrack.dao.*;
import br.com.fiap.htrack.dao.impl.*;

public class DAOFactory {

	public static ExercicioDAO getExercicioDAO() {
		return new OracleExercicioDAO();
	}
	
	public static PesoDAO getPesoDAO() {
		return new OraclePesoDAO();
	}
	
	public static PrsArterialDAO getPrsArterialDAO() {
		return new OraclePrsArterialDAO();
	}
	
	public static RefeicaoDAO getRefeicaoDAO() {
		return new OracleRefeicaoDAO();
	}
	
	public static UsuarioGratuitoDAO getUsuarioGratuitoDAO() {
		return new OracleUsuarioGratuitoDAO();
	}
	
	public static UsuarioVipDAO getUsuarioVipDAO() {
		return new OracleUsuarioVipDAO();
	}
	
}