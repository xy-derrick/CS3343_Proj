package Java.Code.Batch;

import java.util.ArrayList;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandCancelable;
import Java.Code.Exception.ImgProcessorNotFindInListException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class batchAdd extends Command implements CommandCancelable {
	private ArrayList<Integer> imgList = new ArrayList<Integer>();
	private ArrayList<transCommand> cmdList = new ArrayList<transCommand>();
	private batchProcessor bp = batchProcessor.getInstance();

	public batchAdd(imgProcessor ip, ArrayList<?> list) {
		super(null);
		try {

			// TODO Auto-generated constructor stub
			if (list.size() == 0)
				throw new ExNoBatchInput();
			else if (list.get(0) instanceof Integer) {
				for (Integer i : (ArrayList<Integer>) list) {
					if (Software.getInstance().getImgProcessorList().size() < i)
						throw new ImgProcessorNotFindInListException(i);
					else if (bp.getImgBuffer().indexOf(i) != -1)
						throw new ExImgHaveExist(i);
					else {
						imgList.add(i);
					}

				}
			} else if (list.get(0) instanceof transCommand) {

				for (transCommand i : (ArrayList<transCommand>) list) {
					cmdList.add(i);
				}
			}
		} catch (Exception e) {

		}

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		for (transCommand c : cmdList)
			bp.addElement(c);
		for (Integer i : imgList)
			bp.addElement(i);
		bp.info();
		System.out.println("BatchAdd runs compeleted.");
	}

	@Override
	public void undo() {
		for (transCommand c : cmdList)
			bp.deleteElement(c);
		for (Integer i : imgList)
			bp.deleteElement(i);
		bp.info();
		System.out.println("BatchAdd undos compeleted.");

	}

}
