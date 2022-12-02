package Java.Code.Command.Batch;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
		cmdNum-=1;
		switch(cmdType) {
			case "edit":
				String[] editNames = {"FlipHorizontal","FlipVertical","Rotate90R","Rotate90L","Rotate180","Tailor","Zoom"};
				args.add(editNames[cmdNum]);
				Class[] editClasses = {FlipHorizontal.class,FlipVertical.class,Rotate90DegreesClockwise.class,Rotate90DegreesCounterclockwise.class,Rotate180Degrees.class,Tailoring.class,Zoom.class};
				parameterTypes = getParameters(editClasses[cmdNum]);
				para = parseString(string, parameterTypes);
				args.add(para);
				return args;
				
			case "export":
				String[] exportNames = {"jpgTransfer","pngTransfer","gifTransfer","bmpTransfer","tiffTransfer","imagCompress","localSave"};
				args.add(exportNames[cmdNum]);
				Class[] exportClasses = {jpgTransfer.class,pngTransfer.class,gifTransfer.class,bmpTransfer.class,tiffTransfer.class,imagCompress.class,localSave.class};
				parameterTypes = getParameters(exportClasses[cmdNum]);
				para = parseString(string, parameterTypes);
				args.add(para);
				return args;
			case "filter":
				String[] filterNames = {"Gray","Contrast","Vintage","Combine","Mosaic","Paint","Border"};
				args.add(filterNames[cmdNum]);
				Class[] filterClasses = {jpgTransfer.class,pngTransfer.class,gifTransfer.class,bmpTransfer.class,tiffTransfer.class,imagCompress.class,localSave.class};
				parameterTypes = getParameters(filterClasses[cmdNum]);
				para = parseString(string, parameterTypes);
				args.add(para);
				return args;
			default:
				return null;
		}
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
		
		EditCommand eip = new EditCommand(ip);
		Map<String, Command> classNameMap = new HashMap<>();
		classNameMap.put("jpgTransfer", new jpgTransfer(ip, parameters));
		classNameMap.put("pngTransfer", new pngTransfer(ip, parameters));
		classNameMap.put("gifTransfer", new gifTransfer(ip, parameters));
		classNameMap.put("bmpTransfer", new bmpTransfer(ip, parameters));
		classNameMap.put("tiffTransfer", new tiffTransfer(ip, parameters));
		classNameMap.put("imagCompress", new imagCompress(ip, parameters));
		classNameMap.put("localSave", new localSave(ip, parameters));
		
		
		classNameMap.put("Combine", new CombineFilter(eip, parameters));
		classNameMap.put("Gray", new localSave(ip, parameters));
		classNameMap.put("Vintage", new localSave(ip, parameters));
		classNameMap.put("Border", new localSave(ip, parameters));
		classNameMap.put("Contrast", new localSave(ip, parameters));
		classNameMap.put("Paint", new localSave(ip, parameters));
		classNameMap.put("Mosaic", new localSave(ip, parameters));
		classNameMap.put("AntiColor", new localSave(ip, parameters));
		classNameMap.put("FlipHorizontal", new localSave(ip, parameters));
		classNameMap.put("FlipVertical", new localSave(ip, parameters));
		classNameMap.put("Rotate180", new localSave(ip, parameters));
		classNameMap.put("Rotate90R", new localSave(ip, parameters));
		classNameMap.put("Rotate90L", new localSave(ip, parameters));
		classNameMap.put("Tailor", new localSave(ip, parameters));
		classNameMap.put("Zoom", new localSave(ip, parameters));


		return  classNameMap.get(cmdType);
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