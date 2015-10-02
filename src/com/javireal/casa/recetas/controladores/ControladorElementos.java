package com.javireal.casa.recetas.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javireal.casa.recetas.Constantes;
import com.javireal.casa.recetas.bean.Elemento;
import com.javireal.casa.recetas.bean.Mensaje;
import com.javireal.casa.recetas.modelo.DAOElementos;

/**
 * Servlet implementation class ControladorElementos
 */
public class ControladorElementos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	private RequestDispatcher dispatcher = null;       
	//Modelos DAO
	DAOElementos daoElementos = null;
	
	//parametros
	private String pOrigen="";
	private int pAccion;
	private int pID; 
	private String pNombre;
	private String titulo="";
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoger parametros: origen, accion
		if(request.getParameter("id")!=null){
			pID = Integer.parseInt(request.getParameter("id"));			
		}
		if(request.getParameter("accion")!=null){
			pAccion = Integer.parseInt(request.getParameter("accion"));			
		}
		if(request.getParameter("origen")!=null){
			pOrigen=request.getParameter("origen");
		}
		System.out.println("id: " +pID+ " - Accion: "+pAccion);

		//Inicializar DAO del controlador
		inicializarDaoControlador();
		
		switch(pAccion){
			case Constantes.ACCION_LISTAR:
				listar(request,response);
				break;
			case Constantes.ACCION_ELIMINAR:
				eliminar(request,response);
				break;
		}
		dispatcher.forward(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Listando");
		ArrayList<Object> elementos = new ArrayList<Object>();
		elementos=daoElementos.getAll();
		request.setAttribute("elementos", elementos);
		request.setAttribute("origen", pOrigen);
		request.setAttribute("titulo", titulo);
      	dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_ELEMENTOS);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"ERROR");
		
		System.out.println("Eliminando Elemento");
		if(daoElementos.delete(pID)){
			msg.setTipo(Mensaje.MSG_SUCCESS);
			msg.setTexto("Elemento eliminado");
		}else{
			msg.setTexto("Error al eliminar el elemento");
		}
		
		request.setAttribute("msg", msg);
		listar(request,response);
	}		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"ERROR");

		if(request.getParameter("idElemento")!=null){
			pID = Integer.parseInt(request.getParameter("idElemento"));			
		}

		if(request.getParameter("nombreElemento")!=null){
			pNombre = request.getParameter("nombreElemento");			
		}
		
		//Inicializar DAO del controlador
		inicializarDaoControlador();

		Elemento elemento=null;
		//Alta de Elemento
		if (pID==-1){
			System.out.println("Añadiendo elemento "+pID);
			elemento= new Elemento();
			elemento.setNombre(pNombre);
			if(daoElementos.existe(pNombre)==-1){
				daoElementos.save(elemento);
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("Elemento añadido. Aparece al final de la tabla");
			}else{
				msg.setTexto("El elemento ya existe");
			}			

			//Editar elemento
		}else{
			System.out.println("Editando elemento "+pID);
			elemento= new Elemento();
			elemento.setId(pID);
			elemento.setNombre(pNombre);
			if(daoElementos.existe(pNombre)==-1){					
				daoElementos.update(elemento);
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("Elemento modificado.");
			}else{
				msg.setTexto("El elemento ya existe");
			}

		}
		//listamos los elemento
		System.out.println("Listando elementos");
		ArrayList<Object> elementos = new ArrayList<Object>();
		elementos=daoElementos.getAll();
		request.setAttribute("elementos", elementos);
		request.setAttribute("origen", pOrigen);
		request.setAttribute("titulo", titulo);
		request.setAttribute("msg", msg);
      	dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_ELEMENTOS);
		dispatcher.forward(request, response);
	}
	
	private void inicializarDaoControlador(){
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_CATEGORIAS))){
			daoElementos=new DAOElementos(Constantes.TABLA_CATEGORIAS);
			titulo="Categorias";
		}
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_INGREDIENTES))){
			daoElementos=new DAOElementos(Constantes.TABLA_INGREDIENTES);
			titulo="Ingredientes";
		}
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_TIPOSCOCINA))){
			daoElementos=new DAOElementos(Constantes.TABLA_TIPOSCOCINA);
			titulo="Tipos de cocina";
		}

	}
}
