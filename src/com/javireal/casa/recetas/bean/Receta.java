/**
 * 
 */
package com.javireal.casa.recetas.bean;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase para tener las recetas
 * @author Javi
 *
 */
public class Receta implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private int id;
	private String nombre;
	private ArrayList<Ingredientes> ingredientes;
	private ArrayList<String> cantidades;
	private String fotografia;
	private String preparacion;
	private String tiempo;
	private Categorias categoria;
	private TiposCocina tipoCocina;
	
	//Constructores
	public Receta() {
		super();
		this.setId(-1);
		this.setNombre("");
		this.setIngredientes( null);
		this.setFotografia(null);
		this.setPreparacion("");
		this.setTiempo("");
	}

	public Receta(int codigo) {
		super();
		this.setId(codigo);
		this.setNombre("");
		this.setIngredientes( null);
		this.setFotografia(null);
		this.setPreparacion("");
		this.setTiempo("");
	}

	public Receta(int codigo, String nombre) {
		super();
		this.setId(codigo);
		this.setNombre(nombre);
		this.setIngredientes( null);
		this.setFotografia(null);
		this.setPreparacion("");
		this.setTiempo("");
	}
	
	//Seters y geters
	
	public int getId() {
		return id;
	}

	public void setId(int codigo) {
		this.id = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFotografia() {
		return fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	
	/**
	 * @return the ingredientes
	 */
	public ArrayList<Ingredientes> getIngredientes() {
		return ingredientes;
	}

	/**
	 * @param ingredientes the ingredientes to set
	 */
	public void setIngredientes(ArrayList<Ingredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}

	/**
	 * @return the cantidades
	 */
	public ArrayList<String> getCantidades() {
		return cantidades;
	}

	/**
	 * @param cantidades the cantidades to set
	 */
	public void setCantidades(ArrayList<String> cantidades) {
		this.cantidades = cantidades;
	}	
	
	/**
	 * @return the categoria
	 */
	public Categorias getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the tipoCocina
	 */
	public TiposCocina getTipoCocina() {
		return tipoCocina;
	}

	/**
	 * @param tipoCocina the tipoCocina to set
	 */
	public void setTipoCocina(TiposCocina tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", ingredientes="
				+ ingredientes + ", cantidades=" + cantidades + ", fotografia="
				+ fotografia + ", preparacion=" + preparacion + ", tiempo="
				+ tiempo + ", categoria=" + categoria + ", tipoCocina="
				+ tipoCocina + "]";
	}

	
	
	

}
