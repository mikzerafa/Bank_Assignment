package Data.Layer1;

import java.util.ArrayList;
import cmd.cmd;

public class Layer1 
{
    private ArrayList<BasicElements> basicFile;
    
    public Layer1()
    {
        basicFile = new ArrayList();
    }
    
    public void print()
    {
        for(BasicElements b : basicFile)
        {
            b.print();
        }
    }
    
    public void addFile(String text)
    {
       // cmd.test("loading basic elements");
        BasicElements basicElements = new BasicElements();
        basicElements.Load(text);
        basicFile.add(basicElements);
        //cmd.test("file added");
    }
    
    public int count()
    {
        return basicFile.size();
    }
    
    public BasicElements getBasicFile(int index)
    {
        return basicFile.get(index);
    }
}
