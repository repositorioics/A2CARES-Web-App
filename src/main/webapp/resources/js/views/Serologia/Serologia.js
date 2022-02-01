/**
 * Created by ICS on 15/10/2020.
 */
var endPointSero = {};
var SerologiaProcess = function(){
    return{
        init: function(urls){
            endPointSero = urls;
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
                errorElement: 'em',
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
                $.getJSON(endPointSero.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    var len = data.length;
                    if(data.mensaje !=undefined){
                        swal({
                            title: "¡ERROR!",
                            text: data.mensaje,
                            icon: "error",
                            timer: 2000
                        });
                        window.setTimeout(function(){
                            window.location.reload(true);
                        }, 1000);
                    }
                    if(len==0){
                        swal({
                            title: "¡ADVERTENCIA!",
                            text: "Código no encontrado!",
                            icon: "info",
                            timer: 2000
                        });
                        window.setTimeout(function(){
                            window.location.reload(true);
                        }, 1000);
                        $("#parametro").focus().val("");
                    } else{
                        if(data.estado ==1){
                            swal({
                                title: "¡ADVERTENCIA!",
                                text: "Participante Retirado",
                                icon: "error",
                                timer: 2000
                            });
                            window.setTimeout(function(){
                                window.location.reload(true);
                            }, 1000);
                        }else{
                            var hoy=moment();
                            $("#idParticipante").val(data.codigo);
                            $("#nombreCompleto").val(data.nombreCompleto);
                            $("#casaCHF").val(data.codigo_casa);
                            $("#edad_year").val(data.edad_year);
                            $("#edad_meses").val(data.edad_meses);
                            $("#edad_dias").val(data.edad_dias);
                            $("#volumen").val("");
                            $("#observacion").val(data.observacion);
                            var dob = new Date(data.fechaNacimiento);
                            var fnac = moment(dob).format('YYYY-MM-DD');
                            var anios = hoy.diff(fnac,"years");
                            var meses = hoy.diff(fnac,"months");
                            $("#fechaNac").val(fnac);
                            $("#edadMeses").val(DifenciaMeses);
                        }
                    }
                }).fail(function() {
                    Limpiartxt();
                    swal({
                        title: "¡ERROR!",
                        text: "500 Interno del Servidor",
                        icon: "error",
                        tim: false
                    });
                    window.setTimeout(function () {
                        location.reload(true);
                    }, 1200);
                    $("#parametro").focus().val("");
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
                    idParticipante:{
                        required:true,
                        number: true
                    }
                },
                errorElement: 'em',
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
                    saveSerologia(endPointSero);
                }
            });

            function saveSerologia(urls){
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
                }else{}

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
                            window.setTimeout(function () {
                                location.reload(true);
                            }, 1300);
                        } else {
                            swal({
                                title: "¡Buen trabajo!",
                                text: urls.successMessage,
                                icon: 'success',
                                timer: 2000
                            });
                            window.setTimeout(function(){
                                window.location.href = urls.createUrl;
                            }, 1000);
                            $("#parametro").focus().val("");
                        }
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        Limpiartxt();
                        swal({
                            title: "¡ERROR!",
                            text: "500 Interno del Servidor",
                            icon: "error"
                        });
                        window.setTimeout(function(){
                            window.location.reload(true);
                        }, 1000);
                    });

                }//fin isValid
            }

            function DifenciaMeses(){
                var a = moment();
                var b = moment($("#fechaNac").val()).format('YYYY-MM-DD');
                var months = a.diff(b, 'months');
                return months;
            };

            $('.submit_on_enter').on('keyup',function(event) {
                if (event.which ===13) {
                    event.preventDefault();
                    $("#save-Serologia-form").submit();
                }
            });

            function Limpiartxt(){
                $("#idSerologia").val("");
                $("#idParticipante").val("");
                $("#fechaNac").val("");
                $("#edadMeses").val("");
                $("#nombreCompleto").val("");
                $("#edad_year").val("");
                $("#edad_meses").val("");
                $("#edad_dias").val("");
                $("#casaCHF").val("");
                $("#volumen").val("");
                $("#observacion").val("");
            }



        }
    }
}();
