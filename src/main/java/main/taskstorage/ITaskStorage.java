package main.taskstorage;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public interface ITaskStorage extends IArithmeticalTaskStorage, IKillYourselfTaskStorage {

	//public List<Integer> getArithmeticalTask(String string);

	public void setCountDownLatch(Long id, CountDownLatch countDownLatch);

	public void reserveTaskDeque(String robotType, Long robotId);

	public List<Integer> getArithmeticalTask(String taskType, Long robotId);

	public void getRobotIdsByTaskType(String taskType);

	public boolean hasPersonalKillYourselfTask(Long id);

	public void deleteRobotData(String string, Long id);

	public Long getTasksAmount(String robotType);

	public List<Integer> getPublicArithmeticalTask(String robotType);

	public boolean hasPublicKillYourselfTask();

	public void setActivityTrackerCountDownLatch(CountDownLatch activityTrackerCountDownLatch);

}
