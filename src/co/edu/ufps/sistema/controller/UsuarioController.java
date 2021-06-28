package co.edu.ufps.sistema.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.sistema.correo.Mail;
import co.edu.ufps.sistema.dao.RolDAO;
import co.edu.ufps.sistema.dao.UsuarioDAO;
import co.edu.ufps.sistema.entities.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet({"/Usuario/registro","/Usuario/registro/procesar","/Usuario/registro/activar"})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO uDAO;
	private RolDAO rDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        uDAO= new UsuarioDAO();
        rDAO=new RolDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		path=path.replace("/Usuario", "");
		
		switch(path) {
		case "/registro":
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			break;
		case "/registro/procesar":
			registrar(request, response);
			break;
		case "/registro/activar":
			break;
		default:break;
		}
	}

	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario =request.getParameter("usuario");
		if(uDAO.findByUsuario(usuario)!=null) {
			String email =request.getParameter("email");
			String rol =request.getParameter("rol");
			String pass =request.getParameter("pass");
			
			Usuario us= new Usuario(usuario, email, pass, (short)0, rDAO.find(Integer.parseInt(rol)));
			uDAO.insert(us);
		}
		
		
	}
	
	private void enviarActivacion(Usuario us) {
		Mail m = new Mail();
		String url =""
		m.enviarEmail(us.getEmail(), "Correo de activación", "");
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
