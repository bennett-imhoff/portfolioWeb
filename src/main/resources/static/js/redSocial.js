redSocial =(function(){
    function init(){
        $('#btnCrearRedSocial').click(crearRedSocial);
    }

    function crearRedSocial(e){
        e.preventDefault();
        var $crearRedSocialButton = $("#btnCrearRedSocial");
        $crearRedSocialButton.button("loading");

        var redSocial = {
            nombre: $('#inputNombreRedSocial').val(),
            enlace: $('#inputEnlaceRedSocial').val(),
            icono: $('#inputIconoRedSocial').val(),
        }

        $.ajax({
            url: '/api/redSocial',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(redSocial),
            success: function(response) {
                window.location.reload();
                alert("Se creó la red social.");
            },
            error: function(xhr, status, error) {
                $crearRedSocialButton.button("reset");
                alert("No se pudo crear la red social.");
            }
        });
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    redSocial.init();
});