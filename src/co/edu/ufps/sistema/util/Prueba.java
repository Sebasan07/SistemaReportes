package co.edu.ufps.sistema.util;

import java.util.List;

import co.edu.ufps.sistema.dao.ReporteDAO;
import co.edu.ufps.sistema.entities.Reporte;

public class Prueba {
	public static void main(String[] args) {
		List<Reporte> r=new ReporteDAO().listReportes(1+"");
		
		/*
		 * System.out.println(r.get(0)[0].toString()); Reporte ssr= new
		 * ReporteDAO().find(Integer.parseInt(r.get(0)[0].toString()));
		 */

	}
}
