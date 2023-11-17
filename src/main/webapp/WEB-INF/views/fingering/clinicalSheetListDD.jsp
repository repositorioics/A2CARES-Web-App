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
    <!-- DATETIME PICKER -->
    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>
    <style>
        div.dt-buttons {
            float: right !important;
        }

        .act {

        }
        .desact {

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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/hojaclinicaDD/" htmlEscape="true "/>"><spring:message code="Hola Clínica Doble Digitación" /></a>
            </li>
        </ol>
        <c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
        <c:set var="userDisabledLabel"><spring:message code="login.userDisabled" /></c:set>
        <c:set var="userLockedLabel"><spring:message code="login.accountLocked" /></c:set>
        <c:set var="userUnlockedLabel"><spring:message code="login.accountNotLocked" /></c:set>
        <c:set var="habilitar"><spring:message code="enable" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <c:set var="bloquear"><spring:message code="lock" /></c:set>
        <c:set var="desbloquear"><spring:message code="unlock" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="exportar"><spring:message code="export" /></c:set>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <h3 class="page-title">
                        <spring:message code="Hola Clínica Doble Digitación" /> <small><spring:message code="search" /></small>
                    </h3>
                </div>
                <div class="card-block">
                    <div class="row table-toolbar">
                        <div class="col-md-12">
                            <div class="btn-group">
                                <spring:url value="/hojaclinicaDD/add/" var="nuevaHoja"/>
                                <a class="btn btn-lg btn-success"
                                   data-toggle="tooltip" data-placement="bottom" title="<spring:message code="addEntityToolTip" />"
                                   href="${fn:escapeXml(nuevaHoja)}"><i class="fa fa-plus"></i> <spring:message code="add" /></a>
                            </div>
                            <br>
                            <br>
                        </div>
                    </div>

                    <form action="#" autocomplete="off" id="search-form" name="search-form" class="form-horizontal">
                        <div class="card-block">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-12">
                                    <div class="form-group">
                                        <label class="form-control-label" for="participantCode"><spring:message code="participant" />
                                            <span class="required">*</span>
                                        </label>
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fa fa-user"></i>
                                            </span>
                                            <input id="participantCode" name="participantCode" type="text" value="" class="form-control" placeholder="<spring:message code="search_participant_holder" />"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12">
                                    <div class="form-group">
                                        <label class="form-control-label" for="fechaInicioCons"><spring:message code="fecha_inicio_consulta" />
                                            <span class="required">*</span>
                                        </label>
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                            <input type="text" class="form-control date-picker" id="fechaInicioCons" name="fechaInicioCons" placeholder="<spring:message code="fecha_holder" />"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12">
                                    <div class="form-group">
                                        <label class="form-control-label" for="fechaFinCons"><spring:message code="fecha_fin_consulta" />
                                            <span class="required">*</span>
                                        </label>
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                            <input type="text" class="form-control date-picker" id="fechaFinCons" name="fechaFinCons" placeholder="<spring:message code="fecha_holder" />"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="btn-group float-right">
                                        <button id="searchClinicalSheet" class="btn btn-lg btn-primary " type="submit">
                                            <i class="fa fa-search"></i> <spring:message code="search" />
                                        </button>
                                    </div>
                                    <br>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </form>
                    <br>
                    <hr>
                    <br>
                    <div class="table-responsive">

                        <table class="table table-hover table-bordered" id="lista_hojas">
                            <thead>
                            <tr>
                                <th class="expand" width="10%"><spring:message code="code" /></th>
                                <th class="hidden-xs" width="21%"><spring:message code="participant" /></th>
                                <th class="hidden-xs" width="13%"><spring:message code="fecha_consulta" /></th>
                                <th class="hidden-xs" width="13%"><spring:message code="lugar_consulta" /></th>
                                <th class="hidden-xs" width="13%"><spring:message code="consulta" /></th>
                                <th class="hidden-xs" width="15%"><spring:message code="medico" /></th>
                                <th class="hidden-xs" width="15%"><spring:message code="enfermeria" /></th>
                                <!--<th width="5%"></th>-->
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
                            <div id="titulo"></div>
                        </div>
                        <div class="modal-body">
                            <input type=hidden id="accionUrl"/>
                            <div id="cuerpo"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                            <button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>
        <!-- /.conainer-fluid -->
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
<!-- Datatables  -->
<spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>
<!-- JQUERY BLOCK UI -->
<spring:url value="/resources/js/libs/jquery.blockui.min.js" var="jqueryBlockUi" />
<script src="${jqueryBlockUi}"></script>
<!-- Jquery validate-->
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<!-- bootstrap datetimepicker -->
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/moment-with-locales.js" var="moment" />
<script src="${moment}"></script>
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepicker" />
<script src="${datetimepicker}"></script>

<spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/hojaclinicaDD/get" var="sHojasUrl"/>
<spring:url value="/admin/users/habdes/disable1/" var="disableUrl"/>
<spring:url value="/admin/users/habdes/enable1/" var="enableUrl"/>

<script>
    jQuery(document).ready(function() {
        $('.date-picker').datetimepicker({
            format: 'L',
            locale: "${lenguaje}",
            maxDate: new Date(),
            useCurrent: false
        });

        var table = $('#lista_hojas').DataTable({

            dom: "<'row'<'col-sm-12 col-md-12'B>>" +
                    "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
            buttons: [
                'excel', 'pdf'
            ],"oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            ajax:{
                url: "${sHojasUrl}", // Change this URL to where your json data comes from
                type: "GET", // This is the default value, could also be POST, or anything you want.
                data: function(d) {
                    d.codigo = $("#participantCode").val();
                    d.fechaInicioCons = $("#fechaInicioCons").val();
                    d.fechaFinCons = $("#fechaFinCons").val();
                },
                dataSrc: ""
            },
            columns: [
                { data: 'codigo'},
                { data: 'nombreCompleto' },
                { data: 'fechaConsulta' },
                { data: 'lugarAtencion' },
                { data: 'tipoConsulta' },
                { data: 'medico' },
                { data: 'enfermeria' }
                /*{ data: null,
                    className: "dt-center editor-enabled",
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        var aEnabled = "";
                        if(oData.enabled) {
                            aEnabled = "<a data-toggle='modal' href='#' data-id= '${disableUrl}"+oData.username+"' class='btn btn-outline-danger btn-sm desact'><i class='fas fa-trash'></i></a>";
                        } else {
                            aEnabled = "<a data-toggle='modal' href='#' data-id= '${enableUrl}"+oData.username+"' class='btn btn-outline-success btn-sm act'><i class='fas fa-check'></i></a>";
                        }
                        $(nTd).html(aEnabled);
                    },
                    orderable: false
                }*/
            ]
        });

        var formSearch = $('#search-form');
        formSearch.validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                participantCode : {
                    required: function () {
                        return $('#fechaFinCons').val().length === 0 && $('#fechaInicioCons').val().length === 0;
                    },
                    pattern: /^\d{4}$/
                },
                fechaInicioCons: {required: function () {
                    return $('#fechaFinCons').val().length > 0;
                }},
                fechaFinCons: {required: function () {
                    return $('#fechaInicioCons').val().length > 0;
                }}
            },
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    //error.insertAfter( element ); //cuando no es input-group
                    error.insertAfter(element.parent('.input-group'));
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
                console.log("buscar");
                search();
            }
        });

        function search()
        {
            table.ajax.reload();
        }

        $('#lista_hojas').on('click', 'td.editor-enabled', function (e) {
            e.preventDefault();
            console.log(table.cell( this ).data());
            var objeto= table.cell( this ).data();
            if (objeto.enabled){
                mostrarDeshabilitar("${disableUrl}"+objeto.username);
            } else {
                mostrarHabilitar("${enableUrl}"+objeto.username);
            }
        } );

        /*
        $.blockUI({ message: 'bloquedado',
            baseZ: 1051 // para que se muestre bien en los modales
        });*/

        if ("${usuarioHabilitado}"){
            toastr.success("${userEnabledLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioDeshabilitado}"){
            toastr.error("${userDisabledLabel}", "${nombreUsuario}" );
        }

        function mostrarHabilitar(id) {
            $('#accionUrl').val(id);
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${habilitar}"+' '+id.substr(id.lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        }

        function mostrarDeshabilitar(id) {
            $('#accionUrl').val(id);
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+id.substr(id.lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        }

        $("#fechaInicioCons").on("dp.change", function (e) {
            $('#fechaFinCons').data("DateTimePicker").minDate(e.date);
        });
        $("#fechaFinCons").on("dp.change", function (e) {
            $('#fechaInicioCons').data("DateTimePicker").maxDate(e.date);
        });
    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>