<%@page contentType="text/html" pageEncoding="UTF-8"%> 
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
<!-- END Ventana Modal LOGIN -->