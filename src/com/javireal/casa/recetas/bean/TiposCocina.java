package com.javireal.casa.recetas.bean;


public class TiposCocina {

	private int id;
	private String tipoCocina;

	
	/************ CONSTRUCTOR *************************/
	
	public TiposCocina() {
		super();
		this.id=-1;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTipoCocina() {
		return tipoCocina;
	}


	public void setTipoCocina(String tipoCocina) {
		this.tipoCocina = tipoCocina;
	}


	@Override
	public String toString() {
		return "TiposCocina [id=" + id + ", tipoCocina=" + tipoCocina + "]";
	}
	
	
}
