/**
 * Created by ICS on 15/10/2020.
 */
var endPointSero = {};
var JustificacionesProcess = function(){
    return{
        init: function(urls){
            endPointSero = urls;
            $("#select-participante-form").validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules:{
                    parametro: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        maxlength: 8,
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

                }
            });

            var form1 = $('#save-Serologia-form');

            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input,
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
                                title: "Justificación creada exitosamente.",
                                text: urls.successMessage,
                                icon: 'success',
                                timer: 2000
                            });
                            window.setTimeout(function(){
                                window.location.href = urls.createUrl;
                            }, 1000);

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
                $("#userId").val("");
                $('#userId').change();
                $("#tipoJust").val("");
                $('#tipoJust').change();
                $("#fechaInicioCons").val("");
                $('#fechaInicioCons').change();
                $("#fechaFinCons").val("");
                $('#fechaFinCons').change();
            }



        }
    }
}();



