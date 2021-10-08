<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/" htmlEscape="true "/>"><i class="fas fa-home"></i><spring:message code="dashboard" /></a>
            </li>
            <sec:authorize access="hasRole('ROLE_ROOT')">
            <li class="nav-item nav-dropdown administracion">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-wrench"></i><spring:message code="admin" /></a>
	            <ul class="nav-dropdown-items">
	                <li class="nav-item users">
	                    <a class="nav-link" href="<spring:url value="/admin/users/" htmlEscape="true "/>"><i class="icon-people"></i><spring:message code="users" /></a>
	                </li>
	            </ul>
	        </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ROOT')">
                <li class="nav-item nav-dropdown fingering">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-book-open"></i><spring:message code="fingering" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/hoja-clinica/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="clinical_sheet" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>

            <li class="nav-item nav-dropdown reports">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="fas fa-copy"></i><spring:message code="reports" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item filedata">
                        <a class="nav-link" href="<spring:url value="/reportes/pdf/fileData/" htmlEscape="true "/>"><i class="fas fa-file"></i><spring:message code="report.file.data" /></a>
                    </li>
                </ul>
            </li>
	        <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/logout" htmlEscape="true" />"><i class="fas fa-lock"></i><spring:message code="logout" /></a>
            </li>
        </ul>
    </nav>
</div>