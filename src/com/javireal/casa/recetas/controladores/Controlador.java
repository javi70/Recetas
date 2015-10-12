package com.javireal.casa.recetas.controladores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.javireal.casa.recetas.Constantes;
import com.javireal.casa.recetas.bean.Categoria;
import com.javireal.casa.recetas.bean.IngredientesReceta;
import com.javireal.casa.recetas.bean.Mensaje;
import com.javireal.casa.recetas.bean.Receta;
import com.javireal.casa.recetas.bean.TipoCocina;
import com.javireal.casa.recetas.modelo.DAOElementos;
import com.javireal.casa.recetas.modelo.DAOIngredientesReceta;
import com.javireal.casa.recetas.modelo.DAORecetas;

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private RequestDispatcher dispatcher = null;       
	//Modelos DAO
	DAORecetas daoRecetas = null;
	DAOElementos daoCategorias = null;
	DAOElementos daoTiposCocina = null;
	DAOElementos daoIngredientes = null;
	DAOIngredientesReceta daoIngredientesReceta = null;
	
	//parametros
	private int pAccion;
	private int pID; 
	private String pNombre;
	private String pTiempo;
	private String pPreparacion;
	private Categoria pCategoria;
	private TipoCocina pTiposCocina;
	private ArrayList<String> pListaIngredientes = null;
	private IngredientesReceta pIngrediente = null;
	private ArrayList<IngredientesReceta> pIngredientes = null;
	private String pFotografia;
	
	//variables subida imagenes
 	private int maxFileSize = 10000 * 1024;
 	private int maxMemSize = 40 * 1024;
 	private File file ;
    private String fileName;
    
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.daoTiposCocina = new DAOElementos("tiposcocina");
		this.daoCategorias = new DAOElementos("categorias");
		this.daoIngredientes = new DAOElementos("ingredientes");
		this.daoRecetas = new DAORecetas();
		this.daoIngredientesReceta = new DAOIngredientesReceta();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoger parametros: accion, id, origen
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("id")!=null){
			this.pID = Integer.parseInt(request.getParameter("id"));			
		}
		if(request.getParameter("accion")!=null){
			this.pAccion = Integer.parseInt(request.getParameter("accion"));			
		}
		System.out.println("id: " +this.pID+ " - Accion: "+this.pAccion);
		
	
		
		switch(this.pAccion){
			case Constantes.ACCION_LISTAR:
				this.listarRecetas(request,response);
				break;
			case Constantes.ACCION_ELIMINAR:
				this.eliminarRecetas(request,response);
				break;
			case Constantes.ACCION_NUEVO:
				this.formularioReceta(request,response);
				break;
			case Constantes.ACCION_MODIFICAR:
				this.formularioReceta(request,response);
				break;					
		}
		
		this.dispatcher.forward(request, response);
	}

	private void listarRecetas(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Listando Recetas");
		ArrayList<Receta> recetas = new ArrayList<Receta>();
		recetas=this.daoRecetas.getAll();
		request.setAttribute("recetas", recetas);
		request.setAttribute("origen", "Recetas");
      	this.dispatcher = request.getRequestDispatcher("backoffice/recetas.jsp");
	}

	private void eliminarRecetas(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Eliminando Receta");
		//Primero eliminar sus IngredientesReceta si es que los tiene
		daoIngredientesReceta.delete(this.pID);
		this.daoRecetas.delete(this.pID);
		Mensaje msg=new Mensaje(Mensaje.MSG_SUCCESS,"Receta eliminada");
		request.setAttribute("msg", msg);
		this.listarRecetas(request,response);
	}		
	
	private void formularioReceta(HttpServletRequest request, HttpServletResponse response) {
		Receta receta=null;		
		ArrayList<String> nombresIngredientes=new ArrayList<String>();
		System.out.println("Abriendo formulario de Receta");
		
		if(this.pAccion==Constantes.ACCION_NUEVO){
			receta=new Receta();
			
		}else{
			receta = this.daoRecetas.getById(this.pID);
			receta.setIngredientes(daoIngredientesReceta.getByRecetaId(pID));
			//Preparar ingredientes y cantidades			
			  //nombres de los ingredientes de la receta
			 
			  for(int i=0;i<receta.getIngredientes().size();i++){
				  nombresIngredientes.add(daoIngredientes.getById(receta.getIngredientes().get(i).getIdIngrediente()).getNombre());
			  }
			//this.pIngredientes= this.daoIngredientesReceta.getByRecetaId(this.pID);
			//pIngredientes = receta.getIngredientes();
			//System.out.println("Ingredientes de la receta: "+pIngredientes.toString());

		}
		
		request.setAttribute("categorias", this.daoCategorias.getAll());
		request.setAttribute("ingredientes", this.daoIngredientes.getAll());
		request.setAttribute("tiposCocina", this.daoTiposCocina.getAll());
		request.setAttribute("accionFormulario", this.pAccion);		
		request.setAttribute("receta", receta);
		request.setAttribute("nombresIngredientes", nombresIngredientes);
		this.dispatcher=request.getRequestDispatcher("backoffice/formRecetas.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg=null;
		
		this.getParametersFormRecetas(request);
		Receta receta = new Receta();
		receta.setNombre(this.pNombre);
		receta.setPreparacion(this.pPreparacion);
		receta.setTiempo(this.pTiempo);
		receta.setCategoria(this.pCategoria);
		receta.setTipoCocina(this.pTiposCocina);
		receta.setFotografia(this.pFotografia);
		receta.setIngredientes(pIngredientes);

		//Alta de Receta
		if (this.pID==-1){
			System.out.println("Añadiendo receta "+this.pID);

			if(this.daoRecetas.existe(this.pNombre)==-1){
				this.pID=this.daoRecetas.save(receta);
				if(this.pID!=-1){
					request.setAttribute("msg", "Receta añadida. Aparece al final de la tabla");
					//Añado los datos a IngredientesReceta
					
					
					for(int i=0;i<this.pIngredientes.size();i++){
						//Guardo la ID de la receta que acabo de obtener					
						this.pIngredientes.get(i).setIdReceta(pID);
						if(this.daoIngredientesReceta.save(this.pIngredientes.get(i))==-1){
							msg=new Mensaje(Mensaje.MSG_WARNING,"Error al añadir los ingredientes");
						}
					}
				}else{
					msg=new Mensaje(Mensaje.MSG_WARNING,"Error al añadir la receta");
				}
			}else{
				msg=new Mensaje(Mensaje.MSG_WARNING,"La receta ya existe");
			}			

		//Editar Receta
		}else{
			System.out.println("Editando receta "+this.pID);

			//elimina los IngredientesReceta para luego meter los nuevos
			this.daoIngredientesReceta.delete(this.pID);
						
			receta.setId(this.pID);

			if(this.daoRecetas.update(receta)){
				//Añado los datos a IngredientesReceta
				for(int i=0;i<this.pIngredientes.size();i++){
					if(this.daoIngredientesReceta.save(this.pIngredientes.get(i))==-1){
						msg=new Mensaje(Mensaje.MSG_WARNING,"Error al actualizar los ingredientes");
					}else{
						msg=new Mensaje(Mensaje.MSG_SUCCESS,"Receta actualizada correctamente");
					}
				}				
			}else{
				msg=new Mensaje(Mensaje.MSG_DANGER,"Error al actualizar la receta");
			}
		}

		//Listamos las recetas
		ArrayList<Receta> recetas = new ArrayList<Receta>();
		recetas=this.daoRecetas.getAll();
		request.setAttribute("recetas", recetas);
		request.setAttribute("origen", "Recetas");
		request.setAttribute("msg", msg);
      	this.dispatcher = request.getRequestDispatcher("backoffice/recetas.jsp");		
		this.dispatcher.forward(request, response);		
	}
	
	private void getParametersFormRecetas(HttpServletRequest request){
		
		try{
			  request.setCharacterEncoding("UTF-8");
			  
			
			  DiskFileItemFactory factory = new DiskFileItemFactory();
		      factory.setSizeThreshold(this.maxMemSize);
		      factory.setRepository(new File(Constantes.IMG_UPLOAD_TEMP_FOLDER));      
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      upload.setSizeMax( this.maxFileSize );
		      
		      //Parametros de la request del formulario, NO la imagen
		      HashMap<String, String> dataParameters = new HashMap<String,String>();
		      
		      List<FileItem> items = upload.parseRequest(request);
    		  this.pListaIngredientes = new ArrayList<String>();
		      for(FileItem item:items){
		    	  
		    	  //parametro formulario
		    	  if(item.isFormField()){
		    		  dataParameters.put(item.getFieldName(), item.getString("UTF-8"));
		    		  //recoger array de ingredientes y cantidades
		    		  //listaIngredientes contendra: "CANTIDAD de ID_INGREDIENTE", ...
		    		  if("listaIngredientes".equals(item.getFieldName())){
		    			  this.pListaIngredientes.add(item.getString("UTF-8"));
		    		  }

		    	  //imagen
		    	  }else{
		    		  if(!"".equals(item.getName())){
		    			  //se hace si hay archivo para subir  
			    		  this.fileName     = dataParameters.get("idReceta")+"_"+item.getName();
				            
				          if(!"".equals(item.getName())){
				            	this.file = new File( Constantes.IMG_UPLOAD_FOLDER + "\\" +this.fileName );
				            	item.write( this.file ) ; //guarda la imagen
				           }
		    	  	}
		    	  }
		      } //End: for List<FileItem>
		      
		      System.out.println("DATA PARAMETERS: "+dataParameters.toString());
		      if(dataParameters.get("idReceta")!=null){
		    	  this.pID = Integer.parseInt(dataParameters.get("idReceta"));  
		      }
		      if(dataParameters.get("nombreReceta")!=null){
		    	  this.pNombre = dataParameters.get("nombreReceta");
		      }
		      if(dataParameters.get("tiempo")!=null){
		    	  this.pTiempo=dataParameters.get("tiempo");
		      }
		      if(dataParameters.get("preparacion")!=null){
		    	  this.pPreparacion = dataParameters.get("preparacion");
		      }
		      if(dataParameters.get("categoria")!=null){
	   			  this.pCategoria = new Categoria();
	   			  this.pCategoria=this.pCategoria.casteo(this.daoCategorias.getById(Integer.parseInt(dataParameters.get("categoria"))));
		      }
		      if(dataParameters.get("tipoCocina")!=null){
	   			  this.pTiposCocina = new TipoCocina();
				  this.pTiposCocina = this.pTiposCocina.casteo(this.daoTiposCocina.getById(Integer.parseInt(dataParameters.get("tipoCocina"))));
		      }
			  if(this.fileName==null){
				  this.pFotografia=Constantes.IMG_DEFAULT_RECETA;
			  }else{
				  this.pFotografia=this.fileName;
			  }
			  this.pIngredientes = new ArrayList<IngredientesReceta>();
			  for(int i=0;i<this.pListaIngredientes.size();i++){
				  //frase sera "CANTIDAD de ID_INGREDIENTE"
				  String frase = this.pListaIngredientes.get(i);
				  int separador=frase.indexOf(" de ");
				  String strCantidad = frase.substring(0, separador);
				  String strIngrediente = frase.substring(separador+4, frase.length());
				  pIngrediente= new IngredientesReceta(pID,Integer.parseInt(strIngrediente),strCantidad);
				  this.pIngredientes.add(this.pIngrediente);
				  				  
				  System.out.println("IngredienteReceta: "+this.pIngrediente.toString());
			  }
			  
			  
			  System.out.println("pFotografia = "+this.pFotografia);
			  System.out.println("fileName = "+this.fileName);
			  System.out.println("Lista de ingredientes = "+this.pListaIngredientes);
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
