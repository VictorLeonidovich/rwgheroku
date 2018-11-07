package main.robotstorage;

import java.util.List;

public interface IRobotStorage {
	public void addRobotToStorage(Thread robotThread);

	public String getRobotTypes();

	public List<String> getRobotIdsByType(String robotType);

	public boolean hasRobotOfType(String robotType);

	public List<String> getTypesOfDiedRobots();

	public Long getRobotsAmount(String robotType);
}
