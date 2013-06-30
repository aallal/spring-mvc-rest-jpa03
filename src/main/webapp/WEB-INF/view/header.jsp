<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>

<div id="header">
	<spring:message code="header_text" var="headerText" />
	<div id="appname">
		<h1>${headerText}</h1>
	</div>
</div>