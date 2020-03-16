package Data.Layer1;

import cmd.cmd;

public class ClassText 
{
    private String header;
    private String code; //code between brackets;
    
    public ClassText()
    {
        header = "";
        code = "";
    }
    
    public void print()
    {
       cmd.line();
       cmd.out("Class text:");
       cmd.out("header: " + header);
       cmd.out("full:\n" + code);
    }
    
    public void addHeader(String text)
    {
        header = text;
    }
    
    public void addCode(String text)
    {
        code = text;
    }
    
    public String getHeader()
    {
        return header;
    }
    
    public String getCode()
    {
        return code;
    }
}
