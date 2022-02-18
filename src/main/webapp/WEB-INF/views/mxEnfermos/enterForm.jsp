<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 15/10/2020
  Time: 10:43
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
    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>

    <style>

        .form-control:disabled, .form-control[readonly] {
            background-color: #e9ecef00;
            opacity: 1;
        }

        .search-result .title h3 {
            margin: 0 0 15px;
            color: #333;
        }
        .search-result .title p {
            font-size: 12px;
            color: #333;
        }
        .well {
            border: 0;
            padding: 20px;
            min-height: 63px;
            background: #fff;
            box-shadow: none;
            border-radius: 3px;
            position: relative;
            max-height: 100000px;
            border-bottom: 2px solid #ccc;
            transition: max-height 0.5s ease;
            -o-transition: max-height 0.5s ease;
            -ms-transition: max-height 0.5s ease;
            -moz-transition: max-height 0.5s ease;
            -webkit-transition: max-height 0.5s ease;
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
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/mx/enfermo/list" htmlEscape="true "/>"><spring:message code="lbl.list" /> <spring:message code="lbl.serologia.enfermo" /></a>
                <i class="fa fa-angle-right"></i>
                <spring:message code="lbl.form" />
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
              <div class="container col-md-10 col-lg-12">
                  <div class="card">
                      <div class="card-header">
                          <h3 class="page-title">
                              <spring:message code="lbl.serologia.enfermo" /> -
                              <strong>
                                  <c:choose>
                                      <c:when test="${agregando}">
                                          <spring:message code="add" />
                                      </c:when>
                                      <c:otherwise>
                                          <spring:message code="edit" />
                                      </c:otherwise>
                                  </c:choose>
                              </strong>
                          </h3>
                          <spring:url value="/mx/enfermo/guardarRecepcionEnfermo" var="saveFormUrl"/>
                          <spring:url value="/mx/enfermo/searchParticipant" var="searchPartUrl"/>
                          <c:choose>
                              <c:when test="${listado}">
                                  <spring:url value="/mx/enfermo/list/" var="listaUrl"/>
                              </c:when>
                              <c:otherwise>
                                  <spring:url value="/mx/enfermo/search/" var="listaUrl"/>
                              </c:otherwise>
                          </c:choose>
                      </div>
                      <div class="card-body">
                          <c:set var="successMessage"><spring:message code="process.success" /></c:set>
                          <c:set var="errorProcess"><spring:message code="process.error" /></c:set>
                          <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                              <div class="row">
                                  <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                      <div class="well search-result">
                                          <div class="input-group">
                                              <input type="text" class="form-control form-control-lg"  placeholder="Ingrese el código" id="parametro" name="parametro" tabindex="1">
                                              <span class="input-group-btn">
                                          <button class="btn btn-info btn-lg" type="submit">
                                              <i class="fas fa-search"></i> <spring:message code="search" />
                                          </button>
                                              </span>
                                          </div>
                                          <div id="gendererror" class="text-danger"></div>
                                      </div>

                                  </div>
                              </div>
                          </form>

                          <form class="form-horizontal" name="save-Serologia-form" id="save-Serologia-form" autocomplete="off">
                              <div class="form-row" hidden="hidden">
                                  <div class="col-md-3">
                                      <label for="idRecepcion">idRecepcion</label>
                                      <input id="idRecepcion" name="idRecepcion" type="text" class="form-control" value="${recepcionEnfermo.idRecepcion}"/>
                                  </div>
                                  <div class="col-md-3">
                                      <label for="tiporequest">tiporequest</label>
                                    <input type="text" class="form-control" id="tiporequest" name="tiporequest" value="${editando}" />
                                  </div>
                              </div>

                              <div class="form-row">
                                  <div class="col-md-3">
                                      <label><spring:message code="code" /> <spring:message code="lbl.participant" /></label>
                                      <input type="text" class="form-control" id="codigoParticipante" name="codigoParticipante" value="${recepcionEnfermo.participante}" readonly="readonly">
                                  </div>
                                  <div class="form-group col-md-7">
                                      <label for="nombreCompleto"><spring:message code="lbl.names.surnames" /></label>
                                      <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" value="${recepcionEnfermo.nombreCompleto}" disabled="disabled">
                                  </div>


                                  <div class="form-group col-md-2">
                                      <label for="casa"><spring:message code="lbl.house" /></label>
                                      <input type="text" class="form-control" name="casa" id="casa" value="${recepcionEnfermo.codigoCasa}" readonly>
                                  </div>

                              </div>

                              <div class="row">
                                  <div class="col-sm-6">
                                      <div class="card">
                                          <div class="card-body">
                                              <h5 class="card-title">Edad del Participante.</h5>
                                              <div class="form-row">

                                                  <div class="form-group col-md-4">
                                                      <input type="text" class="form-control" id="edad_year" name="edad_year" value="${recepcionEnfermo.edadParteAnios}"  disabled="disabled">
                                                      <small class="text-muted"> Años.</small>
                                                  </div>

                                                  <div class="form-group col-md-4">
                                                      <input type="text" class="form-control" id="edad_meses" name="edad_meses" value="${recepcionEnfermo.edadParteMeses}"  disabled="disabled">
                                                      <small  class="text-muted"> Meses.</small>
                                                  </div>

                                                  <div class="form-group col-md-4">
                                                      <input type="text" class="form-control" id="edad_dias" name="edad_dias" value="${recepcionEnfermo.edadParteDias}"  disabled="disabled">
                                                      <small class="text-muted"> Dias.</small>
                                                  </div>

                                              </div>
                                          </div>
                                      </div>

                                  </div>

                                  <div class="col-sm-6">
                                      <div class="card">
                                          <div class="card-body">
                                              <h5 class="card-title">Muestra.</h5>
                                              <div class="form-row">

                                                  <div class="form-group col-md-6">
                                                      <input type="text" class="form-control datepicker" id="fecha" name="fecha"
                                                             value="${recepcionEnfermo.fechaRecepcion}" />
                                                      <small class="text-muted"> Fecha de Toma dd/mm/yyyy.</small>
                                                  </div>

                                                  <div class="form-group col-md-6">
                                                      <input type="text" name="volumen" id="volumen" class="form-control focusNext" placeholder="Volumen" value="${recepcionEnfermo.volumen}" tabindex="2">
                                                      <small class="text-muted"> <spring:message code="lbl.volumen"/></small>
                                                  </div>
                                              </div>
                                          </div>
                                      </div>
                                  </div>
                              </div>

                              <div class="row">
                                  <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                                      <div class="form-group">
                                          <label class="form-control-label" for="fis"><spring:message code="fecha_inicio_sintomas" /></label>
                                          <input type="text" class="form-control datepicker" id="fis" name="fis" value="${recepcionEnfermo.fis}" required/>
                                      </div>
                                  </div>
                                  <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                                      <div class="form-group">
                                          <label class="form-control-label" for="fif"><spring:message code="fecha_inicio_fiebre" /></label>
                                          <input type="text" class="form-control datepicker" id="fif" name="fif" value="${recepcionEnfermo.fif}" required/>
                                      </div>

                                  </div>
                                  <div class="col-xl-2 col-lg-2 col-md-4 col-sm-6">
                                      <div class="form-group">
                                          <label class="form-control-label"><spring:message code="consulta" />
                                              <span class="required">*</span>
                                          </label>
                                          <select class="form-control focusNext" id="tipoConsulta" name="tipoConsulta" required>
                                              <option selected value=""><spring:message code="select" />...</option>
                                              <c:forEach var="item" items="${catTipoConsulta}">
                                                  <c:choose>
                                                      <c:when test="${item.catKey eq recepcionEnfermo.consulta}">
                                                          <option selected value="${item.catKey}">${item.spanish}</option>
                                                      </c:when>
                                                      <c:otherwise>
                                                          <option value="${item.catKey}">${item.spanish}</option>
                                                      </c:otherwise>
                                                  </c:choose>
                                              </c:forEach>
                                          </select>
                                      </div>
                                  </div>
                                  <div class="col-xl-2 col-lg-2 col-md-4 col-sm-12">
                                      <div class="form-group">
                                          <label class="form-control-label"><spring:message code="categoria" />:
                                              <span class="required">*</span>
                                          </label>
                                          <select class="form-control focusNext" id="categoria" name="categoria" required>
                                              <option selected value=""><spring:message code="select" />...</option>
                                              <c:forEach var="item" items="${catCategoria}">
                                                  <c:choose>
                                                      <c:when test="${item.catKey eq recepcionEnfermo.categoria}">
                                                          <option selected value="${item.catKey}">${item.spanish}</option>
                                                      </c:when>
                                                      <c:otherwise>
                                                          <option value="${item.catKey}">${item.spanish}</option>
                                                      </c:otherwise>
                                                  </c:choose>
                                              </c:forEach>
                                          </select>
                                      </div>
                                  </div>
                                  <div class="col-xl-2 col-lg-2 col-md-4 col-sm-12">
                                      <div class="form-group">
                                          <label class="form-control-label"><spring:message code="lbl.sample.type.phase" />:
                                              <span class="required">*</span>
                                          </label>
                                          <select class="form-control focusNext" id="faseMuestra" name="faseMuestra" required>
                                              <option selected value=""><spring:message code="select" />...</option>
                                              <c:forEach var="item" items="${catFaseMuestra}">
                                                  <c:choose>
                                                      <c:when test="${item.catKey eq recepcionEnfermo.tipoMuestra}">
                                                          <option selected value="${item.catKey}">${item.spanish}</option>
                                                      </c:when>
                                                      <c:otherwise>
                                                          <option value="${item.catKey}">${item.spanish}</option>
                                                      </c:otherwise>
                                                  </c:choose>
                                              </c:forEach>
                                          </select>
                                      </div>
                                  </div>
                              </div>
                              <div class="form-row">
                                  <div class="form-group col-md-12">
                                      <label for="observacion"><spring:message code="lbl.Observation" /></label>
                                      <textarea class="form-control submit_on_enter" id="observacion" name="observacion"  cols="3" rows="5">${recepcionEnfermo.observacion} </textarea>
                                  </div>
                              </div>

                              <div class="form-row">
                                  <div class="col-md-4">
                                      <button type="submit" class="btn btn-primary btn-block btn-lg">
                                          <i class="fa fa-save"></i>
                                          <spring:message code="save" />
                                      </button>
                                  </div>
                                  <div class="col-md-4"></div>
                                  <div class="col-md-4">
                                      <a href="${fn:escapeXml(listaUrl)}" class="btn btn-warning btn-block btn-lg">
                                          <i class="fa fa-minus-circle" aria-hidden="true"></i>
                                          <spring:message code="cancel" /></a>
                                  </div>
                              </div>
                          </form>
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

<spring:url value="/resources/js/libs/jquery.maskedinput.min.js" var="mask" />
<script type="text/javascript" src="${mask}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<!-- bootstrap datetimepicker -->
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/moment-with-locales.js" var="moment" />
<script src="${moment}"></script>
<spring:url value="/resources/js/libs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepicker" />
<script src="${datetimepicker}"></script>

<spring:url value="/resources/js/views/mxEnfermos/ProcessSickSample.js" var="serologia"/>
<script type="application/javascript" src="${serologia}"></script>

<script type="application/javascript">
    $(document).ready(function(){
        $('#fechaNac').mask("9999-99-99", {placeholder: 'yyyy-MM-dd' });
        var today = moment().format('YYYY-MM-DD');

        actDesact();
        var points ={
            "searchPartUrl" : "${searchPartUrl}",
            "saveFormUrl"   : "${saveFormUrl}",
            "successMessage": "${successMessage}",
            "listaUrl" : "${listaUrl}"
        };
        ProcessSickSample.init(points);

        $('.datepicker').datetimepicker({
            format: 'L',
            locale: "${lenguaje}",
            maxDate: new Date(),
            useCurrent: false
        });

        if ($("#tiporequest").val() === 'false') {//es nuevo
            $('#fecha').val(moment().format('DD/MM/YYYY'));
        }

        $("#tipoConsulta").select2();
        $("#categoria").select2();
        $("#faseMuestra").select2();


        function actDesact(){
            var verif = "${editando}";
            if(verif === "true"){
                $("#parametro").prop("disabled", true);
            }else{
                $("#parametro").removeAttr("disabled");
                $("#volumen").val(0);
            }
        }
        document.addEventListener('keypress', function(evt) {
            // Si el evento NO es una tecla Enter
            if (evt.key !== 'Enter') {
                return;
            }
            let element = evt.target;
            // Si el evento NO fue lanzado por un elemento con class "focusNext"
            if (!element.classList.contains('focusNext')) {
                return;
            }
            // AQUI logica para encontrar el siguiente
            let tabIndex = element.tabIndex + 1;
            var next = document.querySelector('[tabindex="'+tabIndex+'"]');
            // Si encontramos un elemento
            if (next) {
                next.focus();
                event.preventDefault();
            }
        });
        $("#parametro").focus();
    });
</script>

</body>
</html>
