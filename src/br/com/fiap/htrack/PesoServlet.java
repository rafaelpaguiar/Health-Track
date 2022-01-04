package br.com.fiap.htrack;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.htrack.bean.Peso;
import br.com.fiap.htrack.dao.PesoDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

@WebServlet("/peso")
public class PesoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PesoDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getPesoDAO();
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

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dtPeso = request.getParameter("dt_peso");
			String dt_peso_dia = dtPeso.substring(8, 10);
			String dt_peso_mes = dtPeso.substring(5, 7);
			String dt_peso_ano = dtPeso.substring(0, 4);

			dtPeso = dt_peso_dia + "/" + dt_peso_mes + "/" + dt_peso_ano;

			double vlPeso = Double.parseDouble(request.getParameter("peso"));

			Peso peso = new Peso(0, sdf.parse(dtPeso), vlPeso, idUsuario);

			dao.cadastrar(peso);

			request.setAttribute("msg", "Peso cadastrado!");
			List<Peso> pesos = dao.listar(idUsuario);
			request.setAttribute("pesos", pesos);
			request.getRequestDispatcher("peso.jsp").forward(request, response);
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
			int idPeso = Integer.parseInt(request.getParameter("idPeso"));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dtPeso = request.getParameter("dt_peso");
			String dt_peso_dia = dtPeso.substring(8, 10);
			String dt_peso_mes = dtPeso.substring(5, 7);
			String dt_peso_ano = dtPeso.substring(0, 4);

			dtPeso = dt_peso_dia + "/" + dt_peso_mes + "/" + dt_peso_ano;

			double vlPeso = Double.parseDouble(request.getParameter("peso"));

			

			Peso peso = new Peso(idPeso, sdf.parse(dtPeso), vlPeso, idUsuario);
			dao.atualizar(peso);

			request.setAttribute("msg", "Peso atualizado!");
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
		int idPeso = Integer.parseInt(request.getParameter("idPeso"));
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		System.out.println(idUsuario);
		System.out.println(idPeso);
		try {
			dao.remover(idPeso);
			request.setAttribute("idUsuario", idUsuario);
			request.setAttribute("msg", "Peso removido!");
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
			int idPeso = Integer.parseInt(request.getParameter("idPeso"));
			Peso peso = dao.buscar(idPeso);
			request.setAttribute("peso", peso);
			request.getRequestDispatcher("/forms/edit-peso.jsp").forward(request, response);
			break;
		case "delete":
			excluir(request, response);
			break;	
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		List<Peso> pesos = dao.listar(idUsuario);
		request.setAttribute("pesos", pesos);
		request.getRequestDispatcher("peso.jsp").forward(request, response);
	}

}