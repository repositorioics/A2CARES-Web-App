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
                    <i class="fa fa-angle-right"></i> <a href="<spring:url value="/hojaclinica/getBHCEnviadas" htmlEscape="true "/>"><spring:message code="Procesamiento CSFV" /> <spring:message code="BHC A2CARES" /></a>
                </li>
            </ol>
            <div class="container-fluid">
                <div class="card">
                    <div class="card-header">
                        <h3 class="page-title">
                            <i class="fa fa-database"></i>&nbsp;<strong><spring:message code="Marcar BHC Enviadas - Procesadas CSSFV" /></strong>
                        </h3>
                    </div>
                    <form action="#" autocomplete="off" id="search-form" name="search-form" class="form-horizontal">
                        <div class="card-block">
                            <div class="row">
                    <div class="col-lg-3 col-md-3 col-sm-12">
                        <div class="form-group">
                            <label class="form-control-label" for="fechaInicioCons"><spring:message code="Fecha_Envío" />
                                <span class="required">*</span>
                            </label>
                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fas fa-calendar-alt"></i>
                                            </span>
                                <input type="text" class="form-control date-picker" id="fechaInicioCons"  value="${fecha}"  name="fechaInicioCons" placeholder="<spring:message code="fecha_holder" />"/>
                            </div>
                        </div>
                    </div>

                                <div class="col-lg-3 col-md-3 col-sm-12">
                                    <label class="form-control-label" for="nEnvios"><spring:message code="Número Envío:" />
                                        <span class="required">*</span>
                                    </label>
                                    <div class="form-group">
                                        <select id="nEnvios" name="nEnvios" class="form-control" required="required">
                                            <option selected value=""><spring:message code="select" />...</option>
                                            <c:forEach items="${numero_envio}" var="n">
                                                <option value="${n.catKey}">${n.spanish}</option>
                                            </c:forEach>
                                        </select>

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
                                    <th><spring:message code="Procesada SI"/></th>
                                    <th><spring:message code="Procesada NO"/></th>
                                    <th><spring:message code="BHC_ID"/></th>
                                <th><spring:message code="Código_Participante"/></th>
                                <th><spring:message code="Fecha Envío"/></th>
                                <th><spring:message code="Volúmen"/></th>
                                <th><spring:message code="Viaje"/></th>
                                <th><spring:message code="ProcesadaCSFV"/></th>
                                <th><spring:message code="Observación"/></th>
                                <th><spring:message code="Usuario Registro"/></th>
                                <th><spring:message code="Estudio"/></th>
                                 <th><spring:message code="Puesto"/></th>


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
    <spring:url value="/hojaclinica/getBHCEnviadas" var="getDifdoble"/>
    <spring:url value="/hojaclinica/editarBHC1/"
                var="editUrl"/>

<script>


    jQuery(document).ready(function() {


        $('.date-picker').datetimepicker({
            format: 'L',
            locale: "${lenguaje}",
            maxDate: new Date(),
            useCurrent: true
        });
      //  alert("${fecha}".substring(3,2));
       // alert("${fecha}".substring(6,2));
        $("#fechaInicioCons").val("${fecha}".substring(8,10)+"/"+"${fecha}".substring(5,7)+"/"+"${fecha}".substring(0,4));
        $("#nEnvios").val("${numEnvio}");


        var edithojas = "SI";
        var edithojasn = "NO";
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
                    d.nEnvios = $("#nEnvios").val();
                    d.participante = "0";
                    d.bhcId =0;
                },
                dataSrc: ""

            },
            "columns": [
                 { data: 'Corrección', defaultContent: "",
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        var editUrl =  '/a2cares/hojaclinica/editarBHC1/' + oData.participante + '/'+ oData.idbhc + '/'+ oData.fecha + '/'+ oData.numEnvio + '/';
                        //    var editUrln =  '/a2cares/hojaclinica/editarBHC1/' + oData.participante + '/'+ oData.idbhc + '/'+ oData.fecha + '/'+ oData.numEnvio + '/';
                        // editUrl = 'editarHC/';
                        //  $(nTd).html('<a data-toggle="tooltip" data-placement="bottom" title= ' + viewMess + ' href=' + viewUrl + ' class="btn btn-outline-primary btn-sm"><i class="fa fa-search"></i></a> <a data-toggle="tooltip" data-placement="bottom" title= ' + editMess + ' href=' + editUrl + ' class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>');
                        $(nTd).html('<a data-toggle="tooltip" data-placement="bottom" title= ' + edithojas + ' href=' + editUrl + ' class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a> ');
                        //      $(nTd).html('<a data-toggle="tooltip" data-placement="bottom" title= ' + edithojasn + ' href=' + editUrln + ' class="btn btn-danger btn-sm"><i class="fa fa-edit"></i></a> ');
                    }

                },
                { data: 'Corrección', defaultContent: "",

                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        // var editUrl =  '/a2cares/hojaclinica/editarBHC1/' + oData.participante + '/'+ oData.idbhc + '/'+ oData.fecha + '/'+ oData.numEnvio + '/';
                        var editUrln =  '/a2cares/hojaclinica/editarBHC2/' + oData.participante + '/'+ oData.idbhc + '/'+ oData.fecha + '/'+ oData.numEnvio + '/';
                        // editUrl = 'editarHC/';
                        //  $(nTd).html('<a data-toggle="tooltip" data-placement="bottom" title= ' + viewMess + ' href=' + viewUrl + ' class="btn btn-outline-primary btn-sm"><i class="fa fa-search"></i></a> <a data-toggle="tooltip" data-placement="bottom" title= ' + editMess + ' href=' + editUrl + ' class="btn btn-outline-primary btn-sm"><i class="fa fa-edit"></i></a>');
                        $(nTd).html('<a data-toggle="tooltip" data-placement="bottom" title= ' + edithojasn + ' href=' + editUrln + ' class="btn btn-danger btn-sm"><i class="fa fa-file-pdf"></i></a> ');
                        //      $(nTd).html('<a data-toggle="tooltip" data-placement="bottom" title= ' + edithojasn + ' href=' + editUrln + ' class="btn btn-danger btn-sm"><i class="fa fa-edit"></i></a> ');
                    }

                },
                { data: 'idbhc', defaultContent: ""},
                { data: 'participante', defaultContent: ""},
                { data: 'fecha', defaultContent: ""},
                { data: 'volumen', defaultContent: ""},
                { data: 'numEnvio', defaultContent: ""},
                { data: 'procesaCSFV', defaultContent: ""},
                { data: 'estudio', defaultContent: ""},
                { data: 'usuarioRegistro', defaultContent: ""},
                { data: 'estudio', defaultContent: ""},
                { data: 'puesto', defaultContent: ""}
            ]
        });

        $("#fechaInicioCons").on("dp.change", function (e) {

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
            nEnvios: {
                required: true
            },
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