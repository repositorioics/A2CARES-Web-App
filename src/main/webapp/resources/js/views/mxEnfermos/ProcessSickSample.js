/**
 * Created by ICS on 03/02/2022.
 */
var ProcessSickSample = function(){
    return{
        init: function(urls){
            $("#select-participante-form").validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules:{
                    parametro: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        maxlength: 6,
                        required: true
                    }
                },
                errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        error.insertAfter( element );
                    }
                    if (element.attr("name") == "parametro") {
                        error.insertAfter("#gendererror");
                    } else {
                        error.insertAfter(element);
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
                    searchParticipante();
                    $("#volumen").focus();
                }
            });

            function searchParticipante(){
                $.getJSON(urls.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    var len = data.length;
                    if(data.mensaje !=undefined){
                        swal({
                            title: "¡ERROR!",
                            text: data.mensaje,
                            icon: "error",
                            timer: 2000
                        });
                        LimpiarParticipante();
                    }
                    if(len==0){
                        swal({
                            title: "¡ADVERTENCIA!",
                            text: "Código no encontrado!",
                            icon: "info",
                            timer: 2000
                        });
                        $("#parametro").focus().val("");
                        LimpiarParticipante();
                    } else{
                        if(data.estado ==1){
                            swal({
                                title: "¡ADVERTENCIA!",
                                text: "Participante Retirado",
                                icon: "error",
                                timer: 2000
                            });
                            LimpiarParticipante();
                        }else {

                            $("#codigoParticipante").val(data.codigo);
                            $("#nombreCompleto").val(data.nombreCompleto);
                            $("#casa").val(data.casa);
                            $("#fechaNac").val(data.fechaNac);
                            $("#edad_year").val(data.edadA);
                            $("#edad_meses").val(data.edadM);
                            $("#edad_dias").val(data.edadD);
                            $("#evento").val(data.evento);
                            $("#ultima_consulta").val(data.ultima_consulta);

                            $("#volumen").val("");

                            var evento_letra = $("#evento").val();
                            var evento_ascii = evento_letra.charCodeAt(0);
                            var evento_ascii1 = evento_letra.charCodeAt(0);

                            evento_ascii = evento_ascii + 1;
                            evento_ascii1 = evento_ascii1 - 1;

                            if (!(data.isNull)) {
                                if ((data.consulta === "1" )) {
                                $("#fif").val(data.fif);
                                $("#fis").val(data.fis);

                                // $("#categoria option[value="+ data.categoria +"]").attr("selected",true);
                                $("#categoria").val(data.categoria);
                                    $('#categoria').trigger('change');
                                swal({
                                    title: "¡Alerta A2CARES!",
                                    text: "El participante " + $("#codigoParticipante").val() + " tiene pendiente Muestra Convaleciente, está en su evento: " + $("#evento").val() + ", se cargarán FIS y FIF de muestra INICIAL de tabla de Recepción.",
                                    icon: "warning"
                                });
                                    $('#faseMuestra').val("2");
                                    $('#faseMuestra').trigger('change');

                                    $('#tipoConsulta').val("3");
                                    $('#tipoConsulta').trigger('change');

                                    $("#categoria").val(data.categoria);
                                    $('#categoria').trigger('change');

                                } else {
                                if ((data.consulta === "3")) {

                                    swal({
                                        title: "¡Alerta A2CARES!",
                                        text: "El participante " + $("#codigoParticipante").val() + " tiene completo su evento " + (String.fromCharCode(evento_ascii1)) + " se le puede recepcionar INICIAL del siguiente Evento: " + $("#evento").val(),
                                        icon: "warning"
                                    });
                                    $("#fif").val("");
                                    $("#fis").val("");
                                    $('#faseMuestra').val("1");
                                    $('#faseMuestra').trigger('change');

                                    $('#tipoConsulta').val("1");
                                    $('#tipoConsulta').trigger('change');

                                    $("#categoria").val("");
                                    $('#categoria').trigger('change');
                                } else {

                                    if (!(data.codigo == null )) {

                                        swal({
                                            title: "¡Alerta A2CARES!",
                                            text: "El participante " + $("#codigoParticipante").val() + ", es su primer evento A, se le puede recepcionar INICIAL",
                                            icon: "warning"
                                        });
                                        $("#fif").val("");
                                        $("#fis").val("");

                                        $('#faseMuestra').val('1');
                                        $('#faseMuestra').trigger('change');

                                        $('#tipoConsulta').val('1');
                                        $('#tipoConsulta').trigger('change');
                                        $("#categoria").val("");
                                        $('#categoria').trigger('change');

                                        $("#evento").val("A");
                                    }else{
                                        swal({
                                            title: "¡Alerta A2CARES!",
                                            text: "El participante " + $("#codigoParticipante").val() + ", No existe",
                                            icon: "error"
                                        });
                                        $("#fif").val("");
                                        $("#fis").val("");

                                        $('#faseMuestra').val("");
                                        $('#faseMuestra').trigger('change');

                                        $('#tipoConsulta').val("");
                                        $('#tipoConsulta').trigger('change');
                                        $("#categoria").val("");
                                        $('#categoria').trigger('change');

                                    }

                                }

                            }
                        }//fin de data.Isnull





                        }

                    }
                }).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                    swal({
                        title: "¡ERROR!",
                        text: "Sucedió un error. Favor informar al administrador",
                        icon: "error"
                    });
                    LimpiarParticipante();
                });
            }


            var form1 = $('#save-Serologia-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules:{
                    volumen:{
                        required:true,
                        number: true,
                        min:1,
                        maxlength: 3
                    },
                    fecha:{
                        required:true
                    },
                    codigoParticipante:{
                        required:true,
                        number: true
                    },
                    fis:{
                        required:true
                    },
                    fif:{
                        required:true
                    }
                },
                errorPlacement: function ( error, element ) {
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        error.insertAfter( element );
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
                    saveSerologia();
                }
            });

            function saveSerologia(){
                var isAllValid = true;
                var vol = $("#volumen").val();
                 if( vol > 7){// valida el volumen de la muestra
                     $("#volumen").val("");
                    swal("Advertencia!", "Volumen máximo permitido de la Muestra es de 7 ml", "error");
                    isAllValid = false;
                    return;
                }else if(vol<6){
                    if($('#observacion').val()==""){
                        swal({
                            title: "¡ERROR!",
                            text: "Ingresa la razón por la cual el volumen es incompleto?",
                            icon: "error",
                            timer: 2000
                        });
                        $('#observacion').prop("required", true);
                        $('#observacion').focus();
                        isAllValid = false;
                        return;
                    }else{
                        $('#observacion').prop("required", false);
                        isAllValid = true;
                    }
                }
                if (isAllValid) {
                    var formSerologia = $("#save-Serologia-form");
                    $.post(urls.saveFormUrl, formSerologia.serialize(), function (data) {
                        if (data.msj != null) {
                            swal({
                                title: "¡ERROR!",
                                text: data.msj,
                                icon: "info",
                                timer: 2000
                            });
                        } else {
                            swal({
                                title: "¡Buen trabajo!",
                                text: urls.successMessage,
                                icon: 'success',
                                timer: 2000
                            });

                            if ($("#tiporequest").val() === 'true') {//es edicion
                                window.setTimeout(function(){
                                    window.location.href = urls.listaUrl;
                                }, 1000);
                            } else {
                                Limpiartxt();
                                $("#parametro").focus().val("");
                            }
                        }
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        swal({
                            title: "¡ERROR!",
                            text: "Sucedió un error. Favor informar al administrador",
                            icon: "error"
                        });
                    });

                }//fin isValid
            }

            $('.submit_on_enter').on('keyup',function(event) {
                if (event.which ===13) {
                    event.preventDefault();
                    $("#save-Serologia-form").submit();
                }
            });

            function Limpiartxt(){
                $("#idRecepcion").val("");
                $("#codigoParticipante").val("");
                $("#nombreCompleto").val("");
                $("#edad_year").val("");
                $("#edad_meses").val("");
                $("#edad_dias").val("");
                $("#casa").val("");
                $("#volumen").val("");
                $("#observacion").val("");
                $("#fis").val("").change();
                $("#fif").val("").change();
                $("#tipoConsulta").val("").change();
                $("#categoria").val("").change();
                $("#faseMuestra").val("").change();
                $("#evento").val("");

            }

            function LimpiarParticipante(){
                $("#idRecepcion").val("");
                $("#codigoParticipante").val("");
                $("#fechaNac").val("");
                $("#nombreCompleto").val("");
                $("#edad_year").val("");
                $("#edad_meses").val("");
                $("#edad_dias").val("");
                $("#casa").val("");
            }
        }
    }
}();
