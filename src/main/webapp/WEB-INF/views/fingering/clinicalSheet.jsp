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
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
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
    </li>
</ol>
<div class="container-fluid">
<div class="card">
<div class="card-header">
    <h3 class="page-title">
        <spring:message code="Hoja Clinica" /> <small><spring:message code="Digitación" /></small>
    </h3>
</div>

<form action="#" autocomplete="off" id="search-participant-form" class="form-horizontal">
    <div class="card-block">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="form-group row">
                        <label class="form-control-label col-lg-2 col-md-12 col-sm-12" for="participantCode">Buscar participante
                            <span class="required">*</span>
                        </label>
                        <div class="input-group col-lg-4 col-md-12 col-sm-12">
                            <span class="input-group-addon">
                                <i class="fa fa-user"></i>
                            </span>
                            <input id="participantCode" name="participantCode" type="text" value="" class="form-control" placeholder="Ingresar código de participante"/>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</form>
<form name="form-clinicalsheet" autocomplete="off" role="form" action="#" id="form-clinicalsheet" method="post" class="form-horizontal">

<div class="card-block">
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize">Datos Personales</h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-5 col-md-12 col-sm-12">
                <div class="form-group">
                    <label for="nombre" class="form-control-label">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre"
                           value="Miguel Salinas Aurelio Salinas"
                           readonly />
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="form-group">
                    <label for="fecha">Fecha de Nacimiento:</label>
                    <input type="text" class="form-control" id="fecha" name="fecha"
                           value="21/12/1989" readonly />
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="form-group">
                    <label for="edadPart">Edad:</label>
                    <input type="text" class="form-control" id="edadPart" name="edadPart" value="31 A 2 M 10 D" readonly />
                </div>

            </div>
            <div class="col-lg-1 col-md-4 col-sm-6">
                <div class="form-group">
                    <label for="sexoPart">Sexo:</label>
                    <input type="text" class="form-control" id="sexoPart" required name="sexoPart" value="M" readonly />
                </div>

            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize">Encabezado</h4>
    </div>
    <div class="col-lg-3 col-md-6 col-sm-12">
        <div class="form-group">
            <label class="form-control-label" for="fechaCons">Fecha Consulta:
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
            <label class="form-control-label" for="horaCons">Hora Consulta:
                <span class="required">*</span>
            </label>
            <div class="input-group">
            <span class="input-group-addon">
                <i class="fa fa-clock"></i>
            </span>
            <input type="text" class="form-control" id="horaCons" required name="horaCons" value="" />
            </div>
        </div>

    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize">Datos de enfermería</h4>
    </div>
    <div class="col-lg-6 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="peso" class="form-control-label">Peso (Kg):
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fas fa-weight"></i>
                    </span>
                    <input type="text" class="form-control" required id="peso" name="peso"
                           value=""/>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="talla">Talla (cm):
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-sort-numeric-up"></i>
            </span>
                    <input type="text" class="form-control" required id="talla" name="talla"
                           value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="edad">Edad:
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-pen"></i>
            </span>
                    <input type="text" class="form-control" id="edad" required name="edad" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="sexo">Sexo:
                        <span class="required">*</span>
                    </label>
                    <select class="form-control focusNext" id="sexo" name="sexo" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                        <option value=""><spring:message code="M" /></option>
                    </select>
                </div>

            </div>
        </div>
    </div>
    <div class="col-lg-6 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="pa" class="form-control-label">Presión arterial:
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-heart"></i>
            </span>
                    <input type="text" class="form-control" required id="pa" name="pa"
                           value=""/>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="fc">Frecuencia cardiaca:
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-heartbeat"></i>
            </span>
                        <input type="text" class="form-control" required id="fc" name="fc"
                           value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="temp">Temperatura:
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-thermometer-half"></i>
            </span>
                    <input type="text" class="form-control" id="temp" required name="temp" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="so">Saturación de oxígeno
                        <span class="required">*</span>
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
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize">Datos para llenar el médico</h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="horaIniCons">Hora de inicio de la consulta:
                        <span class="required">*</span>
                    </label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                    <input type="text" class="form-control" required id="horaIniCons" name="horaIniCons"
                           value="" />
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="tipoConsulta">Consulta:
                        <span class="required">*</span>
                    </label>
                    <select class="form-control focusNext" id="tipoConsulta" name="tipoConsulta" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                    </select>
                </div>

            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="lugarConsutla">Lugar de consulta:
                        <span class="required">*</span>
                    </label>
                    <select class="form-control focusNext" id="lugarConsutla" name="lugarConsutla" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                    </select>
                </div>

            </div>
        </div>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize">Signos vitales del paciente</h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="paMedico">Presión arterial:
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
                    <label class="form-control-label" for="tempMedico">Temperatura:
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
                    <label class="form-control-label" for="frMedico">Frecuencia respiratoria:
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
                    <label class="form-control-label" for="fcMedico">Frecuencia Cardiaca:
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
                    <label class="form-control-label" for="soMedico">Saturación del oxígeno
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
                    <label class="form-control-label" for="fis">Fecha de inicio los síntomas:</label>
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
                    <label class="form-control-label" for="fif">Fecha de inicio la fiebre:</label>
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
                    <label class="form-control-label" for="ultimoDiaFiebre">Ultimo día de fiebre:</label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                    <input type="text" class="form-control date-picker" id="ultimoDiaFiebre" name="ultimoDiaFiebre" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="horaUltimoDiaF">Hora:</label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                    <input type="text" class="form-control" id="horaUltimoDiaF" name="horaUltimoDiaF" value="" />
                    </div>
                </div>

            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="ultimaDosisAntip">Ultima dosis de antiperetico:</label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                    <input type="text" class="form-control date-picker" id="ultimaDosisAntip" name="ultimaDosisAntip" value="" />
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-4 col-sm-6">
                <div class="form-group">
                    <label class="form-control-label" for="horaUltimaDosisAntip">Hora:</label>
                    <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                    <input type="text" class="form-control" id="horaUltimaDosisAntip" name="horaUltimaDosisAntip" value="" />
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
                    <label class="label" style="color: white"><strong>General</strong></label>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_S" name="rbgeneral" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgeneral_S"><strong>S</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_N" name="rbgeneral" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgeneral_N"><strong>N</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_D" name="rbgeneral" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgeneral_D"><strong>D</strong></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Fiebre
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_1S" name="rbgeneral_1" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgeneral_1S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_1N" name="rbgeneral_1" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgeneral_1N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_1D" name="rbgeneral_1" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgeneral_1D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Anormalmente somnoliento
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_2S" name="rbgeneral_2" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgeneral_2S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_2N" name="rbgeneral_2" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgeneral_2N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_2D" name="rbgeneral_2" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgeneral_2D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Mal estado general
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_3S" name="rbgeneral_3" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgeneral_3S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_3N" name="rbgeneral_3" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgeneral_3N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_3D" name="rbgeneral_3" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgeneral_3D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Perdida de la Consciencia
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_4S" name="rbgeneral_4" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgeneral_4S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_4N" name="rbgeneral_4" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgeneral_4N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_4D" name="rbgeneral_4" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgeneral_4D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Inquieto irritable
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_5S" name="rbgeneral_5" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgeneral_5S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_5N" name="rbgeneral_5" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgeneral_5N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_5D" name="rbgeneral_5" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgeneral_5D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Convulsiones
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_6S" name="rbgeneral_6" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgeneral_6S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_6N" name="rbgeneral_6" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgeneral_6N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_6D" name="rbgeneral_6" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgeneral_6D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Letargia
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_7S" name="rbgeneral_7" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgeneral_7S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_7N" name="rbgeneral_7" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgeneral_7N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgeneral_7D" name="rbgeneral_7" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgeneral_7D">D</label>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="col-lg-6 col-md-12 col-sm-12">
        <div class="card-block">
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                    <label class="label" style="color: white" ><strong>Gastrointestinal</strong></label>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5" style="align-content: center; background-color: lightgray">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_S" name="rbgastroin" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgastroin_S"><strong>S</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_N" name="rbgastroin" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgastroin_N"><strong>N</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_D" name="rbgastroin" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgastroin_D"><strong>D</strong></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Poco apetito
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_1S" name="rbgastroin_1" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgastroin_1S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_1N" name="rbgastroin_1" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgastroin_1N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_1D" name="rbgastroin_1" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgastroin_1D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Náuseas
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_2S" name="rbgastroin_2" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgastroin_2S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_2N" name="rbgastroin_2" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgastroin_2N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_2D" name="rbgastroin_2" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgastroin_2D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Vómito
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_3S" name="rbgastroin_3" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgastroin_3S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_3N" name="rbgastroin_3" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgastroin_3N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_3D" name="rbgastroin_3" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgastroin_3D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-10 col-md-12 col-sm-12">
                    <div class="form-group row">
                        <label class="form-control-label col-lg-6 col-md-6 col-sm-12" for="numVomito">N° de vómitos en las últimas 12 hrs:
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
                    <label class="form-control-label">Diarrea
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_4S" name="rbgastroin_4" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgastroin_4S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_4N" name="rbgastroin_4" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgastroin_4N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_4D" name="rbgastroin_4" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgastroin_4D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Dolor abdominal
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_5S" name="rbgastroin_5" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgastroin_5S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_5N" name="rbgastroin_5" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgastroin_5N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_5D" name="rbgastroin_5" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgastroin_5D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Hepatomegalia
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_6S" name="rbgastroin_6" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgastroin_6S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_6N" name="rbgastroin_6" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgastroin_6N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgastroin_6D" name="rbgastroin_6" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgastroin_6D">D</label>
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
                    <label class="label" style="color: white"><strong>Cabeza</strong></label>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_S" name="rbcabeza" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbcabeza_S"><strong>S</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_N" name="rbcabeza" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbcabeza_N"><strong>N</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_D" name="rbcabeza" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbcabeza_D"><strong>D</strong></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Dolor de cabeza
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_1S" name="rbcabeza_1" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbcabeza_1S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_1N" name="rbcabeza_1" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbcabeza_1N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_1D" name="rbcabeza_1" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbcabeza_1D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Conjuntivitis
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_2S" name="rbcabeza_2" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbcabeza_2S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_2N" name="rbcabeza_2" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbcabeza_2N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_2D" name="rbcabeza_2" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbcabeza_2D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Hemorragia Subconjuntival
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_3S" name="rbcabeza_3" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbcabeza_3S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_3N" name="rbcabeza_3" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbcabeza_3N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_3D" name="rbcabeza_3" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbcabeza_3D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Dolor Retroocular
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_4S" name="rbcabeza_4" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbcabeza_4S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_4N" name="rbcabeza_4" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbcabeza_4N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbcabeza_4D" name="rbcabeza_4" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbcabeza_4D">D</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 col-md-12 col-sm-12">
        <div class="card-block">
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                    <label class="label" style="color: white"><strong>Osteomuscular</strong></label>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_S" name="rbosteomusc" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbosteomusc_S"><strong>S</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_N" name="rbosteomusc" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbosteomusc_N"><strong>N</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_D" name="rbosteomusc" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbosteomusc_D"><strong>D</strong></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Artralgia
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_1S" name="rbosteomusc_1" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbosteomusc_1S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_1N" name="rbosteomusc_1" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbosteomusc_1N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_1D" name="rbosteomusc_1" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbosteomusc_1D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Mialgia
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_2S" name="rbosteomusc_2" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbosteomusc_2S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_2N" name="rbosteomusc_2" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbosteomusc_2N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_2D" name="rbosteomusc_2" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbosteomusc_2D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Lumbalgia
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_3S" name="rbosteomusc_3" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbosteomusc_3S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_3N" name="rbosteomusc_3" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbosteomusc_3N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_3D" name="rbosteomusc_3" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbosteomusc_3D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Dolor de cuello
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_4S" name="rbosteomusc_4" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbosteomusc_4S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_4N" name="rbosteomusc_4" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbosteomusc_4N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_4D" name="rbosteomusc_4" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbosteomusc_4D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Edema
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_5S" name="rbosteomusc_5" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbosteomusc_5S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_5N" name="rbosteomusc_5" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbosteomusc_5N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbosteomusc_5D" name="rbosteomusc_5" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbosteomusc_5D">D</label>
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
                    <label class="label" style="color: white"><strong>Garganta</strong></label>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_S" name="rbgarganta" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgarganta_S"><strong>S</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_N" name="rbgarganta" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgarganta_N"><strong>N</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_D" name="rbgarganta" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgarganta_D"><strong>D</strong></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Dolor de garganta
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_1S" name="rbgarganta_1" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgarganta_1S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_1N" name="rbgarganta_1" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgarganta_1N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_1D" name="rbgarganta_1" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgarganta_1D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Eritema
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_2S" name="rbgarganta_2" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgarganta_2S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_2N" name="rbgarganta_2" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgarganta_2N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_2D" name="rbgarganta_2" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgarganta_2D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Adenopatías cervicales
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_3S" name="rbgarganta_3" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgarganta_3S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_3N" name="rbgarganta_3" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgarganta_3N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_3D" name="rbgarganta_3" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgarganta_3D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Exudado
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_4S" name="rbgarganta_4" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgarganta_4S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_4N" name="rbgarganta_4" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgarganta_4N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_4D" name="rbgarganta_4" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgarganta_4D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Petequias en mucosa
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_5S" name="rbgarganta_5" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbgarganta_5S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_5N" name="rbgarganta_5" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbgarganta_5N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbgarganta_5D" name="rbgarganta_5" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbgarganta_5D">D</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 col-md-12 col-sm-12">
    <div class="card-block">
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
            <label class="label" style="color: white"><strong>Cutáneo</strong></label>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_S" name="rbcutaneo" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_S"><strong>S</strong></label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_N" name="rbcutaneo" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_N"><strong>N</strong></label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_D" name="rbcutaneo" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_D"><strong>D</strong></label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Rash localizado
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_1S" name="rbcutaneo_1" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_1S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_1N" name="rbcutaneo_1" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_1N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_1D" name="rbcutaneo_1" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_1D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Rash generalizado
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_2S" name="rbcutaneo_2" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_2S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_2N" name="rbcutaneo_2" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_2N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_2D" name="rbcutaneo_2" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_2D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Rash eritematoso
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_3S" name="rbcutaneo_3" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_3S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_3N" name="rbcutaneo_3" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_3N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_3D" name="rbcutaneo_3" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_3D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Rash Macular
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_4S" name="rbcutaneo_4" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_4S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_4N" name="rbcutaneo_4" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_4N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_4D" name="rbcutaneo_4" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_4D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Rash papular
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_5S" name="rbcutaneo_5" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_5S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_5N" name="rbcutaneo_5" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_5N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_5D" name="rbcutaneo_5" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_5D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Piel moteada
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_6S" name="rbcutaneo_6" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_6S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_6N" name="rbcutaneo_6" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_6N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_6D" name="rbcutaneo_6" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_6D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Rubor facial
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_7S" name="rbcutaneo_7" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_7S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_7N" name="rbcutaneo_7" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_7N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_7D" name="rbcutaneo_7" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_7D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Cianosis central
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_8S" name="rbcutaneo_8" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_8S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_8N" name="rbcutaneo_8" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_8N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_8D" name="rbcutaneo_8" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_8D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Ictericia
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_9S" name="rbcutaneo_9" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbcutaneo_9S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_9N" name="rbcutaneo_9" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbcutaneo_9N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbcutaneo_9D" name="rbcutaneo_9" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbcutaneo_9D">D</label>
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
            <label class="label" style="color: white"><strong>Respiratorio</strong></label>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_S" name="rbrespiratorio" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_S"><strong>S</strong></label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_N" name="rbrespiratorio" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_N"><strong>N</strong></label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_D" name="rbrespiratorio" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_D"><strong>D</strong></label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Tos
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_1S" name="rbrespiratorio_1" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_1S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_1N" name="rbrespiratorio_1" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_1N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_1D" name="rbrespiratorio_1" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_1D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Rinorrea
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_2S" name="rbrespiratorio_2" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_2S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_2N" name="rbrespiratorio_2" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_2N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_2D" name="rbrespiratorio_2" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_2D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Congestión nasal
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_3S" name="rbrespiratorio_3" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_3S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_3N" name="rbrespiratorio_3" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_3N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_3D" name="rbrespiratorio_3" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_3D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Otalgia
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_4S" name="rbrespiratorio_4" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_4S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_4N" name="rbrespiratorio_4" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_4N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_4D" name="rbrespiratorio_4" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_4D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Aleteo nasal
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_5S" name="rbrespiratorio_5" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_5S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_5N" name="rbrespiratorio_5" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_5N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_5D" name="rbrespiratorio_5" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_5D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Respiración rápida
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_6S" name="rbrespiratorio_6" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_6S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_6N" name="rbrespiratorio_6" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_6N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_6D" name="rbrespiratorio_6" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_6D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Estridor en reposo
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_7S" name="rbrespiratorio_7" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_7S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_7N" name="rbrespiratorio_7" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_7N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_7D" name="rbrespiratorio_7" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_7D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Tirajes subcostales
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_8S" name="rbrespiratorio_8" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_8S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_8N" name="rbrespiratorio_8" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_8N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_8D" name="rbrespiratorio_8" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_8D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Sibilancia
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_9S" name="rbrespiratorio_9" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_9S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_9N" name="rbrespiratorio_9" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_9N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_9D" name="rbrespiratorio_9" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_9D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Crépitos
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_10S" name="rbrespiratorio_10" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_10S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_10N" name="rbrespiratorio_10" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_10N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_10D" name="rbrespiratorio_10" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_10D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Roncos
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_11S" name="rbrespiratorio_11" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_11S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_11N" name="rbrespiratorio_11" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_11N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_11D" name="rbrespiratorio_11" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_11D">D</label>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-8 col-md-8 col-sm-7">
            <label class="form-control-label">Disnea
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-group col-lg-4 col-md-4 col-sm-5">
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_12S" name="rbrespiratorio_12" class="custom-control-input" value="S">
                <label class="custom-control-label" for="rbrespiratorio_12S">S</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_12N" name="rbrespiratorio_12" class="custom-control-input" value="N">
                <label class="custom-control-label" for="rbrespiratorio_12N">N</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="rbrespiratorio_12D" name="rbrespiratorio_12" class="custom-control-input" value="D">
                <label class="custom-control-label" for="rbrespiratorio_12D">D</label>
            </div>
        </div>
    </div>
    </div>
    </div>
    <div class="col-lg-6 col-md-12 col-sm-12">
        <div class="card-block">
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7" style="text-align: center; background-color: cornflowerblue">
                    <label class="label" style="color: white"><strong>Estado nutricional</strong></label>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_S" name="rbestadonut" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbestadonut_S"><strong>S</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_N" name="rbestadonut" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbestadonut_N"><strong>N</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_D" name="rbestadonut" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbestadonut_D"><strong>D</strong></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-12">
                    <br>
                    <div class="form-group row">
                        <label class="form-control-label col-lg-6 col-md-6 col-sm-12" for="imc">IMC
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
                    <label class="form-control-label">Obeso
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_1S" name="rbestadonut_1" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbestadonut_1S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_1N" name="rbestadonut_1" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbestadonut_1N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_1D" name="rbestadonut_1" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbestadonut_1D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Sobrepeso
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_2S" name="rbestadonut_2" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbestadonut_2S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_2N" name="rbestadonut_2" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbestadonut_2N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_2D" name="rbestadonut_2" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbestadonut_2D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Sospecha de problema
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_3S" name="rbestadonut_3" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbestadonut_3S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_3N" name="rbestadonut_3" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbestadonut_3N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_3D" name="rbestadonut_3" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbestadonut_3D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Normal
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_4S" name="rbestadonut_4" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbestadonut_4S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_4N" name="rbestadonut_4" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbestadonut_4N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_4D" name="rbestadonut_4" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbestadonut_4D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Bajo peso
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_5S" name="rbestadonut_5" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbestadonut_5S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_5N" name="rbestadonut_5" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbestadonut_5N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_5D" name="rbestadonut_5" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbestadonut_5D">D</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <label class="form-control-label">Bajo peso severo
                        <span class="required">*</span>
                    </label>
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_6S" name="rbestadonut_6" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbestadonut_6S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_6N" name="rbestadonut_6" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbestadonut_6N">N</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbestadonut_6D" name="rbestadonut_6" class="custom-control-input" value="D">
                        <label class="custom-control-label" for="rbestadonut_6D">D</label>
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
        <h4 class="text-capitalize">Categoría</h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-2 col-md-6 col-sm-12">
                <div class="form-group">
                    <label for="categoria" class="form-control-label">Categoria:
                        <span class="required">*</span>
                    </label>
                    <select class="form-control focusNext" id="categoria" name="categoria" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                    </select>
                </div>
            </div>
            <div class="col-lg-2 col-md-6 col-sm-12">
                <div class="form-group">
                    <label for="cambioCategoria" class="form-control-label">Cambio categoria:</label>
                    <select class="form-control focusNext" id="cambioCategoria" name="cambioCategoria" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
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
        <label class="label" style="color: white"><strong>Manifestaciones hemorrágicas</strong></label>
    </div>
    <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_S" name="rbmanhemo" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_S"><strong>S</strong></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_N" name="rbmanhemo" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_N"><strong>N</strong></label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_D" name="rbmanhemo" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_D"><strong>D</strong></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Prueba del torniquete
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_1S" name="rbmanhemo_1" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_1S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_1N" name="rbmanhemo_1" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_1N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_1D" name="rbmanhemo_1" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_1D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Petequias ≥10
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_2S" name="rbmanhemo_2" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_2S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_2N" name="rbmanhemo_2" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_2N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_2D" name="rbmanhemo_2" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_2D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Petequias ≥20
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_3S" name="rbmanhemo_3" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_3S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_3N" name="rbmanhemo_3" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_3N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_3D" name="rbmanhemo_3" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_3D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Piel y extremidades frías
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_4S" name="rbmanhemo_4" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_4S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_4N" name="rbmanhemo_4" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_4N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_4D" name="rbmanhemo_4" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_4D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Palidez en extremidades
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_5S" name="rbmanhemo_5" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_5S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_5N" name="rbmanhemo_5" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_5N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_5D" name="rbmanhemo_5" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_5D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Epistaxis
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_6S" name="rbmanhemo_6" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_6S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_6N" name="rbmanhemo_6" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_6N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_6D" name="rbmanhemo_6" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_6D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Gingivorragia
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_7S" name="rbmanhemo_7" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_7S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_7N" name="rbmanhemo_7" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_7N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_7D" name="rbmanhemo_7" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_7D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Petequias espontaneas
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_8S" name="rbmanhemo_8" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_8S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_8N" name="rbmanhemo_8" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_8N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_8D" name="rbmanhemo_8" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_8D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Llenado capilar ˃2 segundos
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_9S" name="rbmanhemo_9" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_9S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_9N" name="rbmanhemo_9" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_9N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_9D" name="rbmanhemo_9" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_9D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Cianosis
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_10S" name="rbmanhemo_10" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_10S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_10N" name="rbmanhemo_10" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_10N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_10D" name="rbmanhemo_10" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_10D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Hipermenorrea
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_11S" name="rbmanhemo_11" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_11S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_11N" name="rbmanhemo_11" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_11N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_11D" name="rbmanhemo_11" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_11D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Hematemesis
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_12S" name="rbmanhemo_12" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_12S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_12N" name="rbmanhemo_12" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_12N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_12D" name="rbmanhemo_12" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_12D">D</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8 col-md-8 col-sm-7">
        Hemoconcentración
    </div>
    <div class="form-group col-lg-4 col-md-4 col-sm-5">
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_13S" name="rbmanhemo_13" class="custom-control-input" value="S">
            <label class="custom-control-label" for="rbmanhemo_13S">S</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_13N" name="rbmanhemo_13" class="custom-control-input" value="N">
            <label class="custom-control-label" for="rbmanhemo_13N">N</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="rbmanhemo_13D" name="rbmanhemo_13" class="custom-control-input" value="D">
            <label class="custom-control-label" for="rbmanhemo_13D">D</label>
        </div>
    </div>
</div>
</div>
</div>
</div>
<!--- FIN MANIFESTACIONES HEMORRAGICAS CATEGORIA A Y B--->

<div class="card-block">
    <div class="col-lg-12 col-md-12 col-sm-12">
        <h4 class="text-capitalize">Preguntas para todos los pacientes</h4>
    </div>
    <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-12">
                <div class="form-group">
                    <label for="hospitalizado" class="form-control-label">¿Ha sido hospitalizado en el último año?<span class="required">*</span></label>
                    <select class="form-control focusNext" id="hospitalizado" name="hospitalizado" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                    </select>
                </div>
            </div>
            <div class="col-lg-8 col-md-12 col-sm-12">
                <div class="form-group">
                    <label for="unidadSaludHosp" class="form-control-label">Si es un SI especifique nombre de la unidad de salud:</label>
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
                    <label for="transfusion" class="form-control-label">¿Recibió transfusión de sangre en el último año?<span class="required">*</span></label>
                    <select class="form-control focusNext" id="transfusion" name="transfusion" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                    </select>
                </div>
            </div>
            <div class="col-lg-8 col-md-12 col-sm-12">
                <div class="form-group">
                    <label for="transfusionEsp" class="form-control-label">Si es un SI especifique:</label>
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
                    <label for="tomaMedicamento" class="form-control-label">¿Está tomando medicamento en este momento los últimos 6 meses?<span class="required">*</span></label>
                    <select class="form-control focusNext" id="tomaMedicamento" name="tomaMedicamento" required="required">
                        <option selected value=""><spring:message code="select" />...</option>
                    </select>
                </div>
            </div>
            <div class="col-lg-8 col-md-12 col-sm-12">
                <div class="form-group">
                    <label for="cualMedicamento" class="form-control-label">Si es SI especifique el medicamento:</label>
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
                    <label class="label" style="color: white"><strong>Exámenes del laboratorio</strong></label>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5" style="background-color: lightgrey">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_S" name="rbexamen" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbexamen_S"><strong>S</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_N" name="rbexamen" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbexamen_N"><strong>N</strong></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    BHC
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_1S" name="rbexamen_1" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbexamen_1S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_1N" name="rbexamen_1" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbexamen_1N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Serología Arbovirus
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_2S" name="rbexamen_2" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbexamen_2S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_2N" name="rbexamen_2" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbexamen_2N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Gota gruesa
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_3S" name="rbexamen_3" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbexamen_3S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_3N" name="rbexamen_3" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbexamen_3N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    EGO
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_4S" name="rbexamen_4" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbexamen_4S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_4N" name="rbexamen_4" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbexamen_4N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    EGH
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_5S" name="rbexamen_5" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbexamen_5S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_5N" name="rbexamen_5" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbexamen_5N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    OTROS
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_6S" name="rbexamen_6" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbexamen_6S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbexamen_6N" name="rbexamen_6" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbexamen_6N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="form-group row">
                        <label class="form-control-label col-lg-3 col-md-3 col-sm-12" for="descOtroExamen">Especifique:
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
                    <label class="label" style="color: white" ><strong>Tratamiento</strong></label>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5" style="align-content: center; background-color: lightgray">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_S" name="rbtratamiento" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_S"><strong>S</strong></label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_N" name="rbtratamiento" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_N"><strong>N</strong></label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Acetaminofén
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_1S" name="rbtratamiento_1" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_1S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_1N" name="rbtratamiento_1" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_1N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Amoxicilina
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_2S" name="rbtratamiento_2" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_2S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_2N" name="rbtratamiento_2" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_2N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Dicloxacilina
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_3S" name="rbtratamiento_3" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_3S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_3N" name="rbtratamiento_3" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_3N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Penicilina
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_4S" name="rbtratamiento_4" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_4S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_4N" name="rbtratamiento_4" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_4N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Furazolidona
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_5S" name="rbtratamiento_5" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_5S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_5N" name="rbtratamiento_5" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_5N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Metronidazol/Tinidazol
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_6S" name="rbtratamiento_6" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_6S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_6N" name="rbtratamiento_6" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_6N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Albendazol/Mebendazol
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_7S" name="rbtratamiento_7" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_7S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_7N" name="rbtratamiento_7" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_7N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Suero oral
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_8S" name="rbtratamiento_8" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_8S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_8N" name="rbtratamiento_8" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_8N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    Otro tratamiento
                </div>
                <div class="form-group col-lg-4 col-md-4 col-sm-5">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_9S" name="rbtratamiento_9" class="custom-control-input" value="S">
                        <label class="custom-control-label" for="rbtratamiento_9S">S</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" id="rbtratamiento_9N" name="rbtratamiento_9" class="custom-control-input" value="N">
                        <label class="custom-control-label" for="rbtratamiento_9N">N</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="form-group row">
                        <label class="form-control-label col-lg-3 col-md-3 col-sm-12" for="descOtroTratamiento">Especifique:
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
            <label class="form-control-label" for="planes">Planes:<span class="required">*</span>
            </label>
            <textarea  class="form-control focusNext" id="planes" name="planes" cols="30" rows="4" required="required" placeholder="Ingrese los planes"></textarea>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="form-control-label" for="historia">Historia clínica:<span class="required">*</span></label>
            <textarea  class="form-control focusNext" id="historia" name="historia" cols="30" rows="6" required="required" placeholder="Ingrese la historia clínica"></textarea>
        </div>
        <div class="form-group col-lg-12 col-md-12 col-sm-12">
            <label class="form-control-label" for="dx">Diagnóstico:<span class="required">*</span></label>
            <textarea  class="form-control focusNext" id="dx" name="dx" cols="30" rows="2" required="required" placeholder="Ingrese el diagnóstico"></textarea>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-12">
                    <div class="form-group">
                        <label for="telefono" class="form-control-label">Tel. Emergencia:</label>
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
                        <label class="form-control-label" for="cita">Próxima cita:</label>
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
                        <label for="medico" class="form-control-label">Médico:<span class="required">*</span></label>
                        <select class="form-control focusNext" id="medico" name="medico" required="required">
                            <option selected value=""><spring:message code="select" />...</option>
                            <option value=""><spring:message code="1000" /></option>
                        </select>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="fechaMedico" class="form-control-label">Fecha:<span class="required">*</span></label>
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
                        <label for="horaMedico" class="form-control-label">Hora:<span class="required">*</span></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                        <input type="text" class="form-control" id="horaMedico" required name="horaMedico" value="" />
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="enfermeria" class="form-control-label">Enfermería:<span class="required">*</span></label>
                        <select class="form-control focusNext" id="enfermeria" name="enfermeria" required="required">
                            <option selected value=""><spring:message code="select" />...</option>
                            <option value=""><spring:message code="1000" /></option>
                        </select>
                    </div>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="fechaEnfermeria" class="form-control-label">Fecha:<span class="required">*</span></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-calendar"></i>
            </span>
                        <input type="text" class="form-control date-picker" required id="fechaEnfermeria" name="fechaEnfermeria"
                               value="" />
                        </div>
                    </div>

                </div>
                <div class="col-lg-2 col-md-4 col-sm-6">
                    <div class="form-group">
                        <label for="horaEnfermeria" class="form-control-label">Hora:<span class="required">*</span></label>
                        <div class="input-group">
            <span class="input-group-addon">
                <i class="fas fa-clock"></i>
            </span>
                        <input type="text" class="form-control" id="horaEnfermeria" required name="horaEnfermeria" value="" />
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
        <div class="col-sm-2"></div>
        <div class="col-sm-4">
            <div class="form-group">
                <button class="btn btn-success  btn-ladda btn-block btn-lg"
                        type="submit" id="btnModificar">
                    <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" />
                </button>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <a href="${fn:escapeXml(listDetailsHemoUrl)}" data-toggle="tooltip" title="Volver" data-placement="top"
                   class="btn btn-danger  btn-ladda btn-block btn-lg">
                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                    <spring:message code="cancel" />
                </a>
            </div>
        </div>
        <div class="col-sm-2"></div>
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

<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="Datepicker" />
<script src="${Datepicker}" type="text/javascript"></script>

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

<!--own scripts -->
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>

<spring:url value="/resources/js/views/fingering/process-clinicalsheet.js" var="Clinicalsheet" />
<script src="${Clinicalsheet}"></script>


</body>
<script>
    jQuery(document).ready(function() {
        var parameters = {
         searchUrl : "${searchUrl}",
         saveUrl: "${saveUrl}",
         listaUrl: "${listaUrl}",
         successmessage: "${successMessage}",
         error: "${errorProcess}"
         };
        handleDatePickers("${lenguaje}");

        ClinicalSheet.init(parameters);

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
    });
</script>
</html>