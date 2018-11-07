package main.taskstorage;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import main.classes.ActivityTracker;

public class TaskStorage implements ITaskStorage {
	// private final Map<String, Deque<List<Integer>>> taskStorage = new
	// ConcurrentSkipListMap<>();
	private CountDownLatch activityTrackerCountDownLatch;
	private final Map<Long, CountDownLatch> countDownLatches = new ConcurrentSkipListMap<>();
	private final Map<String, Map<Long, Deque<List<Integer>>>> personalArithmeticalTaskStorage = new ConcurrentSkipListMap<>();
	private final Map<Long, Boolean> personalKillMyselfTaskStorage = new ConcurrentSkipListMap<>();

	private final Map<String, Deque<List<Integer>>> publicArithmeticalTaskStorage = new ConcurrentSkipListMap<>();
	private final Deque<Boolean> publicKillMyselfTaskStorage = new ConcurrentLinkedDeque<Boolean>();
	private final Map<String, AtomicLong> publicArithmeticalTasksAmount = new ConcurrentSkipListMap<>();
	private final AtomicLong publicKillMyselTasksAmount = new AtomicLong(0L);
	Log log = LogFactory.getLog(TaskStorage.class);

	public TaskStorage() {
		// taskStorage.put("add", new ConcurrentLinkedDeque<List<Integer>>());
		// taskStorage.put("multiply", new
		// ConcurrentLinkedDeque<List<Integer>>());
		personalArithmeticalTaskStorage.put("add", new ConcurrentSkipListMap<Long, Deque<List<Integer>>>());
		personalArithmeticalTaskStorage.put("multiply", new ConcurrentSkipListMap<Long, Deque<List<Integer>>>());
		publicArithmeticalTaskStorage.put("add", new ConcurrentLinkedDeque<List<Integer>>());
		publicArithmeticalTaskStorage.put("multiply", new ConcurrentLinkedDeque<List<Integer>>());
		publicArithmeticalTasksAmount.put("add", new AtomicLong(0L));
		publicArithmeticalTasksAmount.put("multiply", new AtomicLong(0L));
	}

	@Override
	public void addKillYourselfTaskToConcreteRobot(Long robotId) {
		if (personalKillMyselfTaskStorage.containsKey(robotId)) {
			personalKillMyselfTaskStorage.put(robotId, true);
		}
		CountDownLatch countDownLatch = countDownLatches.get(robotId);
		if (countDownLatch != null) {
			countDownLatch.countDown();
		}
	}

	@Override
	public synchronized void addKillYourselfTaskToAllRobots() {
		publicKillMyselfTaskStorage.addFirst(true);
		increasePublicKillYourselfTasksAmount();
		for (CountDownLatch countDownLatch : countDownLatches.values()) {
			if (countDownLatch != null) {
				//if (countDownLatch.getCount() > 0) {
					countDownLatch.countDown();
					// TODO may be break
				//}

			}
		}
		if (activityTrackerCountDownLatch != null) {
			activityTrackerCountDownLatch.countDown();
		}
		// old implementation
		/*
		 * for (Map.Entry<Long, Boolean> killMyselfTaskStorageElement :
		 * personalKillMyselfTaskStorage.entrySet()) {
		 * addKillYourselfTaskToConcreteRobot(killMyselfTaskStorageElement.
		 * getKey()); }
		 */

	}

	private void increasePublicKillYourselfTasksAmount() {
		publicKillMyselTasksAmount.getAndIncrement();
	}

	@Override
	public void addArithmeticalTaskToConcreteRobot(Long robotId, String taskType, List<Integer> digits)
			throws NoRobotInStorageException {
		// System.out.println("!taskStorage.get(taskType).isEmpty(): " +
		// !taskStorage.get(taskType).isEmpty());
		// System.out.println(
		// "taskStorage.get(taskType).containsKey(robotId): " +
		// taskStorage.get(taskType).containsKey(robotId));
		if (!personalArithmeticalTaskStorage.get(taskType).isEmpty()
				&& personalArithmeticalTaskStorage.get(taskType).containsKey(robotId)) {
			// TODO code repeating
			personalArithmeticalTaskStorage.get(taskType).get(robotId).addFirst(digits);
			// increasePublicArithmeticalTasksAmount(taskType);
			System.out.println("Task was added to robot with id=" + robotId);
			CountDownLatch countDownLatch = countDownLatches.get(robotId);
			if (countDownLatch != null) {
				countDownLatch.countDown();
			}
		} else {
			throw new NoRobotInStorageException("Does not exist robot with [id=" + robotId + "] in robotStorage!");
		}
	}

	private void increasePublicArithmeticalTasksAmount(String taskType) {
		publicArithmeticalTasksAmount.get(taskType).incrementAndGet();
	}

	@Override
	public synchronized void addArithmeticalTaskToAllRobots(String taskType, List<Integer> digits)
			throws NoRobotInStorageException {
		publicArithmeticalTaskStorage.get(taskType).addFirst(digits);
		increasePublicArithmeticalTasksAmount(taskType);
		// TODO repeat
		for (CountDownLatch countDownLatch : countDownLatches.values()) {
			if (countDownLatch != null) {
				//if (countDownLatch.getCount() > 0) {
					countDownLatch.countDown();
					// TODO may be break
				//}

			}
		}
		if (activityTrackerCountDownLatch != null) {
			activityTrackerCountDownLatch.countDown();
		}
		/*
		 * if (!personalArithmeticalTaskStorage.get(taskType).isEmpty()) { for
		 * (Map.Entry<Long, Deque<List<Integer>>> mappedByIdTaskStorage :
		 * personalArithmeticalTaskStorage.get(taskType) .entrySet()) {
		 * personalArithmeticalTaskStorage.get(taskType).get(
		 * mappedByIdTaskStorage.getKey()).addFirst(digits);
		 * increaseArithmeticalTasksAmount(taskType); System.out.println(
		 * "Task was added to robot with id=" + mappedByIdTaskStorage.getKey());
		 * CountDownLatch countDownLatch =
		 * countDownLatches.get(mappedByIdTaskStorage.getKey()); if
		 * (countDownLatch != null) { countDownLatch.countDown(); } } } else {
		 * throw new NoRobotInStorageException(
		 * "No robot exists in robotStorage!"); }
		 */

		// taskStorage.get(taskType).addFirst(digits);
		// countDownLatches.get(id).countDown();
	}

	@Override
	public List<Integer> getArithmeticalTask(String robotType, Long robotId) {
		String taskType = null;
		switch (robotType) {
		case "adder":
			taskType = "add";
			break;
		case "multiplier":
			taskType = "multiply";
			break;
		default:
			System.err.println("From getArithmeticalTask() - robotType not supported yet: " + robotType);
			break;
		}
		// TODO null
		List<Integer> digits = null;
		if (taskType != null) {
			digits = personalArithmeticalTaskStorage.get(taskType).get(robotId).pollLast();
			// decreasePublicArithmeticalTasksAmount(taskType);
		}
		return digits;
	}

	private void decreasePublicArithmeticalTasksAmount(String taskType) {
		publicArithmeticalTasksAmount.get(taskType).decrementAndGet();
	}

	@Override
	public void setCountDownLatch(Long id, CountDownLatch countDownLatch) {
		countDownLatches.put(id, countDownLatch);
	}

	@Override
	public void reserveTaskDeque(String robotType, Long robotId) {
		// TODO if contains key
		personalKillMyselfTaskStorage.put(robotId, false);
		switch (robotType) {
		case "adder":
			personalArithmeticalTaskStorage.get("add").put(robotId, new ConcurrentLinkedDeque<List<Integer>>());
			break;
		case "multiplier":
			personalArithmeticalTaskStorage.get("multiply").put(robotId, new ConcurrentLinkedDeque<List<Integer>>());
			break;
		default:
			System.err.println("From reserveTaskDeque() - robotType not supported yet: " + robotType);
			break;
		}
		if (!publicArithmeticalTaskStorage.containsKey(robotType)) {
			publicArithmeticalTaskStorage.put(robotType, new ConcurrentLinkedDeque<List<Integer>>());
		}

	}

	@Override
	public void getRobotIdsByTaskType(String taskType) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasPersonalKillYourselfTask(Long robotId) {
		if (personalKillMyselfTaskStorage.containsKey(robotId)) {
			return personalKillMyselfTaskStorage.get(robotId);
		}
		return false;
	}

	@Override
	public synchronized void deleteRobotData(String robotType, Long robotId) {
		String taskType = null;
		if (getTaskTypeByRobotType(robotType) != null && robotId != null) {
			taskType = getTaskTypeByRobotType(robotType);
			Integer lostTaskAmount = personalArithmeticalTaskStorage.get(taskType).get(robotId).size();
			decreaseArithmeticalTasksAmount(taskType, lostTaskAmount);
			personalArithmeticalTaskStorage.get(taskType).remove(robotId);
			personalKillMyselfTaskStorage.remove(robotId);
			System.out.println("arithmeticalTaskStorage=" + personalArithmeticalTaskStorage.toString()
					+ "\\n killMyselfTaskStorage=" + personalKillMyselfTaskStorage.toString());
			// TODO aware activity tracker
			// TODO delete robot by id in robotStorage
			if (activityTrackerCountDownLatch != null) {
				activityTrackerCountDownLatch.countDown();
			}			
		} else {
			System.err.println("From deleteRobotData() - null: " + taskType + " : " + robotId);
		}

	}

	private synchronized void decreaseArithmeticalTasksAmount(String taskType, Integer lostTaskAmount) {
		for (int i = 0; i < lostTaskAmount; i++) {
			decreasePublicArithmeticalTasksAmount(taskType);
		}

	}

	private String getTaskTypeByRobotType(String robotType) {
		String taskType = null;
		switch (robotType) {
		case "adder":
			taskType = "add";
			break;
		case "multiplier":
			taskType = "multiply";
			break;
		default:
			System.err.println("From reserveTaskDeque() - robotType not supported yet: " + robotType);
			break;
		}
		return taskType;
	}

	@Override
	public synchronized Long getTasksAmount(String robotType) {
		log.debug("publicArithmeticalTasksAmount="
				+ publicArithmeticalTasksAmount.get(getTaskTypeByRobotType(robotType)).get()
				+ ", publicKillMyselTasksAmount=" + publicKillMyselTasksAmount.get());

		Long publicTasksAmount = publicArithmeticalTasksAmount.get(getTaskTypeByRobotType(robotType)).get()
				+ publicKillMyselTasksAmount.get();
		return publicTasksAmount;
	}

	@Override
	public List<Integer> getPublicArithmeticalTask(String robotType) {
		List<Integer> digits = publicArithmeticalTaskStorage.get(getTaskTypeByRobotType(robotType)).pollLast();
		if (digits != null) {
			decreasePublicArithmeticalTasksAmount(getTaskTypeByRobotType(robotType));
		}
		return digits;
	}

	@Override
	public boolean hasPublicKillYourselfTask() {
		Boolean hasPublicKillYourselfTask = publicKillMyselfTaskStorage.pollLast();
		if (hasPublicKillYourselfTask != null) {
			decreasePublicKillYourselfTask();
			return hasPublicKillYourselfTask;
		}
		return false;
	}

	private void decreasePublicKillYourselfTask() {
		publicKillMyselTasksAmount.getAndDecrement();

	}

	@Override
	public void setActivityTrackerCountDownLatch(CountDownLatch activityTrackerCountDownLatch) {
		this.activityTrackerCountDownLatch = activityTrackerCountDownLatch;
		
	}

}
