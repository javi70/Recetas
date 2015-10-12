//Se ejecuta cuando todo el html se ha cargado

$(function() {
	console.debug('Documento listo!');
	 
    tinymce.init({
    		selector:'#preparacionZoom',
    		language: "es",
    		min_height: 300
    });
    
    /**
     * Traduce al español el plugon Datatables.net
     */
    
	$('.tabla').DataTable({ 
	    "language": {
	    	    "sProcessing":     "Procesando...",
	    	    "sLengthMenu":     "Mostrar _MENU_ registros",
	    	    "sZeroRecords":    "No se encontraron resultados",
	    	    "sEmptyTable":     "Ningún dato disponible en esta tabla",
	    	    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	    	    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
	    	    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
	    	    "sInfoPostFix":    "",
	    	    "sSearch":         "Buscar:",
	    	    "sUrl":            "",
	    	    "sInfoThousands":  ",",
	    	    "sLoadingRecords": "Cargando...",
	    	    "oPaginate": {
	    	        "sFirst":    "Primero",
	    	        "sLast":     "Último",
	    	        "sNext":     "Siguiente",
	    	        "sPrevious": "Anterior"
	    	    },
	    	    "oAria": {
	    	        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
	    	        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
	    	    }
	    }	
	}); //End: DataTable


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
 
	var btn_agregar = document.getElementById('boton_volver_cantidad');
	btn_agregar.onclick=function(){
		$("#listaTodosLosIngredientes option:selected").each(function() {
			cantidad = $('#txtCantidadIngrediente').val();
			id_ingrediente = $('#listaTodosLosIngredientes').val();
			nombre_ingrediente = $("#listaTodosLosIngredientes option:selected").html();;
			$('#listaIngredientes').append('<option value="' + cantidad + ' de ' + id_ingrediente + '">' + cantidad + " de " + nombre_ingrediente + '</option>');
		})	
		$("#txtCantidadIngrediente").val("");
		$("#txtCantidadIngrediente").focus();
		focus();
	};


	
	var btn_eliminar = document.getElementById('eliminarIngredientes');
	btn_eliminar.onclick=function(){
		$("#listaIngredientes option:selected").each(function() {
			$("#listaIngredientes option:selected").remove();
		})			
	};
	
	var btn_zoom = document.getElementById('boton_zoom');
	btn_zoom.onclick=function(){
//		console.log($("#preparacion").val());
		tinymce.get('preparacionZoom').setContent($("#preparacion").val());
	};
	
	var btn_volver = document.getElementById('boton_volver');
	btn_volver.onclick=function(){
//		console.log(tinymce.get('preparacionZoom').getContent());			
		$("#preparacion").val(tinymce.get('preparacionZoom').getContent());
	};
	 

	var btn_submit = document.getElementById('boton_guardar');
	btn_submit.onclick=function(){
		
		//Seleccionar todo el select de ingredientes de las recetas
		elem=document.getElementById("listaIngredientes").options;
	    for(i=0;i<elem.length;i++) elem[i].selected=true;
	    
	    //Se obliga a poner nombre a la receta
	    if($("#nombre").val()!=""){
		    document.form_nuevaReceta.submit();	    	
	    }else{
	    	$("#mensaje_formulario").toggle();
	    }
	};//End: boton_guardar
	
	
	 
}); // end
