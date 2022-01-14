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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/puntos/" htmlEscape="true "/>"><spring:message code="lbl.gps" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h3 class="page-title">
                        <i class="fas fa-map-marked"></i> <strong><spring:message code="lbl.gps" /></strong>
                    </h3>
                </div>
                <div class="row no-gutters row-bordered">
                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <div class="card-body">
                            <table class="table table-striped table-bordered dt-responsive" width="100%" id="lista_puntos">
                                <thead>
                                <tr>
                                    <th><spring:message code="code" /></th>
                                    <th><spring:message code="lbl.neighborhood" /></th>
                                    <th><spring:message code="lbl.latitude" /></th>
                                    <th><spring:message code="lbl.longitude" /></th>
                                    <th><spring:message code="lbl.discarded" /></th>
                                    <th><spring:message code="lbl.discard.reason" /></th>
                                    <th><spring:message code="lbl.discard.date" /></th>
                                    <th><spring:message code="lbl.discard.user" /></th>
                                </tr>
                                </thead>
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

<!-- Datatables  -->
<spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<!-- JQUERY BLOCK UI -->
<spring:url value="/resources/js/libs/jquery.blockui.min.js" var="jqueryBlockUi" />
<script src="${jqueryBlockUi}"></script>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>

<spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/puntos/getAll" var="sPuntosUrl"/>
<script>
    jQuery(document).ready(function() {
        $('#lista_puntos').DataTable({

            dom: "<'row'<'col-sm-12 col-md-12'B>>" +
                    "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
            buttons: [
                'excel', 'pdf'
            ],"oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            responsive: true,
            ajax:{
                url: "${sPuntosUrl}", // Change this URL to where your json data comes from
                type: "GET", // This is the default value, could also be POST, or anything you want.
                dataSrc: ""
            },
            columns: [
                { data: 'codigo'},
                { data: 'Barrio' },
                { data: 'latitud' },
                { data: 'longitud'},
                { data: 'descartado',
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        if(oData.descartado === '1') {
                            $(nTd).html("<span class='badge badge-danger'><spring:message code='CAT_SINO_SI' /></span>");
                        } else {
                            $(nTd).html("<span class='badge badge-success'><spring:message code='CAT_SINO_NO' /></span>");
                        }
                    }
                },
                { data: 'razonDescarte',
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        if(oData.razonDescarte === 'Otra razón') {
                            $(nTd).html(oData.otraRazonDescarte);
                        } else {
                            $(nTd).html(oData.razonDescarte);
                        }
                    },
                  defaultContent: ""
                },
                { data: 'fechaDescarte',
                    defaultContent: ""
                },
                { data: 'usuarioDescarte',
                    defaultContent: ""
                }
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