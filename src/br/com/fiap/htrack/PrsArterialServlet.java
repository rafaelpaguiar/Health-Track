package br.com.fiap.htrack;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.htrack.bean.PrsArterial;
import br.com.fiap.htrack.dao.PrsArterialDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

@WebServlet("/prsarterial")
public class PrsArterialServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PrsArterialDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getPrsArterialDAO();
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
			System.out.println(act + "passou por aqui");
			editar(request, response);
			break;
		}
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			double vlPrsArterialMin = Double.parseDouble(request.getParameter("prsarterial_baixa"));
			double vlPrsArterialMax = Double.parseDouble(request.getParameter("prsarterial_alta"));

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dtPrsArterial = request.getParameter("dt_prsarterial");
			String dt_prsarterial_dia = dtPrsArterial.substring(8, 10);
			String dt_prsarterial_mes = dtPrsArterial.substring(5, 7);
			String dt_prsarterial_ano = dtPrsArterial.substring(0, 4);

			dtPrsArterial = dt_prsarterial_dia + "/" + dt_prsarterial_mes + "/" + dt_prsarterial_ano;

			PrsArterial prsArterial = new PrsArterial(0, sdf.parse(dtPrsArterial), vlPrsArterialMin, vlPrsArterialMax, idUsuario);

			dao.cadastrar(prsArterial);

			request.setAttribute("msg", "Pressão Arterial cadastrada!");
			List<PrsArterial> prsArteriais = dao.listar(idUsuario);
			request.setAttribute("prsArteriais", prsArteriais);
			request.getRequestDispatcher("prsarterial.jsp").forward(request, response);
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
			int idPrsArterial = Integer.parseInt(request.getParameter("idPrsArterial"));
			double vlPrsArterialMin = Double.parseDouble(request.getParameter("prsarterial_baixa"));
			double vlPrsArterialMax = Double.parseDouble(request.getParameter("prsarterial_alta"));

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dtPrsArterial = request.getParameter("dt_prsarterial");
			String dt_prsarterial_dia = dtPrsArterial.substring(8, 10);
			String dt_prsarterial_mes = dtPrsArterial.substring(5, 7);
			String dt_prsarterial_ano = dtPrsArterial.substring(0, 4);

			dtPrsArterial = dt_prsarterial_dia + "/" + dt_prsarterial_mes + "/" + dt_prsarterial_ano;

			PrsArterial prsArterial = new PrsArterial(idPrsArterial, sdf.parse(dtPrsArterial), vlPrsArterialMin, vlPrsArterialMax, idUsuario);
			dao.atualizar(prsArterial);

			request.setAttribute("msg", "Pressão Arterial atualizada!");
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
		int idPrsArterial = Integer.parseInt(request.getParameter("idPrsArterial"));
		int idUsuario = Integer.parseInt(request.getParameter("idPrsArterial"));
		System.out.println(idUsuario);
		System.out.println(idPrsArterial);
		try {
			dao.remover(idPrsArterial);
			request.setAttribute("idUsuario", idUsuario);
			request.setAttribute("msg", "Pressão Arteiral removida!");
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
		switch (act) {
		
		case "list":
			listar(request, response);
			break;
		case "edit":
			int idPrsArterial = Integer.parseInt(request.getParameter("idPrsArterial"));
			PrsArterial prsArterial = dao.buscar(idPrsArterial);
			request.setAttribute("prsArterial", prsArterial);
			request.getRequestDispatcher("/forms/edit-prsarterial.jsp").forward(request, response);
			break;
		case "delete":
			excluir(request, response);
			break;	
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		List<PrsArterial> prsArteriais = dao.listar(idUsuario);
		request.setAttribute("prsArteriais", prsArteriais);
		request.getRequestDispatcher("prsarterial.jsp").forward(request, response);
	}

}