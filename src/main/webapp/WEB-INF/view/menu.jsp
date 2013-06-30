<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>

<div id="menu">
	<spring:message code="menu_header_text" var="menuHeaderText" />
	<spring:message code="menu_add_perso" var="menuAddPerso" />
	<spring:url value="/perso?form" var="addPersoUrl" />
	
	<h3>${menuHeaderText}</h3>
	<a href="${addPersoUrl}"><h3>${menuAddPerso}</h3></a>
</div>