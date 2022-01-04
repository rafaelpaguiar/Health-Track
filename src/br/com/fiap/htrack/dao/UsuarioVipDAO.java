package br.com.fiap.htrack.dao;

import java.util.List;

import br.com.fiap.htrack.bean.UsuarioVip;
import br.com.fiap.htrack.exception.DBException;


public interface UsuarioVipDAO {
	void cadastrar(UsuarioVip usuarioVip) throws DBException;
	UsuarioVip buscar(int id);
	List<UsuarioVip> listar();
	void remover(int id) throws DBException;
	void atualizar(UsuarioVip usuarioVip) throws DBException;
	UsuarioVip validarUsuario(UsuarioVip usuario);
}
