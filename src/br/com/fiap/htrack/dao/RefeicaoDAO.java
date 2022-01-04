package br.com.fiap.htrack.dao;

import java.util.List;

import br.com.fiap.htrack.bean.Refeicao;
import br.com.fiap.htrack.exception.DBException;


public interface RefeicaoDAO {
	void cadastrar(Refeicao refeicao) throws DBException;
	Refeicao buscar(int id);
	Refeicao buscarUltimo(int id);
	List<Refeicao> listar(int id);
	void remover(int id) throws DBException;
	void atualizar(Refeicao refeicao) throws DBException;
}
