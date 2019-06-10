public class Node {

    private float value = 0;
    private float error;

    public Node(){}

    public Node(float value){
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public double getError() {
        return error;
    }

    public void setError(float error) {
        this.error = error;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("node: ");
        str.append(value);
        return str.toString();
    }
}
