<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>File Upload</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
<style type="text/css">
#filedrag {
	display: none;
	font-weight: bold;
	text-align: center;
	padding: 1em 0;
	margin: 1em 0;
	color: #555;
	border: 2px dashed #555;
	border-radius: 7px;
	cursor: default;
}

#filedrag.hover {
	color: #f00;
	border-color: #f00;
	border-style: solid;
	box-shadow: inset 0 3px 4px #888;
}
</style>
</head>
<body>

	<form id="upload" action="/upload" method="POST"
		enctype="multipart/form-data">
		<fieldset>
			<legend>HTML File Upload</legend>

			<div>
				<label for="fileselect">Files to upload:</label> 
				<input type="file" id="fileselect" name="file" multiple="multiple" onchange="handleFiles()"/>
				<div id="filedrag">or drop files here</div>
			</div>

			<div id="submitbutton">
				<input type="submit" value="Submit" />
			</div>

		</fieldset>

	</form>

	<div id="messages">
		<p>Status Messages</p>
	</div>
	<script type="text/javascript">
	function handleFiles(){
		console.log("Fds");
	}
		// getElementById
		function $id(id) {
		return document.getElementById(id);
		}

		//
		// output information
		function Output(msg) {
		var m = $id("messages");
		m.innerHTML = msg + m.innerHTML;
		}


		// call initialization file
		if (window.File && window.FileList && window.FileReader) {
		Init();
		}

		//
		// initialize
		function Init() {

		var fileselect = $id("fileselect"),
			filedrag = $id("filedrag"),
			submitbutton = $id("submitbutton");

		// file select
		fileselect.addEventListener("change", FileSelectHandler, false);

		// is XHR2 available?
		var xhr = new XMLHttpRequest();
		if (xhr.upload) {

			// file drop
			filedrag.addEventListener("dragover", FileDragHover, false);
			filedrag.addEventListener("dragleave", FileDragHover, false);
			filedrag.addEventListener("drop", FileSelectHandler, false);
			filedrag.style.display = "block";
			
			// remove submit button
			//submitbutton.style.display = "none";
		}

		}

		// file drag hover
		function FileDragHover(e) {
			e.stopPropagation();
			e.preventDefault();
			e.target.className = (e.type == "dragover" ? "hover" : "");
		}


		// file selection
		function FileSelectHandler(e) {

		// cancel event and hover styling
		FileDragHover(e);

		// fetch FileList object
		var files = e.target.files || e.dataTransfer.files;

		// process all File objects
		for (var i = 0, f; f = files[i]; i++) {
			ParseFile(f);
		}

		}
		function ParseFile(file) {

		Output(
			"<p>File information: <strong>" + file.name +
			"</strong> type: <strong>" + file.type +
			"</strong> size: <strong>" + file.size +
			"</strong> bytes</p>"
		);

		}
		</script>
</body>
</html>