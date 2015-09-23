package com.javireal.casa.recetas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javireal.casa.recetas.bean.Categorias;
import com.javireal.casa.recetas.bean.TiposCocina;

/**
 * DAO: Data Access Object
 * Clase especializada en mapear una {@code TipoCocina} contra la Base Datos
 * Dispone de los metodos basicos para realizar las operaciones de CRUD
 * @author Javi
 *
 */
public class DAOTiposCocina implements Persistable{

	
	@Override
	public int save(Object o) {
		int resul=-1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rsKeys=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			TiposCocina tiposCocina=(TiposCocina)o;
			String pTipoCocina = tiposCocina.getTipoCocina();
			sql = "INSERT INTO `tiposcocina` (`tipo_cocina`) VALUES (?);";
			pst = con.prepareStatement(sql);
			pst.setString(1,pTipoCocina);
	    	System.out.println(tiposCocina.toString());
	    	//ejecutar insert
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
		TiposCocina resul = new TiposCocina();
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null;		
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql = "SELECT * FROM `tiposcocina` WHERE `id` =?;";
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
	    	sql = "SELECT * FROM `tiposcocina` ";
			pst = con.prepareStatement(sql); 
	    	rs = pst.executeQuery ();
	    	TiposCocina tiposCocina = null;	    	
	    	while(rs.next()){
	    		
	    		tiposCocina = new TiposCocina();
	    		tiposCocina.setId(rs.getInt("id"));
	    		tiposCocina.setTipoCocina(rs.getString("tipo_cocina"));    		
	    		resul.add(tiposCocina);
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
		TiposCocina tiposCocina = (TiposCocina)o;
		boolean resul=false;
		String sql="";
		PreparedStatement pst=null;		
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "UPDATE `tiposcocina` SET `tipo_cocina`=? WHERE `id`=?;";
			pst = con.prepareStatement(sql);
			pst.setString(1,tiposCocina.getTipoCocina() );
			pst.setInt(2, tiposCocina.getId());
			
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
	    	sql   = "DELETE FROM `tiposcocina` WHERE `id` = ?;";	    	
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
	private TiposCocina mapeo(ResultSet rs) throws SQLException{
		TiposCocina tiposCocina = new TiposCocina();
		tiposCocina.setId( rs.getInt("id"));
		tiposCocina.setTipoCocina(rs.getString("tipo_cocina"));
		return tiposCocina;
	}
	
	/**
	 * Mira si ya existe ese tipo de cocina
	 * @param tipoCocina
	 * @return -1 si no existe, su indice si ya existe
	 */
	public int existe(String tipoCocina){
		int resul = -1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null; 
		try {
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "SELECT `id` FROM `tiposcocina` WHERE LOWER(`tipo_cocina`) = ?;";			
	    	pst = con.prepareStatement(sql);
	    	pst.setString(1,tipoCocina.toLowerCase());
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