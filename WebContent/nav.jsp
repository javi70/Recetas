<%@page import="com.javireal.casa.recetas.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="com.javireal.casa.recetas.bean.Ingrediente"%>
<%@page import="com.javireal.casa.recetas.bean.IngredientesReceta"%>
<%@page import="com.javireal.casa.recetas.bean.Receta"%>
<%@page import="com.javireal.casa.recetas.bean.Elemento"%>
<%@page import="com.javireal.casa.recetas.bean.TipoCocina"%>
<%@page import="java.util.ArrayList"%>

<body>

<header>

<nav class="navbar navbar-default navbar-fixed-top mi-navbar" role="navigation">
	<div class="container">
		  <!-- El logotipo y el icono que despliega el menú se agrupan
		       para mostrarlos mejor en los dispositivos móviles -->
		  <div class="navbar-header">
		    <button type="button" class="navbar-toggle" data-toggle="collapse"
		            data-target=".navbar-ex1-collapse">
		      <span class="sr-only">Desplegar navegación</span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </button>
		   <a class="navbar-brand" href="home"><img src="img/logo-chef.png" title="Logotipo"/>
		  </div>
<!-- 		  Las Javirecetas -->
<%
	ArrayList<Elemento> categorias=null;
	if(request.getAttribute("categorias")!=null){
		categorias = (ArrayList<Elemento>)request.getAttribute("categorias");
	}
	
	ArrayList<Elemento> tiposCocina=null;
	if(request.getAttribute("tiposCocina")!=null){
		tiposCocina = (ArrayList<Elemento>)request.getAttribute("tiposCocina");
	}
	
	ArrayList<Receta> recetas=null;
	if(request.getAttribute("recetas")!=null){
		recetas = (ArrayList<Receta>)request.getAttribute("recetas");
	}
	
	ArrayList<Ingrediente> ingredientesReceta=null;
	if(request.getAttribute("ingredientesReceta")!=null){
		ingredientesReceta = (ArrayList<Ingrediente>)request.getAttribute("ingredientesReceta");
	}
%>		 
		  <!-- Agrupar los enlaces de navegación, los formularios y cualquier
		       otro elemento que se pueda ocultar al minimizar la barra -->
		  <div class="collapse navbar-collapse navbar-ex1-collapse">
		    <ul class="nav navbar-nav">
		        <li class="dropdown">
       				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Recetas <span class="caret"></span></a>
       				<ul class="dropdown-menu">
      					<% for(int i=0;i<categorias.size();i++){ %>
 							<li><a href="<%=Constantes.CONTROLLER%>?idCategoria=<%=categorias.get(i).getId()%>"><%=categorias.get(i).getNombre()%></a></li>
 						<% } %>
     				</ul>
   				</li>		     
				<li class="dropdown">
       				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cocina <span class="caret"></span></a>
       				<ul class="dropdown-menu">
      					<% for(int i=0;i<tiposCocina.size();i++){ %>
 							<li><a href="<%=Constantes.CONTROLLER%>?idTipoCocina=<%=tiposCocina.get(i).getId()%>"><%=tiposCocina.get(i).getNombre()%></a></li>
 						<% } %>
     				</ul>
   				</li>		      
				<li class="dropdown">
       				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Gastronomía <span class="caret"></span></a>
       				<ul class="dropdown-menu">
						<li><a href="#">Curiosidades</a></li>
						<li><a href="#">Televisión</a></li>
						<li><a href="#">Enoturismo</a></li>
						<li><a href="#">Turismo gastronómico</a></li>
     				</ul>
   				</li>		      
		      <li><a href="#" data-toggle="modal" data-target="#loginModal">Login</a></li>  
		    </ul>
		    
		    
		    
			
					 
		    <form class="navbar-form navbar-right" role="search">
		      <div class="form-group">
		        <input type="text" class="form-control" placeholder="Buscar receta...">
		      </div>
		      <button type="submit" class="btn btn-default">Buscar</button>
		    </form>
		 
		  </div>
		
	</div>
</nav>	
</header>