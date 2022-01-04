package br.com.fiap.htrack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.htrack.bean.UsuarioVip;
import br.com.fiap.htrack.dao.UsuarioVipDAO;
import br.com.fiap.htrack.factory.DAOFactory;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioVipDAO daoVip = DAOFactory.getUsuarioVipDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		UsuarioVip usuarioObj = new UsuarioVip();
		
		usuarioObj.setNmLogin(usuario);
		usuarioObj.setTxSenha(senha);
		
		System.out.println(usuarioObj.getNmLogin() + "," + usuarioObj.getTxSenha());
		
		usuarioObj = daoVip.validarUsuario(usuarioObj);
		
//		System.out.println(usuarioObj.getIdUsuario() + "," + usuarioObj.getNmLogin() + "," + usuarioObj.getTxSenha());
		
		if (usuarioObj != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("idUsuario", usuarioObj.getIdUsuario());
			
			String mystring = usuarioObj.getNmUsuario();
			String arr[] = mystring.split(" ", 2);

			String primeiroNome = arr[0];   //the
					
			
			session.setAttribute("nmUsuario", primeiroNome);
			
			System.out.println(usuarioObj.getIdUsuario() + "," + usuarioObj.getNmLogin() + "," + usuarioObj.getTxSenha());
			
			request.getRequestDispatcher("dashboard").forward(request, response);
//			try {
//				//bo.enviarEmail(email, "Login Realizado", mensagem);
//			} catch (EmailException e) {
//				e.printStackTrace();
//			}
		}else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
