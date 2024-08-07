/**
 * Created by Everts Morales Reyes.
 */
var SearchPrintStickersMuestreoFormVal = function(){
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

                $.getJSON(urls.getCasaMuestreo, { codigo_casa : $('#codigo_casa').val(),   ajax : 'true'  },
                    function (data) {
                        Swal.fire({
                            title: "¡A2CARES!",
                            text: "El código de CASA ingresado permite imprimir todos los participantes enrolados en ese numero de casa.",
                            icon: "info",
                            timer: 4000
                        });
                        var len = data.length;
                      //  alert(len);
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
                       //     imprimirEtiquetas(data[0].codigo_casa + "*.*1*5");


                            for (var p = 0; p < len; p++) {

                                imprimirEtiquetas(data[p].codigo_participante + "*1*3");
                                imprimirEtiquetas("100" + data[p].codigo_participante + "*1*4");

                            }

                      //  for (var p = 0; p < len; p++) {




                      //  }

                        }
                        $.unblockUI();
                    }).fail(function(jqXHR) {
                        $.unblockUI();
                    });

                if (urls.getcontrol === "1") {
                    $.getJSON(urls.setIncrementarPart, {ajax : 'true'  }, function(data){
                        for (var i = 0; i < data.length; i++) {
                            if (data[i].desc_titulo_imprimir === 'PARTICIPANTES') {
                                $('#utlimo_participante_enrolado').val(data[i].ultimo_existente);
                                $('#utlimo_participante_impreso').val(data[i].ultimo_impreso);
                            }


                            if (data[i].desc_titulo_imprimir === 'CASAS') {
                                $('#utlima_casa_enrolada').val(data[i].ultimo_existente);
                                $('#utlima_casa_impresa').val(data[i].ultimo_impreso);
                            }
                        }
                        toastr.success("CODIGO GENERADO CON EXITO",{timeOut: 6000});
                        location.reload();
                    }).fail(function() {
                        swal("Error!", "Falló al obtener la información!", "error");
                    });
                }

              if (urls.getcontrol === "0"){

                  $.getJSON(urls.setIncrementarCasa, {ajax : 'true'  }, function(data){
                      for (var i = 0; i < data.length; i++) {
                          if (data[i].desc_titulo_imprimir === 'PARTICIPANTES') {
                              $('#utlimo_participante_enrolado').val(data[i].ultimo_existente);
                              $('#utlimo_participante_impreso').val(data[i].ultimo_impreso);
                          }


                          if (data[i].desc_titulo_imprimir === 'CASAS') {
                              $('#utlima_casa_enrolada').val(data[i].ultimo_existente);
                              $('#utlima_casa_impresa').val(data[i].ultimo_impreso);
                          }
                      }
                      toastr.success("CODIGO GENERADO CON EXITO",{timeOut: 6000});
                      location.reload();
                  }).fail(function() {
                      swal("Error!", "Falló al obtener la información!", "error");
                  });
              }
            }//fin funccion

            var form1 = $('#envio-allserologia-form');
                        
                        CargarDatos();



        }// fin init
    }
}();
