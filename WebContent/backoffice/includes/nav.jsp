<%@page import="com.javireal.casa.recetas.Constantes"%>
<%@page contentType="text/html; charset=UTF-8" %>
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Administración de Recetas</a>
            </div>
            <!-- Top Menu Items -->
            
            
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="<%=Constantes.CONTROLLER%>?origen=<%=Constantes.ORIGEN_RECETAS%>&accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-fw fa-cutlery"></i> Recetas</a>
                    </li>
                    <li>
                        <a href="<%=Constantes.CONTROLLER_ELEMENTOS%>?origen=<%=Constantes.ORIGEN_INGREDIENTES%>&accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-fw fa-book"></i> Ingredientes</a>
                    </li>
                    <li>
                        <a href="<%=Constantes.CONTROLLER_ELEMENTOS%>?origen=<%=Constantes.ORIGEN_CATEGORIAS%>&accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-fw fa-file"></i> Categorías</a>
                    </li>
                    <li>
                        <a href="<%=Constantes.CONTROLLER_ELEMENTOS%>?origen=<%=Constantes.ORIGEN_TIPOSCOCINA%>&accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-fw fa-file-text"></i> Tipos de cocina</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

