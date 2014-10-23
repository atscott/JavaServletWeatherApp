package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommandNotFoundCommand implements Command{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		String cmd = request.getParameter("cmd");
		request.setAttribute("Message", "Command [" + cmd + "] not found");
		return null;
	}
	
}
