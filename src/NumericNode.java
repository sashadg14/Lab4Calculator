/**
 * Created by alex o n 11.06.2017.
 */
public class NumericNode implements Node {
    int numberOfOperation=0;
    double value=0;
    public NumericNode(double value){
        this.value=value;
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

    @Override
    public String getAllExpression() {
        return String.valueOf(value);
    }

    /*@Override
    public void setValue(int value) {
        this.value=value;
    }*/
}
