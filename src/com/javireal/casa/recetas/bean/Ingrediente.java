package com.javireal.casa.recetas.bean;


public class Ingrediente extends Elemento {

	public Ingrediente() {
		super();
	}
	
	public Ingrediente casteo(Elemento e){
		Ingrediente resul = new Ingrediente();
		resul.setId(e.getId());
		resul.setNombre(e.getNombre());
		resul.setPublico(e.getPublico());
		return resul;
	}
}
