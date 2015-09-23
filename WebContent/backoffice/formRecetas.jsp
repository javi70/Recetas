<%@page import="com.javireal.casa.recetas.bean.TiposCocina"%>
<%@page import="com.javireal.casa.recetas.bean.Categorias"%>
<%@page import="com.javireal.casa.recetas.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.javireal.casa.recetas.bean.Ingredientes"%>
<%@page import="com.javireal.casa.recetas.bean.Receta"%>
<%@page contentType="text/html; charset=UTF-8" %>
<style>
.fotografia{
	position:absolute;
	width:100px;
	height:auto;
}
.boton_fotografia{
	padding-left:0;
}
</style>
<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

        <div id="page-wrapper">
            <div class="container-fluid">
	            <%
	            ArrayList<Categorias> listaCategorias = null;
	            if(request.getAttribute("categorias")!=null){
					listaCategorias = (ArrayList<Categorias>)request.getAttribute("categorias");
	            }
	            ArrayList<Ingredientes> listaIngredientes= null;
	            if(request.getAttribute("ingredientes")!=null){
            		listaIngredientes= (ArrayList<Ingredientes>)request.getAttribute("ingredientes");
	            }
	            ArrayList<TiposCocina> listaTiposCocina= null;
	            if(request.getAttribute("tiposCocina")!=null){
            		listaTiposCocina= (ArrayList<TiposCocina>)request.getAttribute("tiposCocina");
	            }
            	int accion=-1;
            	if(request.getAttribute("accionFormulario")!=null){
           			accion=(Integer)request.getAttribute("accionFormulario");
            	}
				Receta receta = null;
				if(request.getAttribute("receta")!=null){
					receta = (Receta)request.getAttribute("receta");
				}
				%>
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                        	<%
                        	if(accion==Constantes.ACCION_NUEVO){
                        		out.print("Nueva receta");
                        	}else{
                        		out.print("Editar receta");
                        	}
                        	%>
                        </h1>
                    </div>
                </div>



<!--                  
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-info alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="fa fa-info-circle"></i>
  
                        </div>
                    </div>
                </div>
-->
				
				  <form name="form_nuevaReceta" action="<%=Constantes.CONTROLLER%>" method="post" enctype="multipart/form-data" class="form-horizontal">                
					<div class="form-group col-lg-12 col-md-6">
		  				<input type="text" name="idReceta" value="<%
		  				if(accion==Constantes.ACCION_NUEVO){
		  					out.println("-1");
		  				}else{
		  					out.println(receta.getId());
		  				}
		  				%>" hidden=hidden></input>
		  				<label for="nombreReceta" class="col-lg-2">Nombre: </label>
		  				<input type="text" name="nombreReceta" placeholder="Escribe el nombre de la receta" value="<%=(receta!=null)?receta.getNombre():""%>" size="25" class="col-lg-9"></input>
					</div>
					
					<div class="form-group col-lg-12 col-md-6">
						<label for="fotografia" class="col-lg-2">Fotografía:</label>
						<input type="file" name="fotografia" class="col-lg-6 boton_fotografia" id="fotografiaFile"></input>
		  				<%
		  					String img_path = Constantes.IMG_DEFAULT_RECETA;
		  					if(accion==Constantes.ACCION_NUEVO){
		  						//receta nueva, imagen por defecto
		  						img_path="../img/" +  Constantes.IMG_DEFAULT_RECETA;
		  					}else{
				      			if ( !img_path.equals( receta.getFotografia())){
				      				//receta existente CON imagen, poner su imagen
				      				img_path = Constantes.IMG_WEB_PATH + receta.getFotografia();
				      			}else{
				      				//receta existente SIN imagen, poner imagen por defecto
				      				img_path = "../img/" +  Constantes.IMG_DEFAULT_RECETA;
				      			}	
		  					}
		           			
		           		%>	
		           		<img src="<%=img_path%>"
		           			alt="Imagen de la receta <%=receta.getNombre()%>"
	    	       			class="img-responsive img-thumbnail fotografia"
	    	       			id="imgReceta">
					</div>
					
		  			<div class="form-group col-lg-12 col-md-6">						  				
						<label for="categoria" class="col-lg-2">Categoría:</label>
						<select name="categoria" class="col-lg-3">
							<%
								for(int i=0;i<listaCategorias.size();i++){
					 				if((accion==Constantes.ACCION_MODIFICAR)&&(receta.getCategoria().getId()==listaCategorias.get(i).getId())){
					 					out.print("<option selected value='"+listaCategorias.get(i).getId()+"'>"+listaCategorias.get(i).getCategoria()+"</option>");
					 				}else{
										out.print("<option value='"+listaCategorias.get(i).getId()+"'>"+listaCategorias.get(i).getCategoria()+"</option>");
					 				}
					 			}
						 	%>
						</select>

					
					</div>
					
					<div class="form-group col-lg-12 col-md-6">
						<label for="tipoCocina" class="col-lg-2">Tipo de cocina:</label>
						<select name="tipoCocina" class="col-lg-3">
							<%
								for(int i=0;i<listaTiposCocina.size();i++){
					 				if((accion==Constantes.ACCION_MODIFICAR)&&(receta.getTipoCocina().getId()==listaTiposCocina.get(i).getId())){
					 					out.print("<option selected value='"+listaTiposCocina.get(i).getId()+"'>"+listaTiposCocina.get(i).getTipoCocina()+"</option>");
					 				}else{
					 					out.print("<option value='"+listaTiposCocina.get(i).getId()+"'>"+listaTiposCocina.get(i).getTipoCocina()+"</option>");	
					 				}
						 		}
						 	%>	
						</select>	
					</div>
		  			<div class="form-group col-lg-12 col-md-6">
		  				<label for="tiempo" class="col-lg-2">Tiempo:</label>
						<input type="number" placeholder="Minutos de preparacion" name="tiempo" class="col-lg-3" value="<%=(receta!=null)?receta.getTiempo():""%>"></input>		  									  				
					</div>
					
		  			<div class="form-group col-lg-12 col-md-6">
		  				<label for="listaIngredientes"  class="col-lg-2">Ingredientes:</label>
		                <select id="listaTodosLosIngredientes" name="listaTodosLosIngredientes" size="10"  class="col-lg-3">	
							<%
								for(int i=0;i<listaIngredientes.size();i++){
							%>
									<option value="<%=listaIngredientes.get(i).getId()%>"><%=listaIngredientes.get(i).getIngrediente()%></option>
						 	<%
						 		}
						 	%>					                					                	
		             	</select>
		             	<div  class="col-lg-3">
		             		<div class="row">
			             		<input type="button" name="agregarIngredientes" id="agregarIngredientes" value="Añadir >>" style="margin-left:30%; margin-bottom:30%;margin-top:10%" data-toggle="modal" data-target="#myModalCantidadIngrediente"></input>
			             	</div>
			             	<div class="row">
								<input type="button" name="eliminarIngredientes" id="eliminarIngredientes" value="<< Borrar" style="margin-left:30%"></input>
							</div>
						</div>
			            <select id="listaIngredientes" name="listaIngredientes" multiple size="10"  class="col-lg-3" multiple></select>
		          	</div>
		  			<div class="form-group col-lg-12 col-md-6">						  				
						<label for="preparacion">Preparación:</label> <input type="button" id="boton_zoom" value="Zoom" style="margin-left:20px" data-toggle="modal" data-target="#myModalNuevoZoom"/>
						<textarea class="form-control" id="preparacion" name="preparacion" placeholder="Preparación de la receta"><%=(receta!=null)?receta.getPreparacion():""%></textarea>	
					</div>
				</div>
	  			<div class="modal-footer">
	  				<button type="button" id="boton_guardar" name="boton_guardar" class="btn btn-danger">Guardar</button> 
	  				<!-- <a href="javascript: seleccionarListaIngredientes()" id="boton_guardar" name="boton_guardar" class="btn btn-danger">Guardar</a>-->
	      			<a href="<%=Constantes.CONTROLLER%>?origen=<%=Constantes.ORIGEN_RECETAS%>&accion=<%=Constantes.ACCION_LISTAR%>" class="btn btn-default">Cancelar</a>
	      		</div>
	    	</div> 
				  
				<!-- Ventana Modal cantidadIngrediente -->
				<div class="modal fade col-md-12" id="myModalCantidadIngrediente" role="dialog">
				  <div class="modal-dialog modal-lg">
				  
				  <!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h2 class="modal-title text-center text-info"><i class="fa fa-fw fa-cutlery"></i> Cantidad del ingrediente </h2>
			  			</div>
			  			<div class="modal-body col-lg-12 col-md-6">
			  				<label for="txtCantidadIngrediente">Cantidad del ingrediente:</label>
			  				<input type="text" name="txtCantidadIngrediente" id="txtCantidadIngrediente" placeholder="Escribe la cantidad"/>	
			  			</div>
						
						<div class="modal-footer">
					  		<button type="button" id="boton_volver_cantidad" name="boton_volver_cantidad" class="btn btn-danger" data-dismiss="modal">Volver</button>								    			
					     </div>
					  </div> <!-- END Modal content-->
					  	</div>
				  </div> <!-- END Ventana Modal cantidadIngrediente -->			  			
				
				  
				<!-- Ventana Modal preparacionZoom -->
				  
				<div class="modal fade col-md-12" id="myModalNuevoZoom" role="dialog">
				  <div class="modal-dialog modal-lg">
				  
				  <!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h2 class="modal-title text-center text-info"><i class="fa fa-fw fa-cutlery"></i> Preparación de la receta</h2>
			  			</div>
			  			<div class="modal-body  col-lg-12 col-md-6">
							<textarea class="form-control" id="preparacionZoom" name="preparacionZoom" placeholder="Preparación de la receta" rows="50"></textarea>	
			  			</div>
						
						<div class="modal-footer">
					  		<button type="button" id="boton_volver" name="boton_volver" class="btn btn-danger" data-dismiss="modal">Volver</button>								    			
					     </div>
					  </div> <!-- END Modal content-->
					  	</div>
				  </div> <!-- END Ventana Modal preparacionZoom -->			  			
				  
				</form>
				
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

<jsp:include page="includes/foot.jsp"></jsp:include>

<script>
		var btn_agregar = document.getElementById('boton_volver_cantidad');
		btn_agregar.onclick=function(){
			$("#listaTodosLosIngredientes option:selected").each(function() {
				cantidad = $('#txtCantidadIngrediente').val();
				valor = $('#listaTodosLosIngredientes').val();
				texto = $("#listaTodosLosIngredientes option:selected").html();;
				$('#listaIngredientes').append('<option value="' + valor + '">' + cantidad + " de " + texto + '</option>');
			})			
		};

		
		var btn_eliminar = document.getElementById('eliminarIngredientes');
		btn_eliminar.onclick=function(){
			$("#listaIngredientes option:selected").each(function() {
				$("#listaIngredientes option:selected").remove();
			})			
		};
		
		var btn_zoom = document.getElementById('boton_zoom');
		btn_zoom.onclick=function(){
//			console.log($("#preparacion").val());
			tinymce.get('preparacionZoom').setContent($("#preparacion").val());
		};
		
		var btn_volver = document.getElementById('boton_volver');
		btn_volver.onclick=function(){
//			console.log(tinymce.get('preparacionZoom').getContent());			
			$("#preparacion").val(tinymce.get('preparacionZoom').getContent());
		};
		
	    //Seleccionar todo el select de ingredientes de las recetas
	    //function seleccionarListaIngredientes(){
		var btn_submit = document.getElementById('boton_guardar');
		btn_submit.onclick=function(){	  
//			console.debug("En javascript");
//			console.debug("Cuantos: "+document.getElementById("listaIngredientes").length);
			elem=document.getElementById("listaIngredientes").options;
		    for(i=0;i<elem.length;i++) elem[i].selected=true;
		    document.form_nuevaReceta.submit();
	    };//End: seleccionarListaIngredientes
</script>
