/**
 * Created by ICS on 18/10/2020.
 */
var SearchControlAsistenciaFormVal = function(){
    return {
        init: function(urls){

            var table = $("#Lista_asistencia").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "bDestroy": true,
                "responsive": true,
                "columnDefs": [
                    {
                      //  "targets": [0],
                       // "visible": false,
                        "searchable": false
                    },{
                         targets: [1,2,3,4,5,6,7,8],
                        className: 'text-center'
                    }
                ]
            });

            function CargarDatos() {
                if ($("#desde").val() === "" && $("#hasta").val() === "")  {
                    Swal.fire({
                        title: "¡Serologia!",
                        html: "Favor seleccionar al menos un criterio de búsqueda:<br>" +
                            "<ul>" +
                            "<li>Rango de fecha desede</li>"+
                            "<li>Rango de fecha hasta</li>"+
                            "<li></li>"+
                            "</ul>",
                        icon: 'info',
                        timer: 4000
                    });
                } else {
                    table.clear().draw(false);
                    $.getJSON(urls.getAsistenciaPersonal,
                        form1.serialize(),
                        function (data) {
                            var len = data.length;
                            if (data == 0) {
                                Swal.fire({
                                    title: "¡Control de Asistencia!",
                                    text: "No se encontraron registro.",
                                    icon: "info",
                                    timer: 4000
                                });
                            } else {
                                total_ini = 0;
                                total_con = 0;
                                for (var i = 0; i < len; i++) {
                                    var partsUrl = urls.editUrl ;
                                    //var printUrl = 'http://localhost:13001/print?barcodes=' + data[i].codigoBarra;
                                 //   var botonUpdate = '<a id="btnEditar" class="btn rounded-pill btn-outline-warning btn-sm btnEditar" data-toggle="tooltip" data-placement="bottom" title="Editar" href=' + partsUrl + '><i class="fa fa-edit" aria-hidden="true"></i></a> ';
                                 //   var botonPrint  = '<button type="button" id="btnPrint" data-toggle="tooltip" data-placement="bottom" title="Imprimir"  class="btn rounded-pill btn-outline-primary btn-sm btnPrint" data-id="'+ data[i].codigoBarra + '"> <i class="fas fa-print" aria-hidden="true"></i>  </button>';

                                    table.row.add([
                                        data[i].nombre_usuario,
                                        data[i].nombre_completo,
                                        data[i].horaentrada,
                                        data[i].horasalida,
                                        data[i].fechaasistencia,
                                        data[i].identificador_equipo,
                                        data[i].latitud,
                                        data[i].longitud,
                                        data[i].fecha_registro

                                    ]).draw(false);
                                }
                            }
                        });


                }
            }//fin funccion



            function compararTodo() {
                var count = 0;
                $('.table').each(function () {
                    $(this).find('tablem').each(function () {
                        $(this).find('tr').each(function () {
                            $(this).show();
                            $(this).css('background-color', '');
                            $(this).find('td').css('color', 'black');
                            $(this).find('td').each(function (index, value) {
                                $(this).css('background-color', '');
                                $(this).show();
                            })
                            if (count == 1) {
                                $(this).css('background-color', '#ededed');
                                count = 0;
                            } else {
                                count++;
                                $(this).css('background-color', 'white');
                            }
                        })
                    })
                });
            }

            var form1 = $('#control_asistencia_form');
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
            /**************************/
        }// fin init
    }
}();
