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
 * Servlet implementation class ArithmeticalTaskToAllRobotsAddingServlet
 */
@WebServlet("/arithmeticalTaskToAllRobotsAddingServlet")
public class ArithmeticalTaskToAllRobotsAddingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRobotsWorldGame robotsWorldGame = Configuration.frontController;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskType = request.getParameter("taskType");
		String digits = request.getParameter("digits");
		if (taskType != null || digits != null) {
			//TODO ??? parameters validation?
			robotsWorldGame.addArithmeticalTaskToAllRobots(taskType, digits);;
		} else {
			System.err.println("taskType, or digits is null!!!");
		}
	}

}
