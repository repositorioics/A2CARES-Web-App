<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/" htmlEscape="true "/>"><i class="fas fa-home"></i><spring:message code="home" /></a>
            </li>
            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_ADMIN')">
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

                    <li class="nav-item impresionStickers">
                        <a class="nav-link" href="<spring:url value="/ps/stickers/list" htmlEscape="true "/>">
                            <i class="fab fa-ad"></i>
                            <spring:message code="Impresion Stickers" /></a>
                    </li>

                    <!--      <li class="nav-item filedata">
                        <a class="nav-link" href="<spring:url value="/movil/getcontrolAsistencia" htmlEscape="true "/>">
                            <i class="fas fa-search" aria-hidden="true"></i>
                            <spring:message code="Control de Asistencia de Colaboradores" /></a>
                    </li>-->

	            </ul>
	        </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER', 'ROLE_ASISTENCIA_A2CARES')">
                <li class="nav-item filedata">
                    <a class="nav-link" href="<spring:url value="/movil/getcontrolAsistencia" htmlEscape="true "/>">
                        <i class="fas fa-search" aria-hidden="true"></i>
                        <spring:message code="Control de Asistencia de Colaboradores" /></a>
                </li>


            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER','ROLE_ADMIN')">
                <li class="nav-item filedata">
                    <a class="nav-link" href="<spring:url value="/mx/enfermo/procesosPendientesMuestreoA2cares" htmlEscape="true "/>">
                        <i class="fas fa-search" aria-hidden="true"></i>
                        <spring:message code="Procesos Muestreo A2CARES" /></a>
                </li>


            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_PROCESAR_BHC_CSFV')">
                <li class="nav-item filedata">
                    <a class="nav-link" href="<spring:url value="/hojaclinica/ProcesarBHC" htmlEscape="true "/>">
                        <i class="fas fa-search" aria-hidden="true"></i>
                        <spring:message code="Procesar BHC CSFV" /></a>
                </li>
                <li class="nav-item filedata">
                    <a class="nav-link" href="<spring:url value="/Serologia/listEnviosMuestrasBhc" htmlEscape="true "/>">
                        <i class="fas fa-ambulance" aria-hidden="true"></i>
                        <spring:message code="Reporte Envios BHC" /></a>
                </li>
                <li class="nav-item filedata">
                    <a class="nav-link" href="<spring:url value="/hojaclinica/cargarFileBc6000bhc" htmlEscape="true "/>">
                        <i class="fas fa-file-archive" aria-hidden="true"></i>
                        <spring:message code="Cargar Excel Bc6000" /></a>
                </li>
                <li class="nav-item filedata">
                    <a class="nav-link" href="<spring:url value="/hojaclinica/imprimirBhcBc6000" htmlEscape="true "/>">
                        <i class="fas fa-print" aria-hidden="true"></i>
                        <spring:message code="Imprimir BHC-A2CARES" /></a>
                </li>


            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER', 'ROLE_DIGI')">
                <li class="nav-item nav-dropdown fingering">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-book-open"></i><spring:message code="fingering" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/hojaclinica/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="clinical_sheet" /></a>
                        </li>

                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ROOT','ROLE_ASISTENCIA_ICS')">
                <li class="nav-item nav-dropdown fingering">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-book-open"></i><spring:message code="Asistencia ICS" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/movil/getcontrolAsistenciaICS/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="Asistencia de personal ICS" /></a>
                        </li>
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/movil/getreporteHorasICS/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="Reporte Horas Diarias" /></a>
                        </li>

                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ROOT','ROLE_ASISTENCIA_ICS_JUST')">
                <li class="nav-item nav-dropdown fingering">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-book-open"></i><spring:message code="Justificaciones" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/movil/getJustICS/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="Listar" /></a>
                        </li>
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/movil/getJustAddICS/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="Agregar" /></a>
                        </li>

                    </ul>
                </li>
            </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER', 'ROLE_DIGI_DOBLE')">

                <li class="nav-item nav-dropdown fingering">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-book-open"></i><spring:message code="Digitación 2" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item users">
                            <a class="nav-link" href="<spring:url value="/hojaclinicaDD/" htmlEscape="true "/>"><i class="icon-doc"></i><spring:message code="Hoja Clínica Doble Digitación" /></a>
                        </li>

                    </ul>
                </li>

            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_PRINT_STICKERS')">
                <li class="nav-item nav-dropdown fingering">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="fab fa-ad"></i><spring:message code="Códigos Muestreo" /></a>
                    <ul class="nav-dropdown-items">
                        <<li class="nav-item impresionStickers">
                        <a class="nav-link" href="<spring:url value="/ps/stickers/list" htmlEscape="true "/>">
                            <i class="fab fa-ad"></i>
                            <spring:message code="Impresion Stickers" /></a>
                    </li>
                    </ul>


                </li>

            </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_INFO')">
                <li class="nav-item nav-dropdown fingering">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="fab fa-ad"></i><spring:message code="Informes A2CARES" /></a>
                    <ul class="nav-dropdown-items">
                        <<li class="nav-item impresionStickers">
                        <a class="nav-link" href="<spring:url value="/ps/stickers/list" htmlEscape="true "/>">
                            <i class="fab fa-ad"></i>
                            <spring:message code="Impresion Stickers" /></a>
                    </li>
                    </ul>


                </li>

            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER', 'ROLE_DIGI')">
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

            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER', 'ROLE_DIGI')">
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


            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER', 'ROLE_LABO')">
                <li class="nav-item nav-dropdown Serologia">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fas fa-vial" aria-hidden="true"></i>
                        <spring:message code="Recepción Muestreo" /></a>
                    <ul class="nav-dropdown-items">

                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/Serologia/listSerologia" htmlEscape="true "/>">
                                <i class="fas fa-list-alt" aria-hidden="true"></i>
                                <spring:message code="Serología" /></a>
                        </li>
                            <li class="nav-item filedata">
                                <a class="nav-link" href="<spring:url value="/Serologia/listEnviosMuestras" htmlEscape="true "/>">
                                    <i class="fas fa-ambulance" aria-hidden="true"></i>
                                    <spring:message code="Reporte Envios Serologia" /></a>
                            </li>

                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/Serologia/listBHC" htmlEscape="true "/>">
                                <i class="fas fa-list-alt" aria-hidden="true"></i>
                                <spring:message code="BHC" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/Serologia/listEnviosMuestrasBhc" htmlEscape="true "/>">
                                <i class="fas fa-ambulance" aria-hidden="true"></i>
                                <spring:message code="Reporte Envios BHC" /></a>
                        </li>

                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/mx/enfermo/cuadrarRecepcionMuestreo" htmlEscape="true "/>">
                                <i class="fas fa-ambulance" aria-hidden="true"></i>
                                <spring:message code="Recepción Muestreo   -   Tablas" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/mx/enfermo/diferenciasRecepcionMuestreo" htmlEscape="true "/>">
                                <i class="fas fa-ambulance" aria-hidden="true"></i>
                                <spring:message code="Diferencias Serología Recepción" /></a>
                        </li>


                    </ul>
                </li>
            </sec:authorize>

            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER', 'ROLE_LABO')">
                <li class="nav-item nav-dropdown Serologia">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fas fa-head-side-mask" aria-hidden="true"></i>
                        <spring:message code="lbl.mx.sick" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/mx/enfermo/list" htmlEscape="true "/>">
                                <i class="fas fa-th-list" aria-hidden="true"></i>
                                <spring:message code="Listado" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/mx/enfermo/search" htmlEscape="true "/>">
                                <i class="fas fa-search" aria-hidden="true"></i>
                                <spring:message code="search" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/mx/enfermo/generarReporte" htmlEscape="true "/>">
                                <i class="fas fa-ambulance" aria-hidden="true"></i>
                                <spring:message code="lbl.shipment.report" /></a>
                        </li>
                        <li class="nav-item filedata">
                        <a class="nav-link" href="<spring:url value="/mx/enfermo/controldeIngresosMx" htmlEscape="true "/>">
                            <i class="fas fa-search" aria-hidden="true"></i>
                            <spring:message code="Control de Ingreso Mx" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/mx/enfermo/convalecientesMx" htmlEscape="true "/>">
                                <i class="fas fa-search" aria-hidden="true"></i>
                                <spring:message code="Convalecientes Mx" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/mx/enfermo/admisionPacientesMx" htmlEscape="true "/>">
                                <i class="fas fa-search" aria-hidden="true"></i>
                                <spring:message code="Control Admisión Pacientes" /></a>
                        </li>
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/mx/enfermo/admisionPacientesMx2" htmlEscape="true "/>">
                                <i class="fas fa-search" aria-hidden="true"></i>
                                <spring:message code="Informe Fin de Día Médicos" /></a>
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
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/hojaclinica/HCComparacion" htmlEscape="true "/>">
                                <i class="fas fa-vials" aria-hidden="true"></i>
                                <spring:message code="Hojas Clínicas Digitadas" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ROOT', 'ROLE_SUPER', 'ROLE_DIGI')">
                <li class="nav-item nav-dropdown comparacion">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fas fa-check-double" aria-hidden="true"></i>
                        <spring:message code="corrections" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/correcion/tutor/" htmlEscape="true "/>">
                                <i class="fas fa-check" aria-hidden="true"></i>
                                <spring:message code="lbl.tutor" /></a>
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

            <sec:authorize access="hasRole('ROLE_ENTO')">
                <li class="nav-item nav-dropdown cuestionarios">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fa fa-bug" aria-hidden="true"></i><spring:message code="lbl.ento" /></a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/ento/informacion" htmlEscape="true "/>">
                                <i class="fa fa-flag-checkered" aria-hidden="true"></i><spring:message code="Information" /></a>
                        </li>
                    </ul>
                    <sec:authorize access="hasRole('ROLE_ENTO_EDICION')">
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/entomologia/editdata/" htmlEscape="true "/>">
                                <i class="fa fa-database" aria-hidden="true"></i><spring:message code="Editar Tablas" /></a>
                        </li>
                    </ul>
                    </sec:authorize>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="<spring:url value="/entomologia/dobledigitacion/" htmlEscape="true "/>">
                                <i class="fa fa-book-open" aria-hidden="true"></i><spring:message code="Doble Digitación" /></a>
                        </li>
                    </ul>
                </li>


            </sec:authorize>


            <!-- ** HEMODINAMICA ** -->
            <sec:authorize access="hasRole('ROLE_DIGI')">
                <li class="nav-item nav-dropdown hemodinamica">
                    <a class="nav-link nav-dropdown-toggle" href="#">
                        <i class="fas fa-hospital-symbol"></i>
                        <spring:message code="Hemodinamica" />
                    </a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item filedata">
                            <a class="nav-link" href="<spring:url value="/hemo/list" htmlEscape="true "/>">
                                <i class="fa fa-snowflake-o" aria-hidden="true"></i>
                                <spring:message code="lbl.list" /></a>
                        </li>
                    </ul>
                </li>
            </sec:authorize>
	        <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/logout" htmlEscape="true" />"><i class="fas fa-lock"></i><spring:message code="logout" /></a>
            </li>
        </ul>
    </nav>
</div>