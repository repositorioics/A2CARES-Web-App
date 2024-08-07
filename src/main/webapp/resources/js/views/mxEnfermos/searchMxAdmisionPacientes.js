/**
 * Created by ICS on 18/10/2020.
 */
var SearchMxAdmisionPacientes = function(){
    return {
        init: function(urls){

            var total_enf = 0;
            var total_ini = 0;
            var total_con = 0;
            var table2 = $("#Lista_Admision_Pacientes").DataTable({
                "oLanguage": {
                    "sUrl": urls.dataTablesLang
                },
                "bFilter": true,
                "bInfo": true,
                "bPaginate": true,
                "bDestroy": true,
                "responsive": true,
                "columnDefs": [
                    {
                        //  "targets": [0],
                        // "visible": false,
                        "searchable": false
                    },{
                        targets: [1,2,3,4,5,6,7,8,9,10],
                        className: 'text-center'
                    }
                ]
            });
            var form1 = $('#envio-allserologia-form');
            CargarDatos2();
            function CargarDatos2() {
                if ($("#desde").val() === "" && $("#hasta").val() === ""  ) {
                    Swal.fire({
                        title: "¡Mx Enfermos!",
                        html: "Favor seleccionar al menos un criterio de búsqueda:<br>" +
                            "<ul>" +
                            "<li>Rango de fecha </li>"+
                            "</ul>",
                        icon: 'info',
                        timer: 4000
                    });
                } else {
                    table2.clear().draw(false);
                    $.getJSON(urls.getAdmisionPacientes,
                        form1.serialize(),
                        function (data) {
                            var len = data.length;
                            if (data == 0) {
                                Swal.fire({
                                    title: "¡Mx Enfermos - Admisión!",
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
                                    var botonUpdate = '<a id="btnEditar" class="btn rounded-pill btn-outline-warning btn-sm btnEditar" data-toggle="tooltip" data-placement="bottom" title="Editar" href=' + partsUrl + '><i class="fa fa-edit" aria-hidden="true"></i></a> ';
                                    var botonPrint  = '<button type="button" id="btnPrint" data-toggle="tooltip" data-placement="bottom" title="Imprimir"  class="btn rounded-pill btn-outline-primary btn-sm btnPrint" data-id="'+ data[i].codigoBarra + '"> <i class="fas fa-print" aria-hidden="true"></i>  </button>';

                                    var pertenece = "<span class='badge badge-danger'><i class='fas fa-thumbs-down'></i></span>";
                                    var febril = "<span class='badge badge-danger'><i class='fas fa-thumbs-down'></i></span>";
                                    if (data[i] === '1') {
                                        enviado = "<span class='badge badge-success'><i class='fas fa-thumbs-up'></i></span>";
                                    }
                                    table2.row.add([

                                        data[i].fecha_registro,
                                        data[i].numero_hoja,
                                        data[i].pertenece_estudio,
                                        data[i].febril,
                                        data[i].sexo,
                                        data[i].edad,
                                        data[i].usuario_registro


                                    ]).draw(false);
                                }
                                $('#total_convalecientes').val("Total Muestras: " + len);


                            }
                        });


                }
            }//fin funccion

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

                        CargarDatos2();
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
