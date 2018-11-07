package main.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/contentServlet")
public class ContentServlet extends HttpServlet {
	// private PrintWriter out;
	private static final long serialVersionUID = 1L;
	private final String homePage = "This is a Home page";
	private final String restApi = "This is a RESTful API";
	private final String action = "This is an Action";
	private final String add_robot = "This is an Add_robot";
	private final String send_task = "This is a Send_task";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContentServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		//PrintWriter out = response.getWriter();
		System.out.println("Hello from ContentServlet: " + content);
		if (content == null) {
			System.err.println("Content is null");
			return;
		}
		System.out.println("sendRedirect: " + request.getContextPath() + "/content_pages/" + content + ".jsp");
		response.sendRedirect(request.getContextPath() + "/content_pages/" + content + ".jsp");
		
		/*switch (content) {		
		case "home":
			System.out.println("sendRedirect: " + request.getContextPath() + "/content_pages/" + content + ".jsp");
			response.sendRedirect(request.getContextPath() + "/home.jsp"request.getContextPath() + "/content_pages/" + content + ".jsp");
			//out.write(homePage);
			break;
		case "rest":
			//out.write(restApi);
			break;
		case "Action":
			//out.write(action);
			break;
		case "Add_robot":
			//out.write(add_robot);
			break;
		case "Send_task":
			//out.write(send_task);
			break;
		default:
			System.err.println("Not supported yet: " + content);
			break;
		}		*/
		//out.close();
	}

}
