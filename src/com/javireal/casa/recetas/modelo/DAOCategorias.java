package com.javireal.casa.recetas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javireal.casa.recetas.bean.Categorias;

public class DAOCategorias implements Persistable{
	
	@Override
	public int save(Object o) {
		int resul=-1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rsKeys=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			Categorias categoria=(Categorias)o;
			sql = "INSERT INTO `categorias` (`categoria`) VALUES (?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, categoria.getCategoria());
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
		Categorias resul = new Categorias();
		ResultSet rs=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql = "SELECT * FROM `categorias` WHERE `id` =?;";
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
			sql = "SELECT * FROM `categorias` ";
			pst = con.prepareStatement(sql);
	    	rs = pst.executeQuery ();
	    	Categorias categoria = null;	    	
	    	while(rs.next()){
	    		categoria = new Categorias();
	    		categoria.setId(rs.getInt("id"));
	    		categoria.setCategoria(rs.getString("categoria"));    		
	    		resul.add(categoria);
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
		Categorias categoria = (Categorias)o;
		String sql="";
		PreparedStatement pst=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "UPDATE `categorias` SET `categoria`=? WHERE `id`=?;";
			pst = con.prepareStatement(sql);
			pst.setString(1, categoria.getCategoria());
			pst.setInt(2, categoria.getId());
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
	    	sql   = "DELETE FROM `categorias` WHERE `id` =?;";	    	
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
	private Categorias mapeo(ResultSet rs) throws SQLException{
		Categorias categoria = new Categorias();
		categoria.setId( rs.getInt("id"));
		categoria.setCategoria(rs.getString("categoria"));
		return categoria;
	}
	
	/**
	 * Mira si ya existe ese tipo de cocina
	 * @param tipoCocina
	 * @return -1 si no existe, su indice si ya existe
	 */
	public int existe(String categoria){
		int resul = -1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null; 
		try {
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "SELECT `id` FROM `categorias` WHERE LOWER(`categoria`) = ?;";
	    	pst = con.prepareStatement(sql);
	    	pst.setString(1, categoria.toLowerCase());
	    	
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