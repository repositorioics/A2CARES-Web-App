var EditData = function () {
    var bloquearUI = function (mensaje) {
        var loc = window.location;
        var pathName = loc.pathname.substring(0, loc.pathname.indexOf('/', 1) + 1);
        var mess = '<img src=' + pathName + 'resources/img/ajax-loading.gif>' + mensaje;
        $.blockUI({ message: mess,
            css: {
                border: 'none',
                padding: '15px',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                opacity: .5,
                color: '#fff'
            },
            baseZ: 1051 // para que se muestre bien en los modales
        });
    };

    var desbloquearUI = function () {
        setTimeout($.unblockUI, 500);
    };
   return {
        //main function to initiate the module
        init: function (parametros) {

            var form1 = $('#download-form');
            var error1 = $('.alert-danger', form1);
           

            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    propiedad: {required: true},
                    valor: {required: function () {
                        return !$('#setNull').is(':checked');
                    }},
                    zpform: {
                        required: true
                    },
                    recordId: {
                        required: true,
                        pattern: /^ZPO-\d{3}[0-3]$/
                    },
                    zpEventRC: {
                        required: true
                    },
                    zpEventUS: {
                        required: true
                    }
                },
                invalidHandler: function (event, validator) { //display error alert on form submit
                    error1.show();
                    App.scrollTo(error1, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    error1.hide();
                    run();
                    $("#propiedad").val("");
                    $("#valor").val("");
                    $("#recordId1").val("");
                    $("#columnas").val("");
                }
            });


            $('#zpform').change(function () {
                $("#propiedad").val("");
                if ($(this).val().length > 0) {
                    $.getJSON(parametros.getPropertiesName, {
                            ajax: 'true',
                            tabla : $(this).val()
                        }, function (dataToLoad) {

                            $.each(dataToLoad, function(key, value) {
                                $("#columnas").append('<option name="' + key + '">' + value + '</option>');
                            });
                            /* $( "#propiedad").aaData({
                             source: dataToLoad

                             });*/
                            desbloquearUI();
                        }
                    ).fail(function(jqXHR) {
                            desbloquearUI();
                        });

                    if ($(this).val() === "ento_cuestionario_hogar" || $(this).val() === "ento_cuestionario_hogar" ){
                        $("#divEventosUS").show();
                        $("#divEventosRCap").hide();
                    }else if ($(this).val() === "zp_cons_recepcion" || $(this).val() === "zp_cons_salida" ){
                        $("#divEventosRCap").hide();
                        $("#divEventosUS").hide();
                    } else {
                        $("#divEventosRCap").show();
                        $("#divEventosUS").hide();
                    }
                }else{
                    $("#divEventosRCap").show();
                    $("#divEventosUS").hide();
                }

            });



            function run() {

                bloquearUI("");
                var tabla = $('#zpform').find('option:selected').val();
                var evento = "";
                if (tabla === "ento_cuestionario_hogar" || tabla === "ento_cuestionario_hogar"){
                    evento = $('#zpEventUS').find('option:selected').val();
                }else{
                    evento = $('#zpEventRC').find('option:selected').val();
                }
                var conceptObj = {};
                conceptObj['mensaje'] = '';
                conceptObj['tabla'] = tabla;
                conceptObj['evento'] = evento;
                conceptObj['id'] = $('#recordId1').val();
                conceptObj['propiedad'] = $('#propiedad').val();
                conceptObj['valor'] = $('#valor').val();
                conceptObj['nulo'] = 'false';
               // conceptObj['nulo'] = ($('#setNull').is(':checked'));
                $.ajax(
                    {
                        url: parametros.updateUrl,
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify(conceptObj),
                        contentType: 'application/json',
                        mimeType: 'application/json',
                        success: function (data) {
                            if (data.error.length > 0) {
                                toastr.error(data.mensaje);
                            } else {
                                toastr.success(data.mensaje);
                            }
                            desbloquearUI();
                        },
                        error: function (jqXHR) {
                            desbloquearUI();
                        }
                    }
                );

            }

        }
    };

}();