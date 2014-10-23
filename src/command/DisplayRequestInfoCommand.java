package command;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayRequestInfoCommand implements Command {
	List<String> listOfOutputs = new LinkedList<String>();
	

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		listOfOutputs.clear();
		getRequestInfo(request);
		request.setAttribute("Data", listOfOutputs);

		return null;
	}

	private void getRequestInfo(HttpServletRequest request) {
		listOfOutputs.add("Command: " + request.getParameter("cmd"));
		listOfOutputs.add("Accept: " + request.getHeader("accept"));
		listOfOutputs.add("User-Agent" + request.getHeader("user-agent"));
		listOfOutputs.add("Accept-charset" + request.getHeader("accept-charset"));
		listOfOutputs.add("Accept-Language" + request.getHeader("accept-language"));
		listOfOutputs.add("x-wap-profile" + request.getHeader("x-wap-profile"));
		listOfOutputs.add("profile: " + request.getHeader("profile"));
	}

}
