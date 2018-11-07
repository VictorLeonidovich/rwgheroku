package main.robotstorage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RobotStorage implements IRobotStorage {
	private Log log = LogFactory.getLog(RobotStorage.class);
	private final Map<String, List<Thread>> robotStorage = new ConcurrentSkipListMap<>();
	private final Map<String, AtomicLong> robotAmount = new ConcurrentSkipListMap<>();
	// private final List<Thread> robotStorage = new CopyOnWriteArrayList<>();

	public RobotStorage() {
		robotStorage.put("adder", new CopyOnWriteArrayList<Thread>());
		robotStorage.put("multiplier", new CopyOnWriteArrayList<Thread>());
		robotAmount.put("adder", new AtomicLong(0L));
		robotAmount.put("multiplier", new AtomicLong(0L));
	}

	@Override
	public void addRobotToStorage(Thread robotThread) {
		log.debug("Robot name = " + robotThread.getName());
		robotStorage.get(robotThread.getName()).add(robotThread);
		increaseRobotsAmount(robotThread.getName());
	}

	private void increaseRobotsAmount(String robotType) {
		robotAmount.get(robotType).incrementAndGet();
	}

	@Override
	public String getRobotTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRobotIdsByType(String robotType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRobotOfType(String robotType) {
		return robotAmount.get(robotType).get() != 0;
	}

	// TODO synchronization!!!
	@Override
	public synchronized List<String> getTypesOfDiedRobots() {
		List<String> diedRobotsTypes = new ArrayList<>();
		List<Thread> diedThreads = new ArrayList<>();
		for (String robotType : robotStorage.keySet()) {
			for (Thread robotThread : robotStorage.get(robotType)) {
				if (!robotThread.isAlive()) {
					diedThreads.add(robotThread);
					diedRobotsTypes.add(robotThread.getName());

				}
			}
			/*
			 * Iterator<Thread> robotStorageIterator =
			 * robotStorage.get(robotType).iterator(); while
			 * (robotStorageIterator.hasNext()) { Thread robotThread =
			 * robotStorageIterator.next(); if (!robotThread.isAlive()) {
			 * diedRobots.add(robotType); robotStorageIterator.remove();
			 * decrementRobotsAmount(robotType); } }
			 */
			robotStorage.get(robotType).removeAll(diedThreads);
			decrementRobotsAmount(robotType, diedThreads.size());
		}

		return diedRobotsTypes;
	}

	private void decrementRobotsAmount(String robotType, int robotsAmount) {
		for (int i = 0; i < robotsAmount; i++) {
			decrementRobotsAmount(robotType);
		}

	}

	private void decrementRobotsAmount(String robotType) {
		robotAmount.get(robotType).decrementAndGet();
	}

	@Override
	public Long getRobotsAmount(String robotType) {
		return robotAmount.get(robotType).get();
	}

}
