package Java.Gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImgFileFilter extends FileFilter {  
    public String getDescription() {  
        return "*.jpg;*.jpeg;*.gif,*.bmp,*.png,*.raw";  
    }  
  
    public boolean accept(File file) {  
        String name = file.getName();  
        return file.isDirectory() || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".gif") || name.toLowerCase().endsWith(".bmp") || name.toLowerCase().endsWith(".raw"); 
    }  
} 
