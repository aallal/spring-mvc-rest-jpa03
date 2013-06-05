<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<spring:eval expression="@appProps['jquery.version']"
	var="jqueryVersion" />
<spring:url value="/resources/js/jquery/jquery${jqueryVersion}.js"
	var="jqueryUrl" />

<script src="${jqueryUrl}" type="text/javascript">
	
</script>


<!-- plugin jquery pour les templates -->
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.tmpl.min.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/json.min.js" />"></script>


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
		<p>Cette page démontre la puissance des fonctionnalités de Spring
			MVC avec utilisation des fonctionnalités Ajax.</p>
		<h2>Fonctionnalités implémentées</h2>
		<ul>
			<li>Utilisation de la méthode Get pour extraire la liste des
				personnes
			<li>Utilisation de la méthode Get pour extraire une personne
				avec son identifiant ID
			<li>Utilisation de la méthode POST pour ajouter une nouvelle
				personne
			<li>Utilisation de la méthode PUT pour modifier une personne
			<li>Utilisation de la méthode DELETE pour supprimer une personne




			
		</ul>
		<h2>Get By ID</h2>
		<form id="getPersoForm">
			<div class="error hide" id="idError">Please enter a valid ID</div>
			<label for="persoId">ID : </label> <input name="id" id="persoID"
				value="0" type="number" /> <input type="submit"
				value="Get Personne par son ID" /> <br /> <br />
			<div id="personResponse"></div>
		</form>

		<hr>
		<h2>Get liste personnes</h2>
		<input type="submit" id="btn_get_personnes"
			value="Get liste of persons" /><br /> <br />
		<ul id="perso_list"></ul>
		<div id="perso_list2"></div>
		<!--  <table id="perso_list2" border="1"><tr><th>Nom</th><th>Prénom</th></tr></table>  -->


		<hr>
		<h2>Post new Person</h2>
		<form id="newPersoForm">
			<div class="error hide" id="idError">Please enter a valid ID</div>
			<label for="persoName">Nom : </label> <input name="nom" id="id_nom"
				type="text" /> <label for="persolName">Prénom : </label> <input
				name="prenom" id="id_prenom" type="text" /> <label for="persoAge">Age
				: </label> <input name="age" id="id_age" type="text" /> <input
				type="submit" value="Post Personne" /> <br /> <br />
			<div id="postPersonResponse"></div>
		</form>



	</div>


	<script type="text/javascript">
		var jq = jQuery.noConflict();

		jq(document)
				.ready(
						function() {
							alert("Jquery is Ready !");
							// Random Person AJAX Request

							jq('#getPersoForm')
									.submit(
											function(e) {
												alert("submit get person id !");
												var persoID = jq('#persoID')
														.val();
												console.log(persoID);
												jq
														.getJSON(
																'${pageContext.request.contextPath}/rest/personne2/'
																		+ persoID,
																function(perso) {
																	console
																			.log(perso);
																	var dataObject = perso.data;
																	console
																			.log(dataObject);
																	jq(
																			'#postPersonResponse')
																			.text(
																					JSON
																							.stringify(dataObject));

																});
												e.preventDefault(); // prevent actual form submit
											});

							jq('#newPersoForm').submit(

											function(e) {
												alert("submit post new person !");
												e.preventDefault(); // prevent actual form submit
												var JSONObject = jq(this).serializeObject(jq);
												
												/*var JSONObject = {
													"nom" : jq('#id_nom').val(),
													"prenom" : jq('#id_prenom')	.val(),
													"age" : jq('#id_age')	.val()
												};*/
												
												console.log(JSONObject);
												
												jq.postJSON('${pageContext.request.contextPath}/rest/personne2/',
														JSONObject, function(resp) {
																console.log(resp);
																var dataObject = resp.data;
																jq('#personResponse').text(JSON.stringify(dataObject));
																
															});
												/*jq.ajax({
															type : 'POST',
															url : '${pageContext.request.contextPath}/rest/personne2/',
															contentType : "application/json; charset=utf-8",
															data : JSON.stringify(JSONObject),
															dataType : 'json',
															success : function(resp) {
																console.log(resp);
																var dataObject = resp.data;
																jq('#personResponse').text(JSON.stringify(dataObject));
																
															},
													        error: function(jqXHR, textStatus, errorThrown) {
	            											alert(jqXHR.status + ' ' + jqXHR.responseText);
	        												}
														});*/
											});

							jq('#btn_get_personnes')
									.click(
											function(e) {
												alert("submit get list of person");

												jq
														.getJSON(
																'${pageContext.request.contextPath}/rest/personne2/',
																function(resp) {
																	var dataObject = resp.data;
																	//var dataObject= [{id:1,version:3,nom:"ALLAL MOHALED AMENE",prenom:"SANSPRENOM",age:40, nbenfants:2, marie:2}];
																	console
																			.log(JSON
																					.stringify(dataObject));

																	jq(
																			'#perso_list2')
																			.empty();
																	// $('#perso_list').text();
																	var newTable = jq('<table border="1"><tr><th>Nom</th><th>Prénom</th></tr></table>');
																	jq(
																			'#persoTmpl2')
																			.tmpl(
																					dataObject)
																			.appendTo(
																					newTable);

																	jq(
																			'#perso_list2')
																			.append(
																					newTable);
																});
											});
						});
	</script>

	<script id="persoTmpl" type="text/x-jquery-tmpl">
 
<li><b>\${nom}</b> - \${prenom} - \${nbenfants}
</li>
</script>

	<script id="persoTmpl2" type="text/x-jquery-tmpl">
<tr>
<td>\${nom}</td>
<td>\${prenom}</td>
</tr>
</script>


</body>
</html>