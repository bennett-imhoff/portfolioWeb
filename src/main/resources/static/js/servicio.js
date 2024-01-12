servicio =(function(){
    function init(){
        $('#btnGuardarServicio').click(guardarServicio);
        $('#btnAbrirModalCrearServicio').click(limpiarModal);
        $('.editar-servicio').click(cargarDatosAlModal);
        $('.eliminar-servicio').click(eliminarServicio);
    }

    function limpiarModal(){
        $('#inputIdServicio').val(null);
        $('#inputIdPropietarioServicio').val(null);
        $('#inputIconoServicio').val("");
        $('#inputTituloServicio').val("");
        $('#inputDescripcionServicio').val("");
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idServicio = btnEditar.attr('data-id-servicio');
        var idPropietarioServicio = btnEditar.attr('data-idPropietario-servicio');
        var iconoServicio = btnEditar.attr('data-icono-servicio');
        var tituloServicio = btnEditar.attr('data-titulo-servicio');
        var descripcionServicio = btnEditar.attr('data-descripcion-servicio');

        $('#inputIdServicio').val(idServicio);
        $('#inputIdPropietarioServicio').val(idPropietarioServicio);
        $('#inputIconoServicio').val(iconoServicio);
        $('#inputTituloServicio').val(tituloServicio);
        $('#inputDescripcionServicio').val(descripcionServicio);
    }

    function guardarServicio(e){
        e.preventDefault();
        var $guardarServicioButton = $("#btnGuardarServicio");
        $guardarServicioButton.button("loading");

        var servicio = {
            icono: $('#inputIconoServicio').val(),
            titulo: $('#inputTituloServicio').val(),
            descripcion: $('#inputDescripcionServicio').val(),
        }

        var idServicio = $('#inputIdServicio').val();

        if (idServicio){
            servicio.id = idServicio;
            servicio.idPropietario = $('#inputIdPropietarioServicio').val();
            $.ajax({
                url: '/api/servicio',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(servicio),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarServicioButton.button("reset");
                    alert("No se pudo actualizar el servicio.");
                }
            });
        }else{
            $.ajax({
                url: '/api/servicio',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(servicio),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarServicioButton.button("reset");
                    alert("No se pudo crear el servicio.");
                }
            });
        }
    }

    function eliminarServicio(){
        var btnEliminar = $(this);
        var idServicio = btnEliminar.attr('data-id-servicio');
        var tituloServicio = btnEliminar.attr('data-titulo-servicio');

        if (confirm("¿Estás seguro de que querés eliminar el servicio " + tituloServicio + "?")){
            $.ajax({
                url: '/api/servicio/' + idServicio,
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(servicio),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("No se pudo eliminar el servicio.");
                }
            });
        }
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    servicio.init();
});