/**
 * Created by miguel on 17/2/2022.
 */
function imprimirEtiquetas(strBarCodes){
    $.getJSON("http://localhost:13001/print", {
        barcodes: unicodeEscape(strBarCodes),
        copias: 1,
        ajax:'false'
    }, function (data) {
        console.log(data);
    }).fail(function (jqXHR) {
        console.log(jqXHR);
    });
}