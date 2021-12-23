<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 10/05/2020
  Time: 18:31
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
    <spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
    <link href="${uiCss}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <style>
            .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid transparent;
            border-radius: 0;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link {
            border: 0;
            color: #fff;
            border-bottom: 3px solid transparent;
        }
        .mailbox-widget .custom-tab .nav-item .nav-link.active {
            background: 0 0;
            color: #fff;
            border-bottom: 5px solid #fff
        }

        .no-wrap td, .no-wrap th {
            white-space: nowrap;
        }
        .table td, .table th {
            padding: .9375rem .4rem;
            vertical-align: top;
            border-top: 1px solid rgba(120,130,140,.13);
        }
        .font-light {
            font-weight: 300;
        }
        .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
            background-color: #028dba;
        }
        .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
            border-color: rgba(0, 0, 0, 0.1);
            background-color: #028dba;
        }
        .alert-warning {
            background-color: #fcf8e3;
            border-color: #faf2cc;
            color: #8a6d3b;
        }
    </style>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss"/>
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/cartas/CartaParticipantTmp" htmlEscape="true "/>">
                    <c:choose>
                        <c:when test="${agregando}">
                            <spring:message code="add" />
                        </c:when>
                        <c:otherwise>
                            <spring:message code="edit" />
                        </c:otherwise>
                    </c:choose>
                    <spring:message code="Form" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(editParteUrl)}">
                    <spring:message code="Extension" />
                </a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div class="">
            <div class="row">
            <div class="col-md-12 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-white pb-3"> <spring:message code="Extension"/> <spring:message code="letters"/> <spring:message code="lbl.temporary"/></h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block"><spring:message code="Form" /></span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
            <div>
            <form id="frmExtensionTmp" class="needs-validation" novalidate autocomplete="off">
                <spring:url value="/cartas/CartaParticipantTmp" var="listCartaUrl"></spring:url>
                <spring:url value="/cartas/saveExtensionTmp" var="saveExtensionTmpUrl"></spring:url>
                <c:set var="successMessage"><spring:message code="process.success"/></c:set>
                <c:set var="errorProcess"><spring:message code="process.error"/></c:set>
                <spring:url value="/cartas/getNombre1" var="getNombre1Url"/>
                <spring:url value="/cartas/getNombre2" var="getNombre2Url"/>
                <spring:url value="/cartas/getApellido1" var="getApellido1Url"/>
                <spring:url value="/cartas/getApellido2" var="getApellido2Url"/>
                <spring:url value="/cartas/deletExt" var="delUrl"></spring:url>
                <div class="form-row" hidden="hidden">
                    <div class="form-group col-md-3">
                        <label for="idParticipantExtensiontmp">idParticipantExtensiontmp</label>
                        <input type="text" class="form-control" id="idParticipantExtensiontmp" name="idParticipantExtensiontmp"  value="${caso.idParticipantExtensiontmp}">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="idParticipantExtensiontmp">editando</label>
                        <input type="text" class="form-control" id="editando" name="editando"  value="${editando}">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="participantecartatmp">participantecartatmp</label>
                        <input type="text" class="form-control" id="participantecartatmp" name="participantecartatmp"  value="${participantecartatmp.id}">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="participantecartatmp">idversion</label>
                        <input type="text" class="form-control" id="idversion" name="idversion"  value="${participantecartatmp.version.idversion}">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="codigo_participante"><spring:message code="code" /> <spring:message code="participant" /></label>
                        <input type="text" class="form-control" id="codigo_participante" name="codigo_participante" value="${participantecartatmp.idparticipante}" readonly>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="estudio"><spring:message code="lbl.study" /></label>
                        <input type="text" class="form-control" id="estudio" name="estudio" value="${estudio}" readonly>
                    </div>

                    <div class="form-group col-md-3">
                        <label for="version"><spring:message code="lbl.version.letter" /></label>
                        <input type="text" class="form-control" id="version" name="version" value="${version}" readonly>
                    </div>

                    <div class="form-group col-md-4">
                        <label for="partesAccept"><spring:message code="lbl.Parts" /> <spring:message code="lbl.Accepted" /></label>
                        <input type="text" class="form-control" id="partesAccept" name="partesAccept" value="${partesAccept}" readonly>
                    </div>
                </div>

                <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="fechaExtension"><spring:message code="dateAdded" /> <spring:message code="Extension" /></label>
                            <span class="required text-danger"> * </span>
                            <input type="text" class="form-control" required="required" name="fechaExtension" id="fechaExtension" data-date-end-date="+0d"
                                   value="<fmt:formatDate value="${caso.fechaExtension}" pattern="dd/MM/yyyy" />" />
                            <div class="invalid-feedback">
                                Campo requerido.
                            </div>
                        </div>

                    <div class="form-group col-md-6">
                        <label for="idExtension"><spring:message code="Extension:" /></label>
                        <span class="required text-danger"> * </span>
                        <select class="form-control" name="idExtension" id="idExtension" required="required">
                            <option selected value=""><spring:message code="select"/>...</option>
                            <c:forEach items="${exts}" var="e">
                                <c:choose>
                                    <c:when test="${caso.extensiones.id eq e.id}">
                                        <option selected value="${e.id}">${e.extension}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${e.id}"> ${e.extension} </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Seleccione la extensión.
                        </div>
                    </div>

                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="nombre1Tutor"><spring:message code="first.name" /> <spring:message code="lbl.tutor" /></label>
                        <input type="text" class="form-control onlytext" id="nombre1Tutor" name="nombre1Tutor" required="required" value="${caso.nombre1Tutor}">
                        <div class="invalid-feedback">
                            Campo reqierido.
                        </div>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="nombre2Tutor"><spring:message code="second.name" /> <spring:message code="lbl.tutor" /></label>
                        <input type="text" class="form-control onlytext" id="nombre2Tutor" name="nombre2Tutor"  value="${caso.nombre2Tutor}">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="apellido1Tutor"><spring:message code="first.surname" /> <spring:message code="lbl.tutor" /></label>
                        <input type="text" class="form-control onlytext" id="apellido1Tutor" name="apellido1Tutor" required="required"  value="${caso.apellido1Tutor}">
                        <div class="invalid-feedback">
                            Campo Requerido.
                        </div>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="apellido2Tutor"><spring:message code="second.surname" /> <spring:message code="lbl.tutor" /></label>
                        <input type="text" class="form-control onlytext" id="apellido2Tutor" name="apellido2Tutor" value="${caso.apellido2Tutor}">
                    </div>

                </div>

                <div class="form-group">
                    <div class="form-check">
                        <c:choose>
                            <c:when test="${caso.testigoPresente eq true}">
                              <input type="checkbox" id="chktestigo" name="chktestigo" checked="checked"  class="form-check-input chktestigo"  value="${caso.testigoPresente}"/>
                            </c:when>
                            <c:otherwise>
                               <input type="checkbox" class="form-check-input chktestigo"  id="chktestigo" name="chktestigo" />
                            </c:otherwise>
                        </c:choose>
                        <label class="form-check-label" for="chktestigo"> <spring:message code="lbl.witness.present" /> </label>
                    </div>
                </div>
                <div id="showDivTestigo" style="display: none">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="nombre1Testigo"><spring:message code="first.name" /> <spring:message code="lbl.witness" /> </label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext onlytext" tabindex="5" id="nombre1Testigo"
                                       name="nombre1Testigo" value="${caso.nombre1Testigo}"/>
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="nombre2Testigo"><spring:message code="second.name" /> <spring:message code="lbl.witness" /></label>
                                <input type="text" class="form-control focusNext onlytext" tabindex="6" id="nombre2Testigo"
                                       name="nombre2Testigo" value="${caso.nombre2Testigo}">
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="apellido1Testigo"><spring:message code="first.surname" /> <spring:message code="lbl.witness" /></label>
                                <span class="required text-danger"> * </span>
                                <input type="text" class="form-control focusNext onlytext" tabindex="7" id="apellido1Testigo"
                                       name="apellido1Testigo" value="${caso.apellido1Testigo}">
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="apellido2Testigo"><spring:message code="second.surname" /> <spring:message code="lbl.witness" /></label>
                                <input type="text" class="form-control focusNext onlytext" tabindex="8" id="apellido2Testigo"
                                       name="apellido2Testigo" value="${caso.apellido2Testigo}">
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label for="observacion"><spring:message code="lbl.Observation" /></label>
                            <textarea name="observacion" id="observacion" cols="10" rows="5" class="form-control focusNext"
                                      tabindex="9">${caso.observacion}</textarea>
                        </div>
                    </div>

                <div class="d-flex justify-content-between">

                    <div class="p-2 bd-highlight">
                        <button type="submit" class="btn btn-primary btn-lg"> <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>
                    </div>
                    <div class="p-2 bd-highlight"></div>
                    <div class="p-2 bd-highlight">
                        <a class="btn btn-warning btn-lg" href="<spring:url value="/cartas/listExtensionTmp" htmlEscape="true "/>">
                            <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                            <spring:message code="cancel" /></a>
                    </div>

                </div>

            </form>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin"/>
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>


<script type="text/javascript">
    $(document).ready(function(){
      $("#idExtension").select2();
        var parameters = {
            saveExtensionTmpUrl : "${saveExtensionTmpUrl}",
            successmessage      : "${successMessage}",
            errorProcess        : "${errorProcess}",
            listCartaUrl        : "${listCartaUrl}",
            getNombre1Url           : "${getNombre1Url}",
            getNombre2Url           : "${getNombre2Url}",
            getApellido1Url         : "${getApellido1Url}",
            getApellido2Url         : "${getApellido2Url}"
        };
        $("#fechaExtension").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy",
            todayBtn:true
        });

        $("#chktestigo").click(function () {
            if ($(this).is(":checked")) {
                $("#showDivTestigo").fadeIn("slow");
                $("[name='nombre1Testigo']").prop("required", true);
                $("[name='apellido1Testigo']").prop("required", true);
                $("#nombre1Testigo").focus();
            } else {
                $("#showDivTestigo").fadeOut("slow");
                $("#nombre1Testigo").val("").attr("required", "false");
                $("#nombre2Testigo").val("").attr("required", "false");
                $("#apellido1Testigo").val("").attr("required", "false");
                $("#apellido2Testigo").val("").attr("required", "false");
                $("[name='nombre1Testigo']").prop("required", false);
                $("[name='apellido1Testigo']").prop("required", false);
            }
        });

        $('.onlytext').keypress(function (e) {
            var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
            return !((tecla > 47 && tecla < 58) || tecla == 46);
        });
        var form = $("#frmExtensionTmp");
        form.validate({
            rules:{
                nombre1Tutor    :{ required: true },
                apellido1Tutor  :{ required: true },
                fechaExtension  :{ required: true },
                idExtension     :{ required: true}
            },
            errorElement: 'em',
            errorPlacement: function ( error, element ) {
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    error.insertAfter( element );
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
                GuardarExtensionTmp(parameters);
            }
        });
        function GuardarExtensionTmp(dir){
            $.post(dir.saveExtensionTmpUrl, form.serialize(), function(data){
                if(data.msj != null){
                    toastr.warning(data.msj,"ADVERTENCIA!",{timeOut: 6000});
                }else{
                    toastr.success(parameters.successmessage);
                    window.setTimeout(function(){
                        window.location.href = parameters.listCartaUrl;
                    }, 1500);
                }
            }).fail(function(XMLHttpRequest, textStatus, errorThrown){
                toastr.error("500 Error Internal Server.","ERROR!",{timeOut:6000});
            });
        }

        mostrarTestigo();
        function mostrarTestigo(){
            var status = ($("#chktestigo").is(':checked')) ? 'checked' : 'unchecked';
            if($("#chktestigo").prop('checked')){
                $("#showDivTestigo").fadeIn("slow");
                $("#nombre1Testigo").prop('required',true);
                $("#apellido1Testigo").prop('required',true);
            }else{
                $("#showDivTestigo").fadeOut("slow");
                $("#nombre1Testigo").prop('required', false);
                $("#apellido1Testigo").prop('required', false);
            }
        }


        $( "#nombre1Tutor" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parameters.getNombre1Url, {nombre1: $('#nombre1Tutor').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        $( "#nombre2Tutor" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parameters.getNombre2Url, {nombre2: $('#nombre2Tutor').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });


        $( "#apellido1Tutor" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parameters.getApellido1Url, {apellido1: $('#apellido1Tutor').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        $( "#apellido2Tutor" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parameters.getApellido2Url, {apellido2: $('#apellido2Tutor').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });


    })
</script>
</body>
</html>
