package com.javireal.casa.recetas.modelo;

import java.util.ArrayList;

/**
 * Interfaz para permitir guardar, recuperar, modificar y eliminar beans.
 * Soporta las operaciones basicas de CRUD 
 * @author ur00
 */
public interface Persistable<T> {

	/**
	 * Persiste el Objeto y lo guarda
	 * @param o {@code Obejct} a guardar
	 * @return {@code int} Identificador del objeto guardado, -1 en caso de fallo
	 */
	int save (T t);
	
	/**
	 * Recupera Objecto por su Identificador
	 * @param id {@code int} identificador del objeto a recuperar
	 * @return {@code Object} objeto encontrado o null en caso contrario
	 */
	T getById(int id);
	
	/**
	 * Recupera una coleccion de Objetos
	 * @return coleccion de objetos, si no existen coleccion vacia
	 */
	ArrayList<T> getAll();
	
	/**
	 * Modificado un Objeto el cual debe tener un identificador definido
	 * @param o {@code Obejct} a modificar
	 * @return true si se modificaba bien, false en caso contrario
	 */
	boolean update (T t);
	
	/**
	 * Eliminar un objecto por su identificador
	 * @param id {@code int} identificador del objeto a eliminar
	 * @return true si elimina, false en caso contrario
	 */
	boolean delete (int id);
	
}
