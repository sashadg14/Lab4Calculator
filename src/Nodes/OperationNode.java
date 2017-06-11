package Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by alex o n 11.06.2017.
 */
public class OperationNode implements Node {
    int numberOfOperation=0;
    Node first;
    String operation;
    Node second;
    double value;
    DefaultMutableTreeNode leaf;
    public OperationNode(Node first,String operation,Node second,double value,int numberOfOperation){
        this.first=first;
        this.second=second;
        this.operation=operation;
        this.value=value;
        this.numberOfOperation=numberOfOperation;
        leaf = new DefaultMutableTreeNode(operation);
        leaf.add(first.getLeaf());
        leaf.add(second.getLeaf());
    }
    @Override
    public int getNumberOfOperation() {
        return numberOfOperation;
    }

    @Override
    public double getValue() {
        return value;
    }
    public String getAllExpression(){
        return String.valueOf(first.getValue()+operation+second.getValue());
    }

    @Override
    public DefaultMutableTreeNode getLeaf() {
    return leaf;
    }

    public void setAsLeaf(boolean isList){
        if(isList){
            leaf.removeAllChildren();
            leaf.setUserObject(String.valueOf(value));
        }else {
            leaf.add(first.getLeaf());
            leaf.add(second.getLeaf());
            leaf.setUserObject(operation);
            System.out.println(first.getLeaf()+" "+second.getLeaf());
        }
    };
}
