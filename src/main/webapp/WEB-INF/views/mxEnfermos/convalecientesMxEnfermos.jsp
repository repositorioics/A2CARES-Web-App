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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/mx/enfermo/getConvalecientesMx" htmlEscape="true "/>"><spring:message code="lbl.list" /> <spring:message code="lbl.serologia.enfermo" /></a>
            </li>
        </ol>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>
        <spring:url value="/reportes/downloadConvalecientesMxEnfermoPdf/" var="pdfConvalecientesUrl"/>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                    <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="Muestras de Enfermos - Convalecientes" /></strong>
                    </h5>
                </div>
                <div class="card-block">

                    <form autocomplete="off" id="search-participant-form" class="form-horizontal">

                    <div class="row">
                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                        </div>

                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
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
                        <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="Lista de Convalecientes" /></strong>
                    </h5>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">Total Convalecientes en este Rango: </span>
                        </div>
                    <input type="text"  id="total_convalecientes" name="total_convalecientes" class="form-control" placeholder="Total Convalecientes" aria-describedby="basic-addon1">
                </div>
                </div>
                        <div class="d-flex justify-content-between">
                            <div class="p-2 bd-highlight"></div>
                            <div class="p-2 bd-highlight">
                                <button id="toPdf" type="submit" class="btn btn-success btn-ladda btn-lg" data-style="expand-right"><i class="far fa-file-pdf"></i> <spring:message code="Imprimir Convalecientes" /> <spring:message code="lbl.pdf" /> </button>

                            </div>
                            <div class="p-2 bd-highlight"></div>
                        </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr/>
                    <table id="Lista_Muestra_convalecientes" class="table table-hover table-bordered" width="100%">
                        <thead>
                        <tr>

                            <th class="text-center"><spring:message code="Participante" /></th>
                            <th  class="text-center"><spring:message code="Casa" /></th>
                            <th  class="text-center"><spring:message code="Categoria" /></th>
                            <th  class="text-center"><spring:message code="Dias Convalecientes" /></th>
                            <th  class="text-center"><spring:message code="Nombre Completo" /></th>
                            <th  class="text-center"><spring:message code="Telefono" /></th>
                            <th  class="text-center"><spring:message code="Barrio" /></th>
                            <th  class="text-center"><spring:message code="Nombre Tutor" /></th>
                            <th  class="text-center"><spring:message code="Dirección" /></th>
                            <th class="text-center"><spring:message code="Evento" /></th>
                            <th  class="text-center"><spring:message code="Observaciones" /></th>


                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>


                </div>

                        </form>


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

<spring:url value="/resources/js/views/mxEnfermos/searchMxConv.js" var="SeroJs" />
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


<spring:url value="/mx/enfermo/getConvalecientesMx" var="getConvalecientesMx"/>
<spring:url value="/mx/enfermo/sendAllSerologies" var="envioUrl"/>
<spring:url value="/mx/enfermo/delete" var="deleteUrl"/>
<spring:url value="/mx/enfermo/getTablasMxEnfermos" var="searchUrl"/>


<script>
    jQuery(document).ready(function() {

        var misUrl ={
            "getConvalecientesMx"               : "${getConvalecientesMx}",
            "getrecepcion"             : "${getMxRecepcionEnfermo}",
            "envioUrl"              : "${envioUrl}",
            "successMessage"        : "${successMessage}",
            "dataTablesLang"        : "${dataTablesLang}",
            "searchUrl"       : "${getTablasMxEnfermos}"
        };
        SearchMxEnfermosConvFormVal.init(misUrl);

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

<script>

        var form1 = $('#search-participant-form');
        var $validator = form1.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input

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

                    window.open("${pdfConvalecientesUrl}?"+form1.serialize(), '_blank');
                }

        });

</script>
</body>
</html>

