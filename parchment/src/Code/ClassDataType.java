package Code;

import Code.Structure.Accessor;
import java.util.ArrayList;

public class ClassDataType 
{
    private int classID; //FK
    private Accessor access;
    private String name;
    private ArrayList<SimpleDataType> simpleDataTypes;
    private ArrayList<ClassDataType> classDataTypes;
    
    public ClassDataType()
    {
        classID = -1;
        access = new Accessor();
        name = "";
        simpleDataTypes = new ArrayList();
        classDataTypes = new ArrayList();
    }
    
    public ClassDataType addSimpleDataType(ClassDataType original, SimpleDataType sdt)
    {
        original.getSimpleDataTypes().add(sdt);
        return original;
    }
    
    public ClassDataType addClassDataType(ClassDataType original, ClassDataType toAdd)
    {
        original.getClassDataTypes().add(toAdd);
        return original;
    }
    
    public ClassDataType addName(ClassDataType original, String n)
    {
        original.setName(n);
        return original;
    }
    
    public ClassDataType addClassID(ClassDataType original, int id)
    {
        original.setClassID(id);
        return original;
    }
    
    public ClassDataType addIsPublic(ClassDataType original, Boolean isPublic)
    {
        original.setIsPublic(isPublic);
        return original;
    }
    
    public ClassDataType makePublic (ClassDataType original)
    {
        original.setPublic();
        return original;
    }
    
    public ClassDataType makePrivate (ClassDataType original)
    {
        original.setPrivate();
        return original;
    }
    
    public void setClassID(int id)
    {
        classID = id;
    }
    
    public int getClassID()
    {
        return classID;
    }
    
    public void setPublic()
    {
        access.setPublic();
    }
    
    public void setPrivate()
    {
        access.setPrivate();
    }
    
    public void setIsPublic(Boolean toPublic)
    {
        access.setIsPublic(toPublic);
    }
    
    public Boolean isPublic()
    {
        return access.getIsPublic();
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public String getName()
    {
        return name;
    }

    public Accessor getAccess() {
        return access;
    }

    public void setAccess(Accessor access) {
        this.access = access;
    }

    public ArrayList<SimpleDataType> getSimpleDataTypes() {
        return simpleDataTypes;
    }

    public void setSimpleDataTypes(ArrayList<SimpleDataType> simpleDataTypes) {
        this.simpleDataTypes = simpleDataTypes;
    }

    public ArrayList<ClassDataType> getClassDataTypes() {
        return classDataTypes;
    }

    public void setClassDataTypes(ArrayList<ClassDataType> classDataTypes) {
        this.classDataTypes = classDataTypes;
    }
    
    
}
