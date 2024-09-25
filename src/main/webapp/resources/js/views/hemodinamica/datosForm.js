/**
 * Created by Lizandro Serrano on 15/08/2024.
 */
var guardarHemodinamica = function () {
    return {
        init: function (parametros) {
            'use strict';
            $("#select-participante-form").validate({
                rules: {
                    parametro: {required: true}
                },
                errorPlacement: function (error, element) {
                    // Add the `help-block` class to the error element
                    error.addClass('form-control-feedback');
                    if (element.prop('type') === 'checkbox') {
                        error.insertAfter(element.parent('label'));
                    } else {
                        error.insertAfter(element.parent('.input-group'));
                    }
                },
                highlight: function (element, errorClass, validClass) {
                    $(element).addClass('form-control-danger').removeClass('form-control-success');
                    $(element).parents('.form-group').addClass('has-danger').removeClass('has-success');
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).addClass('form-control-success').removeClass('form-control-danger');
                    $(element).parents('.form-group').addClass('has-success').removeClass('has-danger');
                },
                submitHandler: function (form) {
                    buscar(parametros);
                }
            });

            function buscar(parametros) {
                $.getJSON(parametros.searchPartUrl, {parametro: $("#parametro").val(), ajax: 'true'}, function (data) {
                    if (data.mensaje != null) {
                        toastr.error(data.mensaje, "ERROR", {timeOut: 6000});
                        $("#parametro").focus().val('');
                        return;
                    }else{
                        $("#codigoParticipante").val(data.codigoParticipante);
                        $("#nombre").val(data.nombreCompleto);
                        $("#fechaNac").val(data.fechaNacimiento);
                        var exp = data.fechaNacimiento.split('/');
                        var numExpediente = (exp[0] + "" + exp[1] + "" + exp[2].substr(2, 2));
                        $("#expediente").val(numExpediente);
                        $("#barrio").val(data.codigoBarrio).change();
                        $("#direccion").val(data.direccion);
                        $("#telefono").val('');
                        $("#peso").val('');
                        $("#talla").val('');
                        $("#numParametro").val('');
                        $("#fconsulta").val('');
                        $("#fie").val('');
                        $("#numEvento").val('');
                        $("#telefono").focus();
                    }
                }).fail(function () {
                    toastr.error("Error Interno del Servidor!", "ERROR", {timeOut: 6000});
                    $("#parametro").val("").focus();
                })
            }
            $("#barrio, #unidadSalud").select2();
            $("#fie").prop("readonly", true);
            $("#fconsulta, #fie").datepicker({
                format: "dd/mm/yyyy",
                todayBtn: true,
                todayHighlight: true,
                autoclose: true
            });
            var getDate = function(input){
                return new Date(input.date.valueOf());
            };
            $("#fconsulta").on("changeDate", function (selected) {
                $("#fie").prop("readonly", false).val('');
                $('#fie').datepicker('setEndDate', getDate(selected));
            });loadSelect();
            $("#barrio").on("change", function(){
                if(this.value == 22){
                    $("#divFueraSector").fadeIn("slow");
                    $("#fueraSector").attr("required", "true");
                }else{
                    $("#divFueraSector").fadeOut("slow");
                    $("#fueraSector").val("").attr("required", "false");
                }
            });

            var form1 = $('#save-hemo-form');
            form1.validate({
                rules: {
                    codigoParticipante: {required: true},
                    nombre: {required: true},
                    fechaNac: {required: true},
                    expediente: {required: true},
                    unidadSalud: {required: true},
                    barrio: {required: true},
                    direccion: {required: true},
                    peso: {required: true, number: true, min:0},
                    talla: {required: true, number: true, min:0},
                    fconsulta: {required: true},
                    fie: {required: true},
                    numParametro: {required: true, digits: true, min:1},
                    telefono: {maxlength: 8, minlength: 8,digits: true},
                    numEvento: {required: true},
                    numPagina: {required: true, digits: true, min:1},
                    chkpositivo: {required: true}
                },
                errorPlacement: function (error, element) {
                    error.addClass('form-control-feedback');
                    if (element.prop('type') === 'checkbox') {
                        error.insertAfter(element.parent('label'));
                    } else if (element.attr("name") == "chkpositivo") {
                        error.insertAfter("#positivoerror");
                    } else {
                        error.insertAfter(element);
                    }
                },
                highlight: function (element, errorClass, validClass) {
                    $(element).addClass('form-control-danger').removeClass('form-control-success');
                    $(element).parents('.form-group').addClass('has-danger').removeClass('has-success');
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).addClass('form-control-success').removeClass('form-control-danger');
                    $(element).parents('.form-group').addClass('has-success').removeClass('has-danger');
                },
                submitHandler: function (form) {
                    SaveHemo(parametros);
                }
            });

            function SaveHemo(url) {
                $.post(url.saveHemoUrl,form1.serialize(), function(data){
                    if(data.mensaje != null){
                        toastr.error(data.mensaje,"ERROR",{timeOut:6000});
                        return false;
                    }else{
                        if(url.editando) {
                            if (data.idDatoHemo != undefined) {
                                toastr.success(url.successmessage, "success", {timeOut: 6000});
                                window.setTimeout(function () {
                                    window.location.href = url.addDetailsUrl + "/" + data.idDatoHemo;
                                }, 1300);
                            }
                        }else{
                            toastr.success(url.successmessage, "success", {timeOut: 6000});
                            window.setTimeout(function(){
                                window.location.href = url.createUrl
                            },1300);
                        }
                    }
                }).fail(function (xhr, status, error) {
                    toastr.error("ERROR: " + status + " " + error + " " + xhr.status + " " + xhr.statusText);
                })
            };

            function limpiarCtrl() {
                $("#nombre").val('');
                $("#fechaNac").val('');
                $("#expediente").val('');
                $("#barrio").val('');
                $("#direccion").val('');
                $("#telefono").val('');
                $("#peso").val('');
                $("#talla").val('');
                $("#numParametro").val('');
                $("#fconsulta").val('');
                $("#fie").val('');
                $("#numEvento").val('');
                $("#numPagina").val('');
                $("#parametro").val('').focus();
            }

            function loadSelect(){
                if($("#barrio").val() == 22){
                    $("#divFueraSector").fadeIn("slow");
                    $("#fueraSector").attr("required", "true");
                }else{
                    $("#divFueraSector").fadeOut("slow");
                    $("#fueraSector").val("").attr("required", "false");
                }
            };

        }
    }
}();