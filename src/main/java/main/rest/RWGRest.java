package main.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import main.classes.Configuration;
import main.interfaces.IRobotsWorldGame;

@Path("/action")
public class RWGRest {
	private IRobotsWorldGame robotsWorldGame = Configuration.frontController;

	@Path("/addRobotByType/{stringRobotType}")
	public String addRobotByType(@PathParam("stringRobotType") String stringRobotType) {
		robotsWorldGame.addRobotByType(stringRobotType);
		return "Successful operation: addRobotByType/" + stringRobotType;
	}

	@Path("/addArithmeticalTaskToAllRobots/{taskType}/{stringDigits}")
	public String addArithmeticalTaskToAllRobots(@PathParam("taskType") String taskType,
			@PathParam("stringDigits") String stringDigits) {
		robotsWorldGame.addArithmeticalTaskToAllRobots(taskType, stringDigits);
		return "Successful operation: addArithmeticalTaskToAllRobots/" + taskType + "/" + stringDigits;
	}

	@Path("/addArithmeticalTaskToConcreteRobot/{stringRobotId}/{taskType}/{stringDigits}")
	public String addArithmeticalTaskToConcreteRobot(@PathParam("stringRobotId") String stringRobotId,
			@PathParam("taskType") String taskType, @PathParam("stringDigits") String stringDigits) {
		robotsWorldGame.addArithmeticalTaskToConcreteRobot(stringRobotId, taskType, stringDigits);
		return "Successful operation: addArithmeticalTaskToConcreteRobot/" + stringRobotId + "/" + taskType + "/"
				+ stringDigits;
	}

	@Path("/addKillYourselfTaskToAllRobots")
	public String addKillYourselfTaskToAllRobots() {
		robotsWorldGame.addKillYourselfTaskToAllRobots();
		return "Successful operation: addKillYourselfTaskToAllRobots";
	}

	@Path("/addKillYourselfTaskToConcreteRobot/{stringRobotId}")
	public String addKillYourselfTaskToConcreteRobot(@PathParam("stringRobotId") String stringRobotId) {
		robotsWorldGame.addKillYourselfTaskToConcreteRobot(stringRobotId);
		return "Successful operation: addArithmeticalTaskToConcreteRobot/" + stringRobotId;
	}
}
