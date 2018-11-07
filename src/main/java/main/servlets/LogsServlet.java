package main.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.classes.Configuration;
import main.logstorage.ILogStorage;

/**
 * Servlet implementation class LogsServlet
 */
@WebServlet("/logsServlet")
public class LogsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String stringLog;
	private PrintWriter out;
	private ILogStorage logs = Configuration.frontController.getLogs();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/event-stream");// ;charset=UTF-8
		response.setCharacterEncoding("UTF-8");
		out = response.getWriter();
		// for (int i = 0; i < 100; i++) {
		while (true) {
			System.out.println("LogsServlet");
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//stringLog = logs.getStringLog();
			while ((stringLog = logs.getStringLog()) != null /* && !"no message.".equals(stringLog) */) {
				System.out.println("stringLog != null");
				out.write("id:" + "log" + "\n");
				out.write("data:" + stringLog + "\n\n");
				out.flush();
			}
			CountDownLatch countDownLatch = new CountDownLatch(1);
			logs.setCountDownLatch(countDownLatch);
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				System.err.println("CountDownLatch was Interrupted.");
				e.printStackTrace();
			}
		}
	}
}
