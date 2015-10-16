package com.javireal.casa.recetas;

import java.util.ArrayList;
import java.util.Iterator;

import com.javireal.casa.recetas.bean.Elemento;

public class Daos {

	//DAOS genericos
		public static ArrayList<Elemento> categorias;
		public static ArrayList<Elemento> tiposCocina;
		public static ArrayList<Elemento> ingredientes;
		
		/**
		 * Devuelve el {@code Elemento} pasándole la id y el ArrayList donde buscar
		 * @param id
		 * @param lista
		 * @return {@code Elemento}
		 */
		public static Elemento getById(int id, ArrayList<Elemento> lista){
			Elemento resul = null;
			Iterator<Elemento> iter = lista.iterator();
			while(iter.hasNext()){
				resul = iter.next();
				if(resul.getId()==id){
					return resul;
				}else{
					resul=null;
				}
			}
			return resul;
		}
}
