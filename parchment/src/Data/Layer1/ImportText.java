package Data.Layer1;

import java.util.ArrayList;
import cmd.cmd;

public class ImportText 
{
    public ArrayList<String> importTexts;
    
    public ImportText()
    {
        importTexts = new ArrayList();
    }
    
    public void print()
    {
        cmd.line();
        cmd.out("imports: " + importTexts.size());
        for(int index = 0; index < importTexts.size(); index++)
        {
            cmd.out(importTexts.get(index));
        }
    }
    
    public void Add(String importTxt)
    {
        importTexts.add(importTxt);
    }
    
    public int amt()
    {
        return importTexts.size();
    }
    
    public String get(int index)
    {
        return importTexts.get(index);
    }
}
