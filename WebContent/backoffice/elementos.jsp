<%@page import="com.javireal.casa.recetas.bean.Mensaje"%>
<%@page import="com.javireal.casa.recetas.bean.Elemento"%>
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
                        <%
                        	String titulo="";
                        	if(request.getAttribute("titulo")!=null){
                        		titulo=request.getAttribute("titulo").toString();
                        		out.print(titulo);
                        	}
                        	String origen="";
                        	if(request.getAttribute("origen")!=null){
                        		origen=request.getAttribute("origen").toString();
                        	}
                        	
                        %>

                            <button type="button" class="btn btn-outline btn-success" data-toggle="modal" data-target="#myModalNuevo"><i class="fa fa-plus"></i> Nuevo</button>
                        </h1>
                    </div>
                </div>
                <!-- /.row -->
                <!-- Ventana Modal Nuevo -->
				  <form name="form_modificarElemento" action="<%=Constantes.CONTROLLER_ELEMENTOS%>" method="post">                
					<div class="modal fade col-md-6 col-md-offset-3" id="myModalNuevo" role="dialog">
						<div class="modal-dialog">
					  
					    <!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h2 class="modal-title text-center text-info"><i class="fa fa-fw fa-book"></i> Añadir elemento</h2>
					  			</div>
					  			<div class="modal-body">
						  				<input type="text" name="idElemento" value="-1" hidden=hidden></input>
						  				<input type="text" name="nombreElemento" placeholder="Escribe el nombre" value="" size="75"></input>										  											  			
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
            
		            <table id="tabla_elementos" class="tabla display" cellspacing="0" width="100%">
				        <thead>
				            <tr>
				            	<th>Orden</th>
				                <th>Nombre</th>
				                <th>Modificación</th>
				                <th>Eliminación</th>
				            </tr>
				        </thead>
				 		       		 
				        <tbody>
				            <%
					            
								ArrayList<Elemento> listaElementos = (ArrayList<Elemento>)request.getAttribute("elementos");
								for(int i=0;i<listaElementos.size();i++){
									Elemento elemento = listaElementos.get(i);
								%>
									<tr>
										<td><%=elemento.getId()%></td>
										<td><%=elemento.getNombre()%></td>
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
									    			<p>Estas seguro de que desea eliminar el elemento <%=elemento.getNombre()%></p>
									  			</div>
									  			<div class="modal-footer">						    			
									    			<a href="<%=Constantes.CONTROLLER_ELEMENTOS%>?accion=<%=Constantes.ACCION_ELIMINAR%>&origen=<%=origen%>&id=<%=elemento.getId()%>" id ="boton_eliminar" class="btn btn-danger">Eliminar</a>
									      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
									      		</div>
									    	</div> <!-- END Modal content-->
									  	</div>
									</div> <!-- END Ventana Modal -->

							<!-- Ventana Modal Modificar -->
									<div class="modal fade col-md-6 col-md-offset-3" id="myModalModificar<%=i%>" role="dialog">
										<div class="modal-dialog">
									  
									    <!-- Modal content-->
									      <form name="form_modificarElemento" action="<%=Constantes.CONTROLLER_ELEMENTOS%>" method="post">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h2 class="modal-title text-center text-danger"><i class="fa fa-exclamation-triangle"></i> Modificar elemento</h2>
									  			</div>
									  			<div class="modal-body">
									  				<input type="text" value="<%=elemento.getId()%>" disabled size="4"></input>
									  				<input type="text" name="idElemento" value="<%=elemento.getId()%>" hidden=hidden></input>
									  				<input type="text" name="nombreElemento" value="<%=elemento.getNombre()%>" size="65"></input>
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
