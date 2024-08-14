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
    <jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">

    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css'>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>

    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>

    <style>
        div.dataTables_wrapper div.dataTables_filter {
            text-align: right !important;
        }
        .select-dropdown {
            /* ... */

            transform: scaleY(0);
            opacity: 0;
            visibility: hidden;
        }

        /* .... */

        /* interactivity */
        .custom-select.active .arrow {
            transform: rotate(180deg);
        }

        .custom-select.active .select-dropdown {
            opacity: 1;
            visibility: visible;
            transform: scaleY(1);
        }
        .anchura{
            display:block;
            height:50px;
            width:500px;

        }
    </style>
    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>


</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../../fragments/sideBar.jsp" />
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/movil/getGuardarJustificacionesICS" htmlEscape="true "/>"></a>
            </li>
        </ol>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <spring:url value="/reportes/downloadConvalecientesMxEnfermoPdf/" var="pdfConvalecientesUrl"/>
        <spring:url value="/movil/getGuardarJustificacionesICS" var="saveFormUrl"/>
        <spring:url value="/movil/getJustAddICS/" var="createUrl"/>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                    <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="Agregar Justificaciones" /></strong>
                    </h5>
                </div>
                <div class="card-block">

                    <form action="#" autocomplete="off" id="save-Serologia-form" name="search-form" class="form-horizontal">

                    <div class="row">
                        <div class="col-sm-1">
                        </div>
                        &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="form-control-label" for="empleados"><spring:message code="Seleccione Colaborador" />
                                    <span class="text-danger">*</span>
                                </label>

                                <select name="userId" id="userId" class="selectpicker anchura"  data-show-subtext="true" data-live-search="true" size="10">
                                    <option selected="true" value=""><spring:message code="select" />...</option>
                                    <c:forEach items="${empleados}" var="s">
                                        <c:choose>
                                            <c:when test="${s.catKey eq '0'}">
                                                <option selected value="${s.catKey}">${s.catKey} - <spring:message code="${s.spanish}" /></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${s.catKey}">${s.catKey} - <spring:message code="${s.spanish}" /></option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="form-control-label" for="sitio"><spring:message code="Descripción Justificación" />
                                    <span class="text-danger">*</span>
                                </label>

                                    <select name="tipoJust" id="tipoJust" class="form-control" type="text"  required="required" style="height: 35px;width: 280px">
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${sitios}" var="s">
                                            <c:choose>
                                                <c:when test="${s.catKey eq '0'}">
                                                    <option selected value="${s.catKey}">${s.catKey} - <spring:message code="${s.spanish}" /></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${s.catKey}">${s.catKey} - <spring:message code="${s.spanish}" /></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="form-control-label" for="fechaInicioCons"><spring:message code="fecha_inicio" />
                                    <span class="required">*</span>
                                </label>
                                <div class='input-group date' id='datetimepickerf'>
                                    <input type='text' class="form-control" id="fechaInicioCons" name="fechaInicioCons"/>
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label class="form-control-label" for="fechaFinCons"><spring:message code="fecha_fin" />
                                    <span class="required">*</span>
                                </label>
                                <div class='input-group date' id='datetimepickerfi'>
                                    <input type='text' class="form-control" id="fechaFinCons" name="fechaFinCons"/>
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="row">
                        </div>
                        <div class="row">
                        </div>
                        <div class="col-sm-1">
                        </div>
                        <div class="col-sm-1">
                        </div>
                        <div class="col-sm-2">
                        </div>
                        <div class="col-sm-2">
                            <label class="form-control-label" for="desjust1"><spring:message code="      " />

                            </label>
                            <div class="col-sm-2">
                                <div class="btn-group float-left">
                                    <button id="add" class="btn btn-lg btn-success " type="submit">
                                        <i class="fa fa-address-book"></i> <spring:message code="Agregar Justificación" />
                                    </button>
                                </div>
                                <br>
                                <br>
                            </div>
                        </div>

                        </form>



        </div>



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


<spring:url value="/resources/js/libs/sweetalert.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>


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


<spring:url value="/movil/getGuardarJustificacionesICS" var="getGuardarJustificacionesICS"/>
        <spring:url value="/resources/js/views/personal/justificacionesICSSave.js" var="SeroJs" />
        <script type="text/javascript" src="${SeroJs}"></script>


<script>
jQuery(document).ready(function() {
    var parameters = {
        searchUrl : "${searchUrl}",
        saveFormUrl: "${saveFormUrl}",
        createUrl : "${createUrl}",
        successmessage: "${successMessage}",
        error: "${errorProcess}",
        locale : "${lenguaje}"
    };

    JustificacionesProcess.init(parameters);

    $('.date-picker').datetimepicker({
        format: 'L H:MM a',
        locale: "${lenguaje}",
        maxDate: new Date(),
        useCurrent: false
    });
    $(function() {
        $('#datetimepicker1').datetimepicker();
    });
    $(function() {
        $('#datetimepickerf').datetimepicker();
    });
    $(function() {
        $('#datetimepickerfi').datetimepicker();
    });



    $("#fechaInicioCons").on("dp.change", function (e) {
        $('#fechaFinCons').data("DateTimePicker").minDate(e.date);
    });
    $("#fechaFinCons").on("dp.change", function (e) {
        $('#fechaInicioCons').data("DateTimePicker").minDate(e.date);
    });



    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();

} );
</script>
</body>
</html>

