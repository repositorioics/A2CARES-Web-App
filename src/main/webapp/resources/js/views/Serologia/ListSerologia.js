/**
 * Created by ICS on 18/10/2020.
 */
var EnviarSerologiasForm = function(){

    return {
        init: function(urls){
            var table = $("#Lista_Muestra").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false,
                        "searchable": false
                    },{
                        targets: [1,2,3,4,5,6,7,8,9],
                        className: 'text-center'
                    }
                ]
            });
            CargarDatos();
            function CargarDatos(){
                $.getJSON(urls.MxNoEnviadasUrl, function(data){
                    var len = data.length;
                    if(data==0){
                        toastr.warning("No se encontraron registro","ADVERTENCIA!",{timeOut:6000});
                    }else{
                        for ( var i = 0; i < len; i++) {
                            var d = new Date(data[i].fecha);
                            var partsUrl =  urls.editUrl+ '/'+data[i].idSerologia;
                            var codParticipante = data[i].participante;
                            var datestring = ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                            var fregistro = new Date(data[i].recordDate);
                            var datestring2 = ("0" + fregistro.getDate()).slice(-2) + "/" + ("0"+(fregistro.getMonth()+1)).slice(-2) + "/" + fregistro.getFullYear();
                            var botonUpdate ='<a id="btnEditar" class="btn btn-warning btn-sm btnEditar" data-toggle="tooltip" data-placement="bottom" title="Editar" href='+ partsUrl + '><i class="fa fa-edit" aria-hidden="true"></i></a>';
                            var btnPasive  = '<button type="button" id="btnPasive" data-toggle="tooltip" data-placement="bottom" title="Eliminar"  class="btn btn-danger btn-sm btnPasive" data-id='+ data[i].idSerologia + '> <i class="fas fa-trash" aria-hidden="true"></i>  </button>';
                            table.row.add([
                                data[i].idSerologia,
                                datestring,
                                codParticipante,
                                data[i].volumen,
                                (data[i].pasive=='0')?"<span class='badge badge-danger'> <i class='fas fa-times' aria-hidden='true'></i></span>":"<span class='badge badge-success'><i class='fas fa-check' aria-hidden='true'></i></span>",
                                data[i].descripcion,
                                data[i].codigo_casa,
                                data[i].observacion,
                                botonUpdate, btnPasive
                            ]).draw(false);
                        }
                    }
                });
            }//fin funccion


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
                    desde:{
                        required: true
                    },
                    hasta:{
                        required: true
                    },
                    desde: {required: function () {
                        return $('#desde').val().length > 0;
                    }},
                    hasta: {required: function () {
                        return $('#hasta').val().length > 0;
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
                        crearEnvio();
                    }
                }
            });

            function crearEnvio(){
                debugger;
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
                    $.post(urls.sendAllSerologiasUrl, form1.serialize(), function (data) {
                        debugger;
                        console.log(data);
                        if (data.mensaje != null) {
                            swal("INFORMACIÓN",data.mensaje,"info");
                        }
                        window.setTimeout(function () {
                            table.clear().draw( false );
                            CargarDatos();
                            location.reload();
                        }, 1300);
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        //toastr.error("Error 500 Internal Server", 'EROOR!',{timeOut:6000});
                        swal("Error 500!", "Interno del Servidor!", "error");
                    });
                });
            }


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
                $.post( urls.closeUrl, form2.serialize(), function( data ){
                    registro = JSON.parse(data);
                    console.log(registro);
                    if (registro.idSerologia === undefined) {
                        toastr.error(data,"Error",{timeOut: 5000});
                    }
                    else {
                        $("#basic").modal('hide');
                        toastr.success(urls.successMessage);
                        window.setTimeout(function () {
                            window.location.href = urls.listSerologiaUrl;
                        }, 1500);
                    }
                },'text' )
                    .fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error("error:" + errorThrown);
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