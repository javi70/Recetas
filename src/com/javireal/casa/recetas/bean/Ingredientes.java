package com.javireal.casa.recetas.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javireal.casa.recetas.Constantes;

public class Ingredientes {

	private int id;
	private String ingrediente;

	public Ingredientes() {
		super();
		this.id=-1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	@Override
	public String toString() {
		return "Ingredientes [id=" + id + ", ingrediente=" + ingrediente + "]";
	}	
	
	
}
