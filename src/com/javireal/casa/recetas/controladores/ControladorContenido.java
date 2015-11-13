package com.javireal.casa.recetas.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javireal.casa.recetas.Daos;
import com.javireal.casa.recetas.bean.Receta;
import com.javireal.casa.recetas.modelo.DAOElementos;
import com.javireal.casa.recetas.modelo.DAOIngredientesReceta;
import com.javireal.casa.recetas.modelo.DAORecetas;

/**
 * Servlet implementation class ControladorContenido
 */
public class ControladorContenido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private int pIdCategoria;
    private int pIdTipoCocina;
	
    
  //Modelos DAO
  	DAOElementos daoCategorias = null;
  	DAOElementos daoTiposCocina = null;
  	DAOElementos daoIngredientes = null;
  	DAORecetas daoRecetas = null;
  	DAOIngredientesReceta daoIngredientesReceta = null;

    
	@Override
	public void init() throws ServletException {
		
		super.init();
		daoTiposCocina = new DAOElementos("tiposcocina");
		Daos.tiposCocina = daoTiposCocina.getAll();
		
		daoCategorias = new DAOElementos("categorias");
		Daos.categorias = daoCategorias.getAll();
		
		daoIngredientes = new DAOElementos("ingredientes");
		Daos.ingredientes = daoIngredientes.getAll();	
		
		daoRecetas = new DAORecetas();
		daoIngredientesReceta = new DAOIngredientesReceta();
		
		daoRecetas = new DAORecetas();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Receta> listaRecetas = null;
		
		if(request.getParameter("idCategoria")!=null){
			pIdCategoria = Integer.parseInt(request.getParameter("idCategoria"));
			listaRecetas = daoRecetas.getAllByCategoria(pIdCategoria);
			
		}
		if(request.getParameter("idTipoCocina")!=null){
			pIdTipoCocina = Integer.parseInt(request.getParameter("idTipoCocina"));
			listaRecetas = daoRecetas.getAllByTipoCocina(pIdTipoCocina);
		}		
		
		request.setAttribute("listaRecetas",listaRecetas); 
		
		//carga datos necesarios en NAV
		request.setAttribute("categorias", daoCategorias.getAllPublicos());
		request.setAttribute("tiposCocina", daoTiposCocina.getAllPublicos());
		request.setAttribute("ingredientesReceta", daoIngredientesReceta.getAllPublicosEnRecetas());
		request.setAttribute("recetas", daoRecetas.getAll());
		request.getRequestDispatcher("contenido.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
