import Data.Layer0.Data;
import java.io.IOException;
import Data.Layer1.BasicElements;
import Data.Layer1.Layer1;
import Settings.*;
import Tests.Test;
import cmd.*;
import File.*;

public class Engine 
{
    private Settings settings;
    private FileManager fileManager;
    private Data data;
    private Layer1 layer1Extraction;
    
    public Engine()
    {
      settings = new Settings();  
      fileManager = new FileManager();
      data = new Data();
      layer1Extraction = new Layer1();
    }
    
    public void setup(Settings set)
    {
        settings = set;
    }
    
    public Boolean start() throws IOException
    {
        cmd.out("Welcome to Parchment!");
        cmd.line();
        return Test.SettingsLoaded(settings);
    }
    
    public Boolean Load() throws IOException
    {
        Boolean dataLoaded = true; //--> to add Test;
        
        cmd.out("Loading Files");
        data.setNumberOfFiles(fileManager.numberOfFiles(settings.getCodefilePath()));
        
        //Get file names
        for(int index = 0; index < data.getNumberOfFiles(); index++)
        {
            data.addFileName(fileManager.getFileName(settings.getCodefilePath(), index));
            data.addFileText(fileManager.getText(settings.getCodefilePath() + "\\" + data.getFileName(index)));
        }
        
        data.printMeta();
        data.printFileText();
        
        return dataLoaded;
    }
    
    
    public Boolean extract()
    {
        Boolean layer1Extracted = true; //to add test;

        for(int index = 0; index < data.getNumberOfFiles(); index++)
        {
            //cmd.test("adding file: " + index);
            //cmd.test("file contents: " + data.getFileText(index));
            layer1Extraction.addFile(data.getFileText(index));
        }
        
        layer1Extraction.print();
        
        return layer1Extracted;
        
    }
    
    
    
    
    
}
