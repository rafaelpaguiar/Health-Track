package br.com.fiap.htrack;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.htrack.bean.UsuarioGratuito;
import br.com.fiap.htrack.bean.UsuarioVip;
import br.com.fiap.htrack.dao.UsuarioGratuitoDAO;
import br.com.fiap.htrack.dao.UsuarioVipDAO;
import br.com.fiap.htrack.exception.DBException;
import br.com.fiap.htrack.factory.DAOFactory;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioGratuitoDAO daoGratuito = DAOFactory.getUsuarioGratuitoDAO();
	private UsuarioVipDAO daoVip = DAOFactory.getUsuarioVipDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		String type = request.getParameter("type");
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

		if (type == null) {
			type = "vip";
		}
		
		switch (act) {
		case "list":
			listar(request, response);
			break;
		case "alter":
			switch (type) {
			case "free":
				
				UsuarioGratuito usuarioGratuito = daoGratuito.buscar(idUsuario);
				request.setAttribute("usuario", usuarioGratuito);
				request.setAttribute("type",type);
				request.getRequestDispatcher("conta.jsp").forward(request, response);
				break;
			case "vip":
				UsuarioVip usuarioVip = daoVip.buscar(idUsuario);
				request.setAttribute("usuario", usuarioVip);
				request.setAttribute("type",type);
				request.getRequestDispatcher("conta.jsp").forward(request, response);
				break;
			}
			
		}

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UsuarioGratuito> lista = daoGratuito.listar();
		request.setAttribute("usuarios", lista);
		request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String act = request.getParameter("act");

		switch (act) {
		case "newUser":
			cadastrar(request, response);
			break;
		case "alter":
			editar(request, response);
			break;
		}

	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String nome = request.getParameter("nome");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dt_nasc = request.getParameter("dt_nasc");

			String dt_nasc_dia = dt_nasc.substring(8, 10);
			String dt_nasc_mes = dt_nasc.substring(5, 7);
			String dt_nasc_ano = dt_nasc.substring(0, 4);

			dt_nasc = dt_nasc_dia + "/" + dt_nasc_mes + "/" + dt_nasc_ano;

			System.out.println(dt_nasc);
			double altura = Double.parseDouble(request.getParameter("altura"));
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			Date dt_insc = new Date();
			// String confrmSenha = request.getParameter("confrmSenha");
			String plano = request.getParameter("plano");
			System.out.println(plano);

			if (plano.equals("premium")) {		
				UsuarioVip usuarioVip = new UsuarioVip(0, nome, sdf.parse(dt_nasc), dt_insc, login, email, altura, 49.9, dt_insc);
				usuarioVip.setTxSenha(senha);
				daoVip.cadastrar(usuarioVip);
			} else {
				UsuarioGratuito usuarioGratuito = new UsuarioGratuito(0, nome, sdf.parse(dt_nasc), dt_insc, login, email, altura);
				usuarioGratuito.setTxSenha(senha);
				daoGratuito.cadastrar(usuarioGratuito);
			}

			request.setAttribute("msg", "Usuário cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			String nome = request.getParameter("nome");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dt_nasc = request.getParameter("dt_nasc");

			String dt_nasc_dia = dt_nasc.substring(8, 10);
			String dt_nasc_mes = dt_nasc.substring(5, 7);
			String dt_nasc_ano = dt_nasc.substring(0, 4);

			dt_nasc = dt_nasc_dia + "/" + dt_nasc_mes + "/" + dt_nasc_ano;

			System.out.println(dt_nasc);
			double altura = Double.parseDouble(request.getParameter("altura"));
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			Date dt_insc = new Date();
			// String confrmSenha = request.getParameter("confrmSenha");
			String plano = request.getParameter("plano");
			System.out.println(plano);

			if (plano.equals("premium")) {
				UsuarioVip usuarioVip = new UsuarioVip(idUsuario, nome, sdf.parse(dt_nasc), dt_insc, login, email, altura, 49.9, dt_insc);
				usuarioVip.setTxSenha(senha);
				daoVip.atualizar(usuarioVip);
				String type = request.getParameter("type");
				request.setAttribute("type",type);
				request.setAttribute("usuario", usuarioVip);
				request.getRequestDispatcher("dashboard").forward(request, response);
			} else {
				UsuarioGratuito usuarioGratuito = new UsuarioGratuito(idUsuario, nome, sdf.parse(dt_nasc), dt_insc, login, email, altura);
				usuarioGratuito.setTxSenha(senha);
				String type = request.getParameter("type");
				request.setAttribute("type",type);
				daoGratuito.atualizar(usuarioGratuito);
				request.setAttribute("usuario", usuarioGratuito);
				request.getRequestDispatcher("dashboard").forward(request, response);
			}

			request.setAttribute("msg", "Usuário atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}

	}

}
