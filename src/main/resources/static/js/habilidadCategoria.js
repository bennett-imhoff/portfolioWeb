habilidadCategoria =(function(){
    function init(){
        $('#btnGuardarCategoria').click(guardarCategoria);
        $('#btnAbrirModalCrearCategoria').click(limpiarModal);
        $('.editar-categoria').click(cargarDatosAlModal);
        $('.eliminar-categoria').click(eliminarCategoria);
    }

    function limpiarModal(){
        $('#inputIdCategoria').val(null);
        $('#inputIdPropietarioCategoria').val(null);
        $('#inputNombreCategoria').val("");
        $('#inputDescripcionCategoria').val("");
        $('#inputIconoCategoria').val("");
        $('#inputColorIconoCategoria').val("");
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idCategoria = btnEditar.attr('data-id-categoria');
        var idPropietarioCategoria = btnEditar.attr('data-idPropietario-categoria');
        var nombreCategoria = btnEditar.attr('data-nombre-categoria');
        var descripcionCategoria = btnEditar.attr('data-descripcion-categoria');
        var iconoCategoria = btnEditar.attr('data-icono-categoria');
        var colorIconoCategoria = btnEditar.attr('data-color-icono-categoria');

        $('#inputIdCategoria').val(idCategoria);
        $('#inputIdPropietarioCategoria').val(idPropietarioCategoria);
        $('#inputNombreCategoria').val(nombreCategoria);
        $('#inputDescripcionCategoria').val(descripcionCategoria);
        $('#inputIconoCategoria').val(iconoCategoria);
        $('#inputColorIconoCategoria').val(colorIconoCategoria);
    }

    function guardarCategoria(e){
        e.preventDefault();
        var $guardarCategoriaButton = $("#btnGuardarCategoria");
        $guardarCategoriaButton.button("loading");

        var categoria = {
            nombre: $('#inputNombreCategoria').val(),
            descripcion: $('#inputDescripcionCategoria').val(),
            icono: $('#inputIconoCategoria').val(),
            colorIcono: $('#inputColorIconoCategoria').val(),
        }

        var idCategoria = $('#inputIdCategoria').val();

        if (idCategoria){
            categoria.id = idCategoria;
            categoria.idPropietario = $('#inputIdPropietarioCategoria').val();
            $.ajax({
                url: '/api/habilidadCategoria',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(categoria),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarCategoriaButton.button("reset");
                    alert("No se pudo actualizar la categoría.");
                }
            });
        }else{
            $.ajax({
                url: '/api/habilidadCategoria',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(categoria),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarCategoriaButton.button("reset");
                    alert("No se pudo crear la categoría.");
                }
            });
        }
    }

    function eliminarCategoria(){
        var btnEliminar = $(this);
        var idCategoria = btnEliminar.attr('data-id-categoria');
        var nombreCategoria = btnEliminar.attr('data-nombre-categoria');

        if (confirm("¿Estás seguro de que querés eliminar la categoria " + nombreCategoria + "?")){
            $.ajax({
                url: '/api/habilidadCategoria/' + idCategoria,
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(categoria),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("No se pudo eliminar la categoría.");
                }
            });
        }
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    habilidadCategoria.init();
});