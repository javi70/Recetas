package com.javireal.casa.recetas;


public class Constantes {

	public static final String ROOT_APP = "/Recetas/";
	public static final String APP_NAME = "Recetas";
	public static final String ADMIN = "";
	public static final String PASSWORD = "";
	
	//vistas publicas
	public static final String VIEW_PUBLIC_INDEX = ROOT_APP + "index.jsp";

	//rutas de datos
	public static final String PATH_DATA_FOLDER = ROOT_APP + "recetas";
	public static final String PATH_INGREDIENTES_FILE = "ingredientes.txt";
	public static final String PATH_TIPOSCOCINA_FILE = "tiposcocina.txt";
	public static final String PATH_CATEGORIAS_FILE = "categorias.txt";
	
	//vistas backoffice
	public static final String ROOT_BACK = ROOT_APP + "backoffice/";
	public static final String VIEW_BACK_INDEX = "backoffice/index.jsp";
	public static final String VIEW_BACK_ELEMENTOS = "backoffice/elementos.jsp";
	public static final String VIEW_BACK_INGREDIENTES = "backoffice/ingredientes.jsp";
	public static final String VIEW_BACK_TIPOSCOCINA = "backoffice/tiposCocina.jsp";	
	public static final String VIEW_BACK_CATEGORIAS = "backoffice/categorias.jsp";
	public static final String VIEW_BACK_RECETAS = "backoffice/recetas.jsp";
	
	//controladores
	public static final String CONTROLLER_HOME   = ROOT_APP + "home";
	public static final String CONTROLLER_ELEMENTOS   = ROOT_APP + "elementos";
	public static final String CONTROLLER_INGREDIENTES   = ROOT_APP + "ingredientes";
	public static final String CONTROLLER_TIPOSCOCINA   = ROOT_APP + "tiposCocina";
	public static final String CONTROLLER_CATEGORIAS   = ROOT_APP + "categorias";
	public static final String CONTROLLER_RECETAS   = ROOT_APP + "recetas";
	public static final String CONTROLLER   = ROOT_APP + "contenido";
	public static final String CONTROLLER_DETALLE   = ROOT_APP + "detalle";
	
	
	//Acciones
	public static final int ACCION_NUEVO     = 0;
	public static final int ACCION_DETALLE   = 1;
	public static final int ACCION_LISTAR    = 2;
	public static final int ACCION_ELIMINAR  = 3;
	public static final int ACCION_MODIFICAR = 4;
	
	//Origenes
	public static final int ORIGEN_RECETAS      = 0;
	public static final int ORIGEN_INGREDIENTES = 1;
	public static final int ORIGEN_CATEGORIAS   = 2;
	public static final int ORIGEN_TIPOSCOCINA  = 3;
	
	//Nombres de tablas
	public static final String TABLA_RECETAS      = "recetas";
	public static final String TABLA_INGREDIENTES = "ingredientes";
	public static final String TABLA_CATEGORIAS   = "categorias";
	public static final String TABLA_TIPOSCOCINA  = "tiposcocina";

	//Imagenes
	public static final String IMG_DEFAULT_RECETA = "receta.jpg";	
	public static final String IMG_WEB_PATH = "http://localhost:8081/uploads/";
	public static final String IMG_UPLOAD_FOLDER = "Z:\\Javi\\Documents\\CURSO\\apache-tomcat-6.0.44\\webapps\\uploads";
	public static final String IMG_UPLOAD_TEMP_FOLDER = "Z:\\Javi\\Documents\\CURSO\\apache-tomcat-6.0.44\\temp";
	public static final int MAX_FILE_SIZE = 2*1024*1024; //tamaño en bytes
	public static final int MAX_MEM_SIZE = 40 * 1024;	
	
	
	
	
	
	
	
}
