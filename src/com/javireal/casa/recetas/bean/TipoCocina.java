package com.javireal.casa.recetas.bean;


public class TipoCocina extends Elemento {

	public TipoCocina() {
		super();
	}
	
	public TipoCocina casteo(Elemento e){
		TipoCocina resul = new TipoCocina();
		resul.setId(e.getId());
		resul.setNombre(e.getNombre());
		return resul;
	}
}
