package br.com.fiap.htrack.dao;

import java.util.List;

import br.com.fiap.htrack.bean.Peso;
import br.com.fiap.htrack.exception.DBException;


public interface PesoDAO {
	void cadastrar(Peso	peso) throws DBException;
	Peso buscar(int id);
	Peso buscarUltimo(int id);
	List<Peso> listar(int id);
	void remover(int id) throws DBException;
	void atualizar(Peso peso) throws DBException;
}
