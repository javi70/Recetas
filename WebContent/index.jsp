<!doctype html>

<html lang="es">

<head>

  <base href="<%=request.getContextPath()%>/">
  
  <meta charset="utf-8">

  <title>Las Recetas de Javi</title>

  <!-- ViewPort imprescindible para RWD -->	
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />	

  <!--[if lt IE 9]>
  	  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	  <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
  <![endif]-->

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

	<!-- Google Fonts: Indie Flower -->
	<link href='http://fonts.googleapis.com/css?family=Indie+Flower' rel='stylesheet' type='text/css'>
</head>
<style>
	body{font-family: 'Indie Flower', cursive;}
	header, footer{background-color:#306FFC;color:white;}
	header{
		/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#1e5799+0,2989d8+12,2989d8+25,2989d8+38,2989d8+48,2989d8+61,2989d8+71,2989d8+81,207cca+89,7db9e8+100 */
		background: #1e5799; /* Old browsers */
		/* IE9 SVG, needs conditional override of 'filter' to 'none' */
		background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIxMDAlIiB5Mj0iMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzFlNTc5OSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEyJSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjI1JSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjM4JSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjQ4JSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjYxJSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjcxJSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjgxJSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9Ijg5JSIgc3RvcC1jb2xvcj0iIzIwN2NjYSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiM3ZGI5ZTgiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
		background: -moz-linear-gradient(left,  #1e5799 0%, #2989d8 12%, #2989d8 25%, #2989d8 38%, #2989d8 48%, #2989d8 61%, #2989d8 71%, #2989d8 81%, #207cca 89%, #7db9e8 100%); /* FF3.6+ */
		background: -webkit-gradient(linear, left top, right top, color-stop(0%,#1e5799), color-stop(12%,#2989d8), color-stop(25%,#2989d8), color-stop(38%,#2989d8), color-stop(48%,#2989d8), color-stop(61%,#2989d8), color-stop(71%,#2989d8), color-stop(81%,#2989d8), color-stop(89%,#207cca), color-stop(100%,#7db9e8)); /* Chrome,Safari4+ */
		background: -webkit-linear-gradient(left,  #1e5799 0%,#2989d8 12%,#2989d8 25%,#2989d8 38%,#2989d8 48%,#2989d8 61%,#2989d8 71%,#2989d8 81%,#207cca 89%,#7db9e8 100%); /* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(left,  #1e5799 0%,#2989d8 12%,#2989d8 25%,#2989d8 38%,#2989d8 48%,#2989d8 61%,#2989d8 71%,#2989d8 81%,#207cca 89%,#7db9e8 100%); /* Opera 11.10+ */
		background: -ms-linear-gradient(left,  #1e5799 0%,#2989d8 12%,#2989d8 25%,#2989d8 38%,#2989d8 48%,#2989d8 61%,#2989d8 71%,#2989d8 81%,#207cca 89%,#7db9e8 100%); /* IE10+ */
		background: linear-gradient(to right,  #1e5799 0%,#2989d8 12%,#2989d8 25%,#2989d8 38%,#2989d8 48%,#2989d8 61%,#2989d8 71%,#2989d8 81%,#207cca 89%,#7db9e8 100%); /* W3C */
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1e5799', endColorstr='#7db9e8',GradientType=1 ); /* IE6-8 */	
	}
	.navbar{margin-bottom:0; color:black;}
	nav img{height:30px;width:auto;}
	footer{
		/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#7db9e8+0,207cca+11,2989d8+19,2989d8+29,2989d8+39,2989d8+52,2989d8+62,2989d8+75,2989d8+88,1e5799+100 */
		background: #7db9e8; /* Old browsers */
		/* IE9 SVG, needs conditional override of 'filter' to 'none' */
		background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIxMDAlIiB5Mj0iMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzdkYjllOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjExJSIgc3RvcC1jb2xvcj0iIzIwN2NjYSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjE5JSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjI5JSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjM5JSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjUyJSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjYyJSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9Ijc1JSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9Ijg4JSIgc3RvcC1jb2xvcj0iIzI5ODlkOCIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiMxZTU3OTkiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
		background: -moz-linear-gradient(left,  #7db9e8 0%, #207cca 11%, #2989d8 19%, #2989d8 29%, #2989d8 39%, #2989d8 52%, #2989d8 62%, #2989d8 75%, #2989d8 88%, #1e5799 100%); /* FF3.6+ */
		background: -webkit-gradient(linear, left top, right top, color-stop(0%,#7db9e8), color-stop(11%,#207cca), color-stop(19%,#2989d8), color-stop(29%,#2989d8), color-stop(39%,#2989d8), color-stop(52%,#2989d8), color-stop(62%,#2989d8), color-stop(75%,#2989d8), color-stop(88%,#2989d8), color-stop(100%,#1e5799)); /* Chrome,Safari4+ */
		background: -webkit-linear-gradient(left,  #7db9e8 0%,#207cca 11%,#2989d8 19%,#2989d8 29%,#2989d8 39%,#2989d8 52%,#2989d8 62%,#2989d8 75%,#2989d8 88%,#1e5799 100%); /* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(left,  #7db9e8 0%,#207cca 11%,#2989d8 19%,#2989d8 29%,#2989d8 39%,#2989d8 52%,#2989d8 62%,#2989d8 75%,#2989d8 88%,#1e5799 100%); /* Opera 11.10+ */
		background: -ms-linear-gradient(left,  #7db9e8 0%,#207cca 11%,#2989d8 19%,#2989d8 29%,#2989d8 39%,#2989d8 52%,#2989d8 62%,#2989d8 75%,#2989d8 88%,#1e5799 100%); /* IE10+ */
		background: linear-gradient(to right,  #7db9e8 0%,#207cca 11%,#2989d8 19%,#2989d8 29%,#2989d8 39%,#2989d8 52%,#2989d8 62%,#2989d8 75%,#2989d8 88%,#1e5799 100%); /* W3C */
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#7db9e8', endColorstr='#1e5799',GradientType=1 ); /* IE6-8 */
	}
</style>
<body>
	<header>
		<div class="container">
			<h3>Las Javirecetas</h3>
		</div>
	</header>
	
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
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
		    <a class="navbar-brand" href="#"><img src="img/logo-chef.png" title="Logotipo"/></a>
		  </div>
		 
		  <!-- Agrupar los enlaces de navegación, los formularios y cualquier
		       otro elemento que se pueda ocultar al minimizar la barra -->
		  <div class="collapse navbar-collapse navbar-ex1-collapse">
		    <ul class="nav navbar-nav">
		      <li><a href="#">Crear receta</a></li>
		      <li><a href="#">Modificar receta</a></li>
		      <li><a href="#">Eliminar receta</a></li>    
		      <li><a href="#" data-toggle="modal" data-target="#loginModal">Login</a></li>  
		    </ul>
		    
			<!-- Ventana Modal Login -->
			<div id="loginModal" class="modal" tabindex="-1" role="dialog" aria-hidden="true">
			  <div class="modal-dialog">
			  <div class="modal-content">
			      <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			          <h1 class="text-center">Login</h1>
			      </div>
			      <div class="modal-body">
			          <form class="form col-md-12 center-block" action="login" method="post">
			            <div class="form-group">
			              <input type="text" class="form-control input-lg" placeholder="Usuario" name="usuario">
			            </div>
			            <div class="form-group">
			              <input type="password" class="form-control input-lg" placeholder="Password" name="password">
			            </div>
			            <div class="form-group">
			              <button class="btn btn-primary btn-lg btn-block">Entrar</button>
			            </div>
			          </form>
			      </div>
			      <div class="modal-footer">
			          <div class="col-md-12">
			          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</button>
					  </div>	
			      </div>
			  </div>
			  </div>
			</div>
			 <!-- END Ventana Modal -->
					 
		    <form class="navbar-form navbar-right" role="search">
		      <div class="form-group">
		        <input type="text" class="form-control" placeholder="Nombre del plato">
		      </div>
		      <button type="submit" class="btn btn-default">Buscar</button>
		    </form>
		 
		  </div>
		</nav>
	</div>

	<div class="container">
	
		<section class="main row">
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
		</section>
		
	</div>	
	
	<footer>
		<div class="container">
			<h5>Javi</h5>
		</div>
	</footer>
	
	
<!--  jQuery -->
 <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
 
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>