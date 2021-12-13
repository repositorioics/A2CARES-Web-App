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

        .act {

        }
        .desact {

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
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/admin/users/" htmlEscape="true "/>"><spring:message code="users" /></a>
            </li>
        </ol>
        <c:set var="userEnabledLabel"><spring:message code="login.userEnabled" /></c:set>
        <c:set var="userDisabledLabel"><spring:message code="login.userDisabled" /></c:set>
        <c:set var="userLockedLabel"><spring:message code="login.accountLocked" /></c:set>
        <c:set var="userUnlockedLabel"><spring:message code="login.accountNotLocked" /></c:set>
        <c:set var="habilitar"><spring:message code="enable" /></c:set>
        <c:set var="deshabilitar"><spring:message code="disable" /></c:set>
        <c:set var="bloquear"><spring:message code="lock" /></c:set>
        <c:set var="desbloquear"><spring:message code="unlock" /></c:set>
        <c:set var="confirmar"><spring:message code="confirm" /></c:set>
        <c:set var="exportar"><spring:message code="export" /></c:set>
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <i class="fa fa-group"></i> <strong><spring:message code="users" /></strong>
                </div>
                <div class="card-block">
                    <div class="row table-toolbar">
                        <div class="col-md-12">
                            <div class="btn-group">
                                <spring:url value="/admin/users/newUser"	var="newUser"/>
                                <button id="lista_usuarios_new" onclick="location.href='${fn:escapeXml(newUser)}'" class="btn btn-success">
                                    <spring:message code="add" /> <i class="fa fa-plus"></i>
                                </button>
                            </div>
                            <br>
                            <br>
                        </div>
                    </div>
                    <div class="table-responsive">

                        <table class="table table-hover table-bordered" id="lista_usuarios">
                            <thead>
                            <tr>
                                <th class="expand"><spring:message code="username" /></th>
                                <th class="hidden-xs"><spring:message code="userdesc" /></th>
                                <th class="hidden-xs"><spring:message code="useremail" /></th>
                                <th><spring:message code="enabled" /></th>
                                <th><spring:message code="userlock" /></th>
                                <th><spring:message code="usercred" /></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="basic" tabindex="-1" data-role="basic" data-backdrop="static" data-aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true"></button>
                            <div id="titulo"></div>
                        </div>
                        <div class="modal-body">
                            <input type=hidden id="accionUrl"/>
                            <div id="cuerpo"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel" /></button>
                            <button type="button" class="btn btn-info" onclick="ejecutarAccion()"><spring:message code="ok" /></button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
</div>
<jsp:include page="../../fragments/bodyFooter.jsp" />
<jsp:include page="../../fragments/corePlugins.jsp" />

<!-- Datatables  -->
<spring:url value="/resources/js/libs/DataTables/datatables.min.js" var="dataTableJs" />
<script src="${dataTableJs}" type="text/javascript"></script>
<!-- JQUERY BLOCK UI -->
<spring:url value="/resources/js/libs/jquery.blockui.min.js" var="jqueryBlockUi" />
<script src="${jqueryBlockUi}"></script>
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
<spring:url value="/admin/users/getAllUsers" var="sUsuariosUrl"/>
<spring:url value="/admin/users/" var="sUsuarioUrl" />
<spring:url value="/admin/users/editUser/" var="editUrl"/>
<spring:url value="/admin/users/habdes/disable1/" var="disableUrl"/>
<spring:url value="/admin/users/habdes/enable1/" var="enableUrl"/>
<spring:url value="/admin/users/lockunl/lock1/" var="lockUrl"/>
<spring:url value="/admin/users/lockunl/unlock1/" var="unlockUrl"/>
<spring:url value="/admin/users/chgpass/" var="chgpassUrl"/>
<script>
    jQuery(document).ready(function() {
        var table = $('#lista_usuarios').DataTable({

            dom: "<'row'<'col-sm-12 col-md-12'B>>" +
                    "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
                    "<'row'<'col-sm-12'tr>>" +
                    "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
            buttons: [
                'excel', 'pdf'
            ],"oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            ajax:{
                url: "${sUsuariosUrl}", // Change this URL to where your json data comes from
                type: "GET", // This is the default value, could also be POST, or anything you want.
                /*data: function(d) {
                    d.whateveryouwant = $("#someidhere").val()
                    d.anotherexample = "kittens"
                }*/
                dataSrc: ""
            },
            columns: [
                { data: 'username',
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<a href='${sUsuarioUrl}"+oData.username+"'>"+oData.username+"</a>");
                    }
                },
                { data: 'completeName' },
                { data: 'email' },
                { data: 'enabled',
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        if(oData.enabled) {
                            $(nTd).html("<span class='badge badge-success'><spring:message code='CAT_SINO_SI' /></span>");
                        } else {
                            $(nTd).html("<span class='badge badge-danger'><spring:message code='CAT_SINO_NO' /></span>");
                        }
                    }
                },
                { data: 'accountNonLocked',
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        if(oData.accountNonLocked) {
                            $(nTd).html("<span class='badge badge-success'><spring:message code='CAT_SINO_NO' /></span>");
                        } else {
                            $(nTd).html("<span class='badge badge-danger'><spring:message code='CAT_SINO_SI' /></span>");
                        }
                    }
                },
                { data: 'credentialsNonExpired',
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        if(oData.credentialsNonExpired) {
                            $(nTd).html("<span class='badge badge-success'><spring:message code='CAT_SINO_NO' /></span>");
                        } else {
                            $(nTd).html("<span class='badge badge-danger'><spring:message code='CAT_SINO_SI' /></span>");
                        }
                    }
                },
                { data: null,
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<a href='${sUsuarioUrl}"+oData.username+"' class='btn btn-outline-primary btn-sm'><i class='fas fa-search'></i></a>");
                    },
                    orderable: false
                },
                { data: null,
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<a href='${editUrl}"+oData.username+"' class='btn btn-outline-primary btn-sm' data-toggle='tooltip' title='<spring:message code="editEntidadToolTip" />'><i class='fas fa-edit'></i></a>");
                    },
                    orderable: false
                },
                { data: null,
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        $(nTd).html("<a href='${chgpassUrl}"+oData.username+"' class='btn btn-outline-primary btn-sm'><i class='fas fa-key'></i></a>");
                    },
                    orderable: false
                },
                { data: null,
                    className: "dt-center editor-enabled",
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        var aEnabled = "";
                        if(oData.enabled) {
                            var url = "'"+"${disableUrl}"+oData.username+"'";
                            aEnabled = "<a data-toggle='modal' href='#' data-id= '${disableUrl}"+oData.username+"' class='btn btn-outline-danger btn-sm desact'><i class='fas fa-trash'></i></a>";
                        } else {
                            var url = "${enableUrl}"+oData.username;
                            aEnabled = "<a data-toggle='modal' href='#' data-id= '${enableUrl}"+oData.username+"' class='btn btn-outline-success btn-sm act'><i class='fas fa-check'></i></a>";
                        }
                        $(nTd).html(aEnabled);
                    },
                    orderable: false
                },
                { data: null,
                    className: "dt-center editor-lock",
                    fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                        var aEnabled = "";
                        if(oData.accountNonLocked) {
                            aEnabled = "<a href='#' class='btn btn-outline-danger btn-sm desact'><i class='fas fa-lock'></i></a>";
                        } else {
                            aEnabled = "<a href='#' class='btn btn-outline-success btn-sm act'><i class='fas fa-unlock'></i></a>";
                        }
                        $(nTd).html(aEnabled);
                    },
                    orderable: false
                }

            ]
        });

        $('#lista_usuarios').on('click', 'td.editor-enabled', function (e) {
            e.preventDefault();
            console.log(table.cell( this ).data());
            var objeto= table.cell( this ).data();
            if (objeto.enabled){
                mostrarDeshabilitar("${disableUrl}"+objeto.username);
            } else {
                mostrarHabilitar("${enableUrl}"+objeto.username);
            }
        } );

        $('#lista_usuarios').on('click', 'td.editor-lock', function (e) {
            e.preventDefault();
            var objeto= table.cell( this ).data();
            if (objeto.accountNonLocked){
                mostrarBloquear("${lockUrl}"+objeto.username);
            } else {
                mostrarDesBloquear("${unlockUrl}"+objeto.username);
            }
        } );
        /*
        $.blockUI({ message: 'bloquedado',
            baseZ: 1051 // para que se muestre bien en los modales
        });*/

        if ("${usuarioHabilitado}"){
            toastr.success("${userEnabledLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioDeshabilitado}"){
            toastr.error("${userDisabledLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioBloqueado}"){
            toastr.error("${userLockedLabel}", "${nombreUsuario}" );
        }
        if ("${usuarioNoBloqueado}"){
            toastr.success("${userUnlockedLabel}", "${nombreUsuario}" );
        }

        function mostrarHabilitar(id) {
            $('#accionUrl').val(id);
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${habilitar}"+' '+id.substr(id.lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        }

        function mostrarDeshabilitar(id) {
            $('#accionUrl').val(id);
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${deshabilitar}"+' '+id.substr(id.lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        }

        function mostrarBloquear (id) {
            $('#accionUrl').val(id);
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${bloquear}"+' '+id.substr(id.lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        }

        function mostrarDesBloquear (id) {
        $('#accionUrl').val(id);
            $('#titulo').html('<h2 class="modal-title">'+"${confirmar}"+'</h2>');
            $('#cuerpo').html('<h3>'+"${desbloquear}"+' '+id.substr(id.lastIndexOf("/")+1)+'?</h3>');
            $('#basic').modal('show');
        }
    });

    function ejecutarAccion() {
        window.location.href = $('#accionUrl').val();
    }

    if ($('html').attr('dir') === 'rtl') {
        $('.tooltip-demo [data-placement=right]').attr('data-placement', 'left').addClass('rtled');
        $('.tooltip-demo [data-placement=left]:not(.rtled)').attr('data-placement', 'right').addClass('rtled');
    }
    $('[data-toggle="tooltip"]').tooltip();
</script>
</body>
</html>