package com.javireal.casa.recetas.bean;

public class Elemento {
	
	private int id;
	private String nombre;
	
	public Elemento() {
		super();
		this.id=-1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Elemento [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
