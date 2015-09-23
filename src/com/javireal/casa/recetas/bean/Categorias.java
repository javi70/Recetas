package com.javireal.casa.recetas.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Categorias {

	private int id;
	private String categoria;
	
	public Categorias() {
		super();
		this.id=-1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Categorias [id=" + id + ", categoria=" + categoria + "]";
	}

}
