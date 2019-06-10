import java.util.ArrayList;
import java.util.Random;

public class Network {
    private ArrayList<Node[]> nodeList;
    private ArrayList<float[][]> weights;
    private Random r = new Random();
    private float[] bias;
    private float learningrate = 0.1f;

    Network(int[] layer){
        nodeList = new ArrayList<>();
        Node[] temp;
        for(int nodes : layer){
            temp = new Node[nodes];
            for(int i = 0; i<nodes; i++){
                temp[i] = new Node();
            }
            nodeList.add(temp);
        }

        weights = new ArrayList<>();
        bias = new float[layer.length-1];

        for(int i =0; i<layer.length-1; i++){
            bias[i] = -1 + r.nextFloat() * (1 - (-1));
            initializeWeights(layer[i+1], layer[i]);
        }
    }

    public void query(Node[] inputs){

        this.nodeList.set(0,inputs);

        forwardPropogation();

        for(Node n : nodeList.get(nodeList.size()-1)) System.out.println(n.toString());

    }

    public void train(Node[] inputList, Node[] targetList){

        this.nodeList.set(0,inputList);
        forwardPropogation();
        setErrors(targetList);

        for(int i =nodeList.size()-1;i>0; i--){
            deltaWeight(i);
        }

    }

    public ArrayList<float[][]> getWeights() {
        return weights;
    }

    //------------- helper functions -----------------

    // her så vil den gi alle nodene verdien som de fortjener utifra vektene som er koblet til de.
    private void forwardPropogation(){
        for(int i = 0; i<nodeList.size()-1; i++){
            multiply(i);
        }
    }

    private void initializeWeights(int row, int column){
        float[][] temp = new float[row][column];
        for (int i = 0; i<row; i++){

            for (int j = 0; j<column; j++){
                float random = -1 + r.nextFloat() * (1 - (-1));
                temp[i][j] = random;
            }
        }
        weights.add(temp);
    }

    private void multiply(int nodeIndex){
        Node[] nodes = nodeList.get(nodeIndex);
        float[][] weight = weights.get(nodeIndex);


        // replacing the next nodes with this temp layer
        for(int i = 0; i < weight.length; i++){
            float a = 0;
            for(int j = 0; j<weight[i].length;j++){
                a += weight[i][j] * nodes[j].getValue();
            }
            // her setter jeg inn en ny node med den ny verdiene
            nodeList.get(nodeIndex+1)[i].setValue(sigmoid(a + bias[nodeIndex]));
        }
    }


    private float sigmoid(double value){
        return (float)(1/( 1 + Math.pow(Math.E,(-1*value))));
    }

    private void setErrors(Node[] targetList){

        Node[] nodes = nodeList.get(nodeList.size()-1);

        if (nodes.length != targetList.length) throw new IllegalArgumentException("the length of targetlist and accual is not the same!");

        for(int i = 0; i<nodes.length; i++){
            float error = targetList[i].getValue() - nodes[i].getValue();
            nodes[i].setError(error);
        }

        for(int i = nodeList.size()-1; i>0; i--){
            float[][] transposed = transpose(weights.get(i-1));
            multiplyMatrix(transposed, i);
        }

    }

    private void multiplyMatrix(float[][] weights, int nodeIndex){
        Node[] nodes = nodeList.get(nodeIndex);

        float error = 0.0f;
        for(int i = 0; i<weights.length; i++){
            for(int j = 0; j<weights[i].length; j++){
                error += weights[i][j] * nodes[j].getError();
            }

            nodeList.get(nodeIndex-1)[i].setError(error);
            error = 0.0f;
        }

    }

    private float[][] transpose(float[][] weights){

        int rows = weights.length;
        int columns = weights[0].length;

        float[][] temp = new float[columns][rows];

        for (int i =0; i<columns; i++){
            for (int j = 0; j<rows; j++){
                temp[i][j] = weights[j][i];
            }
        }
        return temp;
    }


    private void deltaWeight(int nodeindex){
        Node[] nodes = nodeList.get(nodeindex);
        Node[] prevNodes = nodeList.get(nodeindex-1);
        float[] temp = new float[nodes.length];

        float[][] newWeights = new float[temp.length][prevNodes.length];

        for(int i = 0; i< temp.length; i++){
            temp[i] = (float)nodes[i].getError() * nodes[i].getValue() * (1-nodes[i].getValue());
        }

        for (int i = 0; i<nodes.length; i++){
            for(int j = 0; j<prevNodes.length; j++){
                newWeights[i][j] = learningrate*(-(temp[i] * prevNodes[j].getValue()));

            }
        }


        float[][] oldWeights = weights.get(nodeindex-1);

        for (int i = 0; i<oldWeights.length; i++){
            for (int j =0 ; j<oldWeights[i].length; j++){
                oldWeights[i][j] = oldWeights[i][j] - newWeights[i][j];
            }

        }

        weights.set(nodeindex-1, oldWeights);
    }
}
