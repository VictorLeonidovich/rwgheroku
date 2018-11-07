package main.logstorage;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LogStorage implements ILogStorage {
	private final Deque<String> logsStorage;
	private CountDownLatch countDownLatch;

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	public LogStorage(Deque<String> logsStorage) {
		this.logsStorage = logsStorage;
	}

	@Override
	public void addStringLog(String stringLog) {
		logsStorage.addFirst(stringLog);
		if (countDownLatch != null) {
			countDownLatch.countDown();
		}
	}

	@Override
	public String getStringLog() {
		String log = logsStorage.pollLast();
		/*
		 * if (log == null) { log = "no message."; // TODO delete "no message."
		 * and not use null }
		 */
		System.out.println("Log sended: " + log);
		return log;
	}

	@Override
	public List<String> getAllStringLogs() {
		List<String> logs = new ArrayList<>();
		String log;
		synchronized (logsStorage) {
			while ((log = getStringLog()) != "no message.") { // TODO delete "no
																// message." and
																// not use null
				logs.add(log);
			}
		}

		return logs;
	}
}
