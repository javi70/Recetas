package com.javireal.casa.recetas.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.javireal.casa.recetas.Constantes;

/**
 * Servlet implementation class ControladorLogin
 */
public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger LOG = Logger.getLogger(ControladorLogin.class);       
   
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//comprobar usuario y password
		LOG.info("Comprabando usuario y password");
		//if( ("admin".equals(request.getParameter("usuario"))) && ("admin".equals(request.getParameter("password")))){
		if( (Constantes.ADMIN.equals(request.getParameter("usuario"))) && (Constantes.PASSWORD.equals(request.getParameter("password")))){
			//meter validacion de usuario en sesion
			
			//ir a backoffice/index
			//System.out.println(Daos.categorias.get(1).getNombre());
			LOG.info("LOGIN OK: Pasando a backoffice/index.jsp");
			request.getRequestDispatcher("backoffice/index.jsp").forward(request, response);
		
		}else{
			LOG.info("LOGIN MAL: Pasando a index.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
