<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 14/10/2021
  Time: 15:45
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

    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>
    <style>
        #list_participante_extensiontmp_filter {
            float: right !important;
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
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/cartas/CartaParticipantTmp" htmlEscape="true "/>">
                    <spring:message code="lbl.Back" /> <spring:message code="Form" />
                </a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="card">
                    <div class="card-header">
                      <h5>
                          <i class="fas fa-clipboard-list" aria-hidden="true"></i>
                          <spring:message code="List"/>  <spring:message code="Extension"/>
                      </h5>
                    </div>
                    <div class="card-body">
                        <spring:url value="/cartas/CartaParticipantTmp" var="CartaParticipantTmpUrl"/>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="col-md-12">
                                    <a class="btn btn-info btn-lg" data-toggle="tooltip" data-placement="bottom" title="Asignar Carta" href="<spring:url value="/cartas/CartaParticipantTmp" htmlEscape="true "/>">
                                        <i class="fas fa-user-tag"></i>
                                        <spring:message code="lbl.To.assign" /> <spring:message code="letters" />
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-4"></div>
                            <div class="col-md-4"></div>
                        </div>
                        <hr/>
                        <div class="table-responsive">
                            <table  id="list_participante_extensiontmp"  class="table table-hover table-bordered" style="width: 100%">
                                <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th class="text-center"> <spring:message code="lbl.participant"/></th>
                                    <th class="text-center"> <spring:message code="dateAdded"/></th>
                                    <th class="text-center"><spring:message code="Extension"/></th>
                                    <th class="text-center"><spring:message code="lbl.tutor"/></th>
                                    <th class="text-center"><spring:message code="lbl.Observation"/></th>
                                    <th class="text-center"><spring:message code="lbl.user"/></th>
                                    <th class="text-center"><spring:message code="actions"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listaExtension}" var="e" varStatus="theCount">
                                    <spring:url value="/cartas/disableExtensionTmp/{idParticipantExtensiontmp}" var="disableExtensionTempUrl">
                                        <spring:param name="idParticipantExtensiontmp" value="${e.idParticipantExtensiontmp}" />
                                    </spring:url>
                                    <c:set var="confirmar"><spring:message code="confirm" /></c:set>
                                    <spring:url value="/cartas/editExtensionTmp/{idParticipantExtensiontmp}" var="editExtensionTmpUrl">
                                        <spring:param name="idParticipantExtensiontmp" value="${e.idParticipantExtensiontmp}" />
                                    </spring:url>
                                    <tr>
                                        <td class="text-center">${e.idParticipantExtensiontmp}</td>
                                        <td class="text-center">${e.participantecartatmp.idparticipante}</td>
                                        <td><fmt:formatDate value="${e.fechaExtension}" pattern="dd/MM/yyyy"/></td>
                                        <td class="text-center">${e.extensiones.extension}</td>
                                        <td class="text-center">${e.nombre1Tutor} ${e.apellido1Tutor}</td>
                                        <td>${e.observacion}</td>
                                        <td class="text-center">${e.recordUser}</td>
                                        <td class="text-center">
                                            <a title="<spring:message code="edit" />" href="${fn:escapeXml(editExtensionTmpUrl)}" class="btn btn-outline-primary btn-sm">
                                                <i class="fa fa-edit" aria-hidden="true"></i></a>

                                            <a title="<spring:message code="disable" />" data-toggle="modal" class="btn btn-outline-danger btn-sm desact">
                                                <i class="far fa-trash-alt" aria-hidden="true"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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

<!-- Datatables  -->
<spring:url value="/resources/js/libs/DataTables/DataTables-1.10.24/js/jquery.dataTables.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/DataTables/DataTables-1.10.24/js/dataTables.bootstrap4.js" var="dataTablesBS" />
<script type="text/javascript" src="${dataTablesBS}"></script>

<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<spring:url value="/resources/js/libs/DataTables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/sweetalert.js" var="sweet" />
<script type="text/javascript" src="${sweet}"></script>

<script>
    $(document).ready(function(){
        var parametros ={
            disableExtensionTempUrl: "${disableExtensionTempUrl}",
            CartaParticipantTmpUrl:"${CartaParticipantTmpUrl}"
        };
        var table = $('#list_participante_extensiontmp').DataTable({
            "oLanguage"  : {
                "sUrl"   : "${dataTablesLang}"
            },"columnDefs": [
                { "width": "1%", "targets": 0 },
                { "width": "2%", "targets": 2 }
            ]
        });

        $("#list_participante_extensiontmp tbody").on("click", ".desact", function(e){
            var correntRow = $(this).closest('tr').find('td');
            var idParticipantExtensiontmp = table.row(correntRow).data()[0];
            var idparticipante = table.row(correntRow).data()[1];
            var fecha = table.row(correntRow).data()[2];

            swal({
                 title: "Deshabilitar? ",
                 text: "código: " + idparticipante + " con fecha: "+ fecha,
                 type: "warning",
                 showCancelButton: true,
                 confirmButtonClass: "btn-danger",
                 confirmButtonText: "Si!",
                 cancelButtonText: "No, plx!",
                 closeOnConfirm: false,
                 closeOnCancel: false
             }, function (isConfirm) {
             if (isConfirm) {
             $.post(parametros.disableExtensionTempUrl, {idParticipantExtensiontmp: idParticipantExtensiontmp, ajax: 'true'}, function (data) {
                swal("Eliminado!", "con éxito!", "success");
                 setTimeout(function () {
                     window.location.href = parametros.CartaParticipantTmpUrl;
                 }, 1500);
             }).fail(function () {
                     setTimeout(function () {
                        swal("Error!", "Servidor no respode!", "error");
                     }, 2000);
                });
             } else {
                swal("Cancelado!", "Registro está seguro! :)", "error");
             }
             });
        });

    });

</script>
</body>
</html>
