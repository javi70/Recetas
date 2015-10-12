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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoger parametros: origen, accion
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("id")!=null){
			this.pID = Integer.parseInt(request.getParameter("id"));			
		}
		if(request.getParameter("accion")!=null){
			this.pAccion = Integer.parseInt(request.getParameter("accion"));			
		}
		if(request.getParameter("origen")!=null){
			this.pOrigen=request.getParameter("origen");
		}
		System.out.println("id: " +this.pID+ " - Accion: "+this.pAccion);

		//Inicializar DAO del controlador
		this.inicializarDaoControlador();
		
		switch(this.pAccion){
			case Constantes.ACCION_LISTAR:
				this.listar(request,response);
				break;
			case Constantes.ACCION_ELIMINAR:
				this.eliminar(request,response);
				break;
		}
		this.dispatcher.forward(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Listando");
		ArrayList<Elemento> elementos = new ArrayList<Elemento>();
		elementos=this.daoElementos.getAll();
		request.setAttribute("elementos", elementos);
		request.setAttribute("origen", this.pOrigen);
		request.setAttribute("titulo", this.titulo);
      	this.dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_ELEMENTOS);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"ERROR");
		
		System.out.println("Eliminando Elemento");
		if(this.daoElementos.delete(this.pID)){
			msg.setTipo(Mensaje.MSG_SUCCESS);
			msg.setTexto("Elemento eliminado");
		}else{
			msg.setTexto("Error al eliminar el elemento");
		}
		
		request.setAttribute("msg", msg);
		this.listar(request,response);
	}		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"ERROR");
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("idElemento")!=null){
			this.pID = Integer.parseInt(request.getParameter("idElemento"));			
		}

		if(request.getParameter("nombreElemento")!=null){
			this.pNombre = request.getParameter("nombreElemento");			
		}
		
		//Inicializar DAO del controlador
		this.inicializarDaoControlador();

		Elemento elemento=null;
		//Alta de Elemento
		if (this.pID==-1){
			System.out.println("Añadiendo elemento "+this.pID);
			elemento= new Elemento();
			elemento.setNombre(this.pNombre);
			if(this.daoElementos.existe(this.pNombre)==-1){
				this.daoElementos.save(elemento);
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("Elemento añadido. Aparece al final de la tabla");
			}else{
				msg.setTexto("El elemento ya existe");
			}			

			//Editar elemento
		}else{
			System.out.println("Editando elemento "+this.pID);
			elemento= new Elemento();
			elemento.setId(this.pID);
			elemento.setNombre(this.pNombre);
			if(this.daoElementos.existe(this.pNombre)==-1){					
				this.daoElementos.update(elemento);
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("Elemento modificado.");
			}else{
				msg.setTexto("El elemento ya existe");
			}

		}
		//listamos los elemento
		System.out.println("Listando elementos");
		ArrayList<Elemento> elementos = new ArrayList<Elemento>();
		elementos=this.daoElementos.getAll();
		request.setAttribute("elementos", elementos);
		request.setAttribute("origen", this.pOrigen);
		request.setAttribute("titulo", this.titulo);
		request.setAttribute("msg", msg);
      	this.dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_ELEMENTOS);
		this.dispatcher.forward(request, response);
	}
	
	private void inicializarDaoControlador(){
		if(this.pOrigen.equals(Integer.toString(Constantes.ORIGEN_CATEGORIAS))){
			this.daoElementos=new DAOElementos(Constantes.TABLA_CATEGORIAS);
			this.titulo="Categorias";
		}
		if(this.pOrigen.equals(Integer.toString(Constantes.ORIGEN_INGREDIENTES))){
			this.daoElementos=new DAOElementos(Constantes.TABLA_INGREDIENTES);
			this.titulo="Ingredientes";
		}
		if(this.pOrigen.equals(Integer.toString(Constantes.ORIGEN_TIPOSCOCINA))){
			this.daoElementos=new DAOElementos(Constantes.TABLA_TIPOSCOCINA);
			this.titulo="Tipos de cocina";
		}

	}
}
