/**
 * Created by ICS on 29/10/2020.
 */
var RealizarRetiro = function(){

    return{
        init: function(params) {
            var form1 = $('#select-participante-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    parametro: {
                        pattern: /^\+?[0-9]*\.?[0-9]+$/,
                        required: true,
                        maxlength: 5
                    }
                },
                errorPlacement: function (error, element) {
                    // Add the `help-block` class to the error element
                    error.addClass('form-control-feedback');
                    if (element.prop('type') === 'checkbox') {
                        error.insertAfter(element.parent('label'));
                    } else {
                        //error.insertAfter( element ); //cuando no es input-group
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
                    search();
                }
            });

            function search() {
                $.getJSON(params.BuscarParticipanteUrl, form1.serialize(), function (data) {
                        if (data.mensaje != undefined) {
                            toastr.warning(data.mensaje,{timeout:1500});
                        }
                        else {
                            $("#nombreCompleto").val(data.nombreCompleto);
                            $("#codigo_casa").val(data.codigo_casa);
                            $("#estudio").val(data.estudios);
                            $("#codigoParticipante").val(data.idparticipante);
                            $("#nombreMadre").val(data.nombremadre);
                            $("#nombrePadre").val(data.nombrepadre);
                            $("#edad").val(data.edadParticipante);
                            $("#nombretutor").val(data.nombretutor);
                            $("#estado").val(data.estado);
                            $("#quiencomunica").focus();
                        }
                    }
                ).fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error(errorThrown,{timeout:1000});
                });
            }

            $("#causa").on("change", function(){
                var causa =this.value;
                var $razon = $('#razonretiro');
                    $razon.select2().val("").trigger("change");
                    $razon.select2().empty();
                GetMotivos(causa);
            });

            function GetMotivos(id){
                var jqxhr = $.getJSON(params.MotivosUrl,{razonretiro : id, ajax : 'true'}, function(data){
                    var $razon = $('#razonretiro');
                    $razon.empty();
                    $razon.append('<option selected value="">'+ "Seleccione..." +'</option>');
                    $.each(data.razonList, function (i, val) {
                    $razon.append('<option value="' + val.motivo + '">' + val.motivo +" - "+ val.Descripcion + '</option>');
                    });
                }).fail(function() {
                   $.notify("Error al obtener los motivos", "error" );
                });
            }
            $("#razonretiro").on("change", function(){
                if(this.value == 'D1' || this.value == 'D2'){
                    $("#bar").fadeIn("slow");
                    $("#fechaFallecido").val("");
                    $("#fechaFallecido").attr("required", "true");
                }else{
                    $("#bar").fadeOut("slow");
                    $("#fechaFallecido").val("").attr("required", "false");
                }

                if(this.value == 'A8' || this.value == 'B5' ) {
                    $("#otherMot").fadeIn("slow");
                    $("#otromotivo").val("");
                    $("#otromotivo").attr("required", "true");
                }else{
                    $("#otherMot").fadeOut("slow");
                    $("#otromotivo").attr("required", "false");
                }
            });

            $(".letras").keypress(function(key) {
                window.console.log(key.charCode)
                if ((key.charCode < 97 || key.charCode > 122) //letras mayusculas
                    &&
                    (key.charCode < 65 || key.charCode > 90) //letras minusculas
                    &&
                    (key.charCode != 45) //retroceso
                    &&
                    (key.charCode != 241) //??
                    &&
                    (key.charCode != 209) //??
                    &&
                    (key.charCode != 32) //espacio
                    &&
                    (key.charCode != 225) //??
                    &&
                    (key.charCode != 233) //??
                    &&
                    (key.charCode != 237) //??
                    &&
                    (key.charCode != 243) //??
                    &&
                    (key.charCode != 250) //??
                    &&
                    (key.charCode != 193) //??
                    &&
                    (key.charCode != 201) //??
                    &&
                    (key.charCode != 205) //??
                    &&
                    (key.charCode != 211) //??
                    &&
                    (key.charCode != 218) //??
                    &&
                    (key.charCode != 45) //-

                    )
                    return false;
            });

            $("#quiencomunica").blur(function(){
                if($(this).val() == ''){
                    $("#parentesco").select2().val("").trigger("change");
                    $("#parentesco").select2().prop('disabled', true).trigger('change');
                    $("#parentesco").attr("required", "false");
                }
            });

            $("#quiencomunica").bind("change paste keyup", function() {
                var valor = $(this).val();
                if(valor != null || valor != ""){
                    $("#parentesco").select2().prop('disabled', false).trigger('change');
                    $("#parentesco").attr("required", "true");
                }
            });



        }//init
    }
}();

var GuardarRetiro = function(){
    return{
        init: function(p){
            var form2 = $('#retiro-participante-form');
            form2.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    fehaRetiro: {
                        required: true
                    },
                    codigoParticipante: {
                        required: true
                    },
                    codigo_casa:{
                        required: true
                    }
                },
                errorPlacement: function (error, element) {
                    // Add the `help-block` class to the error element
                    error.addClass('form-control-feedback');
                    if (element.prop('type') === 'checkbox') {
                        error.insertAfter(element.parent('label'));
                    } else {
                        //error.insertAfter( element ); //cuando no es input-group
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
                    processRetiro();
                }
            });

            function processRetiro() {
                var jqxhr = $.post( p.savePartRetiradoUrl, form2.serialize(), function( data ){
                }).done(function() {
                    toastr.success(p.successmessage);
                    window.setTimeout(function(){
                        location.reload(true);
                    }, 1500);
                }).fail(function() {
                    toastr.error(params.errorProcess);
                });

            }
        }
    }
}();