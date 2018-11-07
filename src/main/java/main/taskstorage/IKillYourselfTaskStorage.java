package main.taskstorage;

public interface IKillYourselfTaskStorage {
	
	public void addKillYourselfTaskToConcreteRobot(Long robotId);

	public void addKillYourselfTaskToAllRobots();
}
