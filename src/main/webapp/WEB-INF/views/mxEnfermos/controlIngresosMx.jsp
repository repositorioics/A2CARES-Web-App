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
    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>


</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<div class="layout-wrapper layout-2">

<div class="layout-inner">
	<jsp:include page="../fragments/bodyHeader.jsp" />

        <jsp:include page="../fragments/sideBar.jsp" />
        <div class="layout-container">
        <!-- Main content -->
        <div class="main">
        	<!-- Breadcrumb -->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/mx/enfermo/ControlIngresosMx" htmlEscape="true "/>"><spring:message code="CONTROL DE INGRESOS DE MX" /> </a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <form action="#" autocomplete="off" id="search-form">
                        <div class="form-group row" align="RIGHT">
                            <label class="form-control-label col-md-3" for="fechaInicio"><spring:message code="lbl.inicio_mx_dia" /></label>
                            <div class="input-group col-md-4">
                                <span class="input-group-addon"> <i class="fa fa-calendar"></i></span>
                                <input name="fechaInicio" id="fechaInicio" class="form-control datepicker" type="text" required   />
                            </div>
                        </div>
                        <div class="form-group row" align="RIGHT">
                            <label class="form-control-label col-md-3" for="fechaFin"><spring:message code="lbl.fin_mx_dia" /> </label>
                            <div class="input-group col-md-4"> <span class="input-group-addon"> <i class="fa fa-calendar"></i></span>
                                <input name="fechaFin" id="fechaFin" class="form-control datepicker" type="text"    />
                            </div>
                        </div>

                        <div class="form-group" align="center">
                            <button type="submit" class="btn rounded-pill btn-outline-primary"
                                    data-toggle="tooltip" data-placement="bottom"
                                    title="<spring:message code="Extraer Muestras" />"
                                    id="search"><i class="ion ion-md-search"></i> <spring:message
                                    code="Extraer"/></button>
                        </div>
                       </form>
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.muestras_enfermos" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                    </div>
                    <div class="row no-gutters row-bordered">

                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_cartas1"  class="table table-striped table-bordered datatable" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="Código Participante"/></th>
                                            <th><spring:message code="Fecha Muestra"/></th>
                                            <th><spring:message code="Hora Muestra"/></th>
                                            <th><spring:message code="Tipo Tubo"/></th>
                                            <th><spring:message code="Volumen"/></th>
                                            <th><spring:message code="FIS"/></th>
                                            <th><spring:message code="FIF"/></th>
                                            <th><spring:message code="Categoria"/></th>
                                            <th><spring:message code="Consulta"/></th>
                                            <th><spring:message code="Tipo Muestra"/></th>
                                            <th><spring:message code="Usuario Registro"/></th>
                                            <th><spring:message code="Fecha Registro"/></th>
                                            <th><spring:message code="Observaciones"/></th>


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
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.ordenes_laboratorio" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <div class="row">
                                    <table id="lista_enfermeria"  class="table table-striped table-bordered dt-responsive" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="Código Participante"/></th>

                                            <th><spring:message code="Consulta"/></th>
                                            <th><spring:message code="Fase de Muestra"/></th>
                                            <th><spring:message code="Categoria"/></th>
                                            <th><spring:message code="FIF"/></th>
                                            <th><spring:message code="FIS"/></th>
                                            <th><spring:message code="Fecha de Oden"/></th>
                                            <th><spring:message code="Tipo de Orden"/></th>
                                            <th><spring:message code="Usuario Registro"/></th>
                                            <th><spring:message code="Observaciones"/></th>
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
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="lbl.mx_enfermos_recepcion_lab" /></strong>
                        </h3>
                    </div>
                    <div class="row no-gutters row-bordered">



                        <div class="col-md-12 col-lg-12 col-xl-12">
                            <div class="card-body">
                                <table id="lista_cartas3"  class="table table-striped table-bordered dt-responsive" width="100%">
                                        <thead>
                                        <tr>
                                            <th><spring:message code="Código Participante"/></th>
                                            <th><spring:message code="Código Lab"/></th>
                                            <th><spring:message code="Fecha Registro"/></th>
                                            <th><spring:message code="Consulta"/></th>
                                            <th><spring:message code="Fase de Muestra"/></th>
                                            <th><spring:message code="Categoria"/></th>
                                            <th><spring:message code="FIF"/></th>
                                            <th><spring:message code="FIS"/></th>
                                            <th><spring:message code="Fecha Recepción"/></th>
                                            <th><spring:message code="Evento"/></th>
                                            <th><spring:message code="Tipo tubo"/></th>
                                            <th><spring:message code="Volumen"/></th>
                                            <th><spring:message code="Usuario Registro"/></th>
                                            <th><spring:message code="Positivo"/></th>
                                            <th><spring:message code="Observaciones"/></th>
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

    <!-- bootstrap datetimepicker -->
    <spring:url value="/resources/js/libs/bootstrap-datetimepicker/moment-with-locales.js" var="moment" />
    <script src="${moment}"></script>
    <spring:url value="/resources/js/libs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepicker" />
    <script src="${datetimepicker}"></script>

    <!-- Datatables  -->
    <spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
    <script src="${dataTableJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <spring:url value="/mx/enfermo/getTablasMxEnfermos" var="getTablasMxEnfermos"/>

   <spring:url value="/resources/js/views/mxEnfermos/MxEnfermosFilter.js" var="searchProcess"/>
    <script src="${searchProcess}"></script>

<script>
    jQuery(document).ready(function() {

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
        Cargar_muestras_enfermos();
        jQuery(document).ready(function () {
            $("li.capture").addClass("open");
            $("li.capture").addClass("active");
            $("li.specimens").addClass("active");

            var parametros = {
                searchUrl: "${getTablasMxEnfermos}",
                dataTablesLang: "${dataTablesLang}"


            };

            SearchProcess.init(parametros);

        });
    });

    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();

    function Cargar_muestras_enfermos(){
        var bloquearUI = function (mensaje) {
            var loc = window.location;
            var pathName = loc.pathname.substring(0, loc.pathname.indexOf('/', 1) + 1);
            var mess = '<img src=' + pathName + 'resources/img/ajax-loading.gif>' + mensaje;
            $.blockUI({ message: mess,
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .5,
                    color: '#fff'
                },
                baseZ: 1051 // para que se muestre bien en los modales
            });
        };

        var desbloquearUI = function () {
            setTimeout($.unblockUI, 500);
        };
        var parametros = {
            "getTablasMxEnfermos" : "${getTablasMxEnfermos}",
            "updateUrl" : "${updateUrl}",
            "getValueColumn" : "${getValueColumn}"
        };

        $.getJSON(parametros.getTablasMxEnfermos, {
                    ajax: 'true',
                    desde: $('#fechaInicio').val(),
                    hasta: $('#fechaFin').val()

                } ,    function (dataToLoad) {
                    //  alert(data);
                    $("#valor").val(dataToLoad);



                    desbloquearUI();

                }

        ).
                fail(function (jqXHR) {
                    //   desbloquearUI();
                });



    };




</script>
</body>
</html>