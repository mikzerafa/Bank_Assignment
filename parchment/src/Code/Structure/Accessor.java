package Code.Structure;

public class Accessor 
{
    private Boolean isPublic;
    
    public Accessor()
    {
        isPublic = true;
    }
    
    public void setPublic()
    {
        isPublic = true;
    }
    
    public void setPrivate()
    {
        isPublic = false;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    public Boolean isAccessor(String text)
    {
        return text.equals("public") || text.equals("private");
    }
}
