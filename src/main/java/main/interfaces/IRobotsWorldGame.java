package main.interfaces;

public interface IRobotsWorldGame {
public void addRobotByType(String robotType);
//TODO to delete ---String taskType,
public void addArithmeticalTaskToConcreteRobot(String robotId, String taskType, String digits);
public void addKillYourselfTaskToConcreteRobot(String id);
//TODO to delete ---String taskType,
public void addArithmeticalTaskToAllRobots(String taskType, String digits);
public void addKillYourselfTaskToAllRobots();

//TODO another interface
String[] getRobotIdsByTaskType(String taskType);

}
