<%@page import="com.javireal.casa.recetas.bean.TiposCocina"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.javireal.casa.recetas.Constantes"%>
<%@page contentType="text/html; charset=UTF-8" %>


<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>


        <div id="page-wrapper">
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Tipos de Cocina 
                            <button type="button" class="btn btn-outline btn-success" data-toggle="modal" data-target="#myModalNuevo"><i class="fa fa-plus"></i> Nuevo</button>
                        </h1>
                    </div>
                </div>
                <!-- /.row -->
                <!-- Ventana Modal Nuevo -->
				  <form name="form_modificarTipoCocina" action="<%=Constantes.CONTROLLER%>" method="post">                
					<div class="modal fade col-md-6 col-md-offset-3" id="myModalNuevo" role="dialog">
						<div class="modal-dialog">
					  
					    <!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h2 class="modal-title text-center text-info"><i class="fa fa-fw fa-file-text"></i> Añadir tipo de cocina</h2>
					  			</div>
					  			<div class="modal-body">
					  					<input type="text" name="origen" value="3" hidden=hidden></input>
						  				<input type="text" name="idTipoCocina" value="-1" hidden=hidden></input>
						  				<input type="text" name="nombreTipoCocina" placeholder="Escribe el nombre del tipo de cocina" value="" size="75"></input>										  											  			
					  			</div>
					  			<div class="modal-footer">
					  				<button type="submit" name="boton_guardar" class="btn btn-danger">Guardar</button>								    			
					      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					      		</div>
					    	</div> <!-- END Modal content-->
					  	</div>
				  </div> <!-- END Ventana Modal -->
				</form>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-info alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="fa fa-info-circle"></i>
                            	<% if(request.getAttribute("msg")!=null)
                            			out.print(request.getAttribute("msg"));
                            	%>                           
                        </div>
                    </div>
                </div>
                
                <!-- /.row -->

                <div class="row">
                    
				 	<!--  tabla -->
            
		            <table id="tabla_tiposCocina" class="tabla display" cellspacing="0" width="100%">
				        <thead>
				            <tr>
				            	<th>Orden</th>
				                <th>Tipo de Cocina</th>
				                <th>Modificación</th>
				                <th>Eliminación</th>
				            </tr>
				        </thead>
				 		       		 
				        <tbody>
				            <%
								ArrayList<TiposCocina> listaTiposCocina = (ArrayList<TiposCocina>)request.getAttribute("tiposCocina");
								for(int i=0;i<listaTiposCocina.size();i++){
									TiposCocina tc = null;
									tc = listaTiposCocina.get(i);
								%>
									<tr>
										<td><%=tc.getId()%></td>
										<td><%=tc.getTipoCocina()%></td>
										<td><button type="button" class="btn btn-outline btn-info" data-toggle="modal" data-target="#myModalModificar<%=i%>"><i class="fa fa-pencil"></i> Modificar</button></td>									
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
									    			<p>Estas seguro de que desea eliminar el tipo de cocina <%=tc.getTipoCocina()%></p>
									  			</div>
									  			<div class="modal-footer">						    			
									    			<a href="<%=Constantes.CONTROLLER%>?origen=<%=Constantes.ORIGEN_TIPOSCOCINA%>&accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=tc.getId()%>" id ="boton_eliminar" class="btn btn-danger">Eliminar</a>
									      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
									      		</div>
									    	</div> <!-- END Modal content-->
									  	</div>
									</div> <!-- END Ventana Modal -->

							<!-- Ventana Modal Modificar -->
									<div class="modal fade col-md-6 col-md-offset-3" id="myModalModificar<%=i%>" role="dialog">
										<div class="modal-dialog">
									  
									    <!-- Modal content-->
									      <form name="form_modificarTipoCocina" action="<%=Constantes.CONTROLLER%>" method="post">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h2 class="modal-title text-center text-danger"><i class="fa fa-exclamation-triangle"></i> Modificar tipo de cocina</h2>
									  			</div>
									  			<div class="modal-body">
									  				<input type="text" name="origen" value="<%=Constantes.ORIGEN_TIPOSCOCINA%>" hidden=hidden></input>
									  				<input type="text" value="<%=tc.getId()%>" disabled size="4"></input>
									  				<input type="text" name="idTipoCocina" value="<%=tc.getId()%>" hidden></input>
									  				<input type="text" name="nombreTipoCocina" value="<%=tc.getTipoCocina()%>" size="65"></input>
									  			</div>
									  			<div class="modal-footer">	
									  				<button type="submit" name="boton_modificar" class="btn btn-danger">Modificar</button>					    			
									      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
									      		</div>
									    	</div> <!-- END Modal content-->
									      </form>
									  	</div>
									</div> <!-- END Ventana Modal -->
					
					
								<%} // end for  %>
				       </tbody>
				       
				    </table>     
	

            
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

<jsp:include page="includes/foot.jsp"></jsp:include>
