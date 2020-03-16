package Extract;

import Data.Layer1.PackageText;
import Text.*;
import cmd.cmd;

public class ExtractPackage 
{
    public ExtractPackage()
    {
        
    }
    
    public PackageText extractPackages(String text)
    {
       PackageText packageText = new PackageText();
       String pckgTxt = "";
       String layer = "";
       //cmd.test("working with :\n" + text);
       //cmd.test("get after package: " + Text.getAfter("package", text));
       pckgTxt = Text.getBefore(";", Text.getAfter("package", text));
       packageText.setPackageText(pckgTxt);
       //cmd.test("packageText: " + pckgTxt);
       while(Text.count(".", pckgTxt) > 0)
       {
           layer = Text.getBefore(".", text);
           pckgTxt = Text.getAfter(".", text);
           packageText.addPackageLayer(layer);
           //cmd.test("layer: " + layer);
       }
       
       return packageText;
    }
}
