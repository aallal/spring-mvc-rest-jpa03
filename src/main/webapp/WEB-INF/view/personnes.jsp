<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/rest/personne3/records" var="recordsUrl" />

<c:url value="/rest/personne3/create" var="addUrl" />
<c:url value="/rest/personne3/update" var="editUrl" />
<c:url value="/rest/personne3/delete" var="deleteUrl" />

<html>
<head>

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/jquery/ui-lightness/jquery-ui-1.10.3.custom.css" />"></link>

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/jquery/ui.jqgrid.css" />"></link>


<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-1.10.0.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-ui-1.10.3.custom.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/i18n/grid.locale-en.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.jqGrid.min.js" />"></script>


<title>User Records</title>

<script type='text/javascript'>
	$(document).ready(function() {
		alert("Jquery is Ready !");
	});

	/*
	
	 */

	$(function() {
		$("#grid").jqGrid({
			url : '${recordsUrl}',
			datatype : 'json',
			mtype : 'GET',
			colNames : [ 'Id', 'Nom', 'Prénom', 'Age' ],
			colModel : [ {
				name : 'id',
				index : 'id',
				width : 10,
				editable : true,
				editoptions : {
					readonly : true,
					size : 10
				},
				hidden : false
			}, {
				name : 'nom',
				index : 'nom',
				width : 100,
				editable : true,
				editrules : {
					required : true
				},
				editoptions : {
					size : 30
				}
			}, {
				name : 'prenom',
				index : 'prenom',
				width : 100,
				editable : true,
				editrules : {
					required : true
				},
				editoptions : {
					size : 30
				}
			}, {
				name : 'age',
				index : 'age',
				width : 20,
				editable : true,
				editrules : {
					required : true
				},
				editoptions : {
					size : 10
				}
			} ],
			postData : {},
			rowNum : 10,
			rowList : [ 10, 20, 40, 60 ],
			height : 240,
			autowidth : true,
			rownumbers : true,
			pager : '#pager',
			sortname : 'id',
			viewrecords : true,
			sortorder : "asc",
			caption : "Records",
			emptyrecords : "Empty records",
			loadonce : false,
			loadComplete : function() {
			},
			jsonReader : {
				root : "rows",
				page : "page",
				total : "total",
				records : "records",
				repeatitems : false,
				cell : "cell",
				id : "id"
			}
		});

		$("#grid").jqGrid('navGrid', '#pager', {
			edit : false,
			add : false,
			del : false,
			search : true
		}, {}, {}, {}, { // search
			sopt : [ 'cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew' ],
			closeOnEscape : true,
			multipleSearch : true,
			closeAfterSearch : true
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Ajouter",
			buttonicon : "ui-icon-plus",
			onClickButton : addRow,
			position : "last",
			title : "",
			cursor : "pointer"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Modifier",
			buttonicon : "ui-icon-pencil",
			onClickButton : editRow,
			position : "last",
			title : "",
			cursor : "pointer"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Suprimer",
			buttonicon : "ui-icon-trash",
			onClickButton : deleteRow,
			position : "last",
			title : "",
			cursor : "pointer"
		});

		// Toolbar Search
		$("#grid").jqGrid('filterToolbar', {
			stringResult : true,
			searchOnEnter : true,
			defaultSearch : "cn"
		});

	});

	function addRow() {

		$('#grid').jqGrid('editGridRow', 'new', {
			url : '${addUrl}',
			editData : {},
			serializeEditData : function(data) {
				data.id = 0;
				return $.param(data);
			},
			recreateForm : true,
			closeAfterAdd : true,
			reloadAfterSubmit : true,
			afterSubmit : function(response, postdata) {
				var result = eval('(' + response.responseText + ')');
				var errors = "";

				if (result.success == false) {
					for ( var i = 0; i < result.message.length; i++) {
						errors += result.message[i] + "<br/>";
					}
				} else {
					$('#msgbox').text('Entry has been added successfully');
					$('#msgbox').dialog({
						title : 'Success',
						modal : true,
						buttons : {
							"Ok" : function() {
								$(this).dialog("close");
							}
						}
					});
				}
				// only used for adding new records
				var newId = null;
				return [ result.success, errors, newId ];
			}
		});

	}

	function editRow() {
		// Get the currently selected row
		var row = $('#grid').jqGrid('getGridParam', 'selrow');

		if (row != null) {

			$('#grid')
					.jqGrid(
							'editGridRow',
							row,
							{
								url : '${editUrl}',
								editData : {},
								recreateForm : true,
								closeAfterEdit : true,
								reloadAfterSubmit : true,
								afterSubmit : function(response, postdata) {
									var result = eval('('
											+ response.responseText + ')');
									console.log(response.responseText);
									var errors = "";

									if (result.success == false) {
										for ( var i = 0; i < result.message.length; i++) {
											errors += result.message[i]
													+ "<br/>";
										}
									} else {
										$('#msgbox')
												.text(
														'Entry has been edited successfully');
										$('#msgbox').dialog({
											title : 'Success',
											modal : true,
											buttons : {
												"Ok" : function() {
													$(this).dialog("close");
												}
											}
										});
									}

									var newId = null;
									return [ result.success, errors, newId ];
								}
							});
		} else {
			$('#msgbox').text('You must select a record first!');
			$('#msgbox').dialog({
				title : 'Error',
				modal : true,
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	}

	function deleteRow() {
		// Get the currently selected row
		var row = $('#grid').jqGrid('getGridParam', 'selrow');

		// A pop-up dialog will appear to confirm the selected action
		if (row != null)
			$('#grid')
					.jqGrid(
							'delGridRow',
							row,
							{
								url : '${deleteUrl}',
								recreateForm : true,
								beforeShowForm : function(form) {
									//Change title
									$(".delmsg").replaceWith(
											'<span style="white-space: pre;">'
													+ 'Delete selected record?'
													+ '</span>');
									//hide arrows
									/*$('#pData').hide();  
									$('#nData').hide();
									 */
								},
								reloadAfterSubmit : true,
								closeAfterDelete : true,
								serializeDelData : function(postdata) {
									// var rowdata = $('#grid').getRowData(postdata.id);
									// append postdata with any information 
									return {
										id : postdata.id
									};
								},
								afterSubmit : function(response, postdata) {
									var result = eval('('
											+ response.responseText + ')');
									var errors = "";

									if (result.success == false) {
										for ( var i = 0; i < result.message.length; i++) {
											errors += result.message[i]
													+ "<br/>";
										}
									} else {
										$('#msgbox')
												.text(
														'Entry has been deleted successfully');
										$('#msgbox').dialog({
											title : 'Success',
											modal : true,
											buttons : {
												"Ok" : function() {
													$(this).dialog("close");
												}
											}
										});
									}
									// only used for adding new records
									var newId = null;

									return [ result.success, errors, newId ];
								}
							});
		else {
			$('#msgbox').text('You must select a record first!');
			$('#msgbox').dialog({
				title : 'Error',
				modal : true,
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					}
				}
			});
		}
	}
</script>
</head>

<body>
	<h1 id='banner'>Table des personnes</h1>

	<table id="grid">
		<tr>
			<td></td>
		</tr>
	</table>
	<div id="pager"></div>


	<div id='msgbox' title='' style='display: none'></div>
</body>
</html>