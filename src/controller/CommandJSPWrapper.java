package controller;

import command.Command;
import command.StockCommand;

public class CommandJSPWrapper {
	public Command command;
	public String AssociatedJspBody;
	public String PageTitle;
	
	public CommandJSPWrapper(Command command, String jspBody, String title)
	{
		this.command = command;
		this.AssociatedJspBody = jspBody;
		this.PageTitle = title;
	}

}
