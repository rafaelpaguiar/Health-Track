package br.com.fiap.htrack;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.htrack.bean.Exercicio;
import br.com.fiap.htrack.dao.ExercicioDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

@WebServlet("/exercicio")
public class ExercicioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ExercicioDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getExercicioDAO();
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
			String nmExercicio = request.getParameter("exercicio");
			int vlDuracao = Integer.parseInt(request.getParameter("duracao"));
			int gastoCalorico = Integer.parseInt(request.getParameter("kcalGasto"));

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dtExercicio = request.getParameter("dt_exercicio");
			String dt_exercicio_dia = dtExercicio.substring(8, 10);
			String dt_exercicio_mes = dtExercicio.substring(5, 7);
			String dt_exercicio_ano = dtExercicio.substring(0, 4);

			dtExercicio = dt_exercicio_dia + "/" + dt_exercicio_mes + "/" + dt_exercicio_ano;

			Exercicio exercicio = new Exercicio(0, nmExercicio, sdf.parse(dtExercicio), vlDuracao, gastoCalorico, idUsuario);

			dao.cadastrar(exercicio);

			request.setAttribute("msg", "Exercício cadastrado!");
			List<Exercicio> exercicios = dao.listar(idUsuario);
			request.setAttribute("exercicios", exercicios);
			request.getRequestDispatcher("exercicio.jsp").forward(request, response);
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
			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			int idExercicio = Integer.parseInt(request.getParameter("idExercicio"));
			String nmExercicio = request.getParameter("exercicio");
			int vlDuracao = Integer.parseInt(request.getParameter("duracao"));
			System.out.println(vlDuracao);
			int gastoCalorico = Integer.parseInt(request.getParameter("kcalGasto"));

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dtExercicio = request.getParameter("dt_exercicio");
			String dt_exercicio_dia = dtExercicio.substring(8, 10);
			String dt_exercicio_mes = dtExercicio.substring(5, 7);
			String dt_exercicio_ano = dtExercicio.substring(0, 4);

			dtExercicio = dt_exercicio_dia + "/" + dt_exercicio_mes + "/" + dt_exercicio_ano;

			Exercicio exercicio = new Exercicio(idExercicio, nmExercicio, sdf.parse(dtExercicio), vlDuracao, gastoCalorico, idUsuario);
			dao.atualizar(exercicio);

			request.setAttribute("msg", "Exercício atualizado!");
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
		int idExercicio = Integer.parseInt(request.getParameter("idExercicio"));
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		System.out.println(idUsuario);
		System.out.println(idExercicio);
		try {
			dao.remover(idExercicio);
			request.setAttribute("idUsuario", idUsuario);
			request.setAttribute("msg", "Exercício removido!");
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
			int idExercicio = Integer.parseInt(request.getParameter("idExercicio"));
			Exercicio exercicio = dao.buscar(idExercicio);
			request.setAttribute("exercicio", exercicio);
			request.getRequestDispatcher("/forms/edit-exercicio.jsp").forward(request, response);
			break;
		case "delete":
			excluir(request, response);
			break;	
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		List<Exercicio> exercicios = dao.listar(idUsuario);
		request.setAttribute("exercicios", exercicios);
		request.getRequestDispatcher("exercicio.jsp").forward(request, response);
	}

}