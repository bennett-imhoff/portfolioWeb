habilidad =(function(){
    function init(){
        $('#btnGuardarHabilidad').click(guardarHabilidad);
        $('#btnAbrirModalCrearHabilidad').click(limpiarModal);
        $('.editar-habilidad').click(cargarDatosAlModal);
        $('.eliminar-habilidad').click(eliminarHabilidad);
        $('#inputIdHabilidadSubcategoria').change(elegirSubcategoria);

        $('#seccionDescripcion').hide();
        $('#seccionPorcentaje').hide();
        $('#seccionIcono').hide();
        $('#seccionImagen').hide();
        $('#seccionNivel').hide();
    }

    function elegirSubcategoria(){
        var idSubcategoriaElegida = $(this).val();

        $.ajax({
            url: '/api/habilidadSubcategoria/' + idSubcategoriaElegida,
            type:'GET',
            success: function(subcategoria){
                if (subcategoria && subcategoria.necesitaDescripcion){
                    $('#seccionDescripcion').show();
                }else{
                    $('#seccionDescripcion').hide();
                }
                if (subcategoria && subcategoria.necesitaPorcentaje){
                    $('#seccionPorcentaje').show();
                }else{
                    $('#seccionPorcentaje').hide();
                }
                if (subcategoria && subcategoria.necesitaIcono){
                    $('#seccionIcono').show();
                }else{
                    $('#seccionIcono').hide();
                }
                if (subcategoria && subcategoria.necesitaImagen){
                    $('#seccionImagen').show();
                }else{
                    $('#seccionImagen').hide();
                }
                if (subcategoria && subcategoria.necesitaNivel){
                    $('#seccionNivel').show();
                }else{
                    $('#seccionNivel').hide();
                }
            },
            error: function(){
                alert("No se pudo obtener ninguna subcategoría.");
            }
        });
    }

    function limpiarModal(){
        $('#inputIdHabilidad').val(null);
        $('#inputNombreHabilidad').val("");
        $('#inputIdHabilidadSubcategoria').val(null);
        $('#inputDescripcionHabilidad').val("");
        $('#inputPorcentajeHabilidad').val(null);
        $('#inputIconoHabilidad').val("");
        $('#inputImagenHabilidad').val("");
        $('#inputNivelHabilidad').val("");
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idHabilidad = btnEditar.attr('data-id-habilidad');
        var idNombreHabilidad = btnEditar.attr('data-nombre-habilidad');
        var idSubcategoriaHabilidad = btnEditar.attr('data-idSubcategoria-habilidad');
        var descripcionHabilidad = btnEditar.attr('data-descripcion-habilidad');
        var porcentajeHabilidad = btnEditar.attr('data-porcentaje-habilidad');
        var iconoHabilidad = btnEditar.attr('data-icono-habilidad');
        var imagenHabilidad = btnEditar.attr('data-imagen-habilidad');
        var nivelHabilidad = btnEditar.attr('data-nivel-habilidad');

        $('#inputIdHabilidad').val(idHabilidad);
        $('#inputNombreHabilidad').val(idNombreHabilidad);
        $('#inputIdHabilidadSubcategoria').val(idSubcategoriaHabilidad);
        $('#inputDescripcionHabilidad').val(descripcionHabilidad);
        $('#inputPorcentajeHabilidad').val(porcentajeHabilidad);
        $('#inputIconoHabilidad').val(iconoHabilidad);
        $('#inputImagenHabilidad').val(imagenHabilidad);
        $('#inputNivelHabilidad').val(nivelHabilidad);
    }

    function guardarHabilidad(e){
        e.preventDefault();
        var $guardarHabilidadButton = $("#btnGuardarHabilidad");
        $guardarHabilidadButton.button("loading");

        var habilidad = {
            nombre: $('#inputNombreHabilidad').val(),
            idHabilidadSubcategoria: $('#inputIdHabilidadSubcategoria').val(),
            descripcion: $('#inputDescripcionHabilidad').val(),
            porcentaje: $('#inputPorcentajeHabilidad').val(),
            icono: $('#inputIconoHabilidad').val(),
            imagen: $('#inputImagenHabilidad').val(),
            nivel: $('#inputNivelHabilidad').val(),
        }

        var idHabilidad = $('#inputIdHabilidad').val();

        if (idHabilidad){
            habilidad.id = idHabilidad;
            $.ajax({
                url: '/api/habilidad',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(habilidad),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarCategoriaButton.button("reset");
                    alert("No se pudo actualizar la habilidad.");
                }
            });
        }else{
            $.ajax({
                url: '/api/habilidad',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(habilidad),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarCategoriaButton.button("reset");
                    alert("No se pudo crear la habilidad.");
                }
            });
        }
    }

    function eliminarHabilidad(){
        var btnEliminar = $(this);
        var idHabilidad = btnEliminar.attr('data-id-habilidad');
        var nombreHabilidad = btnEliminar.attr('data-nombre-habilidad');

        if (confirm("¿Estás seguro de que querés eliminar la habilidad " + nombreHabilidad + "?")){
            $.ajax({
                url: '/api/habilidad/' + idHabilidad,
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(habilidad),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("No se pudo eliminar la habilidad.");
                }
            });
        }
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    habilidad.init();
});