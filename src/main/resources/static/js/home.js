home =(function(){
    function init(){
        $('.navbar-nav a').click(cerrarOffCanvas);
    }

    function cerrarOffCanvas(){
        $('.offcanvas').offcanvas('hide');
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    home.init();
});