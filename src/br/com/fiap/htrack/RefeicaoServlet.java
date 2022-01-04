package br.com.fiap.htrack;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.htrack.bean.Refeicao;
import br.com.fiap.htrack.dao.RefeicaoDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

@WebServlet("/refeicao")
public class RefeicaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RefeicaoDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getRefeicaoDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String act = request.getParameter("act");

		switch (act) {
		case "add":
			cadastrar(request, response);
			System.out.println(act);
			break;
		case "edit":
			editar(request, response);
			break;
		}
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			String dsRefeicao = request.getParameter("refeicao");
			int vlCaloriasConsumidas = Integer.parseInt(request.getParameter("kcalIngerido"));

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dtRefeicao = request.getParameter("dt_refeicao");
			String dt_refeicao_dia = dtRefeicao.substring(8, 10);
			String dt_refeicao_mes = dtRefeicao.substring(5, 7);
			String dt_refeicao_ano = dtRefeicao.substring(0, 4);

			dtRefeicao = dt_refeicao_dia + "/" + dt_refeicao_mes + "/" + dt_refeicao_ano;

			Refeicao refeicao = new Refeicao(0, sdf.parse(dtRefeicao), dsRefeicao, vlCaloriasConsumidas, idUsuario);

			dao.cadastrar(refeicao);

			request.setAttribute("msg", "Refeicão cadastrada!");
			List<Refeicao> refeicoes = dao.listar(idUsuario);
			request.setAttribute("refeicoes", refeicoes);
			request.getRequestDispatcher("refeicao.jsp").forward(request, response);
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}

	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idRefeicao = Integer.parseInt(request.getParameter("idRefeicao"));
			System.out.println(idRefeicao);
			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			String dsRefeicao = request.getParameter("refeicao");
			int vlCaloriasConsumidas = Integer.parseInt(request.getParameter("kcalIngerido"));

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dtRefeicao = request.getParameter("dt_refeicao");
			String dt_refeicao_dia = dtRefeicao.substring(8, 10);
			String dt_refeicao_mes = dtRefeicao.substring(5, 7);
			String dt_refeicao_ano = dtRefeicao.substring(0, 4);

			dtRefeicao = dt_refeicao_dia + "/" + dt_refeicao_mes + "/" + dt_refeicao_ano;

			Refeicao refeicao = new Refeicao(idRefeicao, sdf.parse(dtRefeicao), dsRefeicao, vlCaloriasConsumidas, idUsuario);
			dao.atualizar(refeicao);

			request.setAttribute("msg", "Refeicão atualizada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		listar(request, response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idRefeicao = Integer.parseInt(request.getParameter("idRefeicao"));
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		System.out.println(idUsuario);
		System.out.println(idRefeicao);
		try {
			dao.remover(idRefeicao);
			request.setAttribute("idUsuario", idUsuario);
			request.setAttribute("msg", "Refeicão removida!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		System.out.println(act);
		switch (act) {
		
		case "list":
			listar(request, response);
			break;
		case "edit":
			int idRefeicao = Integer.parseInt(request.getParameter("idRefeicao"));
			Refeicao refeicao = dao.buscar(idRefeicao);
			request.setAttribute("refeicao", refeicao);
			request.getRequestDispatcher("/forms/edit-refeicao.jsp").forward(request, response);
			break;
		case "delete":
			excluir(request, response);
			break;	
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		List<Refeicao> refeicoes = dao.listar(idUsuario);
		request.setAttribute("refeicoes", refeicoes);
		request.getRequestDispatcher("refeicao.jsp").forward(request, response);
	}

}