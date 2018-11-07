package main.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.classes.Configuration;
import main.interfaces.IRobotsWorldGame;

/**
 * Servlet implementation class ArithmeticalTaskToConcreteRobotAdding
 */
@WebServlet("/arithmeticalTaskToConcreteRobotAddingServlet")
public class ArithmeticalTaskToConcreteRobotAddingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRobotsWorldGame robotsWorldGame = Configuration.frontController;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String robotId = request.getParameter("robotId");
		String taskType = request.getParameter("taskType");
		String digits = request.getParameter("digits");
		if (robotId != null || taskType != null || digits != null) {
			//TODO ??? parameters validation?
			robotsWorldGame.addArithmeticalTaskToConcreteRobot(robotId, taskType, digits);
		} else {
			System.err.println("robotId, or taskType, or digits is null!!!");
		}

	}

}
