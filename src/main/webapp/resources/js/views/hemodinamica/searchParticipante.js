/**
 * Created by Lizandro Serrano on 15/08/2024.
 */
var searchParticipante = function(){
'use strict';
    return {
        init: function(parametros){
           var table = $("#tablePartHemo").DataTable({
                "oLanguage": {
                    "sUrl": parametros.dataTablesLang
                },columnDefs: [{
                       targets: 0,
                       visible: false
                   }]
            });
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
                    table.clear().draw( false );
                    buscar(parametros);
                }
            });

            function buscar(parametros) {
                $.getJSON(parametros.listaHojaUrl, {parametro: $("#parametro").val(), ajax: 'true'}, function (data) {
                    var len = data.length;
                    if (len === 0) {
                        toastr.warning("Registro no encontrado.", "Advertencia!", {timeOut: 6000});
                        $("#parametro").focus();
                        return;
                    } else {
                        for(var i=0; i < len; i++){
                            var valor ="";
                            var fechaRegistro = moment(data[i].recordDate).format('DD/MM/YYYY HH:mm:ss');
                            var fechaConsulta =  moment(data[i].fecha).format('DD/MM/YYYY');
                            var id = data[i].idDatoHemo;
                            var editUrl = parametros.edithemoUrl + '/'+data[i].idDatoHemo;
                            var detailUrl = parametros.listDetailsHemoUrl+data[i].idDatoHemo;
                            var codigo = data[i].participante.codigo;
                            var nombreCompleto = data[i].participante.nombre1;
                            if(data[i].participante.nombre2 != null || data[i].participante.nombre2!='NA') nombreCompleto = nombreCompleto + " " + data[i].participante.nombre2;
                            nombreCompleto = nombreCompleto + " " + data[i].participante.apellido1;
                            if(data[i].participante.apellido2 !=null || data[i].participante.apellido2 !='NA') nombreCompleto = nombreCompleto + " " + data[i].participante.apellido2;
                            var edad = data[i].edad.substring(0,6);
                            table.row.add([
                                id,
                                codigo,
                                nombreCompleto,
                                edad,
                                fechaRegistro,
                                fechaConsulta,
                                valor = '<a class="btn btn-outline-primary btn-sm" href="'+ editUrl +'"><i class="fa fa-edit"></i></a>',
                                valor = '<a class="btn btn-outline-success btn-sm" href="'+ detailUrl +'"><i class="fa fa-history"></i></a>',
                                valor = '<a class="btn btn-outline-info btn-sm btnReporte" data-id="' + data[i].idDatoHemo + '"><i class="fa fa-book"></i></a>'
                            ]).draw(false);
                        }
                    }
                }).fail(function () {
                    toastr.error("Error Interno del Servidor!", "ERROR", {timeOut: 6000});
                    $("#parametro").val("").focus();
                })
            }
            $("#tablePartHemo tbody").on("click", ".btnReporte",function(){
                var id = $(this).data('id');
                SendId(id);
            });
            function SendId(id){
                window.open(parametros.pdfUrl+"?idDatoHemo="+id, '_blank');
            }
        }
    }
}();