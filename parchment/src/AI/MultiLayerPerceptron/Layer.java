package AI.MultiLayerPerceptron;

import java.util.ArrayList;

public class Layer 
{
    private ArrayList<Node> nodes;
    
    public Layer()
    {
        nodes = new ArrayList();
    }
    
    public int amount()
    {
        return nodes.size();
    }
    public void addNode()
    {
        Node node = new Node();
        nodes.add(node);
    }
    
    public void addNodes(int amount)
    {
        while(amount > 0)
        {
            addNode();
            amount--;
        }
    }
    
    public void setWeight(int index, double weight)
    {
        nodes.get(index).setWeight(weight);
    }
    
    
    
}
