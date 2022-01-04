package br.com.fiap.htrack.dao;

import java.util.List;

import br.com.fiap.htrack.bean.UsuarioGratuito;
import br.com.fiap.htrack.exception.DBException;


public interface UsuarioGratuitoDAO {
	void cadastrar(UsuarioGratuito usuarioGratuito) throws DBException;
	UsuarioGratuito buscar(int id);
	List<UsuarioGratuito> listar();
	void remover(int id) throws DBException;
	void atualizar(UsuarioGratuito usuarioGratuito) throws DBException;
	boolean validarUsuario(UsuarioGratuito usuario);
}
