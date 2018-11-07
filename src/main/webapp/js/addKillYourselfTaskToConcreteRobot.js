/**
 * 
 */
function addKillYourselfTaskToConcreteRobot() {
	 //alert($("#robotId").val());
	$.ajax({
		method : 'POST',
		url : 'killYourselfTaskToConcreteRobotAddingServlet',
		data : {
			robotId: $("#robotId").val()
		}
	});
	// alert("function sendTask() ended");
}
