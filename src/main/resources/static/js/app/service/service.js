portfolioWeb.service = (function () {
    function uriService() {
        url = che.url();
        return url + "api/";
    }

    function get(uri) {
        return $.get(uri);
    }

    function post(uri, data) {
        return $.ajax({
            contentType: 'application/json; charset=UTF-8',
            url: uri,
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json'
        });
    }

    function put(uri, data) {
        return $.ajax({
            contentType: 'application/json; charset=UTF-8',
            url: uri,
            type: 'PUT',
            data: JSON.stringify(data),
            dataType: 'json'
        });
    }

    function eliminar(uri, data) {
        return $.ajax({
            contentType: 'application/json; charset=UTF-8',
            url: uri,
            data: JSON.stringify(data),
            type: 'DELETE'
        });
    }


    return {
        url: uriService,
        get: get,
        post: post,
        put: put,
        eliminar: eliminar
    };
})();