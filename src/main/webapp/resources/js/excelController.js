/**
 * 
 */

var X = XLSX;
var cDg;
var HTMLOUT;

function init(){
	xlf.addEventListener('change', changeDialog, false);
}

function submitExcle() {
	var objFormData = new FormData();
	
	// GET FILE OBJECT 
    var objFile = $('#xlf')[0].files[0];
    // APPEND FILE TO POST DATA
    objFormData.append('xlfile', objFile);
    $.ajax({
        url: '/uploadExcelFile',
        type: 'POST',
        contentType: false,
        data: objFormData,
        //JQUERY CONVERT THE FILES ARRAYS INTO STRINGS.SO processData:false
        processData: false,
        success: function(data) {
        	document.getElementById("excelContainer").innerHTML = '';
        	//document.getElementById("excelContainer").removeChild(document.getElementById("htmlout"));
        	cDg = null;
        	console.log(data)
        	alert('성공')
        }
    });
}

function openDialog() {
	cDg = null;
	document.getElementById("excelContainer").innerHTML = '<div id="htmlout"></div>';
	HTMLOUT = document.getElementById('htmlout');
	//document.getElementById("excelContainer").appendChild('<div id="htmlout"></div>');
	document.getElementById('xlf').click();
}
function changeDialog(e) {
	console.log('!!!!')
	do_file(e.target.files);
}

var process_wb = (function() {
	return function process_wb(wb) {
		/* get data */
		var ws = wb.Sheets[wb.SheetNames[0]];
		var data = XLSX.utils.sheet_to_json(ws, {
			header : 1
		});
		/* update canvas-datagrid */
		if (!cDg)
			cDg = canvasDatagrid({
				parentNode : HTMLOUT,
				data : data
			});
		cDg.style.height = '100%';
		cDg.style.width = '100%';
		cDg.data = data;
		/* create schema (for A,B,C column headings) */
		var range = XLSX.utils.decode_range(ws['!ref']);
		for (var i = range.s.c; i <= range.e.c; ++i)
			cDg.schema[i - range.s.c].title = XLSX.utils.encode_col(i);
		HTMLOUT.style.height = (window.innerHeight - 400) + "px";
		HTMLOUT.style.width = (window.innerWidth - 50) + "px";
		if (typeof console !== 'undefined')
			console.log("output", new Date());
	};
})();
var do_file = (function() {
	var rABS = typeof FileReader !== "undefined"
			&& (FileReader.prototype || {}).readAsBinaryString;
	var domrabs = document.getElementsByName("userabs")[0];
	return function do_file(files) {
		var f = files[0];
		var reader = new FileReader();
		reader.onload = function(e) {
			if (typeof console !== 'undefined')
				console.log("onload", new Date(), rABS);
			var data = e.target.result;
			if (!rABS)
				data = new Uint8Array(data);
			process_wb(X.read(data, {
				type : rABS ? 'binary' : 'array'
			}));
		};
		if (rABS)
			reader.readAsBinaryString(f);
		else
			reader.readAsArrayBuffer(f);
	};
})();