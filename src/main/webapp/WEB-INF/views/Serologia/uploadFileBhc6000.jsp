<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body>
<div class="page-loader">
    <div class="bg-primary"></div>
</div>

<!-- Layout wrapper -->
<div class="layout-wrapper layout-2">
    <jsp:include page="../fragments/bodyHeader.jsp" />
    <div class="app-body">
        <jsp:include page="../fragments/sideBar.jsp" />
        <!-- Layout container -->
        <div class="main">
            <!-- Layout navbar -->

            <!-- / Layout navbar -->
            <!-- Layout content -->
            <div class="layout-content">
                <!-- Content -->
                <div class="container-fluid flex-grow-1 container-p-y">
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <h4 class="font-weight-bold py-3 mb-4">
                        <spring:message code="Upload Excel BC6000" />
                        <div class="text-muted text-tiny mt-1"><small class="font-weight-normal"><fmt:formatDate value="${now}" dateStyle="long"/></small></div>
                    </h4>

                    <div class="card mb-4">
                        <h6 class="card-header">
                            <i class="fa fa-file-upload"></i>&nbsp;<strong><spring:message code="Cargar Archivo Exportado Excel BC6000" /></strong>
                        </h6>
                        <div class="card-body">
                            <spring:url value="/hojaclinica/uploadEntityFile/" var="uploadUrl"></spring:url>
                            <form method="POST" action="${fn:escapeXml(uploadUrl)}" enctype="multipart/form-data">
                                <div class="d-flex align-items-start">
                                    <label class="btn rounded-pill btn-outline-primary">
                                        <i class="fa fa-hand-point-up"></i> <spring:message code="Seleccione archivo Excel a Cargar" /><input id="fileinput" name="file" type="file" accept=".csv" style="display: none;">
                                    </label>
                                    <span id="selected_filename"><spring:message code="No hay archivo seleccionado" /></span>
                                    <div class="d-flex justify-content-around">
                                        <button type="submit" data-toggle="tooltip" data-placement="bottom" title="<spring:message code="uploadUpdateToolTip" />" class="btn rounded-pill btn-outline-primary"><i class="fa fa-check"></i> <spring:message code="Insertar Archivo Excel BC6000 a BHC A2CARES" /></button>
                                    </div>
                                </div>
                            </form>

                        </div>



                    </div>
                    <div class="d-flex align-items-start">
                        <table id="table" class="table table-striped table-bordered datatable"
                               width="100%" hidden="true">
                            <thead>
                            <tr>
                                <th><spring:message code="Specimens"/></th>
                                <th><spring:message code="SpecimenId"/></th>
                                <th><spring:message code="Volume"/></th>
                            </tr>
                            </thead>

                        </table>
                    </div>
                    <div class="d-flex align-items-stretch">
                        <table id="table1" class="table table-striped table-bordered datatable"
                               width="100%" hidden="true">
                            <thead>
                            <tr>
                                <th><spring:message code="Specimens"/></th>
                                <th><spring:message code="SpecimenId"/></th>
                                <th><spring:message code="Volume"/></th>
                                <th><spring:message code="InStorage"/></th>
                                <th><spring:message code="Frezzer "/></th>
                                <th><spring:message code="Rack"/></th>
                                <th><spring:message code="Box"/></th>
                                <th><spring:message code="Position"/></th>

                            </tr>
                            </thead>

                        </table>
                    </div>
                </div>
                <!-- / Content -->

            </div>
            <!-- Layout content -->
        </div>
        <!-- / Layout container -->
    </div>
</div>
<!-- / Layout wrapper -->

<!-- Bootstrap and necessary plugins -->
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />



<!-- Lenguaje -->
<c:choose>
    <c:when test="${cookie.eSampleManagerLang.value == null}">
        <c:set var="lenguaje" value="en"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eSampleManagerLang.value}"/>
    </c:otherwise>
</c:choose>

<!-- Custom scripts required by this view -->

<script>

    jQuery(document).ready(function() {
        $("li.storage").addClass("open");
        $("li.storage").addClass("active");
        $("li.fileuploadexcel").addClass("active");
        $('#table').DataTable({
            dom: 'lBfrtip',
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            "bFilter": false,
            "bInfo": false,
            "bPaginate": false,
            "bDestroy": false,
            "responsive": false,
            "pageLength": 5,
            "lengthMenu": [5, 10, 20, 50, 100],
            "bLengthChange": false,
            "responsive": false,
            "buttons": [
                {
                    extend: 'excel',
                    text: "Download Excel Template to Update Volume",
                    className: "btn rounded-pill btn-outline-primary"
                }
            ]
        });

        $('#table1').DataTable({
            dom: 'lBfrtip',
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            "bFilter": false,
            "bInfo": false,
            "bPaginate": false,
            "bDestroy": false,
            "responsive": false,
            "pageLength": 5,
            "lengthMenu": [5, 10, 20, 50, 100],
            "bLengthChange": false,
            "responsive": false,
            "buttons": [
                {
                    extend: 'excel',
                    text: "Download Excel Template to Update Volume and Locations",
                    className: "btn rounded-pill btn-outline-primary"
                }
            ]
        });
    });
    $('#fileinput').change(function() {
        $('#selected_filename').text($('#fileinput')[0].files[0].name);
    });

    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();

</script>


</body>
</html>
