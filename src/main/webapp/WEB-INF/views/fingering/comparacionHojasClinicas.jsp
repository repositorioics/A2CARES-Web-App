<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.css" var="boot" />

    <!-- DATETIME PICKER -->
    <spring:url value="/resources/css/bootstrap-datetimepicker.css" var="datetimepickerCss" />
    <link href="${datetimepickerCss}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <style>
        div.dt-buttons {
            float: right !important;

        }
        table {
            border: none;
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 5px 10px;
            text-align: center;
            border: 1px solid #999;
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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/hojaclinica/getHCDigitadas" htmlEscape="true "/>"><spring:message code="comparison" /> <spring:message code="Hojas Clínicas" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="Diferencias - Hojas Clínicas - Digitadas" /></strong>
                        </h3>
                    </div>
                    <form action="#" autocomplete="off" id="search-form" name="search-form" class="form-horizontal">
                        <div class="card-block">
                            <div class="row">
                    <div class="col-lg-3 col-md-3 col-sm-12">
                        <div class="form-group">
                            <label class="form-control-label" for="fechaInicioCons"><spring:message code="fecha_inicio" />
                                <span class="required">*</span>
                            </label>
                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                <input type="text" class="form-control date-picker" id="fechaInicioCons" name="fechaInicioCons" placeholder="<spring:message code="fecha_holder" />"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-12">
                        <div class="form-group">
                            <label class="form-control-label" for="fechaFinCons"><spring:message code="fecha_fin" />
                                <span class="required">*</span>
                            </label>
                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                <input type="text" class="form-control date-picker" id="fechaFinCons" name="fechaFinCons" placeholder="<spring:message code="fecha_holder" />"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="btn-group float-left">
                            <button id="searchClinicalSheet" class="btn btn-lg btn-primary " type="submit">
                                <i class="fa fa-search"></i> <spring:message code="search" />
                            </button>
                        </div>
                        <br>
                        <br>
                    </div>
                            </div>
                        </div>
                        </form>

                    <div class="row no-gutters row-bordered">
                        <div class="col-md-12 col-lg-12 col-xl-12" >
                            <div class="table-responsive">

                            <div  class="card-body" >

                                <div  class="row"   style="width:1400px; height:500px; overflow-x:scroll ; padding-bottom:10px;">
                                <table id="lista_hojas1" class="table table-condensed table-bordered table-hover" >
                                <thead>
                                <tr>
                                <th><spring:message code="Acciones"/></th>
                                <th><spring:message code="Código_Participante1"/></th>
                                <th><spring:message code="Código_Participante2"/></th>
                                <th><spring:message code="Numero_hoja1"/></th>
                                <th><spring:message code="Numero_hoja2"/></th>
                                <th><spring:message code="Código_Supervisor1"/></th>
                                <th><spring:message code="Código_Supervisor2"/></th>
                                <th><spring:message code="Fecha_consulta1"/></th>
                                <th><spring:message code="Fecha_consulta2"/></th>
                                <th><spring:message code="Hora_Consulta1"/></th>
                                <th><spring:message code="Hora_Consulta2"/></th>
                                <th><spring:message code="Peso_kg1"/></th>
                                <th><spring:message code="Peso_kg2"/></th>
                                <th><spring:message code="Talla_cm1"/></th>
                                <th><spring:message code="Talla_cm2"/></th>
                                <th><spring:message code="Presion1"/></th>
                                <th><spring:message code="Presion2"/></th>
                                <th><spring:message code="Fcia_card1"/></th>
                                <th><spring:message code="Fcia_card2"/></th>
                                <th><spring:message code="Temperaturac1"/></th>
                                <th><spring:message code="Temperaturac2"/></th>
                                <th><spring:message code="Saturacion1"/></th>
                                <th><spring:message code="Saturacion2"/></th>
                                <th><spring:message code="Hora_inicio_consulta1"/></th>
                                <th><spring:message code="Hora_inicio_consulta2"/></th>
                                <th><spring:message code="Consulta1"/></th>
                                <th><spring:message code="Consulta2"/></th>
                                <th><spring:message code="Lugar_atencion1"/></th>
                                <th><spring:message code="Lugar_atencion2"/></th>
                                <th><spring:message code="Presion_medico1"/></th>
                                <th><spring:message code="Presion_medico2"/></th>
                                <th><spring:message code="Temperatura_medico1"/></th>
                                <th><spring:message code="Temperatura_medico2"/></th>
                                <th><spring:message code="Fcia_resp1"/></th>
                                <th><spring:message code="Fcia_resp2"/></th>
                                <th><spring:message code="Fcia_card_medico1"/></th>
                                <th><spring:message code="Fcia_card_medico2"/></th>
                                <th><spring:message code="Saturaciono2_medico1"/></th>
                                <th><spring:message code="Saturaciono2_medico2"/></th>
                                <th><spring:message code="Fis1"/></th>
                                <th><spring:message code="Fis2"/></th>
                                <th><spring:message code="Fif1"/></th>
                                <th><spring:message code="Fif2"/></th>
                                <th><spring:message code="Ult_dia_fiebre1"/></th>
                                <th><spring:message code="Ult_dia_fiebre2"/></th>
                                <th><spring:message code="Hora_ult_dia_fiebre1"/></th>
                                <th><spring:message code="Hora_ult_dia_fiebre2"/></th>
                                <th><spring:message code="Ult_dosis_antipiretico1"/></th>
                                <th><spring:message code="Ult_dosis_antipiretico2"/></th>
                                <th><spring:message code="Hora_ult_dosis_antipiretico1"/></th>
                                <th><spring:message code="Hora_ult_dosis_antipiretico2"/></th>
                                <th><spring:message code="Fiebre1"/></th>
                                <th><spring:message code="Fiebre2"/></th>
                                <th><spring:message code="Asomnoliento1"/></th>
                                <th><spring:message code="Asomnoliento2"/></th>
                                <th><spring:message code="Mal_estado1"/></th>
                                <th><spring:message code="Mal_estado2"/></th>
                                <th><spring:message code="Perdida_consciencia1"/></th>
                                <th><spring:message code="Perdida_consciencia2"/></th>
                                <th><spring:message code="Inquieto1"/></th>
                                <th><spring:message code="Inquieto2"/></th>
                                <th><spring:message code="Convulsiones1"/></th>
                                <th><spring:message code="Convulsiones2"/></th>
                                <th><spring:message code="Letargia1"/></th>
                                <th><spring:message code="Letargia2"/></th>
                                <th><spring:message code="Dolor_cabeza1"/></th>
                                <th><spring:message code="Dolor_cabeza2"/></th>
                                <th><spring:message code="Conjuntivitis1"/></th>
                                <th><spring:message code="Conjuntivitis2"/></th>
                                <th><spring:message code="Hemorragia_suconjuntival1"/></th>
                                <th><spring:message code="Hemorragia_suconjuntival2"/></th>
                                <th><spring:message code="Dolor_retroocular1"/></th>
                                <th><spring:message code="Dolor_retroocular2"/></th>
                                <th><spring:message code="Dolor_garganta1"/></th>
                                <th><spring:message code="Dolor_garganta2"/></th>
                                <th><spring:message code="Eritema1"/></th>
                                <th><spring:message code="Eritema2"/></th>
                                <th><spring:message code="Adenopatias_cervicales1"/></th>
                                <th><spring:message code="Adenopatias_cervicales2"/></th>
                                <th><spring:message code="Exudado1"/></th>
                                <th><spring:message code="Exudado2"/></th>
                                <th><spring:message code="Petequias_mucosa1"/></th>
                                <th><spring:message code="Petequias_mucosa2"/></th>
                                <th><spring:message code="Tos1"/></th>
                                <th><spring:message code="Tos2"/></th>
                                <th><spring:message code="Rinorrea1"/></th>
                                <th><spring:message code="Rinorrea2"/></th>
                                <th><spring:message code="CongestionNasal1"/></th>
                                <th><spring:message code="CongestionNasal2"/></th>
                                <th><spring:message code="Otalgia1"/></th>
                                <th><spring:message code="Otalgia2"/></th>
                                <th><spring:message code="AleteoNasal1"/></th>
                                <th><spring:message code="AleteoNasal2"/></th>
                                <th><spring:message code="RespiracionRapida1"/></th>
                                <th><spring:message code="RespiracionRapida2"/></th>
                                <th><spring:message code="EstridorReposo1"/></th>
                                <th><spring:message code="EstridorReposo2"/></th>
                                <th><spring:message code="TirajeSubcostal1"/></th>
                                <th><spring:message code="TirajeSubcostal2"/></th>
                                <th><spring:message code="Sibilancias1"/></th>
                                <th><spring:message code="Sibilancias2"/></th>
                                <th><spring:message code="Crepitos1"/></th>
                                <th><spring:message code="Crepitos2"/></th>
                                <th><spring:message code="Roncos1"/></th>
                                <th><spring:message code="Roncos2"/></th>
                                <th><spring:message code="Disnea1"/></th>
                                <th><spring:message code="Disnea2"/></th>
                                <th><spring:message code="PocoApetito1"/></th>
                                <th><spring:message code="PocoApetito2"/></th>
                                <th><spring:message code="Nausea1"/></th>
                                <th><spring:message code="Nausea2"/></th>
                                <th><spring:message code="Vomito12horas1"/></th>
                                <th><spring:message code="Vomito12horas2"/></th>
                                <th><spring:message code="NumeroVomito12h1"/></th>
                                <th><spring:message code="NumeroVomito12h2"/></th>
                                <th><spring:message code="Diarrea1"/></th>
                                <th><spring:message code="Diarrea2"/></th>
                                <th><spring:message code="Hepatomegalia1"/></th>
                                <th><spring:message code="Hepatomegalia2"/></th>
                                <th><spring:message code="DolorAbdominal1"/></th>
                                <th><spring:message code="DolorAbdominal2"/></th>
                                <th><spring:message code="Artralgia1"/></th>
                                <th><spring:message code="Artralgia2"/></th>
                                <th><spring:message code="Mialgia1"/></th>
                                <th><spring:message code="Mialgia2"/></th>
                                <th><spring:message code="Lumbalgia1"/></th>
                                <th><spring:message code="Lumbalgia2"/></th>
                                <th><spring:message code="DolorCuello1"/></th>
                                <th><spring:message code="DolorCuello2"/></th>
                                <th><spring:message code="Edema1"/></th>
                                <th><spring:message code="Edema2"/></th>
                                <th><spring:message code="RahsLocalizado1"/></th>
                                <th><spring:message code="RahsLocalizado2"/></th>
                                <th><spring:message code="RahsGeneralizado1"/></th>
                                <th><spring:message code="RahsGeneralizado2"/></th>
                                <th><spring:message code="RashEritematoso1"/></th>
                                <th><spring:message code="RashEritematoso2"/></th>
                                <th><spring:message code="RahsMacular1"/></th>
                                <th><spring:message code="RahsMacular2"/></th>
                                <th><spring:message code="RashPapular1"/></th>
                                <th><spring:message code="RashPapular2"/></th>
                                <th><spring:message code="PielMoteada1"/></th>
                                <th><spring:message code="PielMoteada2"/></th>
                                <th><spring:message code="RuborFacial1"/></th>
                                <th><spring:message code="RuborFacial2"/></th>
                                <th><spring:message code="CianosisCentral1"/></th>
                                <th><spring:message code="CianosisCentral2"/></th>
                                <th><spring:message code="Ictericia1"/></th>
                                <th><spring:message code="Ictericia2"/></th>
                                <th><spring:message code="Imc1"/></th>
                                <th><spring:message code="Imc2"/></th>
                                <th><spring:message code="Obeso1"/></th>
                                <th><spring:message code="Obeso2"/></th>
                                <th><spring:message code="Sobrepeso1"/></th>
                                <th><spring:message code="Sobrepeso2"/></th>
                                <th><spring:message code="SospechaProblema1"/></th>
                                <th><spring:message code="SospechaProblema2"/></th>
                                <th><spring:message code="Normal1"/></th>
                                <th><spring:message code="Normal2"/></th>
                                <th><spring:message code="BajoPeso1"/></th>
                                <th><spring:message code="BajoPeso2"/></th>
                                <th><spring:message code="BajoPesoSevero1"/></th>
                                <th><spring:message code="BajoPesoSevero2"/></th>
                                <th><spring:message code="Categoria1"/></th>
                                <th><spring:message code="Categoria2"/></th>
                                <th><spring:message code="CambioCategoria1"/></th>
                                <th><spring:message code="CambioCategoria2"/></th>
                                <th><spring:message code="PruebaTorniquetePositiva1"/></th>
                                <th><spring:message code="PruebaTorniquetePositiva2"/></th>
                                <th><spring:message code="Petequia10Pt1"/></th>
                                <th><spring:message code="Petequia10Pt2"/></th>
                                <th><spring:message code="Petequia20Pt1"/></th>
                                <th><spring:message code="Petequia20Pt2"/></th>
                                <th><spring:message code="PielExtremidadesFrias1"/></th>
                                <th><spring:message code="PielExtremidadesFrias2"/></th>
                                <th><spring:message code="PalidezEnExtremidades1"/></th>
                                <th><spring:message code="PalidezEnExtremidades2"/></th>
                                <th><spring:message code="Epistaxis1"/></th>
                                <th><spring:message code="Epistaxis2"/></th>
                                <th><spring:message code="Gingivorragia1"/></th>
                                <th><spring:message code="Gingivorragia2"/></th>
                                <th><spring:message code="PetequiasEspontaneas1"/></th>
                                <th><spring:message code="PetequiasEspontaneas2"/></th>
                                <th><spring:message code="LlenadoCapilar2seg1"/></th>
                                <th><spring:message code="LlenadoCapilar2seg2"/></th>
                                <th><spring:message code="Cianosis1"/></th>
                                <th><spring:message code="Cianosis2"/></th>
                                <th><spring:message code="Hipermenorrea1"/></th>
                                <th><spring:message code="Hipermenorrea2"/></th>
                                <th><spring:message code="Hematemesis1"/></th>
                                <th><spring:message code="Hematemesis2"/></th>
                                <th><spring:message code="Hemoconcentracion1"/></th>
                                <th><spring:message code="Hemoconcentracion2"/></th>
                                <th><spring:message code="Hospitalizado1"/></th>
                                <th><spring:message code="Hospitalizado2"/></th>
                                <th><spring:message code="HospitalizadoEspecificar1"/></th>
                                <th><spring:message code="HospitalizadoEspecificar2"/></th>
                                <th><spring:message code="TransfusionSangre1"/></th>
                                <th><spring:message code="TransfusionSangre2"/></th>
                                <th><spring:message code="TransfusionEspecificar1"/></th>
                                <th><spring:message code="TransfusionEspecificar2"/></th>
                                <th><spring:message code="TomandoMedicamento1"/></th>
                                <th><spring:message code="TomandoMedicamento2"/></th>
                                <th><spring:message code="MedicamentoEspecificar1"/></th>
                                <th><spring:message code="MedicamentoEspecificar2"/></th>
                                <th><spring:message code="Bhc1"/></th>
                                <th><spring:message code="Bhc2"/></th>
                                <th><spring:message code="SerologiaArbovirus1"/></th>
                                <th><spring:message code="SerologiaArbovirus2"/></th>
                                <th><spring:message code="GotaGruesa1"/></th>
                                <th><spring:message code="GotaGruesa2"/></th>
                                <th><spring:message code="Ego1"/></th>
                                <th><spring:message code="Ego2"/></th>
                                <th><spring:message code="Egh1"/></th>
                                <th><spring:message code="Egh2"/></th>
                                <th><spring:message code="OtroExamenLab1"/></th>
                                <th><spring:message code="OtroExamenLab2"/></th>
                                <th><spring:message code="OtroExamanEspecificar1"/></th>
                                <th><spring:message code="OtroExamanEspecificar2"/></th>
                                <th><spring:message code="Acetaminofen1"/></th>
                                <th><spring:message code="Acetaminofen2"/></th>
                                <th><spring:message code="Amoxicilina1"/></th>
                                <th><spring:message code="Amoxicilina2"/></th>
                                <th><spring:message code="Dicloxacilina1"/></th>
                                <th><spring:message code="Dicloxacilina2"/></th>
                                <th><spring:message code="Penicilina1"/></th>
                                <th><spring:message code="Penicilina2"/></th>
                                <th><spring:message code="Furazolidona1"/></th>
                                <th><spring:message code="Furazolidona2"/></th>
                                <th><spring:message code="MetronidazolTinidazol1"/></th>
                                <th><spring:message code="MetronidazolTinidazol2"/></th>
                                <th><spring:message code="AlbendazolMebendazol1"/></th>
                                <th><spring:message code="AlbendazolMebendazol2"/></th>
                                <th><spring:message code="SueroOral1"/></th>
                                <th><spring:message code="SueroOral2"/></th>
                                <th><spring:message code="LiquidosIv1"/></th>
                                <th><spring:message code="LiquidosIv1"/></th>
                                <th><spring:message code="ReferenciaPorDengue1"/></th>
                                <th><spring:message code="ReferenciaPorDengue2"/></th>
                                <th><spring:message code="OtroTratamiento1"/></th>
                                <th><spring:message code="OtroTratamiento2"/></th>
                                <th><spring:message code="OtroTratamientoEspecificar1"/></th>
                                <th><spring:message code="OtroTratamientoEspecificar2"/></th>
                                <th><spring:message code="Planes1"/></th>
                                <th><spring:message code="Planes2"/></th>
                                <th><spring:message code="HistoriaClinica1"/></th>
                                <th><spring:message code="HistoriaClinica2"/></th>
                                <th><spring:message code="Diagnostico1"/></th>
                                <th><spring:message code="Diagnostico2"/></th>
                                <th><spring:message code="Diagnostico11"/></th>
                                <th><spring:message code="Diagnostico12"/></th>
                                <th><spring:message code="Diagnostico21"/></th>
                                <th><spring:message code="Diagnostico22"/></th>
                                <th><spring:message code="Diagnostico31"/></th>
                                <th><spring:message code="Diagnostico32"/></th>
                                <th><spring:message code="Diagnostico41"/></th>
                                <th><spring:message code="Diagnostico42"/></th>
                                <th><spring:message code="TelefonoEmergencia1"/></th>
                                <th><spring:message code="TelefonoEmergencia2"/></th>
                                <th><spring:message code="ProximaCita1"/></th>
                                <th><spring:message code="ProximaCita2"/></th>
                                <th><spring:message code="Medico1"/></th>
                                <th><spring:message code="Medico2"/></th>
                                <th><spring:message code="FechaMedico1"/></th>
                                <th><spring:message code="FechaMedico2"/></th>
                                <th><spring:message code="HoraMedico1"/></th>
                                <th><spring:message code="HoraMedico2"/></th>
                                <th><spring:message code="Enfermeria1"/></th>
                                <th><spring:message code="Enfermeria2"/></th>
                                <th><spring:message code="FechaEnfermeria1"/></th>
                                <th><spring:message code="FechaEnfermeria2"/></th>
                                <th><spring:message code="HoraEnfermeria1"/></th>
                                <th><spring:message code="HoraEnfermeria2"/></th>


                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                                </table>


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
    <!-- bootstrap datetimepicker -->
    <spring:url value="/resources/js/libs/bootstrap-datetimepicker/moment-with-locales.js" var="moment" />
    <script src="${moment}"></script>
    <spring:url value="/resources/js/libs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" var="datetimepicker" />
    <script src="${datetimepicker}"></script>
    <!-- JQUERY BLOCK UI -->
    <spring:url value="/resources/js/libs/jquery.blockui.min.js" var="jqueryBlockUi" />
    <script src="${jqueryBlockUi}"></script>
    <!-- Jquery validate-->
    <spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
    <script src="${validateJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
    <script src="${validateAMJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <script src="${jQValidationLoc}"></script>
    <!-- Datatables  -->
    <!-- Datatables  -->
    <spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
    <script src="${dataTableJs}" type="text/javascript"></script>

    <spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
    <script src="${dataTableJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <spring:url value="/hojaclinica/getHCDigitadas" var="getDifdoble"/>
    <spring:url value="/hojaclinica/editarHC/"
                var="editUrl"/>

<script>


    jQuery(document).ready(function() {


        $('.date-picker').datetimepicker({
            format: 'L',
            locale: "${lenguaje}",
            maxDate: new Date(),
            useCurrent: true
        });



        var edithoja = "EditarHC";
        var table = $('#lista_hojas1').DataTable({
            dom: "<'row'<'col-sm-12 col-md-12'B>>" +
                    "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            "bFilter": true,
            "bInfo": true,
            "bPaginate": true,
            "bDestroy": true,
            "responsive": true,
            "pageLength": 10,
            "bLengthChange": true,
            "buttons": [
                {
                    extend: 'excel'
                }
            ],
            "ajax":{
                url: "${getDifdoble}", // Change this URL to where your json data comes from
                type: "GET",
                data: function(d) {
                    d.fechaInicioCons = $("#fechaInicioCons").val();
                    d.fechaFinCons = $("#fechaFinCons").val();
                },
                dataSrc: ""

            },
            "columns": [
                { data: 'Corrección', defaultContent: "",
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        var editUrl =  '/a2cares/hojaclinica/editarHC/' + oData.codigo_participante1 + '/'+ oData.numero_hoja1 + '/';
                        // editUrl = 'editarHC/';
                        //  $(nTd).html('<a data-toggle="tooltip" data-placement="bottom" title= ' + viewMess + ' href=' + viewUrl + ' class="btn btn-outline-primary btn-sm"><i class="fa fa-search"></i></a> <a data-toggle="tooltip" data-placement="bottom" title= ' + editMess + ' href=' + editUrl + ' class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>');
                        $(nTd).html('<a data-toggle="tooltip" data-placement="bottom" title= ' + edithoja + ' href=' + editUrl + ' class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a> ');
                    }
                },
                { data: 'codigo_participante1', defaultContent: ""},
                { data: 'codigo_participante2', defaultContent: ""},
                { data: 'numero_hoja1', defaultContent: ""},
                { data: 'numero_hoja2', defaultContent: ""},
                { data: 'codigo_supervisor1', defaultContent: ""},
                { data: 'codigo_supervisor2', defaultContent: ""},
                { data: 'fecha_consulta1', defaultContent: ""},
                { data: 'fecha_consulta2', defaultContent: ""},
                { data: 'hora_consulta1', defaultContent: ""},
                { data: 'hora_consulta2', defaultContent: ""},
                { data: 'peso_kg1', defaultContent: ""},
                { data: 'peso_kg2', defaultContent: ""},
                { data: 'talla_cm1', defaultContent: ""},
                { data: 'talla_cm2', defaultContent: ""},
                { data: 'presion1', defaultContent: ""},
                { data: 'presion2', defaultContent: ""},
                { data: 'fcia_card1', defaultContent: ""},
                { data: 'fcia_card2', defaultContent: ""},
                { data: 'temperaturac1', defaultContent: ""},
                { data: 'temperaturac2', defaultContent: ""},
                { data: 'saturacion1', defaultContent: ""},
                { data: 'saturacion2', defaultContent: ""},
                { data: 'hora_inicio_consulta1', defaultContent: ""},
                { data: 'hora_inicio_consulta2', defaultContent: ""},
                { data: 'consulta1', defaultContent: ""},
                { data: 'consulta2', defaultContent: ""},
                { data: 'lugar_atencion1', defaultContent: ""},
                { data: 'lugar_atencion2', defaultContent: ""},
                { data: 'presion_medico1', defaultContent: ""},
                { data: 'presion_medico2', defaultContent: ""},
                { data: 'temperatura_medico1', defaultContent: ""},
                { data: 'temperatura_medico2', defaultContent: ""},
                { data: 'fcia_resp1', defaultContent: ""},
                { data: 'fcia_resp2', defaultContent: ""},
                { data: 'fcia_card_medico1', defaultContent: ""},
                { data: 'fcia_card_medico2', defaultContent: ""},
                { data: 'saturaciono2_medico1', defaultContent: ""},
                { data: 'saturaciono2_medico2', defaultContent: ""},
                { data: 'fis1', defaultContent: ""},
                { data: 'fis2', defaultContent: ""},
                { data: 'fif1', defaultContent: ""},
                { data: 'fif2', defaultContent: ""},
                { data: 'ult_dia_fiebre1', defaultContent: ""},
                { data: 'ult_dia_fiebre2', defaultContent: ""},
                { data: 'hora_ult_dia_fiebre1', defaultContent: ""},
                { data: 'hora_ult_dia_fiebre2', defaultContent: ""},
                { data: 'ult_dosis_antipiretico1', defaultContent: ""},
                { data: 'ult_dosis_antipiretico2', defaultContent: ""},
                { data: 'hora_ult_dosis_antipiretico1', defaultContent: ""},
                { data: 'hora_ult_dosis_antipiretico2', defaultContent: ""},
                { data: 'fiebre1', defaultContent: ""},
                { data: 'fiebre2', defaultContent: ""},
                { data: 'asomnoliento1', defaultContent: ""},
                { data: 'asomnoliento2', defaultContent: ""},
                { data: 'mal_estado1', defaultContent: ""},
                { data: 'mal_estado2', defaultContent: ""},
                { data: 'perdida_consciencia1', defaultContent: ""},
                { data: 'perdida_consciencia2', defaultContent: ""},
                { data: 'inquieto1', defaultContent: ""},
                { data: 'inquieto2', defaultContent: ""},
                { data: 'convulsiones1', defaultContent: ""},
                { data: 'convulsiones2', defaultContent: ""},
                { data: 'letargia1', defaultContent: ""},
                { data: 'letargia2', defaultContent: ""},
                { data: 'dolor_cabeza1', defaultContent: ""},
                { data: 'dolor_cabeza2', defaultContent: ""},
                { data: 'conjuntivitis1', defaultContent: ""},
                { data: 'conjuntivitis2', defaultContent: ""},
                { data: 'hemorragia_suconjuntival1', defaultContent: ""},
                { data: 'hemorragia_suconjuntival2', defaultContent: ""},
                { data: 'dolor_retroocular1', defaultContent: ""},
                { data: 'dolor_retroocular2', defaultContent: ""},
                { data: 'dolor_garganta1', defaultContent: ""},
                { data: 'dolor_garganta2', defaultContent: ""},
                { data: 'eritema1', defaultContent: ""},
                { data: 'eritema2', defaultContent: ""},
                { data: 'adenopatias_cervicales1', defaultContent: ""},
                { data: 'adenopatias_cervicales2', defaultContent: ""},
                { data: 'exudado1', defaultContent: ""},
                { data: 'exudado2', defaultContent: ""},
                { data: 'petequias_mucosa1', defaultContent: ""},
                { data: 'petequias_mucosa2', defaultContent: ""},
                { data: 'tos1', defaultContent: ""},
                { data: 'tos2', defaultContent: ""},
                { data: 'rinorrea1', defaultContent: ""},
                { data: 'rinorrea2', defaultContent: ""},
                { data: 'congestionNasal1', defaultContent: ""},
                { data: 'congestionNasal2', defaultContent: ""},
                { data: 'otalgia1', defaultContent: ""},
                { data: 'otalgia2', defaultContent: ""},
                { data: 'aleteoNasal1', defaultContent: ""},
                { data: 'aleteoNasal2', defaultContent: ""},
                { data: 'respiracionRapida1', defaultContent: ""},
                { data: 'respiracionRapida2', defaultContent: ""},
                { data: 'estridorReposo1', defaultContent: ""},
                { data: 'estridorReposo2', defaultContent: ""},
                { data: 'tirajeSubcostal1', defaultContent: ""},
                { data: 'tirajeSubcostal2', defaultContent: ""},
                { data: 'sibilancias1', defaultContent: ""},
                { data: 'sibilancias2', defaultContent: ""},
                { data: 'crepitos1', defaultContent: ""},
                { data: 'crepitos2', defaultContent: ""},
                { data: 'roncos1', defaultContent: ""},
                { data: 'roncos2', defaultContent: ""},
                { data: 'disnea1', defaultContent: ""},
                { data: 'disnea2', defaultContent: ""},
                { data: 'pocoApetito1', defaultContent: ""},
                { data: 'pocoApetito2', defaultContent: ""},
                { data: 'nausea1', defaultContent: ""},
                { data: 'nausea2', defaultContent: ""},
                { data: 'vomito12horas1', defaultContent: ""},
                { data: 'vomito12horas2', defaultContent: ""},
                { data: 'numeroVomito12h1', defaultContent: ""},
                { data: 'numeroVomito12h2', defaultContent: ""},
                { data: 'diarrea1', defaultContent: ""},
                { data: 'diarrea2', defaultContent: ""},
                { data: 'hepatomegalia1', defaultContent: ""},
                { data: 'hepatomegalia2', defaultContent: ""},
                { data: 'dolorAbdominal1', defaultContent: ""},
                { data: 'dolorAbdominal2', defaultContent: ""},
                { data: 'artralgia1', defaultContent: ""},
                { data: 'artralgia2', defaultContent: ""},
                { data: 'mialgia1', defaultContent: ""},
                { data: 'mialgia2', defaultContent: ""},
                { data: 'lumbalgia1', defaultContent: ""},
                { data: 'lumbalgia2', defaultContent: ""},
                { data: 'dolorCuello1', defaultContent: ""},
                { data: 'dolorCuello2', defaultContent: ""},
                { data: 'edema1', defaultContent: ""},
                { data: 'edema2', defaultContent: ""},
                { data: 'rahsLocalizado1', defaultContent: ""},
                { data: 'rahsLocalizado2', defaultContent: ""},
                { data: 'rahsGeneralizado1', defaultContent: ""},
                { data: 'rahsGeneralizado2', defaultContent: ""},
                { data: 'rashEritematoso1', defaultContent: ""},
                { data: 'rashEritematoso2', defaultContent: ""},
                { data: 'rahsMacular1', defaultContent: ""},
                { data: 'rahsMacular2', defaultContent: ""},
                { data: 'rashPapular1', defaultContent: ""},
                { data: 'rashPapular2', defaultContent: ""},
                { data: 'pielMoteada1', defaultContent: ""},
                { data: 'pielMoteada2', defaultContent: ""},
                { data: 'ruborFacial1', defaultContent: ""},
                { data: 'ruborFacial2', defaultContent: ""},
                { data: 'cianosisCentral1', defaultContent: ""},
                { data: 'cianosisCentral2', defaultContent: ""},
                { data: 'ictericia1', defaultContent: ""},
                { data: 'ictericia2', defaultContent: ""},
                { data: 'imc1', defaultContent: ""},
                { data: 'imc2', defaultContent: ""},
                { data: 'obeso1', defaultContent: ""},
                { data: 'obeso2', defaultContent: ""},
                { data: 'sobrepeso1', defaultContent: ""},
                { data: 'sobrepeso2', defaultContent: ""},
                { data: 'sospechaProblema1', defaultContent: ""},
                { data: 'sospechaProblema2', defaultContent: ""},
                { data: 'normal1', defaultContent: ""},
                { data: 'normal2', defaultContent: ""},
                { data: 'bajoPeso1', defaultContent: ""},
                { data: 'bajoPeso2', defaultContent: ""},
                { data: 'bajoPesoSevero1', defaultContent: ""},
                { data: 'bajoPesoSevero2', defaultContent: ""},
                { data: 'categoria1', defaultContent: ""},
                { data: 'categoria2', defaultContent: ""},
                { data: 'cambioCategoria1', defaultContent: ""},
                { data: 'cambioCategoria2', defaultContent: ""},
                { data: 'pruebaTorniquetePositiva1', defaultContent: ""},
                { data: 'pruebaTorniquetePositiva2', defaultContent: ""},
                { data: 'petequia10Pt1', defaultContent: ""},
                { data: 'petequia10Pt2', defaultContent: ""},
                { data: 'petequia20Pt1', defaultContent: ""},
                { data: 'petequia20Pt2', defaultContent: ""},
                { data: 'pielExtremidadesFrias1', defaultContent: ""},
                { data: 'pielExtremidadesFrias2', defaultContent: ""},
                { data: 'palidezEnExtremidades1', defaultContent: ""},
                { data: 'palidezEnExtremidades2', defaultContent: ""},
                { data: 'epistaxis1', defaultContent: ""},
                { data: 'epistaxis2', defaultContent: ""},
                { data: 'gingivorragia1', defaultContent: ""},
                { data: 'gingivorragia2', defaultContent: ""},
                { data: 'petequiasEspontaneas1', defaultContent: ""},
                { data: 'petequiasEspontaneas2', defaultContent: ""},
                { data: 'llenadoCapilar2seg1', defaultContent: ""},
                { data: 'llenadoCapilar2seg2', defaultContent: ""},
                { data: 'cianosis1', defaultContent: ""},
                { data: 'cianosis2', defaultContent: ""},
                { data: 'hipermenorrea1', defaultContent: ""},
                { data: 'hipermenorrea2', defaultContent: ""},
                { data: 'hematemesis1', defaultContent: ""},
                { data: 'hematemesis2', defaultContent: ""},
                { data: 'hemoconcentracion1', defaultContent: ""},
                { data: 'hemoconcentracion2', defaultContent: ""},
                { data: 'hospitalizado1', defaultContent: ""},
                { data: 'hospitalizado2', defaultContent: ""},
                { data: 'hospitalizadoEspecificar1', defaultContent: ""},
                { data: 'hospitalizadoEspecificar2', defaultContent: ""},
                { data: 'transfusionSangre1', defaultContent: ""},
                { data: 'transfusionSangre2', defaultContent: ""},
                { data: 'transfusionEspecificar1', defaultContent: ""},
                { data: 'transfusionEspecificar2', defaultContent: ""},
                { data: 'tomandoMedicamento1', defaultContent: ""},
                { data: 'tomandoMedicamento2', defaultContent: ""},
                { data: 'medicamentoEspecificar1', defaultContent: ""},
                { data: 'medicamentoEspecificar2', defaultContent: ""},
                { data: 'bhc1', defaultContent: ""},
                { data: 'bhc2', defaultContent: ""},
                { data: 'serologiaArbovirus1', defaultContent: ""},
                { data: 'serologiaArbovirus2', defaultContent: ""},
                { data: 'gotaGruesa1', defaultContent: ""},
                { data: 'gotaGruesa2', defaultContent: ""},
                { data: 'ego1', defaultContent: ""},
                { data: 'ego2', defaultContent: ""},
                { data: 'egh1', defaultContent: ""},
                { data: 'egh2', defaultContent: ""},
                { data: 'otroExamenLab1', defaultContent: ""},
                { data: 'otroExamenLab2', defaultContent: ""},
                { data: 'otroExamanLabEspecificar1', defaultContent: ""},
                { data: 'otroExamanLabEspecificar2', defaultContent: ""},
                { data: 'acetaminofen1', defaultContent: ""},
                { data: 'acetaminofen2', defaultContent: ""},
                { data: 'amoxicilina1', defaultContent: ""},
                { data: 'amoxicilina2', defaultContent: ""},
                { data: 'dicloxacilina1', defaultContent: ""},
                { data: 'dicloxacilina2', defaultContent: ""},
                { data: 'penicilina1', defaultContent: ""},
                { data: 'penicilina2', defaultContent: ""},
                { data: 'furazolidona1', defaultContent: ""},
                { data: 'furazolidona2', defaultContent: ""},
                { data: 'metronidazolTinidazol1', defaultContent: ""},
                { data: 'metronidazolTinidazol2', defaultContent: ""},
                { data: 'albendazolMebendazol1', defaultContent: ""},
                { data: 'albendazolMebendazol2', defaultContent: ""},
                { data: 'sueroOral1', defaultContent: ""},
                { data: 'sueroOral2', defaultContent: ""},
                { data: 'liquidosiv1', defaultContent: ""},
                { data: 'liquidosiv2', defaultContent: ""},
                { data: 'referenciaPordengue1', defaultContent: ""},
                { data: 'referenciaPordengue2', defaultContent: ""},
                { data: 'otroTratamiento1', defaultContent: ""},
                { data: 'otroTratamiento2', defaultContent: ""},
                { data: 'otroTratamientoEspecificar1', defaultContent: ""},
                { data: 'otroTratamientoEspecificar2', defaultContent: ""},
                { data: 'planes1', defaultContent: ""},
                { data: 'planes2', defaultContent: ""},
                { data: 'historiaClinica1', defaultContent: ""},
                { data: 'historiaClinica2', defaultContent: ""},
                { data: 'diagnostico1', defaultContent: ""},
                { data: 'diagnostico2', defaultContent: ""},
                { data: 'diagnostico11', defaultContent: ""},
                { data: 'diagnostico12', defaultContent: ""},
                { data: 'diagnostico21', defaultContent: ""},
                { data: 'diagnostico22', defaultContent: ""},
                { data: 'diagnostico31', defaultContent: ""},
                { data: 'diagnostico32', defaultContent: ""},
                { data: 'diagnostico41', defaultContent: ""},
                { data: 'diagnostico42', defaultContent: ""},
                { data: 'telefonoEmergencia1', defaultContent: ""},
                { data: 'telefonoEmergencia2', defaultContent: ""},
                { data: 'proximaCita1', defaultContent: ""},
                { data: 'proximaCita2', defaultContent: ""},
                { data: 'medico1', defaultContent: ""},
                { data: 'medico2', defaultContent: ""},
                { data: 'fechaMedico1', defaultContent: ""},
                { data: 'fechaMedico2', defaultContent: ""},
                { data: 'horaMedico1', defaultContent: ""},
                { data: 'horaMedico2', defaultContent: ""},
                { data: 'enfermeria1', defaultContent: ""},
                { data: 'enfermeria2', defaultContent: ""},
                { data: 'fechaEnfermeria1', defaultContent: ""},
                { data: 'fechaEnfermeria2', defaultContent: ""},
                { data: 'horaEnfermeria1', defaultContent: ""},
                { data: 'horaEnfermeria2', defaultContent: ""}





            ]
        });

        $("#fechaInicioCons").on("dp.change", function (e) {
            $('#fechaFinCons').data("DateTimePicker").minDate(e.date);
        });
        $("#fechaFinCons").on("dp.change", function (e) {
            $('#fechaInicioCons').data("DateTimePicker").maxDate(e.date);
        });



    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();


    var formSearch = $('#search-form');
    formSearch.validate({
        focusInvalid: false, // do not focus the last invalid input
        rules: {
            fechaInicioCons: {required: function () {
                return $('#fechaFinCons').val().length > 0;
            }},
            fechaFinCons: {required: function () {
                return $('#fechaInicioCons').val().length > 0;
            }}
        },

        submitHandler: function (form) {
            console.log("buscar");
            search();
        }
    });

    function search()
    {
        table.ajax.reload();
    }
   /* $('#lista_cartas1').on('click', 'td.editor-enabled', function (e) {
        e.preventDefault();
        console.log(table.cell( this ).data());
        var objeto= table.cell( this ).data();
        if (objeto.enabled){
            mostrarDeshabilitar("${disableUrl}"+objeto.username);
        } else {
            mostrarHabilitar("${enableUrl}"+objeto.username);
        }
    });*/
        function ejecutarAccion() {
            window.location.href = $('#accionUrl').val();
        }
    } );
</script>
</body>
</html>