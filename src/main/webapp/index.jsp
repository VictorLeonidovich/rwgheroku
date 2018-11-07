<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.12.4.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Robots World Game</title>
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<div class="container">
		<table>
			<tbody>
				<tr>
					<td>
						<nav>
						<ul id="mcd-menu" class="mcd-menu">
			<li >
				<a class="active" onclick=getContentFromContentServlet('home')>
					<i class="fa fa-home"></i>
					<strong>Home</strong>
					<small>home page</small>
				</a>
			</li>
			<li>
				<a onclick=getContentFromContentServlet('rest')>
					<i class="fa fa-edit"></i>
					<strong>REST</strong>
					<small>REST API information</small>
				</a>
			</li>
			<li>
				<a onclick=getContentFromContentServlet('addRobotByType')>
					<i class="fa fa-gift"></i>
					<strong>Add Robot</strong>
					<small>add robot by type</small>
				</a>
			</li>
			<li>
				<a onclick=getContentFromContentServlet('killConcreteRobot')>
					<i class="fa fa-globe"></i>
					<strong>Add Task</strong>
					<small>kill concrete robot</small>
				</a>
			</li>
			<li>
				<a onclick=getContentFromContentServlet('killAllRobots')>
					<i class="fa fa-comments-o"></i>
					<strong>Add Task</strong>
					<small>kill all robots</small>
				</a>
			</li>
			<li>
				<a onclick=getContentFromContentServlet('addArithmeticalTaskToConcreteRobot')>
					<i class="fa fa-picture-o"></i>
					<strong>Add Task</strong>
					<small>add arithmetical task to concrete robot</small>
				</a>
			</li>
			<li>
				<a onclick=getContentFromContentServlet('addArithmeticalTaskToAllRobots')>
					<i class="fa fa-envelope-o"></i>
					<strong>Add Task</strong>
					<small>add arithmetical task to all robots</small>
				</a>
			</li>
		</ul>
							
					 	</nav>
					</td>
					<td>
						<div>
							<span id="servlet_content"></span>
						</div>

						<div>
							<br> <span> <b>Logs:</b></span>
							<textarea id="logs" class="logs" rows="10" cols=100%></textarea>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<script src="js/getContentFromContentServlet.js" type="text/javascript"></script>
	<script src="js/addRobotByType.js" type="text/javascript"></script>
	<script src="js/addArithmeticalTaskToAllRobots.js"
		type="text/javascript"></script>
	<script src="js/addArithmeticalTaskToConcreteRobot.js"
		type="text/javascript"></script>
	<script src="js/addKillYourselfTaskToAllRobots.js"
		type="text/javascript"></script>
	<script src="js/addKillYourselfTaskToConcreteRobot.js"
		type="text/javascript"></script>
	<script src="js/getLogsFromServlet.js" type="text/javascript"></script>
	
	<script type="text/javascript">$(document).ready(function() {
		alert('$(document).ready');
		getContentFromContentServlet("home");
		getLogsFromServlet();		
		
	})
	</script>
		
		
		
	<!-- 
	<script type="text/javascript">
		$(document).ready(function() {
			alert("$(document).ready");
			getLogsFromServlet();

			function getEventTarget(e) {
				e = e || window.event;
				return e.target || e.srcElement;
			}
			var ul = document.getElementById('mcd-menu');
			//var ul = document.getElementById('content_menu');
			ul.onclick = function(event) {
				var target = getEventTarget(event);

				//getContent(target.innerHTML);
				//var target = target.getElementsByTagName("strong");
				//target = target.textContent.find('strong').text();
				//getContent(target.getElementById('string_to_servlet').innerHTML);
				//getContent(target.innerHTML.find('content_m'));
				getContent($(target).val());
			}
		})
	</script> -->


</body>
</html>