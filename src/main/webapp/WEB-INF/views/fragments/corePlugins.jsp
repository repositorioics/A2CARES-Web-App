<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN CORE PLUGINS -->

<!-- jQuery -->
<spring:url value="/resources/js/libs/jquery-3.5.1.min.js" var="jQuery" />
<script src="${jQuery}" type="text/javascript"></script>
<!--Tether-->
<spring:url value="/resources/js/libs/tether.min.js" var="tether" />
<script src="${tether}" type="text/javascript"></script>
<!-- Bootstrap 4.6.0-->
<spring:url value="/resources/js/libs/bootstrap.bundle.min.js" var="Bootstrap" />
<script src="${Bootstrap}" type="text/javascript"></script>
<!-- Bootstrap-->
<spring:url value="/resources/js/libs/pace.min.js" var="Pace" />
<script src="${Pace}" type="text/javascript"></script>
<!-- Block UI-->
<spring:url value="/resources/js/libs/jquery.blockui.min.js" var="blockUi" />
<script src="${blockUi}" type="text/javascript"></script>
<!-- Bootstrap Toastr-->
<spring:url value="/resources/js/libs/toastr.min.js" var="toastR" />
<script src="${toastR}" type="text/javascript" ></script>
<!-- loading buttons in progress bar-->
<spring:url value="/resources/js/libs/spin.min.js" var="spinJs" />
<script src="${spinJs}" type="text/javascript" ></script>
<spring:url value="/resources/js/libs/ladda.min.js" var="laddaJs" />
<script src="${laddaJs}" type="text/javascript" ></script>

<spring:url value="/resources/js/libs/sweetalert2/sweetalert2.min.js" var="sweetalert2" />
<script src="${sweetalert2}"></script>
<!-- END CORE PLUGINS -->