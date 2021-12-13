<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<jsp:include page="../../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>
    <style>
        div.dt-buttons {
            float: right !important;
        }
    </style>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
	<jsp:include page="../../fragments/bodyHeader.jsp" />
    <div class="app-body">
        <jsp:include page="../../fragments/sideBar.jsp" />
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
                            <spring:message code="catalogs" />
                        </h3>
                    </div>
                    <form action="#" autocomplete="off" id="enter-form">
                        <spring:url value="/admin/catalogs/saveEntity/" var="saveUrl" />
                        <spring:url value="/admin/catalogs/${entidad.messageKey}/" var="listUrl" />
                        <div class="card-block">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                        <div class="col-lg-6 col-md-6 col-sm-12">
                                            <div class="form-group">
                                                <label class="form-control-label" for="catRoot"><spring:message code="catRoot" />
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fas fa-key"></i>
                                                            </span>
                                                    <input type="text" class="form-control time-picker" id="catRoot" name="catRoot" readonly
                                                           value="${entidad.messageKey}" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                        <div class="col-lg-6 col-md-6 col-sm-12">
                                            <div class="form-group">
                                                <label class="form-control-label" for="messageKey"><spring:message code="messageKey" />
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fas fa-key"></i>
                                                            </span>
                                                    <input type="text" class="form-control time-picker" id="messageKey" name="messageKey" value="" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                        <div class="col-lg-6 col-md-6 col-sm-12">
                                            <div class="form-group">
                                                <label class="form-control-label" for="catKey"><spring:message code="catKey" />
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fas fa-sort-alpha-up"></i>
                                                            </span>
                                                    <input type="text" class="form-control time-picker" id="catKey" name="catKey" value="" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                        <div class="col-lg-6 col-md-6 col-sm-12">
                                            <div class="form-group">
                                                <label class="form-control-label" for="spanish"><spring:message code="spanish" />
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fas fa-sort-alpha-up"></i>
                                                            </span>
                                                    <input type="text" class="form-control time-picker" id="spanish" name="spanish" value="" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                        <div class="col-lg-6 col-md-6 col-sm-8">
                                            <div class="form-group">
                                                <label class="form-control-label" for="order"><spring:message code="order" />
                                                    <span class="required">*</span>
                                                </label>
                                                <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fas fa-sort-numeric-up"></i>
                                                            </span>
                                                    <input type="text" class="form-control time-picker" id="order" name="order" value="" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                        <div class="col-lg-3 col-md-3 col-sm-4">
                                            <div class="form-group">
                                                <button class="btn btn-success  btn-ladda btn-block btn-lg" data-toggle="tooltip"
                                                        type="submit" id="btnModificar" title="<spring:message code="saveRecord" />">
                                                    <i class="fa fa-save" aria-hidden="true"></i> <spring:message code="save" />
                                                </button>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-4">
                                            <div class="form-group">
                                                <a href="${fn:escapeXml(listUrl)}" data-toggle="tooltip" title="<spring:message code="cancelRecord" />" data-placement="top"
                                                   class="btn btn-danger  btn-ladda btn-block btn-lg">
                                                    <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                    <spring:message code="cancel" />
                                                </a>
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-2"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
            <!-- /.conainer-fluid -->
        </div>
    </div>
    <jsp:include page="../../fragments/bodyFooter.jsp" />
    <jsp:include page="../../fragments/corePlugins.jsp" />
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
    <!-- Jquery validate-->
    <spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
    <script src="${validateJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
    <script src="${validateAMJs}" type="text/javascript"></script>
    <spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
        <spring:param name="language" value="${lenguaje}" />
    </spring:url>
    <script src="${jQValidationLoc}"></script>
    <spring:url value="/resources/js/views/catalogs/process-catalog.js" var="processCatalog" />
    <script src="${processCatalog}"></script>
    <c:set var="successmessage"><spring:message code="process.success" /></c:set>
    <c:set var="errormessage"><spring:message code="process.errors" /></c:set>
<script>
    jQuery(document).ready(function() {
        var parametros = {saveUrl: "${saveUrl}", successmessage: "${successmessage}",
            errormessage: "${errormessage}",
            listUrl: "${listUrl}"
        };
        ProcessCatalog.init(parametros);
    });

    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();
</script>
</body>
</html>