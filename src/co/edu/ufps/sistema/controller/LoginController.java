package co.edu.ufps.sistema.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.sistema.dao.RolDAO;
import co.edu.ufps.sistema.dao.UsuarioDAO;
import co.edu.ufps.sistema.entities.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet({ "/Login", "/Login/procesar","/Logout" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO uDAO;
	private RolDAO rDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		uDAO = new UsuarioDAO();
		rDAO = new RolDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		switch (path) {
		case "/Login":
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		case "/Login/procesar":
			loguear(request, response);
			break;
		case "/Logout":
			logout(request, response);
			break;
		}

	}

	protected void loguear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(isLog(request, response)) {
			irAInicio(request,response,(Usuario)request.getSession().getAttribute("usuario"));
		}
		
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");

		if (usuario != null || pass != null) {
			Usuario us = uDAO.findByUsuario(usuario);

			if (us != null && us.getState()==1) {
				request.getSession().setAttribute("usuario", usuario);
				irAInicio(request,response,us);
			}else {
				request.setAttribute("mensajeError", "No puede ingresar, no existe o no tiene activación");
				response.sendRedirect(request.getContextPath()+"/Login");
			}
		}

	}

	protected Boolean isLog(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		return  request.getSession().getAttribute("usuario")!=null;
	}
	
	protected void logout(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/Login");
	}
	
	private void irAInicio(HttpServletRequest request,HttpServletResponse response, Usuario us)
			throws ServletException, IOException {
		switch (us.getRolBean().getDescription()) {
		case "Usuario":
			request.getSession().setAttribute("tipoUsuario", 2);
			request.getRequestDispatcher("/Usuario").forward(request, response);
			break;
		case "Administrador":
			request.getSession().setAttribute("tipoUsuario", 1);
			request.getRequestDispatcher("/Admin").forward(request, response);
			break;
		default:
			break;
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
