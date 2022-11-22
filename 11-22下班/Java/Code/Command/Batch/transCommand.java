package Java.Code.Command.Batch;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	


	public transCommand(String cmdType2, Integer cmdNum, String substring) {
		ArrayList<Object> args = parse(cmdType2,cmdNum,substring);
		
		this.cmdType = (String) args.get(0);
		this.parameters = (ArrayList<Object>) args.get(1);

	}




	private ArrayList<Object> parse(String cmdType, Integer cmdNum, String string) {
		// TODO Auto-generated method stub
		ArrayList<Object> args = new ArrayList<>();
		Class[] parameterTypes;
		ArrayList<Object> para;
		switch(cmdType) {
			case "edit": 
				switch(cmdNum) {
					case 1:
						args.add("FlipHorizontal");
						parameterTypes = getParameters(FlipHorizontal.class);				

						para = parseString(string, parameterTypes);
						args.add(para);
						return args;
						
					case 2:
						args.add("FlipVertical");
						parameterTypes = getParameters(FlipVertical.class);				

						para = parseString(string, parameterTypes);
						args.add(para);
						return args;
					case 3:
						args.add("Rotate90R");
						parameterTypes = getParameters(Rotate90DegreesClockwise.class);				

						para = parseString(string, parameterTypes);
						args.add(para);
						return args;
					case 4:
						args.add("Rotate90L");
						parameterTypes = getParameters(Rotate90DegreesCounterclockwise.class);				
						para = parseString(string, parameterTypes);
						args.add(para);
						return args;
					case 5:
						args.add("Rotate180");
						parameterTypes = getParameters(Rotate180Degrees.class);				

						para = parseString(string, parameterTypes);
						args.add(para);
						return args;
					case 6:
						args.add("Tailor");
						parameterTypes = getParameters(Tailoring.class);				

						para = parseString(string, parameterTypes);
						args.add(para);
						return args;
					case 7:
						args.add("Zoom");
						parameterTypes = getParameters(Zoom.class);				
						para = parseString(string, parameterTypes);
						args.add(para);
						return args;
						
				}
				
			case "export":
				switch(cmdNum) {
				case 1: 
					args.add("jpgTransfer");
					parameterTypes = getParameters(jpgTransfer.class);				
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				
				case 2:
					args.add("pngTransfer");
					parameterTypes = getParameters(pngTransfer.class);				
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 3:
					args.add("gifTransfer");
					parameterTypes = getParameters(gifTransfer.class);				
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 4:
					args.add("bmpTransfer");
					parameterTypes = getParameters(bmpTransfer.class);				
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 5:
					args.add("tiffTransfer");
					parameterTypes = getParameters(tiffTransfer.class);				
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 6:
					args.add("imagCompress");
					parameterTypes = getParameters(imagCompress.class);				
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 7:
					args.add("localSave");
					parameterTypes = getParameters(localSave.class);				
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				}
			case "filter":
				switch(cmdNum) {
				case 1: 
					args.add("Gray");
					parameterTypes = getParameters(GrayFilter.class);				
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				
				case 2:
					args.add("Contrast");
					parameterTypes = getParameters(HighContrastFilter.class);
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 3:
					args.add("Vintage");
					parameterTypes = getParameters(VintageFilter.class);
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 4:
					args.add("Combine");
					parameterTypes = getParameters(CombineFilter.class);
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 5:
					args.add("Mosaic");
					parameterTypes = getParameters(MosaicFilter.class);
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 6:
					args.add("Paint");
					parameterTypes = getParameters(PaintFilter.class);
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				case 7:
					args.add("Border");
					parameterTypes = getParameters(CreateBorder.class);
					para = parseString(string, parameterTypes);
					args.add(para);
					return args;
				}
			
		}
		return null;
		
	}



	private Class[] getParameters(Class commond_name) {
		// TODO Auto-generated method stub
		Constructor con = commond_name.getDeclaredConstructors()[0];
		for (Constructor contemp : commond_name.getDeclaredConstructors()) {
			boolean flag = true;
			for (Class para : contemp.getParameterTypes()) {
				if (para.getName() == "java.util.ArrayList") {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				con = contemp;
			}

		}
		return con.getParameterTypes();
	}




	private ArrayList<Object> parseString(String parameterStr, Class[] parameterTypes) {
		ArrayList<Object> para = new ArrayList<>();

		 InputStream ps = System.in;

		 System.setIn(new ByteArrayInputStream(parameterStr.getBytes()));

		 Scanner scanner = new Scanner(System.in);
		for (int i = 1; i < parameterTypes.length; i++) {
			Class arg = parameterTypes[i];
			switch (arg.getName()) {
			case "java.lang.Float":
				para.add(scanner.next());
				break;
			case "java.lang.Integer":
				para.add(scanner.nextInt());
				break;
			case "java.lang.String":
				para.add(scanner.next());
				break;
			case "java.lang.Boolean":
				para.add(scanner.next());
				break;
			}
		}
		System.setIn(ps);
		return para;
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