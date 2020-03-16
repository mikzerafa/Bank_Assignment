package AI.MultiLayerPerceptron.Results;

public class Supervised 
{
    private Result[] expected;
    private Result[] actual;
    
    public Supervised(int size)
    {
        expected = new Result[size];
        actual = new Result[size];
    }
    
    public void addExpected(int index, Result exp)
    {
        expected[index] = exp;
    }
    
    public void addActual(int index, Result act)
    {
        actual[index] = act;
    }
}
