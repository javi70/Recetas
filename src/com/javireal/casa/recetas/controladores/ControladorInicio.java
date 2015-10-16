package com.javireal.casa.recetas.controladores;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.javireal.casa.recetas.Daos;
import com.javireal.casa.recetas.modelo.DAOElementos;

/**
 * Servlet implementation class ControladorInicio
 */
public class ControladorInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger LOG = Logger.getLogger(ControladorInicio.class);
	//Modelos DAO
	DAOElementos daoCategorias = null;
	DAOElementos daoTiposCocina = null;
	DAOElementos daoIngredientes = null;
	
		
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		LOG.info("Inicializando");
		
		this.daoTiposCocina = new DAOElementos("tiposcocina");
		Daos.tiposCocina = daoTiposCocina.getAll();
		
		this.daoCategorias = new DAOElementos("categorias");
		Daos.categorias = daoCategorias.getAll();
		
		this.daoIngredientes = new DAOElementos("ingredientes");
		Daos.ingredientes = daoIngredientes.getAll();		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ir a index
		LOG.info("Pasando a index.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);}
	
	}
