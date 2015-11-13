package com.javireal.casa.recetas.bean;

public class Elemento {
	
	private int id;
	private String nombre;
	private int publico;
	
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

	public int getPublico() {
		return publico;
	}

	public void setPublico(int publico) {
		this.publico = publico;
	}

	@Override
	public String toString() {
		return "Elemento [id=" + id + ", nombre=" + nombre + ", publico="
				+ publico + "]";
	}


	
}
