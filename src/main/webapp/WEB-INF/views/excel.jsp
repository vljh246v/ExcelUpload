<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
#drop {
	border: 2px dashed #bbb;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	padding: 25px;
	text-align: center;
	font: 20pt bold, "Vollkorn";
	color: #bbb
}

#b64data {
	width: 100%;
}

a {
	text-decoration: none
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script lang="javascript"
	src="/resources/js/xlsx.full.min.js"></script>
<script lang="javascript" src="/resources/js/shim.min.js"></script>
<script src="/resources/js/canvas-datagrid.js"></script>
<script type="text/javascript" src="/resources/js/excelController.js"></script>
</head>
<body onload="init()">


<form id='formid' action="/uploadExcelFile" method="POST" enctype="multipart/form-data"> 
	<input id='xlf' type='file' name='xlfile' onchange="changeDialog(this)" hidden/>
	<input id='buttonid' type='button' value='select excel file' onclick="openDialog()"/> 
</form> 

<div id="excelContainer">
	
</div>

<button id="openExcelUpLoad" onclick="submitExcle()">Excel Upload</button>
</body>
</html>