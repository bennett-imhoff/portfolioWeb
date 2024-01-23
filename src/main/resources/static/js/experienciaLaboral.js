experienciaLaboral =(function(){
    function init(){
        $('#btnGuardarExperienciaLaboral').click(guardarExperienciaLaboral);
        $('#btnAbrirModalCrearExperienciaLaboral').click(limpiarModal);
        $('.editar-experiencia-laboral').click(cargarDatosAlModal);
        $('.eliminar-experiencia-laboral').click(eliminarExperienciaLaboral);
    }

    function limpiarModal(){
        $('#inputIdExperienciaLaboral').val(null);
        $('#inputIdPropietarioExperienciaLaboral').val(null);
        $('#inputPuestoExperienciaLaboral').val("");
        $('#inputEmpresaExperienciaLaboral').val("");
        $('#inputDescripcionExperienciaLaboral').val("");
        $('#inputFechaInicioExperienciaLaboral').val(null);
        $('#inputFechaFinExperienciaLaboral').val(null);
        $('#inputIconoExperienciaLaboral').val("");
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idExperienciaLaboral = btnEditar.attr('data-id-experiencia-laboral');
        var idPropietarioExperienciaLaboral = btnEditar.attr('data-idPropietario-experiencia-laboral');
        var puestoExperienciaLaboral = btnEditar.attr('data-puesto-experiencia-laboral');
        var empresaExperienciaLaboral = btnEditar.attr('data-empresa-experiencia-laboral');
        var descripcionExperienciaLaboral = btnEditar.attr('data-descripcion-experiencia-laboral');
        var fechaInicioExperienciaLaboral = btnEditar.attr('data-fecha-inicio-experiencia-laboral');
        var fechaFinExperienciaLaboral = btnEditar.attr('data-fecha-fin-experiencia-laboral');
        var iconoExperienciaLaboral = btnEditar.attr('data-icono-experiencia-laboral');

        $('#inputIdExperienciaLaboral').val(idExperienciaLaboral);
        $('#inputPuestoExperienciaLaboral').val(puestoExperienciaLaboral);
        $('#inputEmpresaExperienciaLaboral').val(empresaExperienciaLaboral);
        $('#inputDescripcionExperienciaLaboral').val(descripcionExperienciaLaboral);
        $('#inputFechaInicioExperienciaLaboral').val(fechaInicioExperienciaLaboral);
        $('#inputFechaFinExperienciaLaboral').val(fechaFinExperienciaLaboral);
        $('#inputIconoExperienciaLaboral').val(iconoExperienciaLaboral);
        $('#inputIdPropietarioExperienciaLaboral').val(idPropietarioExperienciaLaboral);
    }

    function guardarExperienciaLaboral(e){
        e.preventDefault();
        var $guardarExperienciaLaboralButton = $("#btnGuardarExperienciaLaboral");
        $guardarExperienciaLaboralButton.button("loading");

        var experienciaLaboral = {
            puesto: $('#inputPuestoExperienciaLaboral').val(),
            empresa: $('#inputEmpresaExperienciaLaboral').val(),
            descripcion: $('#inputDescripcionExperienciaLaboral').val(),
            fechaInicio: $('#inputFechaInicioExperienciaLaboral').val(),
            fechaFin: $('#inputFechaFinExperienciaLaboral').val(),
            icono: $('#inputIconoExperienciaLaboral').val(),
        }

        var idExperienciaLaboral = $('#inputIdExperienciaLaboral').val();

        if (idExperienciaLaboral){
            experienciaLaboral.id = idExperienciaLaboral;
            experienciaLaboral.idPropietario = $('#inputIdPropietarioExperienciaLaboral').val();
            $.ajax({
                url: '/api/experienciaLaboral',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(experienciaLaboral),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarExperienciaLaboralButton.button("reset");
                    alert("No se pudo actualizar la experiencia laboral.");
                }
            });
        }else{
            $.ajax({
                url: '/api/experienciaLaboral',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(experienciaLaboral),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarExperienciaLaboralButton.button("reset");
                    alert("No se pudo crear la experiencia laboral.");
                }
            });
        }
    }

    function eliminarExperienciaLaboral(){
        var btnEliminar = $(this);
        var idExperienciaLaboral = btnEliminar.attr('data-id-experiencia-laboral');
        var puestoExperienciaLaboral = btnEliminar.attr('data-puesto-experiencia-laboral');
        var empresaExperienciaLaboral = btnEliminar.attr('data-empresa-experiencia-laboral');

        if (confirm("¿Estás seguro de que querés eliminar el puesto " + puestoExperienciaLaboral + " en " + empresaExperienciaLaboral + "?")){
            $.ajax({
                url: '/api/experienciaLaboral/' + idExperienciaLaboral,
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(experienciaLaboral),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("No se pudo eliminar experiencia laboral.");
                }
            });
        }
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    experienciaLaboral.init();
});