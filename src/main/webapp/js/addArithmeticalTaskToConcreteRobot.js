/**
 * 
 */
function addArithmeticalTaskToConcreteRobot() {
	// alert("button #send_task# was pressed");
	$.ajax({
		method : 'POST',
		url : 'arithmeticalTaskToConcreteRobotAddingServlet',
		data : {
			robotId : $("#robotId").val(),
			taskType : $("#taskType").val(),
			digits : $("#digits").val()
		}
	});
	// alert("function sendTask() ended");
}