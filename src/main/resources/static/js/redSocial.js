redSocial =(function(){
    function init(){
        $('#btnGuardarRedSocial').click(guardarRedSocial);
        $('#btnAbrirModalCrearRedSocial').click(limpiarModal);
        $('.editar-red-social').click(cargarDatosAlModal);
        $('.eliminar-red-social').click(eliminarRedSocial);
    }

    function limpiarModal(){
        $('#inputIdRedSocial').val(null);
        $('#inputIdPropietarioRedSocial').val(null);
        $('#inputNombreRedSocial').val("");
        $('#inputEnlaceRedSocial').val("");
        $('#inputIconoRedSocial').val("");
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idRed = btnEditar.attr('data-id-red');
        var idPropietarioRed = btnEditar.attr('data-idPropietario-red');
        var nombreRed = btnEditar.attr('data-nombre-red');
        var enlaceRed = btnEditar.attr('data-enlace-red');
        var iconoRed = btnEditar.attr('data-icono-red');

        $('#inputIdRedSocial').val(idRed);
        $('#inputIdPropietarioRedSocial').val(idPropietarioRed);
        $('#inputNombreRedSocial').val(nombreRed);
        $('#inputEnlaceRedSocial').val(enlaceRed);
        $('#inputIconoRedSocial').val(iconoRed);
    }

    function guardarRedSocial(e){
        e.preventDefault();
        var $guardarRedSocialButton = $("#btnGuardarRedSocial");
        $guardarRedSocialButton.button("loading");

        var redSocial = {
            nombre: $('#inputNombreRedSocial').val(),
            enlace: $('#inputEnlaceRedSocial').val(),
            icono: $('#inputIconoRedSocial').val(),
        }

        var idRedSocial = $('#inputIdRedSocial').val();

        if (idRedSocial){
            redSocial.id = idRedSocial;
            redSocial.idPropietario = $('#inputIdPropietarioRedSocial').val();
            $.ajax({
                url: '/api/redSocial',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(redSocial),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarRedSocialButton.button("reset");
                    alert("No se pudo actualizar la red social.");
                }
            });
        }else{
            $.ajax({
                url: '/api/redSocial',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(redSocial),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarRedSocialButton.button("reset");
                    alert("No se pudo crear la red social.");
                }
            });
        }
    }

    function eliminarRedSocial(){
        var btnEliminar = $(this);
        var idRed = btnEliminar.attr('data-id-red');
        var nombreRed = btnEliminar.attr('data-nombre-red');

        if (confirm("¿Estás seguro de que querés eliminar " + nombreRed + "?")){
            $.ajax({
                url: '/api/redSocial/' + idRed,
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(redSocial),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("No se pudo eliminar la red social.");
                }
            });
        }
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    redSocial.init();
});