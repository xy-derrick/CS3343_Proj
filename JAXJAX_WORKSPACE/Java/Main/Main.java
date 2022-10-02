import java.util.ArrayList;
import java.util.Scanner;

import Code.Command.Commands.readImgFromLocal;
import Code.Command.Commands.showImgInfo;
import Code.Command.Commands.showOperationHint;
import Code.Command.Commands.Sample.EnumReadSampleCommand;
import Code.Command.Commands.Sample.SampleCommand;
import Code.Enum.Degree;
import Code.Software.ArgsReader;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class Main {
    public static void main(String[] args) {

        Software main_software = Software.getInstance();

        //args reader usage sample
        imgProcessor ip_01 = new imgProcessor();
        ArgsReader.getInstance().hint("Plz input a float a int a string a boolean");
        ArrayList<Object> args_object = ArgsReader.getInstance().read(VariousArgsSampleCommand.class);
        Float f_arg =(Float)(args_object.get(0));
        Integer i_arg = (Integer)(args_object.get(1));
        String s_arg = (String)(args_object.get(2));
        Boolean b_arg = (Boolean)(args_object.get(3));

        main_software.setCommand(new VariousArgsSampleCommand(ip_01,f_arg,i_arg,s_arg,b_arg));
        main_software.execute();

        //Sample
 
        Scanner scanner = new Scanner(System.in);

        String type = null;
        Integer num = null;
        while(!(type=="Common"&&num==9))
        {
            main_software.setCommand(new showOperationHint(null));
            main_software.execute();
            try
            {
                type = scanner.next();
                num = scanner.nextInt();
            }
            catch(Exception e)
            {
                System.out.println("plz follow the 'Type Num' format");
                break;
            }

           switch(type)
           {
                case "Common":
                    switch(num)
                    {
                        case 1:
                            ArgsReader.getInstance().hint("Plz input a float a int a string a boolean");
                            args_object = ArgsReader.getInstance().read(VariousArgsSampleCommand.class);
                            f_arg =(Float)(args_object.get(0));
                            i_arg = (Integer)(args_object.get(1));
                            s_arg = (String)(args_object.get(2));
                            b_arg = (Boolean)(args_object.get(3));
                            main_software.setCommand(new VariousArgsSampleCommand(ip_01,f_arg,i_arg,s_arg,b_arg));
                            main_software.execute();
                        case 9:
                            break;
                    };
                    break;
           }
        }
            
    }
}
