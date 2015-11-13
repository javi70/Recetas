package com.javireal.casa.recetas.bean;

/**
 * Clase para tener los ingredientes de las recetas
 * @author Javi
 *
 */

public class IngredientesReceta {

	private int idReceta;
	private int idIngrediente;
	private String nombreIngrediente;
	private String cantidad;
	
	public IngredientesReceta(){
		super();
	}

	public IngredientesReceta(int idIngrediente, String cantidad) {
		super();
		this.idReceta = -1;
		this.idIngrediente = idIngrediente;
		this.cantidad = cantidad;
	}

	public IngredientesReceta(int idReceta, int idIngrediente, String cantidad) {
		super();
		this.idReceta = idReceta;
		this.idIngrediente = idIngrediente;
		this.cantidad = cantidad;
	}

	public IngredientesReceta(int idReceta, int idIngrediente, String nombreIngrediente, String cantidad) {
		super();
		this.idReceta = idReceta;
		this.idIngrediente = idIngrediente;
		this.nombreIngrediente=nombreIngrediente;
		this.cantidad = cantidad;
	}

	
	public int getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	public int getIdIngrediente() {
		return idIngrediente;
	}

	public String getNombreIngrediente() {
		return nombreIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}

	@Override
	public String toString() {
		return "IngredientesReceta [idReceta=" + idReceta + ", idIngrediente="
				+ idIngrediente + ", nombreIngrediente=" + nombreIngrediente
				+ ", cantidad=" + cantidad + "]";
	}
	
	
	
	
}
