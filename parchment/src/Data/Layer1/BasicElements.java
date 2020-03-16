package Data.Layer1;

import Extract.*;
import cmd.cmd;

public class BasicElements 
{
    private ClassText classText;
    private ImportText importText;
    private PackageText packageText;
    
    public BasicElements()
    {
        classText = new ClassText();
        importText = new ImportText();
        packageText = new PackageText();
    }
    
    public void print()
    {
        cmd.line();
        cmd.out("Basic Elements");
        
        classText.print();
        importText.print();
        packageText.print();
        
        cmd.line();
    }
    
    
    
    public void Load(String text)
    {
        //cmd.test("loading class");
        loadClass(text);
        //cmd.test("loading imports");
        loadImports(text);
        //cmd.test("loading package");
        loadPackage(text);
    }

    public ClassText getClassText() {
        return classText;
    }

    public ImportText getImportText() {
        return importText;
    }

    public PackageText getPackageText() {
        return packageText;
    }
    
    
    
    public void loadClass(String text)
    {
        ExtractCurly curlyClass = new ExtractCurly();
        classText = curlyClass.ExtractCurlyClass(text);
    }
    
    public void loadImports(String text)
    {
        ExtractImport imports = new ExtractImport();
        importText = imports.extractImports(text);
    
    }
    
    public void loadPackage(String text)
    {
        ExtractPackage packages = new ExtractPackage();
        packageText = packages.extractPackages(text);
    }
}
