package Java.Main;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;
import Java.Code.Command.Base.Command;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.changeImgProcessor;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.closeImgProcessor;
import Java.Code.Command.Commands.Common.displayImg;
import Java.Code.Command.Commands.Common.existSoftware;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Command.Commands.Common.showImgInfo;
import Java.Code.Command.EditDecorator.CombineFilter;
import Java.Code.Command.EditDecorator.CreateBorder;
import Java.Code.Command.EditDecorator.EditDecorator;
import Java.Code.Command.EditDecorator.FlipHorizontal;
import Java.Code.Command.EditDecorator.FlipVertical;
import Java.Code.Command.EditDecorator.GrayFilter;
import Java.Code.Command.EditDecorator.HighContrastFilter;
import Java.Code.Command.EditDecorator.MosaicFilter;
import Java.Code.Command.EditDecorator.PaintFilter;
import Java.Code.Command.EditDecorator.Rotate180Degrees;
import Java.Code.Command.EditDecorator.Rotate90DegreesClockwise;
import Java.Code.Command.EditDecorator.Rotate90DegreesCounterclockwise;
import Java.Code.Command.EditDecorator.Tailoring;
import Java.Code.Command.EditDecorator.VintageFilter;
import Java.Code.Command.EditDecorator.Zoom;
import Java.Code.Exception.ArgsTypeNotRightException;
import Java.Code.Exception.CommandTypeNotDefinedException;
import Java.Code.Software.ArgsReader;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Gui.guiMain;

public class Main {
    public static Software main_software = null;

    public static void main(String[] args) {
    	guiMain.getInstance();
    };
}