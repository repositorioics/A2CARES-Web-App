/**
 * Created by ICS on 18/10/2020.
 */
var SearchMxEnfermosForm = function(){
    return {
        init: function(urls){
            var table = $("#Lista_Muestra").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "bDestroy": true,
                "responsive": true,
                "columnDefs": [
                    {
                        "targets": [0],
                        "visible": false,
                        "searchable": false
                    },{
                        targets: [1,2,3,4,5,6,7,8,9,10,11,12],
                        className: 'text-center'
                    }
                ]
            });

            function CargarDatos() {
                if ($("#desde").val() === "" && $("#hasta").val() === "" && $("#participante").val() === "" && $("#muestra").val() === "") {
                    Swal.fire({
                        title: "¡Serologia!",
                        html: "Favor seleccionar al menos un criterio de búsqueda:<br>" +
                            "<ul>" +
                            "<li>Rango de fecha de recepción</li>"+
                            "<li>Código de participante</li>"+
                            "<li>Código de muestra</li>"+
                            "</ul>",
                        icon: 'info',
                        timer: 4000
                    });
                } else {
                    table.clear().draw(false);
                    $.getJSON(urls.searchUrl,
                        form1.serialize(),
                        function (data) {
                            var len = data.length;
                            if (data == 0) {
                                Swal.fire({
                                    title: "¡Serologia!",
                                    text: "No se encontraron registro.",
                                    icon: "info",
                                    timer: 4000
                                });
                            } else {
                                for (var i = 0; i < len; i++) {
                                    console.log(data[i].codigoBarra);
                                    var partsUrl = urls.editUrl + '/' + data[i].idRecepcion+'/0';
                                    //var printUrl = 'http://localhost:13001/print?barcodes=' + data[i].codigoBarra;
                                    var botonUpdate = '<a id="btnEditar" class="btn rounded-pill btn-outline-warning btn-sm btnEditar" data-toggle="tooltip" data-placement="bottom" title="Editar" href=' + partsUrl + '><i class="fa fa-edit" aria-hidden="true"></i></a> ';
                                    var botonPrint  = '<button type="button" id="btnPrint" data-toggle="tooltip" data-placement="bottom" title="Eliminar"  class="btn rounded-pill btn-outline-primary btn-sm btnPrint" data-id="'+ data[i].codigoBarra + '"> <i class="fas fa-print" aria-hidden="true"></i>  </button>';

                                    var enviado = "<span class='badge badge-danger'><i class='fas fa-thumbs-down'></i></span>";
                                    if (data[i].enviado === '1') {
                                        enviado = "<span class='badge badge-success'><i class='fas fa-thumbs-up'></i></span>";
                                    }
                                    table.row.add([
                                        data[i].idRecepcion,
                                        data[i].participante,
                                        data[i].fechaRecepcion,
                                        data[i].volumen,
                                        data[i].observacion,
                                        data[i].fis,
                                        data[i].fif,
                                        data[i].categoria,
                                        data[i].consulta,
                                        data[i].tipoMuestra,
                                        enviado,
                                        botonUpdate , botonPrint
                                    ]).draw(false);
                                }
                            }
                        });
                }
            }//fin funccion

            var form1 = $('#envio-allserologia-form');
            var $validator = form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    desde: {required: function () {
                        return $('#hasta').val().length > 0;
                    }},
                    hasta: {required: function () {
                        return $('#desde').val().length > 0;
                    }}
                },errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    }else if ( element.prop( 'type' ) === 'text' ){
                        error.insertAfter(element.parent('.input-group'));
                    } else {
                        error.insertAfter( element ); //cuando no es input-group
                    }
                },highlight: function ( element, errorClass, validClass ) {
                    $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                    $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
                },unhighlight: function (element, errorClass, validClass) {
                    $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                    $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
                },submitHandler: function (form) {
                    var $validarForm = form1.valid();
                    if (!$validarForm) {
                        $validator.focusInvalid();
                        return false;
                    } else {
                        CargarDatos();
                    }
                }
            });

            $("#Lista_Muestra tbody").on("click", ".btnPrint",function(){
                var id = $(this).data('id');
                imprimirEtiquetas(id);
            });
            /**************************/
        }// fin init
    }
}();
