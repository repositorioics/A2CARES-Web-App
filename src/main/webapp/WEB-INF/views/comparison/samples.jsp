<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <style>
        div.dt-buttons {
            float: right !important;
        }
    </style>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
	<jsp:include page="../fragments/bodyHeader.jsp" />
    <div class="app-body">
        <jsp:include page="../fragments/sideBar.jsp" />
        <!-- Main content -->
        <div class="main">
        	<!-- Breadcrumb -->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/comparacion/muestras-ma" htmlEscape="true "/>"><spring:message code="comparison" /> <spring:message code="lbl.samples" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.samples.comparison.1" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_muestras1"  class="table table-striped table-bordered datatable" width="100%">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="code" /></th>
                                        <th><spring:message code="lbl.fecha.recepcion" /></th>
                                        <th><spring:message code="lbl.lugar" /></th>
                                        <th><spring:message code="lbl.volumen" /></th>
                                        <th><spring:message code="lbl.Observation" /></th>
                                        <th><spring:message code="lbl.supervisor" /></th>
                                    </tr>
                                    </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.samples.comparison.2" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_muestras2"  class="table table-striped table-bordered dt-responsive" width="100%">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="code" /></th>
                                        <th><spring:message code="lbl.fecha.recepcion" /></th>
                                        <th><spring:message code="lbl.lugar" /></th>
                                        <th><spring:message code="lbl.volumen" /></th>
                                        <th><spring:message code="lbl.Observation" /></th>
                                        <th><spring:message code="lbl.supervisor" /></th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.samples.comparison.3" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_muestras3"  class="table table-striped table-bordered dt-responsive" width="100%">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="code" /></th>
                                        <th><spring:message code="lbl.fecha.toma" /></th>
                                        <th><spring:message code="lbl.pinchazos" /></th>
                                        <th><spring:message code="lbl.encuestador" /></th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.samples.comparison.4" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_muestras4"  class="table table-striped table-bordered dt-responsive" width="100%">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="code" /></th>
                                        <th><spring:message code="lbl.fecha.toma" /></th>
                                        <th><spring:message code="lbl.pinchazos" /></th>
                                        <th><spring:message code="lbl.encuestador" /></th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.samples.comparison.5" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_muestras5"  class="table table-striped table-bordered dt-responsive" width="100%">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="code" /></th>
                                        <th><spring:message code="lbl.fecha.recepcion" /></th>
                                        <th><spring:message code="lbl.volumen" /></th>
                                        <th><spring:message code="lbl.Observation" /></th>
                                        <th><spring:message code="lbl.laboratorista" /></th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.samples.comparison.6" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_muestras6"  class="table table-striped table-bordered dt-responsive" width="100%">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="code" /></th>
                                        <th><spring:message code="lbl.fecha.recepcion" /></th>
                                        <th><spring:message code="lbl.volumen" /></th>
                                        <th><spring:message code="lbl.Observation" /></th>
                                        <th><spring:message code="lbl.laboratorista" /></th>
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
            <!-- /.conainer-fluid -->
        </div>
    </div>
    <jsp:include page="../fragments/bodyFooter.jsp" />
    <jsp:include page="../fragments/corePlugins.jsp" />
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
    <!-- Datatables  -->
    <spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
    <script src="${dataTableJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>

    <spring:url value="/comparacion/getCompSeroSupNoEstacionesHoy" var="sMuestras1Url"/>
    <spring:url value="/comparacion/getCompSeroSupNoLaboHoy" var="sMuestras2Url"/>
    <spring:url value="/comparacion/getCompSeroEstacionesNoSupHoy" var="sMuestras3Url"/>
    <spring:url value="/comparacion/getCompSeroEstacionesNoLabHoy" var="sMuestras4Url"/>
    <spring:url value="/comparacion/getCompSeroLabNoSupHoy" var="sMuestras5Url"/>
    <spring:url value="/comparacion/getCompSeroLabNoEstHoy" var="sMuestras6Url"/>

<script>
    jQuery(document).ready(function() {
        $('#lista_muestras1').DataTable({
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
                },
                {
                    extend: 'pdfHtml5',
                    orientation: 'portrait',
                    pageSize: 'LETTER'
                }
            ],
            "ajax":{
                url: "${sMuestras1Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigo', defaultContent: ""},
                { data: 'fechaRecepcion', defaultContent: ""},
                { data: 'lugar', defaultContent: ""},
                { data: 'volumen', defaultContent: ""},
                { data: 'observacion', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""}
            ]
        });

        $('#lista_muestras2').DataTable({
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
                },
                {
                    extend: 'pdfHtml5',
                    orientation: 'landscape',
                    pageSize: 'LEGAL'
                }
            ],
            "ajax":{
                url: "${sMuestras2Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigo', defaultContent: ""},
                { data: 'fechaRecepcion', defaultContent: ""},
                { data: 'lugar', defaultContent: ""},
                { data: 'volumen', defaultContent: ""},
                { data: 'observacion', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""}
            ]
        });

        $('#lista_muestras3').DataTable({
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
                },
                {
                    extend: 'pdfHtml5',
                    orientation: 'portrait',
                    pageSize: 'LETTER'
                }
            ],
            "ajax":{
                url: "${sMuestras3Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigo', defaultContent: ""},
                { data: 'fechaMuestra', defaultContent: ""},
                { data: 'pinchazos', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""}
            ]
        });

        $('#lista_muestras4').DataTable({
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
                },
                {
                    extend: 'pdfHtml5',
                    orientation: 'portrait',
                    pageSize: 'LETTER'
                }
            ],
            "ajax":{
                url: "${sMuestras4Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigo', defaultContent: ""},
                { data: 'fechaMuestra', defaultContent: ""},
                { data: 'pinchazos', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""}
            ]
        });

        $('#lista_muestras5').DataTable({
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
                },
                {
                    extend: 'pdfHtml5',
                    orientation: 'portrait',
                    pageSize: 'LETTER'
                }
            ],
            "ajax":{
                url: "${sMuestras5Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigo', defaultContent: ""},
                { data: 'fechaRecepcion', defaultContent: ""},
                { data: 'volumen', defaultContent: ""},
                { data: 'observacion', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""}
            ]
        });

        $('#lista_muestras6').DataTable({
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
                },
                {
                    extend: 'pdfHtml5',
                    orientation: 'portrait',
                    pageSize: 'LETTER'
                }
            ],
            "ajax":{
                url: "${sMuestras6Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigo', defaultContent: ""},
                { data: 'fechaRecepcion', defaultContent: ""},
                { data: 'volumen', defaultContent: ""},
                { data: 'observacion', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""}
            ]
        });

    });

    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();
</script>
</body>
</html>