package com.javireal.casa.recetas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.javireal.casa.recetas.bean.Receta;

public class Utilidades {
	
	private static final String PATH_INGREDIENTES = Constantes.PATH_DATA_FOLDER + "ingredientes.txt";
	
	public static void serializar(Receta receta) throws IOException{
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.dat"))){
			out.writeObject(receta);
		}
	}

	public static Receta desSerializar() throws IOException, ClassNotFoundException{
		Receta resul = null;
		try (ObjectInputStream out = new ObjectInputStream(new FileInputStream("test.dat"))){
			resul = (Receta)out.readObject();
		}
		return resul;
	}

	/**
	 * Lee los ingredientes de "ingredientes.txt" y los carga en un ArrayList
	 * @return {@code ArrayList<String>} con los ingredientes
	 */
	public static ArrayList<String> cargarIngredientes(){
		ArrayList<String> resul = new ArrayList<String>();
		 File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	 
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (PATH_INGREDIENTES);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	 
	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null){
	            System.out.println(linea);
	         	resul.add(linea);
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{         
		        if( null != br ){   
		               br.close();     
		            }                  
	        	 
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }

		return resul;
	}
	
	/**
	 * Guarda los ingredientes pasados como parametro en el fichero "ingredientes.txt" 
	 * @param {@code ArrayList<String> ingredientes}
	 * @return {@code boolean} true si se guarda correctamente, false en caso contrario
	 */
	public static boolean guardarIngredientes(ArrayList<String> ingredientes){
		boolean resul = false;
		File archivo = null;
	    FileWriter fw = null;
	    BufferedWriter bw = null;
	 
	      try {
	         archivo = new File (PATH_INGREDIENTES);
	         fw = new FileWriter (archivo);
	         bw = new BufferedWriter(fw);
	 
	         // Escritura del fichero
	         for (int i = 0; i < ingredientes.size(); i++){
	        	 	bw.write(ingredientes.get(i));
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{       
	            if( null != bw ){   
		               bw.close();     
		            }                  
	        	 
	            if( null != fw ){   
	               fw.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }

		return resul;
	}
}
