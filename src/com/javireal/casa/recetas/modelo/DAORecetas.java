package com.javireal.casa.recetas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javireal.casa.recetas.bean.Categoria;
import com.javireal.casa.recetas.bean.Receta;
import com.javireal.casa.recetas.bean.TipoCocina;

public class DAORecetas implements Persistable<Receta>{
	
	@Override
	public int save(Receta o) {
		int resul=-1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rsKeys=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			Receta receta=o;
			sql = "INSERT INTO `recetas` (`nombre`, `id_categoria`, `id_tipo_cocina`, `tiempo`, `preparacion`, `fotografia`) VALUES (?,?,?,?,?,?);";
			//INSERT INTO `recetas` (`nombre`, `id_categoria`, `id_tipo_cocina`, `tiempo`, `preparacion`) VALUES ('Paella', 1, 2, 30, 'Cocer...');
			pst = con.prepareStatement(sql);
			pst.setString(1, receta.getNombre());
			pst.setInt(2, receta.getCategoria().getId());
			pst.setInt(3, receta.getTipoCocina().getId());
			pst.setString(4, receta.getTiempo());
			pst.setString(5, receta.getPreparacion());
			pst.setString(6, receta.getFotografia());
			System.out.println("Añadiendo receta: "+receta.toString());
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
	public Receta getById(int id) {
		String sql="";
		PreparedStatement pst=null;
		Receta resul = new Receta(id);
		ResultSet rs=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql = "SELECT r.id as id, r.nombre as nombre , tiempo, preparacion, id_categoria, c.nombre as categoria, id_tipo_cocina, tc.nombre as tipo_cocina, fotografia FROM recetas as r, categorias as c, tiposcocina as tc WHERE r.id_categoria = c.id and r.id_tipo_cocina = tc.id and r.id=? ORDER BY r.id;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery ();
	    	rs.first();
	    	resul=this.mapeo(rs);
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
	public ArrayList<Receta> getAll() {
		ArrayList<Receta> resul = new ArrayList<Receta>();
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			sql = "SELECT r.id as id, r.nombre as nombre , tiempo, preparacion, id_categoria, c.nombre as categoria, id_tipo_cocina, tc.nombre as tipo_cocina, fotografia FROM recetas as r, categorias as c, tiposcocina as tc WHERE r.id_categoria = c.id and r.id_tipo_cocina = tc.id ORDER BY r.id;";
			pst = con.prepareStatement(sql);
	    	rs = pst.executeQuery ();
	    	Receta receta = null;	    	
	    	while(rs.next()){
	    		receta = new Receta();
	    		receta.setId(rs.getInt("id"));
	    		receta.setNombre(rs.getString("nombre"));
	    		receta.setTiempo(rs.getString("tiempo"));
	    		receta.setPreparacion(rs.getString("preparacion"));
	    		
	    		Categoria categoria = new Categoria();
	    		categoria.setId(rs.getInt("id_categoria"));
	    		categoria.setNombre(rs.getString("categoria"));
	    		receta.setCategoria(categoria);
	    		
	    		TipoCocina tiposCocina = new TipoCocina();
	    		tiposCocina.setId(rs.getInt("id_tipo_cocina"));
	    		tiposCocina.setNombre(rs.getString("tipo_cocina"));
	    		receta.setTipoCocina(tiposCocina);

	    		resul.add(receta);
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
	public boolean update(Receta o) {
		boolean resul=false;
		Receta receta = o;
		String sql="";
		PreparedStatement pst=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql = "UPDATE `recetas` SET `nombre`=?, `id_categoria`=?, `id_tipo_cocina`=?, `tiempo`=?, `preparacion`=?, `fotografia`=? WHERE  `id`=?;";
			pst = con.prepareStatement(sql);
			pst.setString(1, receta.getNombre());
			pst.setInt(2,receta.getCategoria().getId());
			pst.setInt(3,receta.getTipoCocina().getId());
			pst.setString(4,receta.getTiempo());
			pst.setString(5,receta.getPreparacion());
			pst.setString(6,receta.getFotografia());
			pst.setInt(7, receta.getId());
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
	    	sql   = "DELETE FROM `recetas` WHERE `id` =?;";	    	
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
	 * Mapea un ResultSet a una Receta
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Receta mapeo(ResultSet rs) throws SQLException{
		Receta receta = new Receta();

		receta.setId(rs.getInt("id"));
		receta.setNombre(rs.getString("nombre"));
		receta.setTiempo(rs.getString("tiempo"));
		receta.setPreparacion(rs.getString("preparacion"));
		receta.setFotografia(rs.getString("fotografia"));
		
		Categoria categoria = new Categoria();
		categoria.setId(rs.getInt("id_categoria"));
		categoria.setNombre(rs.getString("categoria"));
		receta.setCategoria(categoria);
		
		TipoCocina tiposCocina = new TipoCocina();
		tiposCocina.setId(rs.getInt("id_tipo_cocina"));
		tiposCocina.setNombre(rs.getString("tipo_cocina"));
		receta.setTipoCocina(tiposCocina);
		
		//TODO completar todos los campos Ingredientes y cantidades
		return receta;
	}
	
	/**
	 * Mira si ya existe es< receta
	 * @param tipoCocina
	 * @return -1 si no existe, su indice si ya existe
	 */
	public int existe(String sReceta){
		int resul = -1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null; 
		try {
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "SELECT `id` FROM `recetas` WHERE LOWER(`nombre`) = ?;";
	    	pst = con.prepareStatement(sql);
	    	pst.setString(1, sReceta.toLowerCase());
	    	
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