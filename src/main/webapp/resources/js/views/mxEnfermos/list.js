/**
 * Created by ICS on 18/10/2020.
 */
var ListaMxEnfermosForm = function(){
    return {
        init: function(urls){
            var table = $("#Lista_Muestra").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "bDestroy": true,
                "responsive": true,
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false,
                        "searchable": false
                    },{
                        targets: [1,2,3,4,5,6,7,8,9,10,11],
                        className: 'text-center'
                    }
                ]
            });
            CargarDatos();

            function CargarDatos() {
                table.clear().draw(false);
                $.getJSON(urls.MxNoEnviadasUrl, function (data) {
                    var len = data.length;
                    if (data == 0) {
                        swal({
                            title: "¡Serologia!",
                            text: "No se encontraron registro.",
                            type: "info",
                            timer: 4000
                        });
                    } else {
                        for (var i = 0; i < len; i++) {
                            var partsUrl = urls.editUrl + '/' + data[i].idRecepcion+'/1';
                            var botonUpdate = '<a id="btnEditar" class="btn btn-warning btn-sm btnEditar" data-toggle="tooltip" data-placement="bottom" title="Editar" href=' + partsUrl + '><i class="fa fa-edit" aria-hidden="true"></i></a> ';
                            var btnPasive = '<button type="button" id="btnPasive" data-toggle="tooltip" data-placement="bottom" title="Eliminar"  class="btn btn-danger btn-sm btnPasive" data-id=' + data[i].idRecepcion + '> <i class="fas fa-trash" aria-hidden="true"></i>  </button> ';
                            table.row.add([
                                data[i].idRecepcion,
                                data[i].participante,
                                data[i].fechaRecepcion,
                                data[i].volumen,
                                data[i].observacion,
                                data[i].fis,
                                data[i].fif,
                                data[i].categoria,
                                data[i].consulta,
                                data[i].tipoMuestra,
                                botonUpdate , btnPasive
                            ]).draw(false);
                        }
                    }
                });
            }//fin funccion

            $('#temperatura').on('change', function() {
                if(isNaN(this.value)){
                    this.value = "";
                }else{
                    this.value = parseFloat(this.value).toFixed(2);
                }
            });


            var form1 = $('#envio-allserologia-form');
            var $validator = form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    horaEnvio:{
                        required:true
                    },
                    numenvio: {
                        required: true
                    },
                    desde: {required: function () {
                        return $('#hasta').val().length > 0;
                    }},
                    hasta: {required: function () {
                        return $('#desde').val().length > 0;
                    }},
                    temperatura:{
                        required: true,
                        number: true
                    },
                    sitio:{
                        required: true
                    }
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
                        crearEnvio();
                    }
                }
            });

            function crearEnvio(){
                swal({
                    title: "Deseas enviar las Muestras...",
                    text: "para generar el Reporte?",
                    type: "info",
                    showCancelButton: true,
                    cancelButtonClass:"btn-warning",
                    cancelButtonText: "Cancelar!",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true,
                    confirmButtonText: "Si, enviar!"
                }, function () {
                    $.post(urls.envioUrl, form1.serialize(), function (data) {
                        console.log(data);
                        if (data.mensaje != null) {
                            swal({
                                title: "¡INFORMACIÓN!",
                                text: data.mensaje,
                                icon: "error",
                                timer: 5000
                            });
                        } else {
                            swal({
                                title: "¡INFORMACIÓN!",
                                text: data.total,
                                icon: "info",
                                timer: 5000
                            });
                         //   imprimirEtiquetas(data.etiquetas);
                        }
                        CargarDatos();
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        swal({
                            title: "Error 500",
                            text: "Sucedió un error. Favor informar al administrador",
                            icon: "error",
                            timer: 5000
                        });
                    });
                });
            }
/*
            function imprimirEtiquetas(strBarCodes){
                $.getJSON("http://localhost:13001/print", {
                    barcodes: unicodeEscape(strBarCodes),
                    copias: 1,
                    ajax:'false'
                }, function (data) {
                    console.log(data);
                }).fail(function (jqXHR) {
                    console.log(jqXHR);
                });
            }*/

            /**************************/
            $("#close-form").validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    message_razon: {
                        required: true
                    }
                }, errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        //error.insertAfter( element ); //cuando no es input-group
                        error.insertAfter(element.parent('.input-group'));
                    }
                },
                highlight: function ( element, errorClass, validClass ) {
                    $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                    $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
                },
                unhighlight: function (element, errorClass, validClass) {
                    $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                    $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
                },
                submitHandler: function (form) {
                    serologiaPasive();
                }
            });

            function serologiaPasive(){
                var form2 =$("#close-form");
                $.post( urls.deleteUrl, form2.serialize(), function( data ){
                    registro = JSON.parse(data);
                    if (registro.idRecepcion ===undefined) {
                        swal({
                            title: "¡INFORMACIÓN!",
                            text: data,
                            type: "error",
                            timer: 5000
                        });
                    }
                    else {
                        $("#basic").modal('hide');
                        swal({
                            title: "¡Buen trabajo!!",
                            text: urls.successMessage,
                            type: "success",
                            timer: 3000
                        });
                        CargarDatos();
                    }
                },'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        swal({
                            title: "¡INFORMACIÓN!",
                            text: errorThrown,
                            type: "error",
                            timer: 5000
                        });
                    });
            }
            $("#Lista_Muestra tbody").on("click", ".btnPasive",function(){
                var id = $(this).data('id');
                $("#idAccion").val(id);
                $("#basic").modal('show');
            });
            /**************************/
        }// fin init
    }
}();
