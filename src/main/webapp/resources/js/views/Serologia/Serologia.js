/**
 * Created by ICS on 15/10/2020.
 */
var endPointSero = {};
var Serologia2020 = function(){
    return{
        init: function(urls){
            endPointSero = urls;
            $("#select-participante-form").validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules:{
                    parametro: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        maxlength: 5,
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
                    if(data.mensaje != undefined){
                        toastr.error(data.mensaje,"ERROR!",{timeOut:6000});
                        Limpiartxt();
                        $("#fechaNac").val("");
                        $("#edadMeses").val("");
                    }
                    if(len==0){
                        toastr.warning("Código no encontrado","ADVERTENCIA!",{timeOut:6000});
                        $("#fechaNac").val("");
                        $("#edadMeses").val("");
                        Limpiartxt();
                        $("#parametro").focus();
                    } else{
                        if(data.estado == 1){
                            toastr.error("Participante Retirado!", "ERROR!",{timeOut:6000});
                            Limpiartxt();
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
                    toastr.error("Código no existe!", "Error!",{timeOut:6000});
                    $("#parametro").val("");
                    $("#parametro").focus();
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
                        min:1
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
                 if( vol > 8){
                     $("#volumen").val("");
                    swal("Advertencia!", "Volumen permitido de la Muestra es de 8 ml", "error");
                    isAllValid = false;
                    return;
                }else if(vol<8){
                    debugger;
                    if($('#observacion').val()==""){
                        toastr.error("Razón por la cual el volumen es incompleto!",{timeOut:6000});
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
                    debugger;
                        if (data.msj != null) {
                            toastr.error( data.msj, "Error!",{timeOut:6000});
                        } else {
                            toastr.success(urls.successMessage);
                            window.setTimeout(function(){
                                window.location.href = urls.createUrl;
                            }, 1000);
                            $("#parametro").val("");
                            $("#parametro").focus();
                        }
                    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        Limpiartxt();
                        toastr.error("Error 500 Internal Server", "ERROR!",{timeOut:6000});
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
                // enter has keyCode = 13, change it if you want to use another button
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
