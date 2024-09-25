<%--
  Created by IntelliJ IDEA.
  User: Lizandro Serrano
  Date: 15/08/2024
  Time: 12:17 PM
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
            width: 4rem !important;
            display: inline-block;
        }
        .form-control-feedback {
            margin-top: 0.25rem;
            width: 100%;
            text-align: left !important;
            margin-left: 15px !important;
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
                <a href="<spring:url value="/hemo/List" htmlEscape="true "/>">
                    <spring:message code="lbl.list" /> <spring:message code="Hemodinámica" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="card">
                    <div class="card-header">
                       <h5><i class="fas fa-search" aria-hidden="true"></i> <spring:message code="search_participant" /> </h5>
                    </div>
                    <div class="card-body">
                      <div class="container">
                          <div class="row">
                              <div class="col-sm-12">
                                  <div class="container mt-2">
                                      <div class="mb-3">
                                          <a class="btn btn-primary" data-toggle="tooltip" data-placement="bottom" title="Nueva Hoja" href="<spring:url value="/hemo/create" htmlEscape="true "/>">
                                              <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                              <spring:message code="add" /> <spring:message code="Hemodinámica" />
                                          </a>
                                      </div>
                                      <spring:url value="/hemo/listaHoja" var="listaHojaUrl"/>
                                      <form action="#" autocomplete="off" name="select-participante-form mt-2" id="select-participante-form" class="form-horizontal" novalidate="novalidate">
                                          <div class="form-group row">
                                              <div class="input-group col-md-12">
                                                  <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                  <input id="parametro" name="parametro" type="text" placeholder="<spring:message code="Código participante" />" class="form-control">
                                                  <button id="buscar" type="submit" class="btn btn-success btn-ladda ladda-button" data-style="expand-right"><span class="ladda-label">
                                            <i class="fa fa-search" aria-hidden="true"></i>
                                             <spring:message code="search" />
                                        </span><span class="ladda-spinner"></span>
                                                  </button>
                                              </div>
                                          </div>
                                      </form>
                                  </div>
                              </div>
                              <div class="col-12">
                                  <div class="table-responsive">
                                      <table id="tablePartHemo" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                                          <thead>
                                          <tr>
                                              <th class="text-center"><spring:message code="id" /></th>
                                              <th class="text-center"><spring:message code="Código" /></th>
                                              <th data-hide="phone,tablet" class="text-center"><spring:message code="lbl.names.surnames"/></th>
                                              <th data-hide="phone,tablet" class="text-center"><spring:message code="lbl.age" /></th>
                                              <th data-hide="phone,tablet" class="text-center"><spring:message code="Registro" /></th>
                                              <th data-hide="phone,tablet" class="text-center"><spring:message code="Fecha Consulta" /></th>
                                              <th data-hide="phone,tablet" class="text-center"><spring:message code="edit" /></th>
                                              <th data-hide="phone,tablet" class="text-center"><spring:message code="Details" /></th>
                                              <th data-hide="phone,tablet" class="text-center"><spring:message code="reports" /></th>
                                          </tr>
                                          </thead>
                                          <tbody></tbody>
                                      </table>
                                  </div>
                              </div>
                          </div>
                      </div>
                    </div>
                    <div class="card-footer text-muted"></div>
                </div>
                <spring:url value="/hemo/edithemo/{idDatoHemo}" var="edithemoUrl">
                    <spring:param name="idDatoHemo" value="${lista.idDatoHemo}" />
                </spring:url>
                <spring:url value="/hemo/listDetailsHemo/{idDatoHemo}" var="listDetailsHemoUrl">
                    <spring:param name="idDatoHemo" value="${lista.idDatoHemo}" />
                </spring:url>
                <spring:url value="/reportes/ReporteHemodinamica" var="pdfUrl"/>
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
<spring:url value="/resources/js/libs/DataTables/DataTables-1.10.24/js/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/DataTables/DataTables-1.10.24/js/dataTables.bootstrap4.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>
<spring:url value="/resources/js/views/hemodinamica/searchParticipante.js" var="BuscaPartiScript" />
<script src="${BuscaPartiScript}" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function(){
        var parametros = {
            pdfUrl:"${pdfUrl}",
            edithemoUrl: "${edithemoUrl}",
            listaHojaUrl: "${listaHojaUrl}",
            dataTablesLang: "${dataTablesLang}",
            listDetailsHemoUrl: "${listDetailsHemoUrl}"
        }
        searchParticipante.init(parametros);
        $("#parametro").val('').focus();
    });
</script>
</body>
</html>
