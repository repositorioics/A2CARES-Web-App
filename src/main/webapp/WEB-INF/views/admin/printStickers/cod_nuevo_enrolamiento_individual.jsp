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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/ps/stickers/getControlSecCodigos" htmlEscape="true "/>"><spring:message code="Impresión de Stickers" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card" style="background-color: white">
                    <div class="card-header" style="background-color: #17a2b8 ">
                        <h3 class="page-title">
                            <i class="fa fa-qrcode"></i>&nbsp;<strong><spring:message code="Re-Impresión de Códigos Nuevos o Existentes" /></strong>
                        </h3>
                    </div>
                    <form name="envio-allserologia-form" id="envio-allserologia-form">
                        <div class="row no-gutters row-bordered" align="center">
                            <div class="col-md-12 col-lg-12 col-xl-12">
                                <div class="card-body">
                                    <div class="row table-toolbar">
                                        <spring:url value="/ps/stickers/getControlSecCodigos/"
                                                    var="addUrl">
                                        </spring:url>
                                        <div   class="col-md-12">
                                            <div class="btn-group">

                                                <div style="width: 190px; height: 60px; margin-left: 160px" >
                                                    <label ><spring:message code="Imprimir Casa:" />
                                                    </label>
                                                    <input type="text"  id="imprimir_casa" name="imprimir_casa" class="form-control" placeholder="Código de Casa" aria-describedby="basic-addon1">
                                                </div>



                                            </div>
                                            <button type="button" id="btnPrint1" style="width: 60px; height: 60px"  data-toggle="tooltip" data-placement="bottom" title="Imprimir Casa"  class="btn btn-outline-success"   onclick="print_nuevo_casa()"> <i class="fas fa-print"></i>
                                            </button>
                                        </div>
                                            </div>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <div class="row table-toolbar" style=" margin-left: 25px" >
                                        <spring:url value="/admin/personal/add/"
                                                    var="addUrl">
                                        </spring:url>
                                        <div  class="col-md-12"  >
                                            <div class="btn-group"  >

                                                <div style="width: 250px; height: 60;margin-left: 160px" >
                                                    <label ><spring:message code="Imprimir Participante:" />
                                                    </label>
                                                    <input type="text"  id="imprmir_participante" name="imprmir_participante" class="form-control" placeholder="Imprimir Participante" aria-describedby="basic-addon1">
                                                </div>

                                            </div>
                                            <button type="button" id="btnPrint" style="width: 60px; height: 60px"  data-toggle="tooltip" data-placement="bottom" title="Imprimir Participante"  class="btn btn-outline-success"   onclick="print_nuevo_part()"> <i class="fas fa-print"></i>
                                            </button>

                                        </div>

                                    </div>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <div class="row table-toolbar" style=" margin-left: 25px" >
                                        <spring:url value="/admin/personal/add/"
                                                    var="addUrl">
                                        </spring:url>
                                        <div  class="col-md-12"  >
                                            <div class="btn-group"  >

                                                <div style="width: 250px; height: 60;margin-left: 160px" >
                                                    <label ><spring:message code="Imprimir Participante BHC:" />
                                                    </label>
                                                    <input type="text"  id="imprmir_participanteBHC" name="imprmir_participanteBHC" class="form-control" placeholder="Imprimir Participante BHC" aria-describedby="basic-addon1">
                                                </div>

                                            </div>
                                            <button type="button" id="btnPrint" style="width: 60px; height: 60px"  data-toggle="tooltip" data-placement="bottom" title="Imprimir Participante BHC"  class="btn btn-outline-success"   onclick="print_nuevo_part_bhc()"> <i class="fas fa-print"></i>
                                            </button>

                                        </div>

                                    </div>

                                        </div>
                                    </div>
                                    <br>
                                    <br>

                                    <br>


                                </div>
                            </div>

                        </div>
                    </form>

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

    <spring:url value="/resources/js/views/searchPrintStickers.js" var="SeroJs" />
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
    jQuery(document).ready(function() {
        var misUrl ={
            "getControlSecCodigos"     : "${getControlSecCodigos}",
            "getrecepcion"             : "${getMxRecepcionEnfermo}",
            "envioUrl"              : "${envioUrl}",
            "successMessage"        : "${successMessage}",
            "dataTablesLang"        : "${dataTablesLang}",
            "searchUrl"       : "${getTablasMxEnfermos}"
        };
        SearchPrintStickersFormVal.init(misUrl);
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
    function print_nuevo_part(){
          var ulpart = $("#imprmir_participante").val()
        if(ulpart.length != 4)
        {
            toastr.error("LONGITUD DE CODIGO DEBE SER DE 4 POSICIONES",{timeOut: 6000});

        }else{
        var id1 =  ulpart + "*1*3";
        imprimirEtiquetas(id1);
        }
    }
    function print_nuevo_casa(){
        var ulpart = $("#imprimir_casa").val()
        if(ulpart < 0 || ulpart > 3000)
        {
            toastr.error("CODIGO DEBE SER UN VALOR POSITIVO Y MENOR A 3000",{timeOut: 6000});

        }else{
        var id1 =  ulpart + "*1*5";
        imprimirEtiquetas(id1);
        }
    }
    function print_nuevo_part_bhc(){
        var ulpart = $("#imprmir_participanteBHC").val()
        if(ulpart.length  != 4)
        {
            alert(ulpart.length );
            toastr.error("LONGITUD DE CODIGO DEBE SER DE 4 POSICIONES",{timeOut: 6000});

        }else{
        var id = "100" + ulpart + "*1*4";
        imprimirEtiquetas(id);
        }

    }
</script>
</body>
</html>