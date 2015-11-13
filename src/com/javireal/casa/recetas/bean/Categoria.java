package com.javireal.casa.recetas.bean;


public class Categoria extends Elemento{

	public Categoria() {
		super();
	}

	public Categoria casteo(Elemento e){
		Categoria resul = new Categoria();
		resul.setId(e.getId());
		resul.setNombre(e.getNombre());
		resul.setPublico(e.getPublico());
		return resul;
	}
}
