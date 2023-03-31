<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <style>
        div.dt-buttons {
            float: right !important;
        }
    </style>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
	<jsp:include page="../../fragments/bodyHeader.jsp" />
    <div class="app-body">
        <jsp:include page="../../fragments/sideBar.jsp" />
        <!-- Main content -->
        <div class="main">
        	<!-- Breadcrumb -->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/ps/stickers/" htmlEscape="true "/>"><spring:message code="Impresión de Stickers" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card" style="background-color: white ">
                    <div class="card-header" style="background-color: #17a2b8 ">
                        <h3 class="page-title">
                            <i class="fa fa-qrcode"></i>&nbsp;<strong><spring:message code="Impresión de Stickers" /></strong>
                        </h3>
                    </div>
                        <div class="row no-gutters row-bordered" align="center">
                            <div class="col-md-12 col-lg-12 col-xl-12">
                                <div class="card-body">
                                    <div class="row table-toolbar">
                                        <spring:url value="/ps/stickers/nuevosStickers/"
                                                    var="nuevosStickers">
                                        </spring:url>

                                        <div class="col-md-12">
                                            <div class="btn-group">
                                                <a class="btn btn-lg btn-success"
                                                   data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Imprime códigos nuevos para nuevo enrolamiento." />"
                                                   href="${fn:escapeXml(nuevosStickers)}"><i class="fa fa-plus"></i>     <spring:message code="Nuevo Enrolamiento" /></a>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="row table-toolbar" align="center" >
                                        <spring:url value="/ps/stickers/CasaMuestreo/"
                                                    var="CasaMuestreo">
                                        </spring:url>
                                        <div class="col-md-12">
                                            <div class="btn-group">
                                                <a class="btn btn-lg btn-success"
                                                   data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Imprime códigos de los participantes existentes para muestreo Anual." />"
                                                   href="${fn:escapeXml(CasaMuestreo)}"><i class="fa fa-plus"></i>     <spring:message code="Sitckers Muestreo" /></a>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="row table-toolbar" align="center" >
                                        <spring:url value="/ps/stickers/ReimpresionCodigos/"
                                            var="ReimpresionCodigos">
                                            </spring:url>
                                            <div class="col-md-12">
                                            <div class="btn-group">
                                                <a class="btn btn-lg btn-success"
                                                   data-toggle="tooltip" data-placement="bottom" title="<spring:message code="Re-Imprime códigos." />"
                                                   href="${fn:escapeXml(ReimpresionCodigos)}"><i class="fa fa-plus"></i>     <spring:message code="Re-Impresión de Sitckers" /></a>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>

                        </div>

                </div>

            </div>
            <!-- /.conainer-fluid -->
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
    <!-- Datatables  -->
    <spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
    <script src="${dataTableJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <spring:url value="/ps/stickers/getControlSecCodigos" var="getControlSecCodigos"/>
    <!-- Mensajes -->
    <c:set var="entityEnabledLabel"><spring:message code="enabled" /></c:set>
    <c:set var="entityDisabledLabel"><spring:message code="disabled" /></c:set>
<script>
    jQuery(document).ready(function() {
        $('#lista_entidades').DataTable({
            //dom: 'lBfrtip',
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
            ]
        });
    });

    if ("${enabledEntity}"){
        Swal.fire("${entityName}", "${entityEnabledLabel}", 'success');
    }
    if ("${disabledEntity}"){
        Swal.fire("${entityName}", "${entityDisabledLabel}", 'error');
    }

    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();
</script>
</body>
</html>