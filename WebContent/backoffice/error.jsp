<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

 <div id="page-wrapper">
 	<div class="row">
    	<div class="col-lg-12">
        	<h1 class="page-header">Página de Error</h1>
		</div>
	</div>
	
  <div id="contenido-error" class="jumbotron">         
    <div class="row">
		<img src="../img/error-500.jpg" >
	</div>
	
    <div class="row">
		<p>Se ha producido un error</p>
		<p>Si lo deseas puedes seguir por <a href="">aquí</a></p>
		<p>También puedes comunicarnos la incidencia 
			<a href="mailto://javi70@gmail.com"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
			</a>
		</p>
	</div>
  </div>	
</div>


<jsp:include page="includes/foot.jsp"></jsp:include>