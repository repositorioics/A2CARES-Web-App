/**
 * Created by Everts Morales Reyes.
 */
var SearchPrintStickersFormVal = function(){
    return {
        init: function(urls){

            var total_enf = 0;
            var total_ini = 0;
            var total_con = 0;

            var table = $("#Lista_Muestra").DataTable({
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
                         targets: [1,2,3,4,5,6,7,8,9,10,11],
                        className: 'text-center'
                    }
                ]
            });

            function CargarDatos() {

                    table.clear().draw(false);
                    $.getJSON(urls.getControlSecCodigos,
                        form1.serialize(),
                        function (data) {
                            var len = data.length;
                            if (data == 0) {
                                Swal.fire({
                                    title: "¡A2CARES!",
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
                                    if (data[i].desc_titulo_imprimir === 'PARTICIPANTES') {
                                        $('#utlimo_participante_enrolado').val(data[i].ultimo_existente) ;
                                        $('#utlimo_participante_impreso').val(data[i].ultimo_impreso);
                                    }


                                    if (data[i].desc_titulo_imprimir === 'CASAS') {
                                        $('#utlima_casa_enrolada').val(data[i].ultimo_existente) ;
                                        $('#utlima_casa_impresa').val(data[i].ultimo_impreso);
                                    }
                                    var enviado = "<span class='badge badge-danger'><i class='fas fa-thumbs-down'></i></span>";
                                    if (data[i].enviado === '1') {
                                        enviado = "<span class='badge badge-success'><i class='fas fa-thumbs-up'></i></span>";
                                    }
                                }
                            }
                        });

            }//fin funccion

            var form1 = $('#envio-allserologia-form');

                        CargarDatos();


            $("#Lista_Muestra tbody").on("click", ".btnPrint",function(){
                var id = $(this).data('id');
             //    imprimirEtiquetas(id);
            });
            /**************************/
        }// fin init
    }
}();
