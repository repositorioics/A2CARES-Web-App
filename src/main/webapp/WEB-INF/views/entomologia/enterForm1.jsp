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
    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>
    <script src="https://smtpjs.com/v3/smtp.js"></script>


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
                <a href="<spring:url value="/entomologia/editdata" htmlEscape="true "/>"><spring:message code="editdata" /></a>
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
                              <spring:message code="Update Data Entomologia" /> -
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
                          <spring:url value="/entomologia/editdata/updatePropertie" var="updatePropertie"/>
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
                          <form action="#" autocomplete="off" id="download-form" class="form-horizontal">
                              <div class="row" align="center">

                                  <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                      <label class="form-control-label" align="center" style="color: teal;font-weight: 500;"><spring:message code="TABLAS ENTOMOLOGICAS" />
                                          <span class="required">*</span>
                                      </label>
                                      <div class="col-md-6">
                                          <select class="form-control" id="zpform" name="zpform" onchange="Validar_variables_unicas1();">
                                              <option value=""><spring:message code="seleccionar Tabla" /> ...</option>
                                              <option value="ento_cuestionario_hogar"><spring:message code="ento_cuestionario_hogar" /></option>
                                              <option value="ento_cuestionario_hogar_pob"><spring:message code="ento_cuestionario_hogar_pob" /></option>
                                              <option value="ento_cuestionario_punto_clave"><spring:message code="ento_cuestionario_punto_clave" /></option>

                                          </select>
                                      </div>
                                      <div>
                                          <br>
                                      </div>

                                      <label class="form-control-label" id="varunica" style="color: teal;font-weight: 500;"><spring:message code="VARIABLE UNICA" />

                                      </label>
                                      <div class="col-md-6">
                                          <input  class="form-control" type="text"  placeholder="CODIGO DE REGISTRO A MODIFICAR" id="recordId1" name="recordId1" style="color: teal;font-weight: 500;"/>
                                      </div>

                                      <div>
                                          <br>
                                      </div>
                                      <label class="form-control-label" style="color: teal;font-weight: 500;"><spring:message code="VARIABLES DE TABLA SELECCIONADA" />
                                          <span class="required">*</span>
                                      </label>

                                      <div class="col-md-6">
                                      <select class="form-control focusNext" id="columnas" name="columnas" required onchange="Validar_variables_unicas();" >
                                          <option selected value=""><spring:message code="seleccionar Variable" />...</option>

                                      </select>
                                      </div>


                                      <div class="col-md-6">
                                          <div class="input-group">
                                              <input id="propiedad" name="propiedad" type="text" value="" hidden="true"/>

                                          </div>
                                      </div>
                                      <div>
                                          <br>
                                      </div>
                                      <label class="form-control-label" style="color: teal;font-weight: 500;"><spring:message code="VALOR DE VARIABLE" />
                                          <span class="required">*</span>
                                      </label>
                                      <div class="col-md-6">

                                              <input class="form-control" id="valor" name="valor" type="text" value="" />


                                      </div>
                                      <div id="divvalor">
                                        <br>
                                      </div>

                                      <div class="col-md-4">

                                          <button  id="run" type="submit" class="btn btn-primary btn-block btn-lg"><spring:message code="EJECUTAR EDICION"  />
                                          </button>

                                      </div>




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
<spring:url value="/resources/js/views/entomologia/process-editdata.js" var="procEDScript" />
<script src="${procEDScript}" type="text/javascript"></script>
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>

<spring:url value="/entomologia/editdata/getPropertiesName" var="getPropertiesName"/>
<spring:url value="/entomologia/editdata/updatePropertie" var="updateUrl"/>
<spring:url value="/entomologia/editdata/getValueColumn" var="getValueColumn"/>
<script type="application/javascript">
    $(document).ready(function(){
        //App.init();
        var parametros = {
            "getPropertiesName" : "${getPropertiesName}",
            "updateUrl" : "${updateUrl}",
            "getValueColumn" : "${getValueColumn}"
        };

        EditData.init(parametros);

        $("#divEventosUS").hide();
    });

    function Val_extraer_valor(){
        var bloquearUI = function (mensaje) {
            var loc = window.location;
            var pathName = loc.pathname.substring(0, loc.pathname.indexOf('/', 1) + 1);
            var mess = '<img src=' + pathName + 'resources/img/ajax-loading.gif>' + mensaje;
            $.blockUI({ message: mess,
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .5,
                    color: '#fff'
                },
                baseZ: 1051 // para que se muestre bien en los modales
            });
        };

        var desbloquearUI = function () {
            setTimeout($.unblockUI, 500);
        };
        var parametros = {
            "getPropertiesName" : "${getPropertiesName}",
            "updateUrl" : "${updateUrl}",
            "getValueColumn" : "${getValueColumn}"
        };

            $.getJSON(parametros.getValueColumn, {
                ajax: 'true',
                tabla: $('#zpform').find('option:selected').val(),
                id: $('#recordId1').val(),
                propiedad: $('#propiedad').val()

        } ,    function (dataToLoad) {
                    //  alert(data);
                        $("#valor").val(dataToLoad);



                        desbloquearUI();

        }

    ).
        fail(function (jqXHR) {
         //   desbloquearUI();
        });



    };

    function Validar_variables_unicas1(){


        if ($("#zpform").val() === "ento_cuestionario_hogar" || $("#zpform").val() === "ento_cuestionario_hogar_pob")
        {
            $("#varunica").text("CODIGO VIVIENDA");
        }
        if ($("#zpform").val() === "ento_cuestionario_punto_clave")
        {
            $("#varunica").text("CODIGO SITIO");
        }


    };
    function Validar_variables_unicas(){

        if ($("#columnas").val() === "codigo_cuestionario" || $("#columnas").val() === "codigo_vivienda"|| $("#columnas").val() === "codigo_sitio" )
        {
            Swal.fire({
                title: 'Alerta A2CARES-ENTOMOLOGIA!',
                text: 'La variable '+ '  [' +$("#columnas").val()+ '] es UNICA no puede modificarse, seleccione otra variable.',
                icon: 'warning',
                confirmButtonText: 'Aceptar'
            });

        }else
        {
            $("#propiedad").val($("#columnas").val());

            if ($("#recordId1").val().length > 0) {
                Val_extraer_valor();
            }

        }
        if ($("#zpform").val() === "ento_cuestionario_hogar" || $("#zpform").val() === "ento_cuestionario_hogar_pob")
        {
            $("#varunica").text("CODIGO VIVIENDA");
        }
        if ($("#zpform").val() === "ento_cuestionario_punto_clave")
        {
            $("#varunica").text("CODIGO SITIO");
        }


    };
</script>

</body>
</html>
