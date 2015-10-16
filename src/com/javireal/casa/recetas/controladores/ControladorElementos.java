package com.javireal.casa.recetas.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.javireal.casa.recetas.Constantes;
import com.javireal.casa.recetas.Daos;
import com.javireal.casa.recetas.bean.Elemento;
import com.javireal.casa.recetas.bean.Mensaje;
import com.javireal.casa.recetas.modelo.DAOElementos;

/**
 * Servlet implementation class ControladorElementos
 */
public class ControladorElementos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger LOG = Logger.getLogger(ControladorElementos.class);
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
			pID = Integer.parseInt(request.getParameter("id"));			
		}
		if(request.getParameter("accion")!=null){
			pAccion = Integer.parseInt(request.getParameter("accion"));			
		}
		if(request.getParameter("origen")!=null){
			pOrigen=request.getParameter("origen");
		}
		LOG.info("id: " +this.pID+ " - Accion: "+this.pAccion);

		//Inicializar DAO del controlador
		//inicializarDaoControlador();
		
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
		
		ArrayList<Elemento> elementos = new ArrayList<Elemento>();

		
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_CATEGORIAS))){
			elementos=Daos.categorias;
			titulo="Categorias";
		}
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_INGREDIENTES))){
			elementos=Daos.ingredientes;
			titulo="Ingredientes";
		}
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_TIPOSCOCINA))){
			elementos=Daos.tiposCocina;
			titulo="Tipos de cocina";
		}
		request.setAttribute("elementos", elementos);
		request.setAttribute("origen",pOrigen);
		request.setAttribute("titulo", titulo);
		LOG.info("Listando "+titulo);
      	dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_ELEMENTOS);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"ERROR");
		
		LOG.info("Eliminando Elemento "+pID);
		//Se borra del modelo
		if(daoElementos.delete(pID)){
			msg.setTipo(Mensaje.MSG_SUCCESS);
			msg.setTexto("Elemento eliminado");
		}else{
			msg.setTexto("Error al eliminar el elemento");
		}
		actualizarDaos();
		request.setAttribute("msg", msg);
		listar(request,response);
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
			elemento= new Elemento();
			elemento.setNombre(this.pNombre);
			if(this.daoElementos.existe(this.pNombre)==-1){
				this.daoElementos.save(elemento);
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("Elemento añadido. Aparece al final de la tabla");
				LOG.info("Añadiendo elemento "+this.pID);
			}else{
				msg.setTexto("El elemento ya existe");
				LOG.error("ERROR añadiendo elemento "+this.pID);
			}			

			//Editar elemento
		}else{
			elemento= new Elemento();
			elemento.setId(this.pID);
			elemento.setNombre(this.pNombre);
			if(this.daoElementos.existe(this.pNombre)==-1){					
				this.daoElementos.update(elemento);
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("Elemento modificado.");
				LOG.info("Editando elemento "+this.pID);
			}else{
				msg.setTexto("El elemento ya existe");
				LOG.error("ERROR editando elemento "+this.pID);
			}

		}
		actualizarDaos();
		//listamos los elemento
		LOG.info("Listando elementos");
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
		LOG.info("Inicializando Dao "+titulo);
	}
	
	/**
	 * Recarga los ArrayList de Categorias, Ingredientes o TiposCocina
	 */
	private void actualizarDaos(){
		
		//Se recarga el ArrayList
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_CATEGORIAS))){
			Daos.categorias = daoElementos.getAll();
			LOG.info("Recargando Dao Categorias");
		}
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_INGREDIENTES))){
			Daos.ingredientes = daoElementos.getAll();
			LOG.info("Recargando Dao Ingredientes");
		}
		if(pOrigen.equals(Integer.toString(Constantes.ORIGEN_TIPOSCOCINA))){
			Daos.tiposCocina = daoElementos.getAll();
			LOG.info("Recargando Dao TiposCocina");
		}

	}
}
