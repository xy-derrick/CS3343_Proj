package Main;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;
import Code.Command.Base.Command;
import Code.Command.Commands.EditCommand;
import Code.Command.Commands.showOperationHint;
import Code.Command.Commands.Common.changeImgProcessor;
import Code.Command.Commands.Common.closeAllImgProcessors;
import Code.Command.Commands.Common.closeImgProcessor;
import Code.Command.Commands.Common.displayImg;
import Code.Command.Commands.Common.existSoftware;
import Code.Command.Commands.Common.readImgFromLocal;
import Code.Command.Commands.Common.showImgInfo;
import Code.Command.EditDecorator.CombineFilter;
import Code.Command.EditDecorator.CreateBorder;
import Code.Command.EditDecorator.EditDecorator;
import Code.Command.EditDecorator.FlipHorizontal;
import Code.Command.EditDecorator.FlipVertical;
import Code.Command.EditDecorator.GrayFilter;
import Code.Command.EditDecorator.HighContrastFilter;
import Code.Command.EditDecorator.MosaicFilter;
import Code.Command.EditDecorator.PaintFilter;
import Code.Command.EditDecorator.Rotate180Degrees;
import Code.Command.EditDecorator.Rotate90DegreesClockwise;
import Code.Command.EditDecorator.Rotate90DegreesCounterclockwise;
import Code.Command.EditDecorator.Tailoring;
import Code.Command.EditDecorator.VintageFilter;
import Code.Command.EditDecorator.Zoom;
import Code.Exception.ArgsTypeNotRightException;
import Code.Exception.CommandTypeNotDefinedException;
import Code.Software.ArgsReader;
import Code.Software.Software;
import Code.Software.imgProcessor;
import Gui.guiMain;

public class Main {
    public static Software main_software = null;

    public static void main(String[] args) {
        guiMain.getInstance();
    };
}