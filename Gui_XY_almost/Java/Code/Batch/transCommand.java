package Java.Code.Batch;

import java.util.ArrayList;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Command.Export.*;
import Java.Code.Command.EditDecorator.*;
import Java.Code.Exception.ArgsInvalidException;

public class transCommand {
	private String cmdType;
	private static ArrayList<String> previewStr;
	private ArrayList<Object> parameters;
	{
		previewStr = new ArrayList<>();
		previewStr.add("Combine");
		previewStr.add("Gray");
		previewStr.add("Vintage");
		previewStr.add("Border");
		previewStr.add("Paint");
		previewStr.add("Mosaic");
		previewStr.add("FlipHorizontal");
		previewStr.add("FlipVertical");
		previewStr.add("Zoom");
		previewStr.add("Tailor");
		previewStr.add("Rotate90L");
		previewStr.add("Rotate90R");
		previewStr.add("Rotate180");
		previewStr.add("Rotate90R");
		previewStr.add("AntiColor");
	}

	public transCommand(String cmdType, ArrayList<Object> parameters) {
		this.cmdType = cmdType;
		this.parameters = parameters;
	}

	public Command transfer(notifyIP ip) throws ArgsInvalidException {
		// export first
		// edit next
		switch (cmdType) {
		case "jpgTransfer":
			return new jpgTransfer(ip, parameters);
		case "pngTransfer":
			return new pngTransfer(ip, parameters);
		case "gifTransfer":
			return new gifTransfer(ip, parameters);
		case "bmpTransfer":
			return new bmpTransfer(ip, parameters);
		case "tiffTransfer":
			return new tiffTransfer(ip, parameters);
		case "imagCompress":
			return new imagCompress(ip, parameters);
		case "localSave":
			return new localSave(ip, parameters);
		}
		EditCommand eip = new EditCommand(ip);
		switch (cmdType) {
		case "Combine":
			return new CombineFilter(eip, parameters);
		case "Gray":
			return new GrayFilter(eip, parameters);
		case "Vintage":
			return new VintageFilter(eip, parameters);
		case "Border":
			return new CreateBorder(eip, parameters);
		case "Contrast":
			return new HighContrastFilter(eip, parameters);
		case "Paint":
			return new PaintFilter(eip, parameters);
		case "Mosaic":
			return new MosaicFilter(eip, parameters);
		case "AntiColor":
			return new Anticolor(eip, parameters);
		case "FlipHorizontal":
			return new FlipHorizontal(eip, parameters);
		case "FlipVertical":
			return new FlipVertical(eip, parameters);
		case "Rotate180":
			return new Rotate180Degrees(eip, parameters);
		case "Rotate90R":
			return new Rotate90DegreesClockwise(eip, parameters);
		case "Rotate90L":
			return new Rotate90DegreesCounterclockwise(eip, parameters);
		case "Tailor":
			return new Tailoring(eip, parameters);
		case "Zoom":
			return new Zoom(eip, parameters);
		}
		return null;
	}

	public static boolean checkPreviewable(transCommand i) {
		if (previewStr.indexOf(i.cmdType) != -1)
			return true;
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof transCommand) {
			transCommand obj1 = (transCommand) obj;
			if (cmdType.equals(obj1.cmdType) && parameters.size() == obj1.parameters.size()) {
				for (Integer i = 0; i < parameters.size(); i++) {
					if (!parameters.get(i).equals(obj1.parameters.get(i)))
						return false;
				}
				return true;
			}
			return false;
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public String toString() {
		String str = "";
		for (Object i : parameters)
			str += (i.toString() + " ");
		return " " + cmdType + " " + str;
	}

}