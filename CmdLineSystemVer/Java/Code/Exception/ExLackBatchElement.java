package Java.Code.Exception;

import Java.Code.Command.Batch.batchProcessor;

public class ExLackBatchElement extends Exception {
	public ExLackBatchElement() {
		batchProcessor bp = batchProcessor.getInstance();
		String msg;
		if ((bp.getCmdBuffer().size() == 0))
			msg = "There is no command(s) in buffer now. Please input command(s) for batch.";
		else
			msg = "There is no img(s) in buffer now. Please input img(s) for batch.";
		System.out.println(msg);
	}

}
