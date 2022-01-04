package br.com.fiap.htrack.dao;

import java.util.List;

import br.com.fiap.htrack.bean.Exercicio;
import br.com.fiap.htrack.exception.DBException;


public interface ExercicioDAO {
	void cadastrar(Exercicio exercicio) throws DBException;
	Exercicio buscar(int id);
	Exercicio buscarUltimo(int id);
	List<Exercicio> listar(int id);
	void remover(int id) throws DBException;
	void atualizar(Exercicio exercio) throws DBException;
}
