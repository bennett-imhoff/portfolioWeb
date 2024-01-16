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
        $('#inputNecesitaDescripcionSubcategoria').prop('checked', false);
        $('#inputNecesitaPorcentajeSubcategoria').prop('checked', false);
        $('#inputNecesitaIconoSubcategoria').prop('checked', false);
        $('#inputNecesitaImagenSubcategoria').prop('checked', false);
        $('#inputNecesitaNivelSubcategoria').prop('checked', false);
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idSubcategoria = btnEditar.attr('data-id-subcategoria');
        var nombreSubcategoria = btnEditar.attr('data-nombre-subcategoria');
        var idCategoriaSubcategoria = btnEditar.attr('data-idCategoria-subcategoria');
        var necesitaDescripcionSubcategoria = btnEditar.attr('data-id-necesita-descripcion') === 'true';
        var necesitaPorcentajeSubcategoria = btnEditar.attr('data-id-necesita-porcentaje') === 'true';
        var necesitaIconoSubcategoria = btnEditar.attr('data-id-necesita-icono') === 'true';
        var necesitaImagenSubcategoria = btnEditar.attr('data-id-necesita-imagen') === 'true';
        var necesitaNivelSubcategoria = btnEditar.attr('data-id-necesita-nivel') === 'true';

        $('#inputIdSubcategoria').val(idSubcategoria);
        $('#inputNombreSubcategoria').val(nombreSubcategoria);
        $('#inputIdCategoriaSubcategoria').val(idCategoriaSubcategoria);
        $('#inputNecesitaDescripcionSubcategoria').prop('checked', necesitaDescripcionSubcategoria);
        $('#inputNecesitaPorcentajeSubcategoria').prop('checked', necesitaPorcentajeSubcategoria);
        $('#inputNecesitaIconoSubcategoria').prop('checked', necesitaIconoSubcategoria);
        $('#inputNecesitaImagenSubcategoria').prop('checked', necesitaImagenSubcategoria);
        $('#inputNecesitaNivelSubcategoria').prop('checked', necesitaNivelSubcategoria);
    }

    function guardarSubcategoria(e){
        e.preventDefault();
        var $guardarSubcategoriaButton = $("#btnGuardarSubcategoria");
        $guardarSubcategoriaButton.button("loading");

        var habilidadSubcategoria = {
            nombre: $('#inputNombreSubcategoria').val(),
            idHabilidadCategoria: $('#inputIdCategoriaSubcategoria').val(),
            necesitaDescripcion: $('#inputNecesitaDescripcionSubcategoria').prop('checked'),
            necesitaPorcentaje: $('#inputNecesitaPorcentajeSubcategoria').prop('checked'),
            necesitaIcono: $('#inputNecesitaIconoSubcategoria').prop('checked'),
            necesitaImagen: $('#inputNecesitaImagenSubcategoria').prop('checked'),
            necesitaNivel: $('#inputNecesitaNivelSubcategoria').prop('checked'),
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