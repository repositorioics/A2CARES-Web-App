/**
 * Created by Lizandro Serrano on 15/08/2024.
 */
var saveDetalle = function(){
    return{
        init:function(parametros){
            'use strict';
            $("#nivelConciencia, #extremidades, #llenadoCapilar, #pulsoCalidad, #diuresis, #personaValida").select2();
            $("#fecha").datepicker({
                format: "dd/mm/yyyy",
                todayBtn: true,
                todayHighlight: true,
                autoclose: true
            });

            var form = $('#formDetailHemo');
            form.validate({
                rules: {
                    fecha:{required:true},
                    hora:{required:true},
                    signo:{ required:true },
                    nivelConciencia:{required:true},
                    ps: { required: true, number: true },
                    pd:{ required: true, number: true },
                    tc:{ required: true, number: true},
                    fr: { required: true, number: true, min:12, max:80 },
                    fc:{ required: true,  number:true, min:50, max:180 },
                    sa: { required: true, number: true, min:70, max:100 },
                    diuresis: {required: true },
                    densidadUrinaria: { required: true, number: true, min:1000, max:1030 },
                    cantidadOrina:{ number:true, min:0 },
                    cargas_iv:{ number: true, min:0 },
                    sro:{ number:true, min:0 },
                    personaValida: { required: true }
                },
                errorPlacement: function ( error, element ) {
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    }else if (element.attr("name") == "signo") {
                        error.insertAfter("#clasificaerror");
                    }else {
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
                    saveDetail(parametros);
                }
            });

            function saveDetail(url) {
                $.post(url.saveDetailUrl, form.serialize(), function (data) {
                    if (data.msj != null) {
                        toastr.error(data.msj, "ERROR", {timeOut: 6000});
                        return false;
                    } else {
                        if (data.idHemoDetalle != undefined) {
                            toastr.success(url.successmessage, "success", {timeOut: 6000});
                            window.setTimeout(function () {
                                window.location.href =url.reloadUrl;
                            }, 1500);
                        }
                    }
                }).fail(function() {
                    toastr.error("Fallo Interno del Servidor.!", "ERROR", {timeOut: 6000});
                })
            }
        }
    }
}();
