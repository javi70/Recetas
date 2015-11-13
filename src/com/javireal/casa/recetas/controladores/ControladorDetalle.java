package com.javireal.casa.recetas.controladores;

import java.io.IOException;

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
 * Servlet implementation class ControladorDetalle
 */
public class ControladorDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int pIdReceta;
	
    private Receta receta = null;
    
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
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//peticiones que llegan de contenido.jsp para mostrar el detalle de la receta
		
		
		if(request.getParameter("id")!=null){
			System.out.println("ID: "+request.getParameter("id"));
			pIdReceta= Integer.parseInt(request.getParameter("id"));
			receta = daoRecetas.getById(pIdReceta);
			request.setAttribute("receta", receta);
			
			//carga datos necesarios en NAV
			request.setAttribute("categorias", daoCategorias.getAllPublicos());
			request.setAttribute("tiposCocina", daoTiposCocina.getAllPublicos());
			request.setAttribute("ingredientesReceta", daoIngredientesReceta.getAllPublicosEnRecetas());
			request.setAttribute("recetas", daoRecetas.getAll());
			
			
			request.getRequestDispatcher("receta.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
