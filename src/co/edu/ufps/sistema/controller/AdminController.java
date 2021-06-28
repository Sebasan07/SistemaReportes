package co.edu.ufps.sistema.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.sistema.dao.TypedbDAO;
import co.edu.ufps.sistema.dao.UsuarioDAO;
import co.edu.ufps.sistema.entities.Typedb;

/**
 * Servlet implementation class AdminController
 */
@WebServlet({"/Admin","/Admin/registroBD","/Admin/registroBD/procesar"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO uDAO;
	private TypedbDAO tdbDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        uDAO=new UsuarioDAO();
        tdbDAO=new TypedbDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		path=path.replace("/Admin", "");
		
		switch(path) {
		case "":
			break;
		case "/registroBD":
			request.getRequestDispatcher("/registroBD.jsp").forward(request, response);
			break;
		case "/registroBD/procesar":
			procesarBD(request,response);
			break;
		default:
			break;
		}
	}

	protected void procesarBD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description");
		String driver =request.getParameter("driver");
		String aditional = request.getParameter("aditional");
		
		if(description!=null && driver !=null && aditional!=null) {
			Typedb tbd= new Typedb(aditional, description, driver);
			tdbDAO.insert(tbd);
			request.setAttribute("mensajeExito", "Registrado Correctamente");
		}else {
			request.setAttribute("mensajeError", "No se pudo registrar");
		}
	
		request.getRequestDispatcher("/registroBD.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
