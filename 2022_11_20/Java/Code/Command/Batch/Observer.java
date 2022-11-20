package Java.Code.Command.Batch;

import Java.Code.Exception.ArgsInvalidException;

public interface Observer {
	public void update() throws ArgsInvalidException;
}