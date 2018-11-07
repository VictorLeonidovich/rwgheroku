/**
 * 
 */
function addRobotByType() {
	 //alert($("#robotType").val());
	$.ajax({
		method : 'POST',
		url : 'addRobotByTypeServlet',
		data : {
			robotType : $("#robotType").val()
		}
	});
	// alert("function sendTask() ended");
}