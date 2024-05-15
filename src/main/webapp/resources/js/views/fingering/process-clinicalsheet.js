var ClinicalSheet = function () {
	
	var handleSelect = function () {
        $("#medico").select2();
        $("#enfermeria").select2();
        $("#sexo").select2();
        $("#tipoConsulta").select2();
        $("#lugarConsulta").select2();
        $("#categoria").select2();
        $("#cambioCategoria").select2();
        $("#hospitalizado").select2();
        $("#transfusion").select2();
        $("#tomaMedicamento").select2();
    };

    var handleDateTimePicker = function (locale) {
        $('.date-picker').datetimepicker({
            format: 'L',
            locale: locale,
            maxDate: new Date(),
            useCurrent: false
        });

        $('.time-picker').datetimepicker({
            format: 'LT',
            icons: {
                up: "fas fa-arrow-up",
                down: "fas fa-arrow-down"
            },
            useCurrent: false
        });

    };
    return {
        //main function to initiate the module
        init: function (parametros) {

            toastr.options = {
                "closeButton": true,
                "showMethod": "fadeIn",
                "progressBar": true,
                "hideMethod": "fadeOut"
            };

            // wrapper function to  block element(indicate loading)
            function blockUI(el, centerY) {
                var el = jQuery(el);
                var loc = window.location;
                var pathName = loc.pathname.substring(0,loc.pathname.indexOf('/', 1)+1);
                var mess = '<img src=' + pathName + 'resources/img/ajax-loading.gif align="">';
                if (el.height() <= 400) {
                    centerY = true;
                }
                el.block({
                    message: mess,
                    centerY: centerY != undefined ? centerY : true,
                    css: {
                        top: '10%',
                        border: 'none',
                        padding: '2px',
                        backgroundColor: 'none'
                    },
                    overlayCSS: {
                        backgroundColor: '#000',
                        opacity: 0.05,
                        cursor: 'wait'
                    }
                });
            }

            // wrapper function to  un-block element(finish loading)
            function unblockUI (el) {
                jQuery(el).unblock({
                    onUnblock: function () {
                        jQuery(el).removeAttr("style");
                    }
                });
            }

            handleSelect();
            handleDateTimePicker(parametros.locale);

            var formSearch = $('#search-participant-form');
            formSearch.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    participantCode : {
                        required: true,
                        pattern: /^\d{4}$/
                    }
                },
                errorPlacement: function ( error, element ) {
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
                    search();

                }
            });
            var formSearchCod = $('#search-cod-form');
            formSearchCod.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    codigoSupervisor : {
                        required: true
                    }
                },
                errorPlacement: function ( error, element ) {
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
                    $('#codSup1').val($('#codigoSupervisor').val());
                        buscarsup();



                }
            });
            var formSearchCod2 = $('#search-num-form');
            formSearchCod2.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    numHoja : {
                        required: true
                    }
                },
                errorPlacement: function ( error, element ) {
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
                    $('#numHoja1').val($('#numHoja').val());
                        buscarH1();
                        buscarH2();


                }
            });

            var formDatos = $('#form-clinicalsheet');
            formDatos.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                     fc: {

                         required: true,
                         digits: true,
                         range: [45, 200]

                    },
                    temp: {
                        required: true,
                        range: [35.5, 41]

                    },
                    so: {
                    //    required: true,
                        digits: true,
                    //    range: [0, 100]
                    },
                    fcMedico: {
                        required: true,
                        digits: true,
                      //  range: [60, 140]
                        range: [0, 180]
                    },
                    tempMedico: {
                        required: true,
                       // range: [34, 44]
                        range: [35.5, 41]

                    },
                    frMedico: {
                        required: true,
                        digits: true,
                        range: [12, 60]
                    },
                    soMedico: {
                        required: true,
                        digits: true,
                        range: [0, 100]
                    },
                    fechaCons: {
                        required: true
                    },
                    horaCons: {
                        required: true
                    },
                    peso: {
                        required: true,
                        range: [8, 200]
                    },
                    talla: {
                        required: true,
                        range: [60, 220]
                    },
                    rbgeneral_1 : {
                        required: true
                    },
                    rbgeneral_2 : {
                        required: true
                    },
                    rbgeneral_3 : {
                        required: true
                    },
                    rbgeneral_4 : {
                        required: true
                    },
                    rbgeneral_5 : {
                        required: true
                    },
                    rbgeneral_6 : {
                        required: true
                    },
                    rbgeneral_7 : {
                        required: true
                    },
                    rbgastroin_1 : {
                        required: true
                    },
                    rbgastroin_2 : {
                        required: true
                    },
                    rbgastroin_3 : {
                        required: true
                    },
                    numVomito : {
                        required: function(element){
                            return $("input[name='rbgastroin_3']:checked").val()==="S";
                        },
                        digits: true
                    },
                    rbgastroin_4 : {
                        required: true
                    },
                    rbgastroin_5 : {
                        required: true
                    },
                    rbgastroin_6 : {
                        required: true
                    },
                    rbcabeza_1 : {
                        required: true
                    },
                    rbcabeza_2 : {
                        required: true
                    },
                    rbcabeza_3 : {
                        required: true
                    },
                    rbcabeza_4 : {
                        required: true
                    },
                    rbosteomusc_1 : {
                        required: true
                    },
                    rbosteomusc_2 : {
                        required: true
                    },
                    rbosteomusc_3 : {
                        required: true
                    },
                    rbosteomusc_4 : {
                        required: true
                    },
                    rbosteomusc_5 : {
                        required: true
                    },
                    rbgarganta_1 : {
                        required: true
                    },
                    rbgarganta_2 : {
                        required: true
                    },
                    rbgarganta_3 : {
                        required: true
                    },
                    rbgarganta_4 : {
                        required: true
                    },
                    rbgarganta_5 : {
                        required: true
                    },
                    rbcutaneo_1 : {
                        required: true
                    },
                    rbcutaneo_2 : {
                        required: true
                    },
                    rbcutaneo_3 : {
                        required: true
                    },
                    rbcutaneo_4 : {
                        required: true
                    },
                    rbcutaneo_5 : {
                        required: true
                    },
                    rbcutaneo_6 : {
                        required: true
                    },
                    rbcutaneo_7 : {
                        required: true
                    },
                    rbcutaneo_8 : {
                        required: true
                    },
                    rbcutaneo_9 : {
                        required: true
                    },
                    rbrespiratorio_1 : {
                        required: true
                    },
                    rbrespiratorio_2 : {
                        required: true
                    },
                    rbrespiratorio_3 : {
                        required: true
                    },
                    rbrespiratorio_4 : {
                        required: true
                    },
                    rbrespiratorio_5 : {
                        required: true
                    },
                    rbrespiratorio_6 : {
                        required: true
                    },
                    rbrespiratorio_7 : {
                        required: true
                    },
                    rbrespiratorio_8 : {
                        required: true
                    },
                    rbrespiratorio_9 : {
                        required: true
                    },
                    rbrespiratorio_10 : {
                        required: true
                    },
                    rbrespiratorio_11 : {
                        required: true
                    },
                    rbrespiratorio_12 : {
                        required: true
                    },
                    imc : {
                        required: true,
                        number: true
                    },
                    rbestadonut_1 : {
                        required: true
                    },
                    rbestadonut_2 : {
                        required: true
                    },
                    rbestadonut_3 : {
                        required: true
                    },
                    rbestadonut_4 : {
                        required: true
                    },
                    rbestadonut_5 : {
                        required: true
                    },
                    rbestadonut_6 : {
                        required: true
                    },
                    descOtroExamen : {
                        required: function(element){
                            return $("input[name='rbexamen_6']:checked").val()==="S";
                        }
                    },
                    descOtroTratamiento : {
                        required: function(element){
                            return $("input[name='rbtratamiento_9']:checked").val()==="S";
                        }
                    },
                    unidadSaludHosp : {
                        required: function(element){
                            return $('#hospitalizado').find('option:selected').val()==="1";
                        }
                    },
                    transfusionEsp : {
                        required: function(element){
                            return $('#transfusion').find('option:selected').val()==="1";
                        }
                    },
                    cualMedicamento : {
                        required: function(element){
                            return $('#tomaMedicamento').find('option:selected').val()==="1";
                        }
                    }

                },
                errorPlacement: function ( error, element ) {
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
                    save();
                }
            });







            function updateReadOnly(id, add)
            {
                if (add) {
                    // Ponemos el atributo de solo lectura
                    $("#" + id).attr("readonly", "readonly");
                    // Ponemos una clase para cambiar el color del texto y mostrar que
                    // esta deshabilitado
                    $("#" + id).addClass("readOnly");
                } else {
                    // Eliminamos el atributo de solo lectura
                    $("#"+id).removeAttr("readonly");
                    // Eliminamos la clase que hace que cambie el color
                    $("#"+id).removeClass("readOnly");
                }
            }

            function clearText(id){
                $("#"+id).val('');
            }

            $('input:radio[name="rbgeneral"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbgeneral_1S").prop('checked', true);
                    $("#rbgeneral_2S").prop('checked', true);
                    $("#rbgeneral_3S").prop('checked', true);
                    $("#rbgeneral_4S").prop('checked', true);
                    $("#rbgeneral_5S").prop('checked', true);
                    $("#rbgeneral_6S").prop('checked', true);
                    $("#rbgeneral_7S").prop('checked', true);
                } else if ($(this).val() == 'N') {
                    $("#rbgeneral_1N").prop('checked', true);
                    $("#rbgeneral_2N").prop('checked', true);
                    $("#rbgeneral_3N").prop('checked', true);
                    $("#rbgeneral_4N").prop('checked', true);
                    $("#rbgeneral_5N").prop('checked', true);
                    $("#rbgeneral_6N").prop('checked', true);
                    $("#rbgeneral_7N").prop('checked', true);
                }
                else {
                    $("#rbgeneral_1D").prop('checked', true);
                    $("#rbgeneral_2D").prop('checked', true);
                    $("#rbgeneral_3D").prop('checked', true);
                    $("#rbgeneral_4D").prop('checked', true);
                    $("#rbgeneral_5D").prop('checked', true);
                    $("#rbgeneral_6D").prop('checked', true);
                    $("#rbgeneral_7D").prop('checked', true);
                }
            });

            $('input:radio[name="rbgastroin"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbgastroin_1S").prop('checked', true);
                    $("#rbgastroin_2S").prop('checked', true);
                    $("#rbgastroin_3S").prop('checked', true);
                    $("#rbgastroin_4S").prop('checked', true);
                    $("#rbgastroin_5S").prop('checked', true);
                    $("#rbgastroin_6S").prop('checked', true);
                    updateReadOnly("numVomito", false);

                } else if ($(this).val() == 'N') {
                    $("#rbgastroin_1N").prop('checked', true);
                    $("#rbgastroin_2N").prop('checked', true);
                    $("#rbgastroin_3N").prop('checked', true);
                    $("#rbgastroin_4N").prop('checked', true);
                    $("#rbgastroin_5N").prop('checked', true);
                    $("#rbgastroin_6N").prop('checked', true);
                    updateReadOnly("numVomito", true);
                    clearText("numVomito");
                }
                else {
                    $("#rbgastroin_1D").prop('checked', true);
                    $("#rbgastroin_2D").prop('checked', true);
                    $("#rbgastroin_3D").prop('checked', true);
                    $("#rbgastroin_4D").prop('checked', true);
                    $("#rbgastroin_5D").prop('checked', true);
                    $("#rbgastroin_6D").prop('checked', true);
                    updateReadOnly("numVomito", true);
                    clearText("numVomito");
                }
            });

            $('input:radio[name="rbcabeza"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbcabeza_1S").prop('checked', true);
                    $("#rbcabeza_2S").prop('checked', true);
                    $("#rbcabeza_3S").prop('checked', true);
                    $("#rbcabeza_4S").prop('checked', true);
                } else if ($(this).val() == 'N') {
                    $("#rbcabeza_1N").prop('checked', true);
                    $("#rbcabeza_2N").prop('checked', true);
                    $("#rbcabeza_3N").prop('checked', true);
                    $("#rbcabeza_4N").prop('checked', true);
                }
                else {
                    $("#rbcabeza_1D").prop('checked', true);
                    $("#rbcabeza_2D").prop('checked', true);
                    $("#rbcabeza_3D").prop('checked', true);
                    $("#rbcabeza_4D").prop('checked', true);
                }
            });

            $('input:radio[name="rbosteomusc"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbosteomusc_1S").prop('checked', true);
                    $("#rbosteomusc_2S").prop('checked', true);
                    $("#rbosteomusc_3S").prop('checked', true);
                    $("#rbosteomusc_4S").prop('checked', true);
                    $("#rbosteomusc_5S").prop('checked', true);
                } else if ($(this).val() == 'N') {
                    $("#rbosteomusc_1N").prop('checked', true);
                    $("#rbosteomusc_2N").prop('checked', true);
                    $("#rbosteomusc_3N").prop('checked', true);
                    $("#rbosteomusc_4N").prop('checked', true);
                    $("#rbosteomusc_5N").prop('checked', true);
                }
                else {
                    $("#rbosteomusc_1D").prop('checked', true);
                    $("#rbosteomusc_2D").prop('checked', true);
                    $("#rbosteomusc_3D").prop('checked', true);
                    $("#rbosteomusc_4D").prop('checked', true);
                    $("#rbosteomusc_5D").prop('checked', true);
                }
            });

            $('input:radio[name="rbgarganta"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbgarganta_1S").prop('checked', true);
                    $("#rbgarganta_2S").prop('checked', true);
                    $("#rbgarganta_3S").prop('checked', true);
                    $("#rbgarganta_4S").prop('checked', true);
                    $("#rbgarganta_5S").prop('checked', true);
                } else if ($(this).val() == 'N') {
                    $("#rbgarganta_1N").prop('checked', true);
                    $("#rbgarganta_2N").prop('checked', true);
                    $("#rbgarganta_3N").prop('checked', true);
                    $("#rbgarganta_4N").prop('checked', true);
                    $("#rbgarganta_5N").prop('checked', true);
                }
                else {
                    $("#rbgarganta_1D").prop('checked', true);
                    $("#rbgarganta_2D").prop('checked', true);
                    $("#rbgarganta_3D").prop('checked', true);
                    $("#rbgarganta_4D").prop('checked', true);
                    $("#rbgarganta_5D").prop('checked', true);
                }
            });

            $('input:radio[name="rbcutaneo"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbcutaneo_1S").prop('checked', true);
                    $("#rbcutaneo_2S").prop('checked', true);
                    $("#rbcutaneo_3S").prop('checked', true);
                    $("#rbcutaneo_4S").prop('checked', true);
                    $("#rbcutaneo_5S").prop('checked', true);
                    $("#rbcutaneo_6S").prop('checked', true);
                    $("#rbcutaneo_7S").prop('checked', true);
                    $("#rbcutaneo_8S").prop('checked', true);
                    $("#rbcutaneo_9S").prop('checked', true);

                } else if ($(this).val() == 'N') {
                    $("#rbcutaneo_1N").prop('checked', true);
                    $("#rbcutaneo_2N").prop('checked', true);
                    $("#rbcutaneo_3N").prop('checked', true);
                    $("#rbcutaneo_4N").prop('checked', true);
                    $("#rbcutaneo_5N").prop('checked', true);
                    $("#rbcutaneo_6N").prop('checked', true);
                    $("#rbcutaneo_7N").prop('checked', true);
                    $("#rbcutaneo_8N").prop('checked', true);
                    $("#rbcutaneo_9N").prop('checked', true);
                }
                else {
                    $("#rbcutaneo_1D").prop('checked', true);
                    $("#rbcutaneo_2D").prop('checked', true);
                    $("#rbcutaneo_3D").prop('checked', true);
                    $("#rbcutaneo_4D").prop('checked', true);
                    $("#rbcutaneo_5D").prop('checked', true);
                    $("#rbcutaneo_6D").prop('checked', true);
                    $("#rbcutaneo_7D").prop('checked', true);
                    $("#rbcutaneo_8D").prop('checked', true);
                    $("#rbcutaneo_9D").prop('checked', true);
                }
            });

            $('input:radio[name="rbrespiratorio"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbrespiratorio_1S").prop('checked', true);
                    $("#rbrespiratorio_2S").prop('checked', true);
                    $("#rbrespiratorio_3S").prop('checked', true);
                    $("#rbrespiratorio_4S").prop('checked', true);
                    $("#rbrespiratorio_5S").prop('checked', true);
                    $("#rbrespiratorio_6S").prop('checked', true);
                    $("#rbrespiratorio_7S").prop('checked', true);
                    $("#rbrespiratorio_8S").prop('checked', true);
                    $("#rbrespiratorio_9S").prop('checked', true);
                    $("#rbrespiratorio_10S").prop('checked', true);
                    $("#rbrespiratorio_11S").prop('checked', true);
                    $("#rbrespiratorio_12S").prop('checked', true);
                } else if ($(this).val() == 'N') {
                    $("#rbrespiratorio_1N").prop('checked', true);
                    $("#rbrespiratorio_2N").prop('checked', true);
                    $("#rbrespiratorio_3N").prop('checked', true);
                    $("#rbrespiratorio_4N").prop('checked', true);
                    $("#rbrespiratorio_5N").prop('checked', true);
                    $("#rbrespiratorio_6N").prop('checked', true);
                    $("#rbrespiratorio_7N").prop('checked', true);
                    $("#rbrespiratorio_8N").prop('checked', true);
                    $("#rbrespiratorio_9N").prop('checked', true);
                    $("#rbrespiratorio_10N").prop('checked', true);
                    $("#rbrespiratorio_11N").prop('checked', true);
                    $("#rbrespiratorio_12N").prop('checked', true);
                }
                else {
                    $("#rbrespiratorio_1D").prop('checked', true);
                    $("#rbrespiratorio_2D").prop('checked', true);
                    $("#rbrespiratorio_3D").prop('checked', true);
                    $("#rbrespiratorio_4D").prop('checked', true);
                    $("#rbrespiratorio_5D").prop('checked', true);
                    $("#rbrespiratorio_6D").prop('checked', true);
                    $("#rbrespiratorio_7D").prop('checked', true);
                    $("#rbrespiratorio_8D").prop('checked', true);
                    $("#rbrespiratorio_9D").prop('checked', true);
                    $("#rbrespiratorio_10D").prop('checked', true);
                    $("#rbrespiratorio_11D").prop('checked', true);
                    $("#rbrespiratorio_12D").prop('checked', true);
                }
            });

            $('input:radio[name="rbestadonut"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbestadonut_1S").prop('checked', true);
                    $("#rbestadonut_2S").prop('checked', true);
                    $("#rbestadonut_3S").prop('checked', true);
                    $("#rbestadonut_4S").prop('checked', true);
                    $("#rbestadonut_5S").prop('checked', true);
                    $("#rbestadonut_6S").prop('checked', true);
                } else if ($(this).val() == 'N') {
                    $("#rbestadonut_1N").prop('checked', true);
                    $("#rbestadonut_2N").prop('checked', true);
                    $("#rbestadonut_3N").prop('checked', true);
                    $("#rbestadonut_4N").prop('checked', true);
                    $("#rbestadonut_5N").prop('checked', true);
                    $("#rbestadonut_6N").prop('checked', true);
                }
                else {
                    $("#rbestadonut_1D").prop('checked', true);
                    $("#rbestadonut_2D").prop('checked', true);
                    $("#rbestadonut_3D").prop('checked', true);
                    $("#rbestadonut_4D").prop('checked', true);
                    $("#rbestadonut_5D").prop('checked', true);
                    $("#rbestadonut_6D").prop('checked', true);
                }
            });

            $('input:radio[name="rbmanhemo"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbmanhemo_1S").prop('checked', true);
                    $("#rbmanhemo_2S").prop('checked', true);
                    $("#rbmanhemo_3S").prop('checked', true);
                    $("#rbmanhemo_4S").prop('checked', true);
                    $("#rbmanhemo_5S").prop('checked', true);
                    $("#rbmanhemo_6S").prop('checked', true);
                    $("#rbmanhemo_7S").prop('checked', true);
                    $("#rbmanhemo_8S").prop('checked', true);
                    $("#rbmanhemo_9S").prop('checked', true);
                    $("#rbmanhemo_10S").prop('checked', true);
                    $("#rbmanhemo_11S").prop('checked', true);
                    $("#rbmanhemo_12S").prop('checked', true);
                    $("#rbmanhemo_13S").prop('checked', true);
                } else if ($(this).val() == 'N') {
                    $("#rbmanhemo_1N").prop('checked', true);
                    $("#rbmanhemo_2N").prop('checked', true);
                    $("#rbmanhemo_3N").prop('checked', true);
                    $("#rbmanhemo_4N").prop('checked', true);
                    $("#rbmanhemo_5N").prop('checked', true);
                    $("#rbmanhemo_6N").prop('checked', true);
                    $("#rbmanhemo_7N").prop('checked', true);
                    $("#rbmanhemo_8N").prop('checked', true);
                    $("#rbmanhemo_9N").prop('checked', true);
                    $("#rbmanhemo_10N").prop('checked', true);
                    $("#rbmanhemo_11N").prop('checked', true);
                    $("#rbmanhemo_12N").prop('checked', true);
                    $("#rbmanhemo_13N").prop('checked', true);
                }
                else {
                    $("#rbmanhemo_1D").prop('checked', true);
                    $("#rbmanhemo_2D").prop('checked', true);
                    $("#rbmanhemo_3D").prop('checked', true);
                    $("#rbmanhemo_4D").prop('checked', true);
                    $("#rbmanhemo_5D").prop('checked', true);
                    $("#rbmanhemo_6D").prop('checked', true);
                    $("#rbmanhemo_7D").prop('checked', true);
                    $("#rbmanhemo_8D").prop('checked', true);
                    $("#rbmanhemo_9D").prop('checked', true);
                    $("#rbmanhemo_10D").prop('checked', true);
                    $("#rbmanhemo_11D").prop('checked', true);
                    $("#rbmanhemo_12D").prop('checked', true);
                    $("#rbmanhemo_13D").prop('checked', true);
                }
            });

            $('input:radio[name="rbexamen"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbexamen_1S").prop('checked', true);
                    $("#rbexamen_2S").prop('checked', true);
                    $("#rbexamen_3S").prop('checked', true);
                    $("#rbexamen_4S").prop('checked', true);
                    $("#rbexamen_5S").prop('checked', true);
                    $("#rbexamen_6S").prop('checked', true);
                    updateReadOnly("descOtroExamen", false);
                } else {
                    $("#rbexamen_1N").prop('checked', true);
                    $("#rbexamen_2N").prop('checked', true);
                    $("#rbexamen_3N").prop('checked', true);
                    $("#rbexamen_4N").prop('checked', true);
                    $("#rbexamen_5N").prop('checked', true);
                    $("#rbexamen_6N").prop('checked', true);
                    updateReadOnly("descOtroExamen", true);
                }
            });

            $('input:radio[name="rbtratamiento"]').change(function(){
                if ($(this).val() == 'S') {
                    $("#rbtratamiento_1S").prop('checked', true);
                    $("#rbtratamiento_2S").prop('checked', true);
                    $("#rbtratamiento_3S").prop('checked', true);
                    $("#rbtratamiento_4S").prop('checked', true);
                    $("#rbtratamiento_5S").prop('checked', true);
                    $("#rbtratamiento_6S").prop('checked', true);
                    $("#rbtratamiento_7S").prop('checked', true);
                    $("#rbtratamiento_8S").prop('checked', true);
                    $("#rbtratamiento_9S").prop('checked', true);
                    $("#rbtratamiento_10S").prop('checked', true);
                    $("#rbtratamiento_11S").prop('checked', true);
                    updateReadOnly("descOtroTratamiento", false);
                } else {
                    $("#rbtratamiento_1N").prop('checked', true);
                    $("#rbtratamiento_2N").prop('checked', true);
                    $("#rbtratamiento_3N").prop('checked', true);
                    $("#rbtratamiento_4N").prop('checked', true);
                    $("#rbtratamiento_5N").prop('checked', true);
                    $("#rbtratamiento_6N").prop('checked', true);
                    $("#rbtratamiento_7N").prop('checked', true);
                    $("#rbtratamiento_8N").prop('checked', true);
                    $("#rbtratamiento_9N").prop('checked', true);
                    $("#rbtratamiento_10N").prop('checked', true);
                    $("#rbtratamiento_11N").prop('checked', true);
                  //  updateReadOnly("descOtroTratamiento", true);
                }



            });

            function save() {
                console.log("save");
                var strJson = $("#form-clinicalsheet").serializeJSON();
                var recepcionObj = {};
                recepcionObj['hojaClinica'] = strJson;
                recepcionObj['participante'] = $("#codigoPart").val();
                $.ajax(
                    {
                        url: parametros.saveUrl,
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify(recepcionObj),
                        contentType: 'application/json',
                        mimeType: 'application/json',
                        async: false,
                        success: function (data) {
                            console.log(data);
                            if (data.error != undefined && data.error.length > 0) {
                                toastr.error(data.error,"Error",{timeOut: 5000});
                            } else {
                                //var msg = $("#msg_receipt_added").val();
                                toastr.success(data.mensaje,"Success",{timeOut: 5000});
                                /*setTimeout(function () {
                                    window.location.href = parametros.sSearchReceiptUrl
                                }, 4000);*/
                            }
                            //desbloquearUI();
                        },
                        error: function (jqXHR) {
                            console.log(jqXHR);
                            //desbloquearUI();
                            //validateLogin(jqXHR);
                        }
                    });
                swal.fire({
                    title: "A2CARES",
                    text: "Hoja guardada con éxito Digitación 1.",
                    type: "info",
                    cancelButtonText: 'Cancelar'
                });
                $("#participantCode").val("");
                $("#numHoja").val("");
                $("#nombre").val("");
                $("#fechanac").val("");
                $("#edadPart").val("");
                $("#sexoPart").val("");
                $("#codigoSuper").val("");

                //Asignacion de valores resultantes de comparacion de hojas digitadas

                $("#sexo").val('' );
                $('#sexo').change();


                $("#fechaCons").val('' );
                $('#fechaCons').change();

                $("#horaCons").val('' );

                $("#numHoja").val('' );

                $("#peso").val('' );

                $("#talla").val('' );

                $("#pa").val('' );

                $("#fc").val('' );

                $("#temp").val('' );

                $("#so").val('' );

                $("#horaIniCons").val('' );

                $("#tipoConsulta").val('' );
                $('#tipoConsulta').change();

                $('#lugarConsulta').val('');
                $('#lugarConsulta').change();

                $("#paMedico").val('' );

                $("#tempMedico").val('' );

                $("#frMedico").val('' );

                $("#fcMedico").val('' );

                $("#soMedico").val('' );

                $("#fis").val('' );
                $('#fis').change();

                $("#fif").val('' );
                $('#fif').change();

                $("#ultimoDiaFiebre").val('' );
                $('#ultimoDiaFiebre').change();

                $("#horaUltimoDiaF").val('' );
                $('#horaUltimoDiaF').change();

                $("#ultimaDosisAntip").val('' );
                $('#ultimaDosisAntip').change();

                $("#horaUltimaDosisAntip").val('' );

                $("#horaUltimaDosisAntip").val('' );

                //llena check box de sintomas
                /*sintomas  General */

                $("#rbgeneral_1S").prop('checked', false);
                $("#rbgeneral_1N").prop('checked', false);
                $("#rbgeneral_1S").prop('checked', false);
                $("#rbgeneral_1N").prop('checked', false);
                $("#rbgeneral_2S").prop('checked', false);
                $("#rbgeneral_2N").prop('checked', false);
                $("#rbgeneral_3S").prop('checked', false);
                $("#rbgeneral_3N").prop('checked', false);
                $("#rbgeneral_4S").prop('checked', false);
                $("#rbgeneral_4N").prop('checked', false);
                $("#rbgeneral_5S").prop('checked', false);
                $("#rbgeneral_5N").prop('checked', false);
                $("#rbgeneral_6S").prop('checked', false);
                $("#rbgeneral_6N").prop('checked', false);
                $("#rbgeneral_7S").prop('checked', false);
                $("#rbgeneral_7N").prop('checked', false);

                /*cabeza*/

                $("#rbcabeza_1S").prop('checked', false);
                $("#rbcabeza_1N").prop('checked', false);
                $("#rbcabeza_2S").prop('checked', false);
                $("#rbcabeza_2N").prop('checked', false);
                $("#rbcabeza_3S").prop('checked', false);
                $("#rbcabeza_3N").prop('checked', false);
                $("#rbcabeza_4S").prop('checked', false);
                $("#rbcabeza_4N").prop('checked', false);

                /*Garganta*/

                $("#rbgarganta_1S").prop('checked', false);
                $("#rbgarganta_1N").prop('checked', false);
                $("#rbgarganta_2S").prop('checked', false);
                $("#rbgarganta_2N").prop('checked', false);
                $("#rbgarganta_3S").prop('checked', false);
                $("#rbgarganta_3N").prop('checked', false);
                $("#rbgarganta_4S").prop('checked', false);
                $("#rbgarganta_4N").prop('checked', false);
                $("#rbgarganta_5S").prop('checked', false);
                $("#rbgarganta_5N").prop('checked', false);

                /*Respiratorio*/

                $("#rbrespiratorio_1S").prop('checked', false);
                $("#rbrespiratorio_1N").prop('checked', false);
                $("#rbrespiratorio_2S").prop('checked', false);
                $("#rbrespiratorio_2N").prop('checked', false);
                $("#rbrespiratorio_3S").prop('checked', false);
                $("#rbrespiratorio_3N").prop('checked', false);
                $("#rbrespiratorio_4S").prop('checked', false);
                $("#rbrespiratorio_4N").prop('checked', false);
                $("#rbrespiratorio_5S").prop('checked', false);
                $("#rbrespiratorio_5N").prop('checked', false);
                $("#rbrespiratorio_6S").prop('checked', false);
                $("#rbrespiratorio_6N").prop('checked', false);
                $("#rbrespiratorio_7S").prop('checked', false);
                $("#rbrespiratorio_7N").prop('checked', false);
                $("#rbrespiratorio_8S").prop('checked', false);
                $("#rbrespiratorio_8N").prop('checked', false);
                $("#rbrespiratorio_9S").prop('checked', false);
                $("#rbrespiratorio_9N").prop('checked', false);
                $("#rbrespiratorio_10S").prop('checked', false);
                $("#rbrespiratorio_10N").prop('checked', false);
                $("#rbrespiratorio_11S").prop('checked', false);
                $("#rbrespiratorio_11N").prop('checked', false);
                $("#rbrespiratorio_12S").prop('checked', false);
                $("#rbrespiratorio_12N").prop('checked', false);

                /*Gastrointestinal*/

                $("#rbgastroin_1S").prop('checked', false);
                $("#rbgastroin_1N").prop('checked', false);
                $("#rbgastroin_2S").prop('checked', false);
                $("#rbgastroin_2N").prop('checked', false);
                $("#rbgastroin_3S").prop('checked', false);
                $("#rbgastroin_3N").prop('checked', false);
                $("#numVomito").val("");
                $("#rbgastroin_4S").prop('checked', false);
                $("#rbgastroin_4N").prop('checked', false);
                $("#rbgastroin_5S").prop('checked', false);
                $("#rbgastroin_5N").prop('checked', false);
                $("#rbgastroin_6S").prop('checked', false);
                $("#rbgastroin_6N").prop('checked', false);

                /*Osteomuscular*/

                $("#rbosteomusc_1S").prop('checked', false);
                $("#rbosteomusc_1N").prop('checked', false);
                $("#rbosteomusc_2S").prop('checked', false);
                $("#rbosteomusc_2N").prop('checked', false);
                $("#rbosteomusc_3S").prop('checked', false);
                $("#rbosteomusc_3N").prop('checked', false);
                $("#rbosteomusc_4S").prop('checked', false);
                $("#rbosteomusc_4N").prop('checked', false);
                $("#rbosteomusc_5S").prop('checked', false);
                $("#rbosteomusc_5N").prop('checked', false);

                /*Cutáneo*/

                $("#rbcutaneo_1S").prop('checked', false);
                $("#rbcutaneo_1N").prop('checked', false);
                $("#rbcutaneo_2S").prop('checked', false);
                $("#rbcutaneo_2N").prop('checked', false);
                $("#rbcutaneo_3S").prop('checked', false);
                $("#rbcutaneo_3N").prop('checked', false);
                $("#rbcutaneo_4S").prop('checked', false);
                $("#rbcutaneo_4N").prop('checked', false);
                $("#rbcutaneo_5S").prop('checked', false);
                $("#rbcutaneo_5N").prop('checked', false);
                $("#rbcutaneo_6S").prop('checked', false);
                $("#rbcutaneo_6N").prop('checked', false);
                $("#rbcutaneo_7S").prop('checked', false);
                $("#rbcutaneo_7N").prop('checked', false);
                $("#rbcutaneo_8S").prop('checked', false);
                $("#rbcutaneo_8N").prop('checked', false);
                $("#rbcutaneo_9S").prop('checked', false);
                $("#rbcutaneo_9N").prop('checked', false);

                /*Estado nutricional*/

                $("#imc").val("");
                $("#rbestadonut_1S").prop('checked', false);
                $("#rbestadonut_1N").prop('checked', false);
                $("#rbestadonut_2S").prop('checked', false);
                $("#rbestadonut_2N").prop('checked', false);
                $("#rbestadonut_3S").prop('checked', false);
                $("#rbestadonut_3N").prop('checked', false);
                $("#rbestadonut_4S").prop('checked', false);
                $("#rbestadonut_4N").prop('checked', false);
                $("#rbestadonut_5S").prop('checked', false);
                $("#rbestadonut_5N").prop('checked', false);
                $("#rbestadonut_6S").prop('checked', false);
                $("#rbestadonut_6N").prop('checked', false);
                $("#rbestadonut_7S").prop('checked', false);
                $("#rbestadonut_7N").prop('checked', false);

                /*categoria*/

                $("#categoria").val("");
                $('#categoria').change();
                $("#cambioCategoria").val("");
                $('#cambioCategoria').change();

                /*Manifestaciones hemorrágicas*/

                $("#rbmanhemo_1S").prop('checked', false);
                $("#rbmanhemo_1N").prop('checked', false);
                $("#rbmanhemo_2S").prop('checked', false);
                $("#rbmanhemo_2N").prop('checked', false);
                $("#rbmanhemo_3S").prop('checked', false);
                $("#rbmanhemo_3N").prop('checked', false);
                $("#rbmanhemo_4S").prop('checked', false);
                $("#rbmanhemo_4N").prop('checked', false);
                $("#rbmanhemo_5S").prop('checked', false);
                $("#rbmanhemo_5N").prop('checked', false);
                $("#rbmanhemo_6S").prop('checked', false);
                $("#rbmanhemo_6N").prop('checked', false);
                $("#rbmanhemo_7S").prop('checked', false);
                $("#rbmanhemo_7N").prop('checked', false);
                $("#rbmanhemo_8S").prop('checked', false);
                $("#rbmanhemo_8N").prop('checked', false);
                $("#rbmanhemo_9S").prop('checked', false);
                $("#rbmanhemo_9N").prop('checked', false);
                $("#rbmanhemo_10S").prop('checked', false);
                $("#rbmanhemo_10N").prop('checked', false);
                $("#rbmanhemo_11S").prop('checked', false);
                $("#rbmanhemo_11N").prop('checked', false);
                $("#rbmanhemo_12S").prop('checked', false);
                $("#rbmanhemo_12N").prop('checked', false);
                $("#rbmanhemo_13S").prop('checked', false);
                $("#rbmanhemo_13N").prop('checked', false);

                /*Preguntas para todos los pacientes*/

                $("#hospitalizado").val("");
                $('#hospitalizado').change();
                $("#unidadSaludHosp").val("");
                $("#transfusion").val("");
                $('#transfusion').change();
                $("#transfusionEsp").val("");
                $("#tomaMedicamento").val("");
                $('#tomaMedicamento').change();
                $("#cualMedicamento").val("");

                /*Exámenes del laboratorio*/

                $("#rbexamen_1S").prop('checked', false);
                $("#rbexamen_1N").prop('checked', false);
                $("#rbexamen_2S").prop('checked', false);
                $("#rbexamen_2N").prop('checked', false);
                $("#rbexamen_3S").prop('checked', false);
                $("#rbexamen_3N").prop('checked', false);
                $("#rbexamen_4S").prop('checked', false);
                $("#rbexamen_4N").prop('checked', false);
                $("#rbexamen_5S").prop('checked', false);
                $("#rbexamen_5N").prop('checked', false);
                $("#rbexamen_6S").prop('checked', false);
                $("#rbexamen_6N").prop('checked', false);
                $("#descOtroExamen").val("");

                /*Tratamiento*/

                $("#rbtratamiento_1S").prop('checked', false);
                $("#rbtratamiento_1N").prop('checked', false);
                $("#rbtratamiento_2S").prop('checked', false);
                $("#rbtratamiento_2N").prop('checked', false);
                $("#rbtratamiento_3S").prop('checked', false);
                $("#rbtratamiento_3N").prop('checked', false);
                $("#rbtratamiento_4S").prop('checked', false);
                $("#rbtratamiento_4N").prop('checked', false);
                $("#rbtratamiento_5S").prop('checked', false);
                $("#rbtratamiento_5N").prop('checked', false);
                $("#rbtratamiento_6S").prop('checked', false);
                $("#rbtratamiento_6N").prop('checked', false);
                $("#rbtratamiento_7S").prop('checked', false);
                $("#rbtratamiento_7N").prop('checked', false);
                $("#rbtratamiento_8S").prop('checked', false);
                $("#rbtratamiento_8N").prop('checked', false);
                $("#rbtratamiento_9S").prop('checked', false);
                $("#rbtratamiento_9N").prop('checked', false);
                $("#rbtratamiento_10S").prop('checked', false);
                $("#rbtratamiento_10N").prop('checked', false);
                $("#rbtratamiento_11S").prop('checked', false);
                $("#rbtratamiento_11N").prop('checked', false);
                $("#descOtroTratamiento").val("");

                /*planes, historia y diagnostico*/

                $("#planes").val("");
                $("#historia").val("");
                $("#dx").val("");
                $('#dx').change();
                $("#diagnostico1").val("");
                $('#diagnostico1').change();
                $("#diagnostico2").val("");
                $('#diagnostico2').change();
                $("#diagnostico3").val("");
                $('#diagnostico3').change();
                $("#diagnostico4").val("");
                $('#diagnostico4').change();

                /*fin de llenado de  checkbox*/
                /*Cierre*/

                $("#telefono").val("");
                $("#cita").val("");
                $("#medico").val("");
                $('#medico').change();
                $("#fechaMedico").val("");
                $('#fechaMedico').change();
                $("#horaMedico").val("");
                $("#enfermeria").val("");
                $('#enfermeria').change();
                $("#fechaEnfermeria").val("");
                $('#fechaEnfermeria').change();
                $("#horaEnfermeria").val("");

            }

            function search()
            {
                $.getJSON( parametros.searchUrl , formSearch.serialize() , function( data )   {

                        if (data.mensaje != undefined) {

                            toastr.error(data.mensaje,"Error",{timeOut: 5000});
                            $("#nombre").val("");
                            $("#fechanac").val("");
                            $("#edadPart").val("");
                            $("#sexoPart").val("");

                        }
                        else {
                            if (data.retirado != undefined)
                            {
                            swal.fire({
                                title: "A2CARES",
                                text: "Participante Retirado, confirmar ditigación",
                                type: "warning",
                                showCancelButton: true,
                                confirmButtonText: "Confirmar Digitación",
                                cancelButtonText: "Cancelar",

                            }).then(resultado => {
                                if (resultado.value) {
                                // Hicieron click en "Sí"
                                $("#codigoPart").val(data.codigo);
                                $("#nombre").val(data.nombre);
                                $("#fechanac").val(data.fechaNac);
                                $("#edadPart").val(data.edad);
                                $("#sexoPart").val(data.sexo);
                            }else{
                                $("#nombre").val("");
                                $("#fechanac").val("");
                                $("#edadPart").val("");
                                $("#sexoPart").val("");
                            }

                            });
                           }else{
                        $("#codigoPart").val(data.codigo);
                        $("#nombre").val(data.nombre);
                        $("#fechanac").val(data.fechaNac);
                        $("#edadPart").val(data.edad);
                        $("#sexoPart").val(data.sexo);
                    }

                        }
                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error");
                        toastr.error( "error:" + errorThrown);
                    });
            }
            function buscarsup()
            {
                $.getJSON( parametros.getcodSupervisor , formSearchCod.serialize() , function( data )   {

                        if (data.mensaje != undefined) {
                            toastr.success( "Código no Auotrizado:");
                            swal.fire({
                                title: "A2CARES",
                                text: "Código No Autorizado, contacte con el Administrador",
                                type: "warning",
                                cancelButtonText: 'Cancelar'
                            });
                            $("#codigoSupervisor").val('');
                        }
                        else {
                            $("#codigoSuper").val($("#codigoSupervisor").val());
                            toastr.success( "Supervisor Auotrizado:" + data[0].toString() );
                            swal.fire({
                                title: "A2CARES",
                                text: data[0].toString(),
                                type: "Info",
                                cancelButtonText: 'Cancelar'
                            });

                        }
                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error");
                        toastr.error( "error:" + errorThrown);
                    });
            }
            function buscarH1()
            {
                $.getJSON( parametros.getNumHojaDigitadaH1 , formSearchCod2.serialize() , function( data )   {
                       alert(data.mensaje);
                       if (data.mensaje != undefined) {
                            toastr.success( "A2CARES " + data.mensaje);
                            swal.fire({
                                title: "A2CARES",
                                text: data.mensaje,
                                type: "warning",
                                cancelButtonText: 'Cancelar'
                            });

                        }

                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error");
                        toastr.error( "error:" + errorThrown);
                    });
            }
            function buscarH2()
            {
                $.getJSON( parametros.getNumHojaDigitadaH2 , formSearchCod2.serialize() , function( data )   {

                        if (data.mensaje != undefined) {
                            toastr.success( "A2CARES " + data.mensaje);
                            swal.fire({
                                title: "A2CARES",
                                text: data.mensaje,
                                type: "warning",
                                cancelButtonText: 'Cancelar'
                            });

                        }

                    }
                ).fail(function(XMLHttpRequest, textStatus, errorThrown) {
                        console.log("error");
                        toastr.error( "error:" + errorThrown);
                    });
            }
        }
    };

}();