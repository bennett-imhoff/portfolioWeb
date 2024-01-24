educacion =(function(){
    function init(){
        $('#btnGuardarEducacion').click(guardarEducacion);
        $('#btnAbrirModalCrearEducacion').click(limpiarModal);
        $('.editar-educacion').click(cargarDatosAlModal);
        $('.eliminar-educacion').click(eliminarEducacion);
    }

    function limpiarModal(){
        $('#inputIdEducacion').val(null);
        $('#inputIdPropietarioEducacion').val(null);
        $('#inputTituloEducacion').val("");
        $('#inputLugarEducacion').val("");
        $('#inputDescripcionEducacion').val("");
        $('#inputFechaInicioEducacion').val(null);
        $('#inputFechaFinEducacion').val(null);
        $('#inputIconoEducacion').val("");
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idEducacion = btnEditar.attr('data-id-educacion');
        var idPropietarioEducacion = btnEditar.attr('data-idPropietario-educacion');
        var tituloEducacion = btnEditar.attr('data-titulo-educacion');
        var lugarEducacion = btnEditar.attr('data-lugar-educacion');
        var descripcionEducacion = btnEditar.attr('data-descripcion-educacion');
        var fechaInicioEducacion = btnEditar.attr('data-fecha-inicio-educacion');
        var fechaFinEducacion = btnEditar.attr('data-fecha-fin-educacion');
        var iconoEducacion = btnEditar.attr('data-icono-educacion');

        $('#inputIdEducacion').val(idEducacion);
        $('#inputIdPropietarioEducacion').val(idPropietarioEducacion);
        $('#inputTituloEducacion').val(tituloEducacion);
        $('#inputLugarEducacion').val(lugarEducacion);
        $('#inputDescripcionEducacion').val(descripcionEducacion);
        $('#inputFechaInicioEducacion').val(fechaInicioEducacion);
        $('#inputFechaFinEducacion').val(fechaFinEducacion);
        $('#inputIconoEducacion').val(iconoEducacion);
    }

    function guardarEducacion(e){
        e.preventDefault();
        var $guardarEducacionButton = $("#btnGuardarEducacion");
        $guardarEducacionButton.button("loading");

        var educacion = {
            titulo: $('#inputTituloEducacion').val(),
            lugar: $('#inputLugarEducacion').val(),
            descripcion: $('#inputDescripcionEducacion').val(),
            fechaInicio: $('#inputFechaInicioEducacion').val(),
            fechaFin: $('#inputFechaFinEducacion').val(),
            icono: $('#inputIconoEducacion').val(),
        }

        var idEducacion = $('#inputIdEducacion').val();

        if (idEducacion){
            educacion.id = idEducacion;
            educacion.idPropietario = $('#inputIdPropietarioEducacion').val();
            $.ajax({
                url: '/api/educacion',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(educacion),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarEducacionButton.button("reset");
                    alert("No se pudo actualizar la educacion.");
                }
            });
        }else{
            $.ajax({
                url: '/api/educacion',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(educacion),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarEducacionButton.button("reset");
                    alert("No se pudo crear la educacion.");
                }
            });
        }
    }

    function eliminarEducacion(){
        var btnEliminar = $(this);
        var idEducacion = btnEliminar.attr('data-id-educacion');
        var tituloEducacion = btnEliminar.attr('data-titulo-educacion');

        if (confirm("¿Estás seguro de que querés eliminar su estudio de " + tituloEducacion + "?")){
            $.ajax({
                url: '/api/educacion/' + idEducacion,
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(educacion),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("No se pudo eliminar educacion.");
                }
            });
        }
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    educacion.init();
});