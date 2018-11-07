package main.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import main.classes.Configuration;
import main.interfaces.IRobotsWorldGame;

/**
 * Application Lifecycle Listener implementation class RobotIdListener
 *
 */
@WebListener
public class RobotIdListener implements ServletContextListener {
	//private IRobotsWorldGame robotsWorldGame = Configuration.frontController;
    /**
     * Default constructor. 
     */
    public RobotIdListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent context)  { 
    	//String[] robotIds = robotsWorldGame.getRobotIdsByTaskType(taskType);
		
		//context.getServletContext().setAttribute("robotIds", robotIds);
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
