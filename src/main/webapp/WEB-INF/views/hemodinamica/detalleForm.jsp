<%--
  Created by IntelliJ IDEA.
  User: Lizandro Serrano
  Date: 15/08/2024
  Time: 11:28 AM
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
        .form-control-feedback {
            margin-top: 0.25rem;
            width: 95%;
            text-align: left !important;
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
                <a href="<spring:url value="/hemo/list" htmlEscape="true"/>">
                  <spring:message code="lbl.list"/></a>
                <i class="fa fa-angle-right"></i>
                <a href="${fn:escapeXml(editDetailUrl)}"> <spring:message code="edit"/> <spring:message code="Parámetro"/> </a>
            </li>
        </ol>
        <c:set var="successMessage"><spring:message code="process.success"/></c:set>
        <spring:url value="/hemo/saveDetail" var="saveDetailUrl"/>
        <spring:url value="/hemo/list" var="listUrl"/>
        <spring:url value="/hemo/UpdateDetalleHemo" var="upateDetHemoUrl"/>
        <spring:url value="/hemo/editDetail/{idHemoDetalle}" var="editDetailUrl">
            <spring:param name="idHemoDetalle" value="${obj.idHemoDetalle}" />
        </spring:url>
        <spring:url value="/hemo/listDetailsHemo/{idDatoHemo}" var="listDetailsHemoUrl">
            <spring:param name="idDatoHemo" value="${datos.idDatoHemo}" />
        </spring:url>
        <spring:url value="/hemo/addDetails/{idDatoHemo}" var="reloadUrl">
            <spring:param name="idDatoHemo" value="${datos.idDatoHemo}" />
        </spring:url>
        <div class="container-fluid">
            <div class="animated-fadeIn">
                <div class="card">
                    <div class="card-header">
                        <h5> <i class="fas fa-file-alt"></i> ${tituloForm} <spring:message code="Parámetro"/></h5>
                    </div>
                    <div class="card-body">
                        <form name="formDetailHemo" autocomplete="off"  id="formDetailHemo" class="form-horizontal">
                            <div class="container col-md-12">
                            <div hidden="hidden">
                                <div class="col-md-4">
                                    <label for="idDatoHemo">idDatoHemo:</label>
                                    <input type="text" class="form-control"  id="idDatoHemo" name="idDatoHemo" value="${datos.idDatoHemo}" readonly>
                                </div>
                                <div class="col-md-2">
                                    <label for="idHemoDetalle">idHemoDetalle:</label>
                                    <input type="text" class="form-control"  id="idHemoDetalle" name="idHemoDetalle" value="${obj.idHemoDetalle}" readonly>
                                </div>
                                <div class="col-md-2">
                                    <label for="editando">editando</label>
                                    <input type="text" class="form-control" name="editando" id="editando" value="${editando}" class="form-control" readonly/>
                                </div>
                                <div class="col-md-2">
                                    <label for="numParams">numParams</label>
                                    <input type="text" class="form-control" name="numParams" id="numParams" value="${numParameter}" class="form-control" readonly/>
                                </div>
                                <div class="col-md-2">
                                    <label for="contParams">contParams</label>
                                    <input type="text" class="form-control" name="contParams" id="contParams" value="${contParams}" class="form-control" readonly/>
                                </div>
                            </div>


                                <div class="row">
                                    <div class="col-md-12 mt-2">
                                        <h5 class="font-weight-bold" style="font-family: Roboto">Clasificación del Dengue: </h5>
                                        <div class="form-group">
                                            <label class="radio-inline mr-1">
                                               <c:choose>
                                                   <c:when test="${obj.signo eq 'Sin signo de Alarma'}">
                                                       <input type="radio" id="customRadio1" name="signo" class="custom-control-input" value="Sin signo de Alarma" checked="checked">
                                                   </c:when>
                                                   <c:otherwise>
                                                       <input type="radio" id="customRadio1" name="signo" class="custom-control-input" value="Sin signo de Alarma">
                                                   </c:otherwise>
                                               </c:choose>
                                                <spring:message  code="CLASIFICACIONDENGUE_SinAlarma"></spring:message>
                                            </label>
                                            <label class="radio-inline mr-1">
                                                <c:choose>
                                                    <c:when test="${obj.signo eq 'Con signo de Alarma'}">
                                                        <input type="radio" id="customRadio2" name="signo" class="custom-control-input" value="Con signo de Alarma" checked="checked">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" id="customRadio2" name="signo" class="custom-control-input" value="Con signo de Alarma">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="CLASIFICACIONDENGUE_ConAlarma"></spring:message>
                                            </label>
                                            <label class="radio-inline mr-1">
                                                <c:choose>
                                                    <c:when test="${obj.signo eq 'Grave'}">
                                                        <input type="radio" id="customRadio3" name="signo" class="custom-control-input" value="Grave" checked="checked">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" id="customRadio3" name="signo" class="custom-control-input" value="Grave">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="CLASIFICACIONDENGUE_Grave"></spring:message>
                                            </label>
                                            <label class="radio-inline mr-1">
                                                <c:choose>
                                                    <c:when test="${obj.signo eq 'Sindrome febril sin foco aparente'}">
                                                        <input type="radio" id="customRadio4" name="signo" class="custom-control-input" value="Sindrome febril sin foco aparente" checked="checked">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" id="customRadio4" name="signo" class="custom-control-input" value="Sindrome febril sin foco aparente">
                                                    </c:otherwise>
                                                </c:choose>
                                                <spring:message code="CLASIFICACIONDENGUE_Foco"></spring:message>
                                            </label>
                                            <div id="clasificaerror" class="text-danger"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label for="dx"> <spring:message code="diagnostico"/></label>
                                            <textarea name="dx" class="form-control" id="dx" cols="30" rows="2" placeholder="Ingrese el diagnóstico">${obj.dx}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label for="fecha"><spring:message code="dateAdded" />:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control" data-date-end-date="+0d"value="<fmt:formatDate value="${obj.fecha}" pattern="dd/MM/yyyy"/>" id="fecha" name="fecha" required>
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="hora"><spring:message code="lbl.Hour"/>:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="time" class="form-control focusNext" id="hora" name="hora" value="${obj.hora}" required>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="nivelConciencia">Nivel de Consciencia:</label>
                                        <span class="required text-danger"> * </span>
                                        <select class="form-control" id="nivelConciencia" name="nivelConciencia" required>
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${nivelConciencia}" var="nivel">
                                                <c:choose>
                                                    <c:when test="${nivel.catKey eq obj.nivelConciencia}">
                                                        <option selected value="${nivel.catKey}">${nivel.spanish}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${nivel.catKey}">${nivel.spanish}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="ps">P/S mmHg:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control " id="ps" name="ps" placeholder="Sistólica mmHg" value="${obj.ps}" required min="0">
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="pd">P/D mmHg:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control" id="pd" name="pd" placeholder="Diastólica mmHg" value="${obj.pd}" required min="0">
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="fc">F.C por Minuto:</label>
                                        <input type="text" class="form-control focusNext" id="fc" name="fc" placeholder="F.C por Minuto" value="${obj.fc}" required>
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="fr">F.R por Minuto:</label>
                                        <input type="text" class="form-control focusNext" id="fr" name="fr" placeholder="F.R por Minuto" value="${obj.fr}" required>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label for="tc">T°C:</label>
                                        <input type="text" class="form-control focusNext" id="tc" name="tc" value="${obj.tc}" placeholder="T°C">
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="sa">SA02%:</label>
                                        <input type="text" class="form-control focusNext" id="sa" name="sa" value="${obj.sa}" placeholder="SA02%">
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="extremidades">Extremidades:</label>
                                        <span class="required text-danger"> * </span>
                                        <select name="extremidades" id="extremidades" class="form-control focusNext" required>
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${extremidades}" var="extrem">
                                                <c:choose>
                                                    <c:when test="${extrem.catKey eq obj.extremidades}">
                                                        <option selected value="${extrem.catKey}">${extrem.spanish}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${extrem.catKey}">${extrem.spanish}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="llenadoCapilar">Llenado Capilar (seg):</label>
                                        <span class="required text-danger"> * </span>
                                        <select name="llenadoCapilar" id="llenadoCapilar" class="form-control focusNext" required/>
                                        <option selected value=""><spring:message code="select" />...</option>
                                        <c:forEach items="${llenadoCapilar}" var="llenado">
                                            <c:choose>
                                                <c:when test="${llenado.catKey eq obj.llenadoCapilar}">
                                                    <option selected  value="${llenado.catKey}">${llenado.spanish}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${llenado.catKey}">${llenado.spanish}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-4">
                                        <label for="pulsoCalidad">Pulso (Calidad):</label>
                                        <span class="required text-danger"> * </span>
                                        <select name="pulsoCalidad" id="pulsoCalidad" class="form-control focusNext" required tabindex="13">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${pulsoCalidad}" var="pulso">
                                                <c:choose>
                                                    <c:when test="${pulso.catKey eq obj.pulsoCalidad}">
                                                        <option selected value="${pulso.catKey}">${pulso.spanish}</option>
                                                    </c:when>
                                                </c:choose>
                                                <option value="${pulso.catKey}">${pulso.spanish}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="diuresis">Diuresis/ml/Kg/Hr:</label>
                                        <select name="diuresis" id="diuresis" class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${diuresis}" var="d">
                                                <c:choose>
                                                    <c:when test="${d.catKey eq obj.diuresis}">
                                                        <option selected value="${d.catKey}">${d.spanish}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${d.catKey}">${d.spanish}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="cantidadOrina">Cantidad de Orina:</label>
                                        <input type="text" class="form-control" id="cantidadOrina" name="cantidadOrina" value="${obj.cantidadOrina}" placeholder="Cantidad de Orina" min="0">
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="cargas_iv">Cargas I.V(Ml/Kg/Hr):</label>
                                        <input type="text" class="form-control" id="cargas_iv" name="cargas_iv" value="${obj.cargasIV}" placeholder="Cargas I.V" min="0">
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="sro">S.R.O:</label>
                                        <input type="text" class="form-control" id="sro" name="sro" value="${obj.sro}" placeholder="Suero Oral" min="0">
                                    </div>

                                    <div class="form-group col-sm-2">
                                        <label for="densidadUrinaria">Densidad Urinaria:</label>
                                        <input type="text" class="form-control" id="densidadUrinaria" value="${obj.densidadUrinaria}" name="densidadUrinaria" placeholder="Densidad Urinaria">
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="personaValida">Valorado Por:</label>
                                        <span class="required text-danger"> * </span>
                                        <select name="personaValida" id="personaValida" required class="form-control">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${personaValida}" var="person">
                                                <c:choose>
                                                    <c:when test="${person.idPersona eq obj.personaValida}">
                                                        <option selected value="${person.idPersona}">${person.idPersona} - ${person.nombre}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${person.idPersona}">${person.idPersona} - ${person.nombre}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br/>
                                    <hr/>
                                    <div class="col-sm-4 mt-2">
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary btn-lg btn-block" id="btnGuardar">
                                                <i class="fa fa-save" aria-hidden="true"></i>
                                                <spring:message code="save"/></button>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 mt-2"></div>
                                    <div class="col-sm-4 mt-2">
                                        <div class="form-group">
                                            <a id="volver" href="${fn:escapeXml(listDetailsHemoUrl)}"  data-toggle="tooltip" title="Volver" data-placement="top"
                                               class="btn btn-warning btn-block btn-lg desabilitado">
                                                <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                <spring:message code="cancel" />
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-muted">
                        &nbsp;
                    </div>
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
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js"
            var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}"/></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/libs/moment.js" var="moment"/>
<script type="text/javascript" src="${moment}"></script>
<spring:url value="/resources/js/views/hemodinamica/detalleForm.js" var="detalleFormJS"/>
<script type="text/javascript" src="${detalleFormJS}"></script>
<script type="text/javascript">
    if("${editando}"){
        isReload();
    }
    $(document).ready(function(){
        var parametros = {
            successmessage: "${successMessage}",
            saveDetailUrl: "${saveDetailUrl}",
            listDetailsHemoUrl:"${listDetailsHemoUrl}",
            reloadUrl:"${reloadUrl}"
        }
        saveDetalle.init(parametros);
    });

    function isReload(){
        $( window ).bind("load",function() {
            if( document.getElementById('editando').value =='true' && document.getElementById('numParams').value == '' && document.getElementById('contParams').value == '' ){
                $("#btnGuardar").prop( "disabled", false );
            }else if(document.getElementById('editando').value =='false' && document.getElementById('numParams').value == document.getElementById('contParams').value){
                window.setTimeout(function () {
                    var p = $("#contParams").val();
                    toastr.info("Total de Parámetros: " +p,"Finalizado", {timeOut:6000});
                    $("#btnGuardar").prop( "disabled", true );
                }, 1500);
            }else{}

            if($("#numParams").val() != $("#contParams").val()){
                $('#volver').bind("click", function (e) {
                    e.preventDefault();
                });
            }
        });
    }
</script>
</body>
</html>
