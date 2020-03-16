package Extract;

import Data.Layer1.ImportText;
import Text.*;

public class ExtractImport 
{
    public ExtractImport()
    {
        
    }
    
    public ImportText extractImports(String text)
    {
        ImportText importText = new ImportText();
        String importStr = "";
        
        while(text.contains("import "))
        {
            importStr = "import" + Text.getBefore(";", Text.getAfter("import", text)) + ";";
            text = Text.removeFirst(importStr, text);
            
            importText.Add(importStr);
        }
        
        return importText;
        
    }
}
