<%--
  Created by IntelliJ IDEA.
  User: Lizandro Serrano
  Date: 15/08/2024
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp"/>
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss"/>
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <style>
        #parametro-error {
            margin-top: 0.25rem;
            width: 95%;
            text-align: left !important;
            margin-left: 13rem !important;
        }

        .form-control-feedback {
            margin-top: 0.25rem;
            width: 95%;
            text-align: left;
        }
        .form-check-input {
            position: absolute;
            margin-top: 0.4rem !important;
            margin-left: 0px !important;
        }
        #chkpositivo-error{
            color: #ff5454;
        }
    </style>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp"/>
<div class="app-body">
<jsp:include page="../fragments/sideBar.jsp"/>
<!-- Main content -->
<div class="main">
<!-- Breadcrumb -->
<ol class="breadcrumb">
    <li class="breadcrumb-item">
        <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home"/></a>
        <i class="fa fa-angle-right"></i>
        <a href="<spring:url value="/hemo/list" htmlEscape="true "/>">
            <spring:message code="Listado"/></a>
        <i class="fa fa-angle-right"></i>
        <a href="<spring:url value="/hemo/create" htmlEscape="true "/>">
            ${tituloForm} <spring:message code="Formulario"/></a>
    </li>
</ol>
<div class="container-fluid">
    <c:set var="successMessage"><spring:message code="process.success"/></c:set>
    <spring:url value="/hemo/addDetails" var="addDetailsUrl"/>
    <spring:url value="/hemo/create" var="createUrl"/>
    <spring:url value="/hemo/searchParticipant" var="searchPartUrl"/>
    <spring:url value="/hemo/save" var="saveHemoUrl"/>
    <div class="animated fadeIn">
        <div class="card">
            <div class="card-header">
                <h5> <i class="fas fa-file-alt"></i> ${tituloForm} <spring:message code="Hemodinámica"/></h5>
            </div>
            <div class="card-body">
            <c:if test="${editando eq false}">
                <div class="container mt-3">
                    <form action="#" autocomplete="off" id="select-participante-form" name="select-participante-form" class="form-horizontal">
                        <div class="form-group row">
                            <label class="form-control-label col-md-2 text-right"
                                   for="parametro"><spring:message code="code"/>
                                <span class="required">*</span>
                            </label>
                            <div class="input-group col-md-10">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input id="parametro" name="parametro" type="text" placeholder="Código participante" class="form-control"/>
                                <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                    <spring:message code="search"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </c:if>

                <hr/>
                <form id="save-hemo-form" name="save-hemo-form" class="form-horizontal" autocomplete="off">
                    <div class="container col-md-12">
                        <div class="row">
                            <div class="col-md-12" hidden="hidden">
                                <div class="form-group">
                                    <label for="idDatoHemo"><spring:message code="idDatoHemo"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="idDatoHemo" value="${obj.idDatoHemo}" name="idDatoHemo" readonly>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="barrio"><spring:message code="Centro Salud"/></label>
                                    <span class="required text-danger"> * </span>
                                    <select name="unidadSalud" id="unidadSalud" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select"/>...</option>
                                        <c:forEach items="${centros}" var="c">
                                            <c:choose>
                                                <c:when test="${obj.unidadSalud eq c.catKey}">
                                                    <option selected value="${c.catKey}"><spring:message code="${c.spanish}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${c.catKey}"><spring:message code="${c.spanish}"/></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="codigoParticipante"><spring:message code="Código"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="codigoParticipante" value="${obj.participante.codigo}" name="codigoParticipante" readonly>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nombre"><spring:message code="Nombre Particpante"/></label>
                                    <input type="text" class="form-control" name="nombre" id="nombre" value="${obj.participante.nombreCompleto}" disabled>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="fechaNac"><spring:message code="Fecha Nacimiento"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="fechaNac" name="fechaNac" value="<fmt:formatDate value="${obj.participante.fechaNac}" pattern="dd/MM/yyyy"/>"
                                           disabled>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="expediente"><spring:message code="Expediente"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="expediente" name="expediente" value="${obj.nExpediente}" readonly>
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="barrio"><spring:message code="Barrio"/></label>
                                    <span class="required text-danger"> * </span>
                                    <select name="barrio" id="barrio" class="form-control" required="required">
                                        <option selected value=""><spring:message code="select"/>...</option>
                                        <c:forEach items="${barrios}" var="barrio">
                                            <c:choose>
                                            <c:when test="${obj.sector eq barrio.codigo}">
                                                <option selected value="${barrio.codigo}"><spring:message code="${barrio.nombre}"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${barrio.codigo}"><spring:message code="${barrio.nombre}"/></option>
                                            </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-9">
                                <div class="form-group">
                                    <label for="direccion"><spring:message code="Dirección"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="direccion" name="direccion" value="${obj.direccion}"  placeholder="Dirección">
                                </div>
                            </div>

                            <div id="divFueraSector" class="form-group col-sm-12" style="display: none">
                                <label for="fueraSector"><spring:message code="Fuera de Sector" /></label>
                                <input type="text" class="form-control" id="fueraSector" name="fueraSector" value="${obj.barrioF}" style="text-transform:uppercase" />
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="telefono"><spring:message code="Teléfono"/></label>
                                    <input type="text" class="form-control" id="telefono" name="telefono" value="${obj.telefono}"
                                           placeholder="Teléfono">
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="peso"><spring:message code="Peso(kg)"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="peso" name="peso" value="${obj.peso}" min="0"
                                           placeholder="Peso (kg)">
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="talla"><spring:message code="Talla(cm)"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="talla" name="talla" value="${obj.talla}" min="0"
                                           placeholder="Talla (cm)">
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="numPagina"><spring:message code="N° página"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="numPagina" name="numPagina" value="${obj.numeroPagina}" placeholder="N° página" min="1">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="numParametro"><spring:message code="Parámetros"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" name="numParametro" value="${obj.numParametros}" id="numParametro" placeholder="parámetros" min="1">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="fconsulta"><spring:message code="Fecha Consulta"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control date-picker from_date" id="fconsulta"
                                           name="fconsulta" placeholder="Fecha consulta" type="text" value="<fmt:formatDate value="${obj.fecha}" pattern="dd/MM/yyyy"/>" data-date-end-date="+0d">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="fie"><spring:message code="F.I.E"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control date-picker to_date" id="fie"
                                           name="fie" placeholder="Fecha inicio enfermedad" type="text" value="<fmt:formatDate value="${obj.fie}" pattern="dd/MM/yyyy"/>"
                                           data-date-end-date="+0d">
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="numEvento"><spring:message code="Evento"/></label>
                                    <span class="required text-danger"> * </span>
                                    <input type="text" class="form-control" id="numEvento" name="numEvento" value="${obj.numeroEvento}" placeholder="Número de evento" required="required">
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="form-check form-check-inline">
                                    <c:choose>
                                        <c:when test="${obj.positivo eq '0'.charAt(0)}">
                                            <input type="radio" id="chkpositivo0" value="${obj.positivo}" name="chkpositivo" checked="checked" class="form-check-input"/>
                                        </c:when>
                                    <c:otherwise>
                                        <input class="form-check-input" type="radio" name="chkpositivo" id="chkpositivo0" value="0">
                                    </c:otherwise>
                                    </c:choose>
                                    <label class="form-check-label" for="chkpositivo0"> <spring:message code="Negativo"/></label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <c:choose>
                                        <c:when test="${obj.positivo eq '1'.charAt(0)}">
                                            <input type="radio" id="chkpositivo1" value="${obj.positivo}" name="chkpositivo" checked="checked" class="form-check-input"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input  type="radio" name="chkpositivo" id="chkpositivo1" value="1">
                                        </c:otherwise>
                                    </c:choose>

                                    <label class="form-check-label" for="chkpositivo1"> <spring:message code="Positivo"/></label>
                                </div>
                                <div id="positivoerror" class="text-danger"></div>
                            </div>
                        </div>
                        <hr/>
                        <div class="row offset-1">
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-primary btn-lg btn-block">
                                    <i class="fa fa-save" aria-hidden="true"></i>
                                    <spring:message code="save"/></button>
                            </div>
                            <div class="col-md-2"></div>
                            <div class="col-md-1"></div>
                            <div class="col-md-2"></div>
                            <div class="col-md-3">
                                <a  class="btn btn-warning btn-lg btn-block" href="<spring:url value="/hemo/list" htmlEscape="true "/>">
                                    <i class="fa fa-ban" aria-hidden="true"></i>
                                    <spring:message code="cancel"/></a>
                            </div>
                        </div>
                    </div>
                </form>
                <br/>
            </div>
            <div class="card-footer text-muted"></div>
        </div>
    </div>
</div>
<!-- /.conainer-fluid -->
</div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp"/>
<jsp:include page="../fragments/corePlugins.jsp"/>
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App"/>
<script src="${App}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs"/>
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs"/>
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}"/>
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw"/>
<script type="text/javascript" src="${sw}"></script>
<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs"/>
<script type="text/javascript" src="${selectJs}"></script>
<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin"/>
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}"/></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/libs/moment.js" var="moment"/>
<script type="text/javascript" src="${moment}"></script>
<spring:url value="/resources/js/views/hemodinamica/datosForm.js" var="datosFormJS"/>
<script type="text/javascript" src="${datosFormJS}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var parametros = {
            successmessage: "${successMessage}",
            searchPartUrl: "${searchPartUrl}",
            saveHemoUrl: "${saveHemoUrl}",
            addDetailsUrl:"${addDetailsUrl}",
            editando:"${editando}",
            createUrl:"${createUrl}"
        }
        guardarHemodinamica.init(parametros);
        $("#parametro").focus();
    })
</script>
</body>
</html>
