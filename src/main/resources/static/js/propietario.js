propietario =(function(){
    function init(){
        $('#btnActualizarPropietario').click(actualizarPropietario);
    }

    function actualizarPropietario(e){
        e.preventDefault();
        var $actualizarButton = $("#btnActualizarPropietario");
        $actualizarButton.button("loading");

        var propietario = {
            id: $('#inputIdPropietario').val(),
            nombre: $('#inputNombrePropietario').val(),
            apellido: $('#inputApellidoPropietario').val(),
            descripcion: $('#inputDescripcionPropietario').val(),
            ubicacion: $('#inputUbicacionPropietario').val(),
            correo: $('#inputCorreoPropietario').val(),
            telefono: $('#inputTelefonoPropietario').val(),
            username: $('#inputUsernamePropietario').val(),
            password: $('#inputPasswordPropietario').val(),
            textoSoy: $('#inputTextoSoyPropietario').val(),
            textoQuiero: $('#inputTextoQuieroPropietario').val(),
            textoHago: $('#inputTextoHagoPropietario').val(),
        }

        $.ajax({
            url: '/api/propietario',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(propietario),
            success: function(response) {
                window.location.reload();
            },
            error: function(xhr, status, error) {
                $actualizarButton.button("reset");
                alert("No se pudieron actualizar tus datos.");
            }
        });
    }

    return {
        init: init
    }
})();

$(document).ready(function(){
    propietario.init();
});