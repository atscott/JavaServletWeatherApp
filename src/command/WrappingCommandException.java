package command;

public class WrappingCommandException extends CommandException{
	
	private static final long serialVersionUID = 1L;

	private Exception wrappedException;
	
	public WrappingCommandException(String message, Exception wrappedException)
	{
		super(message);
		this.setStackTrace(wrappedException.getStackTrace());
		this.wrappedException = wrappedException;
	}
	
	public WrappingCommandException(Exception wrappedException)
	{
		this(wrappedException.getMessage(), wrappedException);
	}
	
	@Override
	public String toString(){
		return this.wrappedException.toString();
	}

}
