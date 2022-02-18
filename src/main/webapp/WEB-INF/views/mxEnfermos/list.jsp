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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/mx/enfermo/list" htmlEscape="true "/>"><spring:message code="lbl.list" /> <spring:message code="lbl.serologia.enfermo" /></a>
            </li>
        </ol>
        <c:set var="recordDisabledLabel"><spring:message code="recordDisabled" /></c:set>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <c:set var="cerrarCaso"><spring:message code="close.case" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>

        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                    <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="lbl.serologia.enfermo" /></strong>
                    </h5>
                </div>
                <div class="card-block">
                    <div class="row">
                        <div class="col-12">
                            <a href="<spring:url value="/mx/enfermo/create" htmlEscape="true"/>" class="btn btn-success btn-lg">
                                <i class="fa fa-plus" aria-hidden="true"></i> <spring:message code="lbl.new" /> <spring:message code="lbl.serologia.enfermo" />  </a>
                        </div>
                    </div>
                    <hr/>
                    <form name="envio-allserologia-form" id="envio-allserologia-form">

                    <div class="row">
                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8 col-xl-8">
                                <div class="row form-group">
                                    <label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label" for="desde"><spring:message code="lbl.from" />
                                        <span class="text-danger">*</span></label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 col-xl-10">
                                        <input type="text" class="form-control from_date datepicker" id="desde" name="desde" data-date-end-date="+0d">
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label" for="hasta"><spring:message code="lbl.until" />
                                        <span class="text-danger">*</span>
                                    </label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 col-xl-10">
                                        <input type="text" class="form-control to_date datepicker" id="hasta" name="hasta" data-date-end-date="+0d">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label" for="horaEnvio">
                                        <spring:message code="lbl.Hour" />
                                        <span class="text-danger">*</span>
                                    </label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 col-xl-10">
                                        <input type="timer" class="form-control" id="horaEnvio" name="horaEnvio">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="fechaEnvio" class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label"><spring:message code="dateAdded" />
                                        <span class="text-danger">*</span></label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 col-xl-10">
                                        <input name="fechaEnvio" id="fechaEnvio" class="form-control datepicker" type="text" data-date-end-date="+0d" required="required" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label" for="numenvio"><spring:message code="lbl.send" />
                                        <span class="text-danger">*</span>
                                    </label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 col-xl-10">
                                        <select id="numenvio" name="numenvio" class="form-control" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${numero_envio}" var="n">
                                                <option value="${n.catKey}">${n.spanish}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label" for="sitio"><spring:message code="Sitio" />
                                        <span class="text-danger">*</span>
                                    </label>

                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 col-xl-10">
                                        <select name="sitio" id="sitio" class="form-control" type="text"  required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${sitios}" var="s">
                                                <c:choose>
                                                    <c:when test="${s.catKey eq '3'}">
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
                                <div class="form-group row">
                                    <label class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 col-form-label" for="temperatura"><spring:message code="Temperatura" />
                                        <span class="text-danger">*</span>
                                    </label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 col-xl-10">
                                        <input name="temperatura" id="temperatura" class="form-control" type="text" maxlength="4"  required="required" />
                                    </div>
                                </div>
                        </div>
                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                        </div>
                    </div>
                        <div class="row">
                            <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8 col-xl-8">
                                <div class="form-group center">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block"> <i class="fas fa-file-import" aria-hidden="true"></i>
                                        <spring:message code="lbl.dispatch" />
                                        <spring:message code="sample" />
                                    </button>
                                </div>
                            </div>
                            <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                            </div>
                        </div>
                    </form>
                    <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr/>
                            <table id="Lista_Muestra" class="table table-hover table-bordered" width="100%">
                                <thead>
                                <tr>
                                    <th class="text-center"><spring:message code="idRecepcion" /></th>
                                    <th width="10%" class="text-center"><spring:message code="code" /></th>
                                    <th width="10%" class="text-center"><spring:message code="lbl.fecha.recepcion" /></th>
                                    <th width="10%" class="text-center"><spring:message code="lbl.volumen" /></th>
                                    <th width="10%" class="text-center"><spring:message code="lbl.Observation" /></th>
                                    <th width="10%" class="text-center"><spring:message code="lbl.fis" /></th>
                                    <th width="10%" class="text-center"><spring:message code="lbl.fif" /></th>
                                    <th width="10%" class="text-center"><spring:message code="categoria" /></th>
                                    <th width="10%" class="text-center"><spring:message code="consulta" /></th>
                                    <th width="10%" class="text-center"><spring:message code="lbl.sample.type.phase" /></th>
                                    <th width="5%" class="text-center"><spring:message code="edit" /></th>
                                    <th width="5%" class="text-center"><spring:message code="delete" /></th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                    </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade bd-example-modal-lg" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-keyboard="false" data-aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><i class="fas fa-trash" aria-hidden="true"></i> <spring:message code="delete" /></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="#" autocomplete="off" id="close-form" name="close-form" class="form-horizontal">
                            <input type="hidden" class="form-control" id="idAccion" name="idAccion"/>
                            <div id="dvSalida" class="form-group row">
                                <div class="form-group col-md-12">
                                    <label for="message_razon" class="col-form-label"> <spring:message code="rason.invalid" />
                                     <span class="required">* </span>
                                    </label>
                                    <textarea class="form-control" id="message_razon" name="message_razon"></textarea>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between">
                                <div class="p-2 bd-highlight"><button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    <i class="fas fa-times" aria-hidden="true"></i>
                                    <spring:message code="cancel" /></button></div>
                                <div class="p-2 bd-highlight"></div>
                                <div class="p-2 bd-highlight">
                                    <button type="submit" class="btn btn-primary">  <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>
                                </div>
                            </div>
                        </form>
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

<!-- bootstrap datetimepicker -->
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/moment-with-locales.js" var="moment" />
<script src="${moment}"></script>
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepicker" />
<script src="${datetimepicker}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/sweetalert.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/mxEnfermos/list.js" var="SeroJs" />
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

<spring:url value="/mx/enfermo/update" var="editUrl"/>
<spring:url value="/mx/enfermo/sendAllSerologies" var="envioUrl"/>
<spring:url value="/mx/enfermo/delete" var="deleteUrl"/>
<spring:url value="/mx/enfermo/list-muestras-no-enviadas" var="MxNoEnviadasUrl"/>


<script>
    jQuery(document).ready(function() {

        var misUrl ={
            "editUrl"               : "${editUrl}",
            "deleteUrl"             : "${deleteUrl}",
            "envioUrl"              : "${envioUrl}",
            "successMessage"        : "${successMessage}",
            "dataTablesLang"        : "${dataTablesLang}",
            "MxNoEnviadasUrl"       : "${MxNoEnviadasUrl}"
        };
        ListaMxEnfermosForm.init(misUrl);

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
</script>
</body>
</html>

