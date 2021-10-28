$(document).ready(() => {
    btnListener();
});


var btnListener = () => {
    $("#btnLogin").on("click", (e) => {
        e.preventDefault();
        var usuario = $("#usuario").val().trim();
        var pass = $("#password").val().trim();

        if ((usuario != "") && (pass != "")) {

            var data = {
                usuario: usuario,
                password: pass
            };

            $.ajax({
                "url": "/login",
                "type": 'POST',
                "contentType": dataTypeJson,
                "dataType": "json",
                "data": JSON.stringify(data),
                "beforeSend": function () { showLoadingDiv(); },
                "complete": function (resp) {
                    if (resp.status == 200) {
                        hideLoadingDiv();
                        //showMessageExtra("Credenciales correctas", "Login", 500);
                        window.location.replace("/")
                    }
                    else if (resp.status == 401) {
                        hideLoadingDiv();
                        showMessage("La contraseña ingresada es incorrecta", "Login");
                        $("#password").val("");
                    }
                    else if (resp.status == 404) {
                        hideLoadingDiv();
                        showMessage("Usuario ingresado no encontrado", "Login");
                        $("#password").val("");
                    }
                    else if (resp.status == 500) {
                        hideLoadingDiv();
                        showMessage("Ha ocurrido un error inesperado. Por favor contactar al administrador del sistema", "Login");
                        $("#password").val("");
                    }
                }
            });
        }
        else {
            showMessage("Debe completar el usuario y contraseña", "Login");
        }
    });
}