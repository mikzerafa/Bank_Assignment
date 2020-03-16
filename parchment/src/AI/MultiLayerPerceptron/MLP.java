package AI.MultiLayerPerceptron;

import java.util.ArrayList;

public class MLP 
{
    private Layer inputLayer;
    private Layer outputLayer;
    private ArrayList<Layer> hiddenLayers; //Minimum 2;
    
    public MLP()
    {
        inputLayer = new Layer();
        outputLayer = new Layer();
        hiddenLayers = new ArrayList(); 
    }
    
    public void addHiddenLayer(int nodes)
    {
        Layer hiddenLayer = new Layer();
        hiddenLayer.addNodes(nodes);
    }
    
    public void loadInputLayer(int nodes)
    {
        inputLayer.addNodes(nodes);
    }
    
    public void LoadOutputLayer(int nodes)
    {
        outputLayer.addNodes(nodes);
    }
    
}
