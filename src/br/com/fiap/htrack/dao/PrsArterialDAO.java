package br.com.fiap.htrack.dao;

import java.util.List;

import br.com.fiap.htrack.bean.PrsArterial;
import br.com.fiap.htrack.exception.DBException;


public interface PrsArterialDAO {
	void cadastrar(PrsArterial prsArterial) throws DBException;
	PrsArterial buscar(int id);
	PrsArterial buscarUltimo(int id);
	List<PrsArterial> listar(int id);
	void remover(int id) throws DBException;
	void atualizar(PrsArterial prsArterial) throws DBException;
}
