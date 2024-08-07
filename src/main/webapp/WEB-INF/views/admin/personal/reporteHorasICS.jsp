<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 17/10/2020
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page  import = "ni.org.ics.webapp.web.utils.Constants" %>
<html>
<head>
    ad>
    <jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>



    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<%--
    <script src="/static/js/jquery-3.6.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
--%>
    <style>
        .dropup .dropdown-menu,.navbar-fixed-bottom .dropdown .dropdown-menu {
            /*top: auto;*/
            /*bottom: 100%;*/
            margin-bottom: 200px;
        }

        div.dataTables_wrapper div.dataTables_filter {
            text-align: right !important;
        }
        .select-dropdown {
            /* ... */

            transform: scaleY(0);
            opacity: 0;
            visibility: hidden;
        }

        /* .... */

        /* interactivity */
        .custom-select.active .arrow {
            transform: rotate(180deg);
        }

        .custom-select.active .select-dropdown {
            opacity: 1;
            visibility: visible;
            transform: scaleY(1);
        }
        .anchura{
            display:block;
            height:50px;
            width:500px;

        }
        table tr th {
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        .sorting {
            background-color: #D4D4D4;
        }

        .asc:after {
            content: ' ↑';
        }

        .desc:after {
            content: " ↓";
        }
        tfoot {
            font-weight: bold;
        }
        .encabezado {
            background-color: black;
        }
        .encabezado-celda-division {
            display: flex;
        }
        .encabezado-celda_texto {
            border-right: 1px solid;
        }
        .encabezado-celda-division_texto {
            border: 1px solid;
        }
        .contenedor {
            width: 300px;
        }
        #div2{
            /* border: 1px solid black;*/
            width: 400;
            display:inline-block;
            margin-bottom: 12px;
        }
        #div2 { overflow-x: scroll;}
    </style>
    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../../fragments/sideBar.jsp" />
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/movil/getHorasTrabajadasICS" htmlEscape="true "/>"></a>
            </li>
        </ol>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <spring:url value="/reportes/downloadConvalecientesMxEnfermoPdf/" var="pdfConvalecientesUrl"/>
        <spring:url value="/reportes/downloadFileReporteHorasICSExcel/" var="excelReporteHorasIcs"/>

        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                    <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="Reporte de Horas - Personal ICS" /></strong>
                    </h5>
                </div>
                <div class="card-block">
                </div>

                    <form action="#" autocomplete="off" id="search-form" name="search-form" class="form-horizontal">
                        <div class="container-fluid">
                        <div class="card">
                           <div class="card-block">
                               <div class="row">
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   <div class="col-sm-2">
                                       <div class="form-group">
                                           <label class="form-control-label" for="sitio"><spring:message code="Sitio" />

                                           </label>

                                           <select name="sitio" id="sitio" class="form-control" type="text" style="height: 35px;width: 280px" onclick="cargarDepto();">
                                               <option selected value=""><spring:message code="select" />...</option>
                                               <c:forEach items="${sitios}" var="s">
                                                   <c:choose>
                                                       <c:when test="${s.id eq '0'}">
                                                           <option selected value="${s.id}">${s.id} - <spring:message code="${s.descripcion}" /></option>
                                                       </c:when>
                                                       <c:otherwise>
                                                           <option value="${s.id}">${s.id} - <spring:message code="${s.descripcion}" /></option>
                                                       </c:otherwise>
                                                   </c:choose>
                                               </c:forEach>
                                           </select>
                                       </div>
                                   </div>
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   <div class="col-sm-2">
                                       <div class="form-group">
                                           <label class="form-label"><spring:message code="Departamento" /></label> <span class="badge badge-dot badge-danger"></span>
                                           <select class="form-control" id="depto" name="depto" style="width: 100%; height: 30px" data-allow-clear="false">
                                               <option selected value=""><spring:message code="select" />...</option>
                                               <c:forEach items="${depto}" var="s">
                                                   <c:choose>
                                                       <c:when test="${s.id eq '0'}">
                                                           <option selected value="${s.id}">${s.id} - <spring:message code="${s.descripcion}" /></option>
                                                       </c:when>
                                                       <c:otherwise>
                                                           <option value="${s.id}">${s.id} - <spring:message code="${s.descripcion}" /></option>
                                                       </c:otherwise>
                                                   </c:choose>
                                               </c:forEach>
                                           </select>
                                       </div>
                                   </div>
                               </div>
                                <div class="row">
                                    &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <div class="form-group">
                                            <label class="form-control-label" for="empleados"><spring:message code="Seleccione Colaborador" />

                                            </label>
                                        <div id="cont" class="contenedor">
                                            <select name="userId" id="userId" class="selectpicker  form-control" data-live-search="true" data-live-search-normalize="true"   style="width:150px">
                                                <option selected="true" value=""><spring:message code="select" />...</option>
                                                <c:forEach items="${empleados}" var="s">
                                                    <c:choose>
                                                        <c:when test="${s.id eq '0'}">
                                                            <option selected value="${s.id}">${s.descripcion}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${s.id}">${s.descripcion}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                    <div class="col-lg-3 col-md-3 col-sm-12">
                                        <div class="form-group">
                                            <label class="form-control-label" for="fechaInicioCons"><spring:message code="fecha_inicio" />
                                                <span class="required">*</span>
                                            </label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                                <input type="text" class="form-control date-picker" id="fechaInicioCons" name="fechaInicioCons" placeholder="<spring:message code="fecha_holder" />"/>
                                            </div>
                                        </div>
                                    </div>
                                    &nbsp; &nbsp;
                                    <div class="col-lg-3 col-md-3 col-sm-12">
                                        <div class="form-group">
                                            <label class="form-control-label" for="fechaFinCons"><spring:message code="fecha_fin" />
                                                <span class="required">*</span>
                                            </label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                                <input type="text" class="form-control date-picker" id="fechaFinCons" name="fechaFinCons" placeholder="<spring:message code="fecha_holder" />"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="BuscarEmpleado" class="col-md-12">
                                        <div class="btn-group float-left">
                                            <button id="searchClinicalSheet" class="btn btn-lg btn-primary " type="submit">
                                                <i class="fa fa-search"></i> <spring:message code="search" />
                                            </button>
                                        </div>
                                        <br>
                                        <br>
                                    </div>
                                    <div id="BuscarTodoICS" class="col-md-12">
                                        <div class="p-2 bd-highlight"></div>
                                        <div class="p-2 bd-highlight">
                                            <button id="toExcel" type="submit" class="btn btn-info btn-ladda" data-style="expand-right"><i class="far fa-file-excel"></i> <spring:message code="generate" /> <spring:message code="Excel" /> </button>
                                        </div>
                                        <div class="p-2 bd-highlight"></div>
                                    </div>

                                </div>
                           </div>
                        </div>
                        </div>
                        </form>
                <div class="div2">
                    <hr/>

                    <table id="Lista_asistencia" class="table table-striped table-bordered datatable" >
                        <thead>
                        <tr>
                            <th id="IdUser"><spring:message code="IdUser"/></th>
                            <th id="Fecha"><spring:message code="Fecha"/></th>
                            <th id="Semana"><spring:message code="Semana"/></th>
                            <th id="Horas"><spring:message code="Horas"/></th>
                            <th id="Veces"><spring:message code="Veces"/></th>
                            <th id="HorasT"><spring:message code="Horas Trabajadas"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </div>
        </div>

</div>
</div>

    </div>


<jsp:include page="../../fragments/bodyFooter.jsp" />
<jsp:include page="../../fragments/corePlugins.jsp" />
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<!-- Datatables  -->
<spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<!-- bootstrap datetimepicker -->
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/moment-with-locales.js" var="moment" />
<script src="${moment}"></script>
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepicker" />
<script src="${datetimepicker}"></script>


<spring:url value="/resources/js/libs/sweetalert.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>


<spring:url value="/resources/js/views/unicodeEscaper.js" var="escaperJs" />
<script type="text/javascript" src="${escaperJs}"></script>

<spring:url value="/resources/js/views/printBarcodeLabels.js" var="printJs" />
<script type="text/javascript" src="${printJs}"></script>

<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>



<spring:url value="/movil/getHorasTrabajadasICS" var="getHorasTrabajadasICS"/>
<spring:url value="/movil/getNombreEmpleadosICS" var="getNombreEmpleadosICS"/>
<%--
<spring:url value="/resources/vendor/libs/select2/select2_{language}.js" var="select2Lang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${select2Lang}"></script>

<!-- Custom scripts required by this view -->
<spring:url value="/resources/js/views/personal/cargarSitiosDepto.js" var="processStorage" />
<script src="${processStorage}"></script>

<spring:url value="/resources/vendor/libs/validate/messages_{language}.js" var="validateLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${validateLang}"></script>

<spring:url value="/resources/vendor/libs/masonry/masonry.js" var="masonry" />
<script src="${masonry}" type="text/javascript"></script>--%>

<spring:url value="/movil/getSitiosICS" var="getSitiosICS"/>
<script type="text/javascript" src="${getSitiosICS}"></script>
<spring:url value="/movil/getDepartamentosICS" var="getDepartamentosICS"/>
<script type="text/javascript" src="${getDepartamentosICS}"></script>



<script>
jQuery(document).ready(function() {

    $.fn.dataTable.ext.errMode = 'none';
    $('.date-picker').datetimepicker({
        format: 'L',
        locale: "${lenguaje}",
        maxDate: new Date(),
        useCurrent: true
    });

    var edithojas = "Justificar";
    var edithojasn = "NO";
    var edithoja = "EditarHC";

    var table = $('#Lista_asistencia').DataTable({
        dom: "<'row'<'col-sm-12 col-md-12'B>>" +
                "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
        "oLanguage": {
            "sUrl": "${dataTablesLang}"
        },
        "oAria": {
            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
        },
        "bFilter": true,
        "pageLength": 10,
        order: [[5, 'asc']],
        "bInfo": true,
        "bPaginate": true,
        "bDestroy": true,
        "responsive": true,
        "bLengthChange": true,
        "responsive": true,
        "buttons": [
            {
                extend: 'excel'
            }
        ],
        "ajax":{
            url: "${getHorasTrabajadasICS}", // Change this URL to where your json data comes from
            type: "GET",
            data: function(d) {
                d.desde = $("#fechaInicioCons").val();
                d.hasta = $("#fechaFinCons").val();
                d.id = $("#userId").val();
                d.idSitio = $("#sitio").val();
                var filtros = {};
                filtros['userId'] = $('#userId').val();
                filtros['fecha'] = $('#fecha').val();
                d.strFilter = JSON.stringify(filtros);
                return d;
            },
            dataSrc: ""

        },
        "columns": [

            { data: 'id',  defaultContent: "" },
            { data: 'fecha', defaultContent: ""},
            { data: 'semana', defaultContent: ""},
            { data: 'horas', defaultContent: ""},
            { data: 'veces', defaultContent: ""},
            { data: 'resta', defaultContent: ""}
        ]

    });


    $("#fechaInicioCons").on("dp.change", function (e) {
        $('#fechaFinCons').data("DateTimePicker").minDate(e.date);
    });
    $("#fechaFinCons").on("dp.change", function (e) {
        $('#fechaFinCons').data("DateTimePicker").minDate(e.date);
    });


    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();



   var parametros = {saveUrl: "${saveUrl}", successmessage: "${successmessage}",
        errormessage: "${errormessage}",waitmessage: "${waitmessage}",
        seleccionar: "${seleccionar}" ,lenguaje: "${lenguaje}",
        getSitiosICS: "${getSitiosICS}" ,getDepartamentosICS: "${getDepartamentosICS}" ,boxUrl: "${boxUrl}" ,listUrl: "${listUrl}"
    };
   /* ProcessStorage.init(parametros);*/
    var formSearch = $('#search-form');
    formSearch.validate({
        focusInvalid: false, // do not focus the last invalid input
        rules: {
            fechaInicioCons: {required: function () {
                    return $('#fechaFinCons').val().length > 0;
                }},
            fechaFinCons: {required: function () {
                    return $('#fechaInicioCons').val().length > 0;
                }}
        },

        submitHandler: function (form) {
            console.log("buscar");
            search();
        }
    });
    function search()
    {
        table.ajax.reload();
    }
    /* $('#lista_cartas1').on('click', 'td.editor-enabled', function (e) {
     e.preventDefault();
     console.log(table.cell( this ).data());
     var objeto= table.cell( this ).data();
     if (objeto.enabled){
     mostrarDeshabilitar("${disableUrl}"+objeto.username);
     } else {
     mostrarHabilitar("${enableUrl}"+objeto.username);
     }
     });*/
    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
    $("#toExcel").on("click", function(){
        var $validarForm = form1.valid();
        if (!$validarForm) {
            $validator.focusInvalid();
            return false;
        } else {
            window.open("${excelReporteHorasIcs}?" + form1.serialize())
        }
    });
} );

/*new DataTable('#Lista_asistencia', {
    order: [[5, 'asc']]
});*/
var parametros = {saveUrl: "${saveUrl}", successmessage: "${successmessage}",
    errormessage: "${errormessage}",waitmessage: "${waitmessage}",
    seleccionar: "${seleccionar}" ,lenguaje: "${lenguaje}",
    getSitiosICS: "${getSitiosICS}" ,getDepartamentosICS: "${getDepartamentosICS}" ,boxUrl: "${boxUrl}" ,listUrl: "${listUrl}"
};
function cargarDepto()
{
    $('#sitio').change(
        function() {
            $.blockUI({ message: parametros.waitmessage });
            $(".masonry-grid").empty();
            $.getJSON(parametros.getDepartamentosICS, {
                id : $('#sitio').val(),
                ajax : 'true'
            }, function(data) {
                var html='<option value=""></option>';
                var len = data.length;
                for ( var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].id + '">'
                        + data[i].descripcion + '</option>';
                }
                $('#depto').html(html);
                $('#depto').focus();
                // $('#depto').select2('open');
            });
            $.unblockUI();
            MostrarOcultarDivBuscarEmpleado();
        });

}
function MostrarOcultarDivBuscarEmpleado() {
    var x = document.getElementById("BuscarEmpleado");
    var y = document.getElementById("BuscarTodoICS");
    if (x.style.display === "none") {
        x.style.display = "block";
        y.style.display = "none";
    } else {
        x.style.display = "none";
        y.style.display = "block";
    }
}
    function MostrarOcultarDivBuscarTodoICS() {
        var x = document.getElementById("BuscarTodoICS");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
}

</script>
</body>
</html>

