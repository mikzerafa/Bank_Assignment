package Settings;

public class Settings 
{
    private String codeFilePath;
    private String saveFilePath;
    
    public Settings()
    {
        codeFilePath = "";
        saveFilePath = "";
    }

    public String getCodefilePath() {
        return codeFilePath;
    }

    public void setCodefilePath(String codefilePath) {
        this.codeFilePath = codefilePath;
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

}
