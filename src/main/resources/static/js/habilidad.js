habilidad =(function(){
    function init(){
        $('#btnGuardarHabilidad').click(guardarHabilidad);
        $('#btnAbrirModalCrearHabilidad').click(limpiarModal);
        $('.editar-habilidad').click(cargarDatosAlModal);
        $('.eliminar-habilidad').click(eliminarHabilidad);
        $('#inputIdHabilidadSubcategoria').change(elegirSubcategoria);
    }

    function elegirSubcategoria(){
        var idSubcategoriaElegida = $(this).val();

        $.ajax({
            url: '/api/habilidadSubcategoria/' + idSubcategoriaElegida,
            type:'GET',
            success: function(subcategoria){
                console.log(subcategoria);
            },
            error: function(){
                alert("ERROR");
            }
        });
    }

    function limpiarModal(){
        $('#inputIdHabilidad').val(null);
        $('#inputNombreHabilidad').val("");
        $('#inputIdHabilidadSubcategoria').val(null);
    }

    function cargarDatosAlModal(){
        var btnEditar = $(this);
        var idHabilidad = btnEditar.attr('data-id-habilidad');
        var idNombreHabilidad = btnEditar.attr('data-nombre-habilidad');
        var idSubcategoriaHabilidad = btnEditar.attr('ddata-idSubcategoria-habilidad');

        $('#inputIdHabilidad').val(idHabilidad);
        $('#inputNombreHabilidad').val(idNombreHabilidad);
        $('#inputIdHabilidadSubcategoria').val(idSubcategoriaHabilidad);
    }

    function guardarHabilidad(e){
        e.preventDefault();
        var $guardarHabilidadButton = $("#btnGuardarHabilidad");
        $guardarHabilidadButton.button("loading");

        var habilidad = {
            nombre: $('#inputNombreHabilidad').val(),
            idHabilidadSubcategoria: $('#inputIdHabilidadSubcategoria').val(),
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