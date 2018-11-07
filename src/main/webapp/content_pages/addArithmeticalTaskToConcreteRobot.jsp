<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p>This is an addArithmeticalTaskToConcreteRobot</p>
<p>Select concrete robot to add task:</p>
<select id="taskType">
<option>Add</option>
<option>Multiply</option>
</select>
<input id="robotId" type="text" />
<%-- <select id="robotId">
		<c:forEach items=getRobotIdsByType($("#taskType").val()) var="robotIdVar">
			<option value="${robotIdVar}">${robotIdVar}</option>
		</c:forEach>
	</select> --%>
<!-- <select id="robotId">
<option>1</option>
<option>2</option>
</select> -->
<input id="digits" type="text" />
<button type="button" onclick=addArithmeticalTaskToConcreteRobot()>Add Task</button>
