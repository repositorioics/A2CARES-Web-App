var direct={};
var scanCarta = function(){
    return{
        init : function(parametroII){
            direct = parametroII;
            clearInput();
            $("#select-participante-form").validate({
                rules: {
                    parametro: 'required',
                    digits: true
                },errorPlacement: function ( error, element ) {
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
                    searchParticipante();
                }
            });

            function searchParticipante(){
                $.getJSON(parametroII.searchPartUrl, { parametro : $('#parametro').val(),   ajax : 'true'  }, function(data) {
                    console.info(data);
                    var codigo = $('#parametro').val();
                    if(data.msj!=null){
                        toastr.error("Código: " + codigo + " no encontrado","Error!",{timeOut:6000});
                    }
                    var len = data.length;
                    if(len==0){
                        swal("Error!","Código: " + codigo + " no encontrado","error");
                        clearInput();
                        $("#parametro").focus();
                    }
                    else{
                        if(data.estado == "1"){
                            toastr.warning("Participante: " + codigo + " Retirado!",{timeOut: 5000});
                            clearInput();
                            $("#parametro").focus();
                        }else{
                            clearInput();
                            $("#codigo").val(data.codigoParticipante);
                            $("#tutor").val(data.nombreTutor);
                            $("#txtNombreCompleto").val(data.nombreCompleto);
                            $("#edadyear").val(data.edadAnios);
                            $("#edadmeses").val(data.edadMes);
                            $("#edaddias").val(data.edadDia);
                            $("#estudios").val('A2CARE');
                            $("#padre").val(data.nombrePadre);
                            $("#madre").val(data.nombreMadre);
                            $("#nombfirma").val(data.name1Tutor);
                            $("#nombre2Firma").val(data.name2Tutor);
                            $("#apellido1Firma").val(data.surname1Tutor);
                            $("#apellido2Firma").val(data.surname2Tutor);
                            var relfamiliar = parseInt(data.realFam);
                            $("#relfam").val(relfamiliar).trigger('change.select2');
                            var text = "";
                            switch (data.realFam){
                                case "1":
                                    text = "Madre";
                                    break;
                                case "2":
                                    text = "Padre";
                                    break;
                                case "3":
                                    text="Abuelo(a)";
                                    break;
                                case "4":
                                    text ="Tio(a)";
                                    break;
                                case "5":
                                    text ="Hermano(a)";
                                    break;
                                case "6":
                                    text="Otra Relación Familiar";
                                    break;
                                default:
                                    text = "Participante";
                            }
                            $("#relacionFam").val(text);
                        }
                    }
                }).fail(function() {
                    toastr.error("500 Internal Server!","ERROR!",{timeOut: 5000});
                    $("#parametro").focus();
                });
            }

            $("#asentimiento").on("change", function(){
               var valor = $(this).val();
                if(valor == "2" || valor == "0"){
                    $("#tipoasentimiento").val("0").trigger('change.select2');
                }
            });

            $("#carta").on("change", function(){
                $('#version').val("").trigger('change.select2');
                $('#version').select2().empty();
                if ($('#version').prop("disabled") == false) {}
                if ($("#carta").val() == null  || $("#carta").val() == "") {
                    $('#version').empty();
                    $('#version').append($('<option></option>').val('').html('Seleccion Versión'));
                    $("#version").prop('disabled',true);
                    $('#proyecto').val('').trigger('change.select2');
                    return;
                }
                if ($(this).val() == 3) {
                    $('#proyecto').val(3).trigger('change.select2');
                } else if ($(this).val() == 5) {
                    $('#proyecto').val(5).trigger('change.select2');
                } else if ($(this).val() == 4) {
                    $('#proyecto').val(2).trigger('change.select2');
                } else {
                    $('#proyecto').val(4).trigger('change.select2');
                }
                $("#partes").prop('disabled',false);
                $("#version").prop('disabled',false);
                ObtenerVersion(parametroII);
            });
            function ObtenerVersion(parametros){
                var idcarta = document.getElementById('carta').value;
                var $version = $('#version');
                $.getJSON(parametros.VersionCartatUrl, { idcarta : idcarta,   ajax : 'true'  }, function(data) {
                    $version.empty();
                    var len = data.objV.length;
                    if(len == 0){

                    }else{
                        var d = data.objV;
                        $version.append($('<option></option>').val('').html('Selecciona la Versión'));
                        $.each(d, function (i, val) {
                        $version.append($('<option></option>').val(val.idversion).html(val.version));
                        });
                    }
                });
            }

            $("#version").on("change", function(){
                //debugger;
                $("#partes").select2("val", "");
                $("#partes").empty();
                if($("#version").val() == "") {
                    $("#DivPartes").hide(1000);
                    $("#DivPartes").empty();
                 }
                else {
                    $("#partes").prop('disabled',false);
                    $("#partes").val(null).trigger("change");
                    $("#DivPartes").show(1000);
                    $("#partes").empty();
                    ObtenerParte(parametroII);
                }
            });//fin
            var bandera = false;
            $("#partes").change(function (e) {
                if (e.added != null){
                    seleccionar(e.added.id);
                }
                if (e.removed != null){
                    deseleccionar(e.removed.id);
                }
            });

            $("#partes").on("select2-removing", function(e) {
             debugger;
                var p = $("#principal").val();
                if (e.choice.text === p) {
                    e.preventDefault();
                    $(this).select2("close");
                }
             });

            function seleccionar(id){
                var cod = parseInt(id);
                for (var i = 0; i < elementos.length; i++) {
                    if (elementos[i].idparte === cod){
                        elementos[i].acepta = true;
                        break;
                    }
                }
            }

            function deseleccionar(id) {
                var cod = parseInt(id);
                for (var i = 0; i < elementos.length; i++) {
                    if (elementos[i].idparte === cod) {
                        elementos[i].acepta = false;
                        break;
                    }
                }
            }

            var elementos = [];
            function ObtenerParte(parametros){
                var idversion = document.getElementById('version').value;
                var $ele = $("#partes");
                $.getJSON(parametros.ParteVersionUrl,{idversion : idversion, ajax:'true'}, function(data){
                    console.log(data);
                     elementos = [];
                    for(var i=0; i < data.parte.length; i++){
                        var obj = {};
                        obj.idparte = parseInt(data.parte[i].idparte);
                        obj.acepta =  (data.parte[i].acepta == "true") ? true : false;
                        obj.locked =   data.parte[i].principal;
                        elementos.push(obj);
                    }
                    if(data.parte.length > 0){
                        bandera=true;
                        $("#principal").val('');
                        $.each(data.parte, function (i, val) {
                            debugger;
                            var option = new Option(val.parte, val.idparte, false, val.principal );
                            $ele.append(option).trigger('change');
                            if(val.principal){
                                seleccionar(val.idparte);
                            }
                        });
                        $("#principal").val($("#partes").find('option:selected').text());

                    }else{
                        $ele.empty();
                    }
                })
            }
            //Validar las cajas de texto...
            $('.onlytext').keypress(function (e) {
                var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
                return !((tecla > 47 && tecla < 58) || tecla == 46);
            });

            $("#btnCancel").on("click", function(e){
                var num= $("#partes").select2().val();
                if($.isEmptyObject(num)){
                    alert("Selecciona al menos una opción");
                    return;
                }else{
                    console.log("else Id : " + num);
                }
            });

            $('#form-scan').submit(function(e){
                e.preventDefault();
                var isOK = ValidateForm();
                if (isOK) {
                    debugger;
                    var text = $("#person option:selected").html();
                    var separador = "-";
                    var textoseparado = text.split(separador);
                    data = {
                        codigo: parseInt($("#codigo").val().trim()),
                        version: parseInt($("#version").val().trim()),
                        person: parseInt($("#person").val().trim()),
                        relfam: parseInt($("#relfam").val().trim()),
                        asentimiento: $("#asentimiento").val().trim(),
                        nombfirma: $("#nombfirma").val().trim(),
                        nombre2Firma: $("#nombre2Firma").val().trim(),
                        apellido1Firma: $("#apellido1Firma").val().trim(),
                        apellido2Firma: $("#apellido2Firma").val().trim(),
                        fechacarta: $("#fechacarta").val(),
                        proyecto: $("#proyecto").val(),
                        contactoFuturo: ($('input:checkbox[name=contactoFuturo]').prop('checked') == true) ? '1' : '0',
                        testigopresente: ($('input:checkbox[name=chktestigo]').prop('checked') == true) ? '1' : '0',
                        nombre1testigo: $("#nombre1Testigo").val().trim(),
                        nombre2testigo: $("#nombre2Testigo").val().trim(),
                        apellido1testigo: $("#apellido1Testigo").val().trim(),
                        apellido2testigo: $("#apellido2Testigo").val().trim(),
                        observacion :$("#observacion").val().trim(),
                        edadyears: $("#edadyear").val().trim(),
                        edadmeses: $("#edadmeses").val().trim(),
                        edaddias:  $("#edaddias").val().trim(),
                        recurso: textoseparado[0].trim(),
                        tipoasentimiento: $("#tipoasentimiento").val().trim(),
                        parte: elementos
                    };
                    GuardarScan(data);
                }
            });
            function ValidateForm() {
                var isAllValid = true;
                $('.form-group').removeClass('is-invalid');

                if( isNaN( $("#codigo").val() )){
                    isAllValid = false;
                    $("#codigo").addClass('is-invalid');
                }else{
                    $("#codigo").removeClass('is-invalid');
                }

                if($("#codigo").val().trim() == "" || $("#codigo").val().trim() == null){
                    isAllValid = false;
                    $('#codigo').addClass('is-invalid');
                }
                else{
                    $('#carta').removeClass('is-invalid');
                }

                if($("#carta").val().trim() == "" || $("#carta").val().trim() == null){
                    isAllValid = false;
                    $('#carta').addClass('is-invalid');
                }
                else{
                    $('#carta').removeClass('is-invalid');
                }

                if($("#nombfirma").val().trim() == "" || $("#nombfirma").val().trim() == null){
                    isAllValid = false;
                    $('#nombfirma').addClass('is-invalid');
                }
                else{
                    $('#nombfirma').removeClass('is-invalid');
                }

                if($("#apellido1Firma").val().trim() == "" || $("#apellido1Firma").val().trim() == null){
                    isAllValid = false;
                    $('#apellido1Firma').addClass('is-invalid');
                }
                else{
                    $('#apellido1Firma').removeClass('is-invalid');
                }

                if($("#version").val() == null || $("#version").val() == ""){
                    isAllValid = false;
                    $('#version').addClass('is-invalid');
                }
                else{
                    $('#version').removeClass('is-invalid');
                }

                var num = $("#partes").select2().val();
                if($.isEmptyObject(num)){
                    $('#partes').addClass('is-invalid');
                    isAllValid = false;
                }else{
                    $('#partes').removeClass('is-invalid');
                }

                if ($('#relfam').val() == null || $('#relfam').val() == "") {
                    $('#relfam').addClass('is-invalid');
                    isAllValid = false;
                }
                else {
                    $('#relfam').removeClass('is-invalid');
                }

                if($("#proyecto").val()=="" || $("#proyecto").val()== null){
                    $('#proyecto').addClass('is-invalid');
                    isAllValid = false;
                }else{
                    $('#proyecto').removeClass('is-invalid');

                }
                if($("#person").val()=="" || $("#person").val()== null){
                    isAllValid = false;
                    $('#person').addClass('is-invalid');
                }
                else{
                    $('#person').removeClass('is-invalid');
                }
                 if($("#fechacarta").val()=="" || $("#fechacarta").val()== null){
                    isAllValid = false;
                    $('#fechacarta').addClass('is-invalid');
                 } else{
                   $('#fechacarta').removeClass('is-invalid');
                 }

                if($("#asentimiento").val()=="" || $("#asentimiento").val()== null){
                    isAllValid = false;
                    $('#asentimiento').addClass('is-invalid');
                } else{
                    $('#asentimiento').removeClass('is-invalid');
                }

                if($("#tipoasentimiento").val()=="" || $("#tipoasentimiento").val()== null){
                    isAllValid = false;
                    $('#tipoasentimiento').addClass('is-invalid');
                } else{
                    $('#tipoasentimiento').removeClass('is-invalid');
                }

                return isAllValid;
            }


            function GuardarScan(obj){
                $.ajax({
                    url: direct.saveScanCartaUrl,
                    type: "POST",
                    data: JSON.stringify(obj),
                    dataType: "JSON",
                    contentType:'application/json;charset=utf-8',
                    success: function(response){
                        if(response.msj != null){
                            toastr.warning(response.msj,{timeOut: 5000});
                        }else{
                            clearInput();
                            toastr.success(direct.successmessage);
                            window.setTimeout(function(){
                                window.location.href = direct.cartaSaveEditUrl+"/"+response.idparticipantecarta;
                            }, 1500);
                        }
                    },error: function(jqXHR, textStatus,e){
                        toastr.error(textStatus,"ERROR",{timeOut:6000});
                    }
                });
            }

            $("#chktestigo").on("click", function(){
                var status = $(this).prop("checked");
                if(status == true){
                    $("#showDivTestigo").fadeIn("slow");
                    $("#nombre1Testigo").prop('required',true);
                    $("#apellido1Testigo").prop('required',true);
                }else{
                    $("#showDivTestigo").fadeOut("slow");
                    $("#nombre1Testigo").val("").prop('required', false);
                    $("#apellido1Testigo").val("").prop('required', false);
                }
            });

            $("#asentimiento").on("change",function(){
                if($(this).val() === "0"){
                    $('#tipoasentimiento').val("0").trigger('change.select2');
                }else{
                    $('#tipoasentimiento').val("").trigger('change.select2');
                    $("#tipoasentimiento").select2("open");
                }
            });


            $( "#nombfirma" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getNombre1Url, {nombre1: $('#nombfirma').val().trim(), ajax: 'true'},function(data){
                        response($.map(data, function (value, key) {
                            return {
                                label: value
                            };
                        }));
                    });
                },minLength: 3,
                scroll: true,
                highlight: true
            });

            $( "#nombre2Firma" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getNombre2Url, {nombre2: $('#nombre2Firma').val().trim(), ajax: 'true'},function(data){
                        response($.map(data, function (value, key) {
                            return {
                                label: value
                            };
                        }));
                    });
                },minLength: 3,
                scroll: true,
                highlight: true
            });


            $( "#apellido1Firma" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getApellido1Url, {apellido1: $('#apellido1Firma').val().trim(), ajax: 'true'},function(data){
                        response($.map(data, function (value, key) {
                            return {
                                label: value
                            };
                        }));
                    });
                },minLength: 3,
                scroll: true,
                highlight: true
            });

            $( "#apellido2Firma" ).autocomplete({
                delay:100,
                source: function(request, response){
                    $.getJSON(direct.getApellido2Url, {apellido2: $('#apellido2Firma').val().trim(), ajax: 'true'},function(data){
                        response($.map(data, function (value, key) {
                            return {
                                label: value
                            };
                        }));
                    });
                },minLength: 3,
                scroll: true,
                highlight: true
            });



            function clearInput(){
                $("#txtNombreCompleto").val("");
                $("#principal").val("");
                $("#edad").val("");
                $("#idParticipante").val("");
                $("#estudios").val("");
                $("#madre").val("");
                $("#padre").val("").change();
                $("#relfam").val('').trigger('change.select2');
                $("#tutor").val("");
                $("#codigo").val("");
                $("#fechacarta").val('');
                $('#carta').val('').trigger('change.select2');
                $('#version').val('').trigger('change.select2');
                $("#version").select2("val", "");
                $('#partes').val('').trigger('change.select2');
                $('#partes').val(null).trigger('change');
                $('#person').val('').trigger('change.select2');
                $('#proyecto').val('').trigger('change.select2');
                $('#asentimiento').val('').trigger('change.select2');
                $('#tipoasentimiento').val('').trigger('change.select2');
                $("#edadyear").val("");
                $("#edadmeses").val("");
                $("#edaddias").val("");
                $("#parametro").val("");
                $("#chktestigo").prop("checked",false);
                $("#showDivTestigo").fadeOut("slow");
                $("#nombre1Testigo").val("").prop('required', false);
                $("#nombre2Testigo").val("").prop('required', false);
                $("#apellido1Testigo").val("").prop('required', false);
                $("#apellido2Testigo").val("").prop('required', false);
                $("#contactoFuturo").prop("checked",false);
            }
       }
    }
}();





