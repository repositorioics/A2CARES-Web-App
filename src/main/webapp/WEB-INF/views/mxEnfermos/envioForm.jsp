<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 18/10/2020
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->

    <style>
        .form-control-feedback {
            margin-top: 0.25rem;
            width: 95%;
            text-align: center !important;
        }
    </style>

    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

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
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /> <i class="fa fa-angle-right"></i></a>
                <a href="<spring:url value="/mx/enfermo/list" htmlEscape="true "/>"><spring:message code="lbl.list" /> <spring:message code="lbl.serologia.enfermo" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/mx/enfermo/generarReporte" htmlEscape="true "/>"><spring:message code="lbl.shipment.report" /></a>
            </li>
        </ol>
        <spring:url value="/reportes/downloadEnvioMxEnfermoPdf/" var="pdfEnvioSeroUrl"/>
        <spring:url value="/reportes/downloadEnvioMxEnfermoExcel/" var="excelEnvioSeroUrl"/>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
        <div class="container-fluid col-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                        <i class="far fa-file"></i>&nbsp;
                        <strong><spring:message code="generate" /> <spring:message code="lbl.report.shipment.specimens.patients" /></strong>
                    </h5>
                </div>
                <form autocomplete="off" id="search-participant-form" class="form-horizontal">
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-10">
                                <div class="form-group row">
                                    <label class="col-sm-3 form-control-label" for="Envio"><spring:message code="lbl.send"/><span class="required">*</span></label>
                                    <div class="col-sm-6">
                                        <select id="nEnvios" name="nEnvios" class="form-control" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${numeroEnvio}" var="n">
                                                <option value="${n.catKey}">${n.spanish}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="fechaInicio"><spring:message code="lbl.shipping.start.date" /></label>
                                    <div class="input-group col-md-6">
                                    <span class="input-group-addon"> <i class="fa fa-calendar"></i></span>
                                        <input name="fechaInicio" id="fechaInicio" class="form-control datepicker" type="text" required />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="form-control-label col-md-3" for="fechaFin"><spring:message code="lbl.shipping.end.date" /> </label>
                                    <div class="input-group col-md-6"> <span class="input-group-addon"> <i class="fa fa-calendar"></i></span>
                                        <input name="fechaFin" id="fechaFin" class="form-control datepicker" type="text" required />
                                    </div>
                                </div>

                                <div class="d-flex justify-content-between">
                                    <div class="p-2 bd-highlight"></div>
                                    <div class="p-2 bd-highlight">
                                        <button id="toPdf" type="submit" class="btn btn-danger btn-ladda btn-lg" data-style="expand-right"><i class="far fa-file-pdf"></i> <spring:message code="generate" /> <spring:message code="lbl.pdf" /> </button>
                                        <button id="toExcel" type="submit" class="btn btn-success btn-ladda btn-lg" data-style="expand-right"><i class="far fa-file-excel"></i> <spring:message code="generate" /> <spring:message code="lbl.excel" /> </button>
                                    </div>
                                    <div class="p-2 bd-highlight"></div>
                                </div>
                        </div>
                    </div>
                </div>
                </form>
            </div>

            <!-- /.conainer-fluid -->
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
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>

<!-- bootstrap datetimepicker -->
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/moment-with-locales.js" var="moment" />
<script src="${moment}"></script>
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepicker" />
<script src="${datetimepicker}"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>



<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/casos/process-case.js" var="processVersionJs" />
<script src="${processVersionJs}"></script>
<script>
    $(function () {
        $("li.reports").addClass("open");
        $("li.filedata").addClass("open");
    });
</script>
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

        var form1 = $('#search-participant-form');
        var $validator = form1.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                nEnvios: {
                    required: true
                },
                fechaInicio:{required:true},
                fechaFin:{required:true}
            },
            errorPlacement: function ( error, element ) {
                console.log(element.prop( 'type' ));
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                }else if ( element.prop( 'type' ) === 'text' ){
                    error.insertAfter(element.parent('.input-group'));
                } else {
                    error.insertAfter( element ); //cuando no es input-group
                }
            },
            highlight: function ( element, errorClass, validClass ) {
                $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
            },
            unhighlight: function (element, errorClass, validClass) {
                $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
            },
            submitHandler: function (form) {
                var $validarForm = form1.valid();
                if (!$validarForm) {
                    $validator.focusInvalid();
                    return false;
                } else {
                    window.open("${pdfEnvioSeroUrl}?"+form1.serialize(), '_blank');
                }
            }
        });


        $("#toExcel").on("click", function(){
            var $validarForm = form1.valid();
            if (!$validarForm) {
                $validator.focusInvalid();
                return false;
            } else {
                window.open("${excelEnvioSeroUrl}?" + form1.serialize())
            }
        });

    });
</script>
</body>
</html>
