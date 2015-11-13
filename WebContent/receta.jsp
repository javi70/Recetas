<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@include file="head.jsp" %>
<%@include file="nav.jsp" %>
<%@include file="login.jsp" %>
<%
	Receta receta = null;
	if (request.getAttribute("receta")!=null){
		receta = (Receta)request.getAttribute("receta");
	}	
%>
	<section class="container fondo-cuaderno">
		<header>
			<div class="receta-titulo">
				<h1><%=receta.getNombre()%></h1>
			</div>
		</header>
		
		<div class="row">
		
			<div class="receta-imagen">
				<img src="<%=Constantes.IMG_WEB_PATH%><%=receta.getFotografia()%>" alt="Fotografia de <%=receta.getNombre()%>">
			</div>

			<div class="receta-varios">
				<h2>Información:</h2>
				<% if(!"".equals(receta.getTiempo())){ %>
					<p>Tiempo de preparación: <%=receta.getTiempo()%></p>
				<% } %>
				<% if(!"".equals(receta.getTipoCocina().getNombre())){ %>
					<p>Tipo de cocina: <%=receta.getTipoCocina().getNombre()%></p>
				<% } %>
				<% if(!"".equals(receta.getCategoria().getNombre())){ %>	
					<p>Tipo de plato: <%=receta.getCategoria().getNombre()%></p>
				<% } %>	
			</div>
			<div class="receta-ingredientes">
 		        <h2>Ingredientes:</h2>		       
		       <%  
		       if (receta.getIngredientes()!=null){
		           IngredientesReceta ingre = null;
		       	for(int j=0; j<receta.getIngredientes().size();j++){ 
		       		ingre = receta.getIngredientes().get(j);	
		       %>
		       	<span class="lista-ingredientes"><%=ingre.getCantidad()%> de <%=ingre.getNombreIngrediente()%></span><br> 
		       <% } 
		       } %>
			</div>			
			
	      <div class="receta-preparacion">
		       <h2>Preparación:</h2>
		       <div class="texto-preparacion">
		       		<%=receta.getPreparacion()%>
		       </div>
		  </div>

      	</div>

		<footer>
		   	<button type="button" class="btn btn-success" onclick="history.back()">Volver</button>
		    <button type="button" class="btn btn-primary">Imprimir</button>
			<button type="button" class="btn btn-warning">Compartir</button>
		</footer>
	</section>	<!-- .container -->
	

	
<%@include file="foot.jsp" %>