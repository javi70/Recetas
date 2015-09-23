package com.javireal.casa.recetas;

import java.sql.*;

public class DriverBD{
		
	public static Connection conexion;
	
	public void abrirConexion(){
		System.out.println("Conectando a la BD");
		try{ 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/recetas", "root", ""); 
		 }catch (Exception e){ 
			 e.printStackTrace(); 
		 } 			
	}
	
	public void cerrarConexion(){
		try {
            conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (Exception e){
			 e.printStackTrace(); 
        }
	}
	
	public DriverBD(){

		System.out.println("Creando driver");
		
		 try{ 
			 Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			 conexion = DriverManager.getConnection("jdbc:mysql://localhost/recetas", "root", ""); 
/*			 Statement s = conexion.createStatement(); 
			 ResultSet rs = s.executeQuery ("select * from categorias"); 
			 while (rs.next()){ 
				 System.out.println (rs.getInt ("ID") + "|" + rs.getString ("categoria") ); 
			 } 
			 rs.close(); 
*/			 
			 conexion.close(); 
		 }catch (Exception e){ 
			 e.printStackTrace(); 
		 } 	
	 } 	
}
