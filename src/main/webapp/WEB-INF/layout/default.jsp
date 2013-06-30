<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--   link rel="stylesheet" type="text/css" media="screen" href="${app_css_url}" /> -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/standard.css" />"></link>

<!-- Get the user locale from the page context (it was set by Spring MVC's locale
resolver) -->
<c:set var="userLocale">
	<c:set var="plocale">${pageContext.response.locale}</c:set>
	<c:out value="${fn:replace(plocale, '_', '-')}" default="en" />
</c:set>

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<spring:message code="application_name" var="app_name"
	htmlEscape="false" />
<title><spring:message code="welcome_h3" arguments="${app_name}" /></title>

</head>
<body class="tundra spring">
	<div id="headerWrapper">
		<tiles:insertAttribute name="header" ignore="true" />
	</div>
	<div id="wrapper">
		<tiles:insertAttribute name="menu" ignore="true" />
		<div id="main">
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="footer" ignore="true" />
		</div>
	</div>
</body>
</html>