<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@include file="head.jsp" %>
<%@include file="nav.jsp" %>

<%@include file="login.jsp" %>


	<div class="container">
	
		<section class="main row">
			<div class="row">
				<div class="col-xs-9 col-sm-9">
<!-- CARRUSEL -->				
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  <ol class="carousel-indicators">
					    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
						<li data-target="#carousel-example-generic" data-slide-to="3"></li>
						<li data-target="#carousel-example-generic" data-slide-to="4"></li>		    
					  </ol>
					
					  <!-- Wrapper for slides -->
					  <div class="carousel-inner" role="listbox">
					    <div class="item active">
					      <img src="img/recetas-slide-1.jpg" alt="...">
					    </div>
					    <div class="item">
					      <img src="img/recetas-slide-2.jpg" alt="...">
					    </div>
					    <div class="item">
					      <img src="img/recetas-slide-3.jpg" alt="...">
					    </div>
					    <div class="item">
					      <img src="img/recetas-slide-4.jpg" alt="...">
					    </div>
					    <div class="item">
					      <img src="img/recetas-slide-5.jpg" alt="...">
					    </div>
					  </div>
					
					  <!-- Controls -->
					  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    <span class="sr-only">Anterior</span>
					  </a>
					  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    <span class="sr-only">Siguiente</span>
					  </a>
					</div>
				</div> <!-- .col-xs-9 col-sm-6" -->

<!-- SOBRE ESTE ESPACIO -->
				<div class="col-xs-3 col-sm-3">
					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">Sobre este espacio</h3>
					  </div>
					  <div class="panel-body">
					    Esta página está dedicada a todas aquellas personas que disfrutan cocinando, compartiendo conocimientos culinarios o simplemente comiendo. 
					  </div>
					</div>
				</div>
			</div> <!-- .row -->
			
			<br>
			
			<div class="row">

<!-- FORM CONTACTO -->
				<div class="col-xs-6 col-sm-6">
					<div class="panel panel-default">
						<div class="panel-heading">
					    	<h3 class="panel-title">Contáctanos:</h3>
						</div>
						<div class="panel-body">
							<form>
							  <div class="form-group">
							    	<label for="nombre">Nombre</label>
							    	<input type="text" class="form-control" id="nombre" placeholder="Nombre">
							  </div>
							  <div class="form-group">
							    <label for="email">Correo electrónico</label>
							    <input type="email" class="form-control" id="email" placeholder="Email">
							  </div>
							  <div class="form-group">
							    	<label for="mensaje">Mensaje</label>
							    	<input type="text" class="form-control" id="mensaje" placeholder="Mensaje">
							  </div>
							  <button type="submit" class="btn btn-default">Enviar</button>
							</form>				
						</div>
					</div>
				</div>
				
<!--  NUBE DE INGREDIENTES -->				
				<div class="col-xs-3 col-sm-3">
					<div class="panel panel-default">
						<div class="panel-heading">
					    	<h3 class="panel-title">Ingredientes:</h3>
						</div>
						<div class="panel-body tags">
							<% 
								String color[]={"primary","success","warning","error","info","danger"};	
							    for(int i=0;i<ingredientesReceta.size();i++){ %>
								<a href="#" class="<%=color[i%6]%>"><%=ingredientesReceta.get(i).getNombre()%></a>
							<% } %>				
						</div>
					</div>					
				</div>
				
				
			</div>
		</section> <!-- .main row -->
		
	</div>	<!-- .container -->
	
<%@include file="foot.jsp" %>