<%@page import="com.javireal.casa.recetas.bean.Mensaje"%>
<%@page import="com.javireal.casa.recetas.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.javireal.casa.recetas.bean.Elemento"%>
<%@page import="com.javireal.casa.recetas.bean.Receta"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@page errorPage="error.jsp"%>
<%@include file="includes/head.jsp" %>
<%@include file="includes/nav.jsp" %>

        <div id="page-wrapper">
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Recetas 
                            <a href="<%=Constantes.CONTROLLER_RECETAS%>?accion=<%=Constantes.ACCION_NUEVO%>" class="btn btn-outline btn-success"><i class="fa fa-plus"></i> Nueva</a>
                        </h1>
                    </div>
                </div>
                <!-- /.row -->
                
              
  
				
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
				                <th>Público</th>		         
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
 										<td><% if (receta.getPublico()==1){%>
		        			        			<span class="label label-success">Público</span>	
		                					<%}else{%>
		                						<span class="label label-warning">Privado</span>
		                					<%}%>
		                				</td>
			                            <td><a href="<%=Constantes.CONTROLLER_RECETAS%>?origen=<%=Constantes.ORIGEN_RECETAS%>&accion=<%=Constantes.ACCION_MODIFICAR%>&id=<%=receta.getId()%>" class="btn btn-outline btn-info"><i class="fa fa-pencil"></i> Modificar</a></td>									
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
								    			<a href="<%=Constantes.CONTROLLER_RECETAS%>?origen=<%=Constantes.ORIGEN_RECETAS%>&accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=receta.getId()%>" id ="boton_eliminar" class="btn btn-danger">Eliminar</a>
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
