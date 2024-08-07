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

    <spring:url value="../resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>

    <style>
        div.dataTables_wrapper div.dataTables_filter {
            text-align: right !important;
        }
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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/movil/getAsistenciaPersonal" htmlEscape="true "/>"></a>
            </li>
        </ol>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <spring:url value="/reportes/downloadConvalecientesMxEnfermoPdf/" var="pdfConvalecientesUrl"/>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                    <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="Control Asistencia" /></strong>
                    </h5>
                </div>
                <div class="card-block">

                    <form action="#" autocomplete="off" id="search-form" name="search-form" class="form-horizontal">

                    <div class="row">
                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                        </div>

                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                        </div>
                    </div>



                    </div>
                </div>

            </div>

        <div class="container-fluid">
            <div class="card">
                <div class="card-block">
                    <div class="row">


                            <div class="card-block">
                                <div class="row">
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
                                    <div class="col-md-12">
                                        <div class="btn-group float-left">
                                            <button id="searchClinicalSheet" class="btn btn-lg btn-primary " type="submit">
                                                <i class="fa fa-search"></i> <spring:message code="search" />
                                            </button>
                                        </div>
                                        <br>
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </form>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr/>
                    <table id="Lista_asistencia" class="table table-hover table-bordered" width="100%">
                        <thead>
                        <tr>

                            <th id="nombre_usuario"><spring:message code="Usuario"/></th>
                            <th id="nombre_completo"><spring:message code="Nombre Completo"/></th>
                            <th id="horaentrada"><spring:message code="Hora Entrada"/></th>
                            <th id="horasalida"><spring:message code="Hora Salida"/></th>
                            <th id="fechaasistencia"><spring:message code="Fecha Asistencia"/></th>
                            <td id="identificador_equipo"><b><spring:message code="Identificador de Equipo"/></b></th>
                            <td id="latitud"><b><spring:message code="Latitud"/></b></th>
                            <th id="longitud"><spring:message code="Longitud"/></th>
                            <th id="Cargo"><spring:message code="Cargo"/></th>
                            <th id="Area"><spring:message code="Area"/></th>


                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>


                </div>


        <div class="row">
            <table id="lsita_BHC" width="30%">
                <thead>
                <tr>

                    <td>

                    </td>
                </tr>
                </thead>
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


<spring:url value="/movil/getAsistenciaPersonal" var="getAsistenciaPersonal"/>



<script>
jQuery(document).ready(function() {


    $('.date-picker').datetimepicker({
        format: 'L',
        locale: "${lenguaje}",
        maxDate: new Date(),
        useCurrent: true
    });


    var edithoja = "EditarHC";
    var table = $('#Lista_asistencia').DataTable({
        dom: "<'row'<'col-sm-12 col-md-12'B>>" +
                "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
        "oLanguage": {
            "sUrl": "${dataTablesLang}"
        },
        "bFilter": true,
        "bInfo": true,
        "bPaginate": true,
        "bDestroy": true,
        "responsive": true,
        "pageLength": 10,
        "bLengthChange": true,
        "buttons": [
            {
                extend: 'excel'
            }
        ],
        "ajax":{
            url: "${getAsistenciaPersonal}", // Change this URL to where your json data comes from
            type: "GET",
            data: function(d) {
                d.desde = $("#fechaInicioCons").val();
                d.hasta = $("#fechaFinCons").val();
            },
            dataSrc: ""

        },
        "columns": [

            { data: 'nombre_usuario', defaultContent: ""},
            { data: 'nombre_completo', defaultContent: ""},
            { data: 'horaentrada', defaultContent: ""},
            { data: 'horasalida', defaultContent: ""},
            { data: 'fechaasistencia', defaultContent: ""},
            { data: 'identificador_equipo', defaultContent: ""},
            { data: 'latitud', defaultContent: ""},
            { data: 'longitud', defaultContent: ""},
            { data: 'cargo', defaultContent: ""},
            { data: 'area', defaultContent: ""}

        ]
    });

    $("#fechaInicioCons").on("dp.change", function (e) {
        $('#fechaFinCons').data("DateTimePicker").minDate(e.date);
    });
    $("#fechaFinCons").on("dp.change", function (e) {
        $('#fechaInicioCons').data("DateTimePicker").maxDate(e.date);
    });



    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();


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
} );
</script>
</body>
</html>

