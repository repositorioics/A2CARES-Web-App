<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/" htmlEscape="true "/>"><i class="fas fa-home"></i><spring:message code="home" /></a>
            </li>
            <sec:authorize access="hasRole('ROLE_ROOT')">
            <li class="nav-item nav-dropdown administracion">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-wrench"></i><spring:message code="admin" /></a>
	            <ul class="nav-dropdown-items">
	                <li class="nav-item users">
	                    <a class="nav-link" href="<spring:url value="/admin/users/" htmlEscape="true "/>"><i class="icon-people"></i><spring:message code="users" /></a>
	                </li>
                    <li class="nav-item catalogs">
                        <a class="nav-link" href="<spring:url value="/admin/catalogs/" htmlEscape="true "/>"><i class="icon-notebook"></i><spring:message code="catalogs" /></a>
                    </li>
                    <li class="nav-item personal">
                        <a class="nav-link" href="<spring:url value="/admin/personal/" htmlEscape="true "/>"><i class="icon-people"></i><spring:message code="personal" /></a>
                    </li>
                    <!-- init Catalogos Cartas Consentimientos -->
                    <li class="nav-item administracion">
                        <a class="nav-link" href="<spring:url value="/CatalogoVersion/CrearNuevaVersion" htmlEscape="true "/>">
                            <i class="fab fa-vimeo-v"></i>
                            <spring:message code="lbl.version.letter" /></a>
                    </li>

                    <li class="nav-item administracion">
                        <a class="nav-link" href="<spring:url value="/CatalogoParte/CrearNuevaParte" htmlEscape="true "/>">
                            <i class="fab fa-pinterest-p"></i>
                            <spring:message code="Letter.Parts" /></a>
                    </li>

                    <li class="nav-item administracion">
                        <a class="nav-link" href="<spring:url value="/CatalogoVersion/extension" htmlEscape="true "/>">
                            <i class="fab fa-etsy"></i>
                            <spring:message code="Extension" /></a>
                    </li>
                    <!-- fin Catalogos Cartas Consentimientos -->

	            </ul>
	        </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ROOT')">
                <li class="nav-item nav-dropdown fingering">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-book-open"></i><spring:message code="fingering" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/hojaclinica/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="clinical_sheet" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_ROOT')">
                <li class="nav-item nav-dropdown consentimiento">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-notebook"></i><spring:message code="letters" /></a>
                    <ul class="nav-dropdown-items">

                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/cartas/ListadoCartaParticipant" htmlEscape="true "/>">
                                <i class="fas fa-envelope-open"></i>
                                <spring:message code="consent" /> </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/cartas/CartaParticipantTmp" htmlEscape="true "/>">
                                <i class="fas fa-envelope" aria-hidden="true"></i>
                                <spring:message code="Temporal" /> </a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_ROOT')">
            <li class="nav-item nav-dropdown">
                <a class="nav-link nav-dropdown-toggle" href="#">
                    <i class="fa fa-user-times" aria-hidden="true"></i>
                    <spring:message code="retirement" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item retiro">
                        <a class="nav-link" href="<spring:url value="/retiro/ListRetiro" htmlEscape="true "/>">
                            <i class="fas fa-user-minus" aria-hidden="true"></i>
                            <spring:message code="remove" /></a>
                    </li>
                </ul>
            </li>
            </sec:authorize>


            <sec:authorize access="hasAnyRole('ROLE_ROOT')">
                <li class="nav-item nav-dropdown Serologia">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fas fa-vial" aria-hidden="true"></i>
                        <spring:message code="Serologia" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/Serologia/listSerologia" htmlEscape="true "/>">
                                <i class="fas fa-list-alt" aria-hidden="true"></i>
                                <spring:message code="Listado" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/Serologia/listEnviosMuestras" htmlEscape="true "/>">
                                <i class="fas fa-ambulance" aria-hidden="true"></i>
                                <spring:message code="Envios" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER')">
                <li class="nav-item nav-dropdown comparacion">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fas fa-not-equal" aria-hidden="true"></i>
                        <spring:message code="comparison" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/comparacion/cartas" htmlEscape="true "/>">
                                <i class="fas fa-clipboard-check" aria-hidden="true"></i>
                                <spring:message code="letters" /></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/comparacion/muestras-ma" htmlEscape="true "/>">
                                <i class="fas fa-vials" aria-hidden="true"></i>
                                <spring:message code="lbl.samples" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT', 'ROLE_SUPER')">
                <li class="nav-item nav-dropdown puntos">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fas fa-map-marked" aria-hidden="true"></i>
                        <spring:message code="lbl.gps" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/puntos/" htmlEscape="true "/>">
                                <i class="fas fa-list-alt" aria-hidden="true"></i>
                                <spring:message code="lbl.list" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
<!--
            <li class="nav-item nav-dropdown reports">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="fas fa-copy"></i><spring:message code="reports" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item filedata">
                        <a class="nav-link" href="<spring:url value="/reportes/pdf/fileData/" htmlEscape="true "/>"><i class="fas fa-file"></i><spring:message code="report.file.data" /></a>
                    </li>
                </ul>
            </li>-->
	        <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/logout" htmlEscape="true" />"><i class="fas fa-lock"></i><spring:message code="logout" /></a>
            </li>
        </ul>
    </nav>
</div>