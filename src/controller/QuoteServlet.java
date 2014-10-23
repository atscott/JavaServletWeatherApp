package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandException;
import command.StockCommand;

/**
 * Servlet implementation class QuoteServlet
 */
public class QuoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jspdir = "/";
	private String error = "error.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CommandJSPWrapper cmd = new CommandJSPWrapper(new StockCommand(),
				jspdir + "stocks.jsp", "Stocks");
		try {
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

}
