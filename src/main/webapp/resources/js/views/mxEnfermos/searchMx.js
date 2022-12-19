/**
 * Created by ICS on 18/10/2020.
 */
var SearchMxEnfermosFormVal = function(){
    return {
        init: function(urls){

            var total_enf = 0;
            var total_ini = 0;
            var total_con = 0;

            var table = $("#Lista_Muestra").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "bDestroy": true,
                "responsive": true,
                "columnDefs": [
                    {
                      //  "targets": [0],
                       // "visible": false,
                        "searchable": false
                    },{
                         targets: [1,2,3,4,5,6,7,8,9,10,11],
                        className: 'text-center'
                    }
                ]
            });

            function CargarDatos() {
                if ($("#desde").val() === "" && $("#hasta").val() === "" && $("#participante").val() === "" && $("#muestra").val() === "") {
                    Swal.fire({
                        title: "¡Serologia!",
                        html: "Favor seleccionar al menos un criterio de búsqueda:<br>" +
                            "<ul>" +
                            "<li>Rango de fecha de recepción</li>"+
                            "<li>Código de participante</li>"+
                            "<li>Código de muestra</li>"+
                            "</ul>",
                        icon: 'info',
                        timer: 4000
                    });
                } else {
                    table.clear().draw(false);
                    $.getJSON(urls.searchUrl,
                        form1.serialize(),
                        function (data) {
                            var len = data.length;
                            if (data == 0) {
                                Swal.fire({
                                    title: "¡Mx Enfermos!",
                                    text: "No se encontraron registro.",
                                    icon: "info",
                                    timer: 4000
                                });
                            } else {
                                total_ini = 0;
                                total_con = 0;
                                for (var i = 0; i < len; i++) {
                                    var partsUrl = urls.editUrl ;
                                    //var printUrl = 'http://localhost:13001/print?barcodes=' + data[i].codigoBarra;
                                    var botonUpdate = '<a id="btnEditar" class="btn rounded-pill btn-outline-warning btn-sm btnEditar" data-toggle="tooltip" data-placement="bottom" title="Editar" href=' + partsUrl + '><i class="fa fa-edit" aria-hidden="true"></i></a> ';
                                    var botonPrint  = '<button type="button" id="btnPrint" data-toggle="tooltip" data-placement="bottom" title="Imprimir"  class="btn rounded-pill btn-outline-primary btn-sm btnPrint" data-id="'+ data[i].codigoBarra + '"> <i class="fas fa-print" aria-hidden="true"></i>  </button>';
                                    if (data[i].consulta === 'Inicial') {
                                        total_ini = total_ini + 1;
                                    }
                                    if (data[i].consulta === 'Convaleciente') {
                                        total_con = total_con + 1;
                                    }
                                    var enviado = "<span class='badge badge-danger'><i class='fas fa-thumbs-down'></i></span>";
                                    if (data[i].enviado === '1') {
                                        enviado = "<span class='badge badge-success'><i class='fas fa-thumbs-up'></i></span>";
                                    }
                                    table.row.add([
                                        data[i].participante,
                                        data[i].fechaMuestra,
                                        data[i].horaMuestra,
                                        data[i].tipoTubo,
                                        data[i].volumen,
                                        data[i].fis,
                                        data[i].fif,
                                        data[i].categoria,
                                        data[i].consulta,
                                        data[i].tipoMuestra,
                                        data[i].recordUser,
                                        data[i].observacion




                                    ]).draw(false);
                                }

                                $('#total_emfermeria').val("Total Muestras: " + i);
                                $('#total_inicial').val("Total Inicial: " +total_ini);
                                $('#total_convaleciente').val("Total Convaleciente: " +total_con);
                            }
                        });


                }
            }//fin funccion
            var tablem = $("#Lista_Muestra_medicos").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "bDestroy": true,
                "responsive": true,
                "columnDefs": [
                    {
                        //  "targets": [0],
                        // "visible": false,
                        "searchable": false
                    },{
                        targets: [1,2,3,4,5,6,7,8,9],
                        className: 'text-center'
                    }
                ]
            });
            function CargarDatos1() {
                if ($("#desde").val() === "" && $("#hasta").val() === "" && $("#participante").val() === "" && $("#muestra").val() === "") {
                    Swal.fire({
                        title: "¡Serologia!",
                        html: "Favor seleccionar al menos un criterio de búsqueda:<br>" +
                            "<ul>" +
                            "<li>Rango de fecha de recepción</li>"+
                            "<li>Código de participante</li>"+
                            "<li>Código de muestra</li>"+
                            "</ul>",
                        icon: 'info',
                        timer: 4000
                    });
                } else {
                    tablem.clear().draw(false);
                    $.getJSON(urls.getmedicos,
                        form1.serialize(),
                        function (data) {
                            var len = data.length;
                            if (data == 0) {
                                Swal.fire({
                                    title: "¡Mx Enfermos!",
                                    text: "No se encontraron registro.",
                                    icon: "info",
                                    timer: 4000
                                });
                            } else {
                                total_ini = 0;
                                total_con = 0;
                                for (var i = 0; i < len; i++) {

                                    var partsUrl = urls.editUrl ;
                                    //var printUrl = 'http://localhost:13001/print?barcodes=' + data[i].codigoBarra;
                                    var botonUpdate = '<a id="btnEditar" class="btn rounded-pill btn-outline-warning btn-sm btnEditar" data-toggle="tooltip" data-placement="bottom" title="Editar" href=' + partsUrl + '><i class="fa fa-edit" aria-hidden="true"></i></a> ';
                                    var botonPrint  = '<button type="button" id="btnPrint" data-toggle="tooltip" data-placement="bottom" title="Imprimir"  class="btn rounded-pill btn-outline-primary btn-sm btnPrint" data-id="'+ data[i].codigoBarra + '"> <i class="fas fa-print" aria-hidden="true"></i>  </button>';
                                    if (data[i].consulta === 'Inicial') {
                                        total_ini = total_ini + 1;

                                    }
                                    if (data[i].consulta === 'Convaleciente') {
                                        total_con = total_con + 1;
                                    }
                                    var enviado = "<span class='badge badge-danger'><i class='fas fa-thumbs-down'></i></span>";
                                    if (data[i].enviado === '1') {
                                        enviado = "<span class='badge badge-success'><i class='fas fa-thumbs-up'></i></span>";
                                    }

                                    /**/
                                    //obtenemos el valor insertado a buscar
                                  /*  buscar = data[i].fis;

                                    //utilizamos esta variable solo de ayuda y mostrar que se encontro
                                    encontradoResultado=false;
                                    var fis1;
                                    var codEnferm;
                                    //realizamos el recorrido solo por las celdas que contienen el código, que es la primera
                                    $("#Lista_Muestra tr").find('td:eq(5)').each(function () {

                                        //obtenemos el codigo de la celda
                                         fis1 = $(this).html();

                                        /* Mostrar el valor de la celda 2 */


                                        //comparamos para ver si el código es igual a la busqueda
                                     /*   if(fis1 === buscar){
                                           // alert("entro" + fis1);
                                            $("#Lista_Muestra tr").find('td:eq(0)').each(function () {

                                            codEnferm = $(this).html();

                                          //      alert("entro" + codEnferm);
                                               // alert("Participante: "+ codEnferm +" -  Fis de Enfermeria "+ fis1 +" Coincide con Médicos " + buscar);

                                            } );
                                         //

                                          /*  $("#enf_fis").click(function(){
                                                document.getElementById('enf_fis').style.backgroundColor = "blue"

                                            });*/



                                 /*       }
                                    })
                                    if (encontradoResultado === false){
                                      //  alert("Participante: "+ data[i].participante +" -  No existe en Enfermeria " );
                                     //   alert("Participante: "+ data[i].participante +" -  Fis de Enfermeria "+ fis1 +" NO Coincide con Médicos " + buscar);
                                    }
                                    if (encontradoResultado === true){
                                    //    alert("Participante: "+ data[i].participante +" -  Fis de Enfermeria "+ fis1 +" Coincide con Médicos " + buscar);
                                    }






                                  //      $('#enf_fis').css('background', 'red');
                                //    $('#enf_fis').trigger('change');
                                    /**/

                                    tablem.row.add([
                                        data[i].participante,
                                        data[i].fechaOrden,
                                        data[i].tipoMuestra,
                                        data[i].fis,
                                        data[i].fif,
                                        data[i].categoria,
                                        data[i].consulta,
                                        data[i].tipoOrden,
                                        data[i].recordUser,
                                        data[i].observacion



                                    ]).draw(false);

                                }
                                $('#total_medicos').val("Total Muestras: " + len);
                                $('#total_inicial_medicos').val("Total Inicial: " +total_ini);
                                $('#total_convaleciente_medicos').val("Total Convaleciente: " +total_con);
                            }
                        });


                }
                compararTodo();
            }//fin funccion

            function compararTodo() {
                var count = 0;
                $('.table').each(function () {
                    $(this).find('tablem').each(function () {
                        $(this).find('tr').each(function () {
                            $(this).show();
                            $(this).css('background-color', '');
                            $(this).find('td').css('color', 'black');
                            $(this).find('td').each(function (index, value) {
                                $(this).css('background-color', '');
                                $(this).show();
                            })
                            if (count == 1) {
                                $(this).css('background-color', '#ededed');
                                count = 0;
                            } else {
                                count++;
                                $(this).css('background-color', 'white');
                            }
                        })
                    })
                });
            }

            var table2 = $("#Lista_Muestra_recepcion").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "bDestroy": true,
                "responsive": true,
                "columnDefs": [
                    {
                        //  "targets": [0],
                        // "visible": false,
                        "searchable": false
                    },{
                        targets: [1,2,3,4,5,6,7,8,9],
                        className: 'text-center'
                    }
                ]
            });
            function CargarDatos2() {
                if ($("#desde").val() === "" && $("#hasta").val() === "" && $("#participante").val() === "" && $("#muestra").val() === "") {
                    Swal.fire({
                        title: "¡Serologia!",
                        html: "Favor seleccionar al menos un criterio de búsqueda:<br>" +
                            "<ul>" +
                            "<li>Rango de fecha de recepción</li>"+
                            "<li>Código de participante</li>"+
                            "<li>Código de muestra</li>"+
                            "</ul>",
                        icon: 'info',
                        timer: 4000
                    });
                } else {
                    table2.clear().draw(false);
                    $.getJSON(urls.getrecepcion,
                        form1.serialize(),
                        function (data) {
                            var len = data.length;
                            if (data == 0) {
                                Swal.fire({
                                    title: "¡Mx Enfermos!",
                                    text: "No se encontraron registro.",
                                    icon: "info",
                                    timer: 4000
                                });
                            } else {
                                total_ini = 0;
                                total_con = 0;
                                for (var i = 0; i < len; i++) {

                                    var partsUrl = urls.editUrl ;
                                    //var printUrl = 'http://localhost:13001/print?barcodes=' + data[i].codigoBarra;
                                    var botonUpdate = '<a id="btnEditar" class="btn rounded-pill btn-outline-warning btn-sm btnEditar" data-toggle="tooltip" data-placement="bottom" title="Editar" href=' + partsUrl + '><i class="fa fa-edit" aria-hidden="true"></i></a> ';
                                    var botonPrint  = '<button type="button" id="btnPrint" data-toggle="tooltip" data-placement="bottom" title="Imprimir"  class="btn rounded-pill btn-outline-primary btn-sm btnPrint" data-id="'+ data[i].codigoBarra + '"> <i class="fas fa-print" aria-hidden="true"></i>  </button>';
                                    if (data[i].consulta === 'Inicial') {
                                        total_ini = total_ini + 1;

                                    }
                                    if (data[i].consulta === 'Convaleciente') {
                                        total_con = total_con + 1;
                                    }
                                    var enviado = "<span class='badge badge-danger'><i class='fas fa-thumbs-down'></i></span>";
                                    if (data[i].enviado === '1') {
                                        enviado = "<span class='badge badge-success'><i class='fas fa-thumbs-up'></i></span>";
                                    }
                                    table2.row.add([

                                        data[i].participante,
                                        data[i].fechaRecepcion,
                                        data[i].volumen,
                                        data[i].observacion,
                                        data[i].fis,
                                        data[i].fif,
                                        data[i].categoria,
                                        data[i].consulta,
                                        data[i].tipoMuestra,
                                        enviado

                                    ]).draw(false);
                                }
                                $('#total_recepcion').val("Total Muestras: " + len);
                                $('#total_inicial_recepcion').val("Total Inicial: " +total_ini);
                                $('#total_convaleciente_recepcion').val("Total Convaleciente: " +total_con);

                            }
                        });


                }
            }//fin funccion
            var form1 = $('#envio-allserologia-form');
            var $validator = form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    desde: {required: function () {
                        return $('#hasta').val().length > 0;
                    }},
                    hasta: {required: function () {
                        return $('#desde').val().length > 0;
                    }}
                },errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    }else if ( element.prop( 'type' ) === 'text' ){
                        error.insertAfter(element.parent('.input-group'));
                    } else {
                        error.insertAfter( element ); //cuando no es input-group
                    }
                },highlight: function ( element, errorClass, validClass ) {
                    $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                    $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
                },unhighlight: function (element, errorClass, validClass) {
                    $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                    $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
                },submitHandler: function (form) {
                    var $validarForm = form1.valid();
                    if (!$validarForm) {
                        $validator.focusInvalid();
                        return false;
                    } else {
                        CargarDatos();
                        CargarDatos1();
                        CargarDatos2();
                    }
                }
            });

            $("#Lista_Muestra tbody").on("click", ".btnPrint",function(){
                var id = $(this).data('id');
                imprimirEtiquetas(id);
            });
            /**************************/
        }// fin init
    }
}();
