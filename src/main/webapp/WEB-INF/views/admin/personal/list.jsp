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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/personal" htmlEscape="true "/>"><spring:message code="personal" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-users"></i>&nbsp;<strong><spring:message code="personal" /></strong>
                        </h3>
                    </div>
                        <div class="row no-gutters row-bordered">
                            <div class="col-md-12 col-lg-12 col-xl-12">
                                <div class="card-body">
                                    <div class="row table-toolbar">
                                        <spring:url value="/admin/personal/add/"
                                                    var="addUrl">
                                        </spring:url>
                                        <div class="col-md-12">
                                            <div class="btn-group">
                                                <a class="btn btn-lg btn-success"
                                                   data-toggle="tooltip" data-placement="bottom" title="<spring:message code="addEntityToolTip" />"
                                                   href="${fn:escapeXml(addUrl)}"><i class="fa fa-plus"></i>     <spring:message code="add" /></a>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <table id="lista_entidades" class="table table-striped table-bordered datatable" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="code" /></th>
                                            <th><spring:message code="name" /></th>
                                            <th><spring:message code="charge" /></th>
                                            <th><spring:message code="enabled" /></th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${entidades}" var="entidad">
                                            <tr>
                                                <spring:url value="/admin/personal/edit/{codigo}/"
                                                            var="editUrl">
                                                    <spring:param name="codigo" value="${entidad.idPersona}" />
                                                </spring:url>
                                                <spring:url value="/admin/personal/disable/{codigo}/" var="disableUrl">
                                                    <spring:param name="codigo" value="${entidad.idPersona}" />
                                                </spring:url>
                                                <spring:url value="/admin/personal/enable/{codigo}/" var="enableUrl">
                                                    <spring:param name="codigo" value="${entidad.idPersona}" />
                                                </spring:url>
                                                <td><c:out value="${entidad.idPersona}" /></td>
                                                <td><c:out value="${entidad.nombre}" /></td>
                                                <td><c:out value="${entidad.cargo.spanish}" /></td>
                                                <c:choose>
                                                    <c:when test="${entidad.pasive eq '0'.charAt(0)}">
                                                        <td><span class="badge badge-success"><spring:message code="CAT_SINO_SI" /></span></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><span class="badge badge-danger"><spring:message code="CAT_SINO_NO" /></span></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>
                                                    <a href="${fn:escapeXml(editUrl)}" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="editEntidadToolTip" />" class="btn rounded-pill btn-outline-primary"><i class="fa fa-edit"></i></a>
                                                    <c:choose>
                                                        <c:when test="${entidad.pasive=='0'.charAt(0)}">
                                                        <button id="disable_entity" onclick="location.href='${fn:escapeXml(disableUrl)}'" type="button" class="btn rounded-pill btn-outline-danger"
                                                                data-toggle="tooltip" data-placement="bottom" title="<spring:message code="disableRecord" />" >
                                                        <i class="fas fa-trash"></i></button>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <button id="enable_entity" onclick="location.href='${fn:escapeXml(enableUrl)}'" type="button" class="btn rounded-pill btn-outline-success"
                                                                    data-toggle="tooltip" data-placement="bottom" title="<spring:message code="enableRecord" />" >
                                                            <i class="fas fa-check"></i></button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
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