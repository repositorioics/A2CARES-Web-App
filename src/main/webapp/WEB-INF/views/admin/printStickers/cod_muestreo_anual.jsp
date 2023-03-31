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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/ps/stickers/getCasaMuestreo" htmlEscape="true "/>"><spring:message code="Impresión de Stickers" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card" style="background-color: white">
                    <div class="card-header" style="background-color: #17a2b8 ">
                        <h3 class="page-title">
                            <i class="fa fa-qrcode"></i>&nbsp;<strong><spring:message code="Impresión de Stickers de Participantes de A2CARES" /></strong>
                        </h3>
                    </div>
                                 <form name="envio-allserologia-form" id="envio-allserologia-form">
                                        <div class="row no-gutters row-bordered" align="center">
                                        <div class="col-md-12 col-lg-12 col-xl-12">
                                          <div class="card-body">
                                             <div class="row table-toolbar">
                                                 <spring:url value="/ps/stickers/getCasaMuestreo" var="getCasaMuestreo"/>
                                                 <spring:url value="/ps/stickers/getCasaMuestreo1" var="getCasaMuestreo1"/>

                                                    <div class="col-md-12">
                                                      <div class="btn-group">
                                                         <div style="width: 190px; height: 60px; margin-left: 125px" >
                                                            <label   ><spring:message code="Dígite Código de Casa a Imprimir:" />
                                                            </label>
                                                            <input type="text"   id="codigo_casa" name="codigo_casa" class="form-control" placeholder="Código de Casa a Imprimir" aria-describedby="basic-addon1" tabindex="1" >
                                                          </div>
                                                       </div>
                                                          <button type="button" id="btnPrint1" style="width: 60px; height: 60px"  data-toggle="tooltip" data-placement="bottom" title="Imprimir Casa"  class="btn btn-outline-success" onclick="print_casa()"> <i class="fas fa-print"></i>
                                                          </button>
                                                     </div>
                                             </div>
                                              <br>
                                              <br>
                                              <br>
                                              <br>
                                          </div>
                                      </div>

                                     </div>
                                    </form>
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

    <spring:url value="/resources/js/libs/sweetalert.js" var="sw" />
    <script type="text/javascript" src="${sw}"></script>

    <spring:url value="/resources/js/views/searchPrintStickersMuestreo.js" var="SeroJs" />
    <script type="text/javascript" src="${SeroJs}"></script>

    <spring:url value="/resources/js/views/unicodeEscaper.js" var="escaperJs" />
    <script type="text/javascript" src="${escaperJs}"></script>

    <spring:url value="/resources/js/views/printBarcodeLabels.js" var="printJs" />
    <script type="text/javascript" src="${printJs}"></script>

    <spring:url value="/ps/stickers/getControlSecCodigos" var="getControlSecCodigos"/>
    <!-- Mensajes -->
    <c:set var="entityEnabledLabel"><spring:message code="enabled" /></c:set>
    <c:set var="entityDisabledLabel"><spring:message code="disabled" /></c:set>

<script>
    $(document).ready(function(){

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

    });
    function print_casa(){
        var misUrl ={
            "getCasaMuestreo"     : "${getCasaMuestreo}",
            "getrecepcion"             : "${getMxRecepcionEnfermo}",
            "envioUrl"              : "${envioUrl}",
            "successMessage"        : "${successMessage}",
            "dataTablesLang"        : "${dataTablesLang}",
            "searchUrl"       : "${getTablasMxEnfermos}"
        };
        SearchPrintStickersMuestreoFormVal.init(misUrl);


    }
</script>
</body>
</html>