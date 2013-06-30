<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>

<div id="footer">
	<spring:message code="home_text" var="homeText" />
	<spring:message code="label_en" var="labelEn" />
	<spring:message code="label_fr" var="labelFr" />
	<spring:url value="/rest/personnes2/perso" var="homeUrl" />
	<a href="${homeUrl}">${homeText}</a> | <a href="${homeUrl}?lang=en">${labelEn}</a>
	| <a href="${homeUrl}?lang=fr">${labelFr}</a>
</div>