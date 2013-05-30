<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<spring:eval expression="@appProps['jquery.version']" var="jqueryVersion" />
<spring:url value="/resources/js/jquery/jquery${jqueryVersion}.js" var="jqueryUrl"></spring:url>
<script src="${jqueryUrl}" type="text/javascript">
	
</script>

<!--  
<script type="text/javascript"	src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js" />" ></script>
-->

<title>Spring MVC :: Page Personne avec Ajax</title>
<style type="text/css">
</style>
</head>
<body>
	<div id="contenu">
		<h1>Page Personne</h1>
		<p>Cette page d�montre la puissance des fonctionnalit�s de Spring
			MVC avec utilisation des fonctionnalit�s Ajax.</p>
		<h2>Fonctionnalit�s impl�ment�es</h2>
		<ul>
			<li>Utilisation de la m�thode Get pour extraire la liste des
				personnes
			<li>Utilisation de la m�thode Get pour extraire une personne
				avec son identifiant ID
			<li>Utilisation de la m�thode POST pour ajouter une nouvelle
				personne
			<li>Utilisation de la m�thode PUT pour modifier une personne
			<li>Utilisation de la m�thode DELETE pour supprimer une personne



			
		</ul>
		<script type="text/javascript">
			// var jq = jQuery.noConflict();
			$(document).ready(
					function() {
						alert("Jquery is Ready !");
						// Random Person AJAX Request

						$('#getPersoForm').submit(
								function(e) {
									alert("submit get person id !");
									var persoID = $('#persoID').val();
									console.log(persoID);
									$.getJSON(
											'${pageContext.request.contextPath}/rest/personne2/'
													+ persoID, function(perso) {
												console.log(perso);
												var dataObject = perso.data;
												console.log(dataObject);
												$('#personResponse').text(JSON.stringify(dataObject));

											});
									e.preventDefault(); // prevent actual form submit
								});
					});
		</script>

		<h2>Get By ID</h2>
		<form id="getPersoForm">
			<div class="error hide" id="idError">Please enter a valid ID in
				range 0-3</div>
			<label for="persoId">ID : </label> <input name="id" id="persoID"
				value="0" type="number" /> <input type="submit"
				value="Get Personne par son ID" /> <br /> <br />
			<div id="personResponse"></div>
		</form>
	</div>
</body>
</html>