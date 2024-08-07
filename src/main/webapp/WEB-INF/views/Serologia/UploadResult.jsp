<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html class="default-style">
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="${dtttcss}"/>-->

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/Serologia/listSerologia/" htmlEscape="true "/>"><spring:message code="List" /> <spring:message code="lbl.serologia" /></a>
            </li>
        </ol>

        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-list-alt"></i> <spring:message code="Resultados carga archivo excel BC6000" />
                </div>
                <div class="card-block">

                    <hr/>
                    <div class="container">
                        <div class="container-fluid flex-grow-1 container-p-y">
                            <jsp:useBean id="now" class="java.util.Date"/>

                            <div class="card mb-4">

                                <div class="card-body">

                                    <div class="import-results col-md-12 col-lg-12 col-xl-12">
                                        <strong><spring:message code="Resumen de Carga de Registros Nuevos a A2CARES BHC:" arguments="${nuevos}" htmlEscape="false"/></strong>
                                        <table id="lista_entidades" class="table table-striped table-bordered datatable" width="100%">
                                            <thead>
                                            <tr>
                                                <th><spring:message code="Id Muestra" /></th>
                                                <th><spring:message code="Fecha Procesamiento" /></th>
                                                <th><spring:message code="Hora Procesamiento" /></th>
                                                <th><spring:message code="Nombre Paciente" /></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${entidades}" var="entidad">
                                                <tr>
                                                    <td><c:out value="${entidad.id_muestr}" /></td>
                                                    <td><c:out value="${entidad.fec.toLocaleString().substring(0,10)}"/></td>
                                                    <td><c:out value="${entidad.hora}"/></td>
                                                    <td><c:out value="${entidad.nomb}"/></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="row justify-content-end">
                                            <spring:url value="/hojaclinica/cargarFileBc6000bhc/" var="listUrl"/>
                                            <a class="btn rounded-pill btn-outline-primary" href="${fn:escapeXml(listUrl)}"><spring:message code="end" /></a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
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

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>

<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/Serologia/ListSerologia.js" var="SeroJs" />
<script type="text/javascript" src="${SeroJs}"></script>
<script>
    jQuery(document).ready(function() {
        $('#btnPasive').tooltip('show');
        $('#btnEditar').tooltip('show');

        var misUrl ={
            "editUrl"               : "${editUrl}",
            "closeUrl"              : "${closeUrl}",
            "envioUrl"              : "${envioUrl}",
            "sendAllSerologiasUrl"  :"${sendAllSerologiasUrl}",
            "successMessage"        : "${successMessage}",
            "dataTablesLang"        : "${dataTablesLang}",
            "MxNoEnviadasUrl"       : "${MxNoEnviadasUrl}",
            listSerologiaUrl        : "${listSerologiaUrl}"
        };

        $('#lista_entidades').DataTable({
            dom: 'lBfrtip',
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            "bFilter": true,
            "bInfo": true,
            "bPaginate": true,
            "bDestroy": true,
            "responsive": true,
            "pageLength": 10,
            "lengthMenu": [5, 10, 20, 50, 100],
            "bLengthChange": true,
            "responsive": true,
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
</script>
</body>
</html>

