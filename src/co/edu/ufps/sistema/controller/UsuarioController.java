package co.edu.ufps.sistema.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.TextFormat.ParseException;

import co.edu.ufps.sistema.correo.Mail;
import co.edu.ufps.sistema.dao.ConnectiontokenDAO;
import co.edu.ufps.sistema.dao.ReporteDAO;
import co.edu.ufps.sistema.dao.RolDAO;
import co.edu.ufps.sistema.dao.UsuarioDAO;
import co.edu.ufps.sistema.entities.Connectiontoken;
import co.edu.ufps.sistema.entities.Typedb;
import co.edu.ufps.sistema.entities.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet({"/Usuario/registro","/Usuario/registro/procesar","/Usuario/registro/activar", "/Usuario/reportes"
	,"/Usuario/reportes/tokens","/Usuario/reportes/tokens/agregar","/Usuario/reportes/seguimientos"})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO uDAO;
	private RolDAO rDAO;
	private ReporteDAO reDAO;
	private ConnectiontokenDAO conDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        uDAO= new UsuarioDAO();
        rDAO=new RolDAO();
        reDAO=new ReporteDAO();
        conDAO= new ConnectiontokenDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		path=path.replace("/Usuario", "");
		Integer reporte=0;
		switch(path) {
		case "/registro":
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			break;
		case "/reportes":
			Integer id=((Usuario)request.getSession().getAttribute("usuario")).getId();
			request.setAttribute("reportes", reDAO.listReportes(id+""));
			request.getRequestDispatcher("listaReportes.jsp").forward(request, response);
			break;
		case "/reportes/tokens":
			reporte=Integer.parseInt(request.getParameter("reporte"));
			request.setAttribute("tokens", reDAO.find(reporte).getConnectiontoken());
			request.getRequestDispatcher("listaReportes.jsp").forward(request, response);
			break;
		case "/reportes/seguimientos":
			reporte=Integer.parseInt(request.getParameter("reporte"));
			request.setAttribute("seguimientos", reDAO.find(reporte).getSeguimientos());
			request.getRequestDispatcher("listaReportes.jsp").forward(request, response);
			break;
		case "/registro/procesar":
			registrar(request, response);
			break;
		case "/registro/activar":
			activar(request, response);
			break;
		case "/reportes/tokens/agregar":
			try {
				registrarTokens(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	protected void registrarTokens(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException, ParseException {

				String aditional = request.getParameter("aditional");
				String description = request.getParameter("description");
				String driver = request.getParameter("driver");

				Typedb td=new Typedb(aditional, description, driver);

				String db = request.getParameter("db");
				String host = request.getParameter("host");
				String pass = request.getParameter("pass");
				short port = Short.parseShort(request.getParameter("port"));
				short state = Short.parseShort(request.getParameter("state"));
				String userdb = request.getParameter("userdb");
				String tokens =request.getParameter("tokens");

		Connectiontoken cn = new Connectiontoken(db, host, pass, port, state, tokens, userdb, td);

		conDAO.insert(cn);
		request.getRequestDispatcher("/Usuario/reportes").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
