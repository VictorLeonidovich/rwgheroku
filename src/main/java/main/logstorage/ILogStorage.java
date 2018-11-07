package main.logstorage;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public interface ILogStorage {
	public void addStringLog(String stringLog);

	public String getStringLog();

	public List<String> getAllStringLogs();

	public void setCountDownLatch(CountDownLatch countDownLatch);
}
