package com.javireal.casa.recetas;

import java.io.IOException;

import com.javireal.casa.recetas.bean.Receta;

public class Entrada {

	static Receta receta = null;
	static Receta receta1 = null;
	
	public static void main(String[] args) {
		receta= new Receta(1,"Alubias");
		try {
			Utilidades.serializar(receta);
			System.out.println("Guardada "+receta.getNombre());
		} catch (IOException e) {
			System.out.println("Error con "+receta.getNombre());
			e.printStackTrace();
		}
		
		receta= new Receta(2,"Paella");
		receta.setTiempo("30");
		try {
			Utilidades.serializar(receta);
			System.out.println("Guardada "+receta.getNombre());			
		} catch (IOException e) {
			System.out.println("Error con "+receta.getNombre());			
			e.printStackTrace();
		}
		
		receta1=null;
		try {
			receta1=Utilidades.desSerializar();
			System.out.println("Leida "+receta1.getNombre());			
		} catch (ClassNotFoundException e) {
			System.out.println("Error con clase "+receta1.getNombre());	
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error con "+receta1.getNombre());	
			e.printStackTrace();
		}
		
	receta1=null;
		
		try {
			receta1=Utilidades.desSerializar();
			System.out.println("Leida "+receta1.getNombre());			
		} catch (ClassNotFoundException e) {
			System.out.println("Error con clase "+receta1.getNombre());	
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error con "+receta1.getNombre());	
			e.printStackTrace();
		}		
		System.out.println("FIN");
	}

}
