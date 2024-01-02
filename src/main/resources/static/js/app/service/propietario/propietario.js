portfolioWeb.service.propietario =(function(){
    function guardar(){
        portfolioWeb.service.post();
    }

    return{
        guardar: guardar
    }
})();