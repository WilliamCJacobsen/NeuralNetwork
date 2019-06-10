# NeuralNetwork
A basic neural network. hacked together to learn the basics of how a neural network operates. It comes with a file reader (read and write to a .txt) and a Image reader to read the normalized values of each pixel. 

basic application: 
```java
public class main {
    public static void main(String[] args) {
    
    Node[] expectedNodes = new Node[10];
        for (int i = 0; i<outputNodes.length; i++){
            expectedNodes[i] = new Node();
        }
        
        //makes a random node have value = 1. The rest is 0.
        expectedNodes[3].setValue(1);
        
        // the layers include the Inputlayer and the Outputlayer.
        int[] layers = {3, 16 ,16,10};
        float learningrate = 0.03f;
        
        Network network = new Network(layers, learningrate);
        
        Node[] inputNodes = new Node[3];
        
        //Here the magic happens. 
        network.train(inputnodes, outputNodes);
        
    }
}
```
Read image: 
```java
  public class main {
    public static void main(String[] args) {
    String path = System.getProperty("user.dir"));
    
    ImageReader imageReader = new ImageReader(ImageIO.read(new File(path+"LOCATION OF IMAGE")));
    Node[] inputPixles = imageReader.imageAsNodes();
    

    network.query(inputPixles);
    
    }
}
```
