/**
 * 
 */
function addArithmeticalTaskToAllRobots() {
	// alert("button #send_task# was pressed");
	$.ajax({
		method : 'POST',
		url : 'arithmeticalTaskToAllRobotsAddingServlet',
		data : {
			taskType : $("#taskType").val(),
			digits : $("#digits").val()
		}
	});
	// alert("function sendTask() ended");
}