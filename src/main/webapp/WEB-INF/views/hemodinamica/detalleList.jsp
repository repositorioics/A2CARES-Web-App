<%--
  Created by IntelliJ IDEA.
  User: Lizandro Serrano
  Date: 30/08/2024
  Time: 09:30 AM
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
    <style>
        div.dataTables_wrapper div.dataTables_filter {
            text-align: right !important;
        }
        div.dataTables_wrapper div.dataTables_length select {
             width: none !important;
            display: inline-block;
        }
        .form-control:disabled, .daterangepicker .input-mini:disabled, .input-group > .ui-select-bootstrap > input.ui-select-search.form-control:disabled {
            cursor: not-allowed;
            background-color: #fff;
            font-size: 0.9rem;
            font-weight: bold;
            color: #000;
            font-family: SANS-SERIF;
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
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(editDetailUrl)}"> <spring:message code="Listado de Parámetros" /></a>
                <i class="fa fa-angle-right"></i>
                <strong>${obj.participante.nombreCompleto}</strong>
            </li>
        </ol>
        <div class="container-fluid">
            <spring:url value="/hemo/list" var="listUrl"/>
            <spring:url value="/hemo/addDetails/{idDatoHemo}" var="addDetailsUrl">
                <spring:param name="idDatoHemo" value="${obj.idDatoHemo}" />
            </spring:url>
            <spring:url value="/hemo/listDetailsHemo/{idDatoHemo}" var="listDetailsHemoUrl">
                <spring:param name="idDatoHemo" value="${obj.idDatoHemo}" />
            </spring:url>
            <div class="animated fadeIn">
                <div class="card">
                    <h5 class="card-header">
                        <i class="fas fa-file-alt"></i>
                        <spring:message code="Information" /> <spring:message code="Hemodinámica" />
                    </h5>
                    <div class="card-body">
                        <div class="row">
                            <div class="container col-md-10">
                                <form class="form-horizontal mt-1">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="codigo"> <spring:message code="code"/>:</label>
                                                <input class="form-control form-control-sm" type="text" id="codigo" value="${obj.participante.codigo}" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <label class="control-label" for="nombreCompleto"><spring:message code="lbl.names.surnames"/>:</label>
                                                <input class="form-control form-control-sm" type="text" id="nombreCompleto" value="${obj.participante.nombreCompleto}" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label class="control-label" for="fechaNac"><spring:message code="Fecha Nacimiento"/>:</label>
                                                <input class="form-control form-control-sm" type="text" id="fechaNac" value="<fmt:formatDate value="${obj.participante.fechaNac}" pattern="dd/MM/yyyy"/>" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label" for="nExpediente"><spring:message code="Expediente"/>:</label>
                                                <input class="form-control form-control-sm" type="text" id="nExpediente" name="nExpediente" value="${obj.nExpediente}" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label" for="edad"><spring:message code="lbl.age"/>:</label>
                                                <input class="form-control form-control-sm" type="text" id="edad" name="edad" value="${obj.edad}" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <a href="${fn:escapeXml(addDetailsUrl)}" class="btn btn-primary float-left">
                                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                                <spring:message code="add"/> <spring:message code="Parámetro"/>
                                            </a>
                                        </div>

                                        <div class="col-md-6">
                                            <a  href="${fn:escapeXml(listUrl)}" class="btn btn-warning float-right">
                                                <i class="fa fa-ban" aria-hidden="true"></i>
                                                <spring:message code="cancel"/>
                                            </a>
                                        </div>
                                    </div>
                                </form>
                                <br/>
                                <hr/>
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered dt-responsive nowrap" style="width:100%" id="tblHistorial">
                                        <thead>
                                        <tr>
                                            <th class="text-center hide"><spring:message code="code"/></th>
                                            <th class="text-center"> <spring:message code="dateAdded"/> <spring:message code="Hemo."/></th>
                                            <th class="text-center"><spring:message code="Clasificación"/></th>
                                            <th class="text-center"><spring:message code="N. Consciencia"/></th>
                                            <th class="text-center"><spring:message code="Extremidades"/></th>
                                            <th class="text-center"><spring:message code="Pulso"/></th>
                                            <th class="text-center"><spring:message code="llenado Capilar"/></th>
                                            <th class="text-center"><spring:message code="edit"/></th>
                                            <th class="text-center"><spring:message code="view"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${dtos}">
                                            <spring:url value="/hemo/listDetailsHemo/{idHemoDetalle}" var="volverUrl">
                                                <spring:param name="idHemoDetalle" value="${item.idHemoDetalle}" />
                                            </spring:url>
                                            <spring:url value="/hemo/editDetail/{idHemoDetalle}" var="editDetailUrl">
                                                <spring:param name="idHemoDetalle" value="${item.idHemoDetalle}" />
                                            </spring:url>
                                            <spring:url value="/hemo/ViewResutl/" var="searchResultUrl"></spring:url>
                                            <tr>
                                                <td> ${item.idHemoDetalle}</td>
                                                <td> ${item.fecha_hora}</td>
                                                <td> ${item.signo}</td>
                                                <c:forEach items="${nivel}" var="n">
                                                    <c:if test="${n.catKey eq item.nivelConciencia}">
                                                        <td class="text-center">${n.spanish}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <c:forEach items="${extremidades}" var="ext">
                                                    <c:if test="${ext.catKey eq item.extremidades}">
                                                        <td class="text-center">${ext.spanish}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <c:forEach items="${pulsoCalidad}" var="pulso">
                                                    <c:if test="${pulso.catKey eq item.pulsoCalidad}">
                                                        <td class="text-center">${pulso.spanish}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <c:forEach items="${llenadoCapilar}" var="llenado">
                                                    <c:if test="${llenado.catKey eq item.llenadoCapilar}">
                                                        <td class="text-center">${llenado.spanish}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <td class="text-center">
                                                    <a href="${fn:escapeXml(editDetailUrl)}" class="btn btn-outline-warning btn-sm">
                                                    <span style="color: orange;">
                                                     <i class="fa fa-edit" aria-hidden="true"></i></span>
                                                    </a>
                                                </td>
                                                <td class="text-center">
                                                    <a class="btn btn-outline-dark btn-sm btnView" data-id="${item.idHemoDetalle}">
                                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer text-muted">
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div id="exampleModal" class="modal fade"  tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Síntomas</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <ul class="list-group">
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold"> Presión Sistólica:</label>
                                <span class="badge badge-primary badge-pill" id="ps" style="font-family: Roboto; font-size: 1em; font-weight: bold"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold"> Presión Diastólica:</label>
                                <span class="badge badge-primary badge-pill" id="pd" style="font-family: Roboto; font-size: 1em; font-weight: bold"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label  style="font-family: Roboto; font-size: 1em; font-weight: bold">Presión Arterial Promedio:</label>
                                <span class="badge badge-primary badge-pill" id="pp" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold"> Presión Arterial Media:</label>
                                <span class="badge badge-primary badge-pill" id="pam" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Rango Frecuencia Cardíaca:</label>
                                <span class="badge badge-primary badge-pill" id="fcardi" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Rango Frecuencia Respiratoria: </label>
                                <span class="badge badge-primary badge-pill" id="fr" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">T°C:</label>
                                <span class="badge badge-primary badge-pill" id="tc" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">SA02%:</label>
                                <span class="badge badge-primary badge-pill" id="sa" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Diuresis/ml/Kg/Hr:</label>
                                <span class="badge badge-primary badge-pill" id="diuresis" style="font-family: Roboto; font-size: 1em"></span>
                            </li>

                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Cantidad Orina (ml/Kg/Hr):</label>
                                <span class="badge badge-primary badge-pill" id="cantidadOrina" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Cargas I.V (ml):</label>
                                <span class="badge badge-primary badge-pill" id="cargas_iv" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold">Suero Oral (ml):</label>
                                <span class="badge badge-primary badge-pill" id="sro" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold"> Densidad Urinaria:</label>
                                <span class="badge badge-primary badge-pill" id="densidadU" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <label style="font-family: Roboto; font-size: 1em; font-weight: bold"> Persona Valida:</label>
                                <span class="badge badge-primary badge-pill" id="Persona" style="font-family: Roboto; font-size: 1em"></span>
                            </li>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">
                            <i class="fa fa-times-circle" aria-hidden="true"></i>
                            Cerrar</button>
                    </div>
                </div>
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
<spring:url value="/resources/js/libs/DataTables/DataTables-1.10.24/js/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/DataTables/DataTables-1.10.24/js/dataTables.bootstrap4.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/views/hemodinamica/detalleList.js" var="detalleListScript" />
<script src="${detalleListScript}" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var parametro = {
            dataTablesLang:"${dataTablesLang}",
            searchResultUrl:"${searchResultUrl}"
        }
        DetalleList.init(parametro);
    });
</script>
</body>
</html>