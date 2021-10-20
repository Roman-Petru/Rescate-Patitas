var dataTypeJson = 'application/json; charset=utf-8';

var styleDataTable = "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
    "<'row'<'col-sm-12'tr>>" +
    "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>";


$(document).ready(() => {
    $(".btnSalir").click(() => {
        showLoadingDiv();
    });
});


var idioma_espanol = {
    "sProcessing": "Procesando...",
    "sLengthMenu": "Mostrar _MENU_ registros",
    "sZeroRecords": "No se encontraron resultados",
    "sEmptyTable": "Ningún dato disponible en esta tabla",
    "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
    "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
    "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
    "sInfoPostFix": "",
    "sSearch": "Buscar:",
    "sUrl": "",
    "sInfoThousands": ",",
    "sLoadingRecords": "Cargando...",
    "oPaginate": {
        "sFirst": "Primero",
        "sLast": "Ãšltimo",
        "sNext": "Siguiente",
        "sPrevious": "Anterior"
    },
    "oAria": {
        "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
    }
}

// $.datepicker.regional['es'] = {
//     closeText: 'Cerrar',
//     prevText: ' nextText: <Sig>',
//     currentText: 'Hoy',
//     monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
//         'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
//     ],
//     monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
//         'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'
//     ],
//     dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
//     dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié;', 'Juv', 'Vie', 'Sáb'],
//     dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá'],
//     weekHeader: 'Sm',
//     dateFormat: 'yy/mm/dd',
//     firstDay: 1,
//     isRTL: false,
//     showMonthAfterYear: false,
//     changeYear: true,
//     yearRange: "2010:" + new Date().getFullYear().toString(),
//     yearSuffix: ''
// };


var formToJson = function (form) {
    var o = {};
    var a = form.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

var showLoadingDiv = function () {
    $("#loading-div-background").show();
}

var hideLoadingDiv = function () {
    $("#loading-div-background").hide();
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('/');
}

var showMessage = (message, from) => {

    var msg = "#error" + from;
    var container = ".errMessage" + from;
    $(msg).html(message);
    $(container).show(500);
    setTimeout(() => {
        $(container).hide(500);
    }, 3000);
};

var showMessageExtra = (message, from, timeout) => {
    var msg = "#msg" + from;
    var container = ".okMessage" + from;
    $(msg).html(message);
    $(container).fadeIn(500);
    if (timeout) {
        setTimeout(() => {
            $(container).fadeOut(500);
        }, 3000);
    }
};

var hideMessage = (container) => {
    $(container).hide(500);
};

function setInputFilter(textbox, inputFilter) {
    ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function (event) {
        textbox.addEventListener(event, function () {
            if (inputFilter(this.value)) {
                this.oldValue = this.value;
                this.oldSelectionStart = this.selectionStart;
                this.oldSelectionEnd = this.selectionEnd;
            } else if (this.hasOwnProperty("oldValue")) {
                this.value = this.oldValue;
                this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
            } else {
                this.value = "";
            }
        });
    });
}


function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min; //The maximum is exclusive and the minimum is inclusive
}

function getTime() {
    var dt = new Date();
    return dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getSeconds();
};

function getHourMin() {
    var dt = new Date();
    return dt.getHours() + ":" + dt.getMinutes();
};

function getDate() {
    var dt = new Date();
    return dt.getFullYear() + "/" + (dt.getMonth() + 1) + "/" + dt.getDate();
};

function getDateTime() {
    var dt = new Date();
    return dt.getFullYear() + "/" + (dt.getMonth() + 1) + "/" + dt.getDate() + " " + dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getUTCSeconds();
};

function getDateHourMin() {
    var dt = new Date();
    return dt.getFullYear() + "/" + (dt.getMonth() + 1) + "/" + dt.getDate() + " " + dt.getHours() + ":" + dt.getMinutes() + ":" + dt.getUTCSeconds();
};

function getDateAddHourMin(value) {
    var dt = new Date();
    return dt.getFullYear() + "/" + (dt.getMonth() + 1) + "/" + dt.getDate() + " " + (dt.getHours() + value) + ":" + dt.getMinutes() + ":" + dt.getUTCSeconds();
};

function getDTAddHour(dp) {
    var auxMonth = (dp.getMonth() + 1) < 10 ? "0" + (dp.getMonth() + 1) : (dp.getMonth() + 1);
    var auxDate = dp.getDate() < 10 ? "0" + dp.getDate() : dp.getDate();
    var auxHour = dp.getHours() < 10 ? "0" + (dp.getHours() + 1) : (dp.getHours() + 1);
    var auxMin = dp.getMinutes() < 10 ? "0" + dp.getMinutes() : dp.getMinutes();
    return dp.getFullYear() + "/" + auxMonth + "/" + auxDate + " " + auxHour + ":" + auxMin;
}

Date.prototype.addHours = function (h) {
    this.setHours(this.getHours() + h);
    return this;
}


String.prototype.replaceAll = function (search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};


//-------------------------------Hora Actual----------------------------------

var DateTimeNow = function () {
    var d = new Date,
        dformat = [d.getFullYear(),
                (d.getMonth() + 1).padLeft(),
                d.getDate().padLeft()].join('/') +
            ' ' +
            [d.getHours().padLeft(),
                d.getMinutes().padLeft()].join(':');
    return dformat;
}

var DateTimeNowArgentina = function () {
    var d = new Date,
        dformat = [d.getDate().padLeft(),
                (d.getMonth() + 1).padLeft(),
                d.getFullYear()].join('/') +
            ' ' +
            [d.getHours().padLeft(),
                d.getMinutes().padLeft()].join(':');
    return dformat;
}


var DateTimeNowExcel = function () {
    var d = new Date,
        dformat = [d.getDate().padLeft(),
                (d.getMonth() + 1).padLeft(),
                d.getFullYear()].join('/') +
            ' ' +
            [d.getHours().padLeft(),
                d.getMinutes().padLeft()].join(':');
    return dformat.replaceAll("/", "").replaceAll(" ", "").replaceAll(":", "");
}

var DateNow = function () {
    var today = new Date();
    var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    return date;
};

//-------------------------Exportacion Excel-------------------------------


Number.prototype.padLeft = function (base, chr) {
    var len = (String(base || 10).length - String(this).length) + 1;
    return len > 0 ? new Array(len).join(chr || '0') + this : this;
}

//Excel Addrow
function Addrow(index, data, sheet) {
    var row = sheet.createElement('row');
    row.setAttribute("r", index);
    for (i = 0; i < data.length; i++) {
        var key = data[i].key;
        var value = data[i].value;

        var c = sheet.createElement('c');
        c.setAttribute("t", "inlineStr");
        c.setAttribute("s", "2");
        c.setAttribute("r", key + index);

        var is = sheet.createElement('is');
        var t = sheet.createElement('t');
        var text = sheet.createTextNode(value)

        t.appendChild(text);
        is.appendChild(t);
        c.appendChild(is);

        row.appendChild(c);
    }

    return row;
}


//-------------------------DATEPICKER-------------------------------


var initDateTimePicker = () => {

    $.datetimepicker.setLocale('es');

    desde = $('#filtro_fechaDesde').datetimepicker({
        //format: 'Y/m/d H:i',
        format: 'Y/m/d',
        onShow: function (ct) {
            this.setOptions({
                //maxDate: jQuery('#fechaFin').val() ? jQuery('#fechaFin').val() : false,
                highlightedPeriods: [jQuery('#filtro_fechaDesde').val() + "," + jQuery('#filtro_fechaHasta').val()]
            })
        },
        onChangeDateTime: function (inicio, $input) {

            var fin = $('#filtro_fechaHasta').datetimepicker('getValue');

            var initDT = Date.parse(inicio.toLocaleString());
            var endDT = Date.parse(fin.toLocaleString());
            var newDate = new Date(inicio.getTime()).addHours(1);

            if (endDT < initDT) {
                $("#filtro_fechaHasta").datetimepicker({
                    value: newDate
                });
            }

        },
        onSelectTime: function (ct, $i) {
            var time = new Date();
            var selection = Date.parse(ct.toLocaleString());
            var actualTime = Date.parse(time.toLocaleString());
        },
        defaultDate: getDate(),
        value: getDate() + " 00:00",
        roundTime: 'ceil',
        defaultTime: getHourMin(),
        step: 15,
        timepicker: false
    });

    hasta = $('#filtro_fechaHasta').datetimepicker({
        //format: 'Y/m/d H:i',
        format: 'Y/m/d',
        onShow: function (ct) {
            this.setOptions({
                minDate: jQuery('#fechaInicio').val() ? jQuery('#filtro_fechaDesde').val() : false,
                highlightedPeriods: [jQuery('#filtro_fechaDesde').val() + "," + jQuery('#filtro_fechaHasta').val()]
            })
        },
        onChangeDateTime: function (fin, $input) {

            var inicio = $('#filtro_fechaDesde').datetimepicker('getValue');
            var newDate = new Date(inicio.getTime()).addHours(1);

            var initDT = Date.parse(inicio.toLocaleString());
            var endDT = Date.parse(fin.toLocaleString());

            if (endDT < initDT) {
                $("#filtro_fechaHasta").datetimepicker({
                    value: newDate
                });
            }

        },

        minDate: getDate(),
        value: getDate() + " 23:59",
        roundTime: 'ceil',
        defaultTime: "23:59",
        step: 15,
        timepicker: false
    });

};