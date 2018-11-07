package main.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import main.interfaces.IRobotsWorldGame;
import main.logstorage.ILogStorage;
import main.logstorage.LogStorage;
import main.robotstorage.Adder;
import main.robotstorage.IRobot;
import main.robotstorage.IRobotStorage;
import main.robotstorage.Multiplier;
import main.robotstorage.RobotStorage;
import main.taskstorage.ITaskStorage;
import main.taskstorage.NoRobotInStorageException;
import main.taskstorage.TaskStorage;

public class FrontController implements IRobotsWorldGame {
	private final ILogStorage logs;
	private final ITaskStorage tasks;
	private final IRobotStorage robots;

	private final ActivityTracker activityTracker;
	private final Thread activityTrackerThread;

	public ILogStorage getLogs() {
		return logs;
	}

	public FrontController() {
		this.logs = new LogStorage(new ConcurrentLinkedDeque<>());
		this.tasks = new TaskStorage();
		this.robots = new RobotStorage();

		this.activityTracker = new ActivityTracker(tasks, robots, logs);
		activityTrackerThread = new Thread(activityTracker);
		activityTrackerThread.setDaemon(true);
		activityTrackerThread.start();
	}

	@Override
	public void addRobotByType(String stringRobotType) {
		activityTracker.addRobotByType(stringRobotType);

	}

	@Override
	public void addArithmeticalTaskToConcreteRobot(String stringRobotId, String taskType, String stringDigits) {
		// TODO validate
		Long robotId = Long.parseLong(stringRobotId);
		List<Integer> digits = getDigits(stringDigits);
		try {
			tasks.addArithmeticalTaskToConcreteRobot(robotId, taskType.toLowerCase(), digits);
			logs.addStringLog("Arithmetical task [task type=" + taskType + ", digits=" + digits
					+ "] was added successfully to robot [id=" + robotId + "].");
		} catch (NoRobotInStorageException e) {
			logs.addStringLog(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void addArithmeticalTaskToAllRobots(String taskType, String stringDigits) {
		// TODO validate
		List<Integer> digits = getDigits(stringDigits);
		try {
			tasks.addArithmeticalTaskToAllRobots(taskType.toLowerCase(), digits);
			logs.addStringLog("Arithmetical task [task type=" + taskType + ", digits=" + digits
					+ "] was added successfully to all robots.");
		} catch (NoRobotInStorageException e) {
			logs.addStringLog(e.getMessage());
			e.printStackTrace();
		}

	}

	private List<Integer> getDigits(String stringDigits) {
		List<Integer> digits = new ArrayList<>();
		String[] stringDigitsArray = stringDigits.split(",");
		for (String stringDigit : stringDigitsArray) {
			digits.add(Integer.parseInt(stringDigit));
		}
		return digits;
	}

	@Override
	public void addKillYourselfTaskToConcreteRobot(String stringRobotId) {
		Long robotId = Long.parseLong(stringRobotId);
		// TODO add condition -- if robot presents
		tasks.addKillYourselfTaskToConcreteRobot(robotId);
		logs.addStringLog("Task [task type=Kill yourself] was added successfully to robot [id=" + robotId + "].");

	}

	@Override
	public void addKillYourselfTaskToAllRobots() {
		// TODO add condition -- if robot is exists
		tasks.addKillYourselfTaskToAllRobots();
		logs.addStringLog("Task [task type=Kill yourself] was added successfully to all robots.");

	}

	@Override
	public String[] getRobotIdsByTaskType(String taskType) {
		tasks.getRobotIdsByTaskType(taskType.toLowerCase());
		return null;
	}

}
