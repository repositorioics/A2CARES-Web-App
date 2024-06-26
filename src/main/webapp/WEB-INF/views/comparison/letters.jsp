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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/comparacion/cartas" htmlEscape="true "/>"><spring:message code="comparison" /> <spring:message code="letters" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.letters.comparison.1" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_cartas1"  class="table table-striped table-bordered datatable" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="code"/></th>
                                            <th><spring:message code="fecha"/></th>
                                            <th><spring:message code="usuario"/></th>
                                            <th><spring:message code="edad_actual"/></th>
                                            <th><spring:message code="contacto_futuro"/></th>
                                            <th><spring:message code="asent"/></th>
                                            <th><spring:message code="parteA_App"/></th>
                                            <th><spring:message code="parteB_App"/></th>
                                            <th><spring:message code="parteC_App"/></th>
                                            <th><spring:message code="quien_firma_App"/></th>
                                            <th><spring:message code="rel_fam_App"/></th>
                                            <th><spring:message code="version"/></th>
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
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.letters.comparison.2" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <div class="row">
                                    <table id="lista_cartas2"  class="table table-striped table-bordered dt-responsive" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="code"/></th>
                                            <th><spring:message code="echa"/></th>
                                            <th><spring:message code="usuario"/></th>
                                            <th><spring:message code="edad_actual"/></th>
                                            <th><spring:message code="edad_firma"/></th>
                                            <th><spring:message code="parteA_App"/></th>
                                            <th><spring:message code="parteA_Car"/></th>
                                            <th><spring:message code="parteB_App"/></th>
                                            <th><spring:message code="parteB_Car"/></th>
                                            <th><spring:message code="parteC_App"/></th>
                                            <th><spring:message code="parteC_Car"/></th>
                                            <th><spring:message code="version_App"/></th>
                                            <th><spring:message code="version_Car"/></th>
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

                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.letters.comparison.3" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_cartas3"  class="table table-striped table-bordered dt-responsive" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="code"/></th>
                                            <th><spring:message code="fecha"/></th>
                                            <th><spring:message code="usuario"/></th>
                                            <th><spring:message code="edad_firma"/></th>
                                            <th><spring:message code="quien_firma_App"/></th>
                                            <th><spring:message code="quien_firma_Car"/></th>
                                            <th><spring:message code="rel_fam_App"/></th>
                                            <th><spring:message code="rel_fam_Car"/></th>
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
    <spring:url value="/comparacion/getCartasSinDigitar" var="sCartas1Url"/>
    <spring:url value="/comparacion/getCartasPartes" var="sCartas2Url"/>
    <spring:url value="/comparacion/getCartasRelFam" var="sCartas3Url"/>


<script>
    jQuery(document).ready(function() {
        $('#lista_cartas1').DataTable({
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
                url: "${sCartas1Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigoParticipante', defaultContent: ""},
                { data: 'fechaFirma', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""},
                { data: 'edadActual', defaultContent: ""},
                { data: 'contactoFuturo', defaultContent: ""},
                { data: 'asentimiento', defaultContent: ""},
                { data: 'parteA', defaultContent: ""},
                { data: 'parteB', defaultContent: ""},
                { data: 'parteC', defaultContent: ""},
                { data: 'quienFirma', defaultContent: ""},
                { data: 'relacionFamiliar', defaultContent: ""},
                { data: 'versionCarta', defaultContent: ""}
            ]
        });

        $('#lista_cartas2').DataTable({
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
                url: "${sCartas2Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigo', defaultContent: ""},
                { data: 'fechaFirma', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""},
                { data: 'edadActualMeses', defaultContent: ""},
                { data: 'edadMeses', defaultContent: ""},
                { data: 'aceptaParteASc', defaultContent: ""},
                { data: 'aceptaParteACc', defaultContent: ""},
                { data: 'aceptaParteBCc', defaultContent: ""},
                { data: 'aceptaParteBSc', defaultContent: ""},
                { data: 'aceptaParteCCc', defaultContent: ""},
                { data: 'aceptaParteCSc', defaultContent: ""},
                { data: 'versionCc', defaultContent: ""},
                { data: 'versionSc', defaultContent: ""}
            ]
        });

        $('#lista_cartas3').DataTable({
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
                url: "${sCartas3Url}", // Change this URL to where your json data comes from
                type: "GET",
                dataSrc: ""
            },
            "columns": [
                { data: 'codigoParticipante', defaultContent: ""},
                { data: 'fechaFirma', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""},
                { data: 'edadFirma', defaultContent: ""},
                { data: 'quienFirmaC', defaultContent: ""},
                { data: 'quienFirmaS', defaultContent: ""},
                { data: 'relacionFamiliarC', defaultContent: ""},
                { data: 'relacionFamiliarS', defaultContent: ""}
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