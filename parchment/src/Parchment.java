import java.io.IOException;
import Settings.Settings;

public class Parchment 
{
    /*
        What is Parchment
        Parchment is a platform that allows for logic to be definied on a java level of code, by
        Both an individual desiring to learn java
        And also an AI trying to learn how to code.
    */
    public static void main(String[] args) throws IOException 
    {
        Boolean alive = true;
        Settings settings = new Settings();
        Engine engine = new Engine();
        settings.setCodefilePath("C:\\Users\\mikzerafa\\Desktop\\Dev\\ParchmentCodeRun\\src\\parchmentcoderun");
        settings.setSaveFilePath("C:\\Users\\mikzerafa\\Desktop\\ParchmentCore");
        
        
        engine.setup(settings);
        alive = engine.start();
        alive = engine.Load();
        alive = engine.extract();
    }
}
