<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 10/05/2020
  Time: 17:08
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
    .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
        background-color: #028dba;
    }
    .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
        border-color: rgba(0, 0, 0, 0.1);
        background-color: #028dba;
    }

    /* fin */


/**/
    .card {
        position: relative;
        display: flex;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        /*background-color: #fff;*/
        background-clip: border-box;
        border: 0 solid transparent;
        border-radius: 0;
    }
    .mailbox-widget .custom-tab .nav-item .nav-link {
        border: 0;
        color: #fff;
        border-bottom: 3px solid transparent;
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
    .table tr {
        cursor: pointer;
    }
    .table{
        background-color: #fff !important;
    }
    .hedding h1{
        color:#fff;
        font-size:25px;
    }
    .main-section{
        margin-top: 120px;
    }
    .hiddenRow {
        padding: 0 4px !important;
        background-color: #eeeeee;
        font-size: 13px;
    }
    .accordian-body span{
        color:#a2a2a2 !important;
    }
    /**/

    .cell-1 {
        border-collapse: separate;
        border-spacing: 0 4em;
        background: #ffffff;
        border-bottom: 5px solid transparent;
        background-clip: padding-box;
        cursor: pointer
    }


    .table-elipse {
        cursor: pointer
    }

    #demo {
        -webkit-transition: all 0.3s ease-in-out;
        -moz-transition: all 0.3s ease-in-out;
        -o-transition: all 0.3s 0.1s ease-in-out;
        transition: all 0.3s ease-in-out
    }

    .row-child {
        background-color: #dddcdc;
        color: #35393d
    }

    .nav-tabs .nav-link, .nav-tabs .nav-link.disabled:hover,
    .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:focus {
        border-color: rgba(2, 124, 255, 0.10);

        background-color: #008cba;
    }
    .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
        background-color: #008cba;
    }
    .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
        border-color: rgba(0, 0, 0, 0.1);
        background-color: #008cba;
    }
    #tblPart_Cartatmp_length {
        display: inline-table;
        margin: 0 auto;
        margin-top: 15px;
    }
    #tblPart_Cartatmp_filter {
        margin-right: 0;
        display: none;
    }
    .nav-tabs .nav-link, .nav-tabs .nav-link.disabled, .nav-tabs .nav-link.disabled:hover, .nav-tabs .nav-link.disabled:focus {
        border-color: rgba(0, 0, 0, 0.1);
        background-color: #007bff;
    }
    .nav-tabs .nav-link:hover, .nav-tabs .nav-link:focus {
        background-color: #007bff;
    }
    .mailbox-widget .custom-tab .nav-item .nav-link.active {
        background: 0 0;
        color: #fff;
        border-bottom: 5px solid #fff
    }

    </style>
    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
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
                    <spring:message code="Form" /></a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
            <div class="container col-md-12 col-lg-12">
            <div class="row">
            <div class="col-md-12 col-lg-12">
            <div class="card">
            <div class="card-body bg-primary text-white mailbox-widget pb-0">
                <h2 class="text-white pb-3"><spring:message code="Participantes, Cartas Temporales" /></h2>
                <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="inbox-tab" data-toggle="tab" aria-controls="inbox" href="#inbox" role="tab" aria-selected="true">
                            <span class="d-block d-md-none"><i class="ti-email"></i></span>
                            <span class="d-none d-md-block"> <spring:message code="Form" /></span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="sent-tab" data-toggle="tab" aria-controls="sent" href="#sent" role="tab" aria-selected="false">
                            <span class="d-block d-md-none"><i class="ti-export"></i></span>
                            <span class="d-none d-md-block"><spring:message code="List" /> <i class="badge badge-light">${listaDto.size()}</i> </span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade active show" id="inbox" aria-labelledby="inbox-tab" role="tabpanel">
            <div>

            <div class="">
            <c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
            <c:set var="DisabledLabel"><spring:message code="notenabled" /></c:set>
            <spring:url value="/cartas/saveCartaTmp" var="saveUrl"/>
            <spring:url value="/cartas/VersionCarta" var="VersionCartatUrl"/>
            <spring:url value="/cartas/ParteVersion" var="ParteVersionUrl"/>
            <spring:url value="/cartas/getNombre1" var="getNombre1Url"/>
            <spring:url value="/cartas/getNombre2" var="getNombre2Url"/>
            <spring:url value="/cartas/getApellido1" var="getApellido1Url"/>
            <spring:url value="/cartas/getApellido2" var="getApellido2Url"/>
            <spring:url value="/cartas/deleteAllTmp" var="deleteAllUrl"/>
            <spring:url value="/cartas/codigoParticipante" var="buscaCodigoUrl"/>
            <spring:url value="/cartas/listaParteTmpById" var="listaParteTmpByIdUrl"/>
            <spring:url value="/cartas/saveCartaExample" var="saveCartaExampleUrl"/>
            <spring:url value="/cartas/saveTmpsToOficial" var="saveTmpsToOficialUrl"/>
            <spring:url value="/cartas/CartaParticipantTmp" var="CartaParticipantTmpUrl"/>
            <c:set var="successMessage"><spring:message code="process.success" /></c:set>
            <div class="d-flex justify-content-between">
                <div class="p-2 bd-highlight">
                    <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom"
                       title="Ir al Listado Extensiones"
                       href="<spring:url value="/cartas/listExtensionTmp" htmlEscape="true "/>">
                        <i class="fas fa-clipboard-list" aria-hidden="true"></i>
                        <spring:message code="List"/>  <spring:message code="Extension"/>
                    </a>
                </div>
                <div class="p-2 bd-highlight"></div>
                <div class="p-2 bd-highlight"></div>
            </div>
            <hr/>

            <div class="container col-sm-12 col-lg-12">

            <form id="form_carta_tmp" name="form_carta_tmp" autocomplete="off"  class="needs-validation" novalidate>
            <div class="row" hidden="hidden">
                <div class="col-md-2">
                    <div class="form-group">
                        <label for="principal">principal</label>
                        <input type="text" class="form-control" disabled="disabled" id="principal" value="${partePrincipal}"/>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="accion">editando</label>
                        <input type="text" class="form-control" name="accion" id="accion" value="${accion}"/>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="id_participante_carta_tmp">id_participante_carta_tmp</label>
                        <input type="text" class="form-control" name="id_participante_carta_tmp" id="id_participante_carta_tmp" value="${caso.id}"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <label for="idparticipante"><spring:message code="lbl.participant" /></label>
                        <span class="required text-danger"> * </span>
                        <input type="text" class="form-control" id="idparticipante" name="idparticipante" value="${caso.idparticipante}" pattern="[0-9]*" required="required">
                        <div class="invalid-feedback">
                            campo requerido.
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="form-group">
                        <label for="idcarta"><spring:message code="lbl.study" /></label>
                        <span class="required text-danger"> * </span>
                        <select class="form-control" id="idcarta" name="idcarta" required="required">
                            <option selected value=""><spring:message code="select" />...</option>
                            <c:forEach items="${cartas}" var="e">
                                <c:choose>
                                    <c:when test="${caso.version.estudio.codigo eq e.codigo}">
                                        <option selected value="${e.codigo}">${e.codigo} - ${e.nombre}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${e.codigo}">${e.codigo} - ${e.nombre}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            campo requerido.
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="form-group">
                        <label for="version"><spring:message code="lbl.version.letter" />:</label>
                        <span class="required text-danger"> * </span>
                        <select name="version" id="version" class="form-control">
                            <option selected value=""><spring:message code="select" />...</option>
                            <c:forEach items="${version}" var="v">
                                <c:choose>
                                    <c:when test="${caso.version.idversion eq v.idversion}">
                                        <option selected value="${v.idversion}"> <spring:message code="${v.version}" />  </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${v.idversion}"><spring:message code="${v.version}" /> </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            campo requerido.
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="form-group">
                        <label for="parte"><spring:message code="Letter.Parts" />:</label>
                        <span class="required text-danger"> * </span>
                        <select class="form-control form-control-sm select2-multiple" multiple id="parte" name="parte"/>
                        <c:forEach items="${dp}" var="d">
                                  <c:choose>
                                      <c:when test="${d.acepta eq true}">
                                          <option selected value="${d.parte.idparte}">${d.parte.parte}</option>
                                      </c:when>
                                      <c:otherwise>
                                          <option value="${d.parte.idparte}">${d.parte.parte}</option>
                                      </c:otherwise>
                                  </c:choose>
                        </c:forEach>
                          </select>
<div class="invalid-feedback">
campo requerido.
</div>
</div>
</div>
</div>

<div class="row">
<div class="col-md-3">
<div class="form-group">
<label for="name1tutor"><spring:message code="first.name" /> <spring:message code="lbl.tutor" /> </label>
<span class="required text-danger"> * </span>
<input type="text" class="form-control onlytext form-control-sm" id="name1tutor" name="name1tutor" required="required" value="${caso.name1tutor}"/>
<div class="invalid-feedback">
campo requerido.
</div>
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<label for="name2tutor"><spring:message code="second.name" /> <spring:message code="lbl.tutor" /></label>
<input type="text" class="form-control onlytext form-control-sm" id="name2tutor" name="name2tutor"  value="${caso.name2tutor}">
</div>
</div>

<div class=" col-md-3">
<div class="form-group">
<label for="apellido1tutor"><spring:message code="first.surname" /> <spring:message code="lbl.tutor" /></label>
<span class="required text-danger"> * </span>
<input type="text" class="form-control onlytext form-control-sm" id="apellido1tutor" required="required" name="apellido1tutor"  value="${caso.surname1tutor}">
<div class="invalid-feedback">
campo requerido.
</div>
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<div class="form-group">
<label for="nombre2Firma"><spring:message code="second.surname" /> <spring:message code="lbl.tutor" /></label>
<input type="text" class="form-control onlytext form-control-sm" id="apellido2Firma" name="apellido2Firma"  value="${caso.surname2tutor}">
</div>
</div>
</div>

<div class="col-md-3">
<div class="form-group ">
<label for="relfam"><spring:message code="family.relationship" /> </label>
<span class="required text-danger"> * </span>
<select name="relfam" id="relfam" class="form-control" required="required"/>
<option selected value=""><spring:message code="select" />...</option>
<c:forEach items="${relFam}" var="rel">
<c:choose>
<c:when test="${caso.relfam eq rel.catKey}">
<option selected value="${rel.catKey}">${rel.spanish}</option>
</c:when>
<c:otherwise>
<option value="${rel.catKey}">${rel.spanish}</option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>
<div class="invalid-feedback">
campo requerido.
</div>
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<label for="fechacarta"><spring:message code="dateAdded" /></label>
<span class="required text-danger"> * </span>
<input type="text" class="form-control" required="required" name="fechacarta" id="fechacarta"
value="<fmt:formatDate value="${caso.fechacarta}" pattern="dd/MM/yyyy" />" data-date-end-date="+0d"/>
<div class="invalid-feedback">
campo requerido.
</div>
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<label for="proyecto"><spring:message code="lbl.project" /> </label>
<select name="proyecto" id="proyecto" class="form-control" required="required">
<option selected value=""><spring:message code="select" />...</option>
<c:forEach items="${proyecto}" var="p">
<c:choose>
<c:when test="${caso.proyecto eq p.catKey}">
<option selected value="${p.catKey}"><spring:message code="${p.spanish}" /></option>
</c:when>
<c:otherwise>
<option value="${p.catKey}"><spring:message code="${p.spanish}" /></option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>
<div class="invalid-feedback">
campo requerido.
</div>
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<label for="person"><spring:message code="lbl.resource"/></label>
<span class="required text-danger"> * </span>
<select name="person" id="person" class="form-control" required="required">
<option selected value=""><spring:message code="select"/>...</option>
<c:forEach items="${personal}" var="p">
<c:choose>
<c:when test="${caso.recurso eq p.codigo}">
<option selected value="${p.codigo}">${p.idPersona} - ${p.nombre}</option>
</c:when>
<c:otherwise>
<option value="${p.codigo}">${p.idPersona} - ${p.nombre}</option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>
<div class="invalid-feedback">
campo requerido.
</div>
</div>
</div>
</div>
<div class="row">
<div class="col-md-3">
<div class="form-group">
<div class="form-check mt-4">
<c:choose>
<c:when test="${caso.testigopresent eq true}">
<input class="form-check-input" type="checkbox" id="chktestigo" name="chktestigo" checked="checked">
</c:when>
<c:otherwise>
<input class="form-check-input" type="checkbox" id="chktestigo" name="chktestigo">
</c:otherwise>
</c:choose>
<label class="form-check-label" for="chktestigo">
    <spring:message code="lbl.witness.present" />
</label>
</div>
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<div class="form-check mt-4">
<c:choose>
<c:when test="${caso.contactoFuturo eq true}">
<input class="form-check-input" type="checkbox" id="contactoFuturo" name="contactoFuturo" checked="checked">
</c:when>
<c:otherwise>
<input class="form-check-input" type="checkbox" id="contactoFuturo" name="contactoFuturo">
</c:otherwise>
</c:choose>
<label class="form-check-label" for="contactoFuturo">
<spring:message code="lbl.Accept.future.contact" />
</label>
</div>
</div>
</div>
<div class="col-md-3">
    <div id="divAsentimiento">
        <div class="form-group">
            <label for="asentimiento"><spring:message code="lbl.assent" /> </label>
            <select name="asentimiento" id="asentimiento" class="form-control" required="required">
                <option selected value=""><spring:message code="select" />...</option>
                <c:forEach items="${SiNoNA}" var="s">
                    <c:choose>
                        <c:when test="${s.catKey  eq caso.asentimiento}">
                            <option selected value="${s.catKey}"> ${s.catKey} -<spring:message code="${s.spanish}" /></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s.catKey}"> ${s.catKey} - <spring:message code="${s.spanish}" /></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <div class="invalid-feedback">
                campo requerido.
            </div>
        </div>
    </div>
<%--<div class="form-group">
<div class="form-check mt-4">
<c:choose>
<c:when test="${caso.asentimiento eq true}">
<input class="form-check-input" type="checkbox" id="asentimiento" name="asentimiento" checked="checked">
</c:when>
<c:otherwise>
<input class="form-check-input" type="checkbox" id="asentimiento" name="asentimiento">
</c:otherwise>
</c:choose>
<label class="form-check-label" for="asentimiento">
<spring:message code="Asentimiento" />
</label>
</div>
</div>--%>
</div>

<div class="col-md-3">
<div class="form-group">
<label for="tipoasentimiento"><spring:message code="type.assent" /> </label>
<select name="tipoasentimiento" id="tipoasentimiento" class="form-control" required="required">
<option selected value=""><spring:message code="select" />...</option>
<c:forEach items="${tpoasent}" var="ta">
<c:choose>
<c:when test="${caso.tipoasentimiento eq ta.catKey}">
<option selected value="${ta.catKey}">${ta.catKey} - <spring:message code="${ta.spanish}" /></option>
</c:when>
<c:otherwise>
<option value="${ta.catKey}">${ta.catKey} - <spring:message code="${ta.spanish}" /></option>
</c:otherwise>
</c:choose>
</c:forEach>
</select>
<div class="invalid-feedback">
campo requerido.
</div>
</div>
</div>
</div>

<div id="showDivTestigo" style="display: none">
<div class="row">
<div class="col-md-3">
<div class="form-group">
<label for="nombre1Testigo"><spring:message code="first.name" /> <spring:message code="lbl.witness" /> </label>
<span class="required text-danger"> * </span>
<input type="text" class="form-control onlytext focusNext form-control-sm" tabindex="5" id="nombre1Testigo" name="nombre1Testigo" value="${caso.nombre1testigo}"/>
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<label for="nombre2Testigo"><spring:message code="second.name" /> <spring:message code="lbl.witness" /></label>
<input type="text" class="form-control onlytext focusNext form-control-sm" tabindex="6" id="nombre2Testigo" name="nombre2Testigo" value="${caso.nombre2testigo}">
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<label for="apellido1Testigo"><spring:message code="first.surname" /> <spring:message code="lbl.witness" /></label>
<span class="required text-danger"> * </span>
<input type="text" class="form-control onlytext focusNext form-control-sm" tabindex="7" id="apellido1Testigo" name="apellido1Testigo" value="${caso.apellido1testigo}">
</div>
</div>

<div class="col-md-3">
<div class="form-group">
<label for="apellido2Testigo"><spring:message code="second.surname" /> <spring:message code="lbl.witness" /></label>
<input type="text" class="form-control onlytext focusNext form-control-sm" tabindex="8" id="apellido2Testigo" name="apellido2Testigo" value="${caso.apellido2testigo}">
</div>
</div>

</div>
</div>

<div class="row">
<div class=" col-md-12">
<div class="form-group">
<label for="observacion"><spring:message code="observacion" /></label>
<textarea class="form-control form-control-sm"  id="observacion" name="observacion" placeholder="observacion" rows="3">${caso.observacion}</textarea>
</div>
</div>
</div>

<div class="d-flex justify-content-between">
<div class="p-2 bd-highlight">
<button id="btnSave" type="submit" class="btn btn-primary btn-lg btn-ladda" data-style="expand-right">
<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" /></button>
</div>
<div class="p-2 bd-highlight"></div>
<div class="p-2 bd-highlight">
<a href="<spring:url value="/cartas/CartaParticipantTmp" htmlEscape="true"/>" class="btn btn-warning btn-lg btn-ladda" data-style="expand-right">
<spring:message code="cancel" />
<i class="fa fa-minus-circle" aria-hidden="true"></i>
</a>
</div>
</div>

</form>

</div>

</div>
</div>
</div>

<div class="tab-pane fade" id="sent" aria-labelledby="sent-tab" role="tabpanel">
<div class="">
<div class="">
<div class="row">
<div class="col-md-12">

    <hr/>
    <div class="">
    <div class="table-responsive" style="height:600px">
     <table id="tblPart_Cartatmp" class="table table-bordered email-table no-wrap table-hover v-middle mb-0 font-14 dt-responsive nowrap" style="width: 100%">
   <thead>
   <tr>
       <th scope="col" class="text-center"><spring:message code="id"/></th>
       <th scope="col" class="text-center"><spring:message code="code"/></th>
       <th scope="col" class="text-center"><spring:message code="dateAdded"/></th>
       <th scope="col" class="text-center"><spring:message code="tutor"/></th>
       <th scope="col" class="text-center"><spring:message code="user"/></th>
       <td scope="col" class="text-center"><spring:message code="actions"/></td>
   </tr>
   </thead>
   <tbody>
<c:forEach items="${listaDto}" var="list"  varStatus="theCount">
   <tr>
       <c:set var="confirmar"><spring:message code="confirm" /></c:set>
       <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
       <c:set var="fecha"><spring:message code="lbl.date" /></c:set>

       <spring:url value="/cartas/extensionTmp/{codigo}" var="extensionUrl">
           <spring:param name="codigo" value="${list.codigo}" />
       </spring:url>

       <spring:url value="/cartas/editTmp/{codigo}" var="editTmpUrl">
           <spring:param name="codigo" value="${list.codigo}" />
       </spring:url>

       <spring:url value="/cartas/desactAllTmp/{idparticipantecartatmp}"
                   var="desactUrl">
           <spring:param name="idparticipantecartatmp" value="${list.codigo}" />
       </spring:url>

       <td class="text-center">${list.codigo}</td>
       <td class="text-center">${list.idparticipante}</td>
       <td class="text-center">${list.fechacarta}</td>
       <td class="text-center">${list.nombfirma} ${list.apellido1Firma}</td>
       <td class="text-center">${list.nombreUsuario}</td>
       <td scope="row" class="text-center">
           <div class="btn-group dropleft">
               <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   <spring:message code="actions"/>
               </button>
               <div class="dropdown-menu">
                   <a data-toggle="tooltip" data-placement="bottom" title="partes de la carta" class="dropdown-item btnModal" data-id="${list.codigo}">
                       <i class="fa fa-eye" aria-hidden="true"></i>
                       <spring:message code="view"/> </a>
                   <a title="<spring:message code="edit" />" href="${fn:escapeXml(editTmpUrl)}" class="dropdown-item edit"><i class="fas fa-user-edit text-warning"></i> <spring:message code="edit"/></a>
                   <div class="dropdown-divider"></div>
                   <a title="<spring:message code="disable" />" data-id="${fn:escapeXml(desactUrl)}" class="dropdown-item desact"><i class="fas fa-user-times text-danger"></i><spring:message code="disable"/></a>
                   <c:choose>
                       <c:when test="${list.tineneExtension eq true}">
                           <a class="dropdown-item" href="${fn:escapeXml(extensionUrl)}">
                                 <i class="fas fa-user-plus text-primary"></i>
                               <spring:message code="Extension"/>
                           </a>
                       </c:when>
                       <c:otherwise>
                       </c:otherwise>
                   </c:choose>
               </div>
           </div>
       </td>
   </tr>
</c:forEach>
   </tbody>
</table>
    </div>
  </div>
                                <hr/>
                                <div class="d-flex justify-content-between">
                                    <div class="p-2 bd-highlight">

                                    </div>
                                    <div class="p-2 bd-highlight"></div>
                                    <div class="p-2 bd-highlight">
                                        <button id="btnFinalizar" class="btn btn-success btn-lg" data-toggle="tooltip" data-placement="bottom" title="Subir Información">
                                            <i class="fa fa-upload" aria-hidden="true"></i>
                                            <spring:message code="end" /> <spring:message code="processes" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-lg" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title float-left"> <i class="fa fa-id-card-o" aria-hidden="true"></i>  <spring:message code="Letter.Parts" /></h5>
                        <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" class="form-control" id="accionUrl2"/>
                        <div class="table-responsive">
                            <table id="tblDetallePartesTmp" class="table table-bordered table-striped dt-responsive nowrap" style="width: 100%">
                                <thead>
                                <tr>
                                    <th scope="col"><spring:message code="code" /></th>
                                    <th scope="col"><spring:message code="study" /></th>
                                    <th scope="col"><spring:message code="Version" /></th>
                                    <th scope="col"><spring:message code="lbl.Parts" /></th>
                                    <th scope="col"><spring:message code="lbl.Accepted" /></th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="lbl.close" /></button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.conainer-fluid -->

        <%--init modal--%>
        <div class="modal fade bd-example-modal-lg" id="basic2" tabindex="-1" data-role="basic2" data-backdrop="static" data-aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
                        <div id="titulo"></div>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="accionUrl"/>
                        <div id="cuerpo"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-dismiss="modal"><spring:message code="cancel" /></button>
                        <button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <%--finish modal--%>
    </div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/DataTables/DataTables-1.10.24/js/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/DataTables/DataTables-1.10.24/js/dataTables.bootstrap4.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

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
<spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/toastr.min.js" var="toastrJs" />
<script type="text/javascript" src="${toastrJs}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/sweetalert.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/Cartas/ParticipanteCartaTmp.js" var="editcartas"/>
<script type="text/javascript" src="${editcartas}"></script>

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

<script type="text/javascript">
    $(document).ready(function(){
        versionVacia();
        function versionVacia(){
            if($("#accion").val() == ""){
                $("#version").select2().empty();
            }
        }

        parametros = {
            saveUrl                 : "${saveUrl}",
            VersionCartatUrl        : "${VersionCartatUrl}",
            ParteVersionUrl         : "${ParteVersionUrl}",
            getNombre1Url           : "${getNombre1Url}",
            getNombre2Url           : "${getNombre2Url}",
            getApellido1Url         : "${getApellido1Url}",
            getApellido2Url         : "${getApellido2Url}",
            successmessage          : "${successMessage}",
            buscaCodigoUrl          : "${buscaCodigoUrl}",
            listaParteTmpByIdUrl    : "${listaParteTmpByIdUrl}",
            saveCartaExampleUrl     : "${saveCartaExampleUrl}",
            deleteAllUrl            : "${deleteAllUrl}",
            editTmpUrl              : "${editTmpUrl}",
            saveTmpsToOficialUrl    : "${saveTmpsToOficialUrl}",
            CartaParticipantTmpUrl  : "${CartaParticipantTmpUrl}",
            desactUrl             : "${desactUrl}"
        };
        saveCartaTMP.init(parametros);


        var table = $("#tblPart_Cartatmp").DataTable({
            "orderCellsTop"   : true,
            "fixedHeader"     : true,
            "oLanguage"  : {
                "sUrl"   : "${dataTablesLang}"
            }, columnDefs     : [{
                width         : 90,
                targets       : 1,
                className     : 'text-center'
            }]
        });
        // Order by the grouping
        var groupColumn = 1;
        var tblParteTMP = $("#tblDetallePartesTmp").DataTable({
            "columnDefs": [{
                "targets": [ 0 ],
                "visible": false,
                "searching": false
            },{ "visible": false,
                "targets": groupColumn
            },{
                targets: [1,2,3,4],
                className: 'text-center'
            }],
            "paging":   false,
            "info":     false,
            "order": [[ groupColumn, 'asc' ]],
            "drawCallback": function ( settings ) {
                var api = this.api();
                var rows = api.rows( {page:'current'} ).nodes();
                var last=null;

                api.column(groupColumn, {page:'current'} ).data().each( function ( group, i ) {
                    if ( last !== group ) {
                        $(rows).eq( i ).before(
                          '<tr class="group"><td class="text-center" colspan="3">'+group+'</td></tr>'
                        );
                        last = group;
                    }
                });
            }
        });
        // Order by the grouping
        $('#tblDetallePartesTmp tbody').on( 'click', 'tr.group', function () {
            var currentOrder = tblParteTMP.order()[0];
            if ( currentOrder[0] === groupColumn && currentOrder[1] === 'asc' ) {
                tblParteTMP.order( [ groupColumn, 'desc' ] ).draw();
            }
            else {
                tblParteTMP.order( [ groupColumn, 'asc' ] ).draw();
            }
        } );

          //input de busqueda en tabla
        $('#tblPart_Cartatmp thead tr').clone(true).appendTo( '#tblPart_Cartatmp thead' );
        $('#tblPart_Cartatmp thead tr:eq(1) th').each( function (i) {
            var title = $(this).text();
            $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
            $( 'input', this ).on( 'keyup change', function () {
                if ( table.column(i).search() !== this.value ) {
                     table.column(i).search( this.value ).draw();
                }
            });
        } );

        $('#tbltmp tbody').on('click', '.btnModal', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col4 = currentRow.find("td:eq(4)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Habilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-warning">'+ col4 + '</h3>');
            $('#basic').modal('show');
        });

        $("#tblPart_Cartatmp tbody").on("click", ".delete", function(){
            var currentRow = $(this).closest("tr");
            var col0 = currentRow.find("td:eq(0)").text();
            var col1 = currentRow.find("td:eq(1)").text();
            eliminarAll(col0,col1);
        });
        function eliminarAll(id, fecha) {
            swal({
                   title: "Eliminar? ",
                   text: "Registro: " + id + " con Fecha: " + fecha,
                   type: "warning",
                   showCancelButton: true,
                   confirmButtonClass: "btn-danger",
                   confirmButtonText: "Si, Bórralo!",
                   cancelButtonText: "No, Borres plx!",
                   closeOnConfirm: false,
                   closeOnCancel: false
                    },
                    function (isConfirm) {
                        if (isConfirm) {
                            $.post(parametros.deleteAllUrl, {idparticipantecartatmp: col0, ajax: 'true'}, function (data) {
                                swal("Eliminado!", "con éxito!", "success");
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            }).fail(function () {
                                setTimeout(function () {
                                    swal("Error!", "Servidor no respode!", "error");
                                }, 2000);
                            });
                        } else {
                            swal("Cancelado!", "Registro está seguro! :)", "error");
                        }
                    });
        }

        $("#tblPart_Cartatmp tbody").on("click", ".btnModal", function(){
            var codigo = $(this).data('id');
            $.getJSON(parametros.listaParteTmpByIdUrl,{codigo: codigo},function(data){
                tblParteTMP.clear().draw( false );
                for(var i = 0; i < data.length; i++){
                    var iddetalle = data[i].iddetalle;
                    var nombre_parte = data[i].parte.parte;
                    var acepta = (data[i].acepta == true)?'<h5><span class="badge badge-pill badge-success">SI</span></h5>':'<h5><span class="badge badge-pill badge-danger">NO</span></h5>';
                    var study = data[i].parte.version.estudio.nombre;
                    var versione = data[i].parte.version.version;
                    tblParteTMP.row.add([
                    iddetalle,
                    study,
                    versione,
                    nombre_parte,
                    acepta
                ]).draw( false );
              }
                $('#basic').modal('show');
            });
        });
        // Order by the grouping
        $('#tblPart_Cartatmp tbody').on( 'click', 'tr.group', function () {
            var currentOrder = table.order()[0];
            if ( currentOrder[0] === groupColumn && currentOrder[1] === 'asc' ) {
                table.order( [ groupColumn, 'desc' ] ).draw();
            }
            else {
                table.order( [ groupColumn, 'asc' ] ).draw();
            }
        } );
        $("#fechacarta").datepicker({
            autoclose: true,
            format: "dd/mm/yyyy"
        });
        $('.accordion-toggle').click(function(){
            $('.hiddenRow').hide();
            $(this).next('tr').find('.hiddenRow').show();
        });

        $("#tableParte").DataTable();
        $("#relfam").select2();
        $("#idcarta").select2();
        $("#parte").select2();
        $("#version").select2();
        $("#person").select2();
        $("#asentimiento").select2();
        $("#tipoasentimiento").select2();
        $("#proyecto").select2();
        $('.onlytext').keypress(function (e) {
            var tecla = document.all ? tecla = e.keyCode : tecla = e.which;
            return !((tecla > 47 && tecla < 58) || tecla == 46);
        });
        $("#chktestigo").on("click", function(){
            var status = $(this).prop("checked");
            if(status == true){
                $("#showDivTestigo").fadeIn("slow");
                $("#nombre1Testigo").prop('required',true);
                $("#apellido1Testigo").prop('required',true);
            }else{
                $("#showDivTestigo").fadeOut("slow");
                $("#nombre1Testigo").prop('required', false);
                $("#apellido1Testigo").prop('required', false);
            }
        });

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
        $("#parte").on("select2-removing", function(e) {
            var p = $("#principal").val();
            if (e.choice.text === p) {
                e.preventDefault();
                $(this).select2("close");
            }
        });

        $("#btnFinalizar").on("click", function(){
            swal({
                title: "Deseas pasar la información?",
                text: "Recuerda tener la información del Participante",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            }, function () {
                PasarDatos();
            });
        });

        function PasarDatos(){
            /* init */
            var dataArrayToSend = [];
            table.column(0).data().each( function ( value, index ) {
                dataArrayToSend.push(parseInt(value));
            });
            if(dataArrayToSend.length > 0) {
                jQuery.ajax({
                    url: parametros.saveTmpsToOficialUrl,
                    type: "POST",
                    data: JSON.stringify(dataArrayToSend),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (result,status) {
                        debugger;
                      console.log("result: "+result.mensaje +" stado:"+status);
                        if(result.mensaje != null){
                            //swal("Información!", result.mensaje, "info");
                            swal({
                                title: "Información!",
                                text: result.mensaje,
                                type: "info",
                                closeOnConfirm: true
                            });

                            window.setTimeout(function(){
                                window.location.reload();
                            }, 1600);
                        }
                        //swal("Excelente!", "Petición finalizada!", "success");
                    },error: function (xhr, thrownError) {
                        swal("Error "+ xhr.status, "Internal Server! ", "error");
                    }
                });
            } /* finish */
        }

        $(".desact").click(function(){
            $('#accionUrl').val($(this).data('id'));
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            debugger;
            var currentRow = $(this).closest("tr");
            var col1 = currentRow.find("td:eq(1)").text();
            var col2 = currentRow.find("td:eq(2)").text();
            $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+ '<strong>'+ col1 +'</strong>'+ " ${fecha} "+'<strong>'+ col2 +'</strong></h3>');
            $('#basic2').modal('show');
        });

        if ("${RegistrosBloqueado}"){
            toastr.info("${DisabledLabel}", "INFO",{timeOut:7000} );
        }
         /*  if ("${usuarioDeshabilitado}"){
            toastr.error("${userDisabledLabel}", "${nombreUsuario}" );
        }

        $('#tableParte tbody').on('click', '.Activar', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col4 = currentRow.find("td:eq(4)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Habilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-warning">'+ col4 + '</h3>');
            $('#basic').modal('show');
        });
        $('#tableParte tbody').on('click', '.desact', function () {
            var id = $(this).data('id');
            $('#accionUrl').val($(this).data('id'));
            var currentRow = $(this).closest("tr");
            var col4 = currentRow.find("td:eq(4)").text();
            $('#titulo').html('<h2 class="modal-title">'+ "<i class='fa fa-exclamation-triangle' aria-hidden='true'></i>" + "Deshabilitar?" +'</h2>');
            $('#cuerpo').html('<h3 class="text-danger">'+ col4 + '</h3>');
            $('#basic').modal('show');
        });*/
    });
    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }
</script>
</body>
</html>
