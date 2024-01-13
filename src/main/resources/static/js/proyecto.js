servicio =(function(){
    function init(){
        $('#btnGuardarProyecto').click(guardarProyecto);
        $('#btnAbrirModalCrearProyecto').click(limpiarModal);
        $('.editar-proyecto').click(cargarDatosAlModal);
        $('.eliminar-proyecto').click(eliminarProyecto);
    }

    function limpiarModal(){
        $('#inputIdProyecto').val(null);
        $('#inputIdPropietarioProyecto').val(null);
        $('#inputTituloProyecto').val("");
        $('#inputDescripcionProyecto').val("");
        $('#inputImagenProyecto').val("");
        $('#inputEnlaceGithubProyecto').val("");
        $('#inputEnlaceDemoProyecto').val("");
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idProyecto = btnEditar.attr('data-id-proyecto');
        var idPropietarioProyecto = btnEditar.attr('data-idPropietario-proyecto');
        var tituloProyecto = btnEditar.attr('data-titulo-proyecto');
        var descripcionProyecto = btnEditar.attr('data-descripcion-proyecto');
        var imagenProyecto = btnEditar.attr('data-imagen-proyeto');
        var enlaceGithubProyecto = btnEditar.attr('data-enlace-github-proyecto');
        var enlaceDemoProyecto = btnEditar.attr('data-enlace-demo-proyecto');

        $('#inputIdProyecto').val(idProyecto);
        $('#inputIdPropietarioProyecto').val(idPropietarioProyecto);
        $('#inputTituloProyecto').val(tituloProyecto);
        $('#inputDescripcionProyecto').val(descripcionProyecto);
        $('#inputImagenProyecto').val(imagenProyecto);
        $('#inputEnlaceGithubProyecto').val(enlaceGithubProyecto);
        $('#inputEnlaceDemoProyecto').val(enlaceDemoProyecto);
    }

    function guardarProyecto(e){
        e.preventDefault();
        var $guardarProyectoButton = $("#btnGuardarProyecto");
        $guardarProyectoButton.button("loading");

        var proyecto = {
            titulo: $('#inputTituloProyecto').val(),
            descripcion: $('#inputDescripcionProyecto').val(),
            imagen: $('#inputImagenProyecto').val(),
            enlaceGithub: $('#inputEnlaceGithubProyecto').val(),
            enlaceDemo: $('#inputEnlaceDemoProyecto').val(),
        }

        var idProyecto = $('#inputIdProyecto').val();

        if (idProyecto){
            proyecto.id = idProyecto;
            proyecto.idPropietario = $('#inputIdPropietarioProyecto').val();
            $.ajax({
                url: '/api/proyecto',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(proyecto),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarProyectoButton.button("reset");
                    alert("No se pudo actualizar el proyecto.");
                }
            });
        }else{
            $.ajax({
                url: '/api/proyecto',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(proyecto),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarProyectoButton.button("reset");
                    alert("No se pudo crear el proyecto.");
                }
            });
        }
    }

    function eliminarProyecto(){
        var btnEliminar = $(this);
        var idProyecto = btnEliminar.attr('data-id-proyecto');
        var tituloProyecto = btnEliminar.attr('data-titulo-proyecto');

        if (confirm("¿Estás seguro de que querés eliminar el proyecto " + tituloProyecto + "?")){
            $.ajax({
                url: '/api/proyecto/' + idProyecto,
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(proyecto),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("No se pudo eliminar el proyecto.");
                }
            });
        }
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    proyecto.init();
});