package AI.MultiLayerPerceptron.Results;

public class Result 
{
    private Boolean result;
    
    public Result()
    {
        result = true;
    }
    
    public Result(Boolean r)
    {
        result = r;
    }
    
    
    public void SetResult(Boolean r)
    {
        result = r;
    }
    
    public Boolean compare(Boolean actual)
    {
        return result == actual;
    }
           
}
