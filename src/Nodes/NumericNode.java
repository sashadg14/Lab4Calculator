package Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by alex o n 11.06.2017.
 */
public class NumericNode implements Node {
    int numberOfOperation=0;
    private double value=0;
    private final DefaultMutableTreeNode leaf;
    public NumericNode(double value){
        this.value=value;
        leaf=new DefaultMutableTreeNode(String.valueOf(value));
    }
    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getTotalExpression() {
        return String.valueOf(value);
    }

    @Override
    public DefaultMutableTreeNode getLeaf() {
    return leaf;
    }

    /*@Override
    public void setValue(int value) {
        this.value=value;
    }*/
}
