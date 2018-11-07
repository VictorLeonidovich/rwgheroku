/**
 * 
 */
function addKillYourselfTaskToAllRobots() {
	 //alert("addKillYourselfTaskToAllRobots");
	$.ajax({
		method : 'POST',
		url : 'killYourselfTaskToAllRobotsAddingServlet',
		
	});
	// alert("function sendTask() ended");
}
