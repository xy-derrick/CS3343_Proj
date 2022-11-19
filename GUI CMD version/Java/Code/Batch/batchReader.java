package Java.Code.Batch;

import java.util.ArrayList;

public class batchReader {

	static private batchReader reader = null;

	private batchReader() {
	};

	static public batchReader getInstance() {
		if (reader == null) {
			reader = new batchReader();
			return reader;
		} else {
			return reader;
		}
	}

	public ArrayList<Object> parse(ArrayList<Object> argLists) {

		try {
			ArrayList args = new ArrayList<>();

			if (argLists.size() == 0)
				throw new ExNoBatchInput();
			if (argLists.get(0) instanceof Integer) {// 判断是imgProcessor
				for (Object idx : argLists) {
					args.add((Integer) idx);
				}
			}

			else if (argLists.get(0) instanceof transCommand) {// 判断是Command
				for (Object cmd : argLists) {
					args.add((transCommand) cmd);
				}
			}
			return args;
		} catch (ExNoBatchInput e) {
			System.out.println(e);
		}
		return null;
	}
};
