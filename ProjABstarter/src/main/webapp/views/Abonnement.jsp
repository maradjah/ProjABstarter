<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib uri="/struts-bootstrap-tags" prefix="sb"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Abonnement Welcome Page</title>
<sj:head jqueryui="true" />
<sb:head includeScripts="true" includeScriptsValidation="false" />

<script type="text/javascript">
	function checkifempty(type) {
		if (type == "GSM") {
			document.getElementById('fid').disabled = false;
			document.getElementById('deb').disabled = true;
			//document.getElementById('deb').value = "0";
		} else {
			document.getElementById('deb').disabled = false;
			document.getElementById('fid').disabled = true;
			//document.getElementById('fid').value = "0";
		}
	}
</script>

<style type="text/css">
body {
	padding-top: 50px;
	/* 50px to make the container go all the way to the bottom of the topbar */
	/
}

.center {
	position: absolute;
	left: 50px;
	right: 0;
	margin-left: 20%;
	margin-top: 20px;
}

.center2 {
	position: fixed;
	left: 50px;
	right: 0;
	margin-right: 10%;
}
</style>
</head>
<body>
	<s:actionerror theme="bootstrap" />
	<s:actionmessage theme="bootstrap" />
	<s:fielderror theme="bootstrap" />

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Appliation Abonnement By
				Maradjah </a>
		</div>
	</nav>
	<div class="container center">
		<div class="row">
			<div class="col-md-7">
				<div class="well">
					<s:form action="save" enctype="multipart/form-data"
						theme="bootstrap" cssClass="form-horizontal" label="Abonnement"
						method="post" labelposition="">
						<s:div>
							<s:textfield label="ID Abonnement" name="idAbonnement"
								elementCssClass="col-sm-7" />

							<sj:datepicker name="dateAbonnement" parentTheme="bootstrap"
								label="Date Abonnement" cssClass="form-control"
								elementCssClass="col-sm-5" showOn="focus"
								inputAppendIcon="calendar" />
							<s:textfield label="Solde" name="solde"
								elementCssClass="col-sm-5" inputAppend="$" />

							<s:checkbox label="Actif" name="actif" />

							<s:radio id="radio" list="typesAb" label="type Abonnement"
								name="typeRadio" onchange="checkifempty(this.value)" />

							<s:textfield id="fid" label="Fidelio" name="fidelio"
								disabled="true" elementCssClass="col-sm-3" />

							<s:textfield id="deb" label="Débit" name="debit" disabled="true"
								elementCssClass="col-sm-3" />
							<s:hidden name="editMode"></s:hidden>

							<div class="form-group">
								<div class="col-sm-offset-3 col-md-9">
									<s:submit cssClass="btn" value="save" />
								</div>
							</div>
						</s:div>
					</s:form>
				</div>
			</div>
		</div>
		<div class="center2">
			<div class="">
				<table id="myTable" class="table-striped table-bordered table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Date Abonnement</th>
							<th>Solde</th>
							<th>Type</th>
							<th>Etat</th>
							<th>Débit</th>
							<th>Fidelio</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>ID</th>
							<th>Date Abonnement</th>
							<th>Solde</th>
							<th>Type</th>
							<th>Etat</th>
							<th>Débit</th>
							<th>Fidelio</th>
						</tr>
					</tfoot>
					<tbody>
						<s:iterator value="abonnements">
							<tr>
								<td><s:property value="idAbonnement" /></td>
								<td><s:property value="dateAbonnement" /></td>
								<td><s:property value="solde" /></td>
								<td><s:property value="class.simpleName" /></td>
								<td><s:property value="actif" /></td>
								<td><s:property value="debit" /></td>
								<td><s:property value="fidelio" /></td>
								<s:url namespace="/" action="update" var="lien1">
									<s:param name="refUpdate">
										<s:property value="idAbonnement" />
									</s:param>
								</s:url>
								<s:url namespace="/" action="delete" var="lien2">
									<s:param name="refDelete">
										<s:property value="idAbonnement" />
									</s:param>
								</s:url>
								<td><s:a href="%{lien1}">Update</s:a></td>
								<td><s:a href="%{lien2}" style="color:red">Delete</s:a></td>
								<%-- <td><button type="submit" value="update"
										class="btn btn-primary btn-sm">Update</button></td>
								<td><s:submit value="delete"></s:submit></td> --%>

							</tr>
						</s:iterator>
					</tbody>
				</table>

			</div>
		</div>
	</div>
</body>
</html>