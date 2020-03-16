package Tests;
import Settings.*;

public class Test 
{
    private txtTest txt;
    
    public Test()
    {
       txt = new txtTest();
    }
    
    public static Boolean SettingsLoaded(Settings s)
    {
        Boolean loaded = !txtTest.isEmpty(s.getCodefilePath()) && !txtTest.isEmpty(s.getSaveFilePath());
        
        return loaded;
    }
}
