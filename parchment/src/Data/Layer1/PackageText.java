package Data.Layer1;

import cmd.cmd;

import java.util.ArrayList;

public class PackageText 
{
    private String packageText;
    private ArrayList<String> packageTextLayered;
    
    public PackageText()
    {
        packageText = "";
        packageTextLayered = new ArrayList();
    }
    
    public void print()
    {
        cmd.line();
        cmd.out("package Text: " + packageText);
        cmd.out("package layers: " + packageTextLayered.size());
        
        for(int index = 0; index < packageTextLayered.size(); index++)
        {
            cmd.out("layer " + index + ": " + packageTextLayered.get(index));
        }
    }
    
    public void setPackageText(String text)
    {
        this.packageText = text;
    }
    
    public String getPackageText()
    {
        return packageText;
    }
    
    public void addPackageLayer(String layer)
    {
        packageTextLayered.add(layer);
    }
    
    public int countLayers()
    {
        return packageTextLayered.size();
    }
    
    public String getLayer(int index)
    {
        return packageTextLayered.get(index);
    }
    
}
