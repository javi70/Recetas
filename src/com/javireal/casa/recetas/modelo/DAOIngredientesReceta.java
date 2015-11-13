package com.javireal.casa.recetas.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javireal.casa.recetas.bean.Ingrediente;
import com.javireal.casa.recetas.bean.IngredientesReceta;

public class DAOIngredientesReceta implements Persistable<IngredientesReceta>{
	/**
	 * Guarda el objeto IngredientesReceta en la tabla
	 * @param ir: objeto a guardar
	 * @return int: -1 si error, 0 en caso contrario
	 */
	@Override
	public int save(IngredientesReceta ir) {
		int resul=-1;
		String sql="";
		PreparedStatement pst=null;
		ResultSet rsKeys=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			//IngredientesReceta ir=(IngredientesReceta)o;
			sql = "INSERT INTO `ingredientesreceta` (`id_receta`, `id_ingrediente`, `cantidad`) VALUES (?,?,?);";
			//INSERT INTO `recetas` (`nombre`, `id_categoria`, `id_tipo_cocina`, `tiempo`, `preparacion`) VALUES ('Paella', 1, 2, 30, 'Cocer...');
			pst = con.prepareStatement(sql);
			pst.setInt(1, ir.getIdReceta());
			pst.setInt(2, ir.getIdIngrediente());
			pst.setString(3, ir.getCantidad());
			System.out.println("Añadiendo ingredientes receta: "+ir.toString());
			if(pst.executeUpdate()!=1){
					throw new SQLException("No se ha podido insertar el ingrediente");
			} else{
				resul=0;
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
	public IngredientesReceta getById(int id) {
		return null;
	}
	
/**
 * Obtiene los ingredientes de la receta cuyo id se pasa como argumento
 * @param id
 * @return ArrayList<IngredientesReceta>
 */

	public ArrayList<IngredientesReceta> getByRecetaId(int id) {
		ArrayList<IngredientesReceta> resul = new ArrayList<IngredientesReceta>();
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	//sql = "SELECT id_receta, id_ingrediente, cantidad FROM ingredientesreceta WHERE id_receta=? ORDER BY id_receta, id_ingrediente;";
			sql = "SELECT id_receta, id_ingrediente, cantidad, nombre as nombre_ingrediente FROM ingredientesreceta, ingredientes  WHERE id_ingrediente = ingredientes.id and id_receta=? ORDER BY id_receta, id_ingrediente;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery ();    	
	    	while(rs.next()){
	    		resul.add(new IngredientesReceta(rs.getInt("id_receta"),rs.getInt("id_ingrediente"),rs.getString("nombre_ingrediente"),rs.getString("cantidad")));	    		
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
	public ArrayList<IngredientesReceta> getAll() { return null; }
	

	@Override
	public boolean update(IngredientesReceta ir) {
		boolean resul=false;
		String sql="";
		PreparedStatement pst=null;
		try{
			Connection con = DataBaseHelper.getConnection();
	    	sql = "UPDATE `ingredientesreceta` SET `id_receta`=?, `id_ingrediente`=?, `cantidad`=? WHERE  `id_receta`=?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1,ir.getIdReceta());
			pst.setInt(2,ir.getIdIngrediente());
			pst.setString(3,ir.getCantidad());
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

	/**
	 * Elimina los registros cuyo id_receta == id
	 * Devuelve true si todo OK false en caso contrario
	 * @param id: identificador de la receta
	 * @return boolean
	 */
	@Override
	public boolean delete(int idReceta) {
		boolean resul=false;
		String sql="";
		PreparedStatement pst=null;	
		try {
			Connection con = DataBaseHelper.getConnection();
	    	sql   = "DELETE FROM `ingredientesreceta` WHERE `id_receta` =?;";	    	
	    	pst = con.prepareStatement(sql);
	    	pst.setInt(1, idReceta);

	    	//ejecutar delete
	    	if ( pst.executeUpdate() != 1){	    		
	    		//throw new Exception("No se ha realizado eliminacion: " + sql);
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
		
	public ArrayList<Ingrediente> getAllPublicosEnRecetas() {
		ArrayList<Ingrediente> resul = new ArrayList<Ingrediente>();
		String sql="";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Connection con = DataBaseHelper.getConnection();
			sql="select distinct(ingredientes.id), ingredientes.nombre from ingredientesreceta, ingredientes where ingredientes.publico=1 and ingredientes.id=ingredientesreceta.id_ingrediente";
			pst = con.prepareStatement(sql);
	    	rs = pst.executeQuery ();
	    	Ingrediente elemento= null;	    	
	    	while(rs.next()){
	    		elemento = new Ingrediente();
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
}