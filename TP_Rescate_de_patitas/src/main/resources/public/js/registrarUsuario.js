$(document).ready(() => {
    btnListener();
});

var btnListener = () => {
    $("#btnRegistrarUsuario").on("click", (e) => {
        e.preventDefault();
        var usuario = $("#usuario").val().trim();
        var pass = $("#password").val().trim();
        var nombre = $("#nombre").val().trim();
        var apellido = $("#apellido").val().trim();
        var dni = $("#dni").val().trim();

        if ((usuario != "") && (pass != "") && (nombre != "") && (apellido != "") && (dni != "")) {

            if (validarPassword("#password")){

                var data = {
                    usuario: usuario,
                    password: pass,
                    documento: dni,
                    nombre: nombre,
                    apellido: apellido
                };

                $.ajax({
                    "url": "/registrar",
                    "type": 'POST',
                    "contentType": dataTypeJson,
                    "dataType": "json",
                    "data": JSON.stringify(data),
                    "beforeSend": function () { showLoadingDiv(); },
                    "complete": function (resp) {
                        if (resp.status == 200) {
                            hideLoadingDiv();
                            $("#usuario").val("");
                            $("#password").val("");
                            $("#nombre").val("");
                            $("#apellido").val("");
                            $("#dni").val("");
                            window.location.replace("/");

                        }
                        else if (resp.status == 401) {
                            hideLoadingDiv();
                            showMessage("El DNI ingresado ya posee un usuario asignado", "RegistroUsuario");
                            $("#usuario").val("");
                            $("#password").val("");
                        }
                        else if (resp.status == 500) {
                            hideLoadingDiv();
                            showMessage("Ha ocurrido un error inesperado. Por favor contactar al administrador del sistema", "RegistroUsuario");
                            $("#usuario").val("");
                            $("#password").val("");
                        }
                    }
                });
            }
        }
        else {
            showMessage("Debe completar todos los campos", "RegistroUsuario");
        }
    });
}


var validarPassword = function (id_password) {
    var esValido = true;

    var valor = $(id_password).val();
    var alMenosUnDigito = /^.*(?=.*\d).*$/;  // should contain at least one digit
    var alMenosUnaMayuscula = /^.*(?=.*[A-Z]).*$/; // should contain at least one upper case
    var alMenosUnaMinuscula = /^.*(?=.*[a-z]).*$/; // should contain at least one lower case
    var alMenosUnCaracterEspecial = /[-._!"`'#%&,:;<>=@{}~\$\(\)\*\+\/\\\?\[\]\^\|]+/;

    if ((valor).length < '8') {
        esValido = false;
        error = "La contraseña debe contener al menos 8 caracteres";
    }
    else if (!alMenosUnDigito.test(valor)) {
        esValido = false;
        error = "La contraseña debe contener al menos 1 número";
    }
    else if (!alMenosUnaMayuscula.test(valor)) {
        esValido = false;
        error = "La contraseña debe contener al menos un letra mayúscula";
    }
    else if (!alMenosUnaMinuscula.test(valor)) {
        esValido = false;
        error = "La contraseña debe contener al menos un letra minúscula";
    }
    else if (!alMenosUnCaracterEspecial.test(valor)) {
        esValido = false;
        error = "La contraseña debe contener al menos un caracter especial";
    }

    if (!esValido) {
        showMessage(error, "RegistroUsuario");
        return false;
    }
    return true;
}



$(document).on("keyup",".validarUsuario",function(e) {
    if (e.keyCode == 32) {  // in case of space...
        if (this.value.indexOf(' ') != -1) { // if the space is inside string
            this.value = this.value.replace(/\s+/g, "");
        } else {  // for type = email: the space is at the end: add a final char.....
            this.value = this.value + '';
        }
    }
});


