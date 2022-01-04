package br.com.fiap.htrack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.htrack.bean.Exercicio;
import br.com.fiap.htrack.bean.Peso;
import br.com.fiap.htrack.bean.PrsArterial;
import br.com.fiap.htrack.bean.Refeicao;
import br.com.fiap.htrack.dao.ExercicioDAO;
import br.com.fiap.htrack.dao.PesoDAO;
import br.com.fiap.htrack.dao.PrsArterialDAO;
import br.com.fiap.htrack.dao.RefeicaoDAO;
import br.com.fiap.htrack.factory.DAOFactory;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ExercicioDAO daoExercicio = DAOFactory.getExercicioDAO();
	private PesoDAO daoPeso = DAOFactory.getPesoDAO();
	private PrsArterialDAO daoPrsArterial = DAOFactory.getPrsArterialDAO();
	private RefeicaoDAO daoRefeicao = DAOFactory.getRefeicaoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int idUsuario = (int) session.getAttribute("idUsuario");
		System.out.println(idUsuario);
		Exercicio exercicio = daoExercicio.buscarUltimo(idUsuario);
		Peso peso = daoPeso.buscarUltimo(idUsuario);
		PrsArterial prsArterial = daoPrsArterial.buscarUltimo(idUsuario);
		Refeicao refeicao = daoRefeicao.buscarUltimo(idUsuario);
		request.setAttribute("ultimoExercicio", exercicio);
		request.setAttribute("ultimoPeso", peso);
		request.setAttribute("ultimoPrsArterial", prsArterial);
		request.setAttribute("ultimoRefeicao", refeicao);
		// request.setAttribute("type",type);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int idUsuario = (int) session.getAttribute("idUsuario");
System.out.println(idUsuario);
		Exercicio exercicio = daoExercicio.buscarUltimo(idUsuario);
		Peso peso = daoPeso.buscarUltimo(idUsuario);
		PrsArterial prsArterial = daoPrsArterial.buscarUltimo(idUsuario);
		Refeicao refeicao = daoRefeicao.buscarUltimo(idUsuario);
		request.setAttribute("ultimoExercicio", exercicio);
		request.setAttribute("ultimoPeso", peso);
		request.setAttribute("ultimoPrsArterial", prsArterial);
		request.setAttribute("ultimoRefeicao", refeicao);
		// request.setAttribute("type",type);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
}
