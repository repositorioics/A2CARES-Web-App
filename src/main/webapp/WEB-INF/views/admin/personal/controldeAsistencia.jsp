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

    <spring:url value="../resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
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
<jsp:include page="../../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../../fragments/sideBar.jsp" />
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/movil/getAsistenciaPersonal" htmlEscape="true "/>"><spring:message code="lbl.list" /> <spring:message code="" /></a>
            </li>
        </ol>
        <c:set var="successMessage"><spring:message code="process.success" /></c:set>

        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h5 class="page-title">
                    <i class="fa fa-list-alt"></i>&nbsp;<strong><spring:message code="lbl.movil.ControlAsistencia" /></strong>
                    </h5>
                </div>
                <div class="card-block">


                    <form action="#" autocomplete="off" id="control_asistencia_form"  name="control_asistencia_form-form" class="form-horizontal">
                    <div class="row">
                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8 col-xl-6">
                                <div class="row form-group">
                                    <label class="col-xs-12 col-sm-2 col-md-3 col-lg-3 col-xl-3 col-form-label" for="desde"><spring:message code="lbl.from" />
                                    </label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-9 col-xl-6">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                        <input type="text" class="form-control date-picker" id="desde" name="desde" >
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <label class="col-xs-12 col-sm-2 col-md-3 col-lg-3 col-xl-3 col-form-label" for="hasta"><spring:message code="lbl.until" />
                                    </label>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-9 col-xl-6">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                        <input type="text" class="form-control date-picker" id="hasta" name="hasta" >
                                        </div>
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
                                        <spring:message code="Extraer Registros" />
                                        <spring:message code="De Control de Asistencia" />
                                    </button>
                                </div>
                            </div>
                            <div class="col-xs-1 col-sm-1 col-md-1 col-lg-2 col-xl-2">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    </form>
                    <div class="row">

                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr/>
                            <table id="Lista_asistencia" class="table table-hover table-bordered" width="100%">
                                <thead>
                                <tr id="fila1">
                                    <th id="nombre_usuario"><spring:message code="Usuario"/></th>
                                    <th id="nombre_completo"><spring:message code="Nombre Completo"/></th>
                                    <th id="horaentrada"><spring:message code="Hora Entrada"/></th>
                                    <th id="horasalida"><spring:message code="Hora Salida"/></th>
                                    <th id="fechaasistencia"><spring:message code="Fecha Asistencia"/></th>
                                    <td id="identificador_equipo"><b><spring:message code="Identificador de Equipo"/></b></th>
                                    <td id="latitud"><b><spring:message code="Latitud"/></b></th>
                                    <th id="longitud"><spring:message code="Longitud"/></th>
                                    <th id="Cargo"><spring:message code="Cargo"/></th>
                                    <th id="Area"><spring:message code="Area"/></th>

                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>



                        </div>
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

<spring:url value="/resources/js/views/personal/searchControlAsistencia.js" var="SeroJs" />
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


<spring:url value="/movil/getAsistenciaPersonal" var="getAsistenciaPersonal"/>
<spring:url value="/mx/enfermo/getMxOrdenLaboratorio" var="getMxOrdenLaboratorio"/>
<spring:url value="/mx/enfermo/getMxRecepcionEnfermo" var="getMxRecepcionEnfermo"/>
<spring:url value="/mx/enfermo/sendAllSerologies" var="envioUrl"/>
<spring:url value="/mx/enfermo/delete" var="deleteUrl"/>
<spring:url value="/movil/getAsistenciaPersonal" var="searchUrl"/>


<script>
    jQuery(document).ready(function() {

        var misUrl ={
            "getAsistenciaPersonal"    : "${getAsistenciaPersonal}",
            "getrecepcion"             : "${getMxRecepcionEnfermo}",
            "envioUrl"              : "${envioUrl}",
            "successMessage"        : "${successMessage}",
            "dataTablesLang"        : "${dataTablesLang}",
            "searchUrl"       : "${getAsistenciaPersonal}"
        };
      //  SearchControlAsistenciaFormVal.init(misUrl);
        $('.date-picker').datetimepicker({
            format: 'L',
            locale: "${lenguaje}",
            maxDate: new Date(),
            useCurrent: true
        });
      /*  $('.datepicker').datetimepicker({
            format: 'L',
            locale: "${lenguaje}",
            maxDate: new Date(),
            useCurrent: true
        }).val(moment().format('DD/MM/YYYY'));*/

       $("#desde").on("dp.change", function (e) {
           $('#hasta').data("DateTimePicker").minDate(e.date);
        });
        $("#hasta").on("dp.change", function (e) {
           $('#desde').data("DateTimePicker").maxDate(e.date);
        });

      /*  var hora = moment().format('HH:mm');
        $("#horaEnvio").val(hora);*/

        if ($('html').attr('dir') === 'rtl') {
            $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
            $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
        }
        $('[data-toggle="tooltip"]').tooltip();

        var edithoja = "EditarHC";
        var table = $('#Lista_asistencia').DataTable({
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
            "pageLength": 11,
            "bLengthChange": true,
            "buttons": [
                {
                    extend: 'excel'
                }
            ],
            "ajax":{
                url: "${getAsistenciaPersonal}", // Change this URL to where your json data comes from
                type: "GET",
                data: function(d) {
                    d.desde = $("#desde").val();
                    d.hasta = $("#hasta").val();
                },
                dataSrc: ""

            },
            "columns": [
                { data: 'nombre_usuario', defaultContent: ""},
                { data: 'nombre_completo', defaultContent: ""},
                { data: 'horaentrada', defaultContent: ""},
                { data: 'horasalida', defaultContent: ""},
                { data: 'fechaasistencia', defaultContent: ""},
                { data: 'identificador_equipo', defaultContent: ""},
                { data: 'latitud', defaultContent: ""},
                { data: 'longitud', defaultContent: ""},
                { data: 'cargo', defaultContent: ""},
                { data: 'area', defaultContent: ""}

            ]
        });



    function print_bhc(){
        if($("#codigo_lineal").text !=""){
        var id ="100"+ $("#codigo_lineal").val()+"*1*2";
        imprimirEtiquetas(id);
        }
    }





    var form1 = $('#control_asistencia_form');
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
        rules: {
            desde: {required: function () {
                return $('#desde').val().length > 0;
            }},
            hasta: {required: function () {
                return $('#hasta').val().length > 0;
            }}
        },
        submitHandler: function (form) {
            var $validarForm = form1.valid();
            if (!$validarForm) {
                search();
                $validator.focusInvalid();
                return false;
            } else {
                window.open("${pdfControlAsistenciaUrl}?" + form1.serialize(), '_blank');
            }
        }

    });
        function search()
        {
            table.ajax.reload();
        }
    });
</script>
</body>
</html>

