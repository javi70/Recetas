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
import com.javireal.casa.recetas.Daos;
import com.javireal.casa.recetas.bean.Categoria;
import com.javireal.casa.recetas.bean.IngredientesReceta;
import com.javireal.casa.recetas.bean.Mensaje;
import com.javireal.casa.recetas.bean.Receta;
import com.javireal.casa.recetas.bean.TipoCocina;
import com.javireal.casa.recetas.modelo.DAOIngredientesReceta;
import com.javireal.casa.recetas.modelo.DAORecetas;

/**
 * Servlet implementation class Controlador
 */
public class ControladorRecetas extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private RequestDispatcher dispatcher = null;       
	//Modelos DAO
	DAORecetas daoRecetas = null;

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
	private int pPublico;
	
	//variables subida imagenes
 	private int maxFileSize = 10000 * 1024;
 	private int maxMemSize = 40 * 1024;
 	private File file ;
    private String fileName;
    
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoRecetas = new DAORecetas();
		daoIngredientesReceta = new DAOIngredientesReceta();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoger parametros: accion, id
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("id")!=null){
			pID = Integer.parseInt(request.getParameter("id"));			
		}
		if(request.getParameter("accion")!=null){
			pAccion = Integer.parseInt(request.getParameter("accion"));			
		}
		System.out.println("id: " +pID+ " - Accion: "+pAccion);
		
	
		
		switch(pAccion){
			case Constantes.ACCION_LISTAR:
				listarRecetas(request,response);
				break;
			case Constantes.ACCION_ELIMINAR:
				eliminarRecetas(request,response);
				break;
			case Constantes.ACCION_NUEVO:
				formularioReceta(request,response);
				break;
			case Constantes.ACCION_MODIFICAR:
				formularioReceta(request,response);
				break;					
		}
		
		dispatcher.forward(request, response);
	}

	private void listarRecetas(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Listando Recetas");
		ArrayList<Receta> recetas = new ArrayList<Receta>();
		recetas=daoRecetas.getAll();
		request.setAttribute("recetas", recetas);
		request.setAttribute("origen", "Recetas");
      	dispatcher = request.getRequestDispatcher("backoffice/recetas.jsp");
	}

	private void eliminarRecetas(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Eliminando Receta");
		//Primero eliminar sus IngredientesReceta si es que los tiene
		daoIngredientesReceta.delete(pID);
		daoRecetas.delete(pID);
		Mensaje msg=new Mensaje(Mensaje.MSG_SUCCESS,"Receta eliminada");
		request.setAttribute("msg", msg);
		listarRecetas(request,response);
	}		
	
	private void formularioReceta(HttpServletRequest request, HttpServletResponse response) {
		Receta receta=null;		
		ArrayList<String> nombresIngredientes=new ArrayList<String>();
		System.out.println("Abriendo formulario de Receta");
		
		if(pAccion==Constantes.ACCION_NUEVO){
			receta=new Receta();
			
		}else{
			receta = daoRecetas.getById(pID);
			receta.setIngredientes(daoIngredientesReceta.getByRecetaId(pID));
			//Preparar ingredientes y cantidades			
			  //nombres de los ingredientes de la receta
			 
			  for(int i=0;i<receta.getIngredientes().size();i++){
				  int id=receta.getIngredientes().get(i).getIdIngrediente();
				  String nombre = Daos.getById(id, Daos.ingredientes).getNombre();
				  nombresIngredientes.add(nombre);
				  //nombresIngredientes.add(daoIngredientes.getById(receta.getIngredientes().get(i).getIdIngrediente()).getNombre());
			  }
			//pIngredientes= daoIngredientesReceta.getByRecetaId(pID);
			//pIngredientes = receta.getIngredientes();
			//System.out.println("Ingredientes de la receta: "+pIngredientes.toString());

		}
		
		request.setAttribute("categorias", Daos.categorias);
		request.setAttribute("ingredientes", Daos.ingredientes);
		request.setAttribute("tiposCocina", Daos.tiposCocina);
		request.setAttribute("accionFormulario", pAccion);		
		request.setAttribute("receta", receta);
		request.setAttribute("nombresIngredientes", nombresIngredientes);
		dispatcher=request.getRequestDispatcher("backoffice/formRecetas.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg=null;
		
		getParametersFormRecetas(request);
		Receta receta = new Receta();
		receta.setNombre(pNombre);
		receta.setPreparacion(pPreparacion);
		receta.setTiempo(pTiempo);
		receta.setCategoria(pCategoria);
		receta.setTipoCocina(pTiposCocina);
		receta.setFotografia(pFotografia);
		receta.setIngredientes(pIngredientes);
		receta.setPublico(pPublico);

		//Alta de Receta
		if (pID==-1){
			System.out.println("Añadiendo receta "+pID);

			pID=daoRecetas.save(receta);
			if(pID!=-1){
				request.setAttribute("msg", "Receta añadida. Aparece al final de la tabla");

				//Añado los datos a IngredientesReceta
				for(int i=0;i<pIngredientes.size();i++){
					//Guardo la ID de la receta que acabo de obtener					
					pIngredientes.get(i).setIdReceta(pID);
					if(daoIngredientesReceta.save(pIngredientes.get(i))==-1){
						msg=new Mensaje(Mensaje.MSG_WARNING,"Error al añadir los ingredientes");
					}
				}
			}else{
				msg=new Mensaje(Mensaje.MSG_WARNING,"Error al añadir la receta");
			}
		//Editar Receta
		}else{
			System.out.println("Editando receta "+pID);

			//elimina los IngredientesReceta para luego meter los nuevos
			daoIngredientesReceta.delete(pID);
						
			receta.setId(pID);

			if(daoRecetas.update(receta)){
				//Añado los datos a IngredientesReceta
				for(int i=0;i<pIngredientes.size();i++){
					if(daoIngredientesReceta.save(pIngredientes.get(i))==-1){
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
		recetas=daoRecetas.getAll();
		request.setAttribute("recetas", recetas);
		request.setAttribute("origen", "Recetas");
		request.setAttribute("msg", msg);
      	dispatcher = request.getRequestDispatcher("backoffice/recetas.jsp");		
		dispatcher.forward(request, response);		
	}
	
	private void getParametersFormRecetas(HttpServletRequest request){
		
		try{
			  request.setCharacterEncoding("UTF-8");
			  
			
			  DiskFileItemFactory factory = new DiskFileItemFactory();
		      factory.setSizeThreshold(maxMemSize);
		      factory.setRepository(new File(Constantes.IMG_UPLOAD_TEMP_FOLDER));      
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      upload.setSizeMax( maxFileSize );
		      
		      //Parametros de la request del formulario, NO la imagen
		      HashMap<String, String> dataParameters = new HashMap<String,String>();
		      
		      List<FileItem> items = upload.parseRequest(request);
    		  pListaIngredientes = new ArrayList<String>();
		      for(FileItem item:items){
		    	  
		    	  //parametro formulario
		    	  if(item.isFormField()){
		    		  dataParameters.put(item.getFieldName(), item.getString("UTF-8"));
		    		  //recoger array de ingredientes y cantidades
		    		  //listaIngredientes contendra: "CANTIDAD de ID_INGREDIENTE", ...
		    		  if("listaIngredientes".equals(item.getFieldName())){
		    			  pListaIngredientes.add(item.getString("UTF-8"));
		    		  }

		    	  //imagen
		    	  }else{
		    		  if(!"".equals(item.getName())){
		    			  //se hace si hay archivo para subir  
			    		  fileName     = dataParameters.get("nombreReceta")+"_"+item.getName();
				            
				          if(!"".equals(item.getName())){
				            	file = new File( Constantes.IMG_UPLOAD_FOLDER + "\\" +fileName );
				            	item.write( file ) ; //guarda la imagen
				           }
		    	  	}
		    	  }
		      } //End: for List<FileItem>
		      
		      System.out.println("DATA PARAMETERS: "+dataParameters.toString());
		      if(dataParameters.get("idReceta")!=null){
		    	  pID = Integer.parseInt(dataParameters.get("idReceta"));  
		      }
		      if(dataParameters.get("nombreReceta")!=null){
		    	  pNombre = dataParameters.get("nombreReceta");
		      }
		      if(dataParameters.get("tiempo")!=null){
		    	  pTiempo=dataParameters.get("tiempo");
		      }
		      if(dataParameters.get("preparacion")!=null){
		    	  pPreparacion = dataParameters.get("preparacion");
		      }
		      if(dataParameters.get("categoria")!=null){
	   			  pCategoria = new Categoria();
	   			  pCategoria = pCategoria.casteo(Daos.getById(Integer.parseInt(dataParameters.get("categoria")), Daos.categorias));
		      }
		      if(dataParameters.get("tipoCocina")!=null){
	   			  pTiposCocina = new TipoCocina();
	   			  pTiposCocina=pTiposCocina.casteo(Daos.getById(Integer.parseInt(dataParameters.get("tipoCocina")), Daos.tiposCocina));
		      }
			  if(fileName==null){
				  pFotografia=Constantes.IMG_DEFAULT_RECETA;
			  }else{
				  pFotografia=fileName;
			  }
			  if (dataParameters.get("publico")!= null){
				  pPublico =Integer.parseInt(dataParameters.get("publico"));
			  }			  
			  pIngredientes = new ArrayList<IngredientesReceta>();
			  for(int i=0;i<pListaIngredientes.size();i++){
				  //frase sera "CANTIDAD de ID_INGREDIENTE"
				  String frase = pListaIngredientes.get(i);
				  int separador=frase.indexOf(" de ");
				  String strCantidad = frase.substring(0, separador);
				  String strIngrediente = frase.substring(separador+4, frase.length());
				  pIngrediente= new IngredientesReceta(pID,Integer.parseInt(strIngrediente),strCantidad);
				  pIngredientes.add(pIngrediente);
				  				  
				  System.out.println("IngredienteReceta: "+pIngrediente.toString());
			  }
			  
			  
			  System.out.println("pFotografia = "+pFotografia);
			  System.out.println("fileName = "+fileName);
			  System.out.println("Lista de ingredientes = "+pListaIngredientes);
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
