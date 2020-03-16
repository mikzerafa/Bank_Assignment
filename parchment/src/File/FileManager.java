package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager 
{
    public FileManager()
    {
        
    }
    
    public int numberOfFiles(String fileLocation)
    {
        File file = new File(fileLocation);
        return file.list().length;
    }
    
    public String getFileName(String fileLocation, int index)
    {
        File file = new File(fileLocation);
        return file.list()[index];
    }
    
    public String getText(String fileLocation) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        String output = "";
        String line = "";
        
        while(line != null)
        {
            output = output + line;
            line = br.readLine();
            if(line != null)
            {
                line = line + "\n";
            }
        }
         return output;       
    }
}
