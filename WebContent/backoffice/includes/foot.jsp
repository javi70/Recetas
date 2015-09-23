
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <!-- 
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>
	 -->
	   
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>


	<!--  Convertir todos los textareas en TinyMce -->
	<script src="js/tinymce/tinymce.min.js"></script>

</body>

	<script>
	$(document).ready( function () {
		
		
	    $('.tabla').DataTable();
	    tinymce.init({
	    		selector:'#preparacionZoom',
	    		language: "es",
	    		min_height: 300
	    });
	    
	    /**
    	*	Carga la imagen seleccionada en el formulario Recetas
    	*/
    	function readURL(input) {

    	    if (input.files && input.files[0]) {
    	        var reader = new FileReader();

    	        reader.onload = function (e) {
    	            $('#imgReceta').attr('src', e.target.result);
    	        }

    	        reader.readAsDataURL(input.files[0]);
    	    }
    	}; //End: readURL
    	
    	$("#fotografiaFile").change(function(){
    	    readURL(this);	    			
    	}); // End: $("#imagenFile").change(function()
	} );
	</script>

</html>
