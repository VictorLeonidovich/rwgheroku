/**
 * 
 */
function getLogsFromServlet() {
	// alert("getResponse()");
	if (typeof (EventSource) !== "undefined") {
		var source = new EventSource('logsServlet');
		source.onmessage = function(event) {
			var data = event.data;
			// alert(data);
			document.getElementById("logs").innerHTML += event.data + "\r\n";
		}
	} else {
		document.getElementById("logs").innerHTML = "Sorry, your browser does not suport server sent events...";
	}
}