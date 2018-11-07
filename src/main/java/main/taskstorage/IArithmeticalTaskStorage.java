package main.taskstorage;

import java.util.List;

public interface IArithmeticalTaskStorage {

	//public void addArithmeticalTaskToConcreteRobot(Integer robotId, TaskType taskType, List<Integer> digits);

	public void addArithmeticalTaskToAllRobots(String taskType, List<Integer> digits) throws NoRobotInStorageException;

	public void addArithmeticalTaskToConcreteRobot(Long robotId, String taskType, List<Integer> digits) throws NoRobotInStorageException;

}
