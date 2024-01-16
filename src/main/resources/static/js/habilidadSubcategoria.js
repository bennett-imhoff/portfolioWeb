habilidadSubcategoria =(function(){
    function init(){
        $('#btnGuardarSubcategoria').click(guardarSubcategoria);
        $('#btnAbrirModalCrearSubcategoria').click(limpiarModal);
        $('.editar-subcategoria').click(cargarDatosAlModal);
        $('.eliminar-subcategoria').click(eliminarSubcategoria);
    }

    function limpiarModal(){
        $('#inputIdSubcategoria').val(null);
        $('#inputNombreSubcategoria').val("");
        $('#inputIdCategoriaSubcategoria').val(null);
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idSubcategoria = btnEditar.attr('data-id-subcategoria');
        var nombreSubcategoria = btnEditar.attr('data-nombre-subcategoria');
        var idCategoriaSubcategoria = btnEditar.attr('data-idCategoria-subcategoria');

        $('#inputIdSubcategoria').val(idSubcategoria);
        $('#inputNombreSubcategoria').val(nombreSubcategoria);
        $('#inputIdCategoriaSubcategoria').val(idCategoriaSubcategoria);
    }

    function guardarSubcategoria(e){
        e.preventDefault();
        var $guardarSubcategoriaButton = $("#btnGuardarSubcategoria");
        $guardarSubcategoriaButton.button("loading");

        var habilidadSubcategoria = {
            nombre: $('#inputNombreSubcategoria').val(),
            idHabilidadCategoria: $('#inputIdCategoriaSubcategoria').val(),
        }

        var idSubcategoria = $('#inputIdSubcategoria').val();

        if (idSubcategoria){
            habilidadSubcategoria.id = idSubcategoria;
            $.ajax({
                url: '/api/habilidadSubcategoria',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(habilidadSubcategoria),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarSubcategoriaButton.button("reset");
                    alert("No se pudo actualizar la subcategoría.");
                }
            });
        }else{
            $.ajax({
                url: '/api/habilidadSubcategoria',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(habilidadSubcategoria),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    $guardarSubcategoriaButton.button("reset");
                    alert("No se pudo crear la subcategoría.");
                }
            });
        }
    }

    function eliminarSubcategoria(){
        var btnEliminar = $(this);
        var idSubcategoria = btnEliminar.attr('data-id-subcategoria');
        var nombreSubcategoria = btnEliminar.attr('data-nombre-subcategoria');

        if (confirm("¿Estás seguro de que querés eliminar la subcategoria " + nombreSubcategoria + "?")){
            $.ajax({
                url: '/api/habilidadSubcategoria/' + idSubcategoria,
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(habilidadSubcategoria),
                success: function(response) {
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("No se pudo eliminar la subcategoría.");
                }
            });
        }
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    habilidadSubcategoria.init();
});