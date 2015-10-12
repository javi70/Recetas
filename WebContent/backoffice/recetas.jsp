<%@page import="com.javireal.casa.recetas.bean.Mensaje"%>
<%@page import="com.javireal.casa.recetas.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.javireal.casa.recetas.bean.Elemento"%>
<%@page import="com.javireal.casa.recetas.bean.Receta"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@page errorPage="error.jsp"%>

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

        <div id="page-wrapper">
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Recetas 
                            <a href="<%=Constantes.CONTROLLER%>?origen=<%=Constantes.ORIGEN_RECETAS%>&accion=<%=Constantes.ACCION_NUEVO%>" class="btn btn-outline btn-success"><i class="fa fa-plus"></i> Nueva</a>
                        </h1>
                    </div>
                </div>
                <!-- /.row -->
                
              
           <!-- Ventana Modal Nueva Receta -->
				  <form name="form_nuevaReceta" action="<%=Constantes.CONTROLLER%>" method="post">                
<!-- 					<div class="modal fade col-md-6 col-md-offset-3" id="myModalNuevo" role="dialog">  -->
 					<div class="modal fade col-md-12" id="myModalNuevo" role="dialog">
						<div class="modal-dialog modal-lg">
					  
					    <!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h2 class="modal-title text-center text-info"><i class="fa fa-fw fa-cutlery"></i> Añadir receta</h2>
					  			</div>
					  			<div class="modal-body  col-lg-12 col-md-6">
					  			
									<div class="form-group col-lg-12 col-md-6">
						  				<input type="text" name="idReceta" value="-1" hidden=hidden></input>
						  				
						  				<label for="nombreReceta" class="col-lg-2">Nombre: </label>
						  				<input type="text" name="nombreReceta" placeholder="Escribe el nombre de la receta" value="" size="50" class="col-lg-9"></input>

									</div>

						  			<div class="form-group col-lg-12 col-md-6">						  				
										<label for="categoria" class="col-lg-2">Categoría:</label>
										<select name="categoria" class="col-lg-3">
											<option value="" selected></option>
													<option value="">ID DE CATEGORIA</option>

										</select>
											
										<label for="tipoDeCocina" class="col-lg-2">Tipo de cocina:</label>
										<select name="tipoDeCocina" class="col-lg-3">
											<option value="" selected></option>
													<option value="">ID DE TIPOCOCINA</option>
										
										</select>	
									</div>
									
						  			<div class="form-group col-lg-12 col-md-6">
						  				<label for="tiempo" class="col-lg-2">Tiempo:</label>
										<input type="number" placeholder="Minutos de preparacion" name="tiempo" class="col-lg-3"></input>	
						  									  				
										<label for="fotografia" class="col-lg-2">Fotografía</label>
										<input type="button" name="fotografia" value="Selecciona la fotografía" class="col-lg-3"></input>	
									</div>
									
															  			
						  			<div class="form-group col-lg-12 col-md-6">
						  				<label for="listaIngredientes"  class="col-lg-2">Ingredientes:</label>
						                <select id="listaTodosLosIngredientes" name="listaTodosLosIngredientes" size="10"  class="col-lg-3">						                	
													<option value="">ID DE INGREDIENTE</option>
	
						             	</select>
						             	<div  class="col-lg-3">
						             		<div class="row">
							             		<input type="button" name="agregarIngredientes" id="agregarIngredientes" value="Añadir >>" style="margin-left:30%; margin-bottom:30%;margin-top:10%" data-toggle="modal" data-target="#myModalCantidadIngrediente"></input>
							             	</div>
							             	<div class="row">
												<input type="button" name="eliminarIngredientes" id="eliminarIngredientes" value="<< Borrar" style="margin-left:30%"></input>
											</div>
										</div>
 						                <select id="listaIngredientes" name="listaIngredientes" multiple size="10"  class="col-lg-3"></select>
						          	</div>
						          							  				
						  			<div class="form-group col-lg-12 col-md-6">						  				
										<label for="preparacion">Preparación:</label> <input type="button" id="boton_zoom" value="Zoom" style="margin-left:20px" data-toggle="modal" data-target="#myModalNuevoZoom"/>
										<textarea class="form-control" id="preparacion" name="preparacion" placeholder="Preparación de la receta"></textarea>	
									</div>
						  			
					  			</div>
					  			<div class="modal-footer">
					  				<button type="submit" name="boton_guardar" class="btn btn-danger">Guardar</button>								    			
					      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					      		</div>
					    	</div> <!-- END Modal content-->
					  	</div>
				  </div> <!-- END Ventana Modal Nueva Receta -->
				  
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
				
      <div class="row">
        <% 
        if(request.getAttribute("msg")!=null){
            Mensaje msg = (Mensaje)request.getAttribute("msg");	
			if (msg != null){
				out.print("<div class='alert alert-"+ msg.getTipo() +" alert-dismissible' role='alert'>");
					out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
						out.print("<span aria-hidden='true'>&times;</span>");
					out.print("</button>");
					out.print("<strong>"+ msg.getTexto() +"</strong>");
				out.print("</div>");
			} 
        }
		%>
		
	</div> <!-- /.row -->
                
                <!-- /.row -->

                <div class="row">
                    
				 	<!--  tabla -->
            
		            <table id="tabla_recetas" class="tabla display" cellspacing="0" width="100%">
				        <thead>
				            <tr>
				                <th>Codigo</th>
				                <th>Nombre</th>
				                <th>Categoría</th>
				                <th>Tipo</th>		         
				                <th>Modificación</th>
				                <th>Eliminación</th>       
				            </tr>
				        </thead>
				 		       		 
				        <tbody>
				            <%
								ArrayList<Receta> listaRecetas = (ArrayList<Receta>)request.getAttribute("recetas");
								for(int i=0;i<listaRecetas.size();i++){
									Receta receta = listaRecetas.get(i);
							%>
								<tr>
										<td><%=receta.getId()%></td>
										<td><%=receta.getNombre()%></td>
										<td><%=receta.getCategoria().getNombre()%></td>
										<td><%=receta.getTipoCocina().getNombre()%></td>
<!-- 										<td><button type="button" class="btn btn-outline btn-info" data-toggle="modal" data-target="#myModalModificar<%=i%>"><i class="fa fa-pencil"></i> Modificar</button></td>
 -->
			                            <td><a href="<%=Constantes.CONTROLLER%>?origen=<%=Constantes.ORIGEN_RECETAS%>&accion=<%=Constantes.ACCION_MODIFICAR%>&id=<%=receta.getId()%>" class="btn btn-outline btn-info"><i class="fa fa-pencil"></i> Modificar</a></td>									
										<td><button type="button" class="btn btn-outline btn-danger" data-toggle="modal" data-target="#myModalEliminar<%=i%>"><i class="fa fa-minus"></i> Eliminar</button></td>										
								</tr>
								
								<!-- Ventana Modal Eliminar -->
								<div class="modal fade col-md-6 col-md-offset-3" id="myModalEliminar<%=i%>" role="dialog">
									<div class="modal-dialog">
								  
								    <!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h2 class="modal-title text-center text-danger"><i class="fa fa-exclamation-triangle"></i> Confirmar eliminación</h2>
								  			</div>
								  			<div class="modal-body">
								    			<p>Estas seguro de que desea eliminar la receta <%=receta.getNombre()%></p>
								  			</div>
								  			<div class="modal-footer">						    			
								    			<a href="<%=Constantes.CONTROLLER%>?origen=<%=Constantes.ORIGEN_RECETAS%>&accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=receta.getId()%>" id ="boton_eliminar" class="btn btn-danger">Eliminar</a>
								      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
								      		</div>
								    	</div> <!-- END Modal content-->
								  	</div>
								</div> <!-- END Ventana Modal -->								
								<% } %>
				       </tbody>
				       
				    </table>     

            
                </div>

                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

<jsp:include page="includes/foot.jsp"></jsp:include>
