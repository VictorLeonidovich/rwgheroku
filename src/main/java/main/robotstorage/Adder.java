package main.robotstorage;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import main.logstorage.ILogStorage;
import main.taskstorage.ITaskStorage;

public class Adder extends AbstractRobot implements IArithmeticalTask, IKillYourself {
	private volatile ILogStorage logs;
	private volatile ITaskStorage tasks;

	public Adder(ITaskStorage tasks, ILogStorage logs) {
		super(tasks, logs);
		this.tasks = tasks;
		this.logs = logs;
	}

	@Override
	public boolean killYourself() {
		tasks.deleteRobotData(Thread.currentThread().getName(), Thread.currentThread().getId());
		logs.addStringLog("Robot with id=" + Thread.currentThread().getId() + " and type=" + Thread.currentThread().getName() + " has been killed by himself.");
		//TODO delete robot by id in robotStorage
		return false;
	}

	@Override
	public void doArithmeticalTask(List<Integer> digits) {
		StringBuffer stringBuffer = new StringBuffer();
		Integer sum = 0;
		for (Integer digit : digits) {
			stringBuffer.append(digit).append(" + ");
			sum = sum + digit;
		}
		stringBuffer.delete(stringBuffer.length() - 3, stringBuffer.length());
		stringBuffer.append(" = ").append(sum);

		logs.addStringLog(stringBuffer.toString());
	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Integer> digits;
			while ((digits = tasks.getArithmeticalTask(Thread.currentThread().getName(),
					Thread.currentThread().getId())) != null) {
				if (digits != null) {
					doArithmeticalTask(digits);
				}
			}
			if (tasks.hasPersonalKillYourselfTask(Thread.currentThread().getId())) {
				killYourself();
				return;
			}
			while ((digits = tasks.getPublicArithmeticalTask(Thread.currentThread().getName())) != null) {
				if (digits != null) {
					doArithmeticalTask(digits);
				}
			}
			if (tasks.hasPublicKillYourselfTask()) {
				killYourself();
				return;
			}
			CountDownLatch countDownLatch = new CountDownLatch(1);
			tasks.setCountDownLatch(Thread.currentThread().getId(), countDownLatch);
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				System.err.println("CountDownLatch was Interrupted.");
				e.printStackTrace();
			}
		}

	}

}
