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
 * Servlet implementation class AddOwnRobotServlet
 */
@WebServlet("/addRobotByTypeServlet")
public class AddRobotByTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRobotsWorldGame robotsWorldGame = Configuration.frontController;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html");
		// response.setCharacterEncoding("UTF-8");
		String robotType = request.getParameter("robotType");
		System.out.println("robotType=" + robotType);
		if (robotType != null) {
			// TODO ??? parameters validation?
			robotsWorldGame.addRobotByType(robotType);
		} else {
			System.err.println("robotType is null!!!");
		}

	}

}
