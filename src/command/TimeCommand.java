package command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		
		request.setAttribute("Date", new Date().toString());

		return null;
	}

}
