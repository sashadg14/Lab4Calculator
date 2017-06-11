/**
 * Created by alex o n 11.06.2017.
 */
public class OperationNode implements Node {
    int numberOfOperation=0;
    Node first;
    String operation;
    Node second;
    double value;
    public OperationNode(Node first,String operation,Node second,double value,int numberOfOperation){
        this.first=first;
        this.second=second;
        this.operation=operation;
        this.value=value;
        this.numberOfOperation=numberOfOperation;
    }
    @Override
    public int getNumberOfOperation() {
        return numberOfOperation;
    }

    @Override
    public void setNumberOfOperation(int numberOfOperation) {
    this.numberOfOperation=numberOfOperation;
    }

    @Override
    public double getValue() {
        return value;
    }
    public String getAllExpression(){
        return String.valueOf(first.getValue()+operation+second.getValue());
    }
   /* @Override
    public void setValue(int value){};*/
}
