package Text;

import cmd.cmd;

public class Text 
{
    public Text()
    {
        
    }
    
    public static String addComma(String text)
    {
        return text + ", ";
    }
    
    public static String addFullStop(String text)
    {
        return text + ".";
    }
    
    //Direct Text Manipulation
    public static String removeComments(String code)
    {
        String output = "";
        
        return output;
    }
    
    public static String headerBeforeCurly(String code, int indexOfCurly) //open
    {
        
        String output = "";
        
        output = code.substring(0, indexOfCurly);
        
        if(output.contains("public"))
        {
            output = getAfter("public", output);
            output = "public" + output;
        }
        else if(output.contains("private"))
        {
            output = getAfter("private", output);
            output = "private" + output;
        }
        return output;
        
        
    }
    
    public static int findOpenCurlyBracket(String code)
    {
        return code.indexOf("{");
    }
    
    public static int count(String code, String toCount)
    {
        int amount = 0;
        
        while( code.contains(toCount))
        {
            code = removeFirst(code, toCount);
            amount++;
        }
        
        return amount;
    }
    
    //Assums that syntax is correct and equal number of brackets are found, does not ignore strings.
    public static String betweenBrackets(String code, String type, int openBracketIndex)
    {
        String output = "";
        String find = ")";
        
        if(type.equals("{"))
        {
            find = "}";
        }
        
        int current = openBracketIndex + 1;
        int nextClose = 0;
        int nextOpen = 0;
        int requireClosing = 1;
        Boolean done = false;
        
        while(!done)
        {
            nextClose = code.indexOf(find, current);
            nextOpen = code.indexOf(type, current);
            
            if((nextClose < nextOpen)|| nextOpen== -1 && requireClosing == 1)
            {
                output = code.substring(openBracketIndex, nextClose);
                done = true;
            }
            else if((nextClose < nextOpen|| nextOpen== -1) && requireClosing > 1)
            {
                requireClosing--;
                current = nextClose +1;
            }
            else if(nextOpen < nextClose)
            {
                requireClosing++;
                current = nextOpen+1;
            }
            
           // cmd.test("done: " + done + " current : " + current + " nextClose: "  + nextClose + " nextOpen: " + nextOpen + " requireClosing: " + requireClosing);
        }

        
        return output;
    }
   
    public static String betweenCurlyBracket(String code, int openBracketIndex)
    {
        return betweenBrackets(code, "{", openBracketIndex);
    }
    
    public static String removeFirst(String toRemove, String text)
    {
        String output = "";
        
        output = getBefore(toRemove, text) + getAfter(toRemove, text);
        
        return output;
    }
    
    //Abstract Text Manipulaton
    public static String removeBetween(String text, String from, String fin)
    {
        String output = "";
        output = getBefore(from, text) + getAfter(fin, text);
        return output;
    }
    
    public static String getBetween(String text, String from, String fin)
    {
        String output = "";
        output = getBefore(fin, getAfter(from, text));        
        return output;
    }
    
    public static String getAfter(String start, String text)
    {
        String output = "";
        
        int pos = text.indexOf(start) + start.length();
        //cmd.test("pos : " + pos + " code.length:" + start.length() + "from.length: " + text.length());
        if(pos < text.length())
        {
            output = text.substring(pos);
        }
        
        //cmd.test("output: " + output);
        
        return output;
    }
    
    public static String getBefore(String fin, String text)
    {
        String output = "";
        
        if(text.indexOf(fin) > 0)
        {
            output = text.substring(0, text.indexOf(fin));
        }
        return output;
    }

}
