package cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmd 
{
    public cmd()
    {
        
    }
    
    public static void test(String s)
    {
        out("<test>" + s);
    }
    
    public static void out(String s)
    {
        System.out.println(s);
    }
    
    public static String in() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
    
    public static void line()
    {
        out("--- --- --- --- --- --- --- --- ---");
    }
}
