/**
 * 
 */
package com.javireal.casa.recetas.bean;

import java.util.ArrayList;


/**
 * Clase para tener las recetas
 * @author Javi
 *
 */
public class Receta {
	

	//Atributos
	private int id;
	private String nombre;
	private ArrayList<IngredientesReceta> ingredientes;
	private String fotografia;
	private String preparacion;
	private String tiempo;
	private Categoria categoria;
	private TipoCocina tipoCocina;
	private int publico;
	
	//Constructores
	public Receta() {
		super();
		this.setId(-1);
		this.setNombre("");
//		this.setIngrediente( null);
		this.setFotografia(null);
		this.setPreparacion("");
		this.setTiempo("");
	}

	public Receta(int codigo) {
		super();
		this.setId(codigo);
		this.setNombre("");
//		this.setIngrediente( null);
		this.setFotografia(null);
		this.setPreparacion("");
		this.setTiempo("");
	}

	public Receta(int codigo, String nombre) {
		super();
		this.setId(codigo);
		this.setNombre(nombre);
//		this.setIngrediente( null);
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
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the tipoCocina
	 */
	public TipoCocina getTipoCocina() {
		return tipoCocina;
	}

	/**
	 * @param tipoCocina the tipoCocina to set
	 */
	public void setTipoCocina(TipoCocina tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

	public ArrayList<IngredientesReceta> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<IngredientesReceta> ingredientes) {
		this.ingredientes = ingredientes;
	}

	
	public int getPublico() {
		return publico;
	}

	public void setPublico(int publico) {
		this.publico = publico;
	}

	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", ingredientes="
				+ ingredientes + ", fotografia=" + fotografia
				+ ", preparacion=" + preparacion + ", tiempo=" + tiempo
				+ ", categoria=" + categoria + ", tipoCocina=" + tipoCocina
				+ ", publico=" + publico + "]";
	}

	
	

}
