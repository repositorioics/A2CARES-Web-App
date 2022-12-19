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
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script language="JavaScript" type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <link href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" rel="Stylesheet">

    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js" ></script>

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
<!-- BEGIN HEADER -->
<jsp:include page="../fragments/bodyHeader.jsp" />
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">

<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->

			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					<spring:message code="title" /> <small><spring:message code="editdata" /></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                            <i class="fa fa-angle-right"></i> <a href="<spring:url value="/entomologia/editdata/" htmlEscape="true "/>"><spring:message code="editdata" /></a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN TABLE PORTLET-->
                    <div class="portlet">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-list"></i>
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form action="#" autocomplete="off" id="download-form" class="form-horizontal">
                                <div class="form-body">
                                    <div class="alert alert-info">
                                        <spring:message code="editdata.help" />
                                    </div>
                                    <div class="alert alert-danger display-hide">
                                        <button class="close" data-close="alert"></button>
                                        <spring:message code="form.errors" />
                                    </div>
                                    <div class="row">
                                        <div class="col col-sm-12 col-md-6 col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="subjectId" />
                                                    <span class="required">
                                                     *
                                                </span>
                                                </label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <input id="recordId" name="recordId" type="text" value="" class="form-control"/>
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-sort-alpha-asc"></i>
                                                            </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-sm-12 col-md-6 col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="form" />
                                                <span class="required">
                                                     *
                                                </span>
                                                </label>
                                                <div class="col-md-6">
                                                    <select class="form-control" id="zpform" name="zpform">
                                                        <option value=""><spring:message code="lbl.select" /> ...</option>
                                                        <option value="ento_cuestionario_hogar"><spring:message code="ento_cuestionario_hogar" /></option>
                                                        <option value="ento_cuestionario_hogar_pob"><spring:message code="ento_cuestionario_hogar_pob" /></option>
                                                        <option value="ento_cuestionario_punto_clave"><spring:message code="ento_cuestionario_punto_clave" /></option>

                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col col-sm-12 col-md-6 col-lg-6" id="divEventosRCap">
                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="event" />
                                                    <span class="required">
                                                     *
                                                </span>
                                                </label>
                                                <div class="col-md-6">
                                                    <select class="form-control" id="ento_cuestionario_hogar" name="zpEventRC">
                                                        <option value=""><spring:message code="lbl.select" /> ...</option>
                                                        <c:forEach items="${eventos}" var="evento">
                                                            <option value="${evento}"><spring:message code="${evento}" /></option>
                                                        </c:forEach>
                                                        <option value="any"><spring:message code="lbl.any" /> ...</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-sm-12 col-md-6 col-lg-6" id="divEventosUS">
                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="event" />
                                                    <span class="required">
                                                     *
                                                </span>
                                                </label>
                                                <div class="col-md-6">
                                                    <select class="form-control" id="zpEventUS" name="zpEventUS">
                                                        <option value=""><spring:message code="lbl.select" /> ...</option>
                                                        <option value="SCREENING"><spring:message code="us_event_screening" /></option>
                                                        <option value="TRI2"><spring:message code="us_event_tri2" /></option>
                                                        <option value="TRI3"><spring:message code="us_event_tri3" /></option>
                                                        <option value="UNSCHED"><spring:message code="us_event_unsched" /></option>
                                                        <option value="any"><spring:message code="lbl.any" /> ...</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-sm-12 col-md-6 col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="entityProperty" />
                                                    <span class="required">
                                                     *
                                                </span>
                                                </label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <input id="propiedad" name="propiedad" type="text" value="" class="form-control ui-autocomplete-input"/>
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-sort-alpha-asc"></i>
                                                            </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col col-sm-12 col-md-6 col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="entityPropertyNewValue" />
                                                    <span class="required">
                                                     *
                                                </span>
                                                </label>
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <input id="valor" name="valor" type="text" value="" class="form-control"/>
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-sort-alpha-asc"></i>
                                                            </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col col-sm-12 col-md-6 col-lg-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-4"><spring:message code="setnull"/></label>
                                                <div class="col-md-6">
                                                        <label class="checkbox">
                                                            <input type="checkbox" name="setNull" id="setNull">
                                                            <i></i></label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="col-md-offset-6 col-md-6">
                                        <button id="run" type="submit" class="btn btn-success"><spring:message code="run" /></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- END TABLE PORTLET-->
                </div>
            </div>
			<!-- END PAGE CONTENT -->
		</div>
	</div>
</div>
<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<jsp:include page="../fragments/bodyFooter.jsp" />
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="../fragments/corePlugins.jsp" />

<!-- BEGIN PAGE LEVEL PLUGINS -->

<!-- DATA TABLE-->
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<spring:url value="/resources/plugins/data-tables/jquery.dataTables.js" var="jQueryDataTables" />
<script type="text/javascript" src="${jQueryDataTables}"></script>
<spring:url value="/resources/plugins/data-tables/DT_bootstrap.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/js/dataTables.tableTools.js" var="dataTablesTT" />
<script type="text/javascript" src="${dataTablesTT}"></script>
<spring:url value="/resources/plugins/data-tables/TableTools/swf/copy_csv_xls_pdf.swf" var="dataTablesTTSWF" />
<spring:url value="/resources/plugins/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<!-- Select 2-->
<spring:url value="/resources/plugins/select2/select2.min.js" var="Select2" />
<script type="text/javascript" src="${Select2}"></script>
<spring:url value="/resources/plugins/select2/select2_locale_{language}.js" var="Select2Loc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${Select2Loc}"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>
<spring:url value="/resources/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>

<!-- VALIDATOR-->
<spring:url value="/resources/js/libs/jquery.validate.js" var="jQValidation" />
<script type="text/javascript" src="${jQValidation}"></script>
<spring:url value="/resources/plugins/jquery-validation/dist/additional-methods.min.js" var="jQValidationAdd" />
<script type="text/javascript" src="${jQValidationAdd}"></script>
<spring:url value="/resources/plugins/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
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
<spring:url value="/resources/js/views/entomologia/process-editdata.js" var="procEDScript" />
<script src="${procEDScript}" type="text/javascript"></script>

<spring:url value="/entomologia/editdata/getPropertiesName" var="getPropertiesName"/>
<spring:url value="/entomologia/editdata/updatePropertie" var="updateUrl"/>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    $(function () {
        $("li.entomologia").removeClass("open").addClass("active");
        $("li.entomologia").removeClass("addres").addClass("active");
    });
</script>
<script>
    $(document).ready(function(){
		//App.init();
		var parametros = {
            "getPropertiesName" : "${getPropertiesName}",
            "updateUrl" : "${updateUrl}"
        };

        EditData.init(parametros);

        $("#divEventosUS").hide();
	});

</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>