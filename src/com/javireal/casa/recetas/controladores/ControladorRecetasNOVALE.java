package com.javireal.casa.recetas.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javireal.casa.recetas.Constantes;
import com.javireal.casa.recetas.bean.Ingredientes;
import com.javireal.casa.recetas.bean.Receta;
import com.javireal.casa.recetas.modelo.DAOCategorias;
import com.javireal.casa.recetas.modelo.DAOIngredientes;
import com.javireal.casa.recetas.modelo.DAORecetas;
import com.javireal.casa.recetas.modelo.DAOTiposCocina;


/**
 * Servlet implementation class ControladorRecetas
 */
public class ControladorRecetasNOVALE extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher dispatcher = null;
	private DAORecetas daoRecetas = null;
	private DAOIngredientes daoIngredientes = null;
	private DAOCategorias daoCategorias = null;
	private DAOTiposCocina daoTiposCocina = null; 
    private Receta receta = null;  
    public Ingredientes ingredientes = null;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
//		receta = new Receta(-1,"");
    	daoRecetas = new DAORecetas();
    	daoIngredientes = new DAOIngredientes();
    	daoCategorias = new DAOCategorias();
    	daoTiposCocina = new DAOTiposCocina();
//    	ingredientes = new Ingredientes();    	

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("accion")!=null){
			int pAccion = Integer.parseInt(request.getParameter("accion"));
			switch(pAccion){
				case Constantes.ACCION_LISTAR:
					System.out.println("Listando recetas");
					listar(request,response);
					break;
				case Constantes.ACCION_ELIMINAR:
					System.out.println("Eliminando receta "+request.getParameter("idReceta"));
					eliminar(request,response);
			}
		}
		dispatcher.forward(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {	
		if(request.getParameter("idReceta")!=null)
			daoRecetas.delete(Integer.parseInt(request.getParameter("idReceta")));
		//request.setAttribute("listaRecetas", daoRecetas.getAll());
		request.setAttribute("msg", "Receta eliminada");
		listar(request,response);
		//dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_RECETAS);
	}

	/**
	 * Obtiene todas las recetas del Modelo y carga dispacth con backoffice/recetas.jsp
	 * @param request
	 * @param response
	 */
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("listaRecetas", daoRecetas.getAll());
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_RECETAS);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pID = -99;
		String pNombre = "";

		if(request.getParameter("idReceta")!=null){
			pID = Integer.parseInt(request.getParameter("idReceta"));
			pNombre = request.getParameter("nombreReceta");
		}

		request.setAttribute("listaRecetas", daoRecetas.getAll());
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_RECETAS);
		dispatcher.forward(request, response);
	}
}
