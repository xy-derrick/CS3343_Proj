package Java.Code.Command.Batch;

import java.util.ArrayList;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandCancelable;
import Java.Code.Exception.ExCommandNotExist;
import Java.Code.Exception.ExImgNotExist;
import Java.Code.Exception.ExNoBatchInput;
import Java.Code.Software.imgProcessor;

public class batchDelete extends Command implements CommandCancelable {
	private ArrayList<Integer> imgList = new ArrayList<Integer>();
	private ArrayList<transCommand> cmdList = new ArrayList<transCommand>();
	private batchProcessor bp = batchProcessor.getInstance();

	public batchDelete(imgProcessor ip, ArrayList<?> list) {
		super(ip);
		try {
			if (list.size() == 0)
				throw new ExNoBatchInput();
			else if (list.size() != 0 && list.get(0) instanceof Integer) {
				for (Integer i : (ArrayList<Integer>) list) {
					if (bp.getImgBuffer().indexOf(i) == -1)
						throw new ExImgNotExist(i);
					else
						imgList.add(i);
				}
			} else if (list.size() != 0 && list.get(0) instanceof transCommand)
			// ------------------------
			{
				for (transCommand i : (ArrayList<transCommand>) list) {
					if (bp.getCmdBuffer().indexOf(i) == -1) {
						throw new ExCommandNotExist(i);
					} else {
						cmdList = (ArrayList<transCommand>) list;
					}
				}

			}
		} catch (Exception e) {
		}

		// --------------------------
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for (transCommand c : cmdList)
			bp.deleteElement(c);
		for (Integer ip : imgList)
			bp.deleteElement(ip);
		System.out.println("BatchDelete runs compeleted.");

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		for (transCommand c : cmdList)
			bp.addElement(c);
		for (Integer ip : imgList)
			bp.addElement(ip);
		System.out.println("BatchDelete undo compeleted.");

	}

}
