package Code;

import Code.Structure.Accessor;

public class SimpleDataType 
{
    
    public Accessor access;
    public String type;
    public String name;
    public String value;
    
    
    public SimpleDataType()
    {
        access = new Accessor();
        type = "";
        name = "";
        value = "";
    }
    
    public SimpleDataType addAccess(SimpleDataType original, Accessor ac)
    {
        original.setAccess(ac);
        return original;
    }
    
    public SimpleDataType addIsPublic(SimpleDataType original, Boolean isPublic)
    {
        original.SetIsPublic(isPublic);
        return original;
    }
    
    public SimpleDataType makePublic(SimpleDataType original)
    {
        original.setPublic();
        return original;
    }
    
    public SimpleDataType makePrivate(SimpleDataType original)
    {
        original.setPrivate();
        return original;
    }
    
    public SimpleDataType addType(SimpleDataType original, String type)
    {
        original.setType(type);
        return original;
    }
    
    public SimpleDataType addName(SimpleDataType original, String name)
    {
        original.setName(name);
        return original;
    }
    
    public SimpleDataType addValue(SimpleDataType original, String value)
    {
        original.setValue(value);
        return original;
    }
    
    public void SetIsPublic(Boolean isPublic)
    {
        access.setIsPublic(isPublic);
    }
    
    public void setPublic()
    {
        access.setPublic();
    }
    
    public void setPrivate()
    {
        access.setPrivate();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Accessor getAccess() {
        return access;
    }

    public void setAccess(Accessor access) {
        this.access = access;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
