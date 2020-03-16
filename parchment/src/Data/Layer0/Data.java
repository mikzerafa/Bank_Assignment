package Data.Layer0;

import java.util.ArrayList;
import cmd.*;
import Text.*;

public class Data 
{
    public int numberOfFiles;
    public ArrayList<String> fileNames;
    public ArrayList<String> filesAsText;
    
    public Data()
    {
        numberOfFiles = 0;
        filesAsText = new ArrayList();
        fileNames = new ArrayList();
    }
    
    public void printMeta()
    {
        cmd.out("metaData");
        printNumberOfFiles();
        printFileNames();
    }
    
    public void printNumberOfFiles()
    {
        cmd.out("Number of Files: " + numberOfFiles);
    }
    
    public void printFileNames()
    {
        String names = "";
        
        for(int index = 0; index < fileNames.size(); index++)
        {
            names = names + fileNames.get(index);

            if(index+1 < fileNames.size())
            {
                names = Text.addComma(names);
            }
            else
            {
                names = Text.addFullStop(names);
            }       
        }
        
        cmd.out("File Names: " + names);
    }
    
    public void printFileText()
    {
        String text = "";
        String name = "";
        
        for(int index = 0; index < filesAsText.size(); index++)
        {
            name = fileNames.get(index);
            text = text + name + ":\n" +  filesAsText.get(index);
        }
        
        cmd.out("File text:\n" + text);
    }
    
    public void addFileName(String name)
    {
        fileNames.add(name);
    }
    
    public void modFileName(String name, int index)
    {
        fileNames.set(index, name);
    }
    
    public String getFileName(int index)
    {
        return fileNames.get(index);
    }
    
    public void addFileText(String text)
    {
        filesAsText.add(text);
    }
    
    public void modFileText(String text, int index)
    {
        filesAsText.set(index, text);
    }
    
    public String getFileText(int index)
    {
        return filesAsText.get(index);
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    } 
}
