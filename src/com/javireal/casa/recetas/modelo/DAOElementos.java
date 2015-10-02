package com.javireal.casa.recetas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javireal.casa.recetas.Constantes;
import com.javireal.casa.recetas.bean.Elemento;

public class DAOElementos implements Persistable{
	
	private String tabla="";
	
	public DAOElementos(String tabla){
		super();
		this.tabla=tabla;
	} 
	
	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	@Override
	public int save(Object o) {
		int resul=-1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rsKeys=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			Elemento elemento=(Elemento)o;
			sql = "INSERT INTO `"+this.tabla+"` (`nombre`) VALUES (?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, elemento.getNombre());
			if(pst.executeUpdate()==1){
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
		String sql="";
		PreparedStatement pst=null;
		Elemento resul = new Elemento();
		ResultSet rs=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql = "SELECT * FROM `"+this.tabla+"` WHERE `id` =?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery ();
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
			sql = "SELECT * FROM `"+this.tabla+"` ";
			pst = con.prepareStatement(sql);
	    	rs = pst.executeQuery ();
	    	Elemento elemento= null;	    	
	    	while(rs.next()){
	    		elemento = new Elemento();
	    		elemento.setId(rs.getInt("id"));
	    		elemento.setNombre(rs.getString("nombre"));    		
	    		resul.add(elemento);
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
		boolean resul=false;
		Elemento elemento= (Elemento)o;
		String sql="";
		PreparedStatement pst=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "UPDATE `"+this.tabla+"` SET `nombre`=? WHERE `id`=?;";
			pst = con.prepareStatement(sql);
			pst.setString(1, elemento.getNombre());
			pst.setInt(2, elemento.getId());
			if(pst.executeUpdate()==1){
				resul=true;
			}else{	    		
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
	    	sql   = "DELETE FROM `"+this.tabla+"` WHERE `id` =?;";	    	
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
	 * Mapea un ResultSet a un Elemento
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Elemento mapeo(ResultSet rs) throws SQLException{
		Elemento elemento= new Elemento();
		elemento.setId( rs.getInt("id"));
		elemento.setNombre(rs.getString("nombre"));
		return elemento;
	}
	
	/**
	 * Mira si ya existe ese elemento
	 * @param nombre
	 * @return -1 si no existe, su indice si ya existe
	 */
	public int existe(String nombre){
		int resul = -1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null; 
		try {
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "SELECT `id` FROM `"+this.tabla+"` WHERE LOWER(`nombre`) = ?;";
	    	pst = con.prepareStatement(sql);
	    	pst.setString(1, nombre.toLowerCase());
	    	
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