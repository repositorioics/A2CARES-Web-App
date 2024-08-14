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
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
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
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/mx/enfermo/list" htmlEscape="true "/>"><spring:message code="lbl.list" /> <spring:message code="lbl.serologia.recepcion" /></a>
            </li>
        </ol>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>

        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                    <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="RECEPCION MUESTREO A2CARES" /></strong>
                    </h5>
                </div>
                <div class="card-block">

                    <form name="envio-allserologia-form" id="envio-allserologia-form">

                    <div class="row">
                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8 col-xl-6">
                                <div class="row form-group">
                                    <label class="col-xs-12 col-sm-2 col-md-3 col-lg-3 col-xl-3 col-form-label" for="desde"><spring:message code="lbl.from" />
                                    </label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-9 col-xl-6">
                                        <input type="text" class="form-control from_date datepicker" id="desde" name="desde" data-date-end-date="+0d">
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <label class="col-xs-12 col-sm-2 col-md-3 col-lg-3 col-xl-3 col-form-label" for="hasta"><spring:message code="lbl.until" />
                                    </label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-9 col-xl-6">
                                        <input type="text" class="form-control to_date datepicker" id="hasta" name="hasta" data-date-end-date="+0d">
                                    </div>
                                </div>

                        </div>
                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                        </div>
                    </div>
                        <div class="row">
                            <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8 col-xl-6">
                                <div class="form-group center">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block"> <i class="fas fa-search" aria-hidden="true"></i>
                                        <spring:message code="Extraer Muestras" />
                                        <spring:message code="De Supervisor, Recepción y Encuestadores" />
                                    </button>
                                </div>
                            </div>
                            <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="card-header">
                            <h5 class="page-title">
                                <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="Tabla de Supervisores" /></strong>
                            </h5>
                            <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Total Supervisores en este Rango: </span>
                            </div>
                            <input type="text" class=""  id="total_supervisor" name="total_supervisor" class="form-control" placeholder="Total Supervisores" aria-describedby="basic-addon1" >
                            <input type="text"  id="total_rojo" name="total_rojo" class="form-control" placeholder="Total Rojo" aria-describedby="basic-addon1">
                            <input type="text"  id="total_bhc" name="total_bhc" class="form-control" placeholder="Total Bhc"aria-describedby="basic-addon1" >
                        </div>
                        </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr/>
                            <table id="Lista_Muestra_supervisor" class="table table-hover table-bordered" width="100%">
                                <thead>
                                <tr id="fila1">
                                    <th id="enf_codigo"><spring:message code="Código Participante"/></th>
                                    <th id="enf_fechaMuestra"><spring:message code="Fecha Muestra"/></th>
                                    <th id="enf_tipoTubo"><spring:message code="Tipo Tubo"/></th>
                                    <th id="enf_volumen"><spring:message code="Volumen"/></th>
                                    <th id="enf_usuarioRegistro"><spring:message code="Usuario Registro"/></th>
                                    <th id="enf_observaciones"><spring:message code="Observaciones"/></th>


                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>

                        <div id="mostrarResultado">

                        </div>
                    </div>

                    </div>
                    </div>
                </div>

            </div>

        <div class="container-fluid">
            <div class="card">
                <div class="card-block">
                    <div class="row">
                <div class="card-header">
                    <h5 class="page-title">
                        <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="Tabla de Encuestadores" /></strong>
                    </h5>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">Total Encuestadores en este Rango: </span>
                        </div>
                    <input type="text"  id="total_encuestadores" name="total_encuestadores" class="form-control" placeholder="Total Médicos" aria-describedby="basic-addon1" >
                    <input type="text"  id="total_rojo_encuestadores" name="total_rojo_encuestadores" class="form-control" placeholder="Total Rojo" aria-describedby="basic-addon1">
                    <input type="text"  id="total_bhc_encuestadores" name="total_bhc_encuestadores" class="form-control" placeholder="Total Bhc" aria-describedby="basic-addon1">
                </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr/>
                    <table id="Lista_Muestra_encuestadores" class="table table-hover table-bordered" width="100%">
                        <thead>
                        <tr>
                            <th id="enc_codigo"><spring:message code="Código Participante"/></th>
                            <th id="enc_fechaMuestra"><spring:message code="Fecha Muestra"/></th>
                            <th id="enc_tipoTubo"><spring:message code="Tomó Rojo"/></th>
                            <th id="enc_tipoTubo1"><spring:message code="Tomó Bhc"/></th>
                            <th id="enc_volumen"><spring:message code="Volumen"/></th>
                            <th id="enc_usuarioRegistro"><spring:message code="Usuario Registro"/></th>
                            <th id="enc_observaciones"><spring:message code="Observaciones"/></th>


                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>


                </div>


                <div class="card-header">
                    <h5 class="page-title">
                        <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="Tabla de Recepción" /></strong>
                    </h5>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">Total Recepción en este Rango: </span>
                        </div>
                    <input type="text"  id="total_recepcion" name="total_recepcion" class="form-control" placeholder="Total Recepción" aria-describedby="basic-addon1">
                    <input type="text"  id="total_rojo_recepcion" name="total_rojo_recepcion" class="form-control" placeholder="Total Rojo" aria-describedby="basic-addon1">

                </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr/>
                    <table id="Lista_Muestra_recepcion" class="table table-hover table-bordered" width="100%">
                        <thead>
                        <tr>

                            <th id="rec_codigo"><spring:message code="Código Participante"/></th>
                            <th id="rec_fechaMuestra"><spring:message code="Fecha Muestra"/></th>
                            <th id="rec_volumen"><spring:message code="Volumen"/></th>
                            <th id="rec_usuarioRegistro"><spring:message code="Usuario Registro"/></th>
                            <th id="rec_observaciones"><spring:message code="Observaciones"/></th>


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

<!-- bootstrap datetimepicker -->
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/moment-with-locales.js" var="moment" />
<script src="${moment}"></script>
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepicker" />
<script src="${datetimepicker}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/mxEnfermos/searchMxMuestreo.js" var="SeroJs" />
<script type="text/javascript" src="${SeroJs}"></script>

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


<spring:url value="/mx/enfermo/getTablaSupervisor" var="getTablaSupervisor"/>
<spring:url value="/mx/enfermo/getTablaEncuestador" var="getTablaEncuestador"/>
<spring:url value="/mx/enfermo/getTablaRecepcion" var="getTablaRecepcion"/>
<spring:url value="/mx/enfermo/sendAllSerologies" var="envioUrl"/>
<spring:url value="/mx/enfermo/delete" var="deleteUrl"/>
<spring:url value="/mx/enfermo/getTablasMxEnfermos" var="searchUrl"/>


<script>
    jQuery(document).ready(function() {

        var misUrl ={
            "getTablaSupervisor"               : "${getTablaSupervisor}",
            "getTablaRecepcion"             : "${getTablaRecepcion}",
            "getTablaEncuestador"              : "${getTablaEncuestador}",
            "successMessage"        : "${successMessage}",
            "dataTablesLang"        : "${dataTablesLang}",
            "searchUrl"       : "${getTablaSupervisor}"
        };
        SearchMxMuestreoFormVal.init(misUrl);

        $('.datepicker').datetimepicker({
            format: 'L',
            locale: "${lenguaje}",
            maxDate: new Date(),
            useCurrent: false
        }).val(moment().format('DD/MM/YYYY'));

        $("#desde").on("dp.change", function (e) {
            $('#hasta').data("DateTimePicker").minDate(e.date);
        });
        $("#hasta").on("dp.change", function (e) {
            $('#desde').data("DateTimePicker").maxDate(e.date);
        });

        var hora = moment().format('HH:mm');
        $("#horaEnvio").val(hora);

        if ($('html').attr('dir') === 'rtl') {
            $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
            $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
        }
        $('[data-toggle="tooltip"]').tooltip();
    });
    function print_bhc(){
        if($("#codigo_lineal").text !=""){
        var id ="100"+ $("#codigo_lineal").val()+"*1*2";
        imprimirEtiquetas(id);
        }
    }
</script>
</body>
</html>
