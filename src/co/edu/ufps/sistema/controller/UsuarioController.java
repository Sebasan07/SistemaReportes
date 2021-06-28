package co.edu.ufps.sistema.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.sistema.correo.Mail;
import co.edu.ufps.sistema.dao.ReporteDAO;
import co.edu.ufps.sistema.dao.RolDAO;
import co.edu.ufps.sistema.dao.UsuarioDAO;
import co.edu.ufps.sistema.entities.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet({"/Usuario/registro","/Usuario/registro/procesar","/Usuario/registro/activar", "/Usuario/reportes"})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO uDAO;
	private RolDAO rDAO;
	private ReporteDAO reDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        uDAO= new UsuarioDAO();
        rDAO=new RolDAO();
        reDAO=new ReporteDAO();
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
		case "/reportes":
			Integer id=((Usuario)request.getSession().getAttribute("usuario")).getId();
			request.setAttribute("reportes", reDAO.listReportes(id+"");
			request.getRequestDispatcher("reportes.jsp").forward(request, response);
			break;
		case "/registro/procesar":
			registrar(request, response);
			break;
		case "/registro/activar":
			activar(request, response);
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
			enviarActivacion(us);
			request.setAttribute("mensajeExito", "Se pudo registrar correctamente");
		}else {
			request.setAttribute("mensajeError", "Ya existe el usuario");
		}
		
		request.getRequestDispatcher("/registroUsuario.jsp").forward(request, response);
	}
	
	private void enviarActivacion(Usuario us) {
		Mail m = new Mail();
		UUID uuid=UUID.randomUUID();
		 String u = uuid.toString();
		String url ="http://localhost:8081/SistemaReporte/Usuario/registro/activar?l="+u.trim()+"&usuario="+us.getUsuario();
		m.enviarEmail(us.getEmail(), "Correo de activación", "Acceda al siguiente link para activar su usuario: "+url);
	}
	
	protected void activar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario =request.getParameter("usuario");
		Usuario us= uDAO.findByUsuario(usuario);
		if(us!=null) {
			us.setState((short)1);
			uDAO.update(us);
		}else {
			request.setAttribute("mensajeError", "no existe el usuario");
		}
		
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
