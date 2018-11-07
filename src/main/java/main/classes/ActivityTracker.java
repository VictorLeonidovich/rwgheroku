package main.classes;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import main.logstorage.ILogStorage;
import main.robotstorage.Adder;
import main.robotstorage.IRobot;
import main.robotstorage.IRobotStorage;
import main.robotstorage.Multiplier;
import main.taskstorage.ITaskStorage;

public class ActivityTracker implements Runnable {
	private final IRobot adder;
	private final IRobot multiplier;
	private final String[] robotTypes = { "adder", "multiplier" };
	private final ITaskStorage tasks;
	private final IRobotStorage robots;
	private final ILogStorage logs;
	Log log = LogFactory.getLog(ActivityTracker.class);

	public ActivityTracker(ITaskStorage tasks, IRobotStorage robots, ILogStorage logs) {
		this.tasks = tasks;
		this.robots = robots;
		this.logs = logs;
		adder = new Adder(tasks, logs);
		multiplier = new Multiplier(tasks, logs);
		createRobotsIfAbsent();
	}

	private void createRobotsIfAbsent() {
		for (String robotType : robotTypes) {
			if (!hasRobotInRobotStorage(robotType)) {
				addRobotByType(robotType);
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			/*try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			createRobotsIfAbsent();
			reviveRobotsIfDied();
			checkRobotsActivity();
			//TODO
			CountDownLatch activityTrackerCountDownLatch = new CountDownLatch(1);
			tasks.setActivityTrackerCountDownLatch(activityTrackerCountDownLatch);
			try {
				activityTrackerCountDownLatch.await();
			} catch (InterruptedException e) {
				System.err.println("ActivityTrackerCountDownLatch was Interrupted.");
				e.printStackTrace();
			}
		}
	}

	private void checkRobotsActivity() {
		for (String robotType : robotTypes) {
			Long robotsAmount = robots.getRobotsAmount(robotType);
			Long tasksAmount = tasks.getTasksAmount(robotType);
			log.debug("robotsAmount=" + robotsAmount + ", tasksAmount=" + tasksAmount);
			if (tasksAmount != null) {
				if (tasksAmount.compareTo(robotsAmount) == 1) {
					for (int i = 0; i < (tasksAmount - robotsAmount); i++) {
						addRobotByType(robotType);
					}
				}
			}

		}
	}

	private void reviveRobotsIfDied() {
		List<String> typesOfDiedRobots = robots.getTypesOfDiedRobots();
		for (String robotType : typesOfDiedRobots) {
			addRobotByType(robotType);
		}
	}

	private boolean hasRobotInRobotStorage(String robotType) {
		return robots.hasRobotOfType(robotType);
	}

	public void addRobotByType(String stringRobotType) {
		Thread robotThread = null;
		switch (stringRobotType.toLowerCase()) {
		case "adder":
			robotThread = startThread(adder);
			break;
		case "multiplier":
			robotThread = startThread(multiplier);
			break;

		default:
			System.err.println(stringRobotType + " -- not suppoted yet!!!");
			break;
		}
		if (robotThread != null) {
			robots.addRobotToStorage(robotThread);
			logs.addStringLog("Robot [id=" + robotThread.getId() + ", type=" + robotThread.getName()
					+ "] was added successfully by Activity Tracker.");
			tasks.reserveTaskDeque(robotThread.getName(), robotThread.getId());

		}

	}

	private Thread startThread(IRobot robot) {
		Thread robotThread = new Thread(robot);
		robotThread.setName(robot.getClass().getSimpleName().toLowerCase());
		robotThread.setDaemon(true);
		robotThread.start();
		return robotThread;
	}

}
