<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@include file="head.jsp" %>
<%@include file="nav.jsp" %>
<%@include file="login.jsp" %>
<%
	ArrayList<Receta> listaRecetas = null;
	if(request.getAttribute("listaRecetas")!=null){
		listaRecetas = (ArrayList<Receta>)request.getAttribute("listaRecetas");	
	}

%>

</script>
	<div class="container">
	
		<div class="container">
			<div class="row">
				<% 
					Receta receta = null;
					if(listaRecetas.size()==0){ %>
						<div class="jumbotron text-center">
  							<h2>Atención!</h2>
  							<p>No hay recetas para mostrar</p>
						</div>
					
					<% }
					for(int i=0; i<listaRecetas.size();i++){ 
						receta = listaRecetas.get(i); 
				%>			  
						
						<a href="<%=Constantes.CONTROLLER_DETALLE%>?id=<%=receta.getId()%>" class="btn btn-primary">
						    <div class="thumbnail">
						      <img src="<%=Constantes.IMG_WEB_PATH%><%=receta.getFotografia()%>" alt="Fotografia de <%=receta.getNombre()%>">
						      <div class="caption">
						        <h4><%=receta.getNombre()%></h4>
						      </div>
						    </div>
					    </a>
		
				<%} %>
			</div>
		</div> <!-- .main row -->
		
	</div>	<!-- .container -->
	

	
<%@include file="foot.jsp" %>

