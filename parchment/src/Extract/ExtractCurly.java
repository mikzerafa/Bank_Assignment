package Extract;

import Data.Layer1.ClassText;
import Text.*;
import cmd.cmd;

public class ExtractCurly 
{
    
    //uses Text Package
    //but the logic of extracting the code structure from the text
    //Methods, Classes, if, else, for, while, do, switch
    
    public ExtractCurly()
    {
        
    }
    
    public ClassText ExtractCurlyClass(String code)
    {
        //cmd.test("extracting curly");
        ClassText classText = new ClassText();
       //Starts with public or private, contains class in the header.
        //cmd.test("adding header");
        classText.addHeader(Text.headerBeforeCurly(code, Text.findOpenCurlyBracket(code)));
        //cmd.test("adding code");
        classText.addCode(Text.betweenCurlyBracket(code, Text.findOpenCurlyBracket(code)));
        //cmd.test("success");
       return  classText;
    }
    
    public String ExtractCurlyIf(String code)
    {
        return "";
    }
    
    public String ExtractCurlyElse(String code)
    {
        return "";
    }
    
    public String ExtractCurlyFor(String code)
    {
       return ""; 
    }
    
    public String ExtractCurlyWhile(String code)
    {
        return "";
    }
    
    public String ExtractCurlyDo(String code)
    {
        return "";
    }
    
    public String ExtractCurlySwitch(String code)
    {
        return "";
    }
    
}
