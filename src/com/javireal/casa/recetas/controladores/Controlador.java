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
import com.javireal.casa.recetas.bean.Categorias;
import com.javireal.casa.recetas.bean.Ingredientes;
import com.javireal.casa.recetas.bean.Receta;
import com.javireal.casa.recetas.bean.TiposCocina;
import com.javireal.casa.recetas.modelo.DAOCategorias;
import com.javireal.casa.recetas.modelo.DAOIngredientes;
import com.javireal.casa.recetas.modelo.DAORecetas;
import com.javireal.casa.recetas.modelo.DAOTiposCocina;

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private RequestDispatcher dispatcher = null;       
	//Modelos DAO
	DAOTiposCocina daoTiposCocina = null;
	DAOCategorias daoCategorias = null;
	DAOIngredientes daoIngredientes = null;
	DAORecetas daoRecetas = null;
	
	//parametros
	private int pAccion;
	private int pID; 
	private int pOrigen;
	private String pNombre;
	private String pTiempo;
	private String pPreparacion;
	private Categorias pCategoria;
	private TiposCocina pTiposCocina;
	private Ingredientes pIngredientes;
	private String pFotografia;
	
	
	//variables subida imagenes
 	private boolean isMultipart;
 	private String filePath = Constantes.IMG_UPLOAD_FOLDER;
 	private int maxFileSize = 10000 * 1024;
 	private int maxMemSize = 40 * 1024;
 	private File file ;
    private String fileName;
    private String contentType;
    private boolean isInMemory;
    private long sizeInBytes;
    
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoTiposCocina = new DAOTiposCocina();
		daoCategorias = new DAOCategorias();
		daoIngredientes = new DAOIngredientes();
		daoRecetas = new DAORecetas();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoger parametros: accion, id, origen
		if(request.getParameter("id")!=null){
			pID = Integer.parseInt(request.getParameter("id"));			
		}
		if(request.getParameter("accion")!=null){
			pAccion = Integer.parseInt(request.getParameter("accion"));			
		}
		if(request.getParameter("origen")!=null){
			pOrigen = Integer.parseInt(request.getParameter("origen"));			
		}

		System.out.println("id: " +pID+ " - Accion: "+pAccion+" - Origen: "+pOrigen );
		
		//En funcion del parametro 'origen' realizar la accion
		// 0: Recetas
		// 1: Ingredientes
		// 2: Categorias
		// 3: TiposCocina
		
		switch (pOrigen){
			case Constantes.ORIGEN_TIPOSCOCINA:
				switch(pAccion){
				case Constantes.ACCION_LISTAR:
					listar_tiposCocina(request,response);
					break;
				case Constantes.ACCION_ELIMINAR:
					eliminar_tiposCocina(request,response);
					break;
				}
				break;
			case Constantes.ORIGEN_CATEGORIAS:
				switch(pAccion){
				case Constantes.ACCION_LISTAR:
					listar_categorias(request,response);
					break;
				case Constantes.ACCION_ELIMINAR:
					eliminar_categorias(request,response);
					break;
				}
				break;
			case Constantes.ORIGEN_INGREDIENTES:
				switch(pAccion){
				case Constantes.ACCION_LISTAR:
					listar_ingredientes(request,response);
					break;
				case Constantes.ACCION_ELIMINAR:
					eliminar_ingredientes(request,response);
					break;
				}
				break;
			case Constantes.ORIGEN_RECETAS:
				switch(pAccion){
				case Constantes.ACCION_LISTAR:
					listar_recetas(request,response);
					break;
				case Constantes.ACCION_ELIMINAR:
					eliminar_recetas(request,response);
					break;
				case Constantes.ACCION_NUEVO:
					formulario_receta(request,response);
					break;
				case Constantes.ACCION_MODIFICAR:
					formulario_receta(request,response);
					break;					
				}
				break;								
				
		}
		
		dispatcher.forward(request, response);
	}

	private void listar_tiposCocina(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Listando TiposCocina");
		ArrayList<Object> tiposCocina = new ArrayList<Object>();
		tiposCocina=daoTiposCocina.getAll();
		request.setAttribute("tiposCocina", tiposCocina);
      	dispatcher = request.getRequestDispatcher("backoffice/tiposCocina.jsp");
	}

	private void eliminar_tiposCocina(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Eliminando TiposCocina");
		daoTiposCocina.delete(pID);
		request.setAttribute("msg", "Tipo de cocina eliminado.");
		listar_tiposCocina(request,response);
	}
	
	private void listar_categorias(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Listando Categorias");
		ArrayList<Object> categorias = new ArrayList<Object>();
		categorias=daoCategorias.getAll();
		request.setAttribute("categorias", categorias);
      	dispatcher = request.getRequestDispatcher("backoffice/categorias.jsp");
	}

	private void eliminar_categorias(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Eliminando Categorias");
		daoCategorias.delete(pID);
		request.setAttribute("msg", "Categoria eliminada.");
		listar_categorias(request,response);
	}	
	
	private void listar_ingredientes(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Listando Ingredientes");
		ArrayList<Object> ingredientes = new ArrayList<Object>();
		ingredientes=daoIngredientes.getAll();
		request.setAttribute("ingredientes", ingredientes);
      	dispatcher = request.getRequestDispatcher("backoffice/ingredientes.jsp");
	}

	private void eliminar_ingredientes(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Eliminando Ingrediente");
		daoIngredientes.delete(pID);
		request.setAttribute("msg", "Ingrediente eliminado.");
		listar_ingredientes(request,response);
	}		
	
	private void listar_recetas(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Listando Recetas");
		ArrayList<Object> recetas = new ArrayList<Object>();
		recetas=daoRecetas.getAll();
		request.setAttribute("recetas", recetas);
      	dispatcher = request.getRequestDispatcher("backoffice/recetas.jsp");
	}

	private void eliminar_recetas(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Eliminando Receta");
		daoRecetas.delete(pID);
		request.setAttribute("msg", "Receta eliminada.");
		listar_recetas(request,response);
	}		
	
	private void formulario_receta(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Abriendo formulario de Receta");
		request.setAttribute("categorias", daoCategorias.getAll());
		request.setAttribute("ingredientes", daoIngredientes.getAll());
		request.setAttribute("tiposCocina", daoTiposCocina.getAll());
		request.setAttribute("accionFormulario", pAccion);
		Receta receta=null;
		if(pAccion==Constantes.ACCION_NUEVO){
			receta=new Receta();
		}else{
			receta = (Receta)daoRecetas.getById(pID);
		}
		request.setAttribute("receta", receta);
		
		dispatcher=request.getRequestDispatcher("backoffice/formRecetas.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("origen")!=null){
			pOrigen = Integer.parseInt(request.getParameter("origen"));
		}else{
			getParametersFormRecetas(request);
		}
		
		switch (pOrigen){
			case Constantes.ORIGEN_TIPOSCOCINA:
				if(request.getParameter("idTipoCocina")!=null){
					pID = Integer.parseInt(request.getParameter("idTipoCocina"));			
				}
		
				TiposCocina tp=null;
				//Alta de TipoCocina
				if (pID==-1){
					System.out.println("Añadiendo tipoCocina "+pID);
					tp = new TiposCocina();
					tp.setTipoCocina(request.getParameter("nombreTipoCocina"));
					if(daoTiposCocina.existe(request.getParameter("nombreTipoCocina"))==-1){
						daoTiposCocina.save(tp);
						request.setAttribute("msg", "Tipo de cocina añadido. Aparece al final de la tabla");
					}else{
						request.setAttribute("msg", "El tipo de cocina ya existe");
					}			
				
					listar_tiposCocina(request,response);
					
				//Editar TipoCocina	
				}else{
					System.out.println("Editando tipoCocina "+pID);
					tp = new TiposCocina();
					tp.setId(pID);
					tp.setTipoCocina(request.getParameter("nombreTipoCocina"));
					if(daoTiposCocina.existe(request.getParameter("nombreTipoCocina"))==-1){
						daoTiposCocina.update(tp);
						request.setAttribute("msg", "Tipo de cocina modificado.");
					}else{
						request.setAttribute("msg", "El tipo de cocina ya existe");
					}								
					listar_tiposCocina(request,response);
				}
				break;
			case Constantes.ORIGEN_CATEGORIAS:
				if(request.getParameter("idCategoria")!=null){
					pID = Integer.parseInt(request.getParameter("idCategoria"));			
				}
		
				Categorias categoria=null;
				//Alta de Categoria
				if (pID==-1){
					System.out.println("Añadiendo categoria "+pID);
					categoria = new Categorias();
					categoria.setCategoria(request.getParameter("nombreCategoria"));
					if(daoCategorias.existe(request.getParameter("nombreCategoria"))==-1){
						daoCategorias.save(categoria);
						request.setAttribute("msg", "Categoria añadida. Aparece al final de la tabla");
					}else{
						request.setAttribute("msg", "La categoria ya existe");
					}			
				
					listar_categorias(request,response);
					
				//Editar TipoCategoria
				}else{
					System.out.println("Editando categoria "+pID);
					categoria = new Categorias();
					categoria.setId(pID);
					categoria.setCategoria(request.getParameter("nombreCategoria"));
					if(daoCategorias.existe(request.getParameter("nombreCategoria"))==-1){
						daoCategorias.update(categoria);
						request.setAttribute("msg", "Categoria modificada.");
					}else{
						request.setAttribute("msg", "La categoria ya existe");
					}
					listar_categorias(request,response);
				}
				break;
			case Constantes.ORIGEN_INGREDIENTES:
				if(request.getParameter("idIngrediente")!=null){
					pID = Integer.parseInt(request.getParameter("idIngrediente"));			
				}
		
				Ingredientes ingrediente=null;
				//Alta de Ingrediente
				if (pID==-1){
					System.out.println("Añadiendo ingrediente "+pID);
					ingrediente = new Ingredientes();
					ingrediente.setIngrediente(request.getParameter("nombreIngrediente"));
					if(daoIngredientes.existe(request.getParameter("nombreIngrediente"))==-1){
						daoIngredientes.save(ingrediente);
						request.setAttribute("msg", "Ingrediente añadido. Aparece al final de la tabla");
					}else{
						request.setAttribute("msg", "El ingrediente ya existe");
					}			
				
					listar_ingredientes(request,response);
					
				//Editar Ingrediente
				}else{
					System.out.println("Editando ingrediente "+pID);
					ingrediente = new Ingredientes();
					ingrediente.setId(pID);
					ingrediente.setIngrediente(request.getParameter("nombreIngrediente"));
					if(daoIngredientes.existe(request.getParameter("nombreIngrediente"))==-1){					
						daoIngredientes.update(ingrediente);
						request.setAttribute("msg", "Ingrediente modificado.");
					}else{
						request.setAttribute("msg", "El ingrediente ya existe");
					}
					listar_ingredientes(request,response);
				}
				break;	
			case Constantes.ORIGEN_RECETAS:
				Receta receta=null;
				//Alta de Receta
				if (pID==-1){
					System.out.println("Añadiendo receta "+pID);
					receta = new Receta();
					receta.setNombre(pNombre);
					receta.setPreparacion(pPreparacion);
					receta.setTiempo(pTiempo);
					receta.setCategoria(pCategoria);
					receta.setTipoCocina(pTiposCocina);
					receta.setFotografia(fileName);

					if(daoRecetas.existe(pNombre)==-1){
						if(daoRecetas.save(receta)!=-1){;
							request.setAttribute("msg", "Receta añadida. Aparece al final de la tabla");
						}else{
							request.setAttribute("msg", "Error al añadir receta");
						}
					}else{
						request.setAttribute("msg", "La receta ya existe");
					}			
				
					listar_recetas(request,response);
					
				//Editar Receta
				}else{
					System.out.println("Editando receta "+pID);
					receta = new Receta();
					receta.setId(pID);
					receta.setNombre(pNombre);
					receta.setPreparacion(pPreparacion);
					receta.setTiempo(pTiempo);
					receta.setCategoria(pCategoria);
					receta.setTipoCocina(pTiposCocina);
					receta.setFotografia(pFotografia);

					if(daoRecetas.update(receta)){
						request.setAttribute("msg", "Receta modificada.");
					}else{
						request.setAttribute("msg", "Error al modificar receta");
					}
					listar_recetas(request,response);
				}
				break;				
		}

		dispatcher.forward(request, response);
		
	}
	
	private void getParametersFormRecetas(HttpServletRequest request){
		
		try{
			  request.setCharacterEncoding("UTF-8");
			  
			
			  DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File(Constantes.IMG_UPLOAD_TEMP_FOLDER));
		      
		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      
		      //Parametros de la request del formulario, NO la imagen
		      HashMap<String, String> dataParameters = new HashMap<String,String>();
		      
		      // Parse the request to get file items.
		      List<FileItem> items = upload.parseRequest(request);
		      for(FileItem item:items){
		    	  
		    	  //parametro formulario
		    	  if(item.isFormField()){
		    		  dataParameters.put(item.getFieldName(), item.getString("UTF-8"));
		    		  
		    	  //imagen
		    	  }else{
		    		  fileName     = dataParameters.get("idReceta")+"_"+item.getName();
			          contentType  = item.getContentType();
			          isInMemory  = item.isInMemory();
			          sizeInBytes    = item.getSize();
			            
			    	  if(!"".equals(fileName)){
			            	file = new File( Constantes.IMG_UPLOAD_FOLDER + "\\" +fileName );
			            	item.write( file ) ; //guarda la imagen
			           }
		    	  }
		      } //End: for List<FileItem>
		      
		      System.out.println("DATA PARAMETERS: "+dataParameters.toString());
		      //pOrigen=Constantes.ORIGEN_RECETAS;
		      pID = Integer.parseInt(dataParameters.get("idReceta"));
		      pNombre = dataParameters.get("nombreReceta");	
		      pTiempo=dataParameters.get("tiempo");
		      pPreparacion = dataParameters.get("preparacion");			
   			  pCategoria = (Categorias)daoCategorias.getById(Integer.parseInt(dataParameters.get("categoria")));
			  pTiposCocina = (TiposCocina)daoTiposCocina.getById(Integer.parseInt(dataParameters.get("tipoCocina")));
			  if("".equals(fileName)){
				  pFotografia=Constantes.IMG_DEFAULT_RECETA;
			  }else{
				  pFotografia=fileName;
			  }
			  System.out.println("pFotografia = "+pFotografia);
			  System.out.println("fileName = "+fileName);
		      
			  //faltan ingredientes, cantidades
/*		      
				if(request.getParameterValues("listaIngredientes")!=null){
					String[] ingredientes = request.getParameterValues("listaIngredientes");
				}
*/				


			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
