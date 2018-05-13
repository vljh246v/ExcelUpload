<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script lang="javascript" src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
	<script lang="javascript" src="https://unpkg.com/xlsx/dist/shim.min.js"></script>
	<script src="https://unpkg.com/canvas-datagrid/dist/canvas-datagrid.js"></script>
	<script type="text/javascript" src="/resources/js/excelController.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>


<form id='formid' action="form.php" method="POST" enctype="multipart/form-data"> 
	<input id='xlf' type='file' name='filename' onchange="changeDialog()" hidden/>
	<input id='buttonid' type='button' value='Upload MB' onclick="openDialog()"/> 
</form> 

<button id="openExcelUpLoad" onclick="submitExcle()">Excel Upload</button>
	
</body>
</html>
