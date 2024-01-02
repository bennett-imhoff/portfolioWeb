portfolioWeb.ui.propietario =(function(){
    function init(){
        $('#botonPrueba').click(guardar);
    }

    function guardar(){
        portfolioWeb.service.propietario.guardar();
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    portfolioWeb.ui.propietario.init();
});