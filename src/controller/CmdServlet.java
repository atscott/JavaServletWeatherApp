package controller;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;

/**
 * Servlet implementation class CmdServlet
 */
public class CmdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, CommandJSPWrapper> commands;
	private String error = "error.jsp";
	private String jspdir = "/";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		initCommands();
	}

	private void initCommands() {
		commands = new HashMap<String, CommandJSPWrapper>();
		commands.put("time", new CommandJSPWrapper(new TimeCommand(), jspdir
				+ "time.jsp", "Time"));
		commands.put("requestInfo", new CommandJSPWrapper(
				new DisplayRequestInfoCommand(), jspdir + "requestInfo.jsp",
				"Client Info"));
		commands.put("weather", new CommandJSPWrapper(new WeatherCommand(),
				jspdir + "weather.jsp", "Weather"));
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CommandJSPWrapper cmd = lookupCommand(request.getParameter("cmd"));
		try {
			if (cmd == null) {
				cmd = new CommandJSPWrapper(new CommandNotFoundCommand(),
						jspdir + "notFound.jsp", "Not Found");
			}
			cmd.command.execute(request, response);

		} catch (CommandException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.toString());
			cmd.AssociatedJspBody = jspdir + error;
			cmd.PageTitle = "Error";
		}
		forwardRequestResponse(request, response, cmd);
	}
	private void forwardRequestResponse(HttpServletRequest request,
			HttpServletResponse response, CommandJSPWrapper jspInfo)
			throws ServletException, IOException {
		request.setAttribute("jspBody", jspInfo.AssociatedJspBody);
		request.setAttribute("pageTitle", jspInfo.PageTitle);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(jspdir + "template.jsp");
		rd.forward(request, response);
	}

	private CommandJSPWrapper lookupCommand(String parameter) {
		return commands.get(parameter);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CmdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

}
