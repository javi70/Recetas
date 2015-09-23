package com.javireal.casa.recetas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javireal.casa.recetas.bean.Categorias;
import com.javireal.casa.recetas.bean.Ingredientes;

public class DAOIngredientes implements Persistable{
	
	@Override
	public int save(Object o) {
		int resul=-1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rsKeys=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			Ingredientes ingrediente=(Ingredientes)o;
			String pIngrediente = ingrediente.getIngrediente();
	    	sql = "INSERT INTO `ingredientes` (`ingrediente`) VALUES (?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, pIngrediente);
	    	System.out.println(ingrediente.toString());
	    	//ejecutar insert
	    	if ( pst.executeUpdate() != 1){	    		
				//aqui recuperamos la id del registro recien insertado para devolverlo
				rsKeys = pst.getGeneratedKeys();
				if (rsKeys.next()){
					resul=rsKeys.getInt(1);
				}else{
					throw new SQLException("No se ha podido generar ID");
				}
			}    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
				if(rsKeys!=null){
					rsKeys.close();
				}				
				DataBaseHelper.closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
		return resul;
	}

	@Override
	public Object getById(int id) {
		Ingredientes resul = new Ingredientes();
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			sql = "SELECT * FROM `ingredientes` WHERE `id` =?;";
			pst = con.prepareStatement(sql); 
			pst.setInt(1, id);
	    	rs = pst.executeQuery ();
	    	rs.first();
	    	resul=mapeo(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}				
				if(pst!=null){
					pst.close();
				}
				DataBaseHelper.closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
		return resul;
	}
	
	@Override
	public ArrayList<Object> getAll() {
		ArrayList<Object> resul = new ArrayList<Object>();
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql = "SELECT * FROM `ingredientes` ORDER BY `ingrediente`;";
			pst = con.prepareStatement(sql); 
	    	rs = pst.executeQuery ();
	    	Ingredientes ingrediente= null;	    	
	    	while(rs.next()){
	    		ingrediente = new Ingredientes();
	    		ingrediente.setId(rs.getInt("id"));
	    		ingrediente.setIngrediente(rs.getString("ingrediente"));    		
	    		resul.add(ingrediente);
	    	}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}				
				if(pst!=null){
					pst.close();
				}
				DataBaseHelper.closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return resul;
	}

	@Override
	public boolean update(Object o) {
		Ingredientes ingrediente= (Ingredientes)o;
		boolean resul=false;
		String sql="";
		PreparedStatement pst=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "UPDATE `ingredientes` SET `ingrediente`=? WHERE `id`=?;";
			pst = con.prepareStatement(sql); 
			pst.setString(1,ingrediente.getIngrediente());
			pst.setInt(2, ingrediente.getId());
	    	if ( pst.executeUpdate() != 1){	    		
	    		throw new Exception("No se ha realizado actualizacion: " + sql);	    		
	    	}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
				DataBaseHelper.closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul=false;
		String sql="";
		PreparedStatement pst=null;	
		try {
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "DELETE FROM `ingredientes` WHERE `id` =?;";	    	
	    	pst = con.prepareStatement(sql);
	    	pst.setInt(1, id);
	    	//ejecutar delete
	    	if ( pst.executeUpdate() != 1){	    		
	    		throw new Exception("No se ha realizado eliminacion: " + sql);
	    	}else{
	    		resul=true;
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
				DataBaseHelper.closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return resul;
	}
	
	/**
	 * Mapea un ResultSet a un TiposCocina
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Ingredientes mapeo(ResultSet rs) throws SQLException{
		Ingredientes ingrediente= new Ingredientes();
		ingrediente.setId( rs.getInt("id"));
		ingrediente.setIngrediente(rs.getString("ingrediente"));
		return ingrediente;
	}
	
	/**
	 * Mira si ya existe ese tipo de cocina
	 * @param tipoCocina
	 * @return -1 si no existe, su indice si ya existe
	 */
	public int existe(String ingrediente){
		int resul = -1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null; 
		try {
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "SELECT `id` FROM `ingredientes` WHERE LOWER(`ingrediente`) =?;";			
	    	pst = con.prepareStatement(sql);
	    	pst.setString(1, ingrediente.toLowerCase());
	    	rs = pst.executeQuery ();
	    	while(rs.next()){
	    		resul=rs.getInt("id");
	    	}
	    	
		} catch (Exception e) {
			e.printStackTrace();
    		return resul;
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}				
				if(pst!=null){
					pst.close();
				}
				DataBaseHelper.closeConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;
	}
	
	
}