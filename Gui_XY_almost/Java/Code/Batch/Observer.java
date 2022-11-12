package Java.Code.Batch;

import Java.Code.Exception.ArgsInvalidException;

public interface Observer {
	public void update() throws ArgsInvalidException;
}