/**
 * Created by Lizandro Serrano on 04/09/2024.
 */
var DetalleList = function(){
    return{
        init: function(parametro){
            $("#tblHistorial").DataTable({
                "oLanguage": {
                    "sUrl": parametro.dataTablesLang
                },"columnDefs": [{
                    "targets": [0],
                    "visible": false
                }]
            });
            $('#tblHistorial tbody').on('click', '.btnView', function () {
                debugger;
                var id = $(this).data('id');
                ver(id);
            });
            function ver(id){
                $.getJSON(parametro.searchResultUrl, { idHemoDetalle : id,   ajax : 'true'  }, function(data){
                    $("#exampleModal").modal("show");
                    $("#ps").text(data.ps);
                    $("#pd").text(data.pd);
                    $("#pp").text(data.pp);
                    $("#pam").text(data.pam);
                    $("#fcardi").text(data.fcardi);
                    $("#fr").text(data.fr);
                    $("#tc").text(data.tc);
                    $("#sa").text(data.sa);
                    $("#diuresis").text(data.diuresis);
                    $("#cantidadOrina").text(data.cantidadOrina);
                    $("#cargas_iv").text(data.cargasIV);
                    $("#sro").text(data.sro);
                    $("#densidadU").text(data.densidadU);
                    $("#Persona").text(data.personaValida);
                })
            }
        }
    }
}();

