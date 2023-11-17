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
    <!-- DATE PICKER
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    END DATE PICKER -->
    <!-- DATETIME PICKER -->
    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>

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
        <i class="fa fa-angle-right"></i> <a href="<spring:url value="/hojaclinicaDD/" htmlEscape="true "/>"><spring:message code="Hola Clínica Doble Digitación" /></a> <i class="fa fa-angle-right"></i> <a href="<spring:url value="/hojaclinicaDD/add" htmlEscape="true "/>"><spring:message code="add" /></a>
    </li>
</ol>
<div class="container-fluid">
<div class="card">
<div class="card-header">
    <h3 class="page-title">
        <spring:message code="Hola Clínica Doble Digitación" /> <small><spring:message code="" /></small>
    </h3>
</div>
<div class="card-block">
        <div class="row">
            <div class="col-md-12">
                <div class="btn-group">
                    <spring:url value="/hojaclinicaDD/" var="buscarHojas"/>
                    <button id="backClinicalSheet" onclick="location.href='${fn:escapeXml(buscarHojas)}'" class="btn btn-block btn-lg btn-primary">
                        <i class="fa fa-arrow-circle-left"></i>
                        <spring:message code="back" />
                    </button>
                </div>
                <br>
                <br>
            </div>
        </div>

        <form action="#" autocomplete="off" id="search-participant-form" name="search-participant-form" class="form-horizontal">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <br>
                <div class="form-group row">
                    <label class="form-control-label col-lg-2 col-md-12 col-sm-12" for="participantCode"><spring:message code="search_participant" />
                        <span class="required">*</span>
                    </label>
                    <div class="input-group col-lg-4 col-md-12 col-sm-12">
                            <span class="input-group-addon">
                                <i class="fa fa-user"></i>
                            </span>
                        <input id="participantCode" name="participantCode" type="text" value="" class="form-control" placeholder="<spring:message code="search_participant_holder" />"/>
                    </div>
                </div>
            </div>

        </div>

        </form>


</div>
<hr/>
<div class="card-block">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <h4 class="text-capitalize"><spring:message code="datos_personales" /></h4>
            <input id="codigoPart" type="hidden" value=""/>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="row">
                <div class="col-lg-5 col-md-12 col-sm-12">
                    <div class="form-group">
                        <label for="nombre" class="form-control-label"><spring:message code="nombre" /></label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                               value=""
                               readonly />
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="fechanac"><spring:message code="fecha_nacimiento" /></label>
                        <input type="text" class="form-control" id="fechanac" name="fechanac"
                               value="" readonly />
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="edadPart"><spring:message code="edad" /></label>
                        <input type="text" class="form-control" id="edadPart" name="edadPart" value="" readonly />
                    </div>

                </div>
                <div class="col-lg-1 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="sexoPart"><spring:message code="sexo" /></label>
                        <input type="text" class="form-control" id="sexoPart" required name="sexoPart" value="" readonly />
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
<form action="#" autocomplete="off" id="search-cod-form" name="search-participant-form" class="form-horizontal">
    <div class="row">
        <div class="col-lg-3 col-md-6 col-sm-12">
            <div class="form-group">
                &ensp;
                &ensp;
                <label class="form-control-label" for="codigoSupervisor"><spring:message code="Código Supervisor" />
                    <span class="required">*</span>
                </label>
                <div class="input-group"  align="center">
                    &ensp;
                    &ensp;
                  <span class="input-group-addon" >
                       <i class="fa fa-book-medical"></i>
                  </span>

                    <input type="text" id="codigoSupervisor" required  name="codigoSupervisor" value=""   />
                </div>
            </div>
        </div>
    </div>
</form>
<form name="form-clinicalsheet" autocomplete="off" role="form" action="#" id="form-clinicalsheet" method="post" class="form-horizontal">

<div class="card-block">
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize"><spring:message code="encabezado" /></h4>
    </div>
    <div class="col-lg-3 col-md-6 col-sm-12">
        <div class="form-group">
            <label class="form-control-label" for="fechaCons"><spring:message code="fecha_consulta" />
                <span class="required">*</span>
            </label>
            <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar-alt"></i>
            </span>
                <input type="text" class="form-control date-picker" id="fechaCons" name="fechaCons" data-date-end-date="+0d"
                       value="" />
            </div>
        </div>
    </div>
    <div class="col-lg-3 col-md-6 col-sm-12">
        <div class="form-group">
            <label class="form-control-label" for="horaCons"><spring:message code="hora_consulta" />
                <span class="required">*</span>
            </label>
            <div class="input-group">
            <span class="input-group-addon">
                <i class="fa fa-clock"></i>
            </span>
                <input type="text" class="form-control time-picker" id="horaCons" required name="horaCons" value="" />
            </div>
        </div>

    </div>

    <div class="col-lg-3 col-md-6 col-sm-12">
        <div class="form-group">
            <label class="form-control-label" for="numHoja"><spring:message code="Numero Hoja" />
                <span class="required">*</span>
            </label>
            <div class="input-group">
            <span class="input-group-addon">
                <i class="fa fa-pen"></i>
            </span>
                <input type="text" id="numHoja" required name="numHoja" value="" />
                <input type="text" id="codigoSuper"   name="codigoSuper" value="" hidden />
            </div>
        </div>
    </div>



</div>
<div class="col-md-12">
    <a id="desactivarEnfermeria"  onclick="desactivar_Enfermeria();" style="font-size: 30px; color:#FF0000">Desactivar Datos de Enfermería</a>
    <a id="activarEnfermeria"  onclick="activar_Enfermeria();" style="font-size: 30px; color:#FF0000">Activar Datos de Enfermería</a>

</div>
<div class="row" id="datos_enfermeria_div"  >
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize"><spring:message code="datos_enfermeria" /></h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                      <label class="form-control-label" for="peso" class="form-control-label"><spring:message code="peso" />
                  <!--   <span class="required">*</span> -->
                    </label>
                    <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fas fa-weight"></i>
                    </span>
                        <input type="text" class="form-control"  id="peso" name="peso"
                               value=""/>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="talla"><spring:message code="talla" />
                        <!--   <span class="required">*</span> -->
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-sort-numeric-up"></i>
            </span>
                        <input type="text" class="form-control"  id="talla" name="talla"
                               value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="edad"><spring:message code="edad" />
                        <!--   <span class="required">*</span> -->
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-pen"></i>
            </span>
                        <input type="text" class="form-control" id="edad"  name="edad" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="sexo"><spring:message code="sexo" />
                        <!--   <span class="required">*</span> -->
                    </label>
                    <select class="form-control focusNext" id="sexo" name="sexo" >
                        <option selected value=""><spring:message code="select" />...</option>
                        <c:forEach var="item" items="${catSexo}">
                            <option value="${item.catKey}">${item.spanish}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="pa" class="form-control-label"><spring:message code="presion_arterial" />
                        <!--   <span class="required">*</span> -->
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-heart"></i>
            </span>
                        <input type="text" class="form-control"  id="pa" name="pa"
                               value=""/>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="fc"><spring:message code="frecuencia_cardiaca" />
                        <!--   <span class="required">*</span> -->
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-heartbeat"></i>
            </span>
                        <input type="text" class="form-control"  id="fc" name="fc"
                               value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="temp"><spring:message code="temperatura" />
                        <!--   <span class="required">*</span> -->
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-thermometer-half"></i>
            </span>
                        <input type="text" class="form-control" id="temp"  name="temp" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="so"><spring:message code="saturacion_oxigeno" />
                        <!--   <span class="required">*</span> -->
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-lungs"></i>
            </span>
                        <input type="text" class="form-control" id="so" required name="so" value="" />
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- Fin seccion de enfermeria -->

<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize"><spring:message code="datos_medico" /></h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="horaIniCons"><spring:message code="hora_inicio_consulta" />
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                        <input type="text" class="form-control time-picker" required id="horaIniCons" name="horaIniCons"
                               value="" />
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="tipoConsulta"><spring:message code="consulta" />
                        <span class="required">*</span>
                    </label>
                    <select class="form-control focusNext" id="tipoConsulta" name="tipoConsulta" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                        <c:forEach var="item" items="${catTipoConsulta}">
                            <option value="${item.catKey}">${item.spanish}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="lugarConsulta"><spring:message code="lugar_consulta" />
                        <span class="required">*</span>
                    </label>
                    <select class="form-control focusNext" id="lugarConsulta" name="lugarConsulta" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                        <c:forEach var="item" items="${catLugarConsulta}">
                            <option value="${item.catKey}">${item.spanish}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize"><spring:message code="signos_vitales" /></h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="paMedico"><spring:message code="presion_arterial" />
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-heart"></i>
            </span>
                        <input type="text" class="form-control" required id="paMedico" name="paMedico"
                               value="" />
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="tempMedico"><spring:message code="temperatura" />
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-thermometer-half"></i>
            </span>
                        <input type="text" class="form-control" id="tempMedico" required name="tempMedico" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="frMedico"><spring:message code="frecuencia_respiratoria" />
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-wave-square"></i>
            </span>
                        <input type="text" class="form-control" id="frMedico" required name="frMedico" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="fcMedico"><spring:message code="frecuencia_cardiaca" />
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-heartbeat"></i>
            </span>
                        <input type="text" class="form-control" id="fcMedico" required name="fcMedico" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="soMedico"><spring:message code="saturacion_oxigeno" />
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-lungs"></i>
            </span>
                        <input type="text" class="form-control" id="soMedico" required name="soMedico" value="" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="fis"><spring:message code="fecha_inicio_sintomas" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                        <input type="text" class="form-control date-picker" id="fis" name="fis"
                               value="" />
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="fif"><spring:message code="fecha_inicio_fiebre" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                        <input type="text" class="form-control date-picker" id="fif" name="fif" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="ultimoDiaFiebre"><spring:message code="ultimo_dia_fiebre" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                        <input type="text" class="form-control date-picker" id="ultimoDiaFiebre" name="ultimoDiaFiebre" value="" onclick="fech_ultimo_dia_fiebre();" />
                    </div>
                </div>

            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="horaUltimoDiaF"><spring:message code="hora" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                        <input type="text" class="form-control time-picker" id="horaUltimoDiaF" name="horaUltimoDiaF" value="" onclick="fech_ultimo_dia_fiebre();" />
                    </div>
                </div>

            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="ultimaDosisAntip"><spring:message code="ultima_dosis_antiperetico" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                        <input type="text" class="form-control date-picker" id="ultimaDosisAntip" name="ultimaDosisAntip" value="" onclick="fech_ultimo_dia_anti();"/>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="horaUltimaDosisAntip"><spring:message code="hora" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                        <input type="text" class="form-control time-picker" id="horaUltimaDosisAntip" name="horaUltimaDosisAntip" value="" onclick="fech_ultimo_dia_anti();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--- tablas de sintomas --->
<!--- general y gastro instestinal --->
<div class="row">
<div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                <label class="label" style="color: white"><strong><spring:message code="general" /></strong></label>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_S" name="rbgeneral" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgeneral_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_N" name="rbgeneral" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgeneral_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_D" name="rbgeneral" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgeneral_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="fiebre" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_1S" name="rbgeneral_1" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgeneral_1S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_1N" name="rbgeneral_1" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgeneral_1N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_1D" name="rbgeneral_1" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgeneral_1D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="anormalmente_somnoliento" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_2S" name="rbgeneral_2" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgeneral_2S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_2N" name="rbgeneral_2" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgeneral_2N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_2D" name="rbgeneral_2" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgeneral_2D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="mal_estado_general" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_3S" name="rbgeneral_3" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgeneral_3S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_3N" name="rbgeneral_3" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgeneral_3N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_3D" name="rbgeneral_3" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgeneral_3D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="perdida_consciencia" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_4S" name="rbgeneral_4" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgeneral_4S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_4N" name="rbgeneral_4" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgeneral_4N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_4D" name="rbgeneral_4" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgeneral_4D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="inquieto_irritable" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_5S" name="rbgeneral_5" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgeneral_5S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_5N" name="rbgeneral_5" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgeneral_5N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_5D" name="rbgeneral_5" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgeneral_5D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="convulsiones" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_6S" name="rbgeneral_6" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgeneral_6S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_6N" name="rbgeneral_6" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgeneral_6N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_6D" name="rbgeneral_6" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgeneral_6D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="letargia" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_7S" name="rbgeneral_7" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgeneral_7S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_7N" name="rbgeneral_7" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgeneral_7N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgeneral_7D" name="rbgeneral_7" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgeneral_7D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>

    </div>
</div>
<div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                <label class="label" style="color: white" ><strong><spring:message code="gastrointestinal" /></strong></label>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-5" style="align-content: center; background-color: lightgray">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_S" name="rbgastroin" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgastroin_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_N" name="rbgastroin" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgastroin_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_D" name="rbgastroin" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgastroin_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="poco_apetito" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_1S" name="rbgastroin_1" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgastroin_1S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_1N" name="rbgastroin_1" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgastroin_1N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_1D" name="rbgastroin_1" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgastroin_1D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="nauseas" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_2S" name="rbgastroin_2" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgastroin_2S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_2N" name="rbgastroin_2" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgastroin_2N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_2D" name="rbgastroin_2" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgastroin_2D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="vomito" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_3S" name="rbgastroin_3" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgastroin_3S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_3N" name="rbgastroin_3" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgastroin_3N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_3D" name="rbgastroin_3" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgastroin_3D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-10 col-md-12 col-sm-12">
                <div class="form-group row">
                    <label class="form-control-label col-lg-6 col-md-6 col-sm-12" for="numVomito"><spring:message code="vomitos_ultimas_12_hrs" />
                    </label>
                    <div class="input-group col-lg-6 col-md-6 col-sm-12">
                <span class="input-group-addon">
                    <i class="fas fa-hashtag"></i>
                </span>
                        <input id="numVomito" name="numVomito" type="text" value="" class="form-control"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="diarrea" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_4S" name="rbgastroin_4" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgastroin_4S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_4N" name="rbgastroin_4" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgastroin_4N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_4D" name="rbgastroin_4" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgastroin_4D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="dolor_abdominal" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_5S" name="rbgastroin_5" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgastroin_5S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_5N" name="rbgastroin_5" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgastroin_5N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_5D" name="rbgastroin_5" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgastroin_5D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="hepatomegalia" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_6S" name="rbgastroin_6" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgastroin_6S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_6N" name="rbgastroin_6" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgastroin_6N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgastroin_6D" name="rbgastroin_6" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgastroin_6D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--- FIN general y gastro instestinal --->
<!--- cabeza y osteomuscular --->
<div class="row">
<div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                <label class="label" style="color: white"><strong><spring:message code="cabeza" /></strong></label>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_S" name="rbcabeza" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbcabeza_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_N" name="rbcabeza" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbcabeza_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_D" name="rbcabeza" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbcabeza_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="dolor_cabeza" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_1S" name="rbcabeza_1" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbcabeza_1S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_1N" name="rbcabeza_1" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbcabeza_1N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_1D" name="rbcabeza_1" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbcabeza_1D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="conjuntivitis" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_2S" name="rbcabeza_2" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbcabeza_2S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_2N" name="rbcabeza_2" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbcabeza_2N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_2D" name="rbcabeza_2" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbcabeza_2D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="hemorragia_subconjuntival" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_3S" name="rbcabeza_3" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbcabeza_3S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_3N" name="rbcabeza_3" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbcabeza_3N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_3D" name="rbcabeza_3" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbcabeza_3D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="dolor_retroocular" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_4S" name="rbcabeza_4" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbcabeza_4S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_4N" name="rbcabeza_4" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbcabeza_4N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbcabeza_4D" name="rbcabeza_4" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbcabeza_4D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                <label class="label" style="color: white"><strong><spring:message code="osteomuscular" />Osteomuscular</strong></label>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_S" name="rbosteomusc" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbosteomusc_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_N" name="rbosteomusc" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbosteomusc_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_D" name="rbosteomusc" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbosteomusc_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="artralgia" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_1S" name="rbosteomusc_1" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbosteomusc_1S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_1N" name="rbosteomusc_1" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbosteomusc_1N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_1D" name="rbosteomusc_1" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbosteomusc_1D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="mialgia" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_2S" name="rbosteomusc_2" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbosteomusc_2S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_2N" name="rbosteomusc_2" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbosteomusc_2N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_2D" name="rbosteomusc_2" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbosteomusc_2D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="lumbalgia" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_3S" name="rbosteomusc_3" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbosteomusc_3S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_3N" name="rbosteomusc_3" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbosteomusc_3N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_3D" name="rbosteomusc_3" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbosteomusc_3D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="dolor_cuello" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_4S" name="rbosteomusc_4" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbosteomusc_4S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_4N" name="rbosteomusc_4" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbosteomusc_4N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_4D" name="rbosteomusc_4" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbosteomusc_4D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="edema" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_5S" name="rbosteomusc_5" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbosteomusc_5S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_5N" name="rbosteomusc_5" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbosteomusc_5N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbosteomusc_5D" name="rbosteomusc_5" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbosteomusc_5D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--- FIN cabeza y osteomuscular --->
<!--- garganta y cutaneo --->
<div class="row">
<div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                <label class="label" style="color: white"><strong><spring:message code="garganta" /></strong></label>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_S" name="rbgarganta" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgarganta_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_N" name="rbgarganta" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgarganta_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_D" name="rbgarganta" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgarganta_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="dolor_garganta" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_1S" name="rbgarganta_1" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgarganta_1S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_1N" name="rbgarganta_1" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgarganta_1N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_1D" name="rbgarganta_1" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgarganta_1D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="eritema" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_2S" name="rbgarganta_2" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgarganta_2S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_2N" name="rbgarganta_2" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgarganta_2N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_2D" name="rbgarganta_2" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgarganta_2D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="adenopatias_cervicales" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_3S" name="rbgarganta_3" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgarganta_3S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_3N" name="rbgarganta_3" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgarganta_3N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_3D" name="rbgarganta_3" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgarganta_3D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="exudado" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_4S" name="rbgarganta_4" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgarganta_4S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_4N" name="rbgarganta_4" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgarganta_4N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_4D" name="rbgarganta_4" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgarganta_4D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="petequias_mucosa" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_5S" name="rbgarganta_5" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbgarganta_5S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_5N" name="rbgarganta_5" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbgarganta_5N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbgarganta_5D" name="rbgarganta_5" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbgarganta_5D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-6 col-md-12 col-sm-12">
<div class="card-block">
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
        <label class="label" style="color: white"><strong><spring:message code="cutaneo" /></strong></label>
    </div>
    <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_S" name="rbcutaneo" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_N" name="rbcutaneo" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_D" name="rbcutaneo" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="rash_localizado" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_1S" name="rbcutaneo_1" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_1S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_1N" name="rbcutaneo_1" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_1N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_1D" name="rbcutaneo_1" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_1D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="rash_generalizado" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_2S" name="rbcutaneo_2" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_2S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_2N" name="rbcutaneo_2" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_2N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_2D" name="rbcutaneo_2" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_2D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="rash_eritematoso" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_3S" name="rbcutaneo_3" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_3S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_3N" name="rbcutaneo_3" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_3N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_3D" name="rbcutaneo_3" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_3D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="rash_macular" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_4S" name="rbcutaneo_4" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_4S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_4N" name="rbcutaneo_4" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_4N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_4D" name="rbcutaneo_4" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_4D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="rash_papular" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_5S" name="rbcutaneo_5" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_5S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_5N" name="rbcutaneo_5" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_5N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_5D" name="rbcutaneo_5" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_5D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="piel_moteada" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_6S" name="rbcutaneo_6" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_6S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_6N" name="rbcutaneo_6" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_6N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_6D" name="rbcutaneo_6" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_6D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="rubor_facial" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_7S" name="rbcutaneo_7" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_7S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_7N" name="rbcutaneo_7" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_7N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_7D" name="rbcutaneo_7" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_7D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="cianosis_central" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_8S" name="rbcutaneo_8" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_8S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_8N" name="rbcutaneo_8" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_8N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_8D" name="rbcutaneo_8" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_8D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="ictericia" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_9S" name="rbcutaneo_9" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbcutaneo_9S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_9N" name="rbcutaneo_9" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbcutaneo_9N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbcutaneo_9D" name="rbcutaneo_9" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbcutaneo_9D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
</div>
</div>
</div>
<!--- FIN garganta y cutaneo --->
<!--- respiratorio y estado nutricional --->
<div class="row">
<div class="col-lg-6 col-md-12 col-sm-12">
<div class="card-block">
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
        <label class="label" style="color: white"><strong><spring:message code="respiratorio" /></strong></label>
    </div>
    <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_S" name="rbrespiratorio" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_N" name="rbrespiratorio" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_D" name="rbrespiratorio" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="tos" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_1S" name="rbrespiratorio_1" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_1S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_1N" name="rbrespiratorio_1" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_1N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_1D" name="rbrespiratorio_1" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_1D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="rinorrea" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_2S" name="rbrespiratorio_2" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_2S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_2N" name="rbrespiratorio_2" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_2N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_2D" name="rbrespiratorio_2" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_2D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="congestion_nasal" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_3S" name="rbrespiratorio_3" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_3S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_3N" name="rbrespiratorio_3" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_3N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_3D" name="rbrespiratorio_3" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_3D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="otalgia" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_4S" name="rbrespiratorio_4" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_4S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_4N" name="rbrespiratorio_4" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_4N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_4D" name="rbrespiratorio_4" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_4D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="aleteo_nasal" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_5S" name="rbrespiratorio_5" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_5S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_5N" name="rbrespiratorio_5" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_5N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_5D" name="rbrespiratorio_5" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_5D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="respiracion_rapida" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_6S" name="rbrespiratorio_6" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_6S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_6N" name="rbrespiratorio_6" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_6N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_6D" name="rbrespiratorio_6" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_6D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="estridor_reposo" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_7S" name="rbrespiratorio_7" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_7S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_7N" name="rbrespiratorio_7" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_7N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_7D" name="rbrespiratorio_7" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_7D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="tirajes_subcostales" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_8S" name="rbrespiratorio_8" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_8S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_8N" name="rbrespiratorio_8" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_8N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_8D" name="rbrespiratorio_8" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_8D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="sibilancia" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_9S" name="rbrespiratorio_9" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_9S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_9N" name="rbrespiratorio_9" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_9N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_9D" name="rbrespiratorio_9" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_9D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="crepitos" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_10S" name="rbrespiratorio_10" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_10S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_10N" name="rbrespiratorio_10" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_10N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_10D" name="rbrespiratorio_10" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_10D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="roncos" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_11S" name="rbrespiratorio_11" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_11S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_11N" name="rbrespiratorio_11" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_11N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_11D" name="rbrespiratorio_11" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_11D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <label class="form-control-label"><spring:message code="disnea" />
            <span class="required">*</span>
        </label>
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_12S" name="rbrespiratorio_12" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbrespiratorio_12S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_12N" name="rbrespiratorio_12" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbrespiratorio_12N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbrespiratorio_12D" name="rbrespiratorio_12" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbrespiratorio_12D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
</div>
</div>
<div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                <label class="label" style="color: white"><strong><spring:message code="estado_nutricional" /></strong></label>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_S" name="rbestadonut" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbestadonut_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_N" name="rbestadonut" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbestadonut_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_D" name="rbestadonut" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbestadonut_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-12">
                <br>
                <div class="form-group row">
                    <label class="form-control-label col-lg-6 col-md-6 col-sm-12" for="imc"><spring:message code="imc" />
                        <span class="required">*</span>
                    </label>
                    <div class="input-group col-lg-6 col-md-6 col-sm-12">
                <span class="input-group-addon">
                    <i class="fas fa-hashtag"></i>
                </span>
                        <input id="imc" name="imc" type="text" value="" required="required" class="form-control"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="obeso" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_1S" name="rbestadonut_1" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbestadonut_1S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_1N" name="rbestadonut_1" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbestadonut_1N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_1D" name="rbestadonut_1" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbestadonut_1D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="sobrepeso" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_2S" name="rbestadonut_2" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbestadonut_2S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_2N" name="rbestadonut_2" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbestadonut_2N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_2D" name="rbestadonut_2" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbestadonut_2D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="sospecha_problema" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_3S" name="rbestadonut_3" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbestadonut_3S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_3N" name="rbestadonut_3" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbestadonut_3N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_3D" name="rbestadonut_3" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbestadonut_3D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="normal" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_4S" name="rbestadonut_4" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbestadonut_4S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_4N" name="rbestadonut_4" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbestadonut_4N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_4D" name="rbestadonut_4" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbestadonut_4D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="bajo_peso" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_5S" name="rbestadonut_5" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbestadonut_5S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_5N" name="rbestadonut_5" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbestadonut_5N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_5D" name="rbestadonut_5" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbestadonut_5D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <label class="form-control-label"><spring:message code="bajo_peso_severo" />
                    <span class="required">*</span>
                </label>
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_6S" name="rbestadonut_6" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbestadonut_6S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_6N" name="rbestadonut_6" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbestadonut_6N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbestadonut_6D" name="rbestadonut_6" class="custom-control-input" value="D">
                    <label class="custom-control-label" for="rbestadonut_6D"><spring:message code="CAT_SND_HC_999" /></label>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--- FINrespiratorio y estado nutricional --->
<!--- FIN tablas de sintomas --->
<!--- CATEGORIA--->
<div class="card-block">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize"><spring:message code="categoria" /></h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-2 col-md-6 col-sm-12">
                <div class="form-group">
                    <label for="categoria" class="form-control-label"><spring:message code="categoria" />:
                        <span class="required">*</span>
                    </label>
                    <select class="form-control focusNext" id="categoria" name="categoria" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                        <c:forEach var="item" items="${catCategoria}">
                            <option value="${item.catKey}">${item.spanish}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-lg-2 col-md-6 col-sm-12">
                <div class="form-group">
                    <label for="cambioCategoria" class="form-control-label"><spring:message code="cambio_categoria" /></label>
                    <select class="form-control focusNext" id="cambioCategoria" name="cambioCategoria" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                        <c:forEach var="item" items="${catSiNo}">
                            <option value="${item.catKey}">${item.catKey} - ${item.spanish}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
</div>
<!--- FIN CATEGORIA --->
<!--- MANIFESTACIONES HEMORRAGICAS CATEGORIA A Y B--->
<div class="row">
<div class="col-lg-6 col-md-12 col-sm-12">
<div class="card-block">
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
        <label class="label" style="color: white"><strong><spring:message code="manifestaciones_hemorragicas" /></strong></label>
    </div>
    <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_S" name="rbmanhemo" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_N" name="rbmanhemo" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_D" name="rbmanhemo" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_D"><strong><spring:message code="CAT_SND_HC_999" /></strong></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="prueba_torniquete" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_1S" name="rbmanhemo_1" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_1S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_1N" name="rbmanhemo_1" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_1N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_1D" name="rbmanhemo_1" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_1D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="petequias_10" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_2S" name="rbmanhemo_2" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_2S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_2N" name="rbmanhemo_2" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_2N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_2D" name="rbmanhemo_2" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_2D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="petequias_20" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_3S" name="rbmanhemo_3" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_3S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_3N" name="rbmanhemo_3" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_3N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_3D" name="rbmanhemo_3" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_3D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="piel_extremidades_frias" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_4S" name="rbmanhemo_4" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_4S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_4N" name="rbmanhemo_4" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_4N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_4D" name="rbmanhemo_4" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_4D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="palidez_extremidades" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_5S" name="rbmanhemo_5" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_5S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_5N" name="rbmanhemo_5" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_5N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_5D" name="rbmanhemo_5" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_5D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="epistaxis" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_6S" name="rbmanhemo_6" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_6S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_6N" name="rbmanhemo_6" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_6N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_6D" name="rbmanhemo_6" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_6D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="gingivorragia" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_7S" name="rbmanhemo_7" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_7S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_7N" name="rbmanhemo_7" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_7N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_7D" name="rbmanhemo_7" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_7D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="petequias_espontaneas" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_8S" name="rbmanhemo_8" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_8S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_8N" name="rbmanhemo_8" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_8N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_8D" name="rbmanhemo_8" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_8D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="llenado_capilar_segundos" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_9S" name="rbmanhemo_9" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_9S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_9N" name="rbmanhemo_9" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_9N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_9D" name="rbmanhemo_9" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_9D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="cianosis" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_10S" name="rbmanhemo_10" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_10S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_10N" name="rbmanhemo_10" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_10N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_10D" name="rbmanhemo_10" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_10D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="hipermenorrea" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_11S" name="rbmanhemo_11" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_11S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_11N" name="rbmanhemo_11" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_11N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_11D" name="rbmanhemo_11" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_11D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="hematemesis" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_12S" name="rbmanhemo_12" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_12S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_12N" name="rbmanhemo_12" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_12N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_12D" name="rbmanhemo_12" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_12D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        <spring:message code="hemoconcentracion" />
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_13S" name="rbmanhemo_13" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_13S"><spring:message code="CAT_SND_HC_01" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_13N" name="rbmanhemo_13" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_13N"><spring:message code="CAT_SND_HC_02" /></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_13D" name="rbmanhemo_13" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_13D"><spring:message code="CAT_SND_HC_999" /></label>
        </div>
    </div>
</div>
</div>
</div>
</div>
<!--- FIN MANIFESTACIONES HEMORRAGICAS CATEGORIA A Y B--->

<div class="card-block">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize"><spring:message code="preguntas_todos" /></h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="form-group">
                    <label for="hospitalizado" class="form-control-label"><spring:message code="hospitalizado_ultimo_anio" /><span class="required">*</span></label>
                    <select class="form-control focusNext" id="hospitalizado" name="hospitalizado" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                        <c:forEach var="item" items="${catSiNo}">
                            <option value="${item.catKey}">${item.catKey} - ${item.spanish}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-lg-8 col-md-12 col-sm-12">
                <div class="form-group">
                    <label for="unidadSaludHosp" class="form-control-label"><spring:message code="especifique_unidad_salud_hosp" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-hospital"></i>
            </span>
                        <input type="text" class="form-control" id="unidadSaludHosp" name="unidadSaludHosp"
                               value="" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="form-group">
                    <label for="transfusion" class="form-control-label"><spring:message code="transfusion_sangre_ultimo_anio" /><span class="required">*</span></label>
                    <select class="form-control focusNext" id="transfusion" name="transfusion" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                        <c:forEach var="item" items="${catSiNo}">
                            <option value="${item.catKey}">${item.catKey} - ${item.spanish}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-lg-8 col-md-12 col-sm-12">
                <div class="form-group">
                    <label for="transfusionEsp" class="form-control-label"><spring:message code="si_especifique" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-syringe"></i>
            </span>
                        <input type="text" class="form-control" id="transfusionEsp" name="transfusionEsp"
                               value="" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="form-group">
                    <label for="tomaMedicamento" class="form-control-label"><spring:message code="medicamento_momento" /><span class="required">*</span></label>
                    <select class="form-control focusNext" id="tomaMedicamento" name="tomaMedicamento" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                        <c:forEach var="item" items="${catSiNo}">
                            <option value="${item.catKey}">${item.catKey} - ${item.spanish}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-lg-8 col-md-12 col-sm-12">
                <div class="form-group">
                    <label for="cualMedicamento" class="form-control-label"><spring:message code="especifique_medicamento" /></label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-prescription-bottle"></i>
            </span>
                        <input type="text" class="form-control" id="cualMedicamento" name="cualMedicamento"
                               value="" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- EXAMENES Y TRATAMIENTO-->
<div class="row">
<div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                <label class="label" style="color: white"><strong><spring:message code="examenes_laboratorio" /></strong></label>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_S" name="rbexamen" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbexamen_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_N" name="rbexamen" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbexamen_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="bhc" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_1S" name="rbexamen_1" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbexamen_1S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_1N" name="rbexamen_1" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbexamen_1N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="serologia_arbovirus" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_2S" name="rbexamen_2" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbexamen_2S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_2N" name="rbexamen_2" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbexamen_2N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="gota_gruesa" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_3S" name="rbexamen_3" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbexamen_3S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_3N" name="rbexamen_3" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbexamen_3N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="ego" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_4S" name="rbexamen_4" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbexamen_4S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_4N" name="rbexamen_4" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbexamen_4N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="egh" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_5S" name="rbexamen_5" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbexamen_5S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_5N" name="rbexamen_5" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbexamen_5N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="otros" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_6S" name="rbexamen_6" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbexamen_6S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbexamen_6N" name="rbexamen_6" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbexamen_6N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="form-group row">
                    <label class="form-control-label col-lg-3 col-md-3 col-sm-12" for="descOtroExamen"><spring:message code="especifique" />
                    </label>
                    <div class="input-group col-lg-9 col-md-9 col-sm-12">
                <span class="input-group-addon">
                    <i class="fas fa-pen"></i>
                </span>
                        <input id="descOtroExamen" name="descOtroExamen" type="text" value="" class="form-control"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                <label class="label" style="color: white" ><strong><spring:message code="tratamiento" /></strong></label>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-5" style="align-content: center; background-color: lightgray">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_S" name="rbtratamiento" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_S"><strong><spring:message code="CAT_SND_HC_01" /></strong></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_N" name="rbtratamiento" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_N"><strong><spring:message code="CAT_SND_HC_02" /></strong></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="acetaminofen" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_1S" name="rbtratamiento_1" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_1S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_1N" name="rbtratamiento_1" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_1N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="amoxicilina" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_2S" name="rbtratamiento_2" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_2S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_2N" name="rbtratamiento_2" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_2N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="dicloxacilina" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_3S" name="rbtratamiento_3" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_3S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_3N" name="rbtratamiento_3" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_3N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="penicilina" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_4S" name="rbtratamiento_4" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_4S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_4N" name="rbtratamiento_4" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_4N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="furazolidona" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_5S" name="rbtratamiento_5" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_5S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_5N" name="rbtratamiento_5" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_5N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="metronidazol_tinidazol" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_6S" name="rbtratamiento_6" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_6S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_6N" name="rbtratamiento_6" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_6N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="albendazol_mebendazol" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_7S" name="rbtratamiento_7" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_7S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_7N" name="rbtratamiento_7" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_7N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="suero_oral" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_8S" name="rbtratamiento_8" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_8S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_8N" name="rbtratamiento_8" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_8N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-7">
                <spring:message code="otro_tratamiento" />
            </div>
            <div class="form-group col-lg-4 col-md-4 col-sm-5">
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_9S" name="rbtratamiento_9" class="custom-control-input" value="S">
                    <label class="custom-control-label" for="rbtratamiento_9S"><spring:message code="CAT_SND_HC_01" /></label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="rbtratamiento_9N" name="rbtratamiento_9" class="custom-control-input" value="N">
                    <label class="custom-control-label" for="rbtratamiento_9N"><spring:message code="CAT_SND_HC_02" /></label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="form-group row">
                    <label class="form-control-label col-lg-3 col-md-3 col-sm-12" for="descOtroTratamiento"><spring:message code="especifique" />
                    </label>
                    <div class="input-group col-lg-9 col-md-9 col-sm-12">
                <span class="input-group-addon">
                    <i class="fas fa-pen"></i>
                </span>
                        <input id="descOtroTratamiento" name="descOtroTratamiento" type="text" value="" class="form-control"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- PLANES e HISTORIA CLINICA-->
<div class="card-block">
    <div class="row">
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="form-control-label" for="planes"><spring:message code="planes" />
            </label>
            <textarea  class="form-control focusNext" id="planes" name="planes" cols="30" rows="4"  required="required"  placeholder="Ingrese los planes"></textarea>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="form-control-label" for="historia"><spring:message code="historia_clinica" /> </label>
            <textarea  class="form-control focusNext" id="historia" name="historia" cols="30" rows="6"  placeholder="Ingrese la historia clínica"></textarea>
        </div>

        <div class="col-lg-2 col-md-4 col-sm-6">
            <div class="form-group">
                <label for="medico" class="form-control-label"><spring:message code="Diagnóstico 1" /><span class="required">*</span></label>
                <select class="form-control focusNext" id="diagnostico1" name="diagnostico1" required="required"  onchange="valida_diag();">
                    <option selected value=""><spring:message code="select" />...</option>
                    <c:forEach var="item" items="${CAT_DIAGNOSTICOS}">
                        <option value="${item.catKey}">${item.catKey} - ${item.spanish}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="col-lg-2 col-md-4 col-sm-6">
            <div class="form-group">
                <label for="medico" class="form-control-label"><spring:message code="Diagnóstico 2" /></label>
                <select class="form-control focusNext" id="diagnostico2" name="diagnostico2"  onchange="valida_diag();">
                    <option selected value=""><spring:message code="select" />...</option>
                    <c:forEach var="item" items="${CAT_DIAGNOSTICOS}">
                        <option value="${item.catKey}">${item.catKey} - ${item.spanish}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="col-lg-2 col-md-4 col-sm-6">
            <div class="form-group">
                <label for="medico" class="form-control-label"><spring:message code="Diagnóstico 3" /></label>
                <select class="form-control focusNext" id="diagnostico3" name="diagnostico3"   onchange="valida_diag();">
                    <option selected value=""><spring:message code="select" />...</option>
                    <c:forEach var="item" items="${CAT_DIAGNOSTICOS}">
                        <option value="${item.catKey}">${item.catKey} - ${item.spanish}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-6">
            <div class="form-group">
                <label for="medico" class="form-control-label"><spring:message code="Diagnóstico 4" /></label>
                <select class="form-control focusNext" id="diagnostico4" name="diagnostico4"   onchange="valida_diag();">
                    <option selected value=""><spring:message code="select" />...</option>
                    <c:forEach var="item" items="${CAT_DIAGNOSTICOS}">
                        <option value="${item.catKey}">${item.catKey} - ${item.spanish}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="form-control-label" for="dx"><spring:message code="Otro diagnostico:" /> </label>
            <textarea  class="form-control focusNext" id="dx" name="dx" cols="30" rows="2"    placeholder="Ingrese el diagnóstico"></textarea>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-12">
                    <div class="form-group">
                        <label for="telefono" class="form-control-label"><spring:message code="tel_emergencia" /></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-phone"></i>
            </span>
                            <input type="text" class="form-control" id="telefono" name="telefono"
                                   value=""/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12">
                    <div class="form-group">
                        <label class="form-control-label" for="cita"><spring:message code="proxima_cita" /></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                            <input type="text" class="form-control date-picker" id="cita" name="cita"
                                   value="" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="row">
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="medico" class="form-control-label"><spring:message code="medico" /><span class="required">*</span></label>
                        <select class="form-control focusNext" id="medico" name="medico" required="required"  onchange="fech_Hora_medico();">
                            <option selected value=""><spring:message code="select" />...</option>
                            <c:forEach var="item" items="${medicos}">
                                <option value="${item.codigo}">${item.idPersona} - ${item.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="fechaMedico" class="form-control-label"><spring:message code="fecha" /><span class="required">*</span></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                            <input type="text" class="form-control date-picker" required id="fechaMedico" name="fechaMedico"
                                   value="" />
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="horaMedico" class="form-control-label"><spring:message code="hora" /><span class="required">*</span></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                            <input type="text" class="form-control time-picker" id="horaMedico" required name="horaMedico" value="" />
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="enfermeria" class="form-control-label"><spring:message code="enfermeria" /><span class="required">*</span></label>
                        <!--     <select class="form-control focusNext" id="enfermeria" name="enfermeria" required="required" onchange="fech_Hora_medico();"> -->
                        <select class="form-control focusNext" id="enfermeria" name="enfermeria">
                        <option selected value=""><spring:message code="select" />...</option>
                            <c:forEach var="item" items="${enfermeria}">
                                <option value="${item.codigo}">${item.idPersona} - ${item.nombre}</option>
                            </c:forEach>

                        </select>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                   <!--     <label for="fechaEnfermeria" class="form-control-label"> <span class="required">*</span></label> -->
                        <label for="fechaEnfermeria" class="form-control-label"><spring:message code="fecha" /></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                            <input type="text" class="form-control date-picker"  id="fechaEnfermeria" name="fechaEnfermeria"
                                   value="" />
                        </div>
                    </div>

                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <!--     <label for="horaEnfermeria" class="form-control-label"> <span class="required">*</span></label> -->
                        <label for="horaEnfermeria" class="form-control-label"><spring:message code="hora" /></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                            <input type="text" class="form-control time-picker" id="horaEnfermeria"  name="horaEnfermeria" value="" />
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- FIN PLANES e HISTORIA CLINICA-->

<div class="card-block">
    <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-2"></div>
        <div class="col-lg-2 col-md-2 col-sm-4">
            <div class="form-group">
                <button class="btn btn-success  btn-ladda btn-block btn-lg"
                        type="submit" id="btnModificar">
                    <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" />
                </button>
            </div>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-4">
            <div class="form-group">
                <a href="${fn:escapeXml(buscarHojas)}" data-toggle="tooltip" title="Volver" data-placement="top"
                   class="btn btn-danger  btn-ladda btn-block btn-lg">
                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                    <spring:message code="cancel" />
                </a>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-2"></div>
    </div>
</div>
</form>
</div>
</div>
</div>
<!-- /.conainer-fluid -->
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
<!--
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="Datepicker" />
<script src="${Datepicker}" type="text/javascript"></script>
-->
<!--  Select2 scripts-->
<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="select2Js" />
<script type="text/javascript" src="${select2Js}"></script>

<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>

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
<!--own scripts -->
<spring:url value="/resources/js/libs/jquery.serializejson.js" var="Serializejson" />
<script src="${Serializejson}"></script>
<spring:url value="/resources/js/views/fingering/process-clinicalsheetDD.js" var="Clinicalsheet" />
<script src="${Clinicalsheet}"></script>

<spring:url value="/hojaclinicaDD/searchParticipant" var="searchUrl"/>
<spring:url value="/hojaclinicaDD/save" var="saveUrl"/>
<spring:url value="/hojaclinicaDD/getcodSupervisor" var="getcodSupervisor"/>

</body>
<script>
    jQuery(document).ready(function() {
        var parameters = {
            searchUrl : "${searchUrl}",
            saveUrl: "${saveUrl}",
            listaUrl: "${listaUrl}",
            successmessage: "${successMessage}",
            error: "${errorProcess}",
            locale : "${lenguaje}",
            getcodSupervisor : "${getcodSupervisor}",
        };

        ClinicalSheetDD.init(parameters);

        /*document.addEventListener('keypress', function(evt) {
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
        });*/

        $('#medico').append('<option value="-1">N/A</option>');
        $('#enfermeria').append('<option value="-1">N/A</option>');


     //   $('#dx').attr("disabled", false);
        $('#dx').hide();
        $('#activarEnfermeria').css("visibility","hidden");

    });

    function buscar_codigosup() {
        var parametro = {
            getcodSupervisor : "${getcodSupervisor}",
            saveCatalogCartaUrl: "${saveCatalogCartaUrl}",
            delCatalogCartaUrl: "${delCatalogCartaUrl}"
        };

      /*  $.getJSON(parametro.getcodSupervisor, { codigo: $("#codigoSupervisor").val(), ajax: 'true'  }, function (data) {
            if (data.result == null) {
                console.log(data);
                if (data != null ) {
                    swal.fire({
                        title: "A2CARES",
                        text: data.nombrecompleto,
                        type: "warning",
                        cancelButtonText: 'Cancelar'

                    });
            } else {

                    swal.fire({
                        title: "A2CARES",
                        text: "Código ingresado no pertence a un Médico Supervisor.",
                        type: "warning",
                        cancelButtonText: 'Cancelar'

                    });
            }
        });*/
    }
      function desactivar_Enfermeria() {

              $('#datos_enfermeria_div').css("visibility","hidden");
          $('#activarEnfermeria').css("visibility","visible");
          $('#desactivarEnfermeria').css("visibility","hidden");

          $('#peso').val(0);
          $('#talla').val(0);
          $('#edad').val(0);
          $('#sexo').val("Null");
          $('#pa').val(0);
          $('#fc').val(60);
          $('#temp').val(34);
          $('#so').val(0);


       }
    function activar_Enfermeria() {
            $('#datos_enfermeria_div').css("visibility","visible");
        $('#activarEnfermeria').css("visibility","hidden");
        $('#desactivarEnfermeria').css("visibility","visible");
    }
    function valida_diag() {
        if ($("#diagnostico1").val() !== "" && $("#diagnostico2").val() !== ""&& $("#diagnostico1").val() === $("#diagnostico2").val() ) {
            swal.fire({
                title: "A2CARES",
                text: "Diagnóstico 2 ya está seleccionado",
                type: "warning",
                cancelButtonText: 'Cancelar'

            });

            $("#diagnostico2").val("");
        };
        if ($("#diagnostico1").val() !== "" && $("#diagnostico3").val() !== "" && $("#diagnostico1").val() === $("#diagnostico3").val() ) {
            swal.fire({
                title: "A2CARES",
                text: "Diagnóstico 3 ya está seleccionado",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });
            $("#diagnostico3").val("");
        };
        if ($("#diagnostico1").val() !== "" && $("#diagnostico4").val() !== "" && $("#diagnostico1").val() === $("#diagnostico4").val() ) {
            swal.fire({
                title: "A2CARES",
                text: "Diagnóstico 4  ya está seleccionado",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });
            $("#diagnostico4").val("");
        };
        if ($("#diagnostico2").val() !== "" && $("#diagnostico3").val() !== "" && $("#diagnostico2").val() === $("#diagnostico3").val() ) {
            swal.fire({
                title: "A2CARES",
                text: "Diagnóstico 3 ya está seleccionado",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });
            $("#diagnostico3").val("");
        };
        if ($("#diagnostico2").val() !== "" && $("#diagnostico4").val() !== "" && $("#diagnostico2").val() === $("#diagnostico4").val() ) {
            swal.fire({
                title: "A2CARES",
                text: "Diagnóstico 4 ya está seleccionado",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });
            $("#diagnostico4").val("");
        };
        if ($("#diagnostico3").val() !== "" && $("#diagnostico4").val() !== ""&& $("#diagnostico3").val() === $("#diagnostico4").val() ) {
            swal.fire({
                title: "A2CARES",
                text: "Diagnóstico 4 ya está seleccionado",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });
            $("#diagnostico4").val("");
        };

        if ($("#diagnostico4").val() !== "" && $("#diagnostico4").val() === "47") {
            $('#dx').attr("disabled", false);
            $('#dx').show();
        }else
        if ($("#diagnostico3").val() !== "" && $("#diagnostico3").val() === "47") {
            $('#dx').attr("disabled", false);
            $('#dx').show();
        }else
        if ($("#diagnostico2").val() !== "" && $("#diagnostico2").val() === "47") {
            $('#dx').attr("disabled", false);
            $('#dx').show();
        }else
        if ($("#diagnostico1").val() !== "" && $("#diagnostico1").val() === "47") {
            $('#dx').attr("disabled", false);
            $('#dx').show();
        }else {
                $('#dx').hide();
        };

    };
    function fech_Hora_medico(){
        if ($("#medico").val() === '-1' && $("#enfermeria").val() !== '-1')
        {
            $('#fechaMedico').attr("disabled", true);
            $('#horaMedico').attr("disabled", true);

            $('#fechaMedico').attr("text",'1900-01-01');
            $('#horaMedico').attr("text",'00:00:001');

        } else
        {
           if ($("#medico").val() !== '-1'){
            $('#fechaMedico').attr("disabled", false);
            $('#horaMedico').attr("disabled", false);
           };


        };


        if ($("#enfermeria").val() === '-1' && $("#medico").val() !== '-1')
        {
            $("#fechaEnfermeria").attr("disabled", true);
            $("#horaEnfermeria").attr("disabled", true);

            $('#fechaEnfermeria').attr("text",'1900-01-01');
            $('#horaEnfermeria').attr("text",'00:00:001');

        }else {
            if ($("#enfermeria").val() !== '-1'){
                $("#fechaEnfermeria").attr("disabled", false);
            $("#horaEnfermeria").attr("disabled", false);
        };

            if ($("#medico").val() === '-1' && $("#enfermeria").val() === '-1')
            {
                swal.fire({
                    title: "A2CARES",
                    text: "No se puede utilizar el valor N/A en Medícos y en Enfermería",
                    type: "warning",
                    cancelButtonText: 'Cancelar'
                });
            };
        };
    };

    function fech_ultimo_dia_fiebre(){


        if ($("#ultimoDiaFiebre").val() !== "" && $("#horaUltimoDiaF").val()=== "")
        {
           // $("horaUltimoDiaF").prop(required, "required");
          //   $('#horaUltimoDiaF').attr('required', 'required');
            swal.fire({
                title: "A2CARES",
                text: "Campo ULTIMA HORA DE FIEBRE, necesita un valor",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });

        };

        if (  $("#horaUltimoDiaF").val()!== "" && $("#ultimoDiaFiebre").val() === ""  )
        {
           // $("ultimoDiaFiebre").prop("required", true);
          //    $('#ultimoDiaFiebre').attr('required', 'required');
            swal.fire({
                title: "A2CARES",
                text: "Campo ULTIMO DIA DE FIEBRE, necesita un valor",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });

        };



    };

    function fech_ultimo_dia_anti(){
        if ($("#ultimaDosisAntip").val() !== "" && $("#horaUltimaDosisAntip").val()=== "")
        {
            // $("horaUltimoDiaF").prop(required, "required");
            //   $('#horaUltimoDiaF').attr('required', 'required');
            swal.fire({
                title: "A2CARES",
                text: "Campo ULTIMA HORA DE ANTIPIRETICO, necesita un valor",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });

        };
        if (  $("#horaUltimaDosisAntip").val()!== "" && $("#ultimaDosisAntip").val() === ""  )
        {
            // $("ultimoDiaFiebre").prop("required", true);
            //    $('#ultimoDiaFiebre').attr('required', 'required');
            swal.fire({
                title: "A2CARES",
                text: "Campo ULTIMA DOSIS DE ANTIPIRETICO, necesita un valor",
                type: "warning",
                cancelButtonText: 'Cancelar'
            });

        };


    };
</script>
</html>